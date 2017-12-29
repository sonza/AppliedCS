/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.ghost;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import static com.google.engedu.ghost.GhostDictionary.MIN_WORD_LENGTH;
import static com.google.engedu.ghost.R.id.gameStatus;
import static com.google.engedu.ghost.R.id.ghostText;


public class GhostActivity extends AppCompatActivity {
    private static final String COMPUTER_TURN = "Computer's turn";
    private static final String USER_TURN = "Your turn";
    private GhostDictionary dictionary;
    private boolean userTurn = false;
    private Random random = new Random();
    private Button challengeButton,resetButton;
    private TextView statusTextView,wordFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghost);
        wordFragment = (TextView) findViewById(ghostText);//ghost text
        statusTextView = (TextView) findViewById(gameStatus); //game status
        challengeButton = (Button) findViewById(R.id.challengeButton);
        //resetButton = (Button) findViewById(R.id.resetButton);
        challengeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        AssetManager assetManager = getAssets();
        try{
            InputStream is = assetManager.open("words.txt");
            dictionary=new FastDictionary(is);
        }
        catch(Exception e){
            e.printStackTrace();
            Log.v("GHOST","words.txt not found!");
        }
        /**
         **
         **  YOUR CODE GOES HERE
         **
         **/
        onStart(null);
    }

    public void onClick(View view){ //challenge button
        String s = wordFragment.getText().toString();
        if(dictionary.isWord(s)&&s.length()>=4){
            statusTextView.setText("You Win!");
            challengeButton.setEnabled(false);
        }
        else if(dictionary.getAnyWordStartingWith(s)!=null){
            statusTextView.setText("Computer Wins!");
            challengeButton.setEnabled(false);
            wordFragment.setText(dictionary.getAnyWordStartingWith(s));
        }
        else{
            statusTextView.setText("You Win!");
            challengeButton.setEnabled(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ghost, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Handler for the "Reset" button.
     * Randomly determines whether the game starts with a user turn or a computer turn.
     * @param view
     * @return true
     */
    public boolean onStart(View view) {
        userTurn = random.nextBoolean();
        TextView text = (TextView) findViewById(ghostText);
        text.setText("");
        TextView label = (TextView) findViewById(gameStatus);
        if (userTurn) {
            label.setText(USER_TURN);
        } else {
            label.setText(COMPUTER_TURN);
            computerTurn();
        }
        return true;
    }

    private void computerTurn() {
        TextView label = (TextView) findViewById(gameStatus);
        // Do computer turn stuff then make it the user's turn again
        //get word fragment from the text view
        String s = wordFragment.getText().toString();
        if (s != null) {
            //if length > MIn_Word_Length and is a valid word
            if (s.length() >= MIN_WORD_LENGTH && dictionary.isWord(s)) {
                //declare win
                statusTextView.setText("Computer Wins!");
            }
            //get good word with prefix, if exists
            if (dictionary.getGoodWordStartingWith(s) != null) {
                //search and append letter to wordfragment
                String temp=dictionary.getAnyWordStartingWith(s);
                char c = s.charAt(s.length());
                s=s+Character.toString(c);
                wordFragment.setText(s);
                //else, declare win
            } else {
                statusTextView.setText("Computer Wins!");
            }
            userTurn = true;
            label.setText(USER_TURN);
        }
        else{
            //we need the computer to pick a good prefix
           // Random random;
            String temp=dictionary.getGoodWordStartingWith(null);
            char c=temp.charAt(0);
            wordFragment.setText(Character.toString(c));
        }
    }

    /**
     * Handler for user key presses.
     * @param keyCode
     * @param event
     * @return whether the key stroke was handled.
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        /**
         **  gets key unicode,coverts to char and appends to text
         **  YOUR CODE GOES HERE
         **
         **/
        if(userTurn){
            int uniCode = event.getUnicodeChar();
            char currentChar = (char) uniCode;
            if(Character.isLetter(currentChar)){
                String s = wordFragment.getText().toString() + Character.toString(currentChar);
                wordFragment.setText(s);
                userTurn=false;
                computerTurn();
                return true;
            }
        }

        return super.onKeyUp(keyCode, event);
    }
}
