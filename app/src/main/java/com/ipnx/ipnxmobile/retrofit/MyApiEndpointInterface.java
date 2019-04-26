package com.ipnx.ipnxmobile.retrofit;


import com.ipnx.ipnxmobile.models.responses.Response;
import com.ipnx.ipnxmobile.models.responses.cdr.CallRecordResponse;
import com.ipnx.ipnxmobile.models.responses.forgotpassword.ForgotPasswordResponse;
import com.ipnx.ipnxmobile.models.responses.login.LoginResponse;
import com.ipnx.ipnxmobile.models.requests.Request;
import com.ipnx.ipnxmobile.models.responses.transactionhistory.TransactionResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface MyApiEndpointInterface {
    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter

//    String BASE_URL= "http://10.50.5.250/ipnxmobilebridge/api/v1/";
    String BASE_URL= "http://mbridge.ipnxnigeria.net/ipnxmobilebridge/api/v1/";


    @POST("auth")
    Call<LoginResponse> loginUser(@Body Request loginRequest);

    @POST("transaction")
    Call<TransactionResponse> fetchTransactionHistory(@Body Request transactionRequest);

    @POST("forgotpass")
    Call<ForgotPasswordResponse> requestPasswordToken(@Body Request forgotPasswordRequest);

    @POST("resetpass")
    Call<Response> resetPassword(@Body Request resetPasswordRequest);

    @POST("cdr")
    Call<CallRecordResponse> fetchCallDetailRecord(@Body Request viewCDRRequest);
}
