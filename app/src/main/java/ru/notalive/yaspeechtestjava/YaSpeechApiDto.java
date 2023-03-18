package ru.notalive.yaspeechtestjava;

import com.google.gson.annotations.SerializedName;

public class YaSpeechApiDto {
    @SerializedName("result")
    public String result;

    @SerializedName("error_code")
    public String errorCode;

    @SerializedName("error_message")
    public String errorMessage;
}
