package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.*;
import android.content.DialogInterface;
import android.widget.*;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = (Button)findViewById(R.id.button1);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DialogFragment date_picker = new TimeDialog();
                date_picker.show(getFragmentManager(),"dialog");
                DialogFragment listFrag = new AlertDialogList();
                listFrag.show(getFragmentManager(),"list dialog");
                DialogFragment newFragment = new MyAlertDialog();
                newFragment.show(getFragmentManager(), "dialog");
            }
        });
    }

    public static class TimeDialog extends DialogFragment implements TimePickerDialog.OnTimeSetListener{
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR);
            int min = c.get(Calendar.MINUTE);
            int sec = c.get(Calendar.MINUTE);

            return new TimePickerDialog(getActivity(), this, hour, min, true);
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            MainActivity main = (MainActivity)getActivity();
            TextView text = (TextView)main.findViewById(R.id.textView1);
            text.setText(hourOfDay+":"+minute);

        }
    }

    public static class DateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        public Dialog onCreateDialog(Bundle savedInstanceState){
//          return builder.create();
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(),this,year,month,day);

        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            MainActivity main = (MainActivity)getActivity();
            TextView text = (TextView)main.findViewById(R.id.textView1);
            text.setText(year+"/"+month+"/"+dayOfMonth);
        }
    }

        public static class AlertDialogList extends DialogFragment{
            String[] tips = {"10%","15%","20%","$0.02"};

            public Dialog onCreateDialog(Bundle savedInstanceState){
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("How much do you want to tip?");
                // builder.setItems(tips, new DialogInterface.OnClickListener() //single choice list
                //used for a radio button list
                //checked button is index position of which tip is selected
               // builder.setSingleChoiceItems(tips,2, new DialogInterface.OnClickListener(){
                builder.setMultiChoiceItems(tips, true, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        String tip = tips[id];
                        return;
                    }
                });
            }
        }

//                    @Override
//                        public void onClick(DialogInterface dialog, int id) {
//                            String tip = tips[id];
//                            MainActivity mainActivity = (MainActivity)getActivity();
//                            TextView text = (TextView)mainActivity.findViewById(R.id.textView1);
//                            text.setText("I will tip "+tip+".");
//                        }
//                    });
//                    builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            return;
//                        }
//                    });
//                    return builder.create();

//        Button myButton = (Button)findViewById(R.id.myButton);
//        myButton.setOnClickListener(new myButtonListener());
//
//        Button yourButton = (Button)findViewById(R.id.yourButton);
//        yourButton.setOnClickListener(new yourButtonListener());
//
//        Button ourButton = (Button)findViewById(R.id.ourButton);//anonymous class
//        ourButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //do something
//            }
//        });




    public static class AlertDialog2 extends DialogFragment{ //must be static; doesn't have to have a 2nd instance created
        public Dialog onCreateDialog(Bundle savedInstanceState){
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()); //access back to activity that started this
            builder.setMessage("This is another alert dialog.");
            builder.setCancelable(true);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() { //create anonymous class
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity myActivity = (MainActivity)getActivity();
                    TextView myView = (TextView)myActivity.findViewById(R.id.myView);
                    myView.setText("this is my text view");
                }
            });

            return builder.create();
        }
    }

    public static class MyAlertDialog extends DialogFragment {
        public Dialog onCreateDialog(Bundle savedInstanceState){
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            builder.setMessage("Do you hate when these pop up?");
            builder.setCancelable(false); //user has to give response back
            builder.setPositiveButton("Yes",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            //do whatever yes should do
                            MainActivity myActivity = (MainActivity)getActivity();
                            TextView myView = (TextView)myActivity.findViewById(R.id.textView1);
                            myView.setText("I clicked yes.");
                        }
                    });
            builder.setMessage("Do you hate when these pop up?");
            builder.setCancelable(false); //user has to give response back
            builder.setNegativeButton("No",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            //do whatever yes should do
                            MainActivity myActivity = (MainActivity)getActivity();
                            TextView myView = (TextView)myActivity.findViewById(R.id.textView1);
                            myView.setText("I clicked no.");
                        }
                    });
            builder.setMessage("Do you hate when these pop up?");
            builder.setCancelable(false); //user has to give response back
            builder.setNeutralButton("I don't care",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            //do whatever yes should do
                            MainActivity myActivity = (MainActivity)getActivity();
                            TextView myView = (TextView)myActivity.findViewById(R.id.textView1);
                            myView.setText("I don't care!");
                        }
                    });


            //builder.setPositiveButton("Yes", new PositiveResponse());

            return builder.create();

        }

//        public class PositiveResponse implements DialogInterface.OnClickListener{
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        }

}
//    class myButtonListener implements View.OnClickListener{
//
//        @Override
//        public void onClick(View v) {
//            //do something
//        }
//    }
//    class yourButtonListener implements View.OnClickListener{
//
//        @Override
//        public void onClick(View v) {
//            //do something
//        }
//    }

}
