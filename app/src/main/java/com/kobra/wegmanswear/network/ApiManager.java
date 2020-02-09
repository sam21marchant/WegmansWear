package com.kobra.wegmanswear.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {
    private static final ApiManager API_MANAGER = new ApiManager();
    public static final String BASE_URL = "https://wegman-watch.appspot.com";
    public static ApiManager getInstance() {
        return API_MANAGER;
    }

    private ApiService service;

    private ApiManager() {
        buildRetrofit();
    }

    private void buildRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        this.service = retrofit.create(ApiService.class);
    }

    public ApiService getService() {
        return service;
    }
}
