package com.ipnx.ipnxmobile;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ipnx.ipnxmobile.adapters.CDRAdapter;
import com.ipnx.ipnxmobile.models.requests.CDRRequestValues;
import com.ipnx.ipnxmobile.models.requests.LoginRequestValues;
import com.ipnx.ipnxmobile.models.requests.Request;
import com.ipnx.ipnxmobile.models.responses.cdr.CallRecord;
import com.ipnx.ipnxmobile.models.responses.cdr.CallRecordResponse;
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

import static com.ipnx.ipnxmobile.utils.ApplicationUtils.ACTION_VIEW_CDR;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.DEVICE_ID;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_LOGIN;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_PHONE_NUMBER;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.anyFieldIsEmpty;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.formatDate;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.networkActive;

public class ViewCDRActivity extends BaseActivity {

    DatePickerDialog datePicker;
    Calendar calendar;
    Date date;
    int year;
    int month;
    int day;

    @BindView(R.id.cdr_date_from)
    TextView dateFrom;

    @BindView(R.id.cdr_date_to)
    TextView dateTo;

    @BindView(R.id.rv_cdr)
    RecyclerView recyclerView;

    @BindView(R.id.cdr_progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.cdr_status_text)
    TextView statusText;

    Date startDate, endDate;

    MyApiEndpointInterface myApi;
    CDRAdapter adapter;
    List<CallRecord> recordList = new ArrayList<>();

    LoginRequestValues loginValues;
    String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cdr);
        ButterKnife.bind(this);

        loginValues = getIntent().getParcelableExtra(EXTRA_KEY_LOGIN);
        phoneNumber = getIntent().getStringExtra(EXTRA_KEY_PHONE_NUMBER);

        DateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        date= new Date();
        String dateString = dateFormat.format(date);
        dateFrom.setText(dateString);
        dateTo.setText(dateString);

        adapter= new CDRAdapter(recordList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    public void onDateClicked(View view){
        switch (view.getId()){
            case R.id.cdr_date_from:
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);


                datePicker = new DatePickerDialog(ViewCDRActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(
                                    DatePicker datepicker, int selectedYear, int selectedMonth, int selectedDay) {
                                selectedMonth = selectedMonth + 1;
                                dateFrom.setText(selectedYear + "-" + selectedMonth + "-" + selectedDay);
                            }
                        }, year, month, day);
                datePicker.setTitle("Select Start Date");
                datePicker.show();
                break;

            case R.id.cdr_date_to:
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);


                datePicker = new DatePickerDialog(ViewCDRActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
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


    public void onSubmitClicked(View view) {
        if (anyFieldIsEmpty(new TextView[]{dateFrom, dateTo})) {
            Toast.makeText(this, "Date fields cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!networkActive(this)){
            Toast.makeText(this, "Please connect to a network", Toast.LENGTH_SHORT).show();
            return;
        }

        String startDateString = dateFrom.getText().toString();
        String endDateString = dateTo.getText().toString();
        startDate = formatDate(startDateString, "yyyy-MM-dd");
        endDate = formatDate(endDateString, "yyyy-MM-dd");

        if (endDate.before(startDate)){
            Toast.makeText(this, "End date cannot be earlier than start date", Toast.LENGTH_SHORT).show();
            dateTo.setText("");
            return;
        }

        Request viewCDRRequest = new Request();
        CDRRequestValues cdrRequestValues = new CDRRequestValues();
        cdrRequestValues.setCUsername(loginValues.getCUsername());
        cdrRequestValues.setCPassword(loginValues.getCPassword());
        cdrRequestValues.setCPhonenumber(phoneNumber);
        cdrRequestValues.setCStartdate(startDateString);
        cdrRequestValues.setCEnddate(endDateString);

        viewCDRRequest.setAction(ACTION_VIEW_CDR);
        viewCDRRequest.setCustomValues(cdrRequestValues);
        viewCDRRequest.setDid(DEVICE_ID);

        progressBar.setVisibility(View.VISIBLE);
        statusText.setText("...Fetching call detail record");
        recyclerView.setVisibility(View.INVISIBLE);
        getCallRecords(viewCDRRequest);

    }

    private void getCallRecords(Request request) {
        myApi= RetrofitUtils.getService();
        Call<CallRecordResponse> call = myApi.fetchCallDetailRecord(request);
        call.enqueue(new Callback<CallRecordResponse>() {
            @Override
            public void onResponse(Call<CallRecordResponse> call, Response<CallRecordResponse> response) {
                Toast.makeText(ViewCDRActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                CallRecordResponse viewCDRResponse = response.body();
                progressBar.setVisibility(View.INVISIBLE);
                if(viewCDRResponse == null){
                    statusText.setText("Network error. Please try again.");
                    return;
                }
                recordList = viewCDRResponse.getCustomValues().getRecords();
                if (recordList.isEmpty()){
                    statusText.setText("There are no records for those dates.");
                    return;
                }
                statusText.setVisibility(View.INVISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
                adapter.setData(recordList);
            }

            @Override
            public void onFailure(Call<CallRecordResponse> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                statusText.setText("Network failure. Please try again.");
            }
        });
    }

    public void onBackClicked(View view){
        finish();
    }

}
