package com.ipnx.ipnxmobile.models.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ipnx.ipnxmobile.models.CustomValues;

public class CDRRequestValues extends CustomValues {

    @SerializedName("c.username")
    @Expose
    private String cUsername;
    @SerializedName("c.password")
    @Expose
    private String cPassword;
    @SerializedName("c.phonenumber")
    @Expose
    private String cPhonenumber;
    @SerializedName("c.startdate")
    @Expose
    private String cStartdate;
    @SerializedName("c.enddate")
    @Expose
    private String cEnddate;
    @SerializedName("c.offset")
    @Expose
    private String cOffset = "10";

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

    public String getCPhonenumber() {
        return cPhonenumber;
    }

    public void setCPhonenumber(String cPhonenumber) {
        this.cPhonenumber = cPhonenumber;
    }

    public String getCStartdate() {
        return cStartdate;
    }

    public void setCStartdate(String cStartdate) {
        this.cStartdate = cStartdate;
    }

    public String getCEnddate() {
        return cEnddate;
    }

    public void setCEnddate(String cEnddate) {
        this.cEnddate = cEnddate;
    }

    public String getCOffset() {
        return cOffset;
    }

    public void setCOffset(String cOffset) {
        this.cOffset = cOffset;
    }

}
