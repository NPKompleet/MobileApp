package com.ipnx.ipnxmobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.ipnx.ipnxmobile.adapters.InternetServiceAdapter;
import com.ipnx.ipnxmobile.adapters.TelephonyServiceAdapter;
import com.ipnx.ipnxmobile.models.requests.LoginRequestValues;
import com.ipnx.ipnxmobile.models.responses.login.LoginResponse;
import com.ipnx.ipnxmobile.wifianalyzer.WifiAnalyzerActivity;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.ipnx.ipnxmobile.utils.ApplicationUtils.APP_URL;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_LOGIN;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_RESPONSE;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_USERNAME;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.FACEBOOK_URL;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.INSTAGRAM_URL;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.TWITTER_URL;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.WHATSSAPP_URL;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.userProfile;

public class ServicesMenuActivity extends AppCompatActivity {
    public final String ProfilePREFERENCES = "ProfilePrefs" ;
    public final String PictureLocation = "ProfilePictureLocation";

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.internetServiceListView)
    ListView internetServiceListView;

    @BindView(R.id.voiceServiceListView)
    ListView voiceServiceListView;

    @BindView(R.id.data_layout)
    ExpandableRelativeLayout dataLayout;

    @BindView(R.id.voice_layout)
    ExpandableRelativeLayout voiceLayout;
    
    @BindView(R.id.data_menu)
    Button dataButton;

    @BindView(R.id.voice_menu)
    Button voiceButton;

    @BindView(R.id.welcome_name)
    TextView name;

    @BindView(R.id.home_avatar)
    CircleImageView avatar;

    SharedPreferences sharedpreferences;

    boolean backAlreadyPressed = false;
    long timeFirstPressed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_menu);
        ButterKnife.bind(this);

        LoginResponse response = getIntent().getParcelableExtra(EXTRA_KEY_RESPONSE);
        LoginRequestValues loginValues = getIntent().getParcelableExtra(EXTRA_KEY_LOGIN);

        if (response.getCustomValues().getInternetServices() != null && 
                !response.getCustomValues().getInternetServices().isEmpty()) {
            InternetServiceAdapter internetServiceAdapter
                    = new InternetServiceAdapter(this,
                    response.getCustomValues().getInternetServices(), loginValues);
            internetServiceListView.setAdapter(internetServiceAdapter);
            // Collapse Expandable layouts in case of android versions
            // where it is always open by default
            dataLayout.collapse();
        }else {
            dataButton.setVisibility(View.GONE);
            dataLayout.setVisibility(View.GONE);
        }
        
        if (response.getCustomValues().getTelephonyServices() != null &&
                !response.getCustomValues().getTelephonyServices().isEmpty()) {
            TelephonyServiceAdapter voiceServiceAdapter
                    = new TelephonyServiceAdapter(this,
                    response.getCustomValues().getTelephonyServices(), loginValues);
            voiceServiceListView.setAdapter(voiceServiceAdapter);
            // Collapse Expandable layouts in case of android versions
            // where it is always open by default
            voiceLayout.collapse();
        }else {
            voiceButton.setVisibility(View.GONE);
            voiceLayout.setVisibility(View.GONE);
        }

        name.setText("Welcome, " + userProfile.getFullName());
        sharedpreferences = getSharedPreferences(ProfilePREFERENCES, Context.MODE_PRIVATE);
        loadProfilePicture();

        View headerView = navigationView.getHeaderView(0);
        TextView navFullName = headerView.findViewById(R.id.nav_full_name);
        TextView navEmail = headerView.findViewById(R.id.nav_email);
        navFullName.setText(userProfile.getFullName());
        navEmail.setText(userProfile.getCustomerNumber());

    }

    public void onMenuClicked(View view) {
        drawer.openDrawer(GravityCompat.START, true);
    }

    public void onMenuButtonClicked(View view){
        switch (view.getId()) {
            case R.id.data_menu:
                dataLayout.toggle();
                break;
            case R.id.voice_menu:
                voiceLayout.toggle();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Date currentDateTime = new Date();
        if (!backAlreadyPressed){
            Toast.makeText(this, "Press Back again to exit app", Toast.LENGTH_SHORT).show();
            timeFirstPressed = currentDateTime.getTime();
            backAlreadyPressed = true;
            return;
        }else {
            if (currentDateTime.getTime() - timeFirstPressed > 2000){
                Toast.makeText(this, "Press Back again to exit app", Toast.LENGTH_SHORT).show();
                timeFirstPressed = currentDateTime.getTime();
                backAlreadyPressed = true;
                return;
            }
        }
        super.onBackPressed();
    }

    private void loadProfilePicture() {
        String uriString = sharedpreferences.getString(PictureLocation, "");
        if (!uriString.equals("")){
            Uri uri = Uri.parse(uriString);
            avatar.setImageURI(uri);
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
                i.putExtra(EXTRA_KEY_USERNAME, userProfile.getUserName());
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
