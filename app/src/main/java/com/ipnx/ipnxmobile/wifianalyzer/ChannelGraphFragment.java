package com.ipnx.ipnxmobile.wifianalyzer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ipnx.ipnxmobile.R;
import com.ipnx.ipnxmobile.customviews.TitleLineGraphSeries;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.ipnx.ipnxmobile.utils.ChannelGraphUtils.addAlpha;
import static com.ipnx.ipnxmobile.utils.ChannelGraphUtils.channelColors;
import static com.ipnx.ipnxmobile.utils.ChannelGraphUtils.channelMap;
import static com.ipnx.ipnxmobile.utils.ChannelGraphUtils.createDataPoints;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ChannelGraphFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ChannelGraphFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChannelGraphFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    WifiManager wifiManager;
    Handler customHandler;
    Unbinder unbinder;

    List<ScanResult> scanResults = new ArrayList<>();

    @BindView(R.id.channel_graph)
    GraphView graphView;

    FloatingActionButton fab;


    public ChannelGraphFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChannelGraphFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChannelGraphFragment newInstance(String param1, String param2) {
        ChannelGraphFragment fragment = new ChannelGraphFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_channel_graph, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        // set manual X bounds
        graphView.getViewport().setXAxisBoundsManual(true);
        graphView.getViewport().setMinX(-1);
        graphView.getViewport().setMaxX(14);

        // set manual Y bounds
        graphView.getViewport().setYAxisBoundsManual(true);
        graphView.getViewport().setMinY(-100);
        graphView.getViewport().setMaxY(-20);

        graphView.getGridLabelRenderer().setGridColor(Color.RED);

        graphView.getGridLabelRenderer().setNumHorizontalLabels(16);
        graphView.getGridLabelRenderer().setNumVerticalLabels(10);
        graphView.getGridLabelRenderer().setLabelVerticalWidth(20);
        graphView.getGridLabelRenderer().setHighlightZeroLines(false);
        graphView.getGridLabelRenderer().setTextSize(18f);
        graphView.setTitleTextSize(24f);
        graphView.getGridLabelRenderer().setVerticalAxisTitle("Signal Strength (dBm)");
        graphView.getGridLabelRenderer().setHorizontalAxisTitle("Channels");
        graphView.getGridLabelRenderer().reloadStyles();

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fab = getActivity().findViewById(R.id.fab);
    }

    @Override
    public void onResume() {
        super.onResume();
//        fab.hide();
        wifiManager = (WifiManager) getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        getActivity().registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        customHandler = new Handler();
        customHandler.postDelayed(updateScanThread, 0);
    }

    public void updateGraph(){
        graphView.removeAllSeries();
        int i= 0;
        for(ScanResult scanResult : scanResults){
            int channel;
            try {
                channel = channelMap.get(scanResult.frequency);
            }catch (NullPointerException e){
                e.printStackTrace();
                System.out.println(scanResult.frequency);
                continue;
            }
            int color = i < 7 ? channelColors[i]
                    : channelColors[i % 7];
            DataPoint[] dataPoints = createDataPoints(
                    channel, scanResult.level);
            TitleLineGraphSeries<DataPoint> series = new TitleLineGraphSeries<>(dataPoints);
            series.setTitle(scanResult.SSID);
            series.setColor(color);
            series.setThickness(5);
            series.setDrawBackground(true);
            series.setBackgroundColor(addAlpha(color));
            graphView.addSeries(series);
            i++;
        }
    }

    public void getScans(){
//        getActivity().registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        wifiManager.startScan();
//        Toast.makeText(this.getContext(), "Scanning WiFi ...", Toast.LENGTH_SHORT).show();
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    private Runnable updateScanThread = new Runnable()
    {
        public void run()
        {
            getScans();
            customHandler.postDelayed(this, 2000);
        }
    };

    BroadcastReceiver wifiReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            scanResults = wifiManager.getScanResults();
//            getActivity().unregisterReceiver(this);
            updateGraph();
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
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
//        void onFragmentInteraction();
    }
}
