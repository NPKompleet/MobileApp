package com.ipnx.ipnxmobile.models.responses.cdr;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ipnx.ipnxmobile.models.responses.Response;
import com.ipnx.ipnxmobile.models.responses.transactionhistory.TransactionCustomValues;

public class CallRecordResponse extends Response {

    @SerializedName("custom_values")
    @Expose
    private CallRecordCustomValues customValues;


    protected CallRecordResponse(Parcel in) {
        super(in);
    }


    public CallRecordCustomValues getCustomValues() {
        return customValues;
    }

    public void setCustomValues(CallRecordCustomValues customValues) {
        this.customValues = customValues;
    }

}
