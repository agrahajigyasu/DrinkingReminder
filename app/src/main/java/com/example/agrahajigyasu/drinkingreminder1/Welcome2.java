package com.example.agrahajigyasu.drinkingreminder1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.preference.PreferenceManager;

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
