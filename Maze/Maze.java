//Name:Zonghan Chang
//USC loginid:zonghanc
//CS 455 PA3
//Fall 2015


import java.util.LinkedList;


/**
   Maze class

   Stores information about a maze and can find a path through the maze
   (if there is one).

   Assumptions about structure of the mazeData (parameter to constructor), and the
   path:
     -- no outer walls given in mazeData -- search assumes there is a virtual 
        border around the maze (i.e., the maze path can't go outside of the maze
        boundaries)
     -- start location for a path is maze coordinate (START_SEARCH_ROW,
        START_SEARCH_COL) (constants defined below)
     -- exit loc is maze coordinate (numRows()-1, numCols()-1) 
           (methods defined below)
     -- mazeData input 2D array of booleans, where true means there is a wall
        at that location, and false means there isn't (see public FREE / WALL 
        constants below) 
     -- in mazeData the first index indicates the row. e.g., mazeData[row][col]
     -- only travel in 4 compass directions (no diagonal paths)
     -- can't travel through walls
 */

public class Maze {
   
   public static final int START_SEARCH_COL = 0;
   public static final int START_SEARCH_ROW = 0;
   
   public static final boolean FREE = false;
   public static final boolean WALL = true;
   
   private boolean[][] maze;
   private boolean[][] visited;
   private LinkedList<MazeCoord> path;
   /**
      Constructs a maze.
      @param mazeData the maze to search.  See general Maze comments for what
      goes in this array.
    */
   public Maze(boolean[][] mazeData)
   {
      maze = new boolean[mazeData.length][mazeData[0].length];
      visited = new boolean[mazeData.length][mazeData[0].length];
      
      for(int i = 0;i < mazeData.length;i++){
    	  for(int j = 0;j < mazeData[0].length;j++){
    		  maze[i][j] = mazeData[i][j];
    		  visited[i][j] = false;
    	  }
      }
      path = new LinkedList<MazeCoord>();
   }


   /**
   Returns the number of rows in the maze
   @return number of rows
   */
   public int numRows() {
      return maze.length;   // DUMMY CODE TO GET IT TO COMPILE
   }

   /**
   Returns the number of columns in the maze
   @return number of columns
   */   
   public int numCols() {
      return maze[0].length;   // DUMMY CODE TO GET IT TO COMPILE
   } 
 
   
   /**
      Returns true iff there is a wall at this location
      @param loc the location in maze coordinates
      @return whether there is a wall here
      PRE: 0 <= loc.getRow() < numRows() and 0 <= loc.getCol() < numCols()
   */
   public boolean hasWallAt(MazeCoord loc) {
      return maze[loc.getRow()][loc.getCol()];   
   }

   
   /**
      Returns path through the maze. First element is starting location, and
      last element is exit location.  If there was not a path, or if this is called
      before search, returns empty list.

      @return the maze path
    */
   public LinkedList<MazeCoord> getPath() {
      return path;  
   }


   /**
      Find a path through the maze if there is one.
      @return whether path was found.
    */
   public boolean search()  {      
      
      return recursiveSearch(numRows() - 1, numCols() - 1);  
      
   }
   private boolean recursiveSearch(int row,int col){
	   if(row >= numRows() || col >= numCols() || row < 0 || col < 0){
		   return false;
	   }
	   // Check if the start point or end point has been visited
	   // Start point visited means the maze has been searched and there is a path
	   // End point visited means the maze has been searched and there is no path
	   if(visited[0][0] == true){
		   return true;
	   }
	   if(visited[row][col]){
		   return false;
	   }

	   if(maze[row][col] == WALL){
		   return false;
	   }
	   
	   visited[row][col] = true;
	   // There is a path
	   if((row == 0 && col == 0)){
		   path.add(new MazeCoord(row,col));
		   return true;
	   }
	   
	   boolean up = recursiveSearch(row - 1, col);
	   boolean left = recursiveSearch(row, col - 1);
	   boolean down = recursiveSearch(row + 1, col);
	   boolean right = recursiveSearch(row, col + 1);
	   
	   boolean hasPath = up || left || down || right;
	   if(hasPath){
		   path.add(new MazeCoord(row,col));
	   }
	   return hasPath;
   }

}
