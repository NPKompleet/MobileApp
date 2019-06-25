package com.ipnx.ipnxmobile.models.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ipnx.ipnxmobile.models.CustomValues;

public class FetchPlansRequestValues extends CustomValues {

    @SerializedName("c.username")
    @Expose
    private String cUsername;
    @SerializedName("c.password")
    @Expose
    private String cPassword;
    @SerializedName("c.customer_number")
    @Expose
    private String cCustomerNumber;
    @SerializedName("c.package_number")
    @Expose
    private String cPackageNumber;

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

    public String getCCustomerNumber() {
        return cCustomerNumber;
    }

    public void setCCustomerNumber(String cCustomerNumber) {
        this.cCustomerNumber = cCustomerNumber;
    }

    public String getCPackageNumber() {
        return cPackageNumber;
    }

    public void setCPackageNumber(String cPackageNumber) {
        this.cPackageNumber = cPackageNumber;
    }

}