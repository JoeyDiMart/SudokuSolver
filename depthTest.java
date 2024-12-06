package SudokuSolver;
/*
Names: Joseph DiMartino, Nicole Scott, Daniel Jaffe
Program: SudokuSolver: depthTest
Description: This class will contain test for doing a DLS.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static SudokuSolver.breadthtest.printBoard;

public class depthTest {
        public static void main(String[] args) {
        String filePath = "msp.txt";
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
        int maximumSolutions = 1000000;
        // Run Depth Limited Search
        DepthLimitedSearch dls = new DepthLimitedSearch(puzzle);
        List<int[][]> solutions = dls.solve(maximumSolutions);
        int count = 1;
        for (int[][] solution : solutions) {
            System.out.println("solution " + count++);
            printBoard(solution);
            }

    }  
}
