package com.ipnx.ipnxmobile.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ipnx.ipnxmobile.R;
import com.ipnx.ipnxmobile.models.responses.serviceplan.PlanPackage;

import java.util.List;

public class ServicePlanAdapter extends ArrayAdapter<PlanPackage> {
    private Context context;
    ServicePlanClickedListener listener;
    private List<PlanPackage> planList;

    public ServicePlanAdapter(@NonNull Context context, ServicePlanClickedListener listener, List<PlanPackage> list) {
            super(context, 0, list);
            this.context = context;
            this.listener = listener;
            this.planList = list;
        }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rowView = convertView;
        if(rowView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            rowView = inflater.inflate(R.layout.item_service_plan_list, null, true);
        }

        TextView packageName = rowView.findViewById(R.id.plan_list_package);
        View line = rowView.findViewById(R.id.plan_list_line);
        TextView price = rowView.findViewById(R.id.plan_list_price);

        final PlanPackage plan = planList.get(position);
        packageName.setText(plan.getPackageName());
        price.setText(plan.getPrice());
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onServicePlanClicked(plan);
            }
        });

        return rowView;

    }

    public interface ServicePlanClickedListener{
        void onServicePlanClicked(PlanPackage plan);
    }
}
