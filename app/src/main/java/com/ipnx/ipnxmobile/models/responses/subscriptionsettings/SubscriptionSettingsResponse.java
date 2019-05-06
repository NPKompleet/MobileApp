package com.ipnx.ipnxmobile.models.responses.subscriptionsettings;

import android.os.Parcel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ipnx.ipnxmobile.models.responses.Response;

public class SubscriptionSettingsResponse extends Response {

    @SerializedName("custom_values")
    @Expose
    private SubscriptionSettingsCustomValues customValues;


    protected SubscriptionSettingsResponse(Parcel in) {
        super(in);
    }


    public SubscriptionSettingsCustomValues getCustomValues() {
        return customValues;
    }

    public void setCustomValues(SubscriptionSettingsCustomValues customValues) {
        this.customValues = customValues;
    }

}
