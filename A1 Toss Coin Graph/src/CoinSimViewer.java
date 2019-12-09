// Name:Sitao Min
// USC NetID:2109923078
// CS 455 PA1
// Spring 2018
import java.util.Scanner;
import javax.swing.JFrame;
/**
 * CoinSimViewer class
 * Prompts for the number of trials.
 * And creates the JFrame containing the CoinSimComponent. 
 */
public class CoinSimViewer {
	
	private static Scanner in;

	public static void main(String[] args) {
		
		/**
	      Prompts for inputing number of trials 
	    */
		in = new Scanner(System.in);
		System.out.print("Enter number of trials:");
		int numberTrials = in.nextInt();
		/**
	      Do error checking of input data
	    */
		while(numberTrials<=0)
		{
			System.out.printf("ERROR:Number entered must be greater than 0 .\n");
			System.out.print("Enter number of trials:");
			numberTrials = in.nextInt();
		}
			 
		/**
	      Creates frame to show the bar plot.
	    */
		JFrame frame = new JFrame();
		frame.setSize(800,500);
		frame.setTitle("Toss simulartor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/**
	      Creates component to accept number of trials, run experiments and draw the bars.
	    */
		CoinSimComponent component  = new CoinSimComponent(numberTrials);
		frame.add(component);
		
		frame.setVisible(true);
		
	}

}