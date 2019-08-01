package com.ipnx.ipnxmobile;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ViewListener;

import org.infobip.mobile.messaging.MobileMessaging;
import org.infobip.mobile.messaging.NotificationSettings;
import org.infobip.mobile.messaging.User;
import org.infobip.mobile.messaging.UserAttributes;
import org.infobip.mobile.messaging.UserIdentity;
import org.infobip.mobile.messaging.api.support.util.CollectionUtils;
import org.infobip.mobile.messaging.mobile.MobileMessagingError;
import org.infobip.mobile.messaging.mobile.Result;
import org.infobip.mobile.messaging.storage.SQLiteMessageStore;

import static com.ipnx.ipnxmobile.utils.ApplicationUtils.userProfile;


public class CarouselActivity extends AppCompatActivity {
    CarouselView customCarouselView;
    TextView nextButton;
    int NUMBER_OF_PAGES = 3;
    int[] carouselItems = {R.layout.carousel_page1, R.layout.carousel_page2, R.layout.carousel_page3};
    int viewPosition = 0;

    MobileMessaging mobileMessaging;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carousel);

        customCarouselView = findViewById(R.id.carouselView);
        customCarouselView.setPageCount(NUMBER_OF_PAGES);
        customCarouselView.setViewListener(viewListener);
        nextButton = findViewById(R.id.carousel_next);

        customCarouselView.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                viewPosition = i;
                if (viewPosition == 2) nextButton.setText("GET STARTED");
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

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

        MobileMessaging.getInstance(this).personalize(userIdentity, userAttributes, new MobileMessaging.ResultListener<User>() {
            @Override
            public void onResult(Result<User, MobileMessagingError> result) {

            }
        });

    }

    ViewListener viewListener = new ViewListener() {

        @Override
        public View setViewForPosition(int position) {
            return getLayoutInflater().inflate(carouselItems[position], null);
        }
    };

    public void onSkipClicked(View view) {
        openLoginActivity();
    }

    public void onNextClicked(View view) {
        if (nextButton.getText().toString().equals("GET STARTED")){
            openLoginActivity();
            return;
        }
        if (viewPosition < 2) customCarouselView.setCurrentItem(viewPosition + 1);
    }

    private void openLoginActivity(){
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}
