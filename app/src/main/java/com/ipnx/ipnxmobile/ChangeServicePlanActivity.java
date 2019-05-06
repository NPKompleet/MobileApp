package com.ipnx.ipnxmobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ChangeServicePlanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_service_plan);
    }

    public void onBackClicked(View view){
        finish();
    }

}
