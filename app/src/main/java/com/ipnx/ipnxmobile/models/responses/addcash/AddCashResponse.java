package com.ipnx.ipnxmobile.models.responses.addcash;

import android.os.Parcel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ipnx.ipnxmobile.models.responses.Response;
import com.ipnx.ipnxmobile.models.responses.cdr.CallRecordCustomValues;

public class AddCashResponse extends Response {

    @SerializedName("custom_values")
    @Expose
    private CallRecordCustomValues customValues;


    protected AddCashResponse(Parcel in) {
        super(in);
    }


    public CallRecordCustomValues getCustomValues() {
        return customValues;
    }

    public void setCustomValues(CallRecordCustomValues customValues) {
        this.customValues = customValues;
    }

}
