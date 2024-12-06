package SudokuSolver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

// used chatgpt to create my test file and manipulated it to fit my test file
public class breadthtest {


    public static void main(String[] args) {
        String filePath = "msp.txt"; // Ensure this path is correct and points to your input file
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


        // max solutions you allow
        // depending on how good your memory is this might need to be higher or lower
        int maxSolutions =100000;
        // Create the BFS solver
        BreadthFirstSearch solver = new BreadthFirstSearch();
        List<int[][]> solutions = solver.solve(puzzle.getMatrix(), maxSolutions);
        int count = 1;
        for (int[][] solution : solutions) {
            System.out.println("solution " + count++);
            printBoard(solution);
        }

    }

    // Helper method to print the Sudoku board
    public static void printBoard(int[][] board) {
        for (int[] row : board) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

}



