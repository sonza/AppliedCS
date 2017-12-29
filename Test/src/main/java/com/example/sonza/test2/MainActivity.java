package com.example.sonza.test2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button yes;
    Button no;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        yes = (Button) findViewById(R.id.yes);
        no = (Button) findViewById(R.id.no);
       // editText =  (EditText) findViewById(R.id.editText);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void yesMessage(View v)
    {
        String message = "Yay! LEVEL UP!";
        //String temp = editText.getText().toString();
        //float percent = Float.parseFloat(temp);
        Intent StrongActivityIntent= new Intent(this,Strong.class);
        StrongActivityIntent.putExtra("Message_Key",message);
       // StrongActivityIntent.putExtra("Percentage",percent);
        startActivity(StrongActivityIntent);
    }

    public void noMessage(View v)
    {
        String message = "Boo! So sad!";
       // String temp = editText.getText().toString();
       // float percent = Float.parseFloat(temp);
        Intent WeakActivityIntent= new Intent(this,Weak.class);
        WeakActivityIntent.putExtra("Message_Key",message);
       // WeakActivityIntent.putExtra("Percentage",percent);
        startActivity(WeakActivityIntent);
    }
}


