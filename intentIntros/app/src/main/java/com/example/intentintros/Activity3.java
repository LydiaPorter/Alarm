package com.example.intentintros;

import androidx.appcompat.app.AppCompatActivity; //these imports are the same
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Activity3 extends Activity{

    @Override  //every app needs an onCreate
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3);
    }

    public void onClick(View view) {
        Intent goToPage1= new Intent(this, MainActivity.class);
        startActivity(goToPage1);
    }

}
