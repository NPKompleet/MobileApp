package com.ipnx.ipnxmobile.models.responses.cdr;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CallRecordCustomValues{

    @SerializedName("records")
    @Expose
    private List<CallRecord> records = null;

    public List<CallRecord> getRecords() {
        return records;
    }

    public void setRecords(List<CallRecord> records) {
        this.records = records;
    }

}