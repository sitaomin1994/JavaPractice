// Name: Sitao Min
// USC NetID: sitaomin
// CS 455 PA4
// Spring 2018


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.util.Scanner;
import java.util.HashMap;
import java.lang.String;



/**
 * A dictionary of all anagram sets. 
 * Note: the processing is case-sensitive; so if the dictionary has all lower
 * case words, you will likely want any string you test to have all lower case
 * letters too, and likewise if the dictionary words are all upper case.
 */

public class AnagramDictionary {
   

    private HashMap<String, ArrayList<String>> dict;
   /**
    * Create an anagram dictionary from the list of words given in the file
    * indicated by fileName.  
    * PRE: The strings in the file are unique.
    * @param fileName  the name of the file to read from
    * @throws FileNotFoundException  if the file is not found
    */
   public AnagramDictionary(String fileName) throws FileNotFoundException {
           
          dict = new HashMap<String, ArrayList<String>>();
          File newFile =  new File(fileName);
          
          try(Scanner in = new Scanner(newFile)){
              while(in.hasNext()) {
                  String word  = in.next();
                  /**
                   * pre-processing words in the dictionary
                   */
                  String sortedWord = preprocessWord(word);
                  /**
                   * add words to the dictionary map
                   */
                  if(dict.containsKey(sortedWord) == false) {
                      ArrayList<String> value = new ArrayList<String>();
                      value.add(word);
                      dict.put(sortedWord, value);
                  }
                  else {
                      ArrayList<String> anagram = dict.get(sortedWord);
                      //System.out.println(word);
                      anagram.add(word);
                      dict.put(word, anagram);
                  }
              }
          }
   }
   

   /**
    * Get all anagrams of the given string. This method is case-sensitive.
    * E.g. "CARE" and "race" would not be recognized as anagrams.
    * @param s string to process
    * @return a list of the anagrams of s
    * 
    */
   public ArrayList<String> getAnagramsOf(String s) {
       
       ArrayList<String> result = new ArrayList<String>();    //result
       /**
        * find if string contain in the dictionary
        */
       if(dict.containsKey(s) == true) {
           result.addAll(dict.get(s));
       }
       
       return result;
   }
   
   /**
    * pre-processing each word in the dictionary
    * @param word processed word
    * @return sorted string word
    */
   private String preprocessWord(String word) {
       char[] wordArray = word.toCharArray();
       Arrays.sort(wordArray);
       String result = new String(wordArray);
       
       return result;
   }
   
   
}
