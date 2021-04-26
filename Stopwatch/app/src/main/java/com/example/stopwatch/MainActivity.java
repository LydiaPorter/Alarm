package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private boolean running = false;
    private int seconds = 0;
    private boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            running = savedInstanceState.getBoolean("running");
            seconds = savedInstanceState.getInt("seconds");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }

    @Override
    protected void onStart() {
        super.onStart(); //because it's inherited you use override and super

     }

     @Override
     protected void onResume() {
        super.onResume();
         if (wasRunning) {
             running = true;
         }
     }

    @Override
    protected void onPause() {
        super.onPause();
        wasRunning = running;
        running  = false;
    }


    @Override
    protected void onStop() {
        super.onStop(); //runs everything from previous method version
        wasRunning = running;
        running  = false;
    }


    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        //save data and put it into Bundle
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds); //name,variable(from the onCreate)
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       // Intent intent = new Intent(this, MainActivity2.class);
      //  startActivity(intent);
    }


    public void onClickStart(View view) {
        running = true;
    }
    public void onClickStop(View view){
        running = false;
    }
    public void onClickReset(View view){
        running = false;
        seconds = 0;
    }

    private void runTimer(){
        final TextView timeview = (TextView)findViewById(R.id.time_lbl);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run(){
                int hours = seconds / 3600;
                int minutes = (seconds%3600) / 60;
                int secs = seconds%60;
                String time = String.format(Locale.getDefault(),"%d:%02d:%02d",hours,minutes,secs);
                timeview.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }


}