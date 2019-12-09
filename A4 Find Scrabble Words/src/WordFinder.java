// Name: Sitao Min
// USC NetID: sitaomin
// CS 455 PA4
// Spring 2018

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Map;
import java.util.Collections;
import java.util.Comparator;
/**
 * This contains the main method. 
 * This class will have a main that's responsible for processing the command-line argument, 
 * and handling any error processing. It will probably also have the main command loop. 
 * Most of the other functionality will be delegated to other object(s) created in main and their methods.
 */
public class WordFinder {

    public static void main(String[] args) {
        
        boolean hasDictionary = false;     // sign for whether user provide dictionary
        
        if(args.length != 0) {             // if args has value means user has dictionary
            hasDictionary = true;
        }
        
        String fileName = "";              // file name of dictionary input
        try(Scanner in = new Scanner(System.in)){
                  
            /*
             * assign dictionary name
             */
            if(hasDictionary) {
                fileName =  args[0];
            }
            else {
                fileName = "sowpods.txt";
            }
            
            AnagramDictionary dictionary = new AnagramDictionary(fileName);
            /*
             * prompt for input and output the result of game
             */
            System.out.println("Type . to quit.");
            while(true) {
                System.out.print("Rack? ");
                if(in.hasNext()) {
                    String nextInput = in.next();
                
                    if(nextInput.equals(".")) {
                        break;
                    }
                    else {
                   
                        findWord(nextInput, dictionary);
                    }
                    in.nextLine();
                }
             }                
           
        }
        catch(FileNotFoundException error) {
            System.out.println("File not found:"+fileName);
        }
    }
    /**
     * find words of input string
     * @param input input word
     */
    private static void findWord(String input, AnagramDictionary dict) {
        Rack rack = new Rack(input);                                // create a new rack
        ScoreTable scoreTable = new ScoreTable();                   // create score table
        ArrayList<String> allSubSet = rack.getAllSubsets();         // get all subset of the give rack
        
        TreeMap<String,Integer> result = new TreeMap<String,Integer>();   // store result words
        /**
         * compute the result
         */
        for(int i = 0; i< allSubSet.size();i++) {
            
            ArrayList<String> anagram = dict.getAnagramsOf(allSubSet.get(i));         // output result of the scrabble game
            
            for(int j = 0; j< anagram.size();j++) {
                String temp = anagram.get(j);
                int score = scoreTable.getScore(temp);
                result.put(temp,score);   
            }    
        }
        /**
         * sort result by score
         */
        ArrayList<Map.Entry<String, Integer>> resultList = new ArrayList<Map.Entry<String,Integer>>(result.entrySet());
        
        Collections.sort(resultList, new ResultComparator());
        
        /**
         * print result
         */
        System.out.println("We can make " + resultList.size() +" words from \"" + rack.getSortedRackWord()+"\"");
        if(resultList.size() > 0) {
            System.out.println("All of the words with their scores (sorted by score):");
            for(int i = 0; i< resultList.size();i++) {
                System.out.println(resultList.get(i).getValue()+ ": "+ resultList.get(i).getKey());
            }
        }
    }

}


class ResultComparator implements Comparator<Map.Entry<String,Integer>>{
    
    public int compare(Map.Entry<String,Integer> a, Map.Entry<String,Integer> b) {
       return -Integer.compare(a.getValue(), b.getValue());
    } 
}