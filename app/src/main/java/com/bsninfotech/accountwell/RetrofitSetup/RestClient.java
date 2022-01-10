package com.bsninfotech.accountwell.RetrofitSetup;

import com.bsninfotech.accountwell.ApplicationControllerAdmin;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient
{

    public static String BASE_URL = ApplicationControllerAdmin.getServicesapplication();
    private ApiService apiService;
    Retrofit retrofit=null;
    public RestClient()
    {

        retrofit = new Retrofit.Builder()
                .baseUrl("http://"+BASE_URL+"/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public ApiService getApiService()
    {
        return apiService;
    }
}