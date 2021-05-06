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


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Uri uri = Uri.parse("https://www.CompuScholar.com");
        Intent viewIntent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(viewIntent);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.webbtn){
            Uri uri = Uri.parse("https://www.CompuScholar.com");
            Intent viewIntent = new Intent(Intent.ACTION_VIEW);
            startActivity(viewIntent);
        }
        else if(id == R.id.contactsbtn){
//            Uri uri = Uri.parse("content://contacts/people");
//            Intent viewIntent = new Intent(Intent.ACTION_EDIT, uri);
//            startActivity(viewIntent);
              Toast myMessage = Toast.makeText(getApplicationContext(),"This is a toast message!", Toast.LENGTH_LONG);
              myMessage.setGravity(android.view.Gravity.BOTTOM, 0, 0);
              myMessage.show();
              Intent not = new Intent(this,MainActivity2.class);
              startActivity(not);
        }
        else if(id == R.id.phonebtn){
            Uri uri = Uri.parse("tel:1-123-456-7890");
            Intent viewIntent = new Intent(Intent.ACTION_CALL, uri);
            startActivity(viewIntent);
        }
    }
}