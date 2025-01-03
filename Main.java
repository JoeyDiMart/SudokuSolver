/*
Names: Joseph DiMartino, Nicole Scott, Daniel Jaffe
Program: SudokuSolver: Main clas
Description: This is the main class, basically the timer to track which search is faster takes place here as well
as instances of the graphs are created here. In this class we read from the text file for the 9x9 Sudoku puzzles

This code is based on the research article "Comparison Analysis of Breadth First Search and Depth Limited Search
Algorithms in Sudoku Game." This research was done by Tirsa Ninia Lina and Matheus Supriyanto Rumetna to analyze the two
search algorithms shown in this code and how they solve the Sudoku puzzles. The link to the article is attached:
https://www.researchgate.net/publication/358642884_Comparison_Analysis_of_Breadth_First_Search_and_Depth_Limited_Search_Algorithms_in_Sudoku_Game
 */

package SudokuSolver;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

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

        int maximumSolutions = 100000;
        DepthLimitedSearch easy_dls = new DepthLimitedSearch(easy_dls_puzzle);
        DepthLimitedSearch medium_dls = new DepthLimitedSearch(medium_dls_puzzle);
        DepthLimitedSearch hard_dls = new DepthLimitedSearch(hard_dls_puzzle);

        BreadthFirstSearch easy_bfs = new BreadthFirstSearch();
        BreadthFirstSearch medium_bfs = new BreadthFirstSearch();
        BreadthFirstSearch hard_bfs = new BreadthFirstSearch();


        long start_easy_dls = System.nanoTime();
        List<int[][]> easySolutionsDLS=easy_dls.solve(maximumSolutions);
        long easy_dls_time = System.nanoTime() - start_easy_dls;
        long start_medium_dls = System.nanoTime();
        List<int[][]> mediumSolutionsDLS=medium_dls.solve(maximumSolutions);
        long medium_dls_time = System.nanoTime() - start_medium_dls;
        long start_hard_dls = System.nanoTime();
        List<int[][]> hardSolutionsDLS=hard_dls.solve(maximumSolutions);
        long hard_dls_time = System.nanoTime() - start_hard_dls;

        System.out.println("---Easy DLS Puzzle Solved in " + easy_dls_time + " ns ---");
        int easyCountDLS = 1;
        for (int[][] solution : easySolutionsDLS) {
            System.out.println("solution " + easyCountDLS++);
            printBoard(solution);
        }        System.out.println("---Medium DLS Puzzle Solved in " + medium_dls_time + " ns ---");
        int mediumCountDLS = 1;
        for (int[][] solution : mediumSolutionsDLS) {
            System.out.println("solution " + mediumCountDLS++);
            printBoard(solution);
        }
        System.out.println("---Hard DLS Puzzle Solved in " + hard_dls_time + " ns ---");
        int hardCountDLS = 1;
        for (int[][] solution : hardSolutionsDLS) {
            System.out.println("solution " + hardCountDLS++);
            printBoard(solution);
        }


        long start_easy_bfs = System.nanoTime();
        List<int[][]> easySolutionsBFS = easy_bfs.solve(easy_bfs_puzzle.getMatrix(),maximumSolutions);
        long easy_bfs_time = System.nanoTime() - start_easy_bfs;
        long start_medium_bfs = System.nanoTime();
        List<int[][]> mediumSolutionsBFS = medium_bfs.solve(medium_bfs_puzzle.getMatrix(),maximumSolutions);
        long medium_bfs_time = System.nanoTime() - start_medium_bfs;
        long start_hard_bfs = System.nanoTime();
        List<int[][]> hardSolutionsBFS = hard_bfs.solve(hard_bfs_puzzle.getMatrix(),maximumSolutions);
        long hard_bfs_time = System.nanoTime() - start_hard_bfs;


        System.out.println("---Easy BFS Puzzle Solved in " + easy_bfs_time + " ns ---");
        int easyCountBFS = 1;
        for (int[][] solution : easySolutionsBFS) {
            System.out.println("solution " + easyCountBFS++);
            printBoard(solution);
        }
        System.out.println("---Medium BFS Puzzle Solved in " + medium_bfs_time + " ns ---");
        int mediumCountBFS = 1;
        for (int[][] solution : mediumSolutionsBFS) {
            System.out.println("solution " + mediumCountBFS++);
            printBoard(solution);
        }
        System.out.println("---Hard BFS Puzzle Solved in " + hard_bfs_time + " ns ---");
        int hardCountBFS = 1;
        for (int[][] solution : hardSolutionsBFS) {
            System.out.println("solution " + hardCountBFS++);
            printBoard(solution);
        }
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

