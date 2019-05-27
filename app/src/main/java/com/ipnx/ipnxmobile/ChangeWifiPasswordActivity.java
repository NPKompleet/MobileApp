package com.ipnx.ipnxmobile;

import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.ipnx.ipnxmobile.models.requests.ChangeWifiRequestValues;
import com.ipnx.ipnxmobile.models.requests.Request;
import com.ipnx.ipnxmobile.models.responses.wifipassword.WifiPasswordResponse;
import com.ipnx.ipnxmobile.models.responses.wifipassword.WifiResponseData;
import com.ipnx.ipnxmobile.retrofit.MyApiEndpointInterface;
import com.ipnx.ipnxmobile.retrofit.RetrofitUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ipnx.ipnxmobile.utils.ApplicationUtils.ACTION_WIFI_PASSWORD;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.DEVICE_ID;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_ONT_SERIAL;

public class ChangeWifiPasswordActivity extends BaseActivity {

    String ontSerial;

    @BindView(R.id.wireless_name)
    EditText wirelessName;

    @BindView(R.id.wireless_device)
    EditText wirelessDevice;

    @BindView(R.id.wireless_status)
    EditText wirelessStatus;

    @BindView(R.id.wireless_security)
    EditText wirelessSecurity;

    @BindView(R.id.wireless_newPassword)
    EditText wirelessNewPassword;

    List<WifiResponseData> dataList = new ArrayList<>();

    MyApiEndpointInterface myApi;

    PopupMenu deviceNamePopUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_wifi_password);
        ButterKnife.bind(this);

        ontSerial = getIntent().getStringExtra(EXTRA_KEY_ONT_SERIAL);
        deviceNamePopUp = new PopupMenu(this, wirelessName);
        getWifiInfo();
    }

    private void getWifiInfo() {
        Request changePasswordRequest = new Request();
        ChangeWifiRequestValues requestValues = new ChangeWifiRequestValues();
        requestValues.setCSettings("1");
        requestValues.setCOntSerial(ontSerial);

        changePasswordRequest.setCustomValues(requestValues);
        changePasswordRequest.setAction(ACTION_WIFI_PASSWORD);
        changePasswordRequest.setDid(DEVICE_ID);

        Location location  = getLastBestLocation();
        if (location != null){
            changePasswordRequest.setLat("" + location.getLatitude());
            changePasswordRequest.setLon("" + location.getLongitude());
            changePasswordRequest.setLt(location.getProvider());
            Toast.makeText(this, location.toString(), Toast.LENGTH_SHORT).show();
        }

        myApi= RetrofitUtils.getService();
        Call<WifiPasswordResponse> call = myApi.changeWifiPassword(changePasswordRequest);
        call.enqueue(new Callback<WifiPasswordResponse>() {
            @Override
            public void onResponse(Call<WifiPasswordResponse> call, Response<WifiPasswordResponse> response) {
                WifiPasswordResponse wifiPasswordResponse = response.body();
                if (wifiPasswordResponse == null){
                    return;
                }
                dataList = wifiPasswordResponse.getCustomValues().getData();
                Toast.makeText(ChangeWifiPasswordActivity.this, "" + dataList.size(), Toast.LENGTH_SHORT).show();
                int itemId = 0;
                for (WifiResponseData data: dataList){
                    deviceNamePopUp.getMenu().add(Menu.NONE, itemId, Menu.NONE, data.getInstance());
                    itemId++;
                }

            }

            @Override
            public void onFailure(Call<WifiPasswordResponse> call, Throwable t) {
                Toast.makeText(ChangeWifiPasswordActivity.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });

        deviceNamePopUp.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                wirelessName.setText(menuItem.getTitle());
                wirelessDevice.setText("" + dataList.get(menuItem.getItemId()).getDevice());
                wirelessStatus.setText("" + dataList.get(menuItem.getItemId()).getStatus());
                wirelessSecurity.setText("" + dataList.get(menuItem.getItemId()).getSecurity());
                return true;
            }
        });
    }

    public void onBackClicked(View view){
        finish();
    }

    public void onWifiNameSelected(View view){
        deviceNamePopUp.show();
    }

    public void onApplyButtonClicked(View view){}
}
