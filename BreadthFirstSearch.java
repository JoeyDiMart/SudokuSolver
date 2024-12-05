/*
Names: Joseph DiMartino, Nicole Scott, Daniel Jaffe
Program: SudokuSolver: BreadthFirstSearchT
Description: This class will contain methods for doing a BFS
 */
package SudokuSolver;

import java.util.LinkedList;
import java.util.Queue;
import java.lang.Math;


public class BreadthFirstSearch {


    // representing a state of the sudoku board at a given level
    private static class BFS {
        private final int[][] board;
        private final int row;
        private final int col;

        // constructor for bfs state
        BFS(int[][] board, int row, int col) {
            this.board = copyBoard(board);
            this.row = row;
            this.col = col;
        }

        // helper method to copy the board
        private static int[][] copyBoard(int[][] original) {
            int len = original.length;
            int[][] copy = new int[len][len];
            for (int i = 0; i < len; i++) {
                System.arraycopy(original[i], 0, copy[i], 0, 9);
            }
            return copy;
        }
    }
    // used https://favtutor.com/blogs/breadth-first-search-java to help me with the BFS solve function
    // method to solve the sudoku using bfs
    public boolean solve(Graph graph) {
        int[][] board = graph.getMatrix();
        int len = graph.getMatrix().length;
        Queue<BFS> queue = new LinkedList<>();
        queue.add(new BFS(board, 0, 0));

        while (!queue.isEmpty()) {
            BFS current = queue.poll();

            // if we reach beyond the last row then the board is solved
            if (current.row == len) {
                copySolution(board, current.board);
                return true;
            }

            // if the current cell is already filled move to the next cell
            if (current.board[current.row][current.col] != 0) {
                queue.add(nextBFS(current, board));
                continue;
            }

            // if the current cell is an answer box, try placing numbers 1-9 in it
            for (int num = 1; num <= len; num++) {
                if (isValid(current.board, current.row, current.col, num)) {
                    current.board[current.row][current.col] = num;
                    queue.add(nextBFS(current, board));
                }
            }
        }

        // if no solution is found, return false
        return false;
    }

    // helper method to move to the next cell
    private BFS nextBFS(BFS current, int[][] board) {
        // if we're at the last column, move to the next row and reset the column ------------------------------------------------------
        int len = board.length;
        int nextRow;
        int nextCol;
        System.out.println();

        if (current.col == len-1) {
            nextRow = current.row + 1;
            nextCol = 0;
        } else {
            nextRow = current.row;
            nextCol = current.col + 1;
        }

        return new BFS(current.board, nextRow, nextCol);
    }

    // helper method to check if placing a number is valid
    private boolean isValid(int[][] board, int row, int col, int num) {
        // check the row and column for conflicts
        int len = board.length;
        int sublen = (int) Math.sqrt(len);
        for (int i = 0; i < len; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }

        // check the 3x3 subgrid for conflicts
        int startRow = row - row % sublen;
        int startCol = col - col % sublen;
        for (int i = startRow; i < startRow + sublen; i++) {
            for (int j = startCol; j < startCol + sublen; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    // helper method to copy the solution back to the original board
    private void copySolution(int[][] original, int[][] solution) {
        int len = original.length;
        for (int i = 0; i < len; i++) {
            System.arraycopy(solution[i], 0, original[i], 0, len);
        }
    }


}