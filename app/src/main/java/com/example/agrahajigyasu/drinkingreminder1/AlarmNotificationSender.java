package com.example.agrahajigyasu.drinkingreminder1;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.preference.PreferenceManager;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.content.Context.NOTIFICATION_SERVICE;

public class AlarmNotificationSender extends BroadcastReceiver
{
    private static final int uniqueID = 125698;
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean b = false;
        Log.d("Notification", "riya2");
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        int wakingHour = pref.getInt("WakingHour", 0);
        int wakingMin = pref.getInt("WakingMin", 0);
        int sleepingHour = pref.getInt("SleepingHour", 23);
        int sleepingMin = pref.getInt("SleepingMin", 59);
        Calendar rightNow = Calendar.getInstance();
        int currentHour = rightNow.get(Calendar.HOUR_OF_DAY);
        int currentMin = rightNow.get(Calendar.MINUTE);
        String currentTime = currentHour + ":" + currentMin;
        String wakingTime = wakingHour + ":" + wakingMin;
        String sleepingTime = sleepingHour + ":" + sleepingMin;
        try {
            Date time1 = new SimpleDateFormat("HH:mm").parse(wakingTime);
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(time1);

            Date time2 = new SimpleDateFormat("HH:mm").parse(sleepingTime);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(time2);
            calendar2.add(Calendar.DATE, 1);

            Date d = new SimpleDateFormat("HH:mm").parse(currentTime);
            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTime(d);
            calendar3.add(Calendar.DATE, 1);

            Date x = calendar3.getTime();
            if (x.before(calendar1.getTime()) && x.after(calendar2.getTime())) {
                b = false;
            }
            else {
                b = true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (b) {
            Log.d("Notification", "riya");
            NotificationCompat.Builder notification = new NotificationCompat.Builder(context, "notify001");
            Intent newIntent = new Intent(context, Home2Activity.class);
            newIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 100, newIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            notification.setAutoCancel(true);
            notification.setContentIntent(pendingIntent);
            notification.setTicker("It's time to drink water");
            notification.setContentTitle("Drinking Reminder");
            notification.setContentText("Feeling lethargic!!! Drinking Water may help");
            notification.setSmallIcon(R.drawable.ic_launcher);
            notification.setPriority(NotificationCompat.PRIORITY_HIGH);
            NotificationManager nm = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            {
                String channelId = "Your_channel_id";
                NotificationChannel channel = new NotificationChannel(
                        channelId,
                        "Channel human readable title",
                        NotificationManager.IMPORTANCE_HIGH);
                nm.createNotificationChannel(channel);
                notification.setChannelId(channelId);
            }
            nm.notify(uniqueID, notification.build());
            try {
                Uri snNotification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Ringtone r = RingtoneManager.getRingtone(context.getApplicationContext(), snNotification);
                r.play();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
