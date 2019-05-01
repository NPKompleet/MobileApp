package com.ipnx.ipnxmobile.wifianalyzer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.ipnx.ipnxmobile.R;
import com.ipnx.ipnxmobile.customviews.SignalMeter;
import com.ipnx.ipnxmobile.utils.ApplicationUtils;

import java.io.File;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SignalMeterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SignalMeterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignalMeterFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    WifiManager wifiManager;
    Handler customHandler;
    Unbinder unbinder;

    @BindView(R.id.signal_meter)
    SignalMeter signalMeter;

    @BindView(R.id.signal_meter_indicator)
    ImageView indicator;

    @BindView(R.id.fab_signal_meter)
    FloatingActionButton fab;

    public SignalMeterFragment() {
        // Required empty public constructor
    }


    public static SignalMeterFragment newInstance() {
        return new SignalMeterFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_signal_meter, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        signalMeter.setInterpolator(new BounceInterpolator());
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
        customHandler = new Handler();
        wifiManager = (WifiManager) getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if(!wifiManager.isWifiEnabled()){
            Toast.makeText(this.getContext(), "Wifi is off. Please put on wifi", Toast.LENGTH_SHORT).show();
        }
        getActivity().registerReceiver(wifiReceiver, new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION));
    }

//    private void makeToast(String source) {
//        Toast.makeText(this.getContext(), "from: " + source, Toast.LENGTH_SHORT).show();
//    }

    public void getWifiInfo(){
        WifiInfo info = wifiManager.getConnectionInfo();
        signalMeter.moveHeadTo(info.getRssi() + 100);
        indicator.setImageDrawable(getResources().getDrawable(R.drawable.blink_on));

        Handler lightHandler = new Handler();
        lightHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                indicator.setImageDrawable(getResources().getDrawable(R.drawable.blink_off));
            }
        }, 500);

    }

    private Runnable updateScanThread = new Runnable()
    {
        public void run()
        {
            getWifiInfo();
            customHandler.postDelayed(this, 2000);
        }
    };

    BroadcastReceiver wifiReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (wifiManager.isWifiEnabled()) {
                customHandler.postDelayed(updateScanThread, 0);
                Toast.makeText(context, "Wifi is on", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context, "WiFi is off", Toast.LENGTH_SHORT).show();
                customHandler.removeCallbacks(updateScanThread);
                // Move signal meter to -90dBm
                signalMeter.moveHeadTo(10);
                indicator.setImageDrawable(getResources().getDrawable(R.drawable.blink_off));
            }
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


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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


    public void shareViewImage(){
        Bitmap bitmap= ApplicationUtils.getBitmapFromView(signalMeter);
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
