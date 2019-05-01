package com.ipnx.ipnxmobile.wifianalyzer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ipnx.ipnxmobile.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.ipnx.ipnxmobile.utils.ApplicationUtils.isLocationEnabled;

public class ScanListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    Unbinder unbinder;
    List<ScanResult> scanResults = new ArrayList<>();
    @BindView(R.id.rv_scan_list)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_scan_list)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.scan_list_message)
    TextView message;

    ScanListAdapter adapter;
    WifiManager wifiManager;
    Handler customHandler;

    public ScanListFragment() {
    }

    public static ScanListFragment newInstance() {
        return new ScanListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_scan_list, container, false);
        unbinder = ButterKnife.bind(this, rootView);
//        message.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        swipeRefreshLayout.setOnRefreshListener(this);
        adapter= new ScanListAdapter(scanResults);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        wifiManager = (WifiManager) getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        getActivity().registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        customHandler = new Handler();
        onRefresh();
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        customHandler.postDelayed(updateScanThread, 0);
        swipeRefreshLayout.setRefreshing(false);

    }

    public void getScans(){
        wifiManager.startScan();
    }

    BroadcastReceiver wifiReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            scanResults = wifiManager.getScanResults();
            if (scanResults.isEmpty()) {
                message.setVisibility(View.VISIBLE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !isLocationEnabled(context)) {
                    message.setText("To enable Scanning, please turn on your location settings.");
                    return;
                }
                message.setText("There are no Wifi sources available");
                return;
            }
            message.setVisibility(View.INVISIBLE);
            adapter.setData(scanResults, wifiManager.getConnectionInfo().getSSID(),
            wifiManager.getConnectionInfo().getBSSID());
        }
    };


    private Runnable updateScanThread = new Runnable()
    {
        public void run()
        {
            getScans();
            customHandler.postDelayed(this, 2000);
        }
    };

    @Override
    public void onPause() {
        customHandler.removeCallbacks(updateScanThread);
        try {
            getActivity().unregisterReceiver(wifiReceiver);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
