package com.example.splashscreen;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Admin on 24-12-2019.
 */

public class Preferences {
    public static void saveStringPreferences(Context context, String key, String value) {
        SharedPreferences mPref = context.getSharedPreferences("MQTT", Context.MODE_PRIVATE);
        mPref.edit().putString(key, value).commit();
    }
    public static String getStringPreferences(Context context, String key) {
        SharedPreferences mPref = context.getSharedPreferences("MQTT", Context.MODE_PRIVATE);
        return mPref.getString(key, null);//sharedPreferences.getString(key, null);
    }
}
