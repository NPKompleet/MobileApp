package com.ipnx.ipnxmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.ipnx.ipnxmobile.adapters.InternetServiceAdapter;
import com.ipnx.ipnxmobile.adapters.TelephonyServiceAdapter;
import com.ipnx.ipnxmobile.models.responses.LoginResponse;

import butterknife.BindView;
import butterknife.ButterKnife;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_menu);
        ButterKnife.bind(this);

        LoginResponse response = getIntent().getParcelableExtra(EXTRA_KEY_RESPONSE);

        InternetServiceAdapter internetServiceAdapter
                = new InternetServiceAdapter(this, response.getCustomValues().getInternetServices());
        internetServiceListView.setAdapter(internetServiceAdapter);

        TelephonyServiceAdapter voiceServiceAdapter
                = new TelephonyServiceAdapter(this, response.getCustomValues().getTelephonyServices());
        voiceServiceListView.setAdapter(voiceServiceAdapter);

        if(dataLayout.isExpanded()) dataLayout.collapse();
        if(voiceLayout.isExpanded()) voiceLayout.collapse();
    }

    public void onMenuButtonClicked(View view){
        Intent i;
        switch (view.getId()) {
            case R.id.data_menu:
//                i = new Intent(this, ManageServiceActivity.class);
//                startActivity(i);
                dataLayout.toggle();
                break;
            case R.id.voice_menu:
//                i = new Intent(this, TopUpActivity.class);
//                startActivity(i);
                voiceLayout.toggle();
                break;
        }
    }
}
