package com.ipnx.ipnxmobile.retrofit;


import com.ipnx.ipnxmobile.models.responses.Response;
import com.ipnx.ipnxmobile.models.responses.addcash.AddCashResponse;
import com.ipnx.ipnxmobile.models.responses.cdr.CallRecordResponse;
import com.ipnx.ipnxmobile.models.responses.datausage.DataHistoryResponse;
import com.ipnx.ipnxmobile.models.responses.datausage.DataUsageResponse;
import com.ipnx.ipnxmobile.models.responses.forgotpassword.ForgotPasswordResponse;
import com.ipnx.ipnxmobile.models.responses.login.LoginResponse;
import com.ipnx.ipnxmobile.models.requests.Request;
import com.ipnx.ipnxmobile.models.responses.serviceplan.ServicePlanResponse;
import com.ipnx.ipnxmobile.models.responses.subscriptionsettings.SubscriptionSettingsResponse;
import com.ipnx.ipnxmobile.models.responses.transactionhistory.TransactionResponse;
import com.ipnx.ipnxmobile.models.responses.wifipassword.WifiPasswordResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface MyApiEndpointInterface {
    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter

//    String BASE_URL= "https://10.50.5.250/ipnxmobilebridge/api/v1/";
    String BASE_URL= "https://mbridge.ipnxnigeria.net/ipnxmobilebridge/api/v1/";


    @POST("auth")
    Call<LoginResponse> loginUser(@Body Request loginRequest);

    @POST("transaction")
    Call<TransactionResponse> fetchTransactionHistory(@Body Request transactionRequest);

    @POST("forgotpass")
    Call<ForgotPasswordResponse> requestPasswordToken(@Body Request forgotPasswordRequest);

    @POST("resetpass")
    Call<Response> resetPassword(@Body Request resetPasswordRequest);

    @POST("changepass")
    Call<Response> changePassword(@Body Request changePasswordRequest);

    @POST("cdr")
    Call<CallRecordResponse> fetchCallDetailRecord(@Body Request viewCDRRequest);

    @POST("data_usage")
    Call<DataUsageResponse> fetchDataInfo(@Body Request dataRequest);

    @POST("history")
    Call<DataHistoryResponse> fetchDataHistory(@Body Request dataHistoryRequest);

    @POST("feedback")
    Call<Response> sendFeedback(@Body Request feedbackRequest);

    @POST("addcash")
    Call<AddCashResponse> addPayment(@Body Request addPaymentRequest);

    @POST("topup")
    Call<AddCashResponse> topUp(@Body Request topUpRequest);

    @POST("duasettings")
    Call<SubscriptionSettingsResponse> subscriptionSettings(@Body Request subscriptionSettingsRequest);

    @POST("changewifi")
    Call<WifiPasswordResponse> changeWifiPassword(@Body Request wifiPasswordRequest);

    @POST("serviceplans")
    Call<ServicePlanResponse> getServicePlans(@Body Request fetchServicePlansRequest);

    @POST("changeplan")
    Call<Response> changePlan(@Body Request changePlanRequest);
}
