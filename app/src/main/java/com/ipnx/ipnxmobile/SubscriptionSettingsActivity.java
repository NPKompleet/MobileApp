package com.ipnx.ipnxmobile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.ipnx.ipnxmobile.models.responses.login.InternetService;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_INTERNET_SERVICE;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.networkActive;

public class SubscriptionSettingsActivity extends AppCompatActivity {
    public static final String AppPREFERENCES = "AppPrefs" ;
    public static final String Analytics = "AnalyticsSettingsKey";
    public static final String AutoRenewal = "AutoRenewalSettingsKey";

    InternetService service;

    @BindView(R.id.page_subtitle)
    TextView pageSubtitle;

    @BindView(R.id.sub_settings_address)
    TextView serviceAddress;

    @BindView(R.id.sub_settings_dev_number)
    TextView deviceNumber;

    @BindView(R.id.sub_settings_switch_analytics)
    SwitchCompat setViewAnalytics;

    @BindView(R.id.sub_settings_switch_renewal)
    SwitchCompat setAutoRenewal;

    SharedPreferences sharedpreferences;

    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription_settings);
        ButterKnife.bind(this);

        service = getIntent().getParcelableExtra(EXTRA_KEY_INTERNET_SERVICE);
        sharedpreferences = getSharedPreferences(AppPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        boolean canViewAnalytics = sharedpreferences.getBoolean(Analytics, false);
        boolean canAutoRenew = sharedpreferences.getBoolean(AutoRenewal, false);

        pageSubtitle.setText("Service Plan: " + service.getPackageName().split("  ")[0]);
        serviceAddress.setText("Service Address: " + service.getServiceLocation());
        deviceNumber.setText("Device Number: " + service.getUsername());
        setViewAnalytics.setChecked(canViewAnalytics);
        setAutoRenewal.setChecked(canAutoRenew);
        

        setViewAnalytics.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (!networkActive(SubscriptionSettingsActivity.this)){
                    Toast.makeText(SubscriptionSettingsActivity.this,
                            "Network is unavailable", Toast.LENGTH_SHORT).show();
                    setViewAnalytics.setChecked(!isChecked);
                    return;
                }
                if (isChecked){
                    editor.putBoolean(Analytics, true);
                    makeToastNotification("Analytics", "ON");
                }else {
                    editor.putBoolean(Analytics, false);
                    makeToastNotification("Analytics", "OFF");
                }
                editor.apply();
            }
        });

        setAutoRenewal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (!networkActive(SubscriptionSettingsActivity.this)){
                    Toast.makeText(SubscriptionSettingsActivity.this,
                            "Network is unavailable", Toast.LENGTH_SHORT).show();
                    setAutoRenewal.setChecked(!isChecked);
                    return;
                }
                if (isChecked){
                    editor.putBoolean(AutoRenewal, true);
                    makeToastNotification("Auto-Renewal", "ON");
                }else {
                    editor.putBoolean(AutoRenewal, false);
                    makeToastNotification("Auto-Renewal", "OFF");
                }
                editor.apply();
            }
        });
    }

    private void makeToastNotification(String property, String state) {
        Toast.makeText(this, property + " is turned " + state, Toast.LENGTH_SHORT).show();
    }

    public void onBackClicked(View view){
        finish();
    }
    
    
}
