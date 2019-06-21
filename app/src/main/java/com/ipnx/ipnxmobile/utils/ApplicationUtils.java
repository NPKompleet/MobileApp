package com.ipnx.ipnxmobile.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ipnx.ipnxmobile.models.Profile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class ApplicationUtils {
    public static final Profile userProfile = new Profile();
    public static final String VERSION = "1.0";
    public static final String APP_BUNDLE = "com.ipnx.ipnxmobile";
    public static final String APP_NAME = "ipnxmobile";
    public static final String NETWORK = "wifi";
    public static String DEVICE_ID = "";
    public static final String ACTION_LOGIN = "login";
    public static final String ACTION_TRANSACTION_HISTORY = "get transaction history";
    public static final String ACTION_FORGOT_PASSWORD = "Recover user password";
    public static final String ACTION_VIEW_CDR = "view call detail records";
    public static final String ACTION_DATA_USAGE = "View data usage records";
    public static final String ACTION_DATA_HISTORY = "get historical data";
    public static final String ACTION_FEEDBACK = "Get ipNXMobile customer feedback";
    public static final String ACTION_ADD_PAYMENT = "Add cash payment";
    public static final String ACTION_SUBSCRIPTION_SETTINGS = "fetch/change data usage settings";
    public static final String ACTION_WIFI_PASSWORD = "fetch/change WiFi settings";
    public static final String EXTRA_KEY_RESPONSE = "response";
    public static final String EXTRA_KEY_LOGIN = "loginValues";
    public static final String EXTRA_KEY_USERNAME = "userID";
    public static final String EXTRA_KEY_INTERNET_SERVICE = "internetService";
    public static final String EXTRA_KEY_VOICE_SERVICE = "voiceService";
    public static final String EXTRA_KEY_PHONE_NUMBER = "phoneumber";
    public static final String EXTRA_KEY_ONT_SERIAL = "ontSerial";
    public static final String EXTRA_KEY_PACKAGE_CLASS_COMMENT = "packageComment";
    public static final String EXTRA_KEY_SERVICE_PLAN = "servicePlan";
    public static final String EXTRA_KEY_EXPIRY_DATE = "expiryDate";
    public static final String APP_URL = "";
    public static final String FACEBOOK_URL = "https://www.facebook.com/ipnxnigeria/";
    public static final String TWITTER_URL = "https://twitter.com/ipNXTweet";
    public static final String INSTAGRAM_URL = "https://www.instagram.com/ipnxnigeria";
    public static final String WHATSSAPP_URL = "https://api.whatsapp.com/send?phone=2349096936884";

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

    public static boolean isLocationEnabled(Context context) {
        int locationMode = 0;
        String locationProviders;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);
            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                return false;
            }
            return locationMode != Settings.Secure.LOCATION_MODE_OFF;

        }else{
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders);
        }
    }

    public static String getRandomAlphabeticString(int stringLength) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < stringLength) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();
    }

    public static String encryptThisString(String input)
    {
        try {
            // getInstance() method is called with algorithm SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            // return the HashText
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
