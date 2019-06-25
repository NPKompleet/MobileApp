package com.ipnx.ipnxmobile;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.interswitchng.sdk.auth.Passport;
import com.interswitchng.sdk.model.RequestOptions;
import com.interswitchng.sdk.payment.Payment;
import com.interswitchng.sdk.payment.android.inapp.PayWithCard;
import com.interswitchng.sdk.payment.model.PurchaseResponse;
import com.interswitchng.sdk.util.RandomString;
import com.ipnx.ipnxmobile.models.requests.AddPaymentRequestValues;
import com.ipnx.ipnxmobile.models.requests.Request;
import com.ipnx.ipnxmobile.models.responses.addcash.AddCashResponse;
import com.ipnx.ipnxmobile.models.responses.login.InternetService;
import com.ipnx.ipnxmobile.payment.PaymentCallback;
import com.ipnx.ipnxmobile.payment.PostPaymentHandler;
import com.ipnx.ipnxmobile.retrofit.MyApiEndpointInterface;
import com.ipnx.ipnxmobile.retrofit.RetrofitUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;

import static com.ipnx.ipnxmobile.utils.ApplicationUtils.ACTION_ADD_PAYMENT;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_INTERNET_SERVICE;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.getRandomAlphabeticString;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.networkActive;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.userProfile;

public class AddCashActivity extends BaseActivity implements PostPaymentHandler {

    TextView planPrice;
    TextView balance;
    EditText amount;
    TextView pageSubtitle;
    InternetService service;

    MyApiEndpointInterface myApi;
    float balanceCalculated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cash);
        Passport.overrideApiBase(Passport.QA_API_BASE);
        Payment.overrideApiBase(Payment.QA_API_BASE);

        planPrice = findViewById(R.id.renew_plan_price);
        balance = findViewById(R.id.renew_balance);
        amount = findViewById(R.id.renew_amount);
        pageSubtitle = findViewById(R.id.page_subtitle);

        service = getIntent().getParcelableExtra(EXTRA_KEY_INTERNET_SERVICE);
        pageSubtitle.setText("Service Plan: " + service.getPackageName().split("  ")[0]);
        planPrice.setText("₦" + service.getAmount());
        balanceCalculated = Float.parseFloat(service.getPackageLevelUnappliedCredits()) +
                Float.parseFloat(service.getPackageLevelUnappliedPayments());
        balance.setText("₦" + Math.round(balanceCalculated));


    }


    public void makePayment(View view) {
        if (!networkActive(this)) {
            Toast.makeText(this, "Network is unavailable", Toast.LENGTH_SHORT).show();
            return;
        }
        String amountToPay = amount.getText().toString();
        if (amountToPay.isEmpty()){
            Toast.makeText(this, "Amount cannot be zero or empty", Toast.LENGTH_SHORT).show();
            return;
        }
        long unixTime = System.currentTimeMillis() / 1000L;
        String customerNumber = userProfile.getCustomerNumber();
        String idAndTime = customerNumber + unixTime;
        String transRef = idAndTime + getRandomAlphabeticString(26-idAndTime.length());
        System.out.println(customerNumber);
        System.out.println(unixTime);
        System.out.println(transRef);
        PaymentCallback paymentCallback = new PaymentCallback(AddCashActivity.this, this, transRef);
        RequestOptions options = RequestOptions.builder()
                .setClientId("IKIA67A8FBB81191FC4F1226098245E9541711B3E959")
                .setClientSecret("FQ+X6B28Y/HJZdsDa1SsbKI23W+pIOLcyxBhGgb8Q9U=")
                .build();
        PayWithCard payWithCard = new PayWithCard(this, customerNumber,
                pageSubtitle.getText().toString(), amountToPay, "NGN", transRef, options, paymentCallback);

        payWithCard.start();
        Toast.makeText(AddCashActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
    }


    public void cancelPayment(View view) {
        amount.setText("");
    }

    public void onBackClicked(View view) {
        finish();
    }

    @Override
    public void postPayment(PurchaseResponse response) {
        Date date= new Date();
        DateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = dateFormat.format(date);

        Request addPaymentRequest = new Request();
        AddPaymentRequestValues requestValues = new AddPaymentRequestValues();
        requestValues.setCUsername(userProfile.getUserName());
        requestValues.setCPassword(userProfile.getPassword());
        requestValues.setCCustomerNumber(userProfile.getCustomerNumber());
        requestValues.setCPackageNumber(String.valueOf(service.getPkgnum()));
        requestValues.setCMerchantReference(response.getTransactionRef());
        requestValues.setCCardNumber(response.toString());
        requestValues.setCRetrievalReferenceNumber(RandomString.numeric(12));
        requestValues.setCPaymentReference(response.getTransactionIdentifier());
        requestValues.setCAmount(Long.parseLong(response.getAmount()));
        requestValues.setCTransactionDate(dateString);

        addPaymentRequest.setCustomValues(requestValues);
        addPaymentRequest.setAction(ACTION_ADD_PAYMENT);

        myApi= RetrofitUtils.getService();
        Call<AddCashResponse> call = myApi.addPayment(addPaymentRequest);
        call.enqueue(new Callback<AddCashResponse>() {
            @Override
            public void onResponse(Call<AddCashResponse> call, retrofit2.Response<AddCashResponse> response) {
                AddCashResponse returnedResponse = response.body();
                if (returnedResponse == null) return;
                if (returnedResponse.getResponseCode().equals("0")){
                    Toast.makeText(AddCashActivity.this, "Payment has been posted", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(AddCashActivity.this, "Problem posting payment", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddCashResponse> call, Throwable t) {
                Toast.makeText(AddCashActivity.this, "Problem posting payment", Toast.LENGTH_SHORT).show();
            }
        });

    }
}