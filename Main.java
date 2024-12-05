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
        String filePath = "SudokuFileRandomized.txt"; // file path
        makeSudokuPuzzle.main(new String[]{});  // call main method from makeSudokuPuzzle

        // create three instances of the graphs (one for each difficulty)
        Graph easy_dls_puzzle = new Graph();
        Graph medium_dls_puzzle = new Graph();
        Graph hard_dls_puzzle = new Graph();
        Graph easy_bfs_puzzle = new Graph();
        Graph medium_bfs_puzzle = new Graph();
        Graph hard_bfs_puzzle = new Graph();

        // read text file (line 1-81) to fill initial sudoku puzzle
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String[] line;
            for (int i = 1; i < 82; i++) {
                line = reader.readLine().split(" ");
                easy_dls_puzzle.insertMatrix(Integer.parseInt(line[0]), i);
                medium_dls_puzzle.insertMatrix(Integer.parseInt(line[1]), i);
                hard_dls_puzzle.insertMatrix(Integer.parseInt(line[2]), i);
                easy_bfs_puzzle.insertMatrix(Integer.parseInt(line[0]), i);
                medium_bfs_puzzle.insertMatrix(Integer.parseInt(line[1]), i);
                hard_bfs_puzzle.insertMatrix(Integer.parseInt(line[2]), i);
            }

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }


        DepthLimitedSearch easy_dls = new DepthLimitedSearch(easy_dls_puzzle);
        DepthLimitedSearch medium_dls = new DepthLimitedSearch(medium_dls_puzzle);
        DepthLimitedSearch hard_dls = new DepthLimitedSearch(hard_dls_puzzle);

        BreadthFirstSearch easy_bfs = new BreadthFirstSearch();
        BreadthFirstSearch medium_bfs = new BreadthFirstSearch();
        BreadthFirstSearch hard_bfs = new BreadthFirstSearch();


        long start_easy_dls = System.nanoTime();
        easy_dls.solve();
        long easy_dls_time = System.nanoTime() - start_easy_dls;
        long start_medium_dls = System.nanoTime();
        medium_dls.solve();
        long medium_dls_time = System.nanoTime() - start_medium_dls;
        long start_hard_dls = System.nanoTime();
        hard_dls.solve();
        long hard_dls_time = System.nanoTime() - start_hard_dls;

        System.out.println("---Easy DLS Puzzle Solved in " + easy_dls_time + " ns ---");
        easy_dls.graph.display();
        System.out.println("---Medium DLS Puzzle Solved in " + medium_dls_time + " ns ---");
        medium_dls.graph.display();
        System.out.println("---Hard DLS Puzzle Solved in " + hard_dls_time + " ns ---");
        hard_dls.graph.display();


        long start_easy_bfs = System.nanoTime();
        easy_bfs.solve(easy_bfs_puzzle);
        long easy_bfs_time = System.nanoTime() - start_easy_bfs;
        long start_medium_bfs = System.nanoTime();
        medium_bfs.solve(medium_bfs_puzzle);
        long medium_bfs_time = System.nanoTime() - start_medium_bfs;
        long start_hard_bfs = System.nanoTime();
        hard_bfs.solve(hard_bfs_puzzle);
        long hard_bfs_time = System.nanoTime() - start_hard_bfs;


        System.out.println("---Easy BFS Puzzle Solved in " + easy_bfs_time + " ns ---");
        printBoard(easy_bfs_puzzle.getMatrix());
        System.out.println("---Medium BFS Puzzle Solved in " + medium_bfs_time + " ns ---");
        printBoard(medium_bfs_puzzle.getMatrix());
        System.out.println("---Hard BFS Puzzle Solved in " + hard_bfs_time + " ns ---");
        printBoard(hard_bfs_puzzle.getMatrix());

        //----- Comparing results-----
        if (easy_bfs_time - easy_dls_time > 0) {
            System.out.println("DLS was faster by " + (easy_bfs_time - easy_dls_time) + "ns for easy puzzle");
        }
        else {
            System.out.println("BFS was faster by " + (easy_dls_time - easy_bfs_time) + "ns for easy puzzle");
        }
        if (medium_bfs_time - medium_dls_time > 0) {
            System.out.println("DLS was faster by " + (medium_bfs_time - medium_dls_time) + "ns for medium puzzle");
        }
        else {
            System.out.println("BFS was faster by " + (medium_dls_time - medium_bfs_time) + "ns for medium puzzle");
        }
        if (hard_bfs_time - hard_dls_time > 0) {
            System.out.println("DLS was faster by " + (hard_bfs_time - hard_dls_time) + "ns for hard puzzle");
        }
        else {
            System.out.println("BFS was faster by " + (hard_dls_time - hard_bfs_time) + "ns for hard puzzle");
        }

    }
    // Helper method to print the Sudoku board for BFS
    private static void printBoard(int[][] board) {
        for (int[] row : board) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}


