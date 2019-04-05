package com.ipnx.ipnxmobile.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ApplicationUtils {
    public static final String VERSION = "1.0";
    public static final String APP_BUNDLE = "com.ipnx.ipnxmobile";
    public static final String APP_NAME = "ipnxmobile";
    public static final String NETWORK = "wifi";
    public static String DEVICE_ID = "";
    public static final String ACTION_LOGIN ="login";
    public static final String EXTRA_KEY_RESPONSE ="response";

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
}
