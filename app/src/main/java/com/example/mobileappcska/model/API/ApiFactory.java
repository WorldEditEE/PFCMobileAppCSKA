package com.example.mobileappcska.model.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactory {

    private static ApiFactory apiFactory;
    private static Retrofit retrofit;
    private static final String
            BASE_URL = "https://apiv2.allsportsapi.com/football/";

    private ApiFactory(){

        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL).build();

    }

    public static ApiFactory getInstance(){

        if(apiFactory == null){
            apiFactory = new ApiFactory();
        }

        return apiFactory;
    }

    public ApiService getApiService(){
        return retrofit.create(ApiService.class);
    }

}
