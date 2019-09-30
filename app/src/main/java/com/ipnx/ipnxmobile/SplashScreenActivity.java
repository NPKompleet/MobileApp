package com.ipnx.ipnxmobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ipnx.ipnxmobile.utils.MyMessageStore;

import org.infobip.mobile.messaging.MobileMessaging;
import org.infobip.mobile.messaging.User;
import org.infobip.mobile.messaging.UserAttributes;
import org.infobip.mobile.messaging.UserIdentity;
import org.infobip.mobile.messaging.api.support.util.CollectionUtils;
import org.infobip.mobile.messaging.mobile.MobileMessagingError;
import org.infobip.mobile.messaging.mobile.Result;
import org.infobip.mobile.messaging.storage.SQLiteMessageStore;

import static android.os.SystemClock.sleep;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.userProfile;

public class SplashScreenActivity extends AppCompatActivity {
    public final String splashPREFERENCES = "SplashPrefs" ;

    /// This helps to indicate if the user has seen the Carousel
    /// screen already.
    public final String deviceAlreadyRegistered = "DeviceAlreadyRegistered";

    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedpreferences = getSharedPreferences(splashPREFERENCES, Context.MODE_PRIVATE);
        sleep(1500);
        if (!sharedpreferences.getBoolean(deviceAlreadyRegistered, false)){
            editor = sharedpreferences.edit();
            editor.putBoolean(deviceAlreadyRegistered, true);
            editor.apply();

            // Set user information on Infobip
            UserIdentity userIdentity = new UserIdentity();
            userIdentity.setPhones(CollectionUtils.setOf(userProfile.getPhoneNumber()));
            String phoneNumber = userProfile.getPhoneNumber();
            phoneNumber = phoneNumber.trim();
            if (phoneNumber.startsWith("0")){
                phoneNumber = phoneNumber.replaceFirst("0", "234");
            }else if (phoneNumber.startsWith("+")){
                phoneNumber = phoneNumber.replaceFirst("\\+", "");
            }
            userIdentity.setPhones(CollectionUtils.setOf(phoneNumber));
            userIdentity.setEmails(CollectionUtils.setOf(userProfile.getEmailAddress().toString()));
            userIdentity.setExternalUserId(userProfile.getCustomerNumber());

            UserAttributes userAttributes = new UserAttributes();
            userAttributes.setFirstName(userProfile.getFirstName());
            userAttributes.setLastName(userProfile.getLastName());

            MobileMessaging mobileMessaging = new MobileMessaging.Builder(getApplication())
                    .withMessageStore(SQLiteMessageStore.class)
                    .build();

            mobileMessaging.personalize(userIdentity, userAttributes, new MobileMessaging.ResultListener<User>() {
                @Override
                public void onResult(Result<User, MobileMessagingError> result) {

                }
            });
        }
        Intent i = new Intent(this, CarouselActivity.class);
        startActivity(i);
        finish();
    }
}
