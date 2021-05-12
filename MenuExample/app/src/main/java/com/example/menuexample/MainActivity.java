package com.example.menuexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu,menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.actionDelete){
            Toast t = Toast.makeText(getApplicationContext(), "Clicked Delete", Toast.LENGTH_LONG);
            t.show();
        }
        else if(item.getItemId()==R.id.actionEdit){
            Toast t = Toast.makeText(getApplicationContext(), "Clicked Edit", Toast.LENGTH_LONG);
            t.show();
        }
        return super.onOptionsItemSelected(item);
    }
}