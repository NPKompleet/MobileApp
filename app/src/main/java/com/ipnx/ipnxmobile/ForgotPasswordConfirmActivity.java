package com.ipnx.ipnxmobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ipnx.ipnxmobile.models.requests.ForgotPasswordConfirmRequestValues;
import com.ipnx.ipnxmobile.models.requests.Request;
import com.ipnx.ipnxmobile.models.responses.Response;
import com.ipnx.ipnxmobile.retrofit.MyApiEndpointInterface;
import com.ipnx.ipnxmobile.retrofit.RetrofitUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;

import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_USERNAME;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.anyFieldIsEmpty;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.networkActive;

public class ForgotPasswordConfirmActivity extends AppCompatActivity {
    @BindView(R.id.forgot_password_message)
    TextView message;

    @BindView(R.id.forgot_password_token)
    EditText token;

    @BindView(R.id.forgot_password_password1)
    EditText newPassword;

    @BindView(R.id.forgot_password_password2)
    EditText confirmNewPassword;

    String userName;

    MyApiEndpointInterface myApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_confirm);

        ButterKnife.bind(this);

        userName = getIntent().getStringExtra(EXTRA_KEY_USERNAME);
    }

    public void onSubmitClicked(View view){
        if (anyFieldIsEmpty(new TextView[]{token, newPassword, confirmNewPassword})){
            Toast.makeText(this, "All fields should be filled", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!networkActive(this)){
            Toast.makeText(this, "Network is unavailable", Toast.LENGTH_SHORT).show();
            return;
        }

        if (! newPassword.getText().toString().equals(confirmNewPassword.getText().toString())){
            Toast.makeText(this, "Password and confirmation are not the same", Toast.LENGTH_SHORT).show();
            return;
        }

        Request forgotPasswordRequest = new Request();
        ForgotPasswordConfirmRequestValues requestValues = new ForgotPasswordConfirmRequestValues();
        requestValues.setCUsername(userName);
        requestValues.setCAccessToken(token.getText().toString());
        requestValues.setCNewPassword(newPassword.getText().toString());
        requestValues.setCPasswordConfirmation(confirmNewPassword.getText().toString());

        forgotPasswordRequest.setCustomValues(requestValues);

        myApi= RetrofitUtils.getService();

        Call<Response> call = myApi.resetPassword(forgotPasswordRequest);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Response returnedResponse = response.body();
                if (returnedResponse == null) {
                    Toast.makeText(ForgotPasswordConfirmActivity.this, "Network error, please try again", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (returnedResponse.getResponseCode().equals("0")) {
                    Toast.makeText(ForgotPasswordConfirmActivity.this, returnedResponse.getResponseMessage(), Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(ForgotPasswordConfirmActivity.this, "Error: "+ returnedResponse.getResponseMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(ForgotPasswordConfirmActivity.this, "Network Failure, please try again", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onBackClicked(View view){
        finish();
    }


}
