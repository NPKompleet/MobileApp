package com.ipnx.ipnxmobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static android.os.SystemClock.sleep;

public class SplashScreenActivity extends AppCompatActivity {
    public final String splashPREFERENCES = "SplashPrefs" ;

    /// This helps to indicate if the user has seen the Carousel
    /// screen already.
    public final String carouselScreenShown = "CarouselScreenShown";

    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedpreferences = getSharedPreferences(splashPREFERENCES, Context.MODE_PRIVATE);
        sleep(1500);
        if (sharedpreferences.getBoolean(carouselScreenShown, false)){
            i = new Intent(this, LoginActivity.class);
        } else {
            i = new Intent(this, CarouselActivity.class);
            editor = sharedpreferences.edit();
            editor.putBoolean(carouselScreenShown, true);
            editor.apply();
        }
        startActivity(i);
        finish();
    }
}
