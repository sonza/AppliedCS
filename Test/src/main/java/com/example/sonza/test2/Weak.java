package com.example.sonza.test2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Weak extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weak);

        Intent recievedIntent = getIntent();
        String Message=recievedIntent.getStringExtra("Message_Key");
       // Float Percent=recievedIntent.getFloatExtra("Percentage");
        TextView finalView = (TextView) findViewById(R.id.myMessage);
       // TextView finalView2 = (TextView) findViewById(R.id.percentage);
        finalView.setText(Message);
       // finalView2.setText(Percent);
    }

}
