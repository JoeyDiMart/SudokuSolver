package SudokuSolver;/*
Names: Joseph DiMartino, Nicole Scott, Daniel Jaffe
Program: SudokuSolver: depthTest
Description: This class will contain test for doing a DLS.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class depthTest {
        public static void main(String[] args) {
        String filePath = "SudokuPuzzles.txt"; 
        Graph puzzle = new Graph();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String[] line;
            // goes through the file and makes the matrix
            for (int i = 1; i < 82; i++) {
                line = reader.readLine().split(" ");
                puzzle.insertMatrix(Integer.parseInt(line[0]), i);
            }
            System.out.println("Input Puzzle:");
            puzzle.display();
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            // Exit if there's an error reading the file
            return;
        }

        // Run Depth Limited Search
        DepthLimitedSearch dls = new DepthLimitedSearch(puzzle);
        boolean solved = dls.solve();

        // Print the result
        if (solved) {
            System.out.println("\nSolved Puzzle:");
            puzzle.display();
        } else {
            System.out.println("\nNo solution found with the depth-limited search.");
        }
    }  
}
