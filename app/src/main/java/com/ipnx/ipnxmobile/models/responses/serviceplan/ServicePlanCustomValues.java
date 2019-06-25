package com.ipnx.ipnxmobile.models.responses.serviceplan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ServicePlanCustomValues {
    @SerializedName("tax")
    @Expose
    private long tax;
    @SerializedName("packages")
    @Expose
    private List<PlanPackage> packages = null;

    public long getTax() {
        return tax;
    }

    public void setTax(long tax) {
        this.tax = tax;
    }

    public List<PlanPackage> getPackages() {
        return packages;
    }

    public void setPackages(List<PlanPackage> packages) {
        this.packages = packages;
    }

}
