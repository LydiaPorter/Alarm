package com.example.reminderapp;

import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
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
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            AddAlarm activity = (AddAlarm)getActivity();
            activity.thisAlarm.alarmYear = year;
            activity.thisAlarm.alarmMonth = month;
            activity.thisAlarm.alarmDay = day;

            Button dateBtn = (Button)activity.findViewById(R.id.dateBtn);
            dateBtn.setText(Integer.toString(month+1)+"/"+day+"/"+year);


        }
    }
    public static class MyTimePicker extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

        public Dialog onCreateDialog(Bundle SavedInstanceState){
            Calendar now = Calendar.getInstance();
            int hour = now.get(Calendar.HOUR_OF_DAY);
            int minute = now.get(Calendar.MINUTE);

            return new TimePickerDialog(getActivity(),this,hour,minute, false);
        }

        @Override
        public void onTimeSet(TimePicker view, int hour, int minute) {
            AddAlarm activity = (AddAlarm)getActivity();
            activity.thisAlarm.alarmHour = hour;
            activity.thisAlarm.alarmMinute = minute;
            String AMPM = "AM";
            String strMinute;

            if(hour>12){
                hour = hour - 12;
                AMPM = "PM";
            }

            if(minute < 10){
                strMinute = "0"+minute;
            }
            else {
                strMinute = "" + minute; //parse/make it a string
            }

            Button timeBtn = (Button)activity.findViewById(R.id.timeBtn);
            timeBtn.setText(hour + ":" + strMinute+AMPM);

        }
    }

        public static class MyAlertDialog extends DialogFragment{
            public Dialog onCreateDialog(Bundle SavedInstanceState){
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Are you sure you want to set this alarm?");
                builder.setCancelable(false); //can't just ignore it
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        AddAlarm activity = (AddAlarm)getActivity();
                        if(activity.thisAlarm.alarmName == null || activity.thisAlarm.alarmName.length() == 0){
                            return;
                        }
                        if(activity.thisAlarm.alarmDesc == null || activity.thisAlarm.alarmDesc.length() == 0){
                            return;
                        }
                        if(activity.thisAlarm.alarmYear == 0){
                            return;
                        }
                        if(activity.thisAlarm.alarmHour == 0){
                            return;
                        }
                        activity.thisAlarm.setAlarm(activity);
                        activity.clearAlarmScreen(); //set back to default by using the method below
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        AddAlarm activity = (AddAlarm)getActivity(); //get reference to activity
                        activity.clearAlarmScreen(); //call method to clear and reset it
                    }
                });
                return builder.create();
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











