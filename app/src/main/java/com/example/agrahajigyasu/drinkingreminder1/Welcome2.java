package com.example.agrahajigyasu.drinkingreminder1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

public class Welcome2 extends AppCompatActivity {
    boolean welcome2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_2);
        final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        welcome2 = pref.getBoolean("welcome2",true);
        if(welcome2) {
            ConstraintLayout rl = (ConstraintLayout) findViewById(R.id.welcome2);
            rl.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.cyan));
            final TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker1);
            Button button = (Button) findViewById(R.id.button5);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int hour = timePicker.getCurrentHour();
                    int min = timePicker.getCurrentMinute();
                    pref.edit().putInt("WakingHour", hour).commit();
                    pref.edit().putInt("WakingMin", min).commit();
                    pref.edit().putBoolean("welcome2",false).commit();
                    Intent newIntent = new Intent(Welcome2.this, Welcome3.class);
                    startActivity(newIntent);
                    finish();
                }
            });
        }
        else{
            Intent newIntent = new Intent(Welcome2.this, Welcome3.class);
            startActivity(newIntent);
            finish();
        }

    }
}
