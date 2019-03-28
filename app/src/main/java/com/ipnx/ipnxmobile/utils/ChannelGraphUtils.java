package com.ipnx.ipnxmobile.utils;

import android.graphics.Color;

import com.jjoe64.graphview.series.DataPoint;

import java.util.HashMap;
import java.util.Map;

public class ChannelGraphUtils {
    public static Map<Integer, Integer> channelMap = getChannelMap();
    public static int[] channelColors =  {
            Color.parseColor("#FF0000"),
            Color.parseColor("#FF7F00"),
            Color.parseColor("#EEEE00"),
            Color.parseColor("#00CC00"),
            Color.parseColor("#0000FF"),
            Color.parseColor("#4B0082"),
            Color.parseColor("#9400D3"),
    };

    @SuppressWarnings("UseSparseArrays")
    private static Map<Integer, Integer> getChannelMap() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(2412, 1);
        map.put(2417, 2);
        map.put(2422, 3);
        map.put(2427, 4);
        map.put(2432, 5);
        map.put(2437, 6);
        map.put(2442, 7);
        map.put(2447, 8);
        map.put(2452, 9);
        map.put(2457, 10);
        map.put(2462, 11);
        map.put(2467, 12);
        map.put(2472, 13);
        map.put(2484, 14);
        return map;
    }


    public static int addAlpha(int color){
        int alpha = Math.round(Color.alpha(color) * 0.2f);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }

    public static DataPoint[] createDataPoints(int channel, int level){
        return new DataPoint[] {
                new DataPoint(channel - 2, -100),
                new DataPoint(channel - 1, level),
                new DataPoint(channel, level),
                new DataPoint(channel + 1, level),
                new DataPoint(channel + 2, -100)
        };
    }
}
