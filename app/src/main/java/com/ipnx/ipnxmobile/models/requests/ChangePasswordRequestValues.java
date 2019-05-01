package com.ipnx.ipnxmobile.models.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ipnx.ipnxmobile.models.CustomValues;

public class ChangePasswordRequestValues extends CustomValues {

        @SerializedName("c.username")
        @Expose
        private String cUsername;
        @SerializedName("c.old_password")
        @Expose
        private String cOldPassword;
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

        public String getCOldPassword() {
            return cOldPassword;
        }

        public void setCOldPassword(String cOldPassword) {
            this.cOldPassword = cOldPassword;
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