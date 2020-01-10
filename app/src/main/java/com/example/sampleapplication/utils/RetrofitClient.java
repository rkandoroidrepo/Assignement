package com.example.sampleapplication.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static String BASE_URL = "https://dl.dropboxusercontent.com/";

    public static Retrofit getClient() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
