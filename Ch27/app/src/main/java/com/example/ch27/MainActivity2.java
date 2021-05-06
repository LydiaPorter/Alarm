package com.example.ch27;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.net.*;
import android.view.View;
import android.widget.Toast;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent implicitIntent = new Intent();
//        static PendingIntent getActivity(Context context, int requestCode, Intent intent, int flag);
        PendingIntent pendIntent = PendingIntent.getActivity(getApplicationContext(), 0, implicitIntent, 0);
        Notification.Builder builder = new Notification.Builder(getApplicationContext());
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setTicker("Notify!");// telling us you have a notification. runs across screen
        builder.setContentTitle("You have been notified!");// title when open
        builder.setContentText("This is the notification information.");// the message itself
        builder.setContentIntent(pendIntent);
        builder.setAutoCancel(true);// be able to clear notification bar

        Notification notify = builder.build();
        NotificationManager nm = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        nm.notify(0,notify);
    }
}