package com.kobra.wegmanswear.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {
    private static final ApiManager API_MANAGER = new ApiManager();
    public static final String BASE_URL = "http://wegman-watch.appspot.com";
//    public static final String BASE_URL = "https://c05002b7-45b1-4ea5-a352-7cfd426db574.mock.pstmn.io";
    public static ApiManager getInstance() {
        return API_MANAGER;
    }

    public ApiService service;

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
}
