package com.ipnx.ipnxmobile.models.responses.serviceplan;

import android.os.Parcel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ipnx.ipnxmobile.models.responses.Response;
import com.ipnx.ipnxmobile.models.responses.wifipassword.WifiPasswordCustomValues;

public class ServicePlanResponse extends Response {

    @SerializedName("custom_values")
    @Expose
    private ServicePlanCustomValues customValues;

    protected ServicePlanResponse(Parcel in) {
        super(in);
    }

    public ServicePlanCustomValues getCustomValues() {
        return customValues;
    }

    public void setCustomValues(ServicePlanCustomValues customValues) {
        this.customValues = customValues;
    }
}
