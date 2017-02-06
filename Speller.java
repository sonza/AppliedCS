/**
* Applied CS PBL MSRIT
* Speller- Lab Exercise 1
* Complete Dictionary.java to handle spell checking functions
* @author  Arjun Rao
* @version 1.0
* @since   2017-02-6 
*/




import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Speller {
  private final static String DICTIONARY = "./words.txt";
  
  public static void main(String args[])
  {


     // check for correct number of args
    if (args.length != 1 && args.length != 2)
    {
        System.out.println("Usage: speller [dictionary] text");
        return;
    }
    
    //Setup words.txt
    String wordsFile = (args.length == 2) ? args[0] : DICTIONARY;    
    
    //Setup text for spell check
    String text = (args.length == 2) ? args[1] : args[0];


    //Initiate Dictionary class
    Dictionary english = new Dictionary();

    //Load Dictionary from wordsFile
    english.load(wordsFile);

    // prepare to spell-check
    int index = 0, misspellings = 0, words = 0,c;
    char[] word = new char[Dictionary.LENGTH+1];

  	BufferedReader br = null;  	
    // try to open text and perform spell check
  	try {

  		br = new BufferedReader(new FileReader(text));
  		
      // prepare to report misspellings
      System.out.println("MISSPELLED WORDS\n\n");
      
      
      // spell check each word in text
      while ((c = br.read()) != -1) 
      {        
        if (Character.isLetter((char)c) || ((char)c == '\'' && index > 0))
        {
            // append character to word
            word[index] = (char)c;
            index++;

            // ignore alphabetical strings too long to be words
            if (index > Dictionary.LENGTH)
            {
                // consume remainder of alphabetical string
                while ((c = br.read()) != -1 && Character.isLetter((char)c));

                // prepare for new word
                index = 0;
            }
        }

        // ignore words with numbers (like MS Word can)
        else if (Character.isDigit((char)c))
        {
            // consume remainder of alphanumeric string
           while ((c = br.read()) != -1 && Character.isLetterOrDigit((char)c));

            // prepare for new word
            index = 0;
        }

        // we must have found a whole word
        else if (index > 0)
        {            
            // update counter
            words++;
            String toCheck = new String(word,0,index);
            // check word's spelling            
            boolean misspelled = !english.check(toCheck);            
           

            // print word if misspelled
            if (misspelled)
            {
                System.out.println(word);
                misspellings++;
            }

            // prepare for next word
            index = 0;
            word = new char[Dictionary.LENGTH];
        }
        

  		}

  	} catch (FileNotFoundException e) {
  		e.printStackTrace();
  	} catch (IOException e) {
  		e.printStackTrace();
  	} finally {
  		if (br != null) {
  			try {
  				br.close();
  			} catch (IOException e) {
  				e.printStackTrace();
  			}
  		}
  	}

    //get number of words in dictionary
    int dWords = english.size();
    // unload dictionary    
    english.unload();
    
    // report statistics
    System.out.println("WORDS IN DICTIONARY: " + dWords);
    System.out.println("WORDS IN DICTIONARY AFTER UNLOAD: " + english.size());
    System.out.println("WORDS IN TEXT: " + words);
    System.out.println("WORDS MISSPELLED: " + misspellings);
  }
}
