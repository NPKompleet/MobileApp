package com.ipnx.ipnxmobile.models.responses.datausage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OffPeak {

    @SerializedName("cycle_mb_used")
    @Expose
    private long cycleMbUsed;
    @SerializedName("start_date")
    @Expose
    private String startDate;

    public long getCycleMbUsed() {
        return cycleMbUsed;
    }

    public void setCycleMbUsed(long cycleMbUsed) {
        this.cycleMbUsed = cycleMbUsed;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

}
