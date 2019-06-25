package com.ipnx.ipnxmobile.models.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ipnx.ipnxmobile.models.CustomValues;

public class ChangePlanRequestValues extends CustomValues {

    @SerializedName("c.settings")
    @Expose
    private String cSettings;
    @SerializedName("c.username")
    @Expose
    private String cUsername;
    @SerializedName("c.password")
    @Expose
    private String cPassword;
    @SerializedName("c.package_number")
    @Expose
    private String cPackageNumber;
    @SerializedName("c.old_package_number")
    @Expose
    private String cOldPackageNumber;
    @SerializedName("c.change_option")
    @Expose
    private String cChangeOption;
    @SerializedName("c._available_balance")
    @Expose
    private String cAvailableBalance;

    public String getCSettings() {
        return cSettings;
    }

    public void setCSettings(String cSettings) {
        this.cSettings = cSettings;
    }

    public String getCUsername() {
        return cUsername;
    }

    public void setCUsername(String cUsername) {
        this.cUsername = cUsername;
    }

    public String getCPassword() {
        return cPassword;
    }

    public void setCPassword(String cPassword) {
        this.cPassword = cPassword;
    }

    public String getCPackageNumber() {
        return cPackageNumber;
    }

    public void setCPackageNumber(String cPackageNumber) {
        this.cPackageNumber = cPackageNumber;
    }

    public String getCOldPackageNumber() {
        return cOldPackageNumber;
    }

    public void setCOldPackageNumber(String cOldPackageNumber) {
        this.cOldPackageNumber = cOldPackageNumber;
    }

    public String getCChangeOption() {
        return cChangeOption;
    }

    public void setCChangeOption(String cChangeOption) {
        this.cChangeOption = cChangeOption;
    }

    public String getCAvailableBalance() {
        return cAvailableBalance;
    }

    public void setCAvailableBalance(String cAvailableBalance) {
        this.cAvailableBalance = cAvailableBalance;
    }

}

