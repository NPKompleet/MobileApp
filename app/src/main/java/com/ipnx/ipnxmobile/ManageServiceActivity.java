package com.ipnx.ipnxmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ipnx.ipnxmobile.wifianalyzer.WifiAnalyzerActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ManageServiceActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

//    @BindView(R.id.toolbar)
//    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_service);
        ButterKnife.bind(this);
//        setSupportActionBar(toolbar);

//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

//        LoginResponse response = getIntent().getParcelableExtra(EXTRA_KEY_RESPONSE);
//
//        InternetServiceAdapter internetServiceAdapter
//                = new InternetServiceAdapter(this, response.getCustomValues().getInternetServices());
//        internetServiceListView.setAdapter(internetServiceAdapter);
//
//        TelephonyServiceAdapter voiceServiceAdapter
//                = new TelephonyServiceAdapter(this, response.getCustomValues().getTelephonyServices());
//        voiceServiceListView.setAdapter(voiceServiceAdapter);
//
//        View headerView= navigationView.getHeaderView(0);
//        TextView nav_name = headerView.findViewById(R.id.nav_full_name);
//        nav_name.setText(response.getCustomValues().getFullName());
//        TextView nav_email = headerView.findViewById(R.id.nav_email);
//        nav_email.setText(response.getCustomValues().getEmailAddresses());

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



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_wifi_analyzer) {
            Intent i = new Intent(this, WifiAnalyzerActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_payment_history) {
            Intent i = new Intent(this, TransactionHistoryActivity.class);
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

    public void onMenuItemSelected(View view){
        Intent i;
        switch (view.getId()){
            case R.id.service_data_usage:
                i = new Intent(this, DataUsageActivity.class);
                startActivity(i);
                break;
            case R.id.service_manage_settings:
                i = new Intent(this, ManageSettingsActivity.class);
                startActivity(i);
                break;
            case R.id.service_renew:
                i = new Intent(this, RenewPaymentActivity.class);
                startActivity(i);
                break;
        }
    }
}
