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
import android.widget.ListView;
import android.widget.TextView;

import com.ipnx.ipnxmobile.adapters.InternetServiceAdapter;
import com.ipnx.ipnxmobile.adapters.TelephonyServiceAdapter;
import com.ipnx.ipnxmobile.models.responses.LoginResponse;
import com.ipnx.ipnxmobile.wifianalyzer.WifiAnalyzerActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_RESPONSE;

public class ManageServiceActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.internet_service_list)
    ListView internetServiceListView;

    @BindView(R.id.voice_service_list)
    ListView voiceServiceListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_service);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        LoginResponse response = getIntent().getParcelableExtra(EXTRA_KEY_RESPONSE);

        InternetServiceAdapter internetServiceAdapter
                = new InternetServiceAdapter(this, response.getCustomValues().getInternetServices());
        internetServiceListView.setAdapter(internetServiceAdapter);

        TelephonyServiceAdapter voiceServiceAdapter
                = new TelephonyServiceAdapter(this, response.getCustomValues().getTelephonyServices());
        voiceServiceListView.setAdapter(voiceServiceAdapter);

        View headerView= navigationView.getHeaderView(0);
        TextView nav_name = headerView.findViewById(R.id.nav_full_name);
        nav_name.setText(response.getCustomValues().getFullName());
        TextView nav_email = headerView.findViewById(R.id.nav_email);
        nav_email.setText(response.getCustomValues().getEmailAddresses());

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.service_plan, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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

        } else if (id == R.id.nav_feedback) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_faq) {
            Intent i = new Intent(this, TestActivity.class);
            startActivity(i);

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void selectService(View view){
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
