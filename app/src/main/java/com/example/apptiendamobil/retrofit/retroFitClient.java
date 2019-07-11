package com.example.apptiendamobil.retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class retroFitClient {
    private static Retrofit Instance;

    public static Retrofit getInstance() {
        if(Instance == null){
            Instance = new Retrofit.Builder().baseUrl(("http://192.168.1.5:8000/v1.0/"))
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return Instance;
    }
}
