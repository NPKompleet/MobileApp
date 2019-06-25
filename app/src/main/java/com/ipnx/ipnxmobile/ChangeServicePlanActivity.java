package com.ipnx.ipnxmobile;

import android.location.Location;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ipnx.ipnxmobile.adapters.ServicePlanAdapter;
import com.ipnx.ipnxmobile.models.requests.ChangePlanRequestValues;
import com.ipnx.ipnxmobile.models.requests.FetchPlansRequestValues;
import com.ipnx.ipnxmobile.models.requests.Request;
import com.ipnx.ipnxmobile.models.responses.Response;
import com.ipnx.ipnxmobile.models.responses.login.InternetService;
import com.ipnx.ipnxmobile.models.responses.serviceplan.PlanPackage;
import com.ipnx.ipnxmobile.models.responses.serviceplan.ServicePlanResponse;
import com.ipnx.ipnxmobile.retrofit.MyApiEndpointInterface;
import com.ipnx.ipnxmobile.retrofit.RetrofitUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;

import static com.ipnx.ipnxmobile.utils.ApplicationUtils.ACTION_CHANGE_SERVICE_PLAN;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.ACTION_FETCH_SERVICE_PLANS;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.DEVICE_ID;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_INTERNET_SERVICE;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.userProfile;

public class ChangeServicePlanActivity extends BaseActivity implements
    ServicePlanAdapter.ServicePlanClickedListener{
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

    @BindView(R.id.servicePlanListView)
    ListView servicePlanListView;

    @BindView(R.id.servicePlan_listHolder)
    RelativeLayout listHolder;

    InternetService service;

    AlertDialog alertDialog;
    LinearLayout dialogProgress;

    MyApiEndpointInterface myApi;

    float balanceCalculated;
    String whenToChangePlan = "";
    boolean shouldAddFunds;
    String newPlanPckgNumber = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_service_plan);

        ButterKnife.bind(this);

        service = getIntent().getParcelableExtra(EXTRA_KEY_INTERNET_SERVICE);
        pageSubtitle.setText(service.getUsername());
        servicePlan.setText(service.getPackageName().split("  ")[0]);
        balanceCalculated = Float.parseFloat(service.getPackageLevelUnappliedCredits()) +
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
        dialogProgress = dialogView.findViewById(R.id.serviceDialog_progress);

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ChangeServicePlanActivity.this, "yes", Toast.LENGTH_SHORT).show();
                whenToChangePlan = "now";
                dialogProgress.setVisibility(View.VISIBLE);
                fetchPlans();
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ChangeServicePlanActivity.this, "no", Toast.LENGTH_SHORT).show();
                whenToChangePlan = "later";
                dialogProgress.setVisibility(View.VISIBLE);
                fetchPlans();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);

        alertDialog = builder.create();
        alertDialog.show();
    }

    private void fetchPlans(){
        Location location = getLastBestLocation();

        Request fetchPlansRequest = new Request();
        final FetchPlansRequestValues fetchPlanValues = new FetchPlansRequestValues();
        fetchPlanValues.setCUsername(userProfile.getUserName());
        fetchPlanValues.setCPassword(userProfile.getPassword());
        fetchPlanValues.setCCustomerNumber(userProfile.getCustomerNumber());
        fetchPlanValues.setCPackageNumber(String.valueOf(service.getPkgnum()));

        fetchPlansRequest.setAction(ACTION_FETCH_SERVICE_PLANS);
        fetchPlansRequest.setCustomValues(fetchPlanValues);

        DEVICE_ID = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        fetchPlansRequest.setDid(DEVICE_ID);

        if (location != null){
            fetchPlansRequest.setLat("" + location.getLatitude());
            fetchPlansRequest.setLon("" + location.getLongitude());
            fetchPlansRequest.setLt(location.getProvider());
            Toast.makeText(this, location.toString(), Toast.LENGTH_SHORT).show();
        }

        myApi= RetrofitUtils.getService();
        Call<ServicePlanResponse> call = myApi.getServicePlans(fetchPlansRequest);
        call.enqueue(new Callback<ServicePlanResponse>() {
            @Override
            public void onResponse(Call<ServicePlanResponse> call, retrofit2.Response<ServicePlanResponse> response) {
                ServicePlanResponse returnedResponse = response.body();
                if (returnedResponse == null){
                    Toast.makeText(ChangeServicePlanActivity.this, "Something went wrong. Try again", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!returnedResponse.getResponseCode().equals("0")){
                    Toast.makeText(ChangeServicePlanActivity.this, returnedResponse.getResponseMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }

                ServicePlanAdapter servicePlanAdapter
                        = new ServicePlanAdapter(ChangeServicePlanActivity.this,
                        ChangeServicePlanActivity.this, returnedResponse.getCustomValues().getPackages());
                servicePlanListView.setAdapter(servicePlanAdapter);
                listHolder.setVisibility(View.VISIBLE);
                dialogProgress.setVisibility(View.GONE);
                alertDialog.cancel();

            }

            @Override
            public void onFailure(Call<ServicePlanResponse> call, Throwable t) {
                Toast.makeText(ChangeServicePlanActivity.this, "Network failure. Try again", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onBackClicked(View view){
        finish();
    }

    @Override
    public void onServicePlanClicked(PlanPackage plan) {
        listHolder.setVisibility(View.GONE);
        newPlanCost.setText("₦" + plan.getPrice());
        float currentBill= Float.parseFloat(plan.getPrice());
        shouldAddFunds = balanceCalculated < currentBill;
        float amountPayable = shouldAddFunds ? currentBill - balanceCalculated : 0.00f;
        amountToPay.setText("₦" + amountPayable);
        newPlanPckgNumber = String.valueOf(plan.getPackageId());
    }

    public void onContinueClicked(View view){
        if (whenToChangePlan.equals("")){
            Toast.makeText(this, "Please select a new plan", Toast.LENGTH_SHORT).show();
            return;
        }
        changeServicePlan();
    }

    public void changeServicePlan(){
        Location location = getLastBestLocation();

        Request changeServicePlanRequest = new Request();
        final ChangePlanRequestValues changePlanValues = new ChangePlanRequestValues();
        changePlanValues.setCUsername(userProfile.getUserName());
        changePlanValues.setCPassword(userProfile.getPassword());
        changePlanValues.setCPackageNumber(newPlanPckgNumber);
        changePlanValues.setCOldPackageNumber(String.valueOf(service.getPkgnum()));
        changePlanValues.setCSettings("1");
        changePlanValues.setCChangeOption(whenToChangePlan);
        changePlanValues.setCAvailableBalance(String.valueOf(balanceCalculated));

        changeServicePlanRequest.setAction(ACTION_CHANGE_SERVICE_PLAN);
        changeServicePlanRequest.setCustomValues(changePlanValues);

        DEVICE_ID = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        changeServicePlanRequest.setDid(DEVICE_ID);

        if (location != null){
            changeServicePlanRequest.setLat("" + location.getLatitude());
            changeServicePlanRequest.setLon("" + location.getLongitude());
            changeServicePlanRequest.setLt(location.getProvider());
        }

        myApi= RetrofitUtils.getService();
        Call<Response> call = myApi.changePlan(changeServicePlanRequest);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });

    }

    public void onCancelClicked(View view){
        whenToChangePlan = "";
        newPlanCost.setText("₦0.00");
        amountToPay.setText("₦0.00");

    }
}
