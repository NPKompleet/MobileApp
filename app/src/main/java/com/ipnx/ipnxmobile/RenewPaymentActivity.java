package com.ipnx.ipnxmobile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.interswitchng.sdk.auth.Passport;
import com.interswitchng.sdk.model.RequestOptions;
import com.interswitchng.sdk.payment.Payment;
import com.interswitchng.sdk.payment.android.inapp.PayWithCard;
import com.interswitchng.sdk.util.RandomString;
import com.ipnx.ipnxmobile.payment.PaymentCallback;

import static com.ipnx.ipnxmobile.utils.ApplicationUtils.networkActive;

public class RenewPaymentActivity extends AppCompatActivity {

    TextView balance;
    EditText amount;
    TextView pageSubtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renew_payment);
        Passport.overrideApiBase(Passport.QA_API_BASE);
        Payment.overrideApiBase(Payment.QA_API_BASE);

        balance = findViewById(R.id.renew_balance);
        amount = findViewById(R.id.renew_amount);
        pageSubtitle = findViewById(R.id.page_subtitle);

    }

    public void makePayment(View view){
        if (!networkActive(this)){
            Toast.makeText(this, "Network is unavailable", Toast.LENGTH_SHORT).show();
            return;
        }
        long unixTime = System.currentTimeMillis() / 1000L;
        String transRef = RandomString.numeric(12);
        PaymentCallback paymentCallback = new PaymentCallback(RenewPaymentActivity.this, transRef);
        System.out.println(transRef);
        RequestOptions options = RequestOptions.builder()
//                .setClientId("IKIA9614B82064D632E9B6418DF358A6A4AEA84D7218")
                .setClientId("IKIA67A8FBB81191FC4F1226098245E9541711B3E959")
//                .setClientId("IKIAA74F4FDA133BDB2712DD1A6D3DA4503CBE745674")
//                .setClientSecret("XCTiBtLy1G9chAnyg0z3BcaFK4cVpwDg/GTw2EmjTZ8=")
                .setClientSecret("FQ+X6B28Y/HJZdsDa1SsbKI23W+pIOLcyxBhGgb8Q9U=")
//                .setClientSecret("secret")
                .build();
        PayWithCard payWithCard = new PayWithCard(this, "1407002510",
                "desc", amount.getText().toString(), "NGN", transRef, options, paymentCallback);

        payWithCard.start();
        Toast.makeText(RenewPaymentActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
    }


    public void cancelPayment(View view){

    }

    public void onBackClicked(View view){
        finish();
    }
}
