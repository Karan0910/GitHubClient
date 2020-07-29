package com.githubclient.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit oAuthRetrofit;

    private static Retrofit retrofit;

    private static OkHttpClient okHttpClient;

    private static Gson gson;

    private static final String BASE_URL = "https://github.com/login/oauth/";

    public static Retrofit getRetrofitInstance() {
        if (oAuthRetrofit == null) {
            oAuthRetrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(getOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();
        }

        return oAuthRetrofit;
    }

    public static Retrofit getRetrofitInstanceForRepo() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .client(getOkHttpClient())
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();
        }

        return retrofit;
    }

    public static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder().build();
        }

        return okHttpClient;
    }

    private static Gson getGson() {
        if (gson == null) {
            gson = new GsonBuilder().create();
        }

        return gson;
    }
}