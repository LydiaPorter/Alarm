package com.example.guessmynumber;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {
    String guess;
    int tries = 3;
    Integer number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Random r = new Random();
        number = r.nextInt(10);
        number += 1;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView test = (TextView)findViewById(R.id.testoutput);
        test.setText(number.toString());
    }

    public void onGuess(View view) {
        tries -= 1;
        EditText guessBox = (EditText)findViewById(R.id.numberField);
        guess = guessBox.getText().toString();
        Intent makeGuess = new Intent(this,playScreen.class);
        makeGuess.putExtra("guess", guess);
        makeGuess.putExtra("number",number);
        makeGuess.putExtra("tries",tries);
        startActivity(makeGuess);
    }

}