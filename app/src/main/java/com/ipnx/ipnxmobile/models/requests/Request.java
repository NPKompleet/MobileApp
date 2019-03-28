package com.ipnx.ipnxmobile.models.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ipnx.ipnxmobile.models.CustomValues;

import static com.ipnx.ipnxmobile.utils.APIUtils.*;


public class Request {

    @SerializedName("ver")
    @Expose
    private String ver = VERSION;
    @SerializedName("app_bundle")
    @Expose
    private String appBundle = APP_BUNDLE;
    @SerializedName("app_name")
    @Expose
    private String appName = APP_NAME;
    @SerializedName("network")
    @Expose
    private String network = NETWORK;
    @SerializedName("client_ip")
    @Expose
    private String clientIp="";
    @SerializedName("lat")
    @Expose
    private String lat="";
    @SerializedName("lon")
    @Expose
    private String lon="";
    @SerializedName("did")
    @Expose
    private String did="";
    @SerializedName("lt")
    @Expose
    private String lt="";
    @SerializedName("hash")
    @Expose
    private String hash="";
    @SerializedName("action")
    @Expose
    private String action="";
    @SerializedName("custom_values")
    @Expose
    private CustomValues customValues;

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getAppBundle() {
        return appBundle;
    }

    public void setAppBundle(String appBundle) {
        this.appBundle = appBundle;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getLt() {
        return lt;
    }

    public void setLt(String lt) {
        this.lt = lt;
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

    public CustomValues getCustomValues() {
        return customValues;
    }

    public void setCustomValues(CustomValues customValues) {
        this.customValues = customValues;
    }

}
