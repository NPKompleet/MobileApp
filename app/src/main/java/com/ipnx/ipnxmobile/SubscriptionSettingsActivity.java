package com.ipnx.ipnxmobile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SubscriptionSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription_settings);
    }

    public void onBackClicked(View view){
        finish();
    }
}
