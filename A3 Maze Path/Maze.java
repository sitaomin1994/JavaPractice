// Name: Sitao Min
// USC NetID: sitaomin
// CS 455 PA3
// Spring 2018

import java.util.LinkedList;


/**
   Maze class

   Stores information about a maze and can find a path through the maze
   (if there is one).
   
   Assumptions about structure of the maze, as given in mazeData, startLoc, and endLoc
   (parameters to constructor), and the path:
     -- no outer walls given in mazeData -- search assumes there is a virtual 
        border around the maze (i.e., the maze path can't go outside of the maze
        boundaries)
     -- start location for a path is maze coordinate startLoc
     -- exit location is maze coordinate exitLoc
     -- mazeData input is a 2D array of booleans, where true means there is a wall
        at that location, and false means there isn't (see public FREE / WALL 
        constants below) 
     -- in mazeData the first index indicates the row. e.g., mazeData[row][col]
     -- only travel in 4 compass directions (no diagonal paths)
     -- can't travel through walls

 */

public class Maze {
   
   public static final boolean FREE = false;
   public static final boolean WALL = true;
   public static final boolean VISITED = true;
   public static final boolean NOT_VISITED = false;
   
  /**
   * instance variable
   */
   /**
    * Representation invariant:
    * 1. startLoc is the location in maze start to search
    *    0 =< startLoc.getRow() <= numRows-1, 0 <= startLoc.getCol() <= numCols-1
    * 2. exitLoc is the destination of the maze out, the end of the search
    *      0 =< exitLoc.getRow() <= numRows-1, 0 <= exitLoc.getCol() <= numCols-1
    * 3. mazeData contains all maze unit information
    *    mazeData.length is the number of rows of maze units, its >=1
    *    mazeData[0].length is the number of columns of maze unites, its >=1
    *    mazeData[i][j] for i in range(0, numRows), j in range(0,numCols) denote whether there is a wall at (ith row,jth colunm), true for there is a wall , false for no wall
    * 4. numRows is the number of Rows of the maze, numRows >=1 
    * 5. numCols is the number of Columns of the maze, numCols >= 1
    * 6. path the an LinkedList to store the mazecoord which belong to the maze solution path 
    */
   private boolean[][] mazeData; //maze intent data
   private int numRows;          //number of rows
   private int numCols;          //number of columns
   private MazeCoord startLoc;   //start location
   private MazeCoord exitLoc;    //exit location
   private LinkedList<MazeCoord> path;   //linkedlist to store the path
   /**
      Constructs a maze.
      @param mazeData the maze to search.  See general Maze comments above for what
      goes in this array.
      @param startLoc the location in maze to start the search (not necessarily on an edge)
      @param exitLoc the "exit" location of the maze (not necessarily on an edge)
      PRE: 0 <= startLoc.getRow() < mazeData.length and 0 <= startLoc.getCol() < mazeData[0].length
         and 0 <= endLoc.getRow() < mazeData.length and 0 <= endLoc.getCol() < mazeData[0].length

    */
   public Maze(boolean[][] mazeData, MazeCoord startLoc, MazeCoord exitLoc) {
       
       this.mazeData = mazeData;        // maze data
       numRows = mazeData.length;       // maze length
       numCols = mazeData[0].length;    // maze width
       this.startLoc = startLoc;        
       this.exitLoc = exitLoc;
       path = new LinkedList<>();
   }


   /**
      Returns the number of rows in the maze
      @return number of rows
   */
   public int numRows() {
       
      return numRows;  
      
   }

   
   /**
      Returns the number of columns in the maze
      @return number of columns
   */   
   public int numCols() {
       
      return numCols;  
      
   } 
 
   
   /**
      Returns true iff there is a wall at this location
      @param loc the location in maze coordinates
      @return whether there is a wall here
      PRE: 0 <= loc.getRow() < numRows() and 0 <= loc.getCol() < numCols()
   */
   public boolean hasWallAt(MazeCoord loc) {
      if(mazeData[loc.getRow()][loc.getCol()] == WALL) {
          return true;
      }
      else {
          return false;
      }
      
   }
   

   /**
      Returns the entry location of this maze.
    */
   public MazeCoord getEntryLoc() {
      return startLoc;
   }
   
   
   /**
     Returns the exit location of this maze.
   */
   public MazeCoord getExitLoc() {
      return exitLoc;
   }

   
   /**
      Returns the path through the maze. First element is start location, and
      last element is exit location.  If there was not path, or if this is called
      before a call to search, returns empty list.

      @return the maze path
    */
   public LinkedList<MazeCoord> getPath() {

      return path;   

   }


   /**
      Find a path from start location to the exit location (see Maze
      constructor parameters, startLoc and exitLoc) if there is one.
      Client can access the path found via getPath method.

      @return whether a path was found.
    */
   public boolean search()  { 
      /*
       * vistited is an array mark the node which is already visited
       */
      boolean[][] visited = new boolean[numRows][numCols];
      
      /*
       * initialize vistied[][] to false: all nodes haven't been visited before the search
       */
      for(int i = 0; i< numRows;i++) {
              for(int j = 0; j < numCols; j++) {
                  visited[i][j] = NOT_VISITED;
              }
      }
     /*
      *  using help method findpath() to find the path
      */
      boolean result = findPath(startLoc, visited);
      return result; 

   }
   /**
    * helper function for search method, to find a path form start location to exit location
    * @param loc : location of the mazecoord
    * @param visited: to mark the node which has been visited or not
    * @return whether there is a path from current location to exit location
    */
    private boolean findPath(MazeCoord loc, boolean[][] visited) {
            
            
            int col = loc.getCol();    // get column of current location
            int row = loc.getRow();    // get row of current location
            /*
             * if current location is a wall, return false
             */
            if(hasWallAt(loc) == WALL) {
                return false;
            }
            /*
             * if current location is visited before, return false
             */
            else if (visited[row][col] == VISITED) {
                return false;
            }
            /*
             * if current location is exit location, denote there is desination, add this location to path and return true
             */
            else if(loc.equals(exitLoc)) {
                visited[row][col] = VISITED;
                path.addFirst(loc);
                return true;
            }
            /*
             * if not the base cases, recursively run the method to the neighbor of current location
             */
            else {
                
                visited[row][col] = VISITED;                     //mark current location visited
                boolean upSign = goUp(row,col,visited);       //search upper location
                boolean rightSign = goRight(row,col,visited); //search right location
                boolean downSign = goDown(row,col,visited);     //search down location
                boolean leftSign = goLeft(row,col,visited);     //search left location
                /*
                 * if one of the neighbor have path to the desination, current location return true and add current location to the path
                 */
                if(leftSign == true || rightSign == true || upSign == true || downSign == true) {                    
                    path.addFirst(loc); 
                    return true;
                }
                /*
                 *  if all neighbor cannot find path, return false
                 */
                else {
                    return false;
                }
            }
    }
    /**
     * check the upper neighbor of current location has path or not
     * @param row row coordination of current location
     * @param col column coordination of current location
     * @param visited mark location which is already visited
     * @return whether the up location can find a path or not
     */
    private boolean goUp(int row, int col, boolean[][] visited) {
            /*
             * if current location is at boundary, up location return false
             */
            if(row == 0) {
                return false;
            }
            /*
             * or search up location
             */
            else {
                MazeCoord upCoord = new MazeCoord(row-1,col);
                return findPath(upCoord,visited);
            }
    }
    /**
     * check whether right neighbor of current location has a path or not
     * @param row row coordination of current location
     * @param col column coordination of current location
     * @param visited mark location which is already visited
     * @return whether the right location can find a path or not
     */
    
    private boolean goRight(int row, int col, boolean[][] visited) {
            /*
         * if current location is at boundary, right location return false
         */
        if(col == numCols-1) {
            return false;
        }
        /*
         * or search right location
         */
        else {
            MazeCoord rightCoord = new MazeCoord(row,col+1);
            return findPath(rightCoord,visited);
        }
    }
    /**
     * check down neighbor of current location has a path or not
     * @param row row coordination of current location
     * @param col column coordination of current location
     * @param visited mark location which is already visited
     * @return whether the down location can find a path or not
     */
    private boolean goDown(int row, int col, boolean[][] visited) {
            /*
         * if current location is at boundary, down location return false
         */
        if(row == numRows-1) {
            return false;
        }
        /*
         * or search down location
         */
        else {
            MazeCoord downCoord = new MazeCoord(row+1,col);
            return findPath(downCoord,visited);
        }
    }
    /**
     * check left neighbor of current location has a path or not
     * @param row row coordination of current location
     * @param col column coordination of current location
     * @param visited mark location which is already visited
     * @return whether the left location can find a path or not
     */
    private boolean goLeft(int row, int col, boolean[][] visited) {
            /*
         * if current location is at boundary, left location return false
         */
        if(col == 0) {
            return false;
        }
        /*
         * or search left location
         */
        else {
            MazeCoord leftCoord = new MazeCoord(row,col-1);
            return findPath(leftCoord,visited);
        }
    }

}

     
