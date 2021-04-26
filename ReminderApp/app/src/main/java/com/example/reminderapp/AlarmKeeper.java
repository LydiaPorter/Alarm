package com.example.reminderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.Calendar;
import java.util.Random;
import java.util.StringTokenizer;

public class AlarmKeeper {
    public String alarmName;
    public String alarmDesc;
    public int alarmYear;
    public int alarmMonth;
    public int alarmDay;
    public int alarmHour;
    public int alarmMinute;
    private int alarmReqCode;
    //store primitive data and put it in an object

    public AlarmKeeper(){
        //constructor
        Random r = new Random(System.currentTimeMillis());
        alarmReqCode = (1+r.nextInt(2))*10000 + r.nextInt(10000);
    }

    public void clear(){
        alarmName = "";
        alarmDesc = "";
        alarmDay = 0;
        alarmMonth = 0;
        alarmYear = 0;
        alarmHour = 0;
        alarmMinute = 0;

        Random r = new Random(System.currentTimeMillis());
        alarmReqCode = (1+r.nextInt(2))*10000 + r.nextInt(10000);
    }

    public void fromString(String alarmString){
        //string tokenizer splits the string into smaller sections (comma determine where a field starts and ends)
        //comma separated value (CSV file)
        StringTokenizer st = new StringTokenizer(alarmString,","); //comma is the separator
        alarmName = st.nextToken();
        alarmDay = Integer.parseInt(st.nextToken());
        alarmMonth = Integer.parseInt(st.nextToken());
        alarmYear = Integer.parseInt(st.nextToken());
        alarmHour = Integer.parseInt(st.nextToken());
        alarmMinute = Integer.parseInt(st.nextToken());
        alarmDesc = st.nextToken();
        alarmReqCode = Integer.parseInt(st.nextToken());
    }

    public void setReqCode (int reqCode){ alarmReqCode = reqCode; }
    public int getReqCode() {return alarmReqCode;}

    public void setAlarm(Context context){
        Calendar cal = Calendar.getInstance();
        cal.set(alarmYear,alarmMonth,alarmDay,alarmHour,alarmMinute);
        Intent intent = new Intent(context,AlarmReceiver.class);
        intent.putExtra("alarm_name",alarmName);
        intent.putExtra("alarm-desc",alarmDesc);

        PendingIntent sender = PendingIntent.getBroadcast(context,alarmReqCode,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        //WAITING FOR TIME TO OCCUR BEFORE INTENT STARTS

        //get alarm manger service
        AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        //set alarm
        am.set(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),sender);



    }
    public void cancelAlarm(Context context){
        
    }

    public String buildString(){
        String strReturn = "";
        strReturn += alarmName + ",";
        strReturn+=alarmDay + ",";
        strReturn+=alarmMonth + ",";
        strReturn+=alarmYear + ",";
        strReturn+=alarmHour + ",";
        strReturn+=alarmMinute + ",";
        strReturn+=alarmDesc + ",";
        strReturn+=alarmReqCode + ",";

        return strReturn;
    }
}