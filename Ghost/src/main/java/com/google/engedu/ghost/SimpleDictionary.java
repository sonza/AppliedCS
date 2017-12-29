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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class SimpleDictionary implements GhostDictionary {
    private ArrayList<String> words;

    public SimpleDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        words = new ArrayList<>();
        String line = null;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            if (word.length() >= MIN_WORD_LENGTH)
              words.add(line.trim());
        }
        Collections.sort(words);
    }

    @Override
    public boolean isWord(String word) {

        return words.contains(word);
    }

    @Override
    public String getAnyWordStartingWith(String prefix) {
        //gets any word from arraylist words
        //3 cases:
        //prefix is null so returns any random word from the arraylist
        //prefix is something, returns first word with that prefix
        //prefix is something but no word in list starts with it so it returns null
        if(prefix == null){
            return words.get(new Random().nextInt(words.size()));
        }
        else{
            //this method is inefficient do binary search
            /*for (String word:words) {
                if (word.startsWith(prefix))  //checks if word starts with prefix
                    return (word);
            }*/
            return(binarySearch(prefix));
        }
    }

    String binarySearch(String prefix){
        int first = 0;
        int last = words.size();
        while(first < last){
            int middle = (first+last)/2;
            String middleWord = words.get(middle);
            if(middleWord.startsWith(prefix)){
            return middleWord;
            }
            else if(middleWord.compareTo(prefix) > 0){ //if word greater than prefix its greater than zero; ie prefix is after middleword
                first = middle+1;
            }
            else{ // if word lesser than prefix its less than zero; ie prefix is before middleword
                last = middle-1;
            }
        }
        return(null);
    }

    @Override
    public String getGoodWordStartingWith(String prefix) {
        //make a list of prefix words and return random word from that
        /*ArrayList<String> prefixList = new ArrayList<>();
        for (String word:words) {
            if (word.startsWith(prefix))  //checks if word starts with prefix
                prefixList.add(word);
        }
        Random random =new Random();
        prefixList[random.nextInt(prefixList.size())];*/
        String selected=null;
        return selected;
    }
}
