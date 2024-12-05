package SudokuSolver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// used chatgpt to create my test file and manipulated it to fit my test file
public class breadthtest {


    public static void main(String[] args) {
        String filePath = "SudokuFileRandomized.txt"; // Ensure this path is correct and points to your input file
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

        // Create the BFS solver
        BreadthFirstSearch solver = new BreadthFirstSearch();

        // checks if puzzle can be solved
        if (solver.solve(puzzle)) {
            System.out.println("Sudoku solved successfully! Here's the solution:");
            printBoard(puzzle.getMatrix());
        } else {
            System.out.println("No solution could be found for the given Sudoku puzzle.");
        }
    }

    // Helper method to print the Sudoku board
    private static void printBoard(int[][] board) {
        for (int[] row : board) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}



