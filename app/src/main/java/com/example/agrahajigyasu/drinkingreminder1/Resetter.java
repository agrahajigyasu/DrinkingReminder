package com.example.agrahajigyasu.drinkingreminder1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class Resetter extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        pref.edit().putInt("WaterDrank",0).commit();
    }
}
