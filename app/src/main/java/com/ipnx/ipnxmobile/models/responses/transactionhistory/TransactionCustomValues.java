package com.ipnx.ipnxmobile.models.responses.transactionhistory;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransactionCustomValues implements Parcelable {

    @SerializedName("payments")
    @Expose
    private List<TransactionPayment> payments = null;
    @SerializedName("total")
    @Expose
    private long total;

    protected TransactionCustomValues(Parcel in) {
        payments = in.createTypedArrayList(TransactionPayment.CREATOR);
        total = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(payments);
        dest.writeLong(total);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TransactionCustomValues> CREATOR = new Creator<TransactionCustomValues>() {
        @Override
        public TransactionCustomValues createFromParcel(Parcel in) {
            return new TransactionCustomValues(in);
        }

        @Override
        public TransactionCustomValues[] newArray(int size) {
            return new TransactionCustomValues[size];
        }
    };

    public List<TransactionPayment> getPayments() {
        return payments;
    }

    public void setPayments(List<TransactionPayment> payments) {
        this.payments = payments;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

}

