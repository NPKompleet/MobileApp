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

import com.ipnx.ipnxmobile.models.requests.Request;
import com.ipnx.ipnxmobile.models.requests.SubscriptionSettingsRequestValues;
import com.ipnx.ipnxmobile.models.responses.login.InternetService;
import com.ipnx.ipnxmobile.models.responses.subscriptionsettings.SubscriptionSettingsResponse;
import com.ipnx.ipnxmobile.retrofit.MyApiEndpointInterface;
import com.ipnx.ipnxmobile.retrofit.RetrofitUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ipnx.ipnxmobile.utils.ApplicationUtils.ACTION_SUBSCRIPTION_SETTINGS;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_INTERNET_SERVICE;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.networkActive;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.userProfile;

public class SubscriptionSettingsActivity extends AppCompatActivity {
    public final String AppPREFERENCES = "AppPrefs" ;
    public final String Analytics = "AnalyticsSettingsKey";
    public final String AutoRenewal = "AutoRenewalSettingsKey";

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

    MyApiEndpointInterface myApi;

    SharedPreferences sharedpreferences;

    SharedPreferences.Editor editor;

    String analyticsState = "0";
    String renewalState = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription_settings);
        ButterKnife.bind(this);

        service = getIntent().getParcelableExtra(EXTRA_KEY_INTERNET_SERVICE);
        pageSubtitle.setText("Service Plan: " + service.getPackageName().split("  ")[0]);
        serviceAddress.setText("Service Address: " + service.getServiceLocation());
        deviceNumber.setText("Device Number: " + service.getUsername());

        sharedpreferences = getSharedPreferences(AppPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();

        getSettingsState();

        setViewAnalytics.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (!networkActive(SubscriptionSettingsActivity.this)){
                    Toast.makeText(SubscriptionSettingsActivity.this,
                            "Network is unavailable", Toast.LENGTH_SHORT).show();
                    setViewAnalytics.setChecked(!isChecked);
                    return;
                }
//                if (isChecked){
//                    editor.putBoolean(Analytics, true);
//                    makeToastNotification("Analytics", "ON");
//                }else {
//                    editor.putBoolean(Analytics, false);
//                    makeToastNotification("Analytics", "OFF");
//                }
                editor.putBoolean(Analytics, isChecked);
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
//                if (isChecked){
//                    editor.putBoolean(AutoRenewal, true);
//                    makeToastNotification("Auto-Renewal", "ON");
//                }else {
//                    editor.putBoolean(AutoRenewal, false);
//                    makeToastNotification("Auto-Renewal", "OFF");
//                }
                editor.putBoolean(AutoRenewal, isChecked);
                editor.apply();
            }
        });
    }

    private void getSettingsState() {
        Request subSettingsRequest = new Request();
        SubscriptionSettingsRequestValues requestValues = new SubscriptionSettingsRequestValues();
        requestValues.setCSettings("1");
        requestValues.setCCustomerId(Long.parseLong(userProfile.getCustomerNumber()));
        requestValues.setCServiceId(service.getUsername());
        requestValues.setCPkgnum(service.getPkgnum());

        subSettingsRequest.setCustomValues(requestValues);
        subSettingsRequest.setAction(ACTION_SUBSCRIPTION_SETTINGS);

        myApi = RetrofitUtils.getService();
        Call<SubscriptionSettingsResponse> call = myApi.subscriptionSettings(subSettingsRequest);
        call.enqueue(new Callback<SubscriptionSettingsResponse>() {
            @Override
            public void onResponse(Call<SubscriptionSettingsResponse> call, Response<SubscriptionSettingsResponse> response) {
                SubscriptionSettingsResponse returnedResponse = response.body();
                if (returnedResponse == null ){
                    Toast.makeText(SubscriptionSettingsActivity.this, "Could not sync settings", Toast.LENGTH_SHORT).show();
                    boolean canViewAnalytics = sharedpreferences.getBoolean(Analytics, false);
                    boolean canAutoRenew = sharedpreferences.getBoolean(AutoRenewal, false);
                    setViewAnalytics.setChecked(canViewAnalytics);
                    setAutoRenewal.setChecked(canAutoRenew);
                    return;
                }
                if (!returnedResponse.getResponseCode().equals("0")){
                    Toast.makeText(SubscriptionSettingsActivity.this, returnedResponse.getResponseMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }


                editor.putBoolean(Analytics, returnedResponse.getCustomValues().getSettings().getAnalytics().equals("1"));
                editor.putBoolean(AutoRenewal, returnedResponse.getCustomValues().getSettings().getAutoRenewal().equals("1"));
                editor.apply();

                boolean canViewAnalytics = sharedpreferences.getBoolean(Analytics, false);
                boolean canAutoRenew = sharedpreferences.getBoolean(AutoRenewal, false);
                setViewAnalytics.setChecked(canViewAnalytics);
                setAutoRenewal.setChecked(canAutoRenew);

            }

            @Override
            public void onFailure(Call<SubscriptionSettingsResponse> call, Throwable t) {
                Toast.makeText(SubscriptionSettingsActivity.this, "Failure: Could not sync settings", Toast.LENGTH_SHORT).show();
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
