package com.ipnx.ipnxmobile.models.responses.wifipassword;

import android.os.Parcel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ipnx.ipnxmobile.models.responses.Response;

public class WifiPasswordResponse extends Response {

    @SerializedName("custom_values")
    @Expose
    private WifiPasswordCustomValues customValues;

    protected WifiPasswordResponse(Parcel in) {
        super(in);
    }

    public WifiPasswordCustomValues getCustomValues() {
        return customValues;
    }

    public void setCustomValues(WifiPasswordCustomValues customValues) {
        this.customValues = customValues;
    }
}
