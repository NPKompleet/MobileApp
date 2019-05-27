package com.ipnx.ipnxmobile;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings.Secure;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ipnx.ipnxmobile.models.requests.LoginRequestValues;
import com.ipnx.ipnxmobile.models.requests.Request;
import com.ipnx.ipnxmobile.models.responses.login.LoginCustomValues;
import com.ipnx.ipnxmobile.models.responses.login.LoginResponse;
import com.ipnx.ipnxmobile.retrofit.MyApiEndpointInterface;
import com.ipnx.ipnxmobile.retrofit.RetrofitUtils;
import com.ipnx.ipnxmobile.wifianalyzer.WifiAnalyzerActivity;

import static com.ipnx.ipnxmobile.utils.ApplicationUtils.*;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.login_linear_layout)
    LinearLayout linearLayout;

    @BindView(R.id.login_progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.login_username)
    EditText username;

    @BindView(R.id.login_password)
    EditText password;

    @BindView(R.id.login_status_text)
    TextView loginStatus;

    MyApiEndpointInterface myApi;

    static private final int MY_PERMISSIONS_REQUEST_LOCATION= 22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkLocationPermission();
    }

    public void openMainView(View view){
        Location location = getLastBestLocation();

        if (anyFieldIsEmpty(new TextView[]{username, password})){
            Snackbar.make(linearLayout, "UserId or Password cannot be empty", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return;
        }

        if (!networkActive(this)){
            Snackbar.make(linearLayout, "No network available. Check your network", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        loginStatus.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.INVISIBLE);

        Request loginRequest = new Request();
        final LoginRequestValues loginValues = new LoginRequestValues();
        loginValues.setCUsername(username.getText().toString());
        loginValues.setCPassword(password.getText().toString());

        loginRequest.setAction(ACTION_LOGIN);
        loginRequest.setCustomValues(loginValues);

        DEVICE_ID = Secure.getString(getApplicationContext().getContentResolver(), Secure.ANDROID_ID);
        loginRequest.setDid(DEVICE_ID);

        if (location != null){
            loginRequest.setLat("" + location.getLatitude());
            loginRequest.setLon("" + location.getLongitude());
            loginRequest.setLt(location.getProvider());
            Toast.makeText(this, location.toString(), Toast.LENGTH_SHORT).show();
        }

        myApi= RetrofitUtils.getService();
        Call<LoginResponse> call = myApi.loginUser(loginRequest);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
//                Toast.makeText(LoginActivity.this, "deviceId: "+ DEVICE_ID, Toast.LENGTH_SHORT).show();
                LoginResponse returnedResponse = response.body();
                if (returnedResponse == null){
                    Toast.makeText(LoginActivity.this, "Network error. Please try again", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                    loginStatus.setVisibility(View.INVISIBLE);
                    linearLayout.setVisibility(View.VISIBLE);
                    return;
                }

                setProfile(returnedResponse.getCustomValues());
                // Checks if the user is validated. 0 means valid response
                if (returnedResponse.getResponseCode().equals("0")){
                    Intent i = new Intent(LoginActivity.this, ServicesMenuActivity.class);
                    userProfile.setUserName(loginValues.getCUsername());
                    userProfile.setPassword(loginValues.getCPassword());
                    i.putExtra(EXTRA_KEY_RESPONSE, returnedResponse);
                    i.putExtra(EXTRA_KEY_LOGIN, loginValues);
                    startActivity(i);
                    closeActivity();
                }else{
                    progressBar.setVisibility(View.INVISIBLE);
                    loginStatus.setVisibility(View.INVISIBLE);
                    linearLayout.setVisibility(View.VISIBLE);

                    Snackbar.make(linearLayout, returnedResponse.getResponseMessage(), Snackbar.LENGTH_LONG)
                            .setAction("Wrong Email or Password.", null).show();
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Network Error.", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
                progressBar.setVisibility(View.INVISIBLE);
                loginStatus.setVisibility(View.INVISIBLE);
                linearLayout.setVisibility(View.VISIBLE);

//                Intent i = new Intent(LoginActivity.this, WifiAnalyzerActivity.class);
//                startActivity(i);

            }
        });

    }

    private void setProfile(LoginCustomValues values) {
        userProfile.setFullName(values.getFullName());
        userProfile.setAddress(values.getAddress());
        userProfile.setEmailAddress(values.getEmailAddress());
        userProfile.setPhoneNumber(values.getPhoneNumber());
        userProfile.setId(values.getId());
        userProfile.setFirstName(values.getFirstName());
        userProfile.setLastName(values.getLastName());
        userProfile.setPhoneNumber(values.getPhoneNumber());
        userProfile.setCustomerNumber(values.getCustomerNumber());
    }

    public void onForgotPasswordClicked(View view){
        Intent i = new Intent(this, ForgotPasswordActivity.class);
        startActivity(i);
    }

    private void closeActivity() {
        finish();
    }

    private void checkLocationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {  // Only ask for these permissions on runtime when running Android 6.0 or higher
            switch (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_COARSE_LOCATION)) {
                case PackageManager.PERMISSION_DENIED:
                    if (ContextCompat.checkSelfPermission(getBaseContext(),
                            Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "Must grant location permission to use wifi analyzer on this device", Toast.LENGTH_SHORT).show();
                        ActivityCompat.requestPermissions(LoginActivity.this,
                                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                                        Manifest.permission.ACCESS_FINE_LOCATION},
                                MY_PERMISSIONS_REQUEST_LOCATION);

//                        ((TextView) new AlertDialog.Builder(this)
//                                .setTitle("Runtime Permissions needed")
//                                .setMessage(Html.fromHtml("<p>To find nearby wifi devices please click \"Allow\" on the runtime permissions popup.</p>" +
//                                        "<p>For more info see <a href=\"http://developer.android.com/about/versions/marshmallow/android-6.0-changes.html#behavior-hardware-id\">here</a>.</p>"))
//                                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        if (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                                            ActivityCompat.requestPermissions(LoginActivity.this,
//                                                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
//                                                    MY_PERMISSIONS_REQUEST_COARSE_LOCATION);
//                                        }
//                                    }
//                                })
//                                .show()
//                                .findViewById(android.R.id.message))
//                                .setMovementMethod(LinkMovementMethod.getInstance());
                        // Make the link clickable. Needs to be called after show(), in order to generate hyperlinks
                    }
//                    checkLocationPermission();
                    break;
                case PackageManager.PERMISSION_GRANTED:
                    break;
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1]==PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    Toast.makeText(
                            getApplicationContext(),
                            "Permissions Granted",
                            Toast.LENGTH_SHORT).show();
                } else {
                    // permission denied. Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(
                            getApplicationContext(),
                            "Permissions Denied",
                            Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
