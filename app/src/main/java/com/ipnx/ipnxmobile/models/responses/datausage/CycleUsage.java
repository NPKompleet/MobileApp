package com.ipnx.ipnxmobile.models.responses.datausage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CycleUsage {

    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("cycle_mb_used")
    @Expose
    private long cycleMbUsed;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public long getCycleMbUsed() {
        return cycleMbUsed;
    }

    public void setCycleMbUsed(long cycleMbUsed) {
        this.cycleMbUsed = cycleMbUsed;
    }

}
