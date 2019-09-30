package com.ipnx.ipnxmobile;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ipnx.ipnxmobile.adapters.NotificationMessageAdapter;

import org.infobip.mobile.messaging.Event;
import org.infobip.mobile.messaging.Message;
import org.infobip.mobile.messaging.MobileMessaging;
import org.infobip.mobile.messaging.storage.MessageStore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PushNotificationActivity extends BaseActivity implements NotificationMessageAdapter.ItemClickListener{

    private MobileMessaging mobileMessaging;
    private MessageStore messageStore;
    private EditText searchView;
    private ImageButton deleteButton;
    private NotificationMessageAdapter adapter;
    private RecyclerView recyclerView;
    AlertDialog alertDialog;

    private BroadcastReceiver sdkBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            refresh();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_notification);

        adapter = new NotificationMessageAdapter(new ArrayList<Message>(), this);
        deleteButton = findViewById(R.id.delete_button);
        searchView = findViewById(R.id.search_view);
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Toast.makeText(PushNotificationActivity.this, editable.toString(), Toast.LENGTH_SHORT).show();
                adapter.getFilter().filter(editable);
            }
        });

        mobileMessaging = MobileMessaging.getInstance(this);
        messageStore = mobileMessaging.getMessageStore();

        recyclerView = findViewById(R.id.rv_messages);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        refresh();
        clearNotifications();

        LocalBroadcastManager
                .getInstance(this)
                .registerReceiver(sdkBroadcastReceiver, new IntentFilter() {{
                    addAction(Event.MESSAGE_RECEIVED.getKey());
                    addAction(Event.INSTALLATION_UPDATED.getKey());
                }});
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager
                .getInstance(this)
                .unregisterReceiver(sdkBroadcastReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
        clearNotifications();
    }


    private void clearNotifications() {
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.cancelAll();
        }
    }

    private void refresh() {
        if (messageStore.countAll(this) == 0) {
            Toast.makeText(this, "There are no notification messages saved", Toast.LENGTH_SHORT).show();
            return;
        }
        List<Message> messages = messageStore.findAll(this);
        Collections.reverse(messages);
        adapter.setData(messages);
        if (!messages.isEmpty()) deleteButton.setVisibility(View.VISIBLE);
    }

    public void deletePushMessages(View view){
        if (messageStore.countAll(this) == 0) {
            Toast.makeText(this, "There are no messages to delete", Toast.LENGTH_SHORT).show();
            return;
        }
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this)
                .setTitle("DELETE ALL NOTIFICATIONS")
                .setIcon(R.drawable.ic_info)
                .setMessage("Are you sure you want to delete all push messages?")
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                messageStore.deleteAll(PushNotificationActivity.this);
                            }
                        })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        alertDialogBuilder.show();
    }


    public void onBackClicked(View view){
        finish();
    }

    @Override
    public void messageClicked(String title, String body) {
        View v = getLayoutInflater().inflate(R.layout.message_detail_dialog, null);
        final TextView titleView = v.findViewById(R.id.push_message_title);
        final TextView bodyView = v.findViewById(R.id.push_message_body);
        titleView.setText(title);
        bodyView.setText(body);
        Button cancelButton = v.findViewById(R.id.push_dialog_close);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setView(v);
        alertDialog = builder.create();
        alertDialog.show();
    }
}
