package com.example.guessmynumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class playScreen extends AppCompatActivity {
    int tries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_screen);
        String guess = getIntent().getStringExtra("guess");
        int number = getIntent().getIntExtra("number",0);
        int tries = getIntent().getIntExtra("tries",0);
        TextView guesstext = (TextView)findViewById(R.id.playerguess);
        guesstext.setText("You guessed "+guess+".");

        TextView triesleft = (TextView)findViewById(R.id.triesleft);
        triesleft.setText("You have "+tries+" tries left.");

        TextView highorlow = (TextView)findViewById(R.id.highorlow);
        Integer numguess = Integer.parseInt(guess);
        //Integer num = Integer.parseInt(number);
        if (numguess < number) {
            highorlow.setText("Guess higher.");
        }
        else if(numguess > number){
            highorlow.setText("Guess lower.");
        }
        else {
            if(tries > 0){
                 if(numguess == number){
                     highorlow.setText("Good guess. YOU GOT IT!");
                 }
                 else{
                     highorlow.setText("Guess again.");
                 }
            }
            else {
                highorlow.setText("You are out of tries.");
            }
        }

        ///////////////
      //  public void onGuess() {

    //    }




    }

    public void buttonClick(View view){
        if (tries > 0){
            Intent backToGuess = new Intent(this,MainActivity.class);
            startActivity(backToGuess);
        }
    }
}