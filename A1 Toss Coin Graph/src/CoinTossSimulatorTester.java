// Name:Sitao Min
// USC NetID:2109923078
// CS 455 PA1
// Spring 2018
/**
 * class CoinTossSimulatorTest
 * 
 * A Class to test your CoinTossSimulator class independently from its use in the CoinSimViewer program.
 * We test all methods display in CoinTossSimulator class
 *  
 * This class is a console-based program -- i.e., no GUI. 
 * This class is a non-interactive program (i.e., fixed data, nothing read in from the user),
 * we just print informative output to the console with the results of each operation.
 * Because of the random nature of the program, we cannot test the result of getHeadTail(), getTwoHeads(),etc.
 * Instead, we test that the sum of the number of two-head tosses, two-tail tosses, and head-tail tosses add up to the total number of trials. 
 * Invariant: getNumTrials() = getTwoHeads() + getTwoTails() + getHeadTails()
 * 
 */
public class CoinTossSimulartorTest 
{
	
	CoinTossSimulator simulator = new CoinTossSimulator();
	
	public static void main(String[] args) 
	{
		
		// test constructor, getNumtrials(),getTwoHeads(),getTwoTails(),getHeadTails()
		System.out.println("After constructor:");
		CoinTossSimulator simulator = new CoinTossSimulator();
		System.out.println("Number of trials [exp:0] :"+ simulator.getNumTrials());
		System.out.println("Two-head tosses :"+ simulator.getTwoHeads());
		System.out.println("Two-tail tosses :"+ simulator.getTwoTails());
		System.out.println("One_head one-tail tosses :"+ simulator.getHeadTails());
		if(simulator.getNumTrials() == (simulator.getTwoHeads()+simulator.getTwoTails()+simulator.getHeadTails()))
		{
			System.out.println("Tosses add up correctly? "+ "true");

		}
		else
		{
			System.out.println("Tosses add up correctly? "+ "false");
		}
		System.out.println("");
		
		// test run(1),getNumtrials(),getTwoHeads(),getTwoTails(),getHeadTails()
		System.out.println("After run(1):");
		simulator.run(1);
		System.out.println("Number of trials [exp:1] :"+ simulator.getNumTrials());
		System.out.println("Two-head tosses :"+ simulator.getTwoHeads());
		System.out.println("Two-tail tosses :"+ simulator.getTwoTails());
		System.out.println("One_head one-tail tosses :"+ simulator.getHeadTails());
		if(simulator.getNumTrials() == (simulator.getTwoHeads()+simulator.getTwoTails()+simulator.getHeadTails()))
		{
			System.out.println("Tosses add up correctly? "+ "true");

		}
		else
		{
			System.out.println("Tosses add up correctly? "+ "false");
		}
		System.out.println("");
		
		// test run(10),getNumtrials(),getTwoHeads(),getTwoTails(),getHeadTails()
		System.out.println("After run(10):");
		simulator.run(10);
		System.out.println("Number of trials [exp:11] :"+ simulator.getNumTrials());
		System.out.println("Two-head tosses :"+ simulator.getTwoHeads());
		System.out.println("Two-tail tosses :"+ simulator.getTwoTails());
		System.out.println("One_head one-tail tosses :"+ simulator.getHeadTails());
		if(simulator.getNumTrials() == (simulator.getTwoHeads()+simulator.getTwoTails()+simulator.getHeadTails()))
		{
			System.out.println("Tosses add up correctly? "+ "true");

		}
		else
		{
			System.out.println("Tosses add up correctly? "+ "false");
		}
		System.out.println("");
		
		// test run(100),getNumtrials(),getTwoHeads(),getTwoTails(),getHeadTails()
		System.out.println("After run(100):");
		simulator.run(100);
		System.out.println("Number of trials [exp:111] :"+ simulator.getNumTrials());
		System.out.println("Two-head tosses :"+ simulator.getTwoHeads());
		System.out.println("Two-tail tosses :"+ simulator.getTwoTails());
		System.out.println("One_head one-tail tosses :"+ simulator.getHeadTails());
		if(simulator.getNumTrials() == (simulator.getTwoHeads()+simulator.getTwoTails()+simulator.getHeadTails()))
		{
			System.out.println("Tosses add up correctly? "+ "true");

		}
		else
		{
			System.out.println("Tosses add up correctly? "+ "false");
		}
		System.out.println("");
		
		// test run(2000),getNumtrials(),getTwoHeads(),getTwoTails(),getHeadTails()
		System.out.println("After run(2000):");
		simulator.run(2000);
		System.out.println("Number of trials [exp:2111] :"+ simulator.getNumTrials());
		System.out.println("Two-head tosses :"+ simulator.getTwoHeads());
		System.out.println("Two-tail tosses :"+ simulator.getTwoTails());
		System.out.println("One_head one-tail tosses :"+ simulator.getHeadTails());
		if(simulator.getNumTrials() == (simulator.getTwoHeads()+simulator.getTwoTails()+simulator.getHeadTails()))
		{
			System.out.println("Tosses add up correctly? "+ "true");

		}
		else
		{
			System.out.println("Tosses add up correctly? "+ "false");
		}
		System.out.println("");

		
		
		// test reset(),getNumtrials(),getTwoHeads(),getTwoTails(),getHeadTails()
		System.out.println("After reset:");
		simulator.reset();
		System.out.println("Number of trials [exp:0] :"+ simulator.getNumTrials());
		System.out.println("Two-head tosses :"+ simulator.getTwoHeads());
		System.out.println("Two-tail tosses :"+ simulator.getTwoTails());
		System.out.println("One_head one-tail tosses :"+ simulator.getHeadTails());
		if(simulator.getNumTrials() == (simulator.getTwoHeads()+simulator.getTwoTails()+simulator.getHeadTails()))
		{
			System.out.println("Tosses add up correctly? "+ "true");

		}
		else
		{
			System.out.println("Tosses add up correctly? "+ "false");
		}
		System.out.println("");
		
		// test run(1000) getNumtrials(),getTwoHeads(),getTwoTails(),getHeadTails()
		System.out.println("After run(1000):");
		simulator.run(1000);
		System.out.println("Number of trials [exp:1000] :"+ simulator.getNumTrials());
		System.out.println("Two-head tosses :"+ simulator.getTwoHeads());
		System.out.println("Two-tail tosses :"+ simulator.getTwoTails());
		System.out.println("One_head one-tail tosses :"+ simulator.getHeadTails());
		if(simulator.getNumTrials() == (simulator.getTwoHeads()+simulator.getTwoTails()+simulator.getHeadTails()))
		{
			System.out.println("Tosses add up correctly? "+ "true");

		}
		else
		{
			System.out.println("Tosses add up correctly? "+ "false");
		}
		System.out.println("");
		
		// test run(1000) getNumtrials(),getTwoHeads(),getTwoTails(),getHeadTails()
		System.out.println("After run(1000):");
		simulator.run(1000);
		System.out.println("Number of trials [exp:2000] :"+ simulator.getNumTrials());
		System.out.println("Two-head tosses :"+ simulator.getTwoHeads());
		System.out.println("Two-tail tosses :"+ simulator.getTwoTails());
		System.out.println("One_head one-tail tosses :"+ simulator.getHeadTails());
		if(simulator.getNumTrials() == (simulator.getTwoHeads()+simulator.getTwoTails()+simulator.getHeadTails()))
		{
				System.out.println("Tosses add up correctly? "+ "true");

		}
		else
		{
				System.out.println("Tosses add up correctly? "+ "false");
		}
		System.out.println("");
			
	} 
}
	
	    

