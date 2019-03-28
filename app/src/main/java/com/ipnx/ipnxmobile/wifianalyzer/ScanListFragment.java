package com.ipnx.ipnxmobile.wifianalyzer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
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

public class ScanListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    Unbinder unbinder;
    List<ScanResult> scanResults = new ArrayList<>();
    @BindView(R.id.rv_scan_list)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_scan_list)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.section_label)
    TextView textView;

    ScanListAdapter adapter;
    WifiManager wifiManager;
    Handler customHandler;

    FloatingActionButton fab;

    public ScanListFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ScanListFragment newInstance(int sectionNumber) {
        ScanListFragment fragment = new ScanListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_scan_list, container, false);
//        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        unbinder = ButterKnife.bind(this, rootView);
        textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        swipeRefreshLayout.setOnRefreshListener(this);
        adapter= new ScanListAdapter(scanResults);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(adapter);
        fab = getActivity().findViewById(R.id.fab);
//        onRefresh();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        fab.show();
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


    public void printLength(){
        Toast.makeText(this.getContext(), "Scan result: "+ scanResults.size(), Toast.LENGTH_SHORT).show();
    }

    public void getScans(){
//        getActivity().registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        wifiManager.startScan();
        Toast.makeText(this.getContext(), "Scanning WiFi ...", Toast.LENGTH_SHORT).show();
    }

    BroadcastReceiver wifiReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            scanResults = wifiManager.getScanResults();
            printLength();
            adapter.setData(scanResults, wifiManager.getConnectionInfo().getBSSID());
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
        getActivity().unregisterReceiver(wifiReceiver);
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
