// Name:Sitao Min
// USC NetID:2109923078
// CS 455 PA1
// Spring 2018
import java.util.Random;
/**
 * class CoinTossSimulator
 * 
 * Simulates trials of repeatedly tossing two coins and allows the user to access the
 * cumulative results.
 * 
 * NOTE: we have provided the public interface for this class.  Do not change
 * the public interface.  You can add private instance variables, constants, 
 * and private methods to the class.  You will also be completing the 
 * implementation of the methods given. 
 * 
 * Invariant: getNumTrials() = getTwoHeads() + getTwoTails() + getHeadTails()
 * 
 */
public class CoinTossSimulator {
	
	/**
	 * instance variables using in this class
	 */
	private int HeadTails;                    // result number of HeadTails
	private int TwoTails;                     // result number of TwoTails
	private int TwoHeads;					 // result number of TwoHeads
	private int NumTrials;					 // total number of trails in this experiment
	private Random generator;				 // random number generator


   /**
      Constructor. Creates a coin toss simulator with no trials done yet.
   */
   public CoinTossSimulator() {
	   this.HeadTails = 0;
	   this.TwoTails = 0;
	   this.TwoHeads = 0;
	   this.NumTrials = 0;
	   generator = new Random();

   }


   /**
      Runs the simulation for numTrials more trials. Multiple calls to this method
      without a reset() between them *add* these trials to the current simulation.
      
      @param numTrials  number of trials to for simulation; must be >= 1
    */
   public void run(int numTrials) {
	   
	   NumTrials += numTrials;

	   /**
	      proceed NumTrials times tossing
	   */
	   for(int i=1;i<= numTrials;i++)
	   {
		   /**
		      Toss two times.
		   */
		  int firstToss = generator.nextInt(2);
		  int secondToss = generator.nextInt(2);
		   /**
		      Calculate results of each case.
		   */
		  if (firstToss == 0 && secondToss ==0)
		  {
			  TwoTails ++;
		  }
		  else if(firstToss ==1 && secondToss == 1)
		  {
			  TwoHeads ++;
		  }
		  else
		  {
			  HeadTails ++;
		  }
	   }  
	   
   }


   /**
      Get number of trials performed since last reset.
   */
   public int getNumTrials() 
   {
       
	   return NumTrials; 
   }


   /**
      Get number of trials that came up two heads since last reset.
   */
   public int getTwoHeads() 
   {
       
	   return TwoHeads; 
   }


   /**
     Get number of trials that came up two tails since last reset.
   */  
   public int getTwoTails() 
   {
       
	   return TwoTails; 
   }


   /**
     Get number of trials that came up one head and one tail since last reset.
   */
   public int getHeadTails() 
   {
       
	   return HeadTails; 
   }


   /**
      Resets the simulation, so that subsequent runs start from 0 trials done.
    */
   public void reset() 
   {
	   this.HeadTails = 0;
	   this.TwoTails = 0;
	   this.TwoHeads = 0;
	   this.NumTrials = 0;

   }

}
