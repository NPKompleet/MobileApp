package com.ipnx.ipnxmobile.models.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ipnx.ipnxmobile.models.CustomValues;

public class ForgotPasswordConfirmRequestValues extends CustomValues {

    @SerializedName("c.username")
    @Expose
    private String cUsername;
    @SerializedName("c.access_token")
    @Expose
    private String cAccessToken;
    @SerializedName("c.new_password")
    @Expose
    private String cNewPassword;
    @SerializedName("c.password_confirmation")
    @Expose
    private String cPasswordConfirmation;

    public String getCUsername() {
        return cUsername;
    }

    public void setCUsername(String cUsername) {
        this.cUsername = cUsername;
    }

    public String getCAccessToken() {
        return cAccessToken;
    }

    public void setCAccessToken(String cAccessToken) {
        this.cAccessToken = cAccessToken;
    }

    public String getCNewPassword() {
        return cNewPassword;
    }

    public void setCNewPassword(String cNewPassword) {
        this.cNewPassword = cNewPassword;
    }

    public String getCPasswordConfirmation() {
        return cPasswordConfirmation;
    }

    public void setCPasswordConfirmation(String cPasswordConfirmation) {
        this.cPasswordConfirmation = cPasswordConfirmation;
    }

}
