package com.ipnx.ipnxmobile.retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.ipnx.ipnxmobile.retrofit.MyApiEndpointInterface.BASE_URL;

/**
 * Created by PHENOMENON on 2/18/2018.
 */

public class RetrofitUtils {

    public static  Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())//gson))
            .build();

    public static MyApiEndpointInterface getService(){
        return retrofit.create(MyApiEndpointInterface.class);
    }

}
