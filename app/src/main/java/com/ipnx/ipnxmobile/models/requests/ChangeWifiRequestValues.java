package com.ipnx.ipnxmobile.models.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ipnx.ipnxmobile.models.CustomValues;

public class ChangeWifiRequestValues extends CustomValues {

    @SerializedName("c.settings")
    @Expose
    private String cSettings;
    @SerializedName("c.ont_serial")
    @Expose
    private String cOntSerial;
    @SerializedName("c.device_number")
    @Expose
    private String cDeviceNumber;
    @SerializedName("c.password")
    @Expose
    private String cPassword;

    public String getCSettings() {
        return cSettings;
    }

    public void setCSettings(String cSettings) {
        this.cSettings = cSettings;
    }

    public String getCOntSerial() {
        return cOntSerial;
    }

    public void setCOntSerial(String cOntSerial) {
        this.cOntSerial = cOntSerial;
    }

    public String getCDeviceNumber() {
        return cDeviceNumber;
    }

    public void setCDeviceNumber(String cDeviceNumber) {
        this.cDeviceNumber = cDeviceNumber;
    }

    public String getCPassword() {
        return cPassword;
    }

    public void setCPassword(String cPassword) {
        this.cPassword = cPassword;
    }

}
