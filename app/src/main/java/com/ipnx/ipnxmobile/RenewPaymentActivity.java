package com.ipnx.ipnxmobile;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.ipnx.ipnxmobile.models.requests.AddPaymentRequestValues;
import com.ipnx.ipnxmobile.models.requests.Request;
import com.ipnx.ipnxmobile.models.responses.addcash.AddCashResponse;
import com.ipnx.ipnxmobile.models.responses.login.InternetService;
import com.ipnx.ipnxmobile.retrofit.MyApiEndpointInterface;
import com.ipnx.ipnxmobile.retrofit.RetrofitUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;

import static com.ipnx.ipnxmobile.utils.ApplicationUtils.ACTION_RENEW_NOW;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_INTERNET_SERVICE;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.getRandomAlphabeticString;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.networkActive;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.userProfile;

public class RenewPaymentActivity extends BaseActivity{

    TextView planPrice;
    TextView balance;
    EditText amount;
    EditText numOfMonths;
    TextView pageSubtitle;
    InternetService service;

    MyApiEndpointInterface myApi;
    float balanceCalculated;

    PopupMenu monthsPopUp;
    int monthCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renew_payment);

        planPrice = findViewById(R.id.renew_plan_price);
        balance = findViewById(R.id.renew_balance);
        amount = findViewById(R.id.renew_amount);
        numOfMonths = findViewById(R.id.renew_months);
        pageSubtitle = findViewById(R.id.page_subtitle);

        service = getIntent().getParcelableExtra(EXTRA_KEY_INTERNET_SERVICE);
        pageSubtitle.setText("Service Plan: " + service.getPackageName().split("  ")[0]);
        planPrice.setText("₦" + service.getAmount());
        balanceCalculated = Float.parseFloat(service.getPackageLevelUnappliedCredits()) +
                Float.parseFloat(service.getPackageLevelUnappliedPayments());
        balance.setText("₦" + Math.round(balanceCalculated));

        monthsPopUp = new PopupMenu(this, numOfMonths);
        createMonthsOptions();

    }

    private void createMonthsOptions() {
        monthsPopUp.getMenu().add(Menu.NONE, 1, Menu.NONE,   "1 Month");
        for (int i=2; i<= 12; i++){
            monthsPopUp.getMenu().add(Menu.NONE, i, Menu.NONE, i + " Months");
        }

        monthsPopUp.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                numOfMonths.setText(menuItem.getTitle());
                monthCount = menuItem.getItemId();
                float currentBill= menuItem.getItemId() * Float.parseFloat(service.getAmount());
                float amountPayable = balanceCalculated > currentBill? 0 : currentBill - balanceCalculated;
                amount.setText(String.valueOf(Math.round(amountPayable)));
                numOfMonths.setText(menuItem.getTitle());
                return true;
            }
        });
    }

    public void onNumberOfMonthsClicked(View view){
        monthsPopUp.show();
    }

    public void makePayment(View view) {
        if (!networkActive(this)) {
            Toast.makeText(this, "Network is unavailable", Toast.LENGTH_SHORT).show();
            return;
        }
        String amountToPay = amount.getText().toString();
        if (amountToPay.isEmpty() || amountToPay.equals("0")){
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
//        PaymentCallback paymentCallback = new PaymentCallback(RenewPaymentActivity.this, this, transRef);
//        RequestOptions options = RequestOptions.builder()
//                .setClientId("IKIA67A8FBB81191FC4F1226098245E9541711B3E959")
//                .setClientSecret("FQ+X6B28Y/HJZdsDa1SsbKI23W+pIOLcyxBhGgb8Q9U=")
//                .build();
//        PayWithCard payWithCard = new PayWithCard(this, customerNumber,
//                pageSubtitle.getText().toString(), amountToPay, "NGN", transRef, options, paymentCallback);
//
//        payWithCard.start();
//        Toast.makeText(RenewPaymentActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
    }


    public void cancelPayment(View view) {
        amount.setText("");
    }

    public void onBackClicked(View view) {
        finish();
    }

//    @Override
//    public void postPayment(PurchaseResponse response) {
//        Date date= new Date();
//        DateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String dateString = dateFormat.format(date);
//
//        Request addPaymentRequest = new Request();
//        AddPaymentRequestValues requestValues = new AddPaymentRequestValues();
//        requestValues.setCUsername(userProfile.getUserName());
//        requestValues.setCPassword(userProfile.getPassword());
//        requestValues.setCCustomerNumber(userProfile.getCustomerNumber());
//        requestValues.setCPackageNumber(String.valueOf(service.getPkgnum()));
//        requestValues.setCMerchantReference(response.getTransactionRef());
//        requestValues.setCCardNumber(response.toString());
//        requestValues.setCRetrievalReferenceNumber(RandomString.numeric(12));
//        requestValues.setCPaymentReference(response.getTransactionIdentifier());
//        requestValues.setCAmount(Long.parseLong(response.getAmount()));
//        requestValues.setCTransactionDate(dateString);
//        requestValues.setCNumberOfMonths(String.valueOf(monthCount));
//
//        addPaymentRequest.setCustomValues(requestValues);
//        addPaymentRequest.setAction(ACTION_RENEW_NOW);
//
//        myApi= RetrofitUtils.getService();
//        Call<AddCashResponse> call = myApi.addPayment(addPaymentRequest);
//        call.enqueue(new Callback<AddCashResponse>() {
//            @Override
//            public void onResponse(Call<AddCashResponse> call, retrofit2.Response<AddCashResponse> response) {
//                AddCashResponse returnedResponse = response.body();
//                if (returnedResponse == null) return;
//                if (returnedResponse.getResponseCode().equals("0")){
//                    Toast.makeText(RenewPaymentActivity.this, "Payment has been posted", Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(RenewPaymentActivity.this, "Problem posting payment", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<AddCashResponse> call, Throwable t) {
//                Toast.makeText(RenewPaymentActivity.this, "Problem posting payment", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }
}
