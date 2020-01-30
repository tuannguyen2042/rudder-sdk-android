package com.rudderstack.android.sdk.core.util;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static android.provider.Settings.Secure.ANDROID_ID;
import static android.provider.Settings.System.getString;

public class Utils {

    // range constants
    public static final int MIN_CONFIG_REFRESH_INTERVAL = 1;
    public static final int MAX_CONFIG_REFRESH_INTERVAL = 24;
    public static final int MIN_SLEEP_TIMEOUT = 10;
    public static final int MIN_FLUSH_QUEUE_SIZE = 1;
    public static final int MAX_FLUSH_QUEUE_SIZE = 100;
    public static final int STATS_DELAY_COUNT = 10;
    public static final TimeUnit STATS_DELAY_TIME_UNIT = TimeUnit.SECONDS;
    public static final boolean METRICS_ENABLED = true;

    public static String getTimeZone() {
        TimeZone timeZone = TimeZone.getDefault();
        return timeZone.getID();
    }

    public static String getTimeStamp() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        return formatter.format(new Date());
    }

    public static String toDateString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        return formatter.format(date);
    }

    public static String getDeviceId(Application application) {
        if (Build.VERSION.SDK_INT >= 17) {
            String androidId = getString(application.getContentResolver(), ANDROID_ID);
            if (!TextUtils.isEmpty(androidId)
                    && !"9774d56d682e549c".equals(androidId)
                    && !"unknown".equals(androidId)
                    && !"000000000000000".equals(androidId)
            ) {
                return androidId;
            }
        }

        // If this still fails, generate random identifier that does not persist across installations
        return UUID.randomUUID().toString();
    }

    public static Map<String, Object> convertToMap(String json) {
        return new Gson().fromJson(json, new TypeToken<Map<String, Object>>() {
        }.getType());
    }

    public static List<Map<String, Object>> convertToList(String json) {
        return new Gson().fromJson(json, new TypeToken<List<Map<String, Object>>>() {
        }.getType());
    }

    public static String getWriteKeyFromStrings(Context context) {
        int id = context.getResources().getIdentifier(
                context.getPackageName(),
                "string",
                "rudder_write_key"
        );
        if (id != 0) {
            return context.getResources().getString(id);
        } else {
            return null;
        }
    }

    public static int computeMax(List<Integer> list) {
        if (list == null || list.isEmpty()) return -1;
        return list.get(list.size() - 1);
    }

    public static int computeMin(List<Integer> list) {
        if (list == null || list.isEmpty()) return -1;
        return list.get(0);
    }

    public static int computeMedian(List<Integer> list) {
        if (list == null || list.isEmpty()) return -1;
        int count = list.size();
        if (count % 2 != 0) {
            return list.get(count / 2);
        } else {
            return (list.get((count - 1) / 2) + list.get(count / 2)) / 2;
        }
    }

    public static float computeAverage(List<Integer> list) {
        if (list == null || list.isEmpty()) return -1;
        int sum = 0;
        for (Integer val : list) {
            sum += val;
        }
        return sum / (float) list.size();
    }

    public static double computeDeviation(List<Integer> list, float mean) {
        if (list == null || list.isEmpty()) return -1;
        float sum = 0;
        for (Integer val : list) {
            sum += Math.pow((val - mean), 2);
        }
        return Math.sqrt(sum / list.size());
    }
}