package ru.notalive.yaspeechtestjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.content.pm.PackageManager;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static ChatFragment chat;
    public Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chat = new ChatFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainerView, chat).commit();
        button = findViewById(R.id.ans_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChatFragment.adapter.addMessage(new Message(MESSAGE_TYPE.QUESTION, "FKJ"));
                ChatFragment.adapter.addMessage(new Message(MESSAGE_TYPE.ANSWER, "FKJ"));
            }
        });
    }
}
