package com.ipnx.ipnxmobile.models.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ipnx.ipnxmobile.models.CustomValues;

public class Response {

    @SerializedName("response_message")
    @Expose
    private String responseMessage;
    @SerializedName("ver")
    @Expose
    private String ver;
    @SerializedName("hash")
    @Expose
    private String hash;
    @SerializedName("action")
    @Expose
    private String action;
    @SerializedName("client-ip")
    @Expose
    private String clientIp;
    @SerializedName("custom_values")
    @Expose
    private CustomValues customValues;
    @SerializedName("response_code")
    @Expose
    private String responseCode;

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
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

    public CustomValues getCustomValues() {
        return customValues;
    }

    public void setCustomValues(CustomValues customValues) {
        this.customValues = customValues;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

}
