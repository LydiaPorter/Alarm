package com.example.reminderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
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
            Intent snoozeIntent = new Intent(context,Snooze.class);
            snoozeIntent.putExtra("name",name);
            snoozeIntent.putExtra("desc",desc);

            PendingIntent pendingIntent = PendingIntent.getActivity(context,0,snoozeIntent,0);
            //pops up when it should occur, not necessarily in the activity
            Notification.Builder builder = new Notification.Builder(context);

            builder.setSmallIcon(R.drawable.ic_launcher_foreground);
            builder.setTicker("Reminder!");
            builder.setContentTitle(name);
            builder.setContentText(desc);
            builder.setContentIntent(pendingIntent);
            builder.setAutoCancel(true);

            Notification notify = builder.build();
            NotificationManager nm = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
            nm.notify(0,notify);

        }
        catch(Exception e){
            Toast t = Toast.makeText(context,"Something went wrong."+"\n\n",Toast.LENGTH_LONG);
            t.show();
        }
    }
}