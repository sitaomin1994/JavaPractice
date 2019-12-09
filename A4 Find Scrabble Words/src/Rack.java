// Name: Sitao Min
// USC NetID: sitaomin
// CS 455 PA4
// Spring 2018

import java.util.ArrayList;
import java.util.Arrays;
import java.lang.String;
import java.util.Map;
import java.util.TreeMap;
import java.util.Iterator;
/**
 * A Rack of Scrabble tiles
 * This corresponds to the idea of the rack in the problem description. 
 * Thus, wherever your program is using a rack, it should be using an object of type Rack. 
 */

public class Rack {
    
    /**
     *  Instance Invariant
     */
    private String rackWordSorted;   // word contain in the rack with sorted order
    private String rackWord;         // word that rack contain without replication
    private int[] multiplication;    // information of multiplication of each character in the rack
    
    public Rack(String inputWord) {
        
        /**
         * create non-replication words of input string
         */
        char[] temp = inputWord.toCharArray();
        Arrays.sort(temp);
        rackWordSorted = new String(temp);
        TreeMap<Character,Integer> rackInfoMap = getRackInfo(inputWord);
        rackWord = "";
        multiplication = new int[rackInfoMap.size()]; 
        Iterator<Map.Entry<Character,Integer>> iter = rackInfoMap.entrySet().iterator();
        int position = 0;
        while(iter.hasNext()) {
           Map.Entry<Character, Integer> element = iter.next(); 
           rackWord += element.getKey();
           multiplication[position] = element.getValue();
           position++;
        }        
       
    }
    /**
     * get all multi set of input rack    
     * @return ArrayList of all subsets words
     */
  
    public ArrayList<String> getAllSubsets(){
        
       ArrayList<String> allCombos = allSubsets(rackWord,multiplication,0);
        
       return allCombos;
        
    }
    /**
     * return sorted rack word
     * @return
     */
    public String getSortedRackWord() {
        
        return rackWordSorted;
    }

   /**
    * Finds all subsets of the multiset starting at position k in unique and mult.
    * unique and mult describe a multiset such that mult[i] is the multiplicity of the char
    *      unique.charAt(i).
    * PRE: mult.length must be at least as big as unique.length()
    *      0 <= k <= unique.length()
    * @param unique a string of unique letters
    * @param mult the multiplicity of each letter from unique.  
    * @param k the smallest index of unique and mult to consider.
    * @return all subsets of the indicated multiset
    * @author Claire Bono
    */
   private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
      ArrayList<String> allCombos = new ArrayList<>();
      
      if (k == unique.length()) {  // multiset is empty
         allCombos.add("");
         return allCombos;
      }
      
      // get all subsets of the multiset without the first unique char
      ArrayList<String> restCombos = allSubsets(unique, mult, k+1);
      
      // prepend all possible numbers of the first char (i.e., the one at position k) 
      // to the front of each string in restCombos.  Suppose that char is 'a'...
      
      String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...
      for (int n = 0; n <= mult[k]; n++) {   
         for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets 
                                                        // we found in the recursive call
            // create and add a new string with n 'a's in front of that subset
            allCombos.add(firstPart + restCombos.get(i));  
         }
         firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
      }
      
      return allCombos;
   }
   /**
    * compute the information of the rack word: replication and multiplication
    * @param inputString string word contained in the rack
    * @return rack information map
    */
   private TreeMap<Character, Integer> getRackInfo(String inputString){
       
       TreeMap<Character,Integer> rackInfoMap = new TreeMap<Character, Integer>();
       /**
        * get information of the rack word: each unique character and their occurence
        * store in the map
        */
       for(int i = 0; i < inputString.length(); i++) {
           
           if(rackInfoMap.containsKey(inputString.charAt(i)) == false) {
                 rackInfoMap.put(inputString.charAt(i), 1);   
           }
           else {
                 int replication = rackInfoMap.get(inputString.charAt(i));
                 replication++;
                 rackInfoMap.put(inputString.charAt(i), replication); 
           }
           
        } 
       return rackInfoMap;
   }
   
}
