package ru.notalive.yaspeechtestjava;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface YaIamApi {

    @POST("speech/v1/stt:recognize")
    Call<YaSpeechApiDto> recognize(
            @Query("topic") String topic,
            @Query("folderId") String fId,
            @Query("lang") String lang,
            @Body RequestBody voice,
            @Header("Authorization") String auth
    );
}
