package com.ipnx.ipnxmobile.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ApplicationUtils {
    public static final String VERSION = "1.0";
    public static final String APP_BUNDLE = "com.ipnx.ipnxmobile";
    public static final String APP_NAME = "ipnxmobile";
    public static final String NETWORK = "wifi";
    public static String DEVICE_ID = "";
    public static final String ACTION_LOGIN = "login";
    public static final String ACTION_TRANSACTION_HISTORY = "get transaction history";
    public static final String ACTION_FORGOT_PASSWORD = "Recover user password";
    public static final String ACTION_VIEW_CDR = "view call detail records";
    public static final String EXTRA_KEY_RESPONSE = "response";
    public static final String EXTRA_KEY_LOGIN = "loginValues";
    public static final String EXTRA_KEY_USERNAME = "userID";
    public static final String EXTRA_KEY_INTERNET_SERVICE = "internetService";
    public static final String EXTRA_KEY_VOICE_SERVICE = "voiceService";
    public static final String EXTRA_KEY_PHONE_NUMBER = "phoneumber";

    public static Bitmap getBitmapFromView(View view) {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null)
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        else
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }

    public static void saveBitmapToCache(Bitmap bitmap, Context context){
        try {

            File cachePath = new File(context.getCacheDir(), "images");
            cachePath.mkdirs();// don't forget to make the directory
            FileOutputStream stream = new FileOutputStream(cachePath + "/image.png"); // overwrites this image every time
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            stream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Date formatDate(String dateString, String pattern){
        Date date = new Date();
        DateFormat format = new SimpleDateFormat(pattern, Locale.ENGLISH);
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    // Checks for network availability
    public static boolean networkActive(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }


    // Since EditTexts inherits from TextView
    public static boolean anyFieldIsEmpty(TextView[] views){
        boolean oneIsEmpty = false;
        for (TextView view : views){
            oneIsEmpty = TextUtils.isEmpty(view.getText().toString());
            if (oneIsEmpty) break;
        }
        return oneIsEmpty;
    }
}
