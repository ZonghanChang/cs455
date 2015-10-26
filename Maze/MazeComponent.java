// Name:
// USC loginid:
// CS 455 PA3
// Fall 2015

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.JComponent;


/**
   MazeComponent class
   
   A component that displays the maze and a path through it if one has been found.
*/
public class MazeComponent extends JComponent
{
   private Maze maze;
   
   private static final int START_X = 10; // where to start drawing maze in frame
   private static final int START_Y = 10;
   
   private static final int BOX_WIDTH = 20;  // width and height of one maze unit
   private static final int BOX_HEIGHT = 20;
   

   /**
      Constructs the component.
      @param maze   the maze to display
   */
   public MazeComponent(Maze maze) 
   {      
	   this.maze = maze;
   }

   
   /**
     Draws the current state of maze including a path through it if one has
     been found.
     @param g the graphics context
   */
   public void paintComponent(Graphics g)
   {
		Graphics2D g2 = (Graphics2D) g;
		Line2D.Double segment = new Line2D.Double();
		for(int i = 0;i < maze.numRows();i++){
			for(int j = 0;j < maze.numCols();j++){
				if(maze.hasWallAt(new MazeCoord(i,j))){
					g2.setColor(Color.BLACK);
				}
				else{
					g2.setColor(Color.WHITE);
					if(i == maze.numRows() - 1 && j == maze.numCols() - 1){
						g2.setColor(Color.GREEN);
					}
				}
				
				Rectangle cell = new Rectangle(START_X + BOX_WIDTH * j,START_Y + BOX_HEIGHT * i,
											   BOX_WIDTH,BOX_HEIGHT);
				g2.fill(cell);
				
			}
		}
		
		LinkedList<MazeCoord> path = maze.getPath();
		ListIterator<MazeCoord> iter = path.listIterator();
		MazeCoord start = null,end=null;
		g2.setColor(Color.BLUE);
		if(iter.hasNext()){
			start = iter.next();
		}
		while(iter.hasNext()){
			end = iter.next();
			segment.setLine(BOX_WIDTH + start.getCol() * BOX_WIDTH, BOX_HEIGHT + start.getRow() * BOX_HEIGHT,
					        BOX_WIDTH + end.getCol() * BOX_WIDTH, BOX_HEIGHT + end.getRow() * BOX_HEIGHT);
			g2.draw(segment);
			start = end;
		}
   }
   
}



