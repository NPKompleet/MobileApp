package com.ipnx.ipnxmobile.models.responses.addcash;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddCashCustomValues {

    @Expose
    private String success;
    @SerializedName("payment")
    @Expose
    private long payment;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public long getPayment() {
        return payment;
    }

    public void setPayment(long payment) {
        this.payment = payment;
    }

}