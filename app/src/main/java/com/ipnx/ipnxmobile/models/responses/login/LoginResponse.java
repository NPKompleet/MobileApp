package com.ipnx.ipnxmobile.models.responses.login;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class LoginResponse implements Parcelable
{

    @SerializedName("ver")
    @Expose
    private String ver;
    @SerializedName("hash")
    @Expose
    private String hash;
    @SerializedName("action")
    @Expose
    private String action;
    @SerializedName("client_ip")
    @Expose
    private String clientIp;
    @SerializedName("custom_values")
    @Expose
    private LoginCustomValues customValues;
    @SerializedName("response_code")
    @Expose
    private String responseCode;
    @SerializedName("response_message")
    @Expose
    private String responseMessage;
    public final static Parcelable.Creator<LoginResponse> CREATOR = new Creator<LoginResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public LoginResponse createFromParcel(Parcel in) {
            return new LoginResponse(in);
        }

        public LoginResponse[] newArray(int size) {
            return (new LoginResponse[size]);
        }

    }
            ;

    protected LoginResponse(Parcel in) {
        this.ver = ((String) in.readValue((String.class.getClassLoader())));
        this.hash = ((String) in.readValue((String.class.getClassLoader())));
        this.action = ((String) in.readValue((String.class.getClassLoader())));
        this.clientIp = ((String) in.readValue((String.class.getClassLoader())));
        this.customValues = ((LoginCustomValues) in.readValue((LoginCustomValues.class.getClassLoader())));
        this.responseCode = ((String) in.readValue((String.class.getClassLoader())));
        this.responseMessage = ((String) in.readValue((String.class.getClassLoader())));
    }

    public LoginResponse() {
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public LoginCustomValues getCustomValues() {
        return customValues;
    }

    public void setCustomValues(LoginCustomValues customValues) {
        this.customValues = customValues;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(ver);
        dest.writeValue(hash);
        dest.writeValue(action);
        dest.writeValue(clientIp);
        dest.writeValue(customValues);
        dest.writeValue(responseCode);
        dest.writeValue(responseMessage);
    }

    public int describeContents() {
        return 0;
    }

}