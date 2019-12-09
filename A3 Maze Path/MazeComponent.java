// Name:Sitao Min
// USC NetID: sitaomin
// CS 455 PA3
// Spring 2018

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.ListIterator;

/**
   MazeComponent class
   
   A component that displays the maze and path through it if one has been found.
*/
public class MazeComponent extends JComponent
{

   private static final int START_X = 10;     // top left of corner of maze in frame
   private static final int START_Y = 10;
   private static final int INSET_OUTLINE = 1;
   private static final int OUTLINE_X = START_X - INSET_OUTLINE;
   private static final int OUTLINE_Y = START_Y - INSET_OUTLINE;
   private static final int BOX_WIDTH = 20;   // width and height of one maze "location"
   private static final int BOX_HEIGHT = 20;
   private static final int INSET = 2;        // how much smaller on each side to make entry/exit inner box
   private static final Color OUTLINE_COLOR = Color.BLACK;
   private static final Color WALL_COLOR  = Color.BLACK;
   private static final Color BOX_COLOR = Color.WHITE;
   private static final Color START_POINT_COLOR = Color.YELLOW;
   private static final Color END_POINT_COLOR = Color.GREEN;
   private static final Color LINE_COLOR = Color.BLUE;
   
   /**
    * instance viriable
    */
   private Maze maze;   // the entity of maze 
   
   /**
      Constructs the component.
      @param maze   the maze to display
   */
   public MazeComponent(Maze maze) 
   {   
      this.maze = maze;
   }

   
   /**
     Draws the current state of maze including the path through it if one has
     been found.
     @param g the graphics context
   */
   public void paintComponent(Graphics g)
   {
       int numRows = maze.numRows();
       int numCols = maze.numCols();
       Graphics2D g2 = (Graphics2D) g;
       drawMaze(g2,numRows, numCols);
       drawPath(g2);
       
               
   }
   /**
    * draw the maze
    * @param g2  graphics context
    * @param numRows : number of rows
    * @param numCols : number of columns
    */
   private void drawMaze(Graphics2D g2, int numRows, int numCols) {
       
       /**
        * draw outline
        */
       Rectangle outline = new Rectangle(OUTLINE_X,OUTLINE_Y,numCols*BOX_WIDTH+2*INSET_OUTLINE,numRows*BOX_HEIGHT+2*INSET_OUTLINE);
       g2.setColor(OUTLINE_COLOR);
       g2.draw(outline);
       g2.fill(outline);
       /**
        * draw the maze
        */
       for(int i = 0; i< numRows; i++) {
           for(int j = 0; j< numCols;j++) {
               MazeCoord loc = new MazeCoord(i,j);
               /*
                *if maze location is a wall draw the black box 
                */
               if(maze.hasWallAt(loc) == true) {
                   Rectangle box = new Rectangle(START_X+j*BOX_WIDTH,START_Y+i*BOX_HEIGHT,BOX_WIDTH,BOX_HEIGHT);
                   g2.setColor(WALL_COLOR);
                   g2.fill(box);
                   g2.draw(box);
               }
               /*
                * if maze location is not a wall draw the white box
                */
               else if(maze.hasWallAt(loc) == false){
                   Rectangle box = new Rectangle(START_X+j*BOX_WIDTH,START_Y+i*BOX_HEIGHT,BOX_WIDTH,BOX_HEIGHT);
                   g2.setColor(BOX_COLOR);  
                   g2.fill(box);
                   g2.draw(box);
                   
               }
           }
       }
       /**
        * draw start and end point
        */
       int startX = maze.getEntryLoc().getCol();
       int startY = maze.getEntryLoc().getRow();
       int endX = maze.getExitLoc().getCol();
       int endY = maze.getExitLoc().getRow();
       Rectangle startPoint = new Rectangle(START_X+startX*BOX_WIDTH+INSET,START_Y+startY*BOX_HEIGHT+INSET,BOX_WIDTH-2*INSET,BOX_HEIGHT-2*INSET);
       g2.setColor(START_POINT_COLOR);
       g2.fill(startPoint);
       g2.draw(startPoint);
       Rectangle endPoint = new Rectangle(START_X+endX*BOX_WIDTH+INSET,START_Y+endY*BOX_HEIGHT+INSET,BOX_WIDTH-2*INSET,BOX_HEIGHT-2*INSET);
       g2.setColor(END_POINT_COLOR);
       g2.fill(endPoint);
       g2.draw(endPoint);
       
   }
   /**
    * draw the path
    * @param g2 : graphics context
    */
   private void drawPath(Graphics2D g2) {
       
       ListIterator<MazeCoord> iterator = maze.getPath().listIterator();
       /*
        * traverse path and draw the path 
        */
       if(iterator.hasNext()) {
           MazeCoord temp = iterator.next();   
           if(iterator.hasNext()) {
               while (iterator.hasNext()) {    
                   MazeCoord from = temp;
                   MazeCoord to   = iterator.next();
                   temp = to;
                   Point2D.Double fromLoc = new Point2D.Double(START_X+from.getCol()*BOX_WIDTH+BOX_WIDTH/2, START_X+from.getRow()*BOX_HEIGHT+BOX_HEIGHT/2); 
                   Point2D.Double toLoc = new Point2D.Double(START_X+to.getCol()*BOX_WIDTH+BOX_WIDTH/2, START_X+to.getRow()*BOX_HEIGHT+BOX_HEIGHT/2); 
                   Line2D.Double segment = new Line2D.Double(fromLoc, toLoc);
                   g2.setColor(LINE_COLOR);
                   g2.draw(segment);                  
                   }
           }
           else {
               Point2D.Double fromLoc = new Point2D.Double(START_X+temp.getCol()*BOX_WIDTH+BOX_WIDTH/2, START_X+temp.getRow()*BOX_HEIGHT+BOX_HEIGHT/2); 
               Point2D.Double toLoc = new Point2D.Double(START_X+temp.getCol()*BOX_WIDTH+BOX_WIDTH/2, START_X+temp.getRow()*BOX_HEIGHT+BOX_HEIGHT/2); 
               Line2D.Double segment = new Line2D.Double(fromLoc, toLoc);
               g2.setColor(LINE_COLOR);
               g2.draw(segment);    
           }
           
       }
       
   }
   
}



