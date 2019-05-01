package com.ipnx.ipnxmobile.models.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ipnx.ipnxmobile.models.CustomValues;

public class DataHistoryRequestValues extends CustomValues {

    @SerializedName("c.ont_serial")
    @Expose
    private String cOntSerial;

    public String getCOntSerial() {
        return cOntSerial;
    }

    public void setCOntSerial(String cOntSerial) {
        this.cOntSerial = cOntSerial;
    }

}