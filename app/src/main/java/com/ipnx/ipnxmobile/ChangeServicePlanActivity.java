package com.ipnx.ipnxmobile;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ipnx.ipnxmobile.models.requests.LoginRequestValues;
import com.ipnx.ipnxmobile.models.responses.login.InternetService;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_INTERNET_SERVICE;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_LOGIN;

public class ChangeServicePlanActivity extends AppCompatActivity {
    @BindView(R.id.service_plan)
    TextView servicePlan;

    @BindView(R.id.balance)
    TextView balance;

    @BindView(R.id.activationDate)
    TextView activationDate;

    @BindView(R.id.expiryDate)
    TextView expiryDate;

    @BindView(R.id.service_location)
    TextView serviceAddress;

    @BindView(R.id.amountToPay)
    TextView amountToPay;

    @BindView(R.id.new_plan_cost)
    TextView newPlanCost;

    @BindView(R.id.page_subtitle)
    TextView pageSubtitle;

    InternetService service;

    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_service_plan);

        ButterKnife.bind(this);

        service = getIntent().getParcelableExtra(EXTRA_KEY_INTERNET_SERVICE);
        pageSubtitle.setText(service.getUsername());
        servicePlan.setText(service.getPackageName().split("  ")[0]);
        float balanceCalculated = Float.parseFloat(service.getPackageLevelUnappliedCredits()) +
                Float.parseFloat(service.getPackageLevelUnappliedPayments());
        balance.setText("₦" + balanceCalculated );
        activationDate.setText(service.getActivationDate());
        expiryDate.setText(service.getExpiryDate());
        serviceAddress.setText(service.getServiceLocation());
    }

    public void onSelectPlanClicked(View view){
        showCustomDialog();
    }

    private void showCustomDialog() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.service_plan_dialog, viewGroup, false);
        Button yesButton = dialogView.findViewById(R.id.dialog_yes);
        Button noButton = dialogView.findViewById(R.id.dialog_no);

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ChangeServicePlanActivity.this, "yes", Toast.LENGTH_SHORT).show();
                alertDialog.cancel();
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ChangeServicePlanActivity.this, "no", Toast.LENGTH_SHORT).show();
                alertDialog.cancel();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);

        alertDialog = builder.create();
        alertDialog.show();
    }

    public void onBackClicked(View view){
        finish();
    }

}
