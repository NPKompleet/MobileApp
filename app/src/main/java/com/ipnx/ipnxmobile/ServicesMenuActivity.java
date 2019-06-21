package com.ipnx.ipnxmobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.ipnx.ipnxmobile.adapters.InternetServiceAdapter;
import com.ipnx.ipnxmobile.adapters.TelephonyServiceAdapter;
import com.ipnx.ipnxmobile.models.requests.LoginRequestValues;
import com.ipnx.ipnxmobile.models.responses.login.LoginResponse;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_LOGIN;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_RESPONSE;

public class ServicesMenuActivity extends AppCompatActivity {

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

    public void onBackPressed(View view){
        onBackPressed();
    }
}
