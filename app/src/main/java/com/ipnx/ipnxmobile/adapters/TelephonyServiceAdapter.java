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

import com.ipnx.ipnxmobile.ManageVoiceServiceActivity;
import com.ipnx.ipnxmobile.R;
import com.ipnx.ipnxmobile.TopUpActivity;
import com.ipnx.ipnxmobile.models.requests.LoginRequestValues;
import com.ipnx.ipnxmobile.models.responses.login.TelephonyService;

import java.util.List;

import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_LOGIN;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_VOICE_SERVICE;

public class TelephonyServiceAdapter extends ArrayAdapter<TelephonyService> {
    private Context context;
    private List<TelephonyService> serviceList;

    private LoginRequestValues loginValues;

    public TelephonyServiceAdapter(@NonNull Context context, List<TelephonyService> list,
                                   LoginRequestValues loginValues) {
        super(context, 0, list);
        this.context = context;
        this.serviceList = list;
        this.loginValues = loginValues;
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
        TextView phoneNumber = rowView.findViewById(R.id.voice_list_number);
        View line = (View) rowView.findViewById(R.id.voice_divider);
        line.setVisibility(position == serviceList.size() - 1 ? View.INVISIBLE : View.VISIBLE);

        final TelephonyService service = serviceList.get(position);
        packageName.setText(service.getPackageName().split("  ")[0]);
        phoneNumber.setText(service.getUsername());


        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startVoiceServiceActivity(service);
            }
        });

        return rowView;
    }

    private void startVoiceServiceActivity(TelephonyService service){
        Intent i = new Intent(context, ManageVoiceServiceActivity.class);
        i.putExtra(EXTRA_KEY_VOICE_SERVICE, service);
        i.putExtra(EXTRA_KEY_LOGIN, loginValues);
        context.startActivity(i);
    }
}
