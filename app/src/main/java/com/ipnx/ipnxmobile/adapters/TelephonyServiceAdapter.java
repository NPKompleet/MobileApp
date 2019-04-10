package com.ipnx.ipnxmobile.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ipnx.ipnxmobile.ManageServiceActivity;
import com.ipnx.ipnxmobile.R;
import com.ipnx.ipnxmobile.TopUpActivity;
import com.ipnx.ipnxmobile.models.responses.InternetService;
import com.ipnx.ipnxmobile.models.responses.TelephonyService;

import java.util.List;

public class TelephonyServiceAdapter extends ArrayAdapter<TelephonyService> {
    private Context context;
    private List<TelephonyService> serviceList;

    public TelephonyServiceAdapter(@NonNull Context context, List<TelephonyService> list) {
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
            rowView = inflater.inflate(R.layout.item_voice_service_list, null, true);
        }

        TextView packageName = rowView.findViewById(R.id.voice_list_type);
        TextView packageStatus = rowView.findViewById(R.id.voice_list_status);
        View line = (View) rowView.findViewById(R.id.voice_divider);
        line.setVisibility(position == serviceList.size() - 1 ? View.INVISIBLE : View.VISIBLE);

        final TelephonyService service = serviceList.get(position);
        packageName.setText(service.getPackageName());
        packageStatus.setText(service.getStatus().equals("")?"Unknown" : service.getStatus());

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startVoiceServiceActivity(service);
            }
        });

        return rowView;
    }

    private void startVoiceServiceActivity(TelephonyService service){
        Intent i = new Intent(context, TopUpActivity.class);
        context.startActivity(i);
    }
}
