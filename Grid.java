/* Used to create a grid that has different regions on it.
 */
package cs1181_lab7_maddox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.lang.String;

/**
 *
 * @author tsmad_000
 */
public class Grid {

    private int currentRegion = 1; //Region we are assigning
    private int regionSize = 0; //Size of a reigon
    private int[][] grid; //Array for the grid
    private ArrayList<Integer> list = new ArrayList<Integer>(); //Array to store the region size
    int rowsSize = 0; //Amout of rows
    int colsSize = 0; //Amount of columns.

    public Grid() {

    }

    /**
     * Reads a file and fills an array with the values from the file
     * @param inputFilename The name of the file being read.
     */
    public void load(String inputFilename) {
        File file = new File(inputFilename);
        Scanner read = null;

        //try to read.
        try {
            read = new Scanner(file);
            rowsSize = read.nextInt();
            colsSize = read.nextInt();
            grid = new int[rowsSize][colsSize];
            for (int[] row : grid) {
                Arrays.fill(row, -2);
            }
            int spot = 0;
            boolean valid = true;

            while (read.hasNextInt()) {
                spot = read.nextInt();
                valid = true;
                if (spot == 1) {
                    spot = -1;
                }
                //Puts inside the array
                for (int i = 0; i < grid.length && valid == true; i++) {
                    for (int j = 0; j < grid[i].length && valid == true; j++) {
                        if (grid[i][j] == -2 && valid == true) {
                            grid[i][j] = spot;
                            valid = false;
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("File doesnt' exist.");
        }
    }

    /**
     * Goes through the array and creates regions out of -1 values that are next to each other.
     */
    public void findRegions() {
        list.clear();
        currentRegion = 1;
        try {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {

                    if (grid[i][j] == -1) {
                        recursion(i, j);
                        list.add(regionSize);
                        currentRegion++;
                        regionSize = 0;

                    }
                }

            }
        } catch (NullPointerException e) {
        }
    }

    /**
     * This method is called when a value of -1 is found in findRegion, it than uses recursion to change all values of
     * -1 to the current region we are adding.
     *
     * @param r the current row we're looking at.
     * @param c the current column we're looking at.
     */
    public void recursion(int r, int c) {
        //Base Case
        if (r < 0 || c < 0 || r >= rowsSize || c >= colsSize || grid[r][c] != -1) {

        } else {
            grid[r][c] = currentRegion;
            regionSize++;
            recursion(r + 1, c);
            recursion(r - 1, c);
            recursion(r, c + 1);
            recursion(r, c - 1);
            recursion(r + 1, c + 1);
            recursion(r + 1, c - 1);
            recursion(r - 1, c + 1);
            recursion(r - 1, c - 1);
        }
    }

    /**
     * Saves the input and output to a new file called results.
     *
     * @param outputFilename The name
     * @param input
     * @param output
     */
    public void save(String outputFilename, String input, String output) {
        File newFile = new File(outputFilename);
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter(newFile, true));
            pw.print(input + output);

        } catch (Exception e) {

        }
        try {
            pw.close();
        } catch (NullPointerException n) {
        }
    }

    /**
     * Creates a string with the output info
     *
     * @return string with the output info
     */
    public String print() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("Sample Output File: \n");
        try {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    sb.append(grid[i][j]);
                    sb.append("  ");
                    // System.out.printf("%2d", grid[i][j]);
                }
                sb.append("\n");
            }
        } catch (NullPointerException e) {
        }
        int regionNumber = 1;

        sb.append("\n Region Size \n");
        for (int i = 0; i < list.size(); i++) {
            sb.append("Region: " + regionNumber + " Size: " + list.get(i));
            sb.append("\n");
            regionNumber++;
        }
        sb.append("-------------------- \n");
        sb.append("\n");
        String test = sb.toString();
        //  System.out.print(test);
        return test;
    }

    /**
     * Creates a string with the input info
     *
     * @return String for the input info.
     */
    public String printBeforeFind() {
        String input = "";
        input += "Simple Input File \n";
        input += "\nRows:" + rowsSize + "\n";
        input += "Columns:" + colsSize + "\n";
        input += "\n";
        try {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {

                    input += String.format("%2d ", grid[i][j]);
                }
                input += "\n";
            }
        } catch (NullPointerException e) {
        }
        //System.out.print(input);
        return input;
    }
}
