package ru.notalive.yaspeechtestjava;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.ByteString;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static ChatFragment chat;
    public Button button;

    public Retrofit client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chat = new ChatFragment();
        client = new Retrofit.Builder().baseUrl("https://stt.api.cloud.yandex.net/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        YaSpeechApi service = client.create(YaSpeechApi.class);
        InputStream istream = getResources().openRawResource(R.raw.test_audio2);
        byte[] data = new byte[0];
        try {
            data = new byte[istream.available()];
            istream.read(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainerView, chat).commit();
        button = findViewById(R.id.ans_button);

        byte[] finalData = data;
        button.setOnClickListener(view -> {
            ChatFragment.adapter.addMessage(new Message(MESSAGE_TYPE.ANSWER,"            "));
            long id = ChatFragment.adapter.getItemId(ChatFragment.adapter.getItemCount() -1 );
                service.recognize(
                        "general",
                        "b1gm9uknsdls90cl8bdg",
                        "ru-RU",
                        RequestBody.create(MediaType.parse("audio/*"
                        ), finalData),
                        "t1.9euelZqKz4uWmsudm56Qm5yRk5eQyO3rnpWai5iWjcqPkZqNjMyJm8yRkJvl8_dCEypf-e9KSmdM_N3z9wJCJ1_570pKZ0z8.B2YRVZjHaDvD6ATg9MrJltlBzLcA3SgQ0If703OhswyZmn8X6woo0MlrGh3bw3MX4aP9FaTol5HsAZXb9VIrDg"
                ).enqueue(new Callback<YaSpeechApiDto>() {
                    @Override
                    public void onResponse(Call<YaSpeechApiDto> call, Response<YaSpeechApiDto> response) {
                        Toast.makeText(MainActivity.this, String.valueOf(response.code()), Toast.LENGTH_LONG).show();
                        if(response.isSuccessful() && response.code() == 200) {
    //                        assert response.body() != null;
                            ChatFragment.adapter.changeMessage((int)id, response.body().result);
                        }
                    }

                    @Override
                    public void onFailure(Call<YaSpeechApiDto> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_LONG).show();
                    }
                });
        });

    }
}
