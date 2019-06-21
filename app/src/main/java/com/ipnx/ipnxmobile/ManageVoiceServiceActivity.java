package com.ipnx.ipnxmobile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ipnx.ipnxmobile.models.requests.LoginRequestValues;
import com.ipnx.ipnxmobile.models.responses.login.TelephonyService;
import com.ipnx.ipnxmobile.retrofit.MyApiEndpointInterface;
import com.ipnx.ipnxmobile.wifianalyzer.WifiAnalyzerActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ipnx.ipnxmobile.utils.ApplicationUtils.APP_URL;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_LOGIN;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_PHONE_NUMBER;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_USERNAME;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_VOICE_SERVICE;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.FACEBOOK_URL;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.INSTAGRAM_URL;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.TWITTER_URL;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.userProfile;

public class ManageVoiceServiceActivity extends AppCompatActivity {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.voice_package_name)
    TextView packageName;

    @BindView(R.id.balance)
    TextView balance;

    @BindView(R.id.expiryDate)
    TextView expDate;

    @BindView(R.id.page_subtitle)
    TextView pageSubtitle;



    LoginRequestValues loginValues;
    TelephonyService service;

    MyApiEndpointInterface myApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_voice_service);
        ButterKnife.bind(this);

        service = getIntent().getParcelableExtra(EXTRA_KEY_VOICE_SERVICE);
        loginValues = getIntent().getParcelableExtra(EXTRA_KEY_LOGIN);

        pageSubtitle.setText("Phone Number: " + service.getUsername());
        packageName.setText(service.getPackageName().split("  ")[0]);
        balance.setText("₦" + service.getPackageBalance());
        expDate.setText(service.getExpiryDate());
//        pageSubtitle.setText(service.getPackageName().split("  ")[0]);


        View headerView = navigationView.getHeaderView(0);
        TextView navFullName = headerView.findViewById(R.id.nav_full_name);
        TextView navEmail = headerView.findViewById(R.id.nav_email);
        navFullName.setText(userProfile.getFullName());
        navEmail.setText(userProfile.getCustomerNumber());
    }

    public void viewCDR(View view){
        Intent i = new Intent(this, ViewCDRActivity.class);
        i.putExtra(EXTRA_KEY_PHONE_NUMBER, service.getUsername());
        i.putExtra(EXTRA_KEY_LOGIN, loginValues);
        startActivity(i);
    }

    public void topUp(View view){
        Intent i = new Intent(this, TopUpActivity.class);
        i.putExtra(EXTRA_KEY_VOICE_SERVICE, service);
        i.putExtra(EXTRA_KEY_LOGIN, loginValues);
        startActivity(i);
    }

    public void onMenuClicked(View view){
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

    public void onNavItemSelected(View view) {
        // Handle navigation view item clicks here.
        int id = view.getId();

        if (id == R.id.menu_wifi_analyzer) {
            Intent i = new Intent(this, WifiAnalyzerActivity.class);
            startActivity(i);
        } else if (id == R.id.menu_profile) {
            Intent i = new Intent(this, ProfileActivity.class);
            startActivity(i);
        } else if (id == R.id.menu_password) {
            Intent i = new Intent(this, ChangePasswordActivity.class);
            i.putExtra(EXTRA_KEY_USERNAME, loginValues.getCUsername());
            startActivity(i);
        } else if (id == R.id.menu_choose_plan) {
            finish();
        } else if (id == R.id.menu_payment_history) {
            Intent i = new Intent(this, TransactionHistoryActivity.class);
            i.putExtra(EXTRA_KEY_LOGIN, loginValues);
            startActivity(i);
        } else if (id == R.id.menu_feedback) {
            Intent i = new Intent(this, FeedbackActivity.class);
            startActivity(i);
        } else if (id == R.id.menu_share) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Hello. Download ipNX Mobile app from here: " + APP_URL);
            startActivity(shareIntent);
        } else if (id == R.id.menu_faq) {
            Intent i = new Intent(this, FAQActivity.class);
            startActivity(i);
        } else if (id == R.id.menu_notification) {
            Intent i = new Intent(this, PushNotificationActivity.class);
            startActivity(i);
        } else if (id == R.id.menu_facebook) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(FACEBOOK_URL));
            startActivity(intent);
        }else if (id == R.id.menu_twitter) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(TWITTER_URL));
            startActivity(intent);
        }else if (id == R.id.menu_instagram) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(INSTAGRAM_URL));
            startActivity(intent);
        }
        drawer.closeDrawer(GravityCompat.START);
    }
}
