package com.ipnx.ipnxmobile;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public abstract class BaseActivity extends AppCompatActivity {

    LocationManager locationManager;
    Location locationGPS, locationNet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    protected Location getLastBestLocation() {
        try {
            locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            locationNet = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            System.out.println(locationGPS);
            System.out.println(locationNet);
        } catch (SecurityException e) {
            e.printStackTrace();
        }

        long GPSLocationTime = 0;
        if (null != locationGPS) {
            GPSLocationTime = locationGPS.getTime();
        }

        long NetLocationTime = 0;

        if (null != locationNet) {
            NetLocationTime = locationNet.getTime();
        }

        if (0 < GPSLocationTime - NetLocationTime) {
            return locationGPS;
        } else {
            return locationNet;
        }
    }

    public void onBottomNavItemClicked(View view){
        Intent i;
        switch (view.getId()){
            case R.id.bottomNav_profile:
                i = new Intent(this, ProfileActivity.class);
                startActivity(i);
                break;
            case R.id.bottomNav_notification:
                i = new Intent(this, PushNotificationActivity.class);
                startActivity(i);
                break;
            case R.id.bottomNav_payHistory:
                i = new Intent(this, TransactionHistoryActivity.class);
                startActivity(i);
                break;
            case R.id.bottomNav_faq:
                i = new Intent(this, FAQActivity.class);
                startActivity(i);
                break;
        }
    }

}
