package com.ipnx.ipnxmobile.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ipnx.ipnxmobile.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DataHistoryAdapter extends
        RecyclerView.Adapter<DataHistoryAdapter.HistoryHolder> {

    private Context context;
    private List<List<String>> historyList;

    public DataHistoryAdapter(List<List<String>> historyList) {
        this.historyList = historyList;
    }

    public void setData(List<List<String>> historyList){
        this.historyList = historyList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        context= parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_data_usage, parent, false);

        return new HistoryHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryHolder historyHolder, int position) {
        List<String> item = historyList.get(position);
        historyHolder.month.setText(item.get(0));
        historyHolder.data.setText(item.get(1));

    }

    @Override
    public int getItemCount() {
        return historyList == null ? 0 : historyList.size();
    }

    public class HistoryHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.month)
        TextView month;
        @BindView(R.id.data)
        TextView data;

        public HistoryHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
