package com.ipnx.ipnxmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ipnx.ipnxmobile.models.requests.LoginRequestValues;
import com.ipnx.ipnxmobile.models.responses.login.InternetService;
import com.ipnx.ipnxmobile.models.responses.login.LoginCustomValues;
import com.ipnx.ipnxmobile.models.responses.login.LoginResponse;
import com.ipnx.ipnxmobile.wifianalyzer.WifiAnalyzerActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_INTERNET_SERVICE;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_LOGIN;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_ONT_SERIAL;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_PACKAGE_CLASS_COMMENT;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_RESPONSE;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_SERVICE_PLAN;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_USERNAME;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.userProfile;

public class ManageServiceActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.page_subtitle)
    TextView pageSubtitle;

    LoginRequestValues loginValues;
    InternetService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_service);
        ButterKnife.bind(this);

        navigationView.setNavigationItemSelectedListener(this);

        loginValues = getIntent().getParcelableExtra(EXTRA_KEY_LOGIN);
        service = getIntent().getParcelableExtra(EXTRA_KEY_INTERNET_SERVICE);
        pageSubtitle.setText("User ID: " + userProfile.getId());

    }

    public void onMenuClicked(View view) {
        drawer.openDrawer(GravityCompat.START, true);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_wifi_analyzer) {
            Intent i = new Intent(this, WifiAnalyzerActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_sub_settings) {
            Intent i = new Intent(this, SubscriptionSettingsActivity.class);
            i.putExtra(EXTRA_KEY_INTERNET_SERVICE, service);
            startActivity(i);
        } else if (id == R.id.nav_profile) {
            Intent i = new Intent(this, ProfileActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_password) {
            Intent i = new Intent(this, ChangePasswordActivity.class);
            i.putExtra(EXTRA_KEY_USERNAME, loginValues.getCUsername());
            startActivity(i);

        } else if (id == R.id.nav_choose_plan) {
            finish();

        } else if (id == R.id.nav_payment_history) {
            Intent i = new Intent(this, TransactionHistoryActivity.class);
            i.putExtra(EXTRA_KEY_LOGIN, loginValues);
            startActivity(i);

        } else if (id == R.id.nav_feedback) {
            Intent i = new Intent(this, FeedbackActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_faq) {
            Intent i = new Intent(this, FAQActivity.class);
            startActivity(i);

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onMenuItemSelected(View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.service_data_usage:
                i = new Intent(this, DataUsageActivity.class);
                i.putExtra(EXTRA_KEY_ONT_SERIAL, service.getUsername());
                i.putExtra(EXTRA_KEY_PACKAGE_CLASS_COMMENT, service.getPackageClassComment());
                i.putExtra(EXTRA_KEY_SERVICE_PLAN, service.getPackageName());
                startActivity(i);
                break;
            case R.id.service_manage_settings:
                i = new Intent(this, SubscriptionSettingsActivity.class);
                i.putExtra(EXTRA_KEY_INTERNET_SERVICE, service);
                startActivity(i);
                break;
            case R.id.service_renew:
                i = new Intent(this, RenewPaymentActivity.class);
                startActivity(i);
                break;
            case R.id.service_choose_plan:
                finish();
                break;
        }
    }
}
