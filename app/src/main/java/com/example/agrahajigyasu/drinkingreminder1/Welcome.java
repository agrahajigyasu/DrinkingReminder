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
import android.widget.EditText;
import android.widget.Toast;

public class Welcome extends AppCompatActivity {
    boolean welcome1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_1);
        final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        welcome1 = pref.getBoolean("welcome1",true);
        if(welcome1){
        ConstraintLayout rl = (ConstraintLayout) findViewById(R.id.welcome);
        rl.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.bg_screen1));
        final EditText editText = (EditText) findViewById(R.id.editText10);
        Button button = (Button) findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String weight = editText.getText().toString();
                int weightInt;
                if(!weight.trim().matches("")){
                    weightInt = Integer.parseInt(weight);
                    pref.edit().putInt("Weight",weightInt).commit();
                    pref.edit().putBoolean("welcome1",false).commit();
                    Intent newIntent = new Intent(Welcome.this,Welcome2.class);
                    startActivity(newIntent);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Weight cannot be empty",Toast.LENGTH_SHORT).show();
                }
            }
        });
        }
        else {
            Intent newIntent = new Intent(Welcome.this,Welcome2.class);
            startActivity(newIntent);
            finish();
        }
    }

}