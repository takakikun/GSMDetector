package com.bluedon.gsm.detector.data;

import android.content.Context;
import android.location.Location;
import android.os.Build;
import android.util.Log;

import com.bluedon.gsm.detector.utils.BSInfoCollector;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class GlobalInfo {
    private static final String TAG = GlobalInfo.class.getName();

    public List<BSInfo> cells;
    public final String gps;
    public final String date;
    public final String model;

    public GlobalInfo(Context context, Location location) {
        this.cells = getCells(context);
        this.gps = getGPS(location);
        this.date = getDate();
        this.model = getModel();
    }

    private List<BSInfo> getCells(Context context) {
        List<BSInfo> cells = new ArrayList<>();
        Context c = context.getApplicationContext();
        for (BSInfo info : BSInfoCollector.collect(c)) {
            Log.w(TAG, "cell info : " + info);
            cells.add(info);
        }
        return cells;
    }

    private String getGPS(Location location) {
        if (location == null) return null;
        return location.getLongitude() + " / " + location.getLatitude();
    }

    private String getDate() {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.US);
        return sdf.format(now);
    }

    private String getModel() {
        return Build.MANUFACTURER + " " + Build.MODEL;
    }
}
