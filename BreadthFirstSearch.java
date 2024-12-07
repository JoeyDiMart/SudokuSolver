/*
Names: Joseph DiMartino, Nicole Scott, Daniel Jaffe
Program: SudokuSolver: BreadthFirstSearchT
Description: This class will contain methods for doing a BFS
 */
package SudokuSolver;

import java.util.*;

public class BreadthFirstSearch {

    private final List<int[][]> solutions = new ArrayList<>();
    // used https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/ to help me with the BFS solve function
    // method to solve the sudoku using bfs
    public List<int[][]> solve(int[][] board, int maxSolutions) {
        int len = board.length;
        // making a queue for BFS
        Queue<int[][]> queue = new LinkedList<>();
        // used chatgpt to put the copy board to the end of the queue
        queue.offer(copyBoard(board));


        // go through the queue
        while (!queue.isEmpty() && solutions.size() < maxSolutions) {
            int[][] currentBoard = queue.poll();

            // find the empty cell
            int[] emptyCell = findCellWithFewestVals(currentBoard);
            if (emptyCell == null) {
                // finds a solutions add it to list
                solutions.add(copyBoard(currentBoard));
                continue;
            }

            int row = emptyCell[0];
            int col = emptyCell[1];


            // now try numbers from 1 to x
            for (int num = 1; num <= len; num++) {
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
        int len = board.length;
        int sub_len = (int) Math.sqrt(len);
        // checks row
        for (int i = 0; i < len; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }
        // checks column
        for (int i = 0; i < len; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }
        // check the 3x3 subgrid for conflicts
        int startRow = row - row % sub_len;
        int startCol = col - col % sub_len;
        for (int i = startRow; i < startRow + sub_len; i++) {
            for (int j = startCol; j < startCol + sub_len; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    // finds the fewests values
    private int[] findCellWithFewestVals(int[][] board) {
        int len = board.length;
        int[] bestCell = null;

        // start with max amount of empty spaces (all empty spaces in row, column, and box (9x3)) so min is found
        int minEmptySpaces = len * 3;

        // check all spaces in puzzle
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    // count how many empty spaces are in row, column, or box
                    int count = countZeros(board, i, j);
                    // set bestCell to space with lowest amount of empty spaces
                    if (count < minEmptySpaces) {
                        minEmptySpaces = count;
                        bestCell = new int[]{i, j};
                    }
                }
            }
        }
        //return space with least amount of zeros in row, col, or box
        return bestCell;
    }

    // function to count the empty spaces are in row, column, or box
    private int countZeros(int[][] board, int row, int col) {
        int len = board.length;
        int count = 0;

        for (int value = 1; value <= len; value++) {
            // check if numbers 1-9 are in row, column, or box, if not must be empty
            if (isValid(board, row, col, value)) {
                count++;
            }
        }
        return count;
    }
    // helper method to copy the board
    private static int[][] copyBoard(int[][] original) {
        int len = original.length;
        int[][] copy = new int[len][len];
        for (int i = 0; i < len; i++) {
            System.arraycopy(original[i], 0, copy[i], 0, len);
        }
        return copy;
    }
}