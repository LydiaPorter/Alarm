package com.example.sodaadvisor;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Spinner;
//you can import each one separately to save memory
import java.util.List;
import java.util.Random;

public class MainActivity extends Activity{
    private DrinkExpert expert = new DrinkExpert();
    Random r = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) { //onCreate is called when you open the app
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //links activity_main.xml to Java
    }

    //onClick method called when clicked
    public void onClick_findDrink(View view){ //must be public.
        // reference to text view so i can change the text
        TextView brands = (TextView) findViewById(R.id.brands);
        //parses brands into text view
        //be sure to call whatever the id is of your "brands" text view
        brands.setText("You clicked the button!");
        //setting the text on the brands text
        //creates text view object, finds by id, and makes it equal brands variable
        //R is a java file that creates stuff
        //gives us access to text view properties
        Spinner color = (Spinner) findViewById(R.id.color);
        //giving reference to the spinner object
        String selected = color.getSelectedItem().toString();
        //getting selected item an converting it to a string
        brands.setText("Try this drink:"+selected);
        //setting text of brands to what was selected

        //getting the drink list of references
        List<String>choice_list = expert.getBrands(selected);
        //returns choice_list
        StringBuilder drinkString = new StringBuilder();
        for(String choice: choice_list){
            drinkString.append("\t\t "+choice+"\n"); //add onto end of string
        }
        brands.setText("Try one of these drinks: \n"+drinkString);


        //random option Jaiden
       // brands.setText("try this drink"+choice_list.get(r.nextInt(choice_list.size())));


      }
}