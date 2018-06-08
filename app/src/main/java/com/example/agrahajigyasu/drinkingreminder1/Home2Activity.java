package com.example.agrahajigyasu.drinkingreminder1;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Home2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    int WaterDrank;
    String water;
    int waterToDrink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);


        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemTextColor(ColorStateList.valueOf(getResources().getColor(R.color.white)));
        navigationView.setBackgroundResource(R.drawable.navbackground);
        final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        final TextView textView = (TextView)findViewById(R.id.textView);
        final EditText editText4 = (EditText)findViewById(R.id.editText4);
        ImageButton button2 = (ImageButton)findViewById(R.id.button2);
        ImageButton button5 = (ImageButton)findViewById(R.id.imageButton5);
        WaterDrink waterDrink = new WaterDrink(getApplicationContext());
        waterToDrink = waterDrink.calculateWater();
        WaterDrank=pref.getInt("WaterDrank",0);
        water = String.valueOf(WaterDrank)+"/"+String.valueOf(waterToDrink)+"ml";
        textView.setText(water);
        final RadioButton radioButton = (RadioButton)findViewById(R.id.radioButton);
        final RadioButton radioButton2 = (RadioButton)findViewById(R.id.radioButton2);
        final RadioButton radioButton3 = (RadioButton)findViewById(R.id.radioButton3);
        final RadioButton radioButton4 = (RadioButton)findViewById(R.id.radioButton4);
        pref.edit().putInt("WaterDrank", WaterDrank).commit();
        textView.setGravity(Gravity.CENTER);
        final ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setProgress(WaterDrank/(waterToDrink/100));
        Animation leave = AnimationUtils.loadAnimation(this,R.anim.anim_leave);
        Animation enter = AnimationUtils.loadAnimation(this,R.anim.anim_enter);
        Animation expand_in = AnimationUtils.loadAnimation(this,R.anim.expand_in);
        Animation enter_top = AnimationUtils.loadAnimation(this,R.anim.enter_top);
        ImageView imageView5 = (ImageView)findViewById(R.id.imageView5);
        ImageView imageView8 = (ImageView)findViewById(R.id.imageView8);
        ImageView imageView9 = (ImageView)findViewById(R.id.imageView9);
        ImageView imageView6 = (ImageView)findViewById(R.id.imageView6);
        ImageButton button3 = (ImageButton)findViewById(R.id.button3);
        radioButton.setAnimation(enter);
        radioButton3.setAnimation(enter);
        radioButton2.setAnimation(leave);
        radioButton4.setAnimation(leave);
        progressBar.setAnimation(expand_in);
        editText4.setAnimation(expand_in);
        imageView9.setAnimation(enter);
        imageView5.setAnimation(enter);
        imageView8.setAnimation(leave);
        imageView6.setAnimation(leave);
        textView.setAnimation(enter_top);
        button2.setAnimation(expand_in);
        button3.setAnimation(expand_in);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String drank = String.valueOf(editText4.getText().toString());
                if(!drank.matches(""))
                {WaterDrank+=Integer.parseInt(drank);}
                if(radioButton.isChecked())
                    WaterDrank+=200;
                if(radioButton2.isChecked())
                    WaterDrank+=400;
                if(radioButton3.isChecked())
                    WaterDrank+=600;
                if(radioButton4.isChecked())
                    WaterDrank+=800;
                water = String.valueOf(WaterDrank)+"/"+String.valueOf(waterToDrink)+"ml";
                textView.setText(water);
                pref.edit().putInt("WaterDrank",WaterDrank).commit();
                if(WaterDrank>=waterToDrink)
                {
                    Toast.makeText(getApplicationContext(),"Congrats!! you have achieved today's TARGET",Toast.LENGTH_SHORT).show();
                }
                progressBar.setProgress(WaterDrank/(waterToDrink/100));
                editText4.setText("");
                radioButton.setChecked(false);
                radioButton2.setChecked(false);
                radioButton3.setChecked(false);
                radioButton4.setChecked(false);

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText4.setText("");
                radioButton.setChecked(false);
                radioButton2.setChecked(false);
                radioButton3.setChecked(false);
                radioButton4.setChecked(false);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        int id = item.getItemId();

        if (id == R.id.nav_weight) {
            pref.edit().putBoolean("welcome1",true).commit();
            Intent newIntent = new Intent(Home2Activity.this,Welcome.class);
            startActivity(newIntent);
            finish();
        } else if (id == R.id.nav_wake) {
            pref.edit().putBoolean("welcome2",true).commit();
            Intent newIntent = new Intent(Home2Activity.this,Welcome2.class);
            startActivity(newIntent);
            finish();

        } else if (id == R.id.nav_sleep) {
            pref.edit().putBoolean("welcome3",true).commit();
            Intent newIntent = new Intent(Home2Activity.this,Welcome3.class);
            startActivity(newIntent);
            finish();

        } else if (id == R.id.nav_logout) {
            pref.edit().putBoolean("firstActivity",true).commit();
            AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
            Intent myIntent = new Intent(getApplicationContext(),AlarmNotificationSender.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),0,myIntent,0);
            manager.cancel(pendingIntent);
            SharedPreferences.Editor editor = pref.edit();
            editor.clear();
            editor.commit();
            Intent newIntent = new Intent(Home2Activity.this,Welcome.class);
            startActivity(newIntent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
