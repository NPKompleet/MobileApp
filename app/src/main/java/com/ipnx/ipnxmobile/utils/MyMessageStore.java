package com.ipnx.ipnxmobile.utils;

import android.content.Context;

import org.infobip.mobile.messaging.Message;
import org.infobip.mobile.messaging.storage.MessageStore;

import java.util.ArrayList;
import java.util.List;

public class MyMessageStore implements MessageStore {

    private static List<Message> messages = new ArrayList<>();

    /**
     * Binds message list to the message store
     *
     * @param context current context
     * @return immutable list of all stored messages
     */
//    @Override
    public List<Message> bind(Context context) {
        return messages;
    }



    /**
     * Finds all stored messages
     *
     * @param context current context
     * @return all stored messages
     */
    @Override
    public List<Message> findAll(Context context) {
        return new ArrayList<>(messages);
    }

    /**
     * Counts all stored messages
     *
     * @param context current context
     * @return all stored messages count
     */
    @Override
    public long countAll(Context context) {
        return messages.size();
    }

//    @Override
//    public void save(Context context, Message... messages) {
//
//    }

    /**
     * Saves the messages in the store
     *
     * @param context current context
     */
    @Override
    public synchronized void save(Context context, Message... message) {
//        for (Message m : findAll(context)) {
//            if (m.getMessageId().equals(message.getMessageId())) {
//                messages.remove(m);
//            }
//        }
//        messages.add(message);
    }

    /**
     * Deletes all stored messages
     *
     * @param context current context
     */
    @Override
    public synchronized void deleteAll(Context context) {
        messages.clear();
    }
}
