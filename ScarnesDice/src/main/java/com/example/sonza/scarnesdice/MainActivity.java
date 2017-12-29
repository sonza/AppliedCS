package com.example.sonza.scarnesdice;

import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int userScore;
    private int userTurnScore;
    private int computerScore;
    private int computerTurnScore;
    private ImageView diceImageView;
    private ImageView diceImage2;
    private TextView status;
    private TextView scores;
    private Button rollButton;
    private Button holdButton;
    private Button resetButton;
    private Random random;
    private boolean userTurn;
    private Handler turnHandler; //android handler not java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        random = new Random();
        diceImageView = (ImageView) findViewById(R.id.diceImageView);
        diceImage2 = (ImageView) findViewById(R.id.diceImage2);
        scores = (TextView) findViewById(R.id.Score);
        status = (TextView) findViewById(R.id.Status);
        rollButton = (Button) findViewById(R.id.rollButton);
        holdButton = (Button) findViewById(R.id.holdButton);
        resetButton = (Button) findViewById(R.id.resetButton);
        userTurn=true;
        turnHandler = new Handler();
    }

    public void roll(View v){
        int rolledValue1 = getRoll1();
        int rolledValue2 = getRoll2();
        if(rolledValue1==1 || rolledValue2==1)
        {
            userTurnScore=0;
            userTurn = false;
            showScore();
            computerTurn();
        }
        else{
            userTurnScore+=(rolledValue1+rolledValue2);
            showScore();
        }
    }

    public void hold(View v){
        userScore+=userTurnScore;
        userTurnScore=0;
        userTurn=false;
        showScore();
        computerTurn();
    }

    public void reset(View v){
        computerScore=0;
        computerTurnScore=0;
        userScore=0;
        userTurnScore=0;
        showScore();
        rollButton.setEnabled(true);
        holdButton.setEnabled(true);
    }

    void computerTurn(){
        rollButton.setEnabled(false);
        holdButton.setEnabled(false);
        userTurn=false;
        computerTurnScore=0;
        Runnable runnable = new Runnable(){
            public void run(){
                int rolledValue1 = getRoll1();
                int rolledValue2 = getRoll2();
                if (rolledValue1 != 1 && rolledValue2 != 1) {
                    computerTurnScore+=(rolledValue1+rolledValue2);
                    showScore();
                    if(computerTurnScore>=20){
                        computerScore+=computerTurnScore;
                        computerTurnScore=0;
                        userTurn=true;
                        showScore();
                        status.setText("Computer Holds!");
                        rollButton.setEnabled(true);
                        holdButton.setEnabled(true);
                    }
                    else
                        turnHandler.postDelayed(this,2000);
                }
                else{
                    computerTurnScore=0;
                    showScore();
                    userTurn=true;
                    rollButton.setEnabled(true);
                    holdButton.setEnabled(true);
                }
            }
        };
        turnHandler.postDelayed(runnable,500);
    }

    public void showScore() {
        status.setText(" ");
        if(userTurn==true){
            status.setText("Your Turn!");
        }
        else{
            status.setText("Computer Turn!");
        }
        scores.setText("User Score:" + userScore + " User Turn Score:" + userTurnScore + " \nComputer Score:" + computerScore + " Computer Turn Score:" + computerTurnScore);
        if(userScore>=100){
            status.setText("USER WINS!");
            rollButton.setEnabled(false);
            holdButton.setEnabled(false);
        }
        else if (computerScore>=100){
            status.setText("COMPUTER WINS!");
            rollButton.setEnabled(false);
            holdButton.setEnabled(false);
        }
    }

    private void setDice1Image(int val){

        switch(val){
            case 1:
                diceImageView.setImageResource(R.drawable.dice1);
                break;
            case 2:
                diceImageView.setImageResource(R.drawable.dice2);
                break;
            case 3:
                diceImageView.setImageResource(R.drawable.dice3);
                break;
            case 4:
                diceImageView.setImageResource(R.drawable.dice4);
                break;
            case 5:
                diceImageView.setImageResource(R.drawable.dice5);
                break;
            case 6 :
                diceImageView.setImageResource(R.drawable.dice6);
                break;
            default:
                diceImageView.setImageResource(R.drawable.dice1);
        }
    }

    private void setDice2Image(int val){

        switch(val){
            case 1:
                diceImage2.setImageResource(R.drawable.dice1);
                break;
            case 2:
                diceImage2.setImageResource(R.drawable.dice2);
                break;
            case 3:
                diceImage2.setImageResource(R.drawable.dice3);
                break;
            case 4:
                diceImage2.setImageResource(R.drawable.dice4);
                break;
            case 5:
                diceImage2.setImageResource(R.drawable.dice5);
                break;
            case 6 :
                diceImage2.setImageResource(R.drawable.dice6);
                break;
            default:
                diceImage2.setImageResource(R.drawable.dice1);
        }
    }

    private int getRoll1(){
        int i = random.nextInt(6)+1;
        setDice1Image(i);
        return(i);
    }

    private int getRoll2(){
        int j = random.nextInt(6)+1;
        setDice2Image(j);
        return(j);
    }
}
