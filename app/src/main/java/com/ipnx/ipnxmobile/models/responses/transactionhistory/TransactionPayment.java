package com.ipnx.ipnxmobile.models.responses.transactionhistory;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransactionPayment implements Parcelable {

    @SerializedName("package_status_color")
    @Expose
    private String packageStatusColor;
    @SerializedName("retrieval_reference_number")
    @Expose
    private String retrievalReferenceNumber;
    @SerializedName("cheque_details")
    @Expose
    private String chequeDetails;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("transaction_number")
    @Expose
    private String transactionNumber;
    @SerializedName("package_name")
    @Expose
    private String packageName;
    @SerializedName("invoice_payments")
    @Expose
    private List<Object> invoicePayments = null;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("package_status")
    @Expose
    private String packageStatus;
    @SerializedName("transaction_reference")
    @Expose
    private String transactionReference;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("payment_method")
    @Expose
    private String paymentMethod;
    @SerializedName("payment_id")
    @Expose
    private long paymentId;
    @SerializedName("package_number")
    @Expose
    private long packageNumber;
    @SerializedName("unapplied")
    @Expose
    private String unapplied;

    protected TransactionPayment(Parcel in) {
        packageStatusColor = in.readString();
        retrievalReferenceNumber = in.readString();
        chequeDetails = in.readString();
        date = in.readString();
        transactionNumber = in.readString();
        packageName = in.readString();
        username = in.readString();
        packageStatus = in.readString();
        transactionReference = in.readString();
        amount = in.readString();
        paymentMethod = in.readString();
        paymentId = in.readLong();
        packageNumber = in.readLong();
        unapplied = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(packageStatusColor);
        dest.writeString(retrievalReferenceNumber);
        dest.writeString(chequeDetails);
        dest.writeString(date);
        dest.writeString(transactionNumber);
        dest.writeString(packageName);
        dest.writeString(username);
        dest.writeString(packageStatus);
        dest.writeString(transactionReference);
        dest.writeString(amount);
        dest.writeString(paymentMethod);
        dest.writeLong(paymentId);
        dest.writeLong(packageNumber);
        dest.writeString(unapplied);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TransactionPayment> CREATOR = new Creator<TransactionPayment>() {
        @Override
        public TransactionPayment createFromParcel(Parcel in) {
            return new TransactionPayment(in);
        }

        @Override
        public TransactionPayment[] newArray(int size) {
            return new TransactionPayment[size];
        }
    };

    public String getPackageStatusColor() {
        return packageStatusColor;
    }

    public void setPackageStatusColor(String packageStatusColor) {
        this.packageStatusColor = packageStatusColor;
    }

    public String getRetrievalReferenceNumber() {
        return retrievalReferenceNumber;
    }

    public void setRetrievalReferenceNumber(String retrievalReferenceNumber) {
        this.retrievalReferenceNumber = retrievalReferenceNumber;
    }

    public String getChequeDetails() {
        return chequeDetails;
    }

    public void setChequeDetails(String chequeDetails) {
        this.chequeDetails = chequeDetails;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<Object> getInvoicePayments() {
        return invoicePayments;
    }

    public void setInvoicePayments(List<Object> invoicePayments) {
        this.invoicePayments = invoicePayments;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPackageStatus() {
        return packageStatus;
    }

    public void setPackageStatus(String packageStatus) {
        this.packageStatus = packageStatus;
    }

    public String getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

    public long getPackageNumber() {
        return packageNumber;
    }

    public void setPackageNumber(long packageNumber) {
        this.packageNumber = packageNumber;
    }

    public String getUnapplied() {
        return unapplied;
    }

    public void setUnapplied(String unapplied) {
        this.unapplied = unapplied;
    }

}
