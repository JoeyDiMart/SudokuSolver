/*
Names: Joseph DiMartino, Nicole Scott, Daniel Jaffe
Program: SudokuSolver: BreadthFirstSearchT
Description: This class will contain methods for doing a BFS
 */
package SudokuSolver;

import java.util.*;

import static SudokuSolver.breadthtest.printBoard;


public class BreadthFirstSearch {
    private final List<int[][]> solutions = new ArrayList<>();


    // used https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/ to help me with the BFS solve function
    // method to solve the sudoku using bfs
    public List<int[][]> solve(int[][] board, int maxSolutions) {

        // making a queue for BFS
        Queue<int[][]> queue = new LinkedList<>();
        // used chatgpt to put the copy board to the end of the queue
        queue.offer(copyBoard(board));


        // go through the queue
        while (!queue.isEmpty() && solutions.size() < maxSolutions) {
            int[][] currentBoard = queue.poll();

            // find the empty cell
            int[] emptyCell = findEmptyCell(currentBoard);
            if (emptyCell == null) {
                // finds a solutions add it to list
                solutions.add(copyBoard(currentBoard));
                continue;
            }

            int row = emptyCell[0];
            int col = emptyCell[1];


            // now try numbers from 1 to x
            for (int num = 1; num <= 9; num++) {
                if (isValid(currentBoard, row, col, num)) {
                    // set the number then make a copy
                    currentBoard[row][col] = num;
                    queue.offer(copyBoard(currentBoard));
                    currentBoard[row][col] = 0;
                }
            }
        }
        return solutions;
    }
    // helper method to check if placing a number is valid
    private boolean isValid(int[][] board, int row, int col, int num) {
        // checks row
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }
        // checks column
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }
        // check the 3x3 subgrid for conflicts
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    // finds the empty cells in a board
    private int[] findEmptyCell(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    // if empty cell return position of that empty cell
                    return new int[]{row, col};
                }
            }
        }
        // otherwise no empty cell return null
        return null;
    }

    // helper method to copy the board
    private static int[][] copyBoard(int[][] original) {
        int[][] copy = new int[9][9];
        for (int i = 0; i < 9; i++) {
            System.arraycopy(original[i], 0, copy[i], 0, 9);
        }
        return copy;
    }
}