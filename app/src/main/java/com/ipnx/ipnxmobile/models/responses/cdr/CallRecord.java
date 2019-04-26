package com.ipnx.ipnxmobile.models.responses.cdr;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CallRecord {

    @SerializedName("rated_cost")
    @Expose
    private String ratedCost;
    @SerializedName("startdate")
    @Expose
    private String startdate;
    @SerializedName("svcnum")
    @Expose
    private int svcnum;
    @SerializedName("rated_granularity")
    @Expose
    private int ratedGranularity;
    @SerializedName("enddate")
    @Expose
    private String enddate;
    @SerializedName("rated_pretty_dst")
    @Expose
    private String ratedPrettyDst;
    @SerializedName("detailnum")
    @Expose
    private int detailnum;
    @SerializedName("rated_ratedetailnum")
    @Expose
    private int ratedRatedetailnum;
    @SerializedName("src")
    @Expose
    private String src;
    @SerializedName("acctid")
    @Expose
    private int acctid;
    @SerializedName("duration")
    @Expose
    private int duration;
    @SerializedName("rated_seconds")
    @Expose
    private int ratedSeconds;
    @SerializedName("freesidestatus")
    @Expose
    private String freesidestatus;
    @SerializedName("calldate")
    @Expose
    private String calldate;
    @SerializedName("rated_price")
    @Expose
    private String ratedPrice;
    @SerializedName("billsec")
    @Expose
    private int billsec;
    @SerializedName("rated_regionname")
    @Expose
    private String ratedRegionname;
    @SerializedName("charged_party")
    @Expose
    private String chargedParty;
    @SerializedName("dst")
    @Expose
    private String dst;

    public String getRatedCost() {
        return ratedCost;
    }

    public void setRatedCost(String ratedCost) {
        this.ratedCost = ratedCost;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public int getSvcnum() {
        return svcnum;
    }

    public void setSvcnum(int svcnum) {
        this.svcnum = svcnum;
    }

    public int getRatedGranularity() {
        return ratedGranularity;
    }

    public void setRatedGranularity(int ratedGranularity) {
        this.ratedGranularity = ratedGranularity;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getRatedPrettyDst() {
        return ratedPrettyDst;
    }

    public void setRatedPrettyDst(String ratedPrettyDst) {
        this.ratedPrettyDst = ratedPrettyDst;
    }

    public int getDetailnum() {
        return detailnum;
    }

    public void setDetailnum(int detailnum) {
        this.detailnum = detailnum;
    }

    public int getRatedRatedetailnum() {
        return ratedRatedetailnum;
    }

    public void setRatedRatedetailnum(int ratedRatedetailnum) {
        this.ratedRatedetailnum = ratedRatedetailnum;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public int getAcctid() {
        return acctid;
    }

    public void setAcctid(int acctid) {
        this.acctid = acctid;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getRatedSeconds() {
        return ratedSeconds;
    }

    public void setRatedSeconds(int ratedSeconds) {
        this.ratedSeconds = ratedSeconds;
    }

    public String getFreesidestatus() {
        return freesidestatus;
    }

    public void setFreesidestatus(String freesidestatus) {
        this.freesidestatus = freesidestatus;
    }

    public String getCalldate() {
        return calldate;
    }

    public void setCalldate(String calldate) {
        this.calldate = calldate;
    }

    public String getRatedPrice() {
        return ratedPrice;
    }

    public void setRatedPrice(String ratedPrice) {
        this.ratedPrice = ratedPrice;
    }

    public int getBillsec() {
        return billsec;
    }

    public void setBillsec(int billsec) {
        this.billsec = billsec;
    }

    public String getRatedRegionname() {
        return ratedRegionname;
    }

    public void setRatedRegionname(String ratedRegionname) {
        this.ratedRegionname = ratedRegionname;
    }

    public String getChargedParty() {
        return chargedParty;
    }

    public void setChargedParty(String chargedParty) {
        this.chargedParty = chargedParty;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

}
