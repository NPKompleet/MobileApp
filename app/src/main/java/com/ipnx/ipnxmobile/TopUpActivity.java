package com.ipnx.ipnxmobile;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ipnx.ipnxmobile.models.requests.AddPaymentRequestValues;
import com.ipnx.ipnxmobile.models.requests.LoginRequestValues;
import com.ipnx.ipnxmobile.models.requests.Request;
import com.ipnx.ipnxmobile.models.responses.Response;
import com.ipnx.ipnxmobile.models.responses.addcash.AddCashResponse;
import com.ipnx.ipnxmobile.models.responses.login.TelephonyService;
import com.ipnx.ipnxmobile.retrofit.MyApiEndpointInterface;
import com.ipnx.ipnxmobile.retrofit.RetrofitUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;

import static com.ipnx.ipnxmobile.utils.ApplicationUtils.ACTION_TOP_UP;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_LOGIN;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_VOICE_SERVICE;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.getRandomAlphabeticString;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.networkActive;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.userProfile;

public class TopUpActivity extends BaseActivity{

    @BindView(R.id.page_subtitle)
    TextView pageSubtitle;

    @BindView(R.id.package_balance)
    TextView balance;

    @BindView(R.id.phone_number)
    TextView phoneNumber;

    @BindView(R.id.topUp_amount)
    EditText amount;

    LoginRequestValues loginValues;
    TelephonyService service;

    MyApiEndpointInterface myApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);
        ButterKnife.bind(this);

        service = getIntent().getParcelableExtra(EXTRA_KEY_VOICE_SERVICE);
        loginValues = getIntent().getParcelableExtra(EXTRA_KEY_LOGIN);

        pageSubtitle.setText(service.getPackageName().split("  ")[0]);
        phoneNumber.setText(service.getUsername());
        balance.setText("₦" + service.getPackageBalance());

    }

    public void makePayment(View view){
        if (!networkActive(this)){
            Toast.makeText(this, "Network is unavailable", Toast.LENGTH_SHORT).show();
            return;
        }
        String amountToPay = amount.getText().toString();
        if (amountToPay.isEmpty()){
            Toast.makeText(this, "Amount cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }
        long unixTime = System.currentTimeMillis() / 1000L;
        String customerNumber = userProfile.getCustomerNumber();
        String idAndTime = customerNumber + unixTime;
        String transRef = idAndTime + getRandomAlphabeticString(26-idAndTime.length());
        System.out.println(customerNumber);
        System.out.println(unixTime);
        System.out.println(transRef);
//        PaymentCallback paymentCallback = new PaymentCallback(TopUpActivity.this, this, transRef);
//        RequestOptions options = RequestOptions.builder()
//                .setClientId("IKIA67A8FBB81191FC4F1226098245E9541711B3E959")
//                .setClientSecret("FQ+X6B28Y/HJZdsDa1SsbKI23W+pIOLcyxBhGgb8Q9U=")
//                .build();
//        PayWithCard payWithCard = new PayWithCard(this, customerNumber,
//                pageSubtitle.getText().toString(), amountToPay, "NGN", transRef, options, paymentCallback);
//
//        payWithCard.start();
        Toast.makeText(TopUpActivity.this, "Clicked", Toast.LENGTH_SHORT).show();

    }

    public void cancelPayment(View view){
        amount.setText("");
    }

//    @Override
//    public void postPayment(PurchaseResponse response) {
//        Date date= new Date();
//        DateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String dateString = dateFormat.format(date);
//
//        Request topUpRequest = new Request();
//        AddPaymentRequestValues requestValues = new AddPaymentRequestValues();
//        requestValues.setCUsername(userProfile.getUserName());
//        requestValues.setCPassword(userProfile.getPassword());
//        requestValues.setCCustomerNumber(userProfile.getCustomerNumber());
//        requestValues.setCPackageNumber(service.getPkgnum() + "");
//        requestValues.setCMerchantReference(response.getTransactionRef());
//        requestValues.setCCardNumber(response.toString());
//        requestValues.setCRetrievalReferenceNumber(RandomString.numeric(12));
//        requestValues.setCPaymentReference(response.getTransactionIdentifier());
//        requestValues.setCAmount(Long.parseLong(response.getAmount()));
//        requestValues.setCTransactionDate(dateString);
//
//        topUpRequest.setCustomValues(requestValues);
//        topUpRequest.setAction(ACTION_TOP_UP);
//
//        myApi= RetrofitUtils.getService();
//        Call<AddCashResponse> call = myApi.topUp(topUpRequest);
//        call.enqueue(new Callback<AddCashResponse>() {
//            @Override
//            public void onResponse(Call<AddCashResponse> call, retrofit2.Response<AddCashResponse> response) {
//                Response returnedResponse = response.body();
//                if (returnedResponse == null) return;
//                if (returnedResponse.getResponseCode().equals("0")){
//                    Toast.makeText(TopUpActivity.this, "Payment has been posted", Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(TopUpActivity.this, "Problem posting payment", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<AddCashResponse> call, Throwable t) {
//                Toast.makeText(TopUpActivity.this, "Problem posting payment", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }

    public void onBackClicked(View view){
        finish();
    }
}
