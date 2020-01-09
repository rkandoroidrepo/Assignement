package com.example.sampleapplication.modal;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class RetrofitClient {
    private static String BASE_URL = "https://dl.dropboxusercontent.com/";

    static Retrofit getClient() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
