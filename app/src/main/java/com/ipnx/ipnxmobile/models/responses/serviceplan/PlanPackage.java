package com.ipnx.ipnxmobile.models.responses.serviceplan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlanPackage {
    @SerializedName("setup")
    @Expose
    private String setup;
    @SerializedName("package_id")
    @Expose
    private long packageId;
    @SerializedName("package_name")
    @Expose
    private String packageName;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("price")
    @Expose
    private String price;

    public String getSetup() {
        return setup;
    }

    public void setSetup(String setup) {
        this.setup = setup;
    }

    public long getPackageId() {
        return packageId;
    }

    public void setPackageId(long packageId) {
        this.packageId = packageId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
