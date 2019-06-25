package com.ipnx.ipnxmobile;

import android.os.Bundle;
import android.view.View;

public class PushNotificationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_notification);
    }

    public void onBackClicked(View view){
        finish();
    }
}
