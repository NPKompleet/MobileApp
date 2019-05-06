package com.ipnx.ipnxmobile.models.responses.subscriptionsettings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubscriptionSettingsCustomValues {

    @SerializedName("subscription_id")
    @Expose
    private String subscriptionId;
    @SerializedName("settings")
    @Expose
    private Settings settings;

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

}

