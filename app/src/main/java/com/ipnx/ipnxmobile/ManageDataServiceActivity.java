package com.ipnx.ipnxmobile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.TextView;

import com.ipnx.ipnxmobile.models.requests.LoginRequestValues;
import com.ipnx.ipnxmobile.models.responses.login.InternetService;
import com.ipnx.ipnxmobile.wifianalyzer.WifiAnalyzerActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ipnx.ipnxmobile.utils.ApplicationUtils.APP_URL;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_EXPIRY_DATE;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_INTERNET_SERVICE;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_LOGIN;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_ONT_SERIAL;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_PACKAGE_CLASS_COMMENT;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_SERVICE_PLAN;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_USERNAME;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.FACEBOOK_URL;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.INSTAGRAM_URL;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.TWITTER_URL;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.WHATSSAPP_URL;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.userProfile;

public class ManageDataServiceActivity extends BaseActivity {


    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.service_plan)
    TextView servicePlan;

    @BindView(R.id.balance)
    TextView balance;

    @BindView(R.id.expiryDate)
    TextView expiryDate;

    @BindView(R.id.page_subtitle)
    TextView pageSubtitle;

    LoginRequestValues loginValues;
    InternetService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_data_service);
        ButterKnife.bind(this);

        loginValues = getIntent().getParcelableExtra(EXTRA_KEY_LOGIN);
        service = getIntent().getParcelableExtra(EXTRA_KEY_INTERNET_SERVICE);
        pageSubtitle.setText(service.getUsername());
        servicePlan.setText(service.getPackageName().split("  ")[0]);
        float balanceCalculated = Float.parseFloat(service.getPackageLevelUnappliedCredits()) +
                Float.parseFloat(service.getPackageLevelUnappliedPayments());
        balance.setText("₦" + balanceCalculated );
        expiryDate.setText(service.getExpiryDate());

        View headerView = navigationView.getHeaderView(0);
        TextView navFullName = headerView.findViewById(R.id.nav_full_name);
        TextView navEmail = headerView.findViewById(R.id.nav_email);
        navFullName.setText(userProfile.getFullName());
        navEmail.setText(userProfile.getCustomerNumber());

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

    public void onMenuItemSelected(View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.service_data_usage:
                i = new Intent(this, DataUsageActivity.class);
                i.putExtra(EXTRA_KEY_ONT_SERIAL, service.getUsername());
                i.putExtra(EXTRA_KEY_PACKAGE_CLASS_COMMENT, service.getPackageClassComment());
                i.putExtra(EXTRA_KEY_SERVICE_PLAN, service.getPackageName());
                i.putExtra(EXTRA_KEY_EXPIRY_DATE, service.getExpiryDate());
                startActivity(i);
                break;
            case R.id.service_manage_settings:
                i = new Intent(this, ManageSettingsActivity.class);
                i.putExtra(EXTRA_KEY_INTERNET_SERVICE, service);
                startActivity(i);
                break;
            case R.id.service_renew:
                i = new Intent(this, RenewPaymentActivity.class);
                i.putExtra(EXTRA_KEY_INTERNET_SERVICE, service);
                startActivity(i);
                break;
            case R.id.service_add_funds:
                i = new Intent(this, AddCashActivity.class);
                i.putExtra(EXTRA_KEY_INTERNET_SERVICE, service);
                startActivity(i);
                break;
            case R.id.service_change_wifi_password:
                i = new Intent(this, ChangeWifiPasswordActivity.class);
                i.putExtra(EXTRA_KEY_ONT_SERIAL, service.getUsername());
                startActivity(i);
                break;
            case R.id.service_change_plan:
                i = new Intent(this, ChangeServicePlanActivity.class);
                i.putExtra(EXTRA_KEY_INTERNET_SERVICE, service);
                startActivity(i);
                break;
        }
    }

    public void onNavItemSelected(View view) {
        // Handle navigation view item clicks here.
        int id = view.getId();
        Intent i;
        switch (id){
            case R.id.menu_wifi_analyzer:
                i = new Intent(this, WifiAnalyzerActivity.class);
                startActivity(i);
                break;
            case R.id.menu_profile:
                i = new Intent(this, ProfileActivity.class);
                startActivity(i);
                break;
            case R.id.menu_password:
                i = new Intent(this, ChangePasswordActivity.class);
                i.putExtra(EXTRA_KEY_USERNAME, loginValues.getCUsername());
                startActivity(i);
                break;
            case R.id.menu_choose_plan:
                finish();
                break;
            case R.id.menu_payment_history:
                i = new Intent(this, TransactionHistoryActivity.class);
                startActivity(i);
                break;
            case R.id.menu_feedback:
                i = new Intent(this, FeedbackActivity.class);
                startActivity(i);
                break;
            case R.id.menu_share:
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Hello. Download ipNX Mobile app from here: " + APP_URL);
                startActivity(shareIntent);
                break;
            case R.id.menu_faq:
                i = new Intent(this, FAQActivity.class);
                startActivity(i);
                break;
            case R.id.menu_notification:
                i = new Intent(this, PushNotificationActivity.class);
                startActivity(i);
                break;
            case R.id.menu_facebook:
                i = new Intent(Intent.ACTION_VIEW, Uri.parse(FACEBOOK_URL));
                startActivity(i);
                break;
            case R.id.menu_twitter:
                i = new Intent(Intent.ACTION_VIEW, Uri.parse(TWITTER_URL));
                startActivity(i);
                break;
            case R.id.menu_instagram:
                i = new Intent(Intent.ACTION_VIEW, Uri.parse(INSTAGRAM_URL));
                startActivity(i);
                break;
            case R.id.menu_whatsapp:
                i = new Intent(Intent.ACTION_VIEW, Uri.parse(WHATSSAPP_URL));
                startActivity(i);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
    }

}
