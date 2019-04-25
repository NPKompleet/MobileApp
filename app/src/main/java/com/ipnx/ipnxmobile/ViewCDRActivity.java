package com.ipnx.ipnxmobile;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ipnx.ipnxmobile.utils.ApplicationUtils.formatDate;

public class ViewCDRActivity extends AppCompatActivity {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cdr);

        ButterKnife.bind(this);
    }

    private boolean anyDateFieldIsEmpty(){
        return  (TextUtils.isEmpty(dateFrom.getText().toString()) || TextUtils.isEmpty(dateTo.getText().toString()));
    }

    public void onSubmitClicked(View view) {
        if (anyDateFieldIsEmpty()) {
            Toast.makeText(this, "Date fields cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }
        String startDateString = dateFrom.getText().toString();
        String endDateString = dateTo.getText().toString();
        startDate = formatDate(startDateString, "yyyy-MM-dd");
        endDate = formatDate(endDateString, "yyyy-MM-dd");
    }

    public void onBackClicked(View view){
        finish();
    }

}
