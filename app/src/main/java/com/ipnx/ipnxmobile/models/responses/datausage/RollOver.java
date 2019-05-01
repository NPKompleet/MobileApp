package com.ipnx.ipnxmobile.models.responses.datausage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RollOver {

    @SerializedName("data_rolledover")
    @Expose
    private String dataRolledover;

    public String getDataRolledover() {
        return dataRolledover;
    }

    public void setDataRolledover(String dataRolledover) {
        this.dataRolledover = dataRolledover;
    }

}
