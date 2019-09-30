package com.ipnx.ipnxmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ipnx.ipnxmobile.models.responses.login.InternetService;
import com.ipnx.ipnxmobile.retrofit.MyApiEndpointInterface;
import com.ipnx.ipnxmobile.retrofit.RetrofitUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

public class AddCashActivity extends BaseActivity{

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
//        System.out.println(customerNumber);
//        System.out.println(unixTime);
//        System.out.println(transRef);
//        PaymentCallback paymentCallback = new PaymentCallback(AddCashActivity.this, this, transRef);
//        RequestOptions options = RequestOptions.builder()
//                .setClientId("IKIA67A8FBB81191FC4F1226098245E9541711B3E959")
//                .setClientSecret("FQ+X6B28Y/HJZdsDa1SsbKI23W+pIOLcyxBhGgb8Q9U=")
//                .build();
//        PayWithCard payWithCard = new PayWithCard(this, customerNumber,
//                pageSubtitle.getText().toString(), amountToPay, "NGN", transRef, options, paymentCallback);
//
//        payWithCard.start();
//        Toast.makeText(AddCashActivity.this, "Clicked", Toast.LENGTH_SHORT).show();

        String currencyCode = "566";
        String customerName = userProfile.getFullName();

        String paymentPath = "https://mbridge.ipnxnigeria.net/ipnxmobilebridge/api/v1/show_payment?";
        String productId = "6205";
        String redirect = "https://www.ipnxnigeria.net";
        String itemId = "101";
        String key = "D3D1D05AFE42AD50818167EAC73C109168A0F108F32645C8B59E897FA930DA44F9230910DAC9E20641823799A107A02068F7BC0F4CC41D2952E249552255710F";
        String hash = encryptThisString(transRef + productId + itemId + amountToPay + redirect + key);

        String url = paymentPath + "product_id=" + productId + "&amount=" + amountToPay
                + "&currency=" + currencyCode + "&site_redirect_url=" + redirect
                + "&site_name=ipnxmobile" + "&customer_id=" + customerNumber
                + "&customer_name=" + customerName + "&txn_ref=" + transRef
                + "&pay_item_id=" + itemId + "&hash=" + hash;

        Intent i = new Intent(this, PaymentViewActivity.class);
        i.putExtra("url", url);
        startActivity(i);

    }


    public void cancelPayment(View view) {
        amount.setText("");
    }

    public void onBackClicked(View view) {
        finish();
    }

    public static String encryptThisString(String input)
    {
        try {
            // getInstance() method is called with algorithm SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            // return the HashText
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
