package com.example.kabisa.registre.retrofitutil;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL ="10.0.2.2/base de donnees";
    private static Retrofit retrofit = null;
    public static Retrofit getApiClient()
    {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
