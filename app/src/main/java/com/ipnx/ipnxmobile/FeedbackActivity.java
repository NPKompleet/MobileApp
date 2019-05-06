package com.ipnx.ipnxmobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ipnx.ipnxmobile.models.requests.FeedbackRequestValues;
import com.ipnx.ipnxmobile.models.requests.Request;
import com.ipnx.ipnxmobile.models.responses.Response;
import com.ipnx.ipnxmobile.retrofit.MyApiEndpointInterface;
import com.ipnx.ipnxmobile.retrofit.RetrofitUtils;

import retrofit2.Call;
import retrofit2.Callback;

import static com.ipnx.ipnxmobile.utils.ApplicationUtils.ACTION_FEEDBACK;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.networkActive;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.userProfile;

public class FeedbackActivity extends AppCompatActivity {
    EditText feedback;
    MyApiEndpointInterface myApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        feedback = findViewById(R.id.feedback);
    }

    public void onSubmitClicked(View view){
        if (!networkActive(this)){
            Toast.makeText(this, "Network is unavailable", Toast.LENGTH_SHORT).show();
            return;
        }
        if (feedback.getText().length() == 0){
            Toast.makeText(this, "Feedback field cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        Request feedbackRequest = new Request();
        FeedbackRequestValues feedbackRequestValues = new FeedbackRequestValues();
        feedbackRequestValues.setCCustomerId(""+ userProfile.getId());
        feedbackRequestValues.setCFirstName(userProfile.getFirstName());
        feedbackRequestValues.setCLastName(userProfile.getLastName());
        feedbackRequestValues.setCPhoneNumber(userProfile.getPhoneNumber());
        feedbackRequestValues.setCEmailAddress(userProfile.getEmailAddress().toString());
        feedbackRequestValues.setCFeedback(feedback.getText().toString());

        feedbackRequest.setCustomValues(feedbackRequestValues);
        feedbackRequest.setAction(ACTION_FEEDBACK);

        postFeedback(feedbackRequest);
    }

    private void postFeedback(Request request) {
        myApi= RetrofitUtils.getService();
        Call<Response> call = myApi.sendFeedback(request);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Response feedbackResponse = response.body();
                if (feedbackResponse == null){
                    Toast.makeText(FeedbackActivity.this, "Network error. Please try again.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (feedbackResponse.getResponseCode().equals("0")){
                    Toast.makeText(FeedbackActivity.this, "Feedback sent", Toast.LENGTH_SHORT).show();
                    feedback.setText("");
                    return;
                }
                Toast.makeText(FeedbackActivity.this, feedbackResponse.getResponseMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(FeedbackActivity.this, "Network failure. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onBackClicked(View view){
        finish();
    }
}
