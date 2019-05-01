package com.ipnx.ipnxmobile.models.responses.datausage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DataAllowance {

    @SerializedName("off-peak")
    @Expose
    private String offPeak;
    @SerializedName("peak")
    @Expose
    private String peak;

    public String getOffPeak() {
        return offPeak;
    }

    public void setOffPeak(String offPeak) {
        this.offPeak = offPeak;
    }

    public String getPeak() {
        return peak;
    }

    public void setPeak(String peak) {
        this.peak = peak;
    }
}
