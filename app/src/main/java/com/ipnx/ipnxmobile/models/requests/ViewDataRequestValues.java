package com.ipnx.ipnxmobile.models.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ipnx.ipnxmobile.models.CustomValues;

public class ViewDataRequestValues extends CustomValues {

    @SerializedName("c.ont_serial")
    @Expose
    private String cOntSerial;
    @SerializedName("c.regime")
    @Expose
    private String cRegime;
    @SerializedName("c.package_name")
    @Expose
    private String cPackageName;

    public String getCOntSerial() {
        return cOntSerial;
    }

    public void setCOntSerial(String cOntSerial) {
        this.cOntSerial = cOntSerial;
    }

    public String getCRegime() {
        return cRegime;
    }

    public void setCRegime(String cRegime) {
        this.cRegime = cRegime;
    }

    public String getCPackageName() {
        return cPackageName;
    }

    public void setCPackageName(String cPackageName) {
        this.cPackageName = cPackageName;
    }

}