package com.ipnx.ipnxmobile;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ipnx.ipnxmobile.adapters.DataHistoryAdapter;
import com.ipnx.ipnxmobile.customviews.DataChartView;
import com.ipnx.ipnxmobile.models.requests.DataHistoryRequestValues;
import com.ipnx.ipnxmobile.models.requests.Request;
import com.ipnx.ipnxmobile.models.requests.ViewDataRequestValues;
import com.ipnx.ipnxmobile.models.responses.datausage.DataHistoryResponse;
import com.ipnx.ipnxmobile.models.responses.datausage.DataUsageResponse;
import com.ipnx.ipnxmobile.retrofit.MyApiEndpointInterface;
import com.ipnx.ipnxmobile.retrofit.RetrofitUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ipnx.ipnxmobile.utils.ApplicationUtils.ACTION_DATA_HISTORY;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.ACTION_DATA_USAGE;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.DEVICE_ID;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_EXPIRY_DATE;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_ONT_SERIAL;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_PACKAGE_CLASS_COMMENT;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_SERVICE_PLAN;

public class DataUsageActivity extends BaseActivity {


    DatePickerDialog datePicker;
    Calendar calendar;
    Date date;
    int year;
    int month;
    int day;

    @BindView(R.id.data_date_from)
    TextView dateFrom;

    @BindView(R.id.data_date_to)
    TextView dateTo;

    @BindView(R.id.data_chart)
    DataChartView dataChartView;

    @BindView(R.id.rv_data_history)
    RecyclerView recyclerView;

    @BindView(R.id.data_progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.data_status_text)
    TextView statusText;

    @BindView(R.id.data_exp_date)
    TextView expiryDate;

    @BindView(R.id.data_data_allowance)
    TextView allowance;

    @BindView(R.id.data_rollOver)
    TextView rollOver;

    @BindView(R.id.data_usedData)
    TextView usedData;

    @BindView(R.id.data_unusedData)
    TextView unusedData;

    @BindView(R.id.data_totalData)
    TextView totalData;

    @BindView(R.id.data_peak_text)
    TextView peakText;

    @BindView(R.id.data_offPeak_text)
    TextView offPeakText;

    @BindView(R.id.data_indicator_peak)
    View peakIndicator;

    @BindView(R.id.data_indicator_offPeak)
    View offPeakIndicator;

    @BindView(R.id.page_subtitle)
    TextView pageSubtitle;

    String ontSerial, packageComment;
    MyApiEndpointInterface myApi;
    DataUsageResponse usageResponse;
    boolean dataLoaded = false;

    DataHistoryAdapter adapter;
    List<List<String>> dataHistoryList = new ArrayList<>();

    long dataRollOver, dataAllowance, dataUsed, dataTotal;
    final long oneGigaByte = 1073741824;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_usage);
        ButterKnife.bind(this);

        ontSerial = getIntent().getStringExtra(EXTRA_KEY_ONT_SERIAL);
        packageComment = getIntent().getStringExtra(EXTRA_KEY_PACKAGE_CLASS_COMMENT);
        String plan = getIntent().getStringExtra(EXTRA_KEY_SERVICE_PLAN);
        String expDate = getIntent().getStringExtra(EXTRA_KEY_EXPIRY_DATE);

        pageSubtitle.setText("Service Plan: " + plan.split("  ")[0]);
        expiryDate.setText(expDate);

        DateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        date= new Date();
        String dateString = dateFormat.format(date);
        dateFrom.setText(dateString);
        dateTo.setText(dateString);

        getDataInfo();

        adapter= new DataHistoryAdapter(dataHistoryList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        getDataHistory();
    }

    private void getDataInfo() {
        Request dataRequest = new Request();
        ViewDataRequestValues requestValues = new ViewDataRequestValues();
        requestValues.setCOntSerial(ontSerial);
        requestValues.setCPackageName(packageComment);
        requestValues.setCRegime("peak");

        dataRequest.setAction(ACTION_DATA_USAGE);
        dataRequest.setCustomValues(requestValues);
        dataRequest.setDid(DEVICE_ID);

        myApi= RetrofitUtils.getService();
        Call<DataUsageResponse> call = myApi.fetchDataInfo(dataRequest);
        call.enqueue(new Callback<DataUsageResponse>() {
            @Override
            public void onResponse(Call<DataUsageResponse> call, Response<DataUsageResponse> response) {
                Toast.makeText(DataUsageActivity.this, "successful", Toast.LENGTH_SHORT).show();
                usageResponse = response.body();
                if (usageResponse == null){
                    Toast.makeText(DataUsageActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (usageResponse.getResponseCode().equals("0")){
                    dataLoaded = true;
                    updatePeakData();
                    return;
                }
                Toast.makeText(DataUsageActivity.this, usageResponse.getResponseMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<DataUsageResponse> call, Throwable t) {
                Toast.makeText(DataUsageActivity.this, "Network Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updatePeakData(){
        dataRollOver = Long.parseLong(usageResponse.getCustomValues().getRollover().getDataRolledover());
        dataAllowance = Long.parseLong(usageResponse.getCustomValues().getDataAllowance().getPeak());
        dataUsed = usageResponse.getCustomValues().getCycleUsage().getPeak().getCycleMbUsed();
        dataTotal = dataRollOver + dataAllowance;
        rollOver.setText(dataRollOver/oneGigaByte + "GB");
        allowance.setText(dataAllowance/oneGigaByte+ "GB");
        usedData.setText("Used Data: " + dataUsed/oneGigaByte + "GB");
        unusedData.setText("Unused Data: " + (dataTotal-dataUsed)/oneGigaByte  + "GB");
        totalData.setText(dataTotal/oneGigaByte  + "GB");
        dataChartView.moveTo(dataTotal, dataUsed);
    }

    private void updateOffPeakData(){
        dataRollOver = 0;
        dataAllowance = Long.parseLong(usageResponse.getCustomValues().getDataAllowance().getOffPeak());
        dataUsed = usageResponse.getCustomValues().getCycleUsage().getOffPeak().getCycleMbUsed();
        dataTotal = dataRollOver + dataAllowance;
        rollOver.setText(dataRollOver/oneGigaByte + "GB");
        allowance.setText(dataAllowance/oneGigaByte+ "GB");
        usedData.setText("Used Data: " + dataUsed/oneGigaByte + "GB");
        unusedData.setText("Unused Data: " + (dataTotal-dataUsed)/oneGigaByte  + "GB");
        totalData.setText(dataTotal/oneGigaByte  + "GB");
        dataChartView.moveTo(dataTotal, dataUsed);
    }

    private void getDataHistory() {
        Request dataHistoryRequest = new Request();
        DataHistoryRequestValues requestValues = new DataHistoryRequestValues();
        requestValues.setCOntSerial(ontSerial);

        dataHistoryRequest.setAction(ACTION_DATA_HISTORY);
        dataHistoryRequest.setCustomValues(requestValues);
        dataHistoryRequest.setDid(DEVICE_ID);

        myApi= RetrofitUtils.getService();
        Call<DataHistoryResponse> call = myApi.fetchDataHistory(dataHistoryRequest);
        call.enqueue(new Callback<DataHistoryResponse>() {
            @Override
            public void onResponse(Call<DataHistoryResponse> call, Response<DataHistoryResponse> response) {
                DataHistoryResponse historyResponse = response.body();
                if (historyResponse != null){
                    dataHistoryList = historyResponse.getCustomValues().getHistory();
                }
                adapter.setData(dataHistoryList);
                progressBar.setVisibility(View.INVISIBLE);
                if (dataHistoryList.isEmpty()){
                    statusText.setText("There are no records to show");
                }else {
                    statusText.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<DataHistoryResponse> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void onDateClicked(View view){
        switch (view.getId()){
            case R.id.data_date_from:
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);


                datePicker = new DatePickerDialog(DataUsageActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedYear, int selectedMonth, int selectedDay) {
                        selectedMonth = selectedMonth + 1;
                        dateFrom.setText(selectedYear + "-" + selectedMonth + "-" + selectedDay);
                    }
                }, year, month, day);
                datePicker.setTitle("Select Start Date");
                datePicker.show();
                break;

            case R.id.data_date_to:
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);


                datePicker = new DatePickerDialog(DataUsageActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedYear, int selectedMonth, int selectedDay) {
                        selectedMonth = selectedMonth + 1;
                        dateTo.setText(selectedYear + "-" + selectedMonth + "-" + selectedDay);
                    }
                }, year, month, day);
                datePicker.setTitle("Select End Date");
                datePicker.show();
                break;
        }
    }

    public void oRegimeSelected(View view){
        if (!dataLoaded){
            Toast.makeText(this, "Loading data... Please wait", Toast.LENGTH_SHORT).show();
            return;
        }
        switch (view.getId()){
            case R.id.data_peak_text:
                peakText.setTextColor(getResources().getColor(R.color.red_button));
                peakText.setTypeface(ResourcesCompat.getFont(this, R.font.heebo_medium));
                offPeakText.setTextColor(getResources().getColor(R.color.data_grey));
                offPeakText.setTypeface(ResourcesCompat.getFont(this, R.font.heebo));
                peakIndicator.setVisibility(View.VISIBLE);
                offPeakIndicator.setVisibility(View.INVISIBLE);
                updatePeakData();
                break;

            case R.id.data_offPeak_text:
                peakText.setTextColor(getResources().getColor(R.color.data_grey));
                peakText.setTypeface(ResourcesCompat.getFont(this, R.font.heebo));
                offPeakText.setTextColor(getResources().getColor(R.color.red_button));
                offPeakText.setTypeface(ResourcesCompat.getFont(this, R.font.heebo_medium));
                peakIndicator.setVisibility(View.INVISIBLE);
                offPeakIndicator.setVisibility(View.VISIBLE);
//                updateOffPeakData();
                break;
        }
    }

    public void onSubmitClicked(View view){
    }

    public void onBackClicked(View view){
        finish();
    }

}
