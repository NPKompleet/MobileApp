package com.ipnx.ipnxmobile.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ipnx.ipnxmobile.R;
import com.ipnx.ipnxmobile.models.responses.transactionhistory.TransactionPayment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionHistoryAdapter extends
        RecyclerView.Adapter<TransactionHistoryAdapter.HistoryHolder> {

    private Context context;
    private List<TransactionPayment> paymentList;

    public TransactionHistoryAdapter(List<TransactionPayment> paymentList) {
        this.paymentList = paymentList;
    }

    public void setData(List<TransactionPayment> paymentList){
        this.paymentList = paymentList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        context= parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_transaction_history, parent, false);

        return new HistoryHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryHolder historyHolder, int position) {
        TransactionPayment item = paymentList.get(position);
        historyHolder.date.setText(item.getDate());
        historyHolder.amount.setText(item.getAmount());
        historyHolder.paymentMethod.setText(item.getPaymentMethod());
        historyHolder.packageType.setText(item.getPackageName());

    }

    @Override
    public int getItemCount() {
        return paymentList == null ? 0 : paymentList.size();
    }

    public class HistoryHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.amount)
        TextView amount;
        @BindView(R.id.payment_method)
        TextView paymentMethod;
        @BindView(R.id.package_type)
        TextView packageType;

        public HistoryHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
