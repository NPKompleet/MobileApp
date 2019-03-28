package com.ipnx.ipnxmobile.retrofit;


import com.ipnx.ipnxmobile.models.responses.LoginResponse;
import com.ipnx.ipnxmobile.models.requests.Request;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by PHENOMENON on 2/11/2018.
 */

public interface MyApiEndpointInterface {
    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter

    String BASE_URL= "http://10.50.5.250/ipnxmobilebridge/api/v1/";


    @POST("auth")
    Call<LoginResponse> loginUser(@Body Request loginRequest);

//    @POST("http://197.210.12.72:8080/fait/mobileApi/")
//    Call<List<Asset>> getAssets(@Body Query query);
//
//    @POST("http://197.210.12.72:8080/fait/mobileApi/")
//    Call<List<Location>> getLocations(@Body Query query);
//
//    @POST("http://197.210.12.72:8080/fait/mobileApi/")
//    Call<List<Customer>> getCustomers(@Body Query query);
//
//    @POST("http://197.210.12.72:8080/fait/mobileApi/")
//    Call<List<PrintADI>> getADIForms(@Body ADIQuery query);
//
//    @POST("http://197.210.12.72:8080/fait/mobileApi/")
//    Call<List<PrintPTN>> getPTNForms(@Body ADIQuery query);
//
//    @POST("http://197.210.12.72:8080/fait/mobileApi/")
//    Call<Template> getTemplate(@Body Query query);
//
//    @POST("http://197.210.12.72:8080/fait/mobileApi/")
//    Call<List<String>> insertADI(@Body InsertUpdateQuery insertQuery);
//
//    @POST("http://197.210.12.72:8080/fait/mobileApi/")
//    Call<List<String>> insertPTN(@Body InsertUpdateQuery insertQuery);
//
//    @POST("http://197.210.12.72:8080/fait/mobileApi/")
//    Call<List<String>> insertLMF(@Body InsertUpdateQuery insertQuery);
//
//    @POST("http://197.210.12.72:8080/fait/mobileApi/")
//    Call<List<User>> getUserList(@Body MainAccess access);
}
