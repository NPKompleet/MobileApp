package com.ipnx.ipnxmobile.wifianalyzer;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ipnx.ipnxmobile.R;
import com.ipnx.ipnxmobile.customviews.MeterView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScanListAdapter extends RecyclerView.Adapter<ScanListAdapter.ScanHolder>{
    private Context context;
    private List<ScanResult> scanResultList;

    public ScanListAdapter(List<ScanResult> scanResultList) {
        this.scanResultList = scanResultList;
    }

    public void setData(List<ScanResult> scans){
        this.scanResultList= scans;
        notifyDataSetChanged();
    }

    public void clear(){
        this.scanResultList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ScanListAdapter.ScanHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        context= parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_scan_list, parent, false);

        return new ScanHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ScanHolder holder, int position) {
        ScanResult item = scanResultList.get(position);
        holder.ssId.setText(item.SSID);
        holder.bssId.setText(item.BSSID);
        holder.frequency.setText(item.frequency + "MHz");
        holder.level.setText(item.level + "dBm");
        holder.meter.moveHeadTo(item.level + 100);
    }

    @Override
    public int getItemCount() {
        return scanResultList == null ? 0 : scanResultList.size();
    }

    public class ScanHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.scan_ssid)
        TextView ssId;
        @BindView(R.id.scan_bssid)
        TextView bssId;
        @BindView(R.id.scan_frequency)
        TextView frequency;
        @BindView(R.id.scan_level)
        TextView level;
        @BindView(R.id.scan_meter)
        MeterView meter;


        public ScanHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
