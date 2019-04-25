package com.ipnx.ipnxmobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import static com.ipnx.ipnxmobile.utils.ApplicationUtils.networkActive;

public class FeedbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
    }

    public void onSubmitClicked(View view){
        if (!networkActive(this)){
            Toast.makeText(this, "Network is unavailable", Toast.LENGTH_SHORT).show();
            return;
        }

    }

    public void onBackClicked(View view){
        finish();
    }
}
