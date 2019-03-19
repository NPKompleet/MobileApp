package com.ipnx.ipnxmobile.wifianalyzer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.ipnx.ipnxmobile.R;
import com.ipnx.ipnxmobile.customviews.SignalMeter;

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

    @BindView(R.id.signal_meter)
    SignalMeter signalMeter;

    @BindView(R.id.signal_meter_indicator)
    ImageView indicator;

    FloatingActionButton fab;

    public SignalMeterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignalMeterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignalMeterFragment newInstance(String param1, String param2) {
        SignalMeterFragment fragment = new SignalMeterFragment();
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
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_signal_meter, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        signalMeter.setInterpolator(new BounceInterpolator());
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
        fab.show();
        customHandler = new Handler();
        wifiManager = (WifiManager) getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if(!wifiManager.isWifiEnabled()){
            Toast.makeText(this.getContext(), "Wifi is off. Please put on wifi", Toast.LENGTH_SHORT).show();
        }
        getActivity().registerReceiver(wifiReceiver, new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION));
    }

    public void getWifiInfo(){
        WifiInfo info = wifiManager.getConnectionInfo();
        signalMeter.moveHeadTo(info.getRssi() + 100);
        indicator.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.blink_on));

        Handler lightHandler = new Handler();
        lightHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                indicator.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.blink_off));
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
                indicator.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.blink_off));
            }
        }
    };


    @Override
    public void onPause() {
        customHandler.removeCallbacks(updateScanThread);
        getActivity().unregisterReceiver(wifiReceiver);
        fab.hide();
        super.onPause();
    }


    // TODO: Rename method, update argument and hook method into UI event
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
    }
}
