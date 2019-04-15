package com.ipnx.ipnxmobile;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionHistoryActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);
        ButterKnife.bind(this);

        DateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        date= new Date();
        String dateString = dateFormat.format(date);
        dateFrom.setText(dateString);
        dateTo.setText(dateString);
    }

    public void onDateClicked(View view){
        switch (view.getId()){
            case R.id.transaction_date_from:
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);


                datePicker = new DatePickerDialog(TransactionHistoryActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedYear, int selectedMonth, int selectedDay) {
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


                datePicker = new DatePickerDialog(TransactionHistoryActivity.this, new DatePickerDialog.OnDateSetListener() {
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

    public void onSubmitClicked(View view){
    }

    public void onBackClicked(View view){
        finish();
    }
}
