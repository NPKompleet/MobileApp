package com.ipnx.ipnxmobile.models.responses.forgotpassword;

import android.os.Parcel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ipnx.ipnxmobile.models.requests.ForgotPasswordConfirmRequestValues;
import com.ipnx.ipnxmobile.models.responses.Response;

public class ForgotPasswordConfirmResponse extends Response {
    @SerializedName("custom_values")
    @Expose
    private ForgotPasswordConfirmRequestValues customValues;

    public ForgotPasswordConfirmResponse(Parcel in) {
        super(in);
    }
    
    public ForgotPasswordConfirmRequestValues getCustomValues(){
        return this.customValues;
    }

    public void setCustomValues(ForgotPasswordConfirmRequestValues customValues) {
        this.customValues = customValues;
    }
}