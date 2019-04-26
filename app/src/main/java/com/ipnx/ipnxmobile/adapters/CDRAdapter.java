package com.ipnx.ipnxmobile.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ipnx.ipnxmobile.R;
import com.ipnx.ipnxmobile.models.responses.cdr.CallRecord;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CDRAdapter extends
        RecyclerView.Adapter<CDRAdapter.RecordHolder> {

    private Context context;
    private List<CallRecord> recordList;

    public CDRAdapter(List<CallRecord> recordList) {
        this.recordList = recordList;
    }

    public void setData(List<CallRecord> recordList){
        this.recordList = recordList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecordHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        context= parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_cdr, parent, false);

        return new RecordHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordHolder recordHolder, int position) {
        CallRecord item = recordList.get(position);
        recordHolder.startDate.setText(item.getStartdate());
        recordHolder.endDate.setText(item.getEnddate());
        recordHolder.duration.setText(item.getDuration());
        recordHolder.price.setText(item.getRatedPrice());

    }

    @Override
    public int getItemCount() {
        return recordList == null ? 0 : recordList.size();
    }

    public class RecordHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.start_date)
        TextView startDate;
        @BindView(R.id.end_date)
        TextView endDate;
        @BindView(R.id.duration)
        TextView duration;
        @BindView(R.id.price)
        TextView price;

        public RecordHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
