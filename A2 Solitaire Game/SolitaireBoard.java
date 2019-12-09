// Name:Sitao Min
// USC NetID:sitaomin
// CSCI455 PA2
// Spring 2018

import java.util.ArrayList;
import java.util.Random;

/*
   class SolitaireBoard
   The board for Bulgarian Solitaire.  You can change what the total number of cards is for the game
   by changing NUM_FINAL_PILES, below.  Don't change CARD_TOTAL directly, because there are only some values
   for CARD_TOTAL that result in a game that terminates.
   (See comments below next to named constant declarations for more details on this.)
 */


public class SolitaireBoard {
   
   public static final int NUM_FINAL_PILES = 9;
   // number of piles in a final configuration
   // (note: if NUM_FINAL_PILES is 9, then CARD_TOTAL below will be 45)
   
   public static final int CARD_TOTAL = NUM_FINAL_PILES * (NUM_FINAL_PILES + 1) / 2;
   // bulgarian solitaire only terminates if CARD_TOTAL is a triangular number.
   // see: http://en.wikipedia.org/wiki/Bulgarian_solitaire for more details
   // the above formula is the closed form for 1 + 2 + 3 + . . . + NUM_FINAL_PILES
   // Note to students: you may not use an ArrayList -- see assgt description for details.
   
   public static final int MAX_CARDS_FINAL = 9;
   public static final int MIN_CARDS_FINAL = 1;
   // maximum and minimum number of cards can be appear in the final result
   // if number of cards above maximum or below minimum, the game will not be done   
   
   /**
    *Representation invariant:
    *1.numPiles is the number of piles, piles is an array to store different number of cards in different piles
    *2.The number of piles (numPiles) should be larger than 0 and smaller than CARD_TOTAL, 1 <= numPiles <= CARD_TOTAL;
    *3.Card values are stored in the piles[0] - piles[numPiles - 1] and The number of cards in each piles should be larger than 0 and smaller than CARD_TOTAL: 0 <= piles[i] <= CARD_TOTAL(0<=i<CARD_TOTAL)
	*4.Total number of1 cards should be always CARD_TOTAL, which means piles[0] + piles[1] + piles[2] + ...  piles[numPiles -1] = CARD_TOTAL
   */   
   // <add instance variables here>
   private Random generator;
   private int[] piles; 
   private int numPiles;
   
   /**
    *Creates a solitaire board with the configuration specified in piles.
    *piles has the number of cards in the first pile, then the number of cards in the second pile, etc.
    *PRE: piles contains a sequence of positive numbers that sum to SolitaireBoard.CARD_TOTAL
   */
   public SolitaireBoard(ArrayList<Integer> piles) 
   {
	   /**
	    * initialize card piles array
	    */
	  this.piles = new int[CARD_TOTAL];
	  numPiles = piles.size();
	  for(int i = 0; i< numPiles; i++)
	  {
		this.piles[i] = piles.get(i);  
	  }
      assert isValidSolitaireBoard();   // sample assert statement
   }
   
   /**
   *Creates a solitaire board with a random initial configuration.
   */
   public SolitaireBoard() 
   {
	   /**
	    * initialize card piles array with a random initial configuration
	    */
	  generator = new Random();
	  this.piles = new int[CARD_TOTAL];
	  numPiles = 0;
	  int restCards = CARD_TOTAL;                          //rest number of cards

	  /**
	   * assign cards to each pile
	   */
	  while(!isValidSolitaireBoard()) 
	  {
		  
		   numPiles++;
		   this.piles[numPiles-1] = generator.nextInt(restCards)+1;
		   restCards -= this.piles[numPiles-1];
	  }
	 	  
	  assert isValidSolitaireBoard();   // sample assert statement (you will be adding more of these calls)
   }
    
   /**
   *Plays one round of Bulgarian solitaire.  Updates the configuration according to the rules
   *of Bulgarian solitaire: Takes one card from each pile, and puts them all together in a new pile.
   *The old piles that are left will be in the same relative order as before, 
   *and the new pile will be at the end.
   */
   public void playRound() 
   {   
	   int newPileCards = 0;             //number of cards in new piles
	   int numOfZeros = 0;               //number of zeros card piles
	   int cursor = 0;					//a cursor for remove zero piles
	   /**
	      take one card from current piles and remove zeros cards piles
	    */
	   
	   for(int i=0; i< numPiles;i++)
	   {
		   /**
		      take one card from each piles
		    */
		   this.piles[i]--;
		   newPileCards++;
		   /**
		      remove piles with zero card piles
		    */
		   if(this.piles[i] == 0)
		   {
			   numOfZeros++;
		   }
		   else {
			   this.piles[cursor] = this.piles[i];
			   cursor++;
		   }
	   }
	   
	   numPiles -= numOfZeros;
	   
	   /**
	      insert a new pile
	    */
	   this.piles[numPiles] = newPileCards;
	   numPiles++;   
	   assert isValidSolitaireBoard();     // sample assert statement
	   	
   }
   
   /**
   *Returns true iff the current board is at the end of the game.  That is, there are NUM_FINAL_PILES
   *piles that are of sizes 1, 2, 3, . . . , NUM_FINAL_PILES, in any order.
   */   
   public boolean isDone() 
   {
      
	   boolean result = false;                                    // default assign to false
	   boolean[] cardNumCheckSign = new boolean[NUM_FINAL_PILES]; // check whether specific number of cards exists
	   for(int i=0;i < cardNumCheckSign.length;i++)
	   {
		   cardNumCheckSign[i] = false;                           // initialize with false
	   }
       /**
        * check the result
        */
	   if(numPiles == NUM_FINAL_PILES) 
	   {
		   // check each number of piles whether contain [1,2,3,4,5,6,7,8,9], if true, set sign to true
		   for(int i=0; i< numPiles; i++)
		   {
			   if(piles[i] >= MIN_CARDS_FINAL && piles[i] <= MAX_CARDS_FINAL)
			   {
				   cardNumCheckSign[piles[i]-1] = true;
			   }
		   }
		   // check whether all number of cards of [1,2,3,4,5,6,7,8,9] exist, if one is not, set result to false
		   result = true;
		   for(int i=0; i < cardNumCheckSign.length; i++)
		   {
			   if(cardNumCheckSign[i] == false)
			   {
				   result = false;
			   }
		   }
	   }
       
       assert isValidSolitaireBoard();
       return result;   
      
   }
   
   /**
   *Returns current board configuration as a string with the format of
   *a space-separated list of numbers with no leading or trailing spaces.
   *The numbers represent the number of cards in each non-empty pile.
   */
   public String configString() 
   {
	   
	  String s = "";    //change array to string
	  s +=  this.piles[0];
	  for(int i=1; i< numPiles; i++)
	  {
		s += " ";
		s += this.piles[i];
	  }
	  
	  assert isValidSolitaireBoard();
      return s;  
   }
  
   /**
   *Returns true iff the solitaire board data is in a valid state
   *(See representation invariant comment for more details.)
   */
   private boolean isValidSolitaireBoard() 
   {
      boolean result = false;
      boolean hasInvalidValue = false;    // sign to check whether number of piles is invalid value
      int cardTotal = 0;                  // number of cards in current round
      /**
       * calculate number of total cards 
       */
      for(int i = 0; i< numPiles; i++)
      {
    	  		cardTotal += this.piles[i];
    	  		if(this.piles[i] <= 0 || this.piles[i] > 45)
    	  		{
    	  			hasInvalidValue = true;
    	  		}
      }
      
      // check whether configuration of current round is right or not
      if(cardTotal == CARD_TOTAL && numPiles <= CARD_TOTAL &&(!hasInvalidValue))
      {
    	      result  = true;
      }
      
      return result;  

   }

}
