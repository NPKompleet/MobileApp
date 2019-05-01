package com.ipnx.ipnxmobile.models.responses.datausage;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DataHistoryCustomValues {

    @SerializedName("history")
    @Expose
    private List<List<String>> history = null;

    public List<List<String>> getHistory() {
        return history;
    }

    public void setHistory(List<List<String>> history) {
        this.history = history;
    }

}