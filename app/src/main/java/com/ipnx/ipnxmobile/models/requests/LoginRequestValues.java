package com.ipnx.ipnxmobile.models.requests;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ipnx.ipnxmobile.models.CustomValues;

public class LoginRequestValues extends CustomValues implements Parcelable {
    @SerializedName("c.username")
    @Expose
    private String cUsername;
    @SerializedName("c.password")
    @Expose
    private String cPassword;

    public LoginRequestValues(){}

    protected LoginRequestValues(Parcel in) {
        cUsername = in.readString();
        cPassword = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cUsername);
        dest.writeString(cPassword);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LoginRequestValues> CREATOR = new Creator<LoginRequestValues>() {
        @Override
        public LoginRequestValues createFromParcel(Parcel in) {
            return new LoginRequestValues(in);
        }

        @Override
        public LoginRequestValues[] newArray(int size) {
            return new LoginRequestValues[size];
        }
    };

    public String getCUsername() {
        return cUsername;
    }

    public void setCUsername(String cUsername) {
        this.cUsername = cUsername;
    }

    public String getCPassword() {
        return cPassword;
    }

    public void setCPassword(String cPassword) {
        this.cPassword = cPassword;
    }


}
