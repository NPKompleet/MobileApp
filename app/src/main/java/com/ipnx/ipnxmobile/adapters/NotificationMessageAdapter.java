package com.ipnx.ipnxmobile.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.ipnx.ipnxmobile.R;

import org.infobip.mobile.messaging.Message;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationMessageAdapter extends
        RecyclerView.Adapter<NotificationMessageAdapter.MessageHolder> implements Filterable {

    private Context context;
    private List<Message> messageList;
    private List<Message> messageListFull;
    private ItemClickListener listener;

    public NotificationMessageAdapter(List<Message> messageList, ItemClickListener listener) {
        this.messageList = messageList;
        messageListFull = new ArrayList<>(messageList);
        this.listener = listener;
    }

    public void setData(List<Message> messageList){
        this.messageList = messageList;
        messageListFull = new ArrayList<>(messageList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        context= parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.message_row, parent, false);

        return new MessageHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageHolder historyHolder, int position) {
        Message item = messageList.get(position);
        String date = String.valueOf(new Date(item.getReceivedTimestamp()));
        String[] split_date = date.split(" ");
        historyHolder.date.setText(split_date[0] + " \n" + split_date[1] + " " + split_date[2] + " \n" + split_date[5]);
        historyHolder.title.setText(item.getTitle());
        historyHolder.body.setText(item.getBody());
    }

    @Override
    public int getItemCount() {
        return messageList == null ? 0 : messageList.size();
    }

    @Override
    public Filter getFilter() {
        return messageFilter;
    }

    private Filter messageFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Message> filteredList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0){
                filteredList.addAll(messageListFull);
            }else{
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (Message message : messageListFull){
                    if (message.getTitle().toLowerCase().contains(filterPattern) ||
                            message.getBody().toLowerCase().contains(filterPattern)){
                        filteredList.add(message);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            messageList.clear();
            messageList.addAll((List<Message>)filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class MessageHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.message_date)
        TextView date;
        @BindView(R.id.message_title)
        TextView title;
        @BindView(R.id.tv_message_text)
        TextView body;

        public MessageHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
//            int position = getLayoutPosition();
            listener.messageClicked(title.getText().toString(), body.getText().toString());
        }
    }

    public interface ItemClickListener{
        void messageClicked(String title, String body);
    }
}
