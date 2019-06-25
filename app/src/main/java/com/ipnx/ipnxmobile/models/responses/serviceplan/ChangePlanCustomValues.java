package com.ipnx.ipnxmobile.models.responses.serviceplan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangePlanCustomValues {

    @SerializedName("success")
    @Expose
    private long success;
    @SerializedName("email_address")
    @Expose
    private Object emailAddress;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("change_date")
    @Expose
    private String changeDate;
    @SerializedName("old_pkg_title")
    @Expose
    private String oldPkgTitle;
    @SerializedName("new_pkg_title")
    @Expose
    private String newPkgTitle;
    @SerializedName("old_pkg_status")
    @Expose
    private long oldPkgStatus;
    @SerializedName("customer_number")
    @Expose
    private String customerNumber;

    public long getSuccess() {
        return success;
    }

    public void setSuccess(long success) {
        this.success = success;
    }

    public Object getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(Object emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(String changeDate) {
        this.changeDate = changeDate;
    }

    public String getOldPkgTitle() {
        return oldPkgTitle;
    }

    public void setOldPkgTitle(String oldPkgTitle) {
        this.oldPkgTitle = oldPkgTitle;
    }

    public String getNewPkgTitle() {
        return newPkgTitle;
    }

    public void setNewPkgTitle(String newPkgTitle) {
        this.newPkgTitle = newPkgTitle;
    }

    public long getOldPkgStatus() {
        return oldPkgStatus;
    }

    public void setOldPkgStatus(long oldPkgStatus) {
        this.oldPkgStatus = oldPkgStatus;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

}
