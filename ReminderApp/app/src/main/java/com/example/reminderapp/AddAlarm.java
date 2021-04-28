package com.example.reminderapp;

import androidx.appcompat.app.AppCompatActivity;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

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
        int id = v.getId();
        if(id == R.id.saveBtn){
            EditText name = (EditText)findViewById(R.id.editAlarmName);
            EditText desc = (EditText)findViewById(R.id.editDescription);

            thisAlarm.alarmName = name.getText().toString();
            thisAlarm.alarmDesc = desc.getText().toString();
            DialogFragment newFragment = new MyAlertDialog();
            newFragment.show(getFragmentManager(),"MyAlertDialog");
        }
        else if (id == R.id.dateBtn){
            DialogFragment newFragment = new MyDatePicker();
            newFragment.show(getFragmentManager(),"MyAlertDialog");
        }
        else if(id == R.id.timeBtn){
            DialogFragment newFragment = new MyTimePicker();
            newFragment.show(getFragmentManager(),"MyAlertDialog");
        }
    }

    //
    public static class MyDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener{

        public Dialog onCreateDialog(Bundle SavedInstanceState){
            Calendar now = Calendar.getInstance();
            int year = now.get(Calendar.YEAR);
            int month = now.get(Calendar.MONTH);
            int day = now.get(Calendar.DAY_OF_MONTH);
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











