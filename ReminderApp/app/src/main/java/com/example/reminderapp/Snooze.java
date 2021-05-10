package com.example.reminderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import java.util.Calendar;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.Button;


public class Snooze extends AppCompatActivity implements View.OnClickListener{
    String alarmName;
    String alarmDesc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.snooze);

        Button button = (Button)findViewById(R.id.snoozebtn);
        button.setOnClickListener(this);
        Intent startingIntent = getIntent();
        Bundle bun = startingIntent.getExtras();
        alarmName = bun.getString("name");
        alarmDesc = bun.getString("desc");
    }

    @Override
    public void onClick(View v) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(c.getTimeInMillis()+60000);
        AlarmKeeper snoozeAlarm = new AlarmKeeper();
        snoozeAlarm.alarmDesc = alarmDesc;
        snoozeAlarm.alarmDay = c.get(Calendar.DAY_OF_MONTH);
        snoozeAlarm.alarmMonth = c.get(Calendar.MONTH);
        snoozeAlarm.alarmYear = c.get(Calendar.YEAR);
        snoozeAlarm.alarmHour = c.get(Calendar.HOUR);
        snoozeAlarm.alarmMinute = c.get(Calendar.MINUTE);

        snoozeAlarm.setAlarm(Snooze.this);
        finish();
    }
}