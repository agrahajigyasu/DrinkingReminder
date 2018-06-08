package com.example.agrahajigyasu.drinkingreminder1;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

public class AfterReboot extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            AlarmManager manager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
            Intent myIntent = new Intent(context,AlarmNotificationSender.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,myIntent,0);
            manager.setRepeating(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime()+AlarmManager.INTERVAL_HOUR,AlarmManager.INTERVAL_HOUR,pendingIntent);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.MILLISECOND, 0);
            PendingIntent pendingIntent1 = PendingIntent.getBroadcast(context.getApplicationContext(),0,new Intent(context.getApplicationContext(),Resetter.class),0);
            AlarmManager am = (AlarmManager) context.getSystemService(ALARM_SERVICE);
            am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent1);
        }
    }
}
