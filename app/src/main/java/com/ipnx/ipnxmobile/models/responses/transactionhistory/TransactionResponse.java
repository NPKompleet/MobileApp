package com.ipnx.ipnxmobile.models.responses.transactionhistory;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransactionResponse implements Parcelable {

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
    private TransactionCustomValues customValues;
    @SerializedName("response_code")
    @Expose
    private String responseCode;
    @SerializedName("response_message")
    @Expose
    private String responseMessage;

    protected TransactionResponse(Parcel in) {
        ver = in.readString();
        hash = in.readString();
        action = in.readString();
        clientIp = in.readString();
        customValues = in.readParcelable(TransactionCustomValues.class.getClassLoader());
        responseCode = in.readString();
        responseMessage = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ver);
        dest.writeString(hash);
        dest.writeString(action);
        dest.writeString(clientIp);
        dest.writeParcelable(customValues, flags);
        dest.writeString(responseCode);
        dest.writeString(responseMessage);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TransactionResponse> CREATOR = new Creator<TransactionResponse>() {
        @Override
        public TransactionResponse createFromParcel(Parcel in) {
            return new TransactionResponse(in);
        }

        @Override
        public TransactionResponse[] newArray(int size) {
            return new TransactionResponse[size];
        }
    };

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

    public TransactionCustomValues getCustomValues() {
        return customValues;
    }

    public void setCustomValues(TransactionCustomValues customValues) {
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

}
