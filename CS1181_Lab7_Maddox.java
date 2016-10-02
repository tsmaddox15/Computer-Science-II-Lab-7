/*This project allows the user to input file names which are then read to create a grid object with regions on it. Each region is given a different number to identify it and has a size for how many spots it takes up. 
 */
package cs1181_lab7_maddox;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author Taylor 
 * Instructor: Rick Volker
 * TA: Sai
 */
public class CS1181_Lab7_Maddox {

    /**
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        Grid grid = new Grid();
        String input = "";
        String output = "";
        boolean valid = true;
        String newFile = "";
        System.out.println("Enter files you want read, the results get save to results.txt");
        while (valid == true) {

            System.out.print("Enter the name of the file: ");
            String fileName = keyboard.next();
            grid.load(fileName);
            input = grid.printBeforeFind();
            grid.findRegions();
            output = grid.print();
            newFile = "results";
            grid.save(newFile, input, output);
            System.out.println("Do you want to continue to add files? Yes/No");
            String cont = keyboard.next();
            if (cont.equals("no")) {
                valid = false;
            }
        }
    }

}
