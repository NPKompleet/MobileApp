package com.ipnx.ipnxmobile;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.interswitchng.sdk.auth.Passport;
import com.interswitchng.sdk.model.RequestOptions;
import com.interswitchng.sdk.payment.Payment;
import com.interswitchng.sdk.payment.android.inapp.PayWithCard;
import com.ipnx.ipnxmobile.payment.PaymentCallback;

public class RenewPaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renew_payment);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Passport.overrideApiBase(Passport.SANDBOX_API_BASE);
        Payment.overrideApiBase(Payment.SANDBOX_API_BASE);

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void makePayment(View view){
        PaymentCallback paymentCallback = new PaymentCallback(RenewPaymentActivity.this);
        RequestOptions options = RequestOptions.builder()
                .setClientId("IKIA9614B82064D632E9B6418DF358A6A4AEA84D7218")
                .setClientSecret("XCTiBtLy1G9chAnyg0z3BcaFK4cVpwDg/GTw2EmjTZ8=")
                .build();
        PayWithCard payWithCard = new PayWithCard(this, "cust", "desc", "100",
                "NGN", options, paymentCallback);
        payWithCard.start();
        Toast.makeText(RenewPaymentActivity.this, "Clicked", Toast.LENGTH_SHORT).show();

    }

}
