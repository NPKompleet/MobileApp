package com.ipnx.ipnxmobile;

import android.app.Application;
import android.support.v4.content.ContextCompat;

import org.infobip.mobile.messaging.MobileMessaging;
import org.infobip.mobile.messaging.NotificationSettings;
import org.infobip.mobile.messaging.storage.SQLiteMessageStore;

public class MobileApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        new MobileMessaging.Builder(this)
                .withMessageStore(SQLiteMessageStore.class)
                .withDisplayNotification(new NotificationSettings.Builder(this)
                        .withMultipleNotifications()
                        .withDefaultIcon(R.drawable.ic_notification)
                        .withColor(ContextCompat.getColor(this, R.color.red_button))
                        .build())
                .build();
    }
}
