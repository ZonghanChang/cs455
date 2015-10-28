//Name:Zonghan Chang
//USC loginid:zonghanc
//CS 455 PA3
//Fall 2015

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;


/**
 * MazeViewer class
 * 
 * Program to read in and display a maze and a path through the maze. At user
 * command displays a path through the maze if there is one.
 * 
 * How to call it from the command line:
 * 
 *      java MazeViewer mazeFile
 * 
 * where mazeFile is a text file of the maze. The format is the number of rows
 * and number of columns, followed by one line per row. Each maze location is
 * either a wall (1) or free (0). Here is an example of contents of a file for
 * a 3x4 maze:
 * 
 * 3 4 
 * 0111
 * 0000
 * 1110
 * 0010
 * 
 * The top left is the maze entrance, and the bottom right is the maze exit.
 * 
 */

public class MazeViewer {
   
   public static void main(String[] args)  {

      String fileName = "";

      try {

         if (args.length < 1) {
            System.out.println("ERROR: missing file name command line argument");
         }
         else {
            fileName = args[0];

            boolean[][] mazeData = readMazeFile(fileName);

            JFrame frame = new MazeFrame(mazeData);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.setVisible(true);
         }

      }
      catch (FileNotFoundException exc) {
         System.out.println("File not found: " + fileName);
      }
      catch (IOException exc) {
         exc.printStackTrace();
      }
   }

   /**
   readMazeFile reads in and returns a maze from the file whose name is
   String given. The file format is shown in the MazeViewer class comments.
   
   @param fileName
             the name of a file to read from
   @returns 
            the array with maze contents. false at a location means there is no wall
            (0 in the file) and true means there is a wall (1 in the file).
            The first dimension is which row, and the second is which column. E.g. if the file
            started with 3 10, it would mean the array returned would have
            3 rows, and 10 columns.
   @throws FileNotFoundException
              if there's no such file (subclass of IOException)
   @throws IOException
              (hook given in case you want to do more error-checking.
               that would also involve changing main to catch other exceptions)
   */
   private static boolean[][] readMazeFile(String fileName) throws IOException {
	   File inFile = new File(fileName);
	   Scanner in = new Scanner(inFile);
	   try
	      {
	         return readData(in);
	      }
	      finally
	      {
	         in.close();
	      }

   }
   private static boolean[][] readData(Scanner in){
	   String line = "";
	   int row = 0, col = 0;
	   if(in.hasNextLine()){
		   line = in.nextLine();
	   }
	   // Read the size of the maze
	   Scanner scan = new Scanner(line);
	   if(scan.hasNextInt()){
		   row = scan.nextInt();
	   }
	   if(scan.hasNextInt()){
		   col = scan.nextInt();
	   }
	   boolean[][] data = new boolean[row][col];
	   int rowNum = 0;
	   while(in.hasNextLine()){
		   line = in.nextLine();
		   for(int i = 0;i < line.length();i++){
			   if(line.charAt(i) == '0'){
				   data[rowNum][i] = false;
			   }
			   else{
				   data[rowNum][i] = true;
			   }
		   }
		   rowNum++;
	   }
	   scan.close();
	   return data;
   }
}

