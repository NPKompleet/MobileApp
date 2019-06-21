package com.ipnx.ipnxmobile.models.responses.datausage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CycleUsage {

    @SerializedName("peak")
    @Expose
    private Peak peak;
    @SerializedName("off-peak")
    @Expose
    private OffPeak offPeak;

    public Peak getPeak() {
        return peak;
    }

    public void setPeak(Peak peak) {
        this.peak = peak;
    }

    public OffPeak getOffPeak() {
        return offPeak;
    }

    public void setOffPeak(OffPeak offPeak) {
        this.offPeak = offPeak;
    }

}
