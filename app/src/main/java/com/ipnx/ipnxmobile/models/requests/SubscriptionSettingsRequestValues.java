package com.ipnx.ipnxmobile.models.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ipnx.ipnxmobile.models.CustomValues;

import java.util.List;

public class SubscriptionSettingsRequestValues extends CustomValues {

    @SerializedName("c.pkgnum")
    @Expose
    private long cPkgnum;
    @SerializedName("c.settings")
    @Expose
    private String cSettings;
    @SerializedName("c.customer_id")
    @Expose
    private long cCustomerId;
    @SerializedName("c.service_id")
    @Expose
    private String cServiceId;
    @SerializedName("c.analytics")
    @Expose
    private String cAnalytics = "1";
    @SerializedName("c.auto_renewal")
    @Expose
    private String cAutoRenewal = "1";

    public long getCPkgnum() {
        return cPkgnum;
    }

    public void setCPkgnum(long cPkgnum) {
        this.cPkgnum = cPkgnum;
    }

    public String getCSettings() {
        return cSettings;
    }

    public void setCSettings(String cSettings) {
        this.cSettings = cSettings;
    }

    public long getCCustomerId() {
        return cCustomerId;
    }

    public void setCCustomerId(long cCustomerId) {
        this.cCustomerId = cCustomerId;
    }

    public String getCServiceId() {
        return cServiceId;
    }

    public void setCServiceId(String cServiceId) {
        this.cServiceId = cServiceId;
    }

    public String getCAnalytics() {
        return cAnalytics;
    }

    public void setCAnalytics(String cAnalytics) {
        this.cAnalytics = cAnalytics;
    }

    public String getCAutoRenewal() {
        return cAutoRenewal;
    }

    public void setCAutoRenewal(String cAutoRenewal) {
        this.cAutoRenewal = cAutoRenewal;
    }

}