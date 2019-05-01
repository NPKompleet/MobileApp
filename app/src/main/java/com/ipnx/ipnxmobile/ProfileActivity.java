package com.ipnx.ipnxmobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ipnx.ipnxmobile.R;

import static com.ipnx.ipnxmobile.utils.ApplicationUtils.userProfile;

public class ProfileActivity extends AppCompatActivity {
    TextView name, phone, email, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name = findViewById(R.id.profile_name);
        phone = findViewById(R.id.profile_phoneNumber);
        email = findViewById(R.id.profile_email);
        address = findViewById(R.id.profile_address);

        name.setText(userProfile.getFullName());
        phone.setText(userProfile.getPhoneNumber());
        email.setText(userProfile.getEmailAddress().toString());
        address.setText(userProfile.getAddress());
    }

    public void onBackClicked(View view){
        finish();
    }
}
