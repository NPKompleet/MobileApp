package com.ipnx.ipnxmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static android.os.SystemClock.sleep;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sleep(1500);
        Intent i = new Intent(this, CarouselActivity.class);
        startActivity(i);
        finish();
    }
}
