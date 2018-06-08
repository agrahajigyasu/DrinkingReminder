package com.example.agrahajigyasu.drinkingreminder1;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class WaterDrink {
    Context context;

    public WaterDrink(Context context) {
        this.context = context;
    }

    int calculateWater() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        int weight = pref.getInt("Weight", 60);
        if (weight <= 50)
            return 1900;
        if (weight > 50 && weight <= 55)
            return 2100;
        if (weight > 55 && weight <= 60)
            return 2300;
        if (weight > 60 && weight <= 70)
            return 2700;
        if (weight > 70 && weight <= 80)
            return 3200;
        if (weight > 80 && weight <= 90)
            return 3700;
        if (weight > 90 && weight <= 100)
            return 4100;
        if (weight > 100)
            return 4300;
        return 4000;
    }

}
