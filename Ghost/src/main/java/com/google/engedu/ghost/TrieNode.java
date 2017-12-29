package com.google.engedu.ghost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static android.R.attr.x;


public class TrieNode {
    private HashMap<Character, TrieNode> children;
    private boolean isWord;
    char value;

    public TrieNode() {
        children = new HashMap<>();
        isWord = false;
        value='\0';
    }

    TrieNode(char c){
        isWord=false;
        value=c;
        children = new HashMap<>();
    }


    public void add(String s) {
        TrieNode temp = this;
        //HashMap <Character, TrieNode> children;
        int len= s.length();
        for(int i=0;i<len;i++){
            char c = s.charAt(i);
            if(temp.children.containsKey(c))
                temp = temp.children.get(c);
            else{
                TrieNode leaf = new TrieNode(c);
                temp.children.put(c,leaf);
                temp = leaf;
            }
        }
        temp.isWord = true;
    }

    TrieNode findNode(String s){
        TrieNode temp=this;
        for(char i=0;i<s.length()-1;i++){
            char c=s.charAt(i);
            if(temp.children.containsKey(c) && !temp.isWord){
                temp=temp.children.get(c);
            }
            else
                return null;
        }
        return temp;
    }

    public boolean isWord(String s) {
        TrieNode temp=findNode(s);
        if(temp != null && temp.isWord)
            return true;
        else
            return false;
    }

    public String getAnyWordStartingWith(String s) {
        ArrayList<String> words = new ArrayList<>();
        Random r = new Random();
        String prefix;
        if(s==null){
            char randchar = (char)(r.nextInt(26) + 'a');
            prefix = Character.toString(randchar);
        }
        else
            prefix=s;
        TrieNode temp = findNode(prefix);
        if(temp!=null){
            if(temp.isWord)
                words.add(prefix);
            findWords(prefix,temp,words);
            if(words.size()!=0)
                return(words.get(r.nextInt(words.size())));
            else
                return null;
        }
        else
            return null;

    }
    public void findWords(String prefix, TrieNode temp, ArrayList<String> words){
        for(TrieNode x:temp.children.values()){
            String currentStr = prefix+x.value;
            if(x.isWord)
                words.add(currentStr);
            if(x.children.size()>0)
                findWords(currentStr,x,words);
        }
    }

    public String getGoodWordStartingWith(String s) {
        return null;
    }
}
