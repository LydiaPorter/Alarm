package com.example.viewexamples;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    String bread = "";
    boolean cb1bool;
    boolean cb2bool;
    boolean cb3bool;
    CheckBox cb1 = (CheckBox)findViewById(R.id.checkBox1);
    CheckBox cb2 = (CheckBox)findViewById(R.id.checkBox2);
    CheckBox cb3 = (CheckBox)findViewById(R.id.checkBox3);
    TextView text1;
    String Jelly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text1 = (TextView) findViewById(R.id.test1);

        Integer x = text1.getHeight();
        text1.setText(x.toString());

        EditText name = (EditText) findViewById(R.id.editTextTextPersonName);
        String nameEntry = name.getText().toString();
        text1.setText(nameEntry);

        Button btn = (Button) findViewById(R.id.button);
        btn.setHeight(100);
        //btn.setOnClickListener();

        ToggleButton toggle = (ToggleButton) findViewById(R.id.toggleBtn); //getting access

    }

    public void oncheckedcb(View view) {
  //      cb1bool = cb1.isChecked();
 //       cb2bool = cb2.isChecked();
  //      cb3bool = cb3.isChecked();
        bread = "";
        boolean checked = ((CheckBox)view).isChecked();
        switch(view.getId()) {
            case R.id.checkBox1:
                if(checked){
                    bread+=cb1.getText()+" ";
                }
                break;
           case R.id.checkBox2:
                if(checked) {
                    bread += cb2.getText()+" ";
                }
                break;
            case R.id.checkBox3:
                if(checked) {
                    bread+=cb3.getText()+" ";
                }
                break;
        }

        /*
        if (cb1bool) {
            bread += cb1.getText()+" ";
        }
        if (cb2bool) {
            bread += cb2.getText()+" ";
        }
        if (cb3bool) {
            bread += cb3.getText()+" ";
        }*/
    }

    public void radioChecked(View view) {
        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.jelly);
        int id = radioGroup.getCheckedRadioButtonId();
        switch (id){
            case R.id.radioButton1:
                Jelly ="Raspberry";
                break;
            case R.id.radioButton2:
                Jelly ="Grape";
            case R.id.radioButton3:
                Jelly ="Mixed Berry";
                break;
        }
        text1.setText(Jelly);
    }

    public void onSwitch(View view) {
        boolean on = ((Switch) view).isChecked();
        LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
        if (on) {
            layout.setBackgroundColor(Color.GREEN);
        } else {
            layout.setBackgroundColor(Color.RED);
        }
    }

    public void onToggleSwitch (View view) {
        boolean ons = ((Switch) view).isChecked();
        LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
        if (ons) {
            layout.setBackgroundColor(Color.GREEN);
        } else {
            layout.setBackgroundColor(Color.RED);
        }
    }

    public void onButtonClicked(View view) {
        Spinner spinner = (Spinner)findViewById(R.id.spinner);

        text1.setText(String.valueOf(spinner.getSelectedItem()));
        String x = String.valueOf(spinner.getSelectedItem());
        ImageView pic = (ImageView)findViewById(R.id.pic);
        if (x=="burnt") {
            int image = R.drawable.buttertoast;
            String description = "buttered toast";
            pic.setImageResource(image);
            pic.setContentDescription(description);
        }
        if(x=="perfect") {
            int image = R.drawable.toast;
            String description = "toast";
            pic.setImageResource(image);
            pic.setContentDescription(description);
        }

        //TOAST MESSAGE!!
        CharSequence text = "this is a toast message!";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(this,text,duration);
        //location, what it's gonna say, for how long
        toast.show();

    }

}

