package com.example.reminderapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

public class AddAlarm extends AppCompatActivity implements View.OnClickListener{
    //Create a global instance of AlarmKeeper. This will allow us to access this variable anywhere in our class

    AlarmKeeper thisAlarm;

    int index = -1;
    Button saveBtn;
    Button timeBtn;
    Button dateBtn;
    EditText description;
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm);
        //get handlers to all of our buttons
        saveBtn = (Button)findViewById(R.id.saveBtn);
        timeBtn = (Button)findViewById(R.id.timeBtn);
        dateBtn = (Button)findViewById(R.id.dateBtn);

        //set the buttons' onClickListeners
        saveBtn.setOnClickListener(this);
        timeBtn.setOnClickListener(this);
        dateBtn.setOnClickListener(this);

        //initialize the AlarmKeeper object
        thisAlarm = new AlarmKeeper();

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            //
            String alarmString = extras.getString("alarm");
            index = extras.getInt("index");//which alarm

            if((index>=0)&&(alarmString != null)){
                thisAlarm.fromString(alarmString);
                displayAlarm();//show the alarm info
            }

        }

    }

    private void displayAlarm(){
        description = (EditText)findViewById(R.id.editDescription);
        name = (EditText)findViewById(R.id.editAlarmName);

        //when you make a new alarm, set new data to it.
        description.setText(thisAlarm.alarmDesc);
        name.setText(thisAlarm.alarmName);

        //set alarm date as text for the date button
        dateBtn.setText((thisAlarm.alarmMonth+1)+"/"+thisAlarm.alarmDay+"/"+thisAlarm.alarmYear);


        String AMPM = "AM";
        String strMinute;
        int hour = thisAlarm.alarmHour;
        if(hour>12){
            hour = hour - 12;
            AMPM = "PM";
        }
        if (thisAlarm.alarmMinute < 10){
            strMinute = "0"+thisAlarm.alarmMinute;
        }
        else{
            strMinute = ""+thisAlarm.alarmMinute;
        }
        timeBtn.setText(hour+":"+strMinute+" "+AMPM);

    }

    @Override
    public void onClick(View v) {

    }

    public static class MyDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener{

        public Dialog onCreateDialog(Bundle SavedInstanceState){
            return null;
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        }
    }
    public static class MyTimePicker extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

        public Dialog onCreateDialog(Bundle SavedInstanceState){
            return null;
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        }
    }

        public static class MyAlertDialog extends DialogFragment{
            public Dialog onCreateDialog(Bundle SavedInstanceState){
                return null;
            }
        }

        public void clearAlarmScreen(){
            description = (EditText)findViewById(R.id.editDescription);
            name = (EditText)findViewById(R.id.editAlarmName);
            timeBtn = (Button)findViewById(R.id.timeBtn);
            dateBtn = (Button)findViewById(R.id.dateBtn);

            description.setText("");
            name.setText("");
            timeBtn.setText("Set Time");
            dateBtn.setText("Set Date");

            thisAlarm.clear();

        }

        public void closeAlarmScreen(){
            String alarmString = thisAlarm.buildString();
            Intent listIntent = new Intent(AddAlarm.this,AlarmList.class);
            listIntent.putExtra("alarm",alarmString);
            listIntent.putExtra("index",index);

            setResult(RESULT_OK, listIntent);
            finish();


        }

}











