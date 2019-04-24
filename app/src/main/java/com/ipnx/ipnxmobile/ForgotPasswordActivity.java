package com.ipnx.ipnxmobile;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ipnx.ipnxmobile.models.requests.ForgotPasswordRequestValues;
import com.ipnx.ipnxmobile.models.requests.Request;
import com.ipnx.ipnxmobile.models.responses.forgotpassword.ForgotPasswordResponse;
import com.ipnx.ipnxmobile.retrofit.MyApiEndpointInterface;
import com.ipnx.ipnxmobile.retrofit.RetrofitUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ipnx.ipnxmobile.utils.ApplicationUtils.ACTION_FORGOT_PASSWORD;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.DEVICE_ID;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_USERNAME;

public class ForgotPasswordActivity extends AppCompatActivity {
    EditText username;
    TextView info;
    Button button;
    LinearLayout progressIndicator;


    MyApiEndpointInterface myApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        info = findViewById(R.id.forgot_password_info);
        username = findViewById(R.id.forgot_password_username);
        button = findViewById(R.id.button);
        progressIndicator = findViewById(R.id.forgot_password_progress);
    }

    public void onResetButtonClicked(View view){
        final String userId = username.getText().toString();
        if (userId.isEmpty()) return;
        if (!networkActive()){
            Toast.makeText(this, "Network is unavailable", Toast.LENGTH_SHORT).show();
            return;
        }

        info.setVisibility(View.INVISIBLE);
        username.setVisibility(View.INVISIBLE);
        button.setVisibility(View.INVISIBLE);
        progressIndicator.setVisibility(View.VISIBLE);

        Request forgotPasswordRequest = new Request();
        ForgotPasswordRequestValues requestValues = new ForgotPasswordRequestValues();
        requestValues.setCUsername(userId);
        forgotPasswordRequest.setCustomValues(requestValues);
        forgotPasswordRequest.setAction(ACTION_FORGOT_PASSWORD);

        DEVICE_ID = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        forgotPasswordRequest.setDid(DEVICE_ID);

        myApi= RetrofitUtils.getService();
        Call<ForgotPasswordResponse> call = myApi.requestPasswordToken(forgotPasswordRequest);

        call.enqueue(new Callback<ForgotPasswordResponse>() {
            @Override
            public void onResponse(Call<ForgotPasswordResponse> call, Response<ForgotPasswordResponse> response) {
                ForgotPasswordResponse returnedResponse = response.body();
                if (returnedResponse == null){
                    Toast.makeText(ForgotPasswordActivity.this, "Network error, please try again", Toast.LENGTH_SHORT).show();
                    info.setVisibility(View.VISIBLE);
                    username.setVisibility(View.VISIBLE);
                    button.setVisibility(View.VISIBLE);
                    progressIndicator.setVisibility(View.INVISIBLE);
                    return;
                }

                if (returnedResponse.getResponseCode().equals("0")) {
                    Intent i = new Intent(ForgotPasswordActivity.this, ForgotPasswordConfirmActivity.class);
                    i.putExtra(EXTRA_KEY_USERNAME, userId);
                    startActivity(i);
                    closeActivity();
                }else {
                    Toast.makeText(ForgotPasswordActivity.this, "Wrong user ID", Toast.LENGTH_SHORT).show();
                    info.setVisibility(View.VISIBLE);
                    username.setVisibility(View.VISIBLE);
                    button.setVisibility(View.VISIBLE);
                    progressIndicator.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ForgotPasswordResponse> call, Throwable t) {
                Toast.makeText(ForgotPasswordActivity.this, "Network error, please try again", Toast.LENGTH_SHORT).show();
                info.setVisibility(View.VISIBLE);
                username.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
                progressIndicator.setVisibility(View.INVISIBLE);
            }
        });

    }

    private void closeActivity() {
        finish();
    }

    // Checks for network availability
    public boolean networkActive() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
