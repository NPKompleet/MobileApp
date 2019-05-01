package com.ipnx.ipnxmobile.models.requests;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ipnx.ipnxmobile.models.CustomValues;

public class AddPaymentRequestValues extends CustomValues {

    @SerializedName("c.username")
    @Expose
    private String cUsername;
    @SerializedName("c.password")
    @Expose
    private String cPassword;
    @SerializedName("c.Amount")
    @Expose
    private long cAmount;
    @SerializedName("c.CardNumber")
    @Expose
    private String cCardNumber;
    @SerializedName("c.MerchantReference")
    @Expose
    private String cMerchantReference;
    @SerializedName("c.PaymentReference")
    @Expose
    private String cPaymentReference;
    @SerializedName("c.RetrievalReferenceNumber")
    @Expose
    private String cRetrievalReferenceNumber;
    @SerializedName("c.LeadBankCbnCode")
    @Expose
    private String cLeadBankCbnCode;
    @SerializedName("c.LeadBankName")
    @Expose
    private String cLeadBankName;
    @SerializedName("c.SplitAccounts")
    @Expose
    private List<Object> cSplitAccounts = null;
    @SerializedName("c.ResponseCode")
    @Expose
    private String cResponseCode;
    @SerializedName("c.ResponseDescription")
    @Expose
    private String cResponseDescription;
    @SerializedName("c.message")
    @Expose
    private String cMessage;
    @SerializedName("c.payment_method")
    @Expose
    private String cPaymentMethod;
    @SerializedName("c.package_number")
    @Expose
    private String cPackageNumber;
    @SerializedName("c.customer_number")
    @Expose
    private String cCustomerNumber;
    @SerializedName("c.transaction_date")
    @Expose
    private String cTransactionDate;
    @SerializedName("c.number_of_months")
    @Expose
    private String cNumberOfMonths;

    public String getCUsername() {
        return cUsername;
    }

    public void setCUsername(String cUsername) {
        this.cUsername = cUsername;
    }

    public String getCPassword() {
        return cPassword;
    }

    public void setCPassword(String cPassword) {
        this.cPassword = cPassword;
    }

    public long getCAmount() {
        return cAmount;
    }

    public void setCAmount(long cAmount) {
        this.cAmount = cAmount;
    }

    public String getCCardNumber() {
        return cCardNumber;
    }

    public void setCCardNumber(String cCardNumber) {
        this.cCardNumber = cCardNumber;
    }

    public String getCMerchantReference() {
        return cMerchantReference;
    }

    public void setCMerchantReference(String cMerchantReference) {
        this.cMerchantReference = cMerchantReference;
    }

    public String getCPaymentReference() {
        return cPaymentReference;
    }

    public void setCPaymentReference(String cPaymentReference) {
        this.cPaymentReference = cPaymentReference;
    }

    public String getCRetrievalReferenceNumber() {
        return cRetrievalReferenceNumber;
    }

    public void setCRetrievalReferenceNumber(String cRetrievalReferenceNumber) {
        this.cRetrievalReferenceNumber = cRetrievalReferenceNumber;
    }

    public String getCLeadBankCbnCode() {
        return cLeadBankCbnCode;
    }

    public void setCLeadBankCbnCode(String cLeadBankCbnCode) {
        this.cLeadBankCbnCode = cLeadBankCbnCode;
    }

    public String getCLeadBankName() {
        return cLeadBankName;
    }

    public void setCLeadBankName(String cLeadBankName) {
        this.cLeadBankName = cLeadBankName;
    }

    public List<Object> getCSplitAccounts() {
        return cSplitAccounts;
    }

    public void setCSplitAccounts(List<Object> cSplitAccounts) {
        this.cSplitAccounts = cSplitAccounts;
    }

    public String getCResponseCode() {
        return cResponseCode;
    }

    public void setCResponseCode(String cResponseCode) {
        this.cResponseCode = cResponseCode;
    }

    public String getCResponseDescription() {
        return cResponseDescription;
    }

    public void setCResponseDescription(String cResponseDescription) {
        this.cResponseDescription = cResponseDescription;
    }

    public String getCMessage() {
        return cMessage;
    }

    public void setCMessage(String cMessage) {
        this.cMessage = cMessage;
    }

    public String getCPaymentMethod() {
        return cPaymentMethod;
    }

    public void setCPaymentMethod(String cPaymentMethod) {
        this.cPaymentMethod = cPaymentMethod;
    }

    public String getCPackageNumber() {
        return cPackageNumber;
    }

    public void setCPackageNumber(String cPackageNumber) {
        this.cPackageNumber = cPackageNumber;
    }

    public String getCCustomerNumber() {
        return cCustomerNumber;
    }

    public void setCCustomerNumber(String cCustomerNumber) {
        this.cCustomerNumber = cCustomerNumber;
    }

    public String getCTransactionDate() {
        return cTransactionDate;
    }

    public void setCTransactionDate(String cTransactionDate) {
        this.cTransactionDate = cTransactionDate;
    }

    public String getCNumberOfMonths() {
        return cNumberOfMonths;
    }

    public void setCNumberOfMonths(String cNumberOfMonths) {
        this.cNumberOfMonths = cNumberOfMonths;
    }

}