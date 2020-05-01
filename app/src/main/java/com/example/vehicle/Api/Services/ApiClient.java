package com.example.vehicle.Api.Services;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient { // diger yerlerden cagırılınca baglantı gerceklescek
    private static Retrofit retrofit = null;
    public static Retrofit getClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        retrofit = new Retrofit.Builder().baseUrl("http://192.168.43.93:45457/").addConverterFactory(
                GsonConverterFactory.create()
        ).client(client).build();
        return retrofit;
    }

    public static UserService getApiService() {
        return retrofit.create(UserService.class);
    }
}
