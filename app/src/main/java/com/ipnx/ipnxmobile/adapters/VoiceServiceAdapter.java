package com.ipnx.ipnxmobile.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ipnx.ipnxmobile.ManageVoiceServiceActivity;
import com.ipnx.ipnxmobile.R;
import com.ipnx.ipnxmobile.models.requests.LoginRequestValues;
import com.ipnx.ipnxmobile.models.responses.login.TelephonyService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_LOGIN;
import static com.ipnx.ipnxmobile.utils.ApplicationUtils.EXTRA_KEY_VOICE_SERVICE;

public class VoiceServiceAdapter extends RecyclerView.Adapter<VoiceServiceAdapter.TelephonyServiceHolder>{

    private Context context;
    private List<TelephonyService> serviceList;
    private LoginRequestValues loginValues;
//    private ItemClickListener listener;

    public VoiceServiceAdapter(Context context, List<TelephonyService> serviceList,
                               LoginRequestValues loginValues) {
        this.context = context;
        this.serviceList = serviceList;
//        this.listener = listener;
        this.loginValues = loginValues;
    }

    public void setData(List<TelephonyService> serviceList){
        this.serviceList = serviceList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TelephonyServiceHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_voice_service_list, parent, false);

        return new TelephonyServiceHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TelephonyServiceHolder itemHolder, int position) {
        TelephonyService service = serviceList.get(position);
        itemHolder.packageName.setText(service.getPackageName().split("  ")[0]);
        itemHolder.phoneNumber.setText(service.getUsername());
        itemHolder.line.setVisibility(position == serviceList.size() - 1 ? View.INVISIBLE : View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return serviceList == null ? 0 : serviceList.size();
    }
    

    public class TelephonyServiceHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.voice_list_type)
        TextView packageName;
        @BindView(R.id.voice_list_number)
        TextView phoneNumber;
        @BindView(R.id.voice_divider)
        View line;

        public TelephonyServiceHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getLayoutPosition();
            TelephonyService service = serviceList.get(position);
            Intent i = new Intent(context, ManageVoiceServiceActivity.class);
            i.putExtra(EXTRA_KEY_VOICE_SERVICE, service);
            i.putExtra(EXTRA_KEY_LOGIN, loginValues);
            context.startActivity(i);
//            listener.messageClicked(packageName.getText().toString(), phoneNumber.getText().toString());
        }
    }

//    public interface ItemClickListener{
//        void messageClicked(String phoneNumber, String body);
//    }
}
