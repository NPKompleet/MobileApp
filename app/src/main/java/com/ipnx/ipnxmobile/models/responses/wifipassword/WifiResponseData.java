package com.ipnx.ipnxmobile.models.responses.wifipassword;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WifiResponseData {

    @SerializedName("instance")
    @Expose
    private String instance;
    @SerializedName("device")
    @Expose
    private long device;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("security")
    @Expose
    private String security;

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public long getDevice() {
        return device;
    }

    public void setDevice(long device) {
        this.device = device;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

}
