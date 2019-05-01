package com.ipnx.ipnxmobile.models.responses.datausage;

import android.os.Parcel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ipnx.ipnxmobile.models.responses.Response;

public class DataHistoryResponse extends Response {

    @SerializedName("custom_values")
    @Expose
    private DataHistoryCustomValues customValues;

    protected DataHistoryResponse(Parcel in) {
        super(in);
    }

    public DataHistoryCustomValues getCustomValues() {
        return customValues;
    }

    public void setCustomValues(DataHistoryCustomValues customValues) {
        this.customValues = customValues;
    }


}
