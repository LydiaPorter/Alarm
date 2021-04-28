package com.example.reminderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    //when BroadcastReceiver receives a message
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            Bundle bun = intent.getExtras(); //gives access to bundle data
            String name = bun.getString("alarm_name");
            String desc= bun.getString("alarm_desc");
            Toast t = Toast.makeText(context,name+"\n\n"+desc,Toast.LENGTH_LONG);
            t.show();
        }
        catch(Exception e){
            Toast t = Toast.makeText(context,"Something went wrong."+"\n\n",Toast.LENGTH_LONG);
            t.show();
        }
    }
}