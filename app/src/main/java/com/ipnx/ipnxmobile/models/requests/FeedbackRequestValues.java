package com.ipnx.ipnxmobile.models.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ipnx.ipnxmobile.models.CustomValues;

import java.util.List;

public class FeedbackRequestValues extends CustomValues {

    @SerializedName("c.customer_id")
    @Expose
    private String cCustomerId;
    @SerializedName("c.first_name")
    @Expose
    private String cFirstName;
    @SerializedName("c.last_name")
    @Expose
    private String cLastName;
    @SerializedName("c.email_address")
    @Expose
    private String cEmailAddress;
    @SerializedName("c.phone_number")
    @Expose
    private String cPhoneNumber;
    @SerializedName("c.feedback")
    @Expose
    private String cFeedback;

    public String getCCustomerId() {
        return cCustomerId;
    }

    public void setCCustomerId(String cCustomerId) {
        this.cCustomerId = cCustomerId;
    }

    public String getCFirstName() {
        return cFirstName;
    }

    public void setCFirstName(String cFirstName) {
        this.cFirstName = cFirstName;
    }

    public String getCLastName() {
        return cLastName;
    }

    public void setCLastName(String cLastName) {
        this.cLastName = cLastName;
    }

    public String getCEmailAddress() {
        return cEmailAddress;
    }

    public void setCEmailAddress(String cEmailAddress) {
        this.cEmailAddress = cEmailAddress;
    }

    public String getCPhoneNumber() {
        return cPhoneNumber;
    }

    public void setCPhoneNumber(String cPhoneNumber) {
        this.cPhoneNumber = cPhoneNumber;
    }

    public String getCFeedback() {
        return cFeedback;
    }

    public void setCFeedback(String cFeedback) {
        this.cFeedback = cFeedback;
    }

}