// Name:Sitao Min
// USC NetID:sitaomin
// CSCI455 PA2
// Spring 2018

import java.util.ArrayList;
import java.util.Scanner;
/**
 * class SolitaireBoardSimulator
   A main program that does a Bulgarian Solitaire Simulation.
   user should use different argument for playing in different mode
   (See comments below next to named constant declarations for more details on this.)
 */ 
public class BulgarianSolitaireSimulator {
	
	/**
	* main process to play the Solitaire game
	*  @param args, the argument for the Solitaire Game mode 
	* -u USER MODE:user should input the initial cards own
	* -s SINGLE STEP MODE: play the game round by round 
	*/
	public static void main(String[] args) 
	{
      
      boolean singleStep = false;
      boolean userConfig = false;
      Scanner in = new Scanner(System.in);
      /**
       * check argument
       */
      for (int i = 0; i < args.length; i++) 
      {
         if (args[i].equals("-u"))
         {
            userConfig = true;
         }
         else if (args[i].equals("-s")) 
         {
            singleStep = true;
         }
      }
      
      /**
       *  generate initial configuration
       *  if in user mode, prompt for an initial configuration
       *  if not in user mode, generate a random configuration
       */
      SolitaireBoard newGame;
      
      if(userConfig == true)
      {
    	  	System.out.println("Number of total cards is "+ SolitaireBoard.CARD_TOTAL);
    	  	System.out.println("You will be entering the initial configuration of the cards (i.e., how many in each pile).");
    	    ArrayList<Integer> initialConfig = new ArrayList<Integer>();
    	    initialConfig = UserInput(in);
    	  	newGame = new SolitaireBoard(initialConfig);
      }
      else 
      {
    	  	newGame = new SolitaireBoard();
      }
      
      /**
       * start the game one by one round
       * if in singeStep mode, prompt for press <RETURN> to continue next step
       * if not in singleStep mode, play the game automate until game end
       */
    	  RunGame(newGame,in, singleStep);     
    	  in.close();
      
	}
    
    // <add private static methods here>
	/**
    * Prompt for input of user initial configuration of cards 
    * @return user input of ArrayList type
    */
	private static ArrayList<Integer> UserInput(Scanner in)
    {
	   ArrayList<Integer> input = new ArrayList<Integer>();         // store user input card value	   
	   boolean hasError = true;                                     // to check whether input has error or not
	   boolean notAllInt;                                           // to check whether input has other type except integer
	   boolean hasInvalidValue = false;							   // to check whether input value is valid
	   boolean incorrectTotalCards;								   // to check whether input cards total number is correct or not
	   int nextInputValue;                                          // store nextInputValue
	   int cardTotal = 0;                                           // sum of inputs
	   
	    //prompts for input with no error
	   while(hasError) 
	   {		   
		   cardTotal = 0;
		   System.out.println("Please enter a space-separated list of positive integers followed by newline:");
		   String line = in.nextLine();                              // scan user input line
		   Scanner lineScanner = new Scanner(line);                 // scan input by each word
		   // check whether has other input type
		   notAllInt = hasOtherType(line);
		   //read input number one by one
		   while(lineScanner.hasNextInt()) 
		   {
		    		nextInputValue =  lineScanner.nextInt();
		    		// check whether input value is valid or not
		    		hasInvalidValue = IsInvalidInput(nextInputValue);	
		    		if(hasInvalidValue == true) {
		    			break;
		    		}
		    		input.add(nextInputValue);
		    		cardTotal += nextInputValue; 
		    	}		
		   	// check input total card number is valid or not
		    incorrectTotalCards = IsIncorrectTotalCards(cardTotal);
		    // check whether has error input or not
		    if(incorrectTotalCards || notAllInt || hasInvalidValue)          // if has error, clear the input arraylist and prompt again
		    {
		    	   hasError = true;
		    	   System.out.println("ERROR: Each pile must have at least one card and the total number of cards must be 45");
		    	   input.clear();
		    }
		    else                                                             // if has no error, end while loop
		    {
		    	   hasError = false;
		    }
		    // close line scanner
		    lineScanner.close();                  
	   }   
	   return input; 
    }
  
    /**
    * Run SolitaireBoard game round by round
    * @param game :SolitaireBoard game object, using to run game
    * @param in :Scanner, using to read <return>
    * @param singleStep: program argument: singStep is true means it will stop each round
    */
	private static void RunGame(SolitaireBoard game, Scanner in, boolean singleStep)
	{
	   
	   System.out.println("Initial configuration: "+game.configString());
	   //run game rounds until finished
	   int numRound = 0;
	   while(!game.isDone())
	   {
		   game.playRound();
		   numRound++;
		   System.out.println("["+numRound+"] Current configuration: "+game.configString());
		   if(singleStep == true)                    // if user use singleStep argument, each round press <return> to continue
		   {
			   System.out.print("<Type return to continue>");
			   in.nextLine();
		   }
	   }
	   System.out.println("Done!");
	}
   
    /**
    * check whether input line has other type except integer
    * @param line : input line
    * @return boolean: result: true -> has other type, false -> has no other type
    */
	private static boolean hasOtherType(String line)
	{
	   boolean result;
	   Scanner lineScanner1 = new Scanner(line);                 // scan input by each word
	   Scanner lineScanner2 = new Scanner(line);                 // scan input by each integer
	   int inputSize = 0;      								   // store input size
	   int inputIntSize = 0;										// store input size of Integer
	   //compare two different read method length, if length is equal to each other, means no other type input
	   while(lineScanner1.hasNext())
	   {
		   lineScanner1.next();
		   inputSize ++;
	   }
		   
	   while(lineScanner2.hasNextInt())
	   {
	    		lineScanner2.nextInt();
	    		inputIntSize ++;
	   }
	   
	   if(inputSize == inputIntSize)                   // if two input size equals, no other type input exists                         
	   {
		   result = false;
	   }
	   else                                           // if two input size not equals, there exists some other input type
	   {
		   result = true;
	   }
	   lineScanner1.close();
	   lineScanner2.close();
	   
	   return result;
	}
   
    /**
    * check input value is valid or not
    * @param value: input value
    * @return result: true -> invalid, false -> valid
    */
    private static boolean IsInvalidInput(int value)
	{
		boolean result;
		if(value <= 0 || value > SolitaireBoard.CARD_TOTAL)  
		{
			result = true;
		}
		else	
		{
			result = false;
		}
		return result;
	}
    
    /**
    * check total cards number is correct or not
    * @param value: total cards
    * @return result: true -> total cards number incorrect, false -> total cards number correct
    */
    private static boolean IsIncorrectTotalCards(int value)
	{
		boolean result;
		if(value == SolitaireBoard.CARD_TOTAL)  
		{
			result = false;
		}
		else	
		{
			result = true;
		}
		return result;
	}
    
}

