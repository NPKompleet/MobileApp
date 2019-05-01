package com.ipnx.ipnxmobile.models.responses.datausage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataUsageCustomValues {

    @SerializedName("rollover")
    @Expose
    private RollOver rollover;
    @SerializedName("cycle_usage")
    @Expose
    private CycleUsage cycleUsage;
    @SerializedName("data_allowance")
    @Expose
    private DataAllowance dataAllowance;

    public RollOver getRollover() {
        return rollover;
    }

    public void setRollover(RollOver rollover) {
        this.rollover = rollover;
    }

    public CycleUsage getCycleUsage() {
        return cycleUsage;
    }

    public void setCycleUsage(CycleUsage cycleUsage) {
        this.cycleUsage = cycleUsage;
    }

    public DataAllowance getDataAllowance() {
        return dataAllowance;
    }

    public void setDataAllowance(DataAllowance dataAllowance) {
        this.dataAllowance = dataAllowance;
    }
}
