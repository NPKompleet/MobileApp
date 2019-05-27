package com.ipnx.ipnxmobile.models.responses.wifipassword;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WifiPasswordCustomValues {

    @SerializedName("data")
    @Expose
    private List<WifiResponseData> data = null;

    public List<WifiResponseData> getData() {
        return data;
    }

    public void setData(List<WifiResponseData> data) {
        this.data = data;
    }

}
