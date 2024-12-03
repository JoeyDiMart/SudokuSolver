/*
Names: Joseph DiMartino, Nicole Scott, Daniel Jaffe
Program: SudokuSolver: Main clas
Description: This is the main class, basically the timer to track which search is faster takes place here as well
as instances of the graphs are created here. In this class we read from the text file for the 9x9 Sudoku puzzles
 */

package SudokuSolver;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String filePath = "SudokuPuzzles.txt"; // file path

        // create three instances of the graphs (one for each difficulty)
        Graph easy_puzzle = new Graph();
        Graph medium_puzzle = new Graph();
        Graph hard_puzzle = new Graph();

        // read text file (line 1-81) to fill initial sudoku puzzle
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String[] line;
            for (int i = 1; i < 82; i++) {
                line = reader.readLine().split(" ");
                easy_puzzle.insertMatrix(Integer.parseInt(line[0]), i);
                medium_puzzle.insertMatrix(Integer.parseInt(line[1]), i);
                hard_puzzle.insertMatrix(Integer.parseInt(line[2]), i);
            }

            //medium_puzzle.display();  // test if puzzles are being displayed properly

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }


    }
}

