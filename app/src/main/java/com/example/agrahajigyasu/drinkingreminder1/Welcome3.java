package com.example.agrahajigyasu.drinkingreminder1;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Calendar;

public class Welcome3 extends AppCompatActivity {
    boolean welcome3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_3);
        final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        welcome3 = pref.getBoolean("welcome3",true);
        if(welcome3) {
            ConstraintLayout rl = (ConstraintLayout) findViewById(R.id.welcome3);
            rl.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.hello));
            final TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker2);
            Button button = (Button) findViewById(R.id.buttonNext);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int hour = timePicker.getCurrentHour();
                    int min = timePicker.getCurrentMinute();
                    pref.edit().putInt("SleepingHour", hour).commit();
                    pref.edit().putInt("SleepingMin", min).commit();
                    pref.edit().putBoolean("welcome3",false).commit();
                    startAlarm();
                    Intent newIntent = new Intent(Welcome3.this, Home2Activity.class);
                    startActivity(newIntent);
                    finish();
                }
            });
        }
        else {
            Intent newIntent = new Intent(Welcome3.this, Home2Activity.class);
            startActivity(newIntent);
            finish();
        }
    }
    private void startAlarm() {
        AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
        Intent myIntent = new Intent(getApplicationContext(),AlarmNotificationSender.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),0,myIntent,0);
        manager.setRepeating(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime()+AlarmManager.INTERVAL_HOUR,AlarmManager.INTERVAL_HOUR,pendingIntent);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        Intent myIntent1 = new Intent(getApplicationContext(),Resetter.class);
        PendingIntent pendingIntent1 = PendingIntent.getBroadcast(getApplicationContext(),0,myIntent1,0);
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent1);
    }
}