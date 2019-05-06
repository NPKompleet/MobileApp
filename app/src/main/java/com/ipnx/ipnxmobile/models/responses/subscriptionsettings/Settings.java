package com.ipnx.ipnxmobile.models.responses.subscriptionsettings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Settings {

    @SerializedName("analytics")
    @Expose
    private String analytics;
    @SerializedName("auto-renewal")
    @Expose
    private String autoRenewal;

    public String getAnalytics() {
        return analytics;
    }

    public void setAnalytics(String analytics) {
        this.analytics = analytics;
    }

    public String getAutoRenewal() {
        return autoRenewal;
    }

    public void setAutoRenewal(String autoRenewal) {
        this.autoRenewal = autoRenewal;
    }

}