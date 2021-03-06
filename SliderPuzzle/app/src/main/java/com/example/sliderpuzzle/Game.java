package com.example.sliderpuzzle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Random;

public class Game extends AppCompatActivity implements View.OnClickListener{
    private int emptyX= 3;
    private int emptyY = 3;
    private RelativeLayout btngroup;
    private Button[][] buttons; //2d so 2[][]
    private int[] tiles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        loadViews();
        loadNumbers();
        genNumbers();
        loadDataToViews();
    }

    public void buttonClick(View view){

        }
    }

    private void checkWin(){
        boolean isWin = false;
        if(emptyX == 3 && emptyY == 3){
            for(int i = 0; i <btngroup.getChildCount()-1;i++){
                if(buttons[i/4][i%4].getText().toString().equals(String.valueOf(i+1))){
                    isWin = true;
                }
                else{
                    isWin = false;
                    break;
                }
            }
        }

        if (isWin){
            Toast.makeText(this,"Win!!!",Toast.LENGTH_LONG).show();
            for(int i = 0; i <btngroup.getChildCount()-1;i++){
                buttons[i/4][i%4].setClickable(false);
            }
        }
    }


    private void loadDataToViews(){
        emptyX =3;
        emptyY = 3;
        for(int i = 0; i<btngroup.getChildCount()-1;i++){
            buttons[i/4][i%4].setText(String.valueOf(tiles[i]));
            buttons[i/4][i%4].setBackgroundResource(android.R.drawable.btn_default);
        }
        buttons[emptyX][emptyY].setText("");
        buttons[emptyX][emptyY].setBackgroundColor(ContextCompat.getColor(this,R.color.colorFreeButton));

    }


    private void genNumbers(){
        int n = 15;
        Random r = new Random();
        while(n>1){
            int randomNum = r.nextInt(n--);
            int temp = tiles[randomNum];

        }
        if(!isSolvable()){
            genNumbers();
        }
    }

    private boolean isSolvable(){
        int countInversions = 0;
        for(int i=0; i<15; i++){
            for(int j =0; j<i; i++){
                if(tiles[j]> tiles[i]){
                    countInversions++;
                }
            }
        }
        return countInversions%2 == 0;
    }

    private void loadNumbers(){
        tiles= new int[16];
        for(int i =0; i<btngroup.getChildCount()-1;i++){
            tiles[i]=i+1;
        }
    }
    private void loadViews(){
        btngroup=findViewById(R.id.btngroup);
        buttons = new Button[4][4];

        for(int i = 0; i<btngroup.getChildCount(); i++){
            buttons[i/4][i%4] = (Button)btngroup.getChildAt(i);
        }
    }

    @Override
    public void onClick(View view) {
        Button button = (Button)view;
        int x = button.getTag().toString().charAt(0)-'0';
        int y = button.getTag().toString().charAt(1)-'0';

        if((Math.abs(emptyX-x) == 1 && emptyY == y) || (Math.abs(emptyY-y) == 1&& emptyX == x)){
            buttons[emptyX][emptyY].setText(button.getText().toString());
            buttons[emptyX][emptyY].setBackgroundResource(android.R.drawable.btn_default);

            button.setText("");
            button.setBackgroundColor(ContextCompat.getColor(this,R.color.colorFreeButton));
            emptyY = y;
            emptyX = x;
            checkWin();
    }
}
}