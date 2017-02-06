import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;
//import java.util.StringUtils;

public class Dictionary {

  // maximum length for a word
  // (e.g., pneumonoultramicroscopicsilicovolcanoconiosis)
  final static int LENGTH = 45;
  HashSet<String> dict = new HashSet<String>();
  
  /**
  * Returns true if word is in dictionary else false.
  */
  boolean check(String word)
  {
      //TODO
	String temp =word.toLowerCase();
	if (dict.contains(temp))
		return true;
	else
      		return false;
  }

  /**
  * Loads dictionary into memory.  Returns true if successful else false.
  */
  boolean load(String wordsFile)
  {
    //TODO
	try
	{
		BufferedReader reader = new BufferedReader(new FileReader(wordsFile));
	 	String line;
    		while ((line = reader.readLine()) != null)
    	 	{
      			dict.add(line);
    	 	}
    		reader.close();
		return true;
	}
	catch (Exception e)
	{
		System.err.format("Exception occurred trying to read '%s'.", wordsFile);
    		return false;
	}
  }

  /**
  * Returns number of words in dictionary if loaded else 0 if not yet loaded.
  */
  int size()
  {
	int siz=dict.size();
	if(siz!=0) 	
		return siz;
	else
      		return 0; 
  }

  /**
  * Unloads dictionary from memory.  Returns true if successful else false.
  */
  boolean unload()
  {
    //TODO
	try
	{
		dict.clear();
		return true;
	}
	catch (Exception e)
	{
		 return false;
	}
  }
}
