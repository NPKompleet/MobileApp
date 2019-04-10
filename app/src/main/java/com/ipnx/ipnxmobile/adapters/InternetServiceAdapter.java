package com.ipnx.ipnxmobile.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ipnx.ipnxmobile.ManageServiceActivity;
import com.ipnx.ipnxmobile.R;
import com.ipnx.ipnxmobile.models.responses.InternetService;

import java.util.List;

public class InternetServiceAdapter extends ArrayAdapter<InternetService> {
    private Context context;
    private List<InternetService> serviceList;

    public InternetServiceAdapter(@NonNull Context context, List<InternetService> list) {
        super(context, 0, list);
        this.context = context;
        this.serviceList = list;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rowView = convertView;
        if(rowView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            rowView = inflater.inflate(R.layout.item_data_service_list, null, true);
        }

        TextView packageName = rowView.findViewById(R.id.data_list_package);
        TextView packageBalance = rowView.findViewById(R.id.data_list_balance);
        TextView packageExpDate = rowView.findViewById(R.id.data_list_exp_date);
        View line = (View) rowView.findViewById(R.id.data_list_line);
        line.setVisibility(position == serviceList.size() - 1 ? View.INVISIBLE : View.VISIBLE);

        final InternetService service = serviceList.get(position);
        packageName.setText(service.getPackageName());
        packageBalance.setText(service.getPackageBalance());
        packageExpDate.setText(service.getExpiryDate());

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startManageServiceActivity(service);
            }
        });

        return rowView;

    }

    private void startManageServiceActivity(InternetService service){
        Intent i = new Intent(context, ManageServiceActivity.class);
        context.startActivity(i);
    }
}
