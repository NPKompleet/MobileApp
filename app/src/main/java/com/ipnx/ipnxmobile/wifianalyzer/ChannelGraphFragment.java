package com.ipnx.ipnxmobile.wifianalyzer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ipnx.ipnxmobile.R;
import com.ipnx.ipnxmobile.customviews.TitleLineGraphSeries;
import com.ipnx.ipnxmobile.utils.ApplicationUtils;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.ipnx.ipnxmobile.utils.ApplicationUtils.isLocationEnabled;
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

    private OnFragmentInteractionListener mListener;

    WifiManager wifiManager;
    Handler customHandler;
    Unbinder unbinder;

    List<ScanResult> scanResults = new ArrayList<>();

    @BindView(R.id.channel_graph)
    GraphView graphView;

    @BindView(R.id.fab_channel_graph)
    FloatingActionButton fab;

    @BindView(R.id.channel_graph_message)
    TextView message;


    public ChannelGraphFragment() {
        // Required empty public constructor
    }


    public static ChannelGraphFragment newInstance() {
        return new ChannelGraphFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    }

    @Override
    public void onResume() {
        super.onResume();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareViewImage();
            }
        });
        wifiManager = (WifiManager) getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        getActivity().registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        customHandler = new Handler();
        customHandler.postDelayed(updateScanThread, 0);
    }

    private void makeToast(String source) {
        Toast.makeText(this.getContext(), "from: " + source, Toast.LENGTH_SHORT).show();
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
        wifiManager.startScan();
//        Toast.makeText(this.getContext(), "Scanning WiFi ...", Toast.LENGTH_SHORT).show();
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
            updateGraph();
        }
    };


    public void shareViewImage(){
        Bitmap bitmap= ApplicationUtils.getBitmapFromView(graphView);
        ApplicationUtils.saveBitmapToCache(bitmap, getContext());

        File imagePath = new File(getContext().getCacheDir(), "images");
        File newFile = new File(imagePath, "image.png");
        Uri contentUri = FileProvider.getUriForFile(getContext(), "com.ipnx.ipnxmobile.fileprovider", newFile);

        if (contentUri != null) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // temp permission for receiving app to read this file
            shareIntent.setDataAndType(contentUri, getContext().getContentResolver().getType(contentUri));
            shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Shared from ipNX Mobile App\nCopyright © ipNX 2019");
            startActivity(Intent.createChooser(shareIntent, "Choose an app"));
        }
    }

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
        void onFragmentInteraction(Uri uri);
    }
}
