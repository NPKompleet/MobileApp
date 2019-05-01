package com.ipnx.ipnxmobile.models.responses.datausage;

import android.os.Parcel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ipnx.ipnxmobile.models.responses.Response;

public class DataUsageResponse extends Response {

    @SerializedName("custom_values")
    @Expose
    private DataUsageCustomValues customValues;

    protected DataUsageResponse(Parcel in) {
        super(in);
    }

    public DataUsageCustomValues getCustomValues() {
        return customValues;
    }

    public void setCustomValues(DataUsageCustomValues customValues) {
        this.customValues = customValues;
    }


}
