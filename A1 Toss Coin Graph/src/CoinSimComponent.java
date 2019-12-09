// Name:Sitao Min
// USC NetID:2109923078
// CS 455 PA1
// Spring 2018
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Font;
import java.lang.Math;
/**
 * class CoinSimComponent
 * 
 * Constructor initializes any necessary data and runs the simulation. 
 * Overrides paintComponent to draw the bar graph, using Bar objects for each bar in the graph.
 *  This class uses the CoinTossSimulator and Bar class.
 * 
 */
public class CoinSimComponent extends JComponent {
	/**
	 * MAGIC NUMBERS
	 */
	public static final int BAR_WIDTH = 100;                   //bar width
	public static final int VERTICAL_BUFFER = 50;              //Tallest bar means the tallest possible bar you could draw
	public static final int PERCENT = 100;
	public static final Color TWO_HEADS_COLOR = Color.RED;     //color of two tails' bar
	public static final Color TWO_TAILS_COLOR = Color.BLUE;    //color of two heads' bar
	public static final Color HEAD_TAILS_COLOR = Color.GREEN;  //color of headtail's bar
	
	/**
	 * instance variables using in this class
	 */
	private int numTrials;           // total number of trials in experiment
	private int numTwoHeads;        // number of two heads
	private int numTwoTails;        // number of two tails
	private int numHeadTails;       // number of headtails
	private int numTotalTrials;
	
	/** 
     *Creates a CoinSimComponent which is to give a interface to run the simulator
     *and draw the bar in the plot
     *Run simulator and give result of how many two heads, two tails, headtial.
     *@param num_trials  numer of trials to proceed the coin toss experiments
	 */
	public CoinSimComponent(int numberTrials)
	{
		this.numTrials = numberTrials;
		CoinTossSimulator simulator  = new CoinTossSimulator();
		simulator.run(numTrials);
		numTwoHeads = simulator.getTwoHeads();
		numTwoTails = simulator.getTwoTails();
		numHeadTails = simulator.getHeadTails();
		numTotalTrials = simulator.getNumTrials();
		
	}
	/**
     *Draw the bars based on the result of the run simulation. 
     *@param g  the graphics context
	 */
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		/**
		 * computing how many pixels one units contains using component height
		 */
		int windowsHeight = getHeight();
		int windowsWidth = getWidth();
		int widthBetweenBar = (int)((windowsWidth-3*BAR_WIDTH)/4);
		/**
		 * two heads bar information initialization
		 */
		int barTwoHeadsBottom = windowsHeight-VERTICAL_BUFFER;
		int barTwoHeadsLeft = widthBetweenBar;
		int barTwoHeadsWidth = BAR_WIDTH;
		int barHeightTwoHeads = numTwoHeads;
		Color barColorTwoHeads = TWO_HEADS_COLOR;
		int percentTwoHeads = Math.round((float)(numTwoHeads*PERCENT)/(float)numTotalTrials);
		String labelTwoHeads = "Two Heads:"+ numTwoHeads+" ("+percentTwoHeads+"%)";
		/**
		 * head tails bar information initialization
		 */
		int barHeadTailBottom = windowsHeight-VERTICAL_BUFFER;
		int barHeadTailLeft = 2*widthBetweenBar + BAR_WIDTH;
		int barHeadTailWidth = BAR_WIDTH ;
		int barHeightHeadTail = numHeadTails;
		Color barColorHeadTail = HEAD_TAILS_COLOR;
		int percentHeadTail = Math.round((float)(numHeadTails*PERCENT)/(float)numTotalTrials);
		String labelHeadTail = "A Head and a Tail:"+ numHeadTails+" ("+percentHeadTail+"%)";
		/**
		 * two tails bar information initialization
		 */
		int barTwoTailsBottom = windowsHeight-VERTICAL_BUFFER;
		int barTwoTailsLeft = 3*widthBetweenBar+2*BAR_WIDTH;
		int barTwoTailsWidth = BAR_WIDTH ;
		int barHeightTwoTails = numTwoTails;
		Color barColorTwoTails = TWO_TAILS_COLOR;
		int percentTwoTails = 100- percentHeadTail - percentTwoHeads;
		String labelTwoTails = "Two Tails:"+ numTwoTails+" ("+percentTwoTails+"%)";
		
		Font font = g2.getFont();
		FontRenderContext context = g2.getFontRenderContext();
		Rectangle2D labelBounds = font.getStringBounds(labelTwoHeads, context);
		int labelHeight = (int)labelBounds.getHeight();
		double scale = (double)(windowsHeight-2*VERTICAL_BUFFER-labelHeight)/(double)numTotalTrials;
		/**
		 * create three bars using information computed above
		 */
		Bar barTwoHeads = new Bar(barTwoHeadsBottom, barTwoHeadsLeft, barTwoHeadsWidth, barHeightTwoHeads, scale, barColorTwoHeads, labelTwoHeads);
		Bar barHeadTail = new Bar(barHeadTailBottom, barHeadTailLeft, barHeadTailWidth, barHeightHeadTail, scale, barColorHeadTail, labelHeadTail);
		Bar barTwoTails = new Bar(barTwoTailsBottom, barTwoTailsLeft, barTwoTailsWidth, barHeightTwoTails, scale, barColorTwoTails, labelTwoTails);
		/**
		 * draw three bars in the component
		 */
		barTwoHeads.draw(g2);
		barHeadTail.draw(g2);
		barTwoTails.draw(g2);
		
	}

}
