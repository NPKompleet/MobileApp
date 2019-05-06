package com.ipnx.ipnxmobile;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.interswitchng.sdk.model.RequestOptions;
import com.interswitchng.sdk.payment.android.inapp.PayWithCard;
import com.interswitchng.sdk.payment.model.PurchaseResponse;
import com.interswitchng.sdk.util.RandomString;
import com.ipnx.ipnxmobile.models.requests.AddPaymentRequestValues;
import com.ipnx.ipnxmobile.models.requests.LoginRequestValues;
import com.ipnx.ipnxmobile.models.requests.Request;
import com.ipnx.ipnxmobile.models.responses.Response;
import com.ipnx.ipnxmobile.models.responses.addcash.AddCashResponse;
import com.ipnx.ipnxmobile.models.responses.login.TelephonyService;
import com.ipnx.ipnxmobile.payment.PaymentCallback;
import com.ipnx.ipnxmobile.payment.PostPaymentHandler;
import com.ipnx.ipnxmobile.retrofit.MyApiEndpointInterface;
import com.ipnx.ipnxmobile.retrofit.RetrofitUtils;
import com.ipnx.ipnxmobile.wifianalyzer.WifiAnalyzerActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;

import static com.ipnx.ipnxmobile.utils.ApplicationUtils.ACTION_ADD_PAYMENT;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.APP_URL;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_LOGIN;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_PHONE_NUMBER;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_USERNAME;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_VOICE_SERVICE;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.getRandomAlphabeticString;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.networkActive;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.userProfile;

public class TopUpActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, PostPaymentHandler {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.voice_package_name)
    TextView packageName;

    @BindView(R.id.page_subtitle)
    TextView pageSubtitle;

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
        navigationView.setNavigationItemSelectedListener(this);

        service = getIntent().getParcelableExtra(EXTRA_KEY_VOICE_SERVICE);
        loginValues = getIntent().getParcelableExtra(EXTRA_KEY_LOGIN);

        packageName.setText(service.getPackageName());
        pageSubtitle.setText(service.getPackageName().split("  ")[0]);

        View headerView = navigationView.getHeaderView(0);
        TextView navFullName = headerView.findViewById(R.id.nav_full_name);
        TextView navEmail = headerView.findViewById(R.id.nav_email);
        navFullName.setText(userProfile.getFullName());
        navEmail.setText(""+userProfile.getId());
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
        PaymentCallback paymentCallback = new PaymentCallback(TopUpActivity.this, this, transRef);
        RequestOptions options = RequestOptions.builder()
                .setClientId("IKIA67A8FBB81191FC4F1226098245E9541711B3E959")
                .setClientSecret("FQ+X6B28Y/HJZdsDa1SsbKI23W+pIOLcyxBhGgb8Q9U=")
                .build();
        PayWithCard payWithCard = new PayWithCard(this, customerNumber,
                pageSubtitle.getText().toString(), amountToPay, "NGN", transRef, options, paymentCallback);

        payWithCard.start();
        Toast.makeText(TopUpActivity.this, "Clicked", Toast.LENGTH_SHORT).show();

    }

    public void cancelPayment(View view){

    }

    public void viewCDR(View view){
        Intent i = new Intent(this, ViewCDRActivity.class);
        i.putExtra(EXTRA_KEY_PHONE_NUMBER, service.getUsername());
        i.putExtra(EXTRA_KEY_LOGIN, loginValues);
        startActivity(i);
    }

    public void onMenuClicked(View view){
        drawer.openDrawer(GravityCompat.START, true);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_wifi_analyzer) {
            Intent i = new Intent(this, WifiAnalyzerActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_choose_plan) {
            finish();
        } else if (id == R.id.nav_profile) {
            Intent i = new Intent(this, ProfileActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_password) {
            Intent i = new Intent(this, ChangePasswordActivity.class);
            i.putExtra(EXTRA_KEY_USERNAME, loginValues.getCUsername());
            startActivity(i);
        } else if (id == R.id.nav_payment_history) {
            Intent i = new Intent(this, TransactionHistoryActivity.class);
            i.putExtra(EXTRA_KEY_LOGIN, loginValues);
            startActivity(i);

        } else if (id == R.id.nav_feedback) {
            Intent i = new Intent(this, FeedbackActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_share) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Download ipNX Mobile app from here: " + APP_URL);
            startActivity(shareIntent);
        } else if (id == R.id.nav_faq) {
            Intent i = new Intent(this, FAQActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_notification) {
            Intent i = new Intent(this, PushNotificationActivity.class);
            startActivity(i);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
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
        requestValues.setCPackageNumber(service.getPkgnum() + "");
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
                Response returnedResponse = response.body();
                if (returnedResponse == null) return;
                if (returnedResponse.getResponseCode().equals("0")){
                    Toast.makeText(TopUpActivity.this, "Payment has been posted", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(TopUpActivity.this, "Problem posting payment", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddCashResponse> call, Throwable t) {
                Toast.makeText(TopUpActivity.this, "Problem posting payment", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
