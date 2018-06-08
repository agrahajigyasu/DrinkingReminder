package com.example.agrahajigyasu.drinkingreminder1;

import java.util.Date;
import java.text.DateFormat;

public class user
{
    String email;
    String pass;
    String currentDateTimeString;
    public user(String email,String pass)
    {
        this.email=email;
        this.pass=pass;
        currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date()).replaceAll("[^\\w\\s]","").replaceAll("\\s+","");
    }
    public String getEmail()
    {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public String getCurrentDateTimeString(){
        return currentDateTimeString;
    }
}
