package com.ipnx.ipnxmobile.models.responses.forgotpassword;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ipnx.ipnxmobile.models.CustomValues;

public class ForgotPasswordCustomValues implements Parcelable {

    @SerializedName("email_address")
    @Expose
    private String emailAddress;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("message")
    @Expose
    private String message;

    protected ForgotPasswordCustomValues(Parcel in) {
        emailAddress = in.readString();
        name = in.readString();
        message = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(emailAddress);
        dest.writeString(name);
        dest.writeString(message);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ForgotPasswordCustomValues> CREATOR = new Creator<ForgotPasswordCustomValues>() {
        @Override
        public ForgotPasswordCustomValues createFromParcel(Parcel in) {
            return new ForgotPasswordCustomValues(in);
        }

        @Override
        public ForgotPasswordCustomValues[] newArray(int size) {
            return new ForgotPasswordCustomValues[size];
        }
    };

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
