package com.ipnx.ipnxmobile.models.responses.forgotpassword;

import android.os.Parcel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ipnx.ipnxmobile.models.responses.Response;

public class ForgotPasswordResponse extends Response {
    @SerializedName("custom_values")
    @Expose
    private ForgotPasswordCustomValues customValues;

    public ForgotPasswordResponse(Parcel in) {
        super(in);
    }

    public ForgotPasswordCustomValues getCustomValues(){
        return this.customValues;
    }

    public void setCustomValues(ForgotPasswordCustomValues customValues) {
        this.customValues = customValues;
    }
}