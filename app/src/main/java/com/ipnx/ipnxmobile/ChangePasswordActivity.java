package com.ipnx.ipnxmobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ipnx.ipnxmobile.models.requests.ChangePasswordRequestValues;
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

public class ChangePasswordActivity extends AppCompatActivity {

    @BindView(R.id.change_password_message)
    TextView message;

    @BindView(R.id.change_password_old)
    EditText oldPassword;

    @BindView(R.id.change_password_password1)
    EditText newPassword;

    @BindView(R.id.change_password_password2)
    EditText confirmNewPassword;

    String userName;

    MyApiEndpointInterface myApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        ButterKnife.bind(this);

        userName = getIntent().getStringExtra(EXTRA_KEY_USERNAME);
    }

    public void onSubmitClicked(View view){
        if (anyFieldIsEmpty(new TextView[]{oldPassword, newPassword, confirmNewPassword})){
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

        Request changePasswordRequest = new Request();
        ChangePasswordRequestValues requestValues = new ChangePasswordRequestValues();
        requestValues.setCUsername(userName);
        requestValues.setCOldPassword(oldPassword.getText().toString());
        requestValues.setCNewPassword(newPassword.getText().toString());
        requestValues.setCPasswordConfirmation(confirmNewPassword.getText().toString());

        changePasswordRequest.setCustomValues(requestValues);

        myApi= RetrofitUtils.getService();

        Call<Response> call = myApi.changePassword(changePasswordRequest);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Response returnedResponse = response.body();
                if (returnedResponse == null) {
                    Toast.makeText(ChangePasswordActivity.this, "Network error, please try again", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (returnedResponse.getResponseCode().equals("0")) {
                    Toast.makeText(ChangePasswordActivity.this, returnedResponse.getResponseMessage(), Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(ChangePasswordActivity.this, "Error: "+ returnedResponse.getResponseMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(ChangePasswordActivity.this, "Network Failure, please try again", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onBackClicked(View view){
        finish();
    }

}
