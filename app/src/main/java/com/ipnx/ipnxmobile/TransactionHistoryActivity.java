package com.ipnx.ipnxmobile;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ipnx.ipnxmobile.adapters.TransactionHistoryAdapter;
import com.ipnx.ipnxmobile.models.requests.LoginRequestValues;
import com.ipnx.ipnxmobile.models.requests.Request;
import com.ipnx.ipnxmobile.models.requests.TransactionRequestValues;
import com.ipnx.ipnxmobile.models.responses.transactionhistory.TransactionPayment;
import com.ipnx.ipnxmobile.models.responses.transactionhistory.TransactionResponse;
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

import static com.ipnx.ipnxmobile.utils.ApplicationUtils.ACTION_TRANSACTION_HISTORY;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.DEVICE_ID;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_LOGIN;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.formatDate;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.networkActive;

public class TransactionHistoryActivity extends BaseActivity {

    DatePickerDialog datePicker;
    Calendar calendar;
    Date date;
    int year;
    int month;
    int day;

    @BindView(R.id.transaction_date_from)
    TextView dateFrom;

    @BindView(R.id.transaction_date_to)
    TextView dateTo;

    @BindView(R.id.rv_transaction)
    RecyclerView recyclerView;

    @BindView(R.id.transaction_progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.transaction_status_text)
    TextView statusText;

    LoginRequestValues loginValues;
    MyApiEndpointInterface myApi;
    TransactionHistoryAdapter adapter;
    List<TransactionPayment> paymentList = new ArrayList<>();

    Date startDate, endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);
        ButterKnife.bind(this);

        loginValues = getIntent().getParcelableExtra(EXTRA_KEY_LOGIN);

        DateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        date= new Date();
        String dateString = dateFormat.format(date);
        dateFrom.setText(dateString);
        dateTo.setText(dateString);

        adapter= new TransactionHistoryAdapter(paymentList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        getTransactionHistory();
    }

    private void getTransactionHistory() {
        if (!networkActive(this)){
            Toast.makeText(this, "Please connect to a network", Toast.LENGTH_SHORT).show();
            return;
        }

        Request transactionRequest = new Request();
        final TransactionRequestValues transactionRequestValues = new TransactionRequestValues();
        transactionRequestValues.setCUsername(loginValues.getCUsername());
        transactionRequestValues.setCPassword(loginValues.getCPassword());

        transactionRequest.setAction(ACTION_TRANSACTION_HISTORY);
        transactionRequest.setCustomValues(transactionRequestValues);
        transactionRequest.setDid(DEVICE_ID);

        myApi= RetrofitUtils.getService();
        Call<TransactionResponse> call = myApi.fetchTransactionHistory(transactionRequest);
        call.enqueue(new Callback<TransactionResponse>() {
            @Override
            public void onResponse(Call<TransactionResponse> call, Response<TransactionResponse> response) {
                Toast.makeText(TransactionHistoryActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                TransactionResponse transactionResponse = response.body();
                if (transactionResponse != null) {
                    paymentList = transactionResponse.getCustomValues().getPayments();
                }

                adapter.setData(paymentList);
                progressBar.setVisibility(View.INVISIBLE);
                if (paymentList.isEmpty()){
                    statusText.setText("There are no records to show");
                }else {
                    statusText.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<TransactionResponse> call, Throwable t) {
                Toast.makeText(TransactionHistoryActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void onDateClicked(View view){
        switch (view.getId()){
            case R.id.transaction_date_from:
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);


                datePicker = new DatePickerDialog(TransactionHistoryActivity.this,
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

            case R.id.transaction_date_to:
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);


                datePicker = new DatePickerDialog(TransactionHistoryActivity.this,
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

    private boolean anyDateFieldIsEmpty(){
        return  (TextUtils.isEmpty(dateFrom.getText().toString()) || TextUtils.isEmpty(dateTo.getText().toString()));
    }

    public void onSubmitClicked(View view){
        if (anyDateFieldIsEmpty()){
            Toast.makeText(this, "Date fields cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }
        String startDateString = dateFrom.getText().toString();
        String endDateString = dateTo.getText().toString();
        startDate = formatDate(startDateString, "yyyy-MM-dd");
        endDate = formatDate(endDateString, "yyyy-MM-dd");

        System.out.println(startDate);
        System.out.println(endDate);

        List<TransactionPayment> sortedPaymentList = new ArrayList<>();

        for(TransactionPayment payment : paymentList){
            Date paymentDate = formatDate(payment.getDate(), "MMMM dd, yyyy");
            if ((paymentDate.after(startDate) || paymentDate.equals(startDate)) &&
                    (paymentDate.before(endDate) || paymentDate.equals(endDate))){
                sortedPaymentList.add(payment);
            }
        }

        adapter.setData(sortedPaymentList);
    }


    public void onBackClicked(View view){
        finish();
    }

}
