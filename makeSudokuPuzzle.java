package SudokuSolver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

// This makes random sudoku puzzles for a 9 x 9
public class makeSudokuPuzzle {
    public static void main(String[] args) {
        // this is the inner box size
        int subBoxSize = 3;
        // this is the whole grid size
        int gridSize = subBoxSize * subBoxSize;


        int[][] puzzleEasy = generatePuzzle(gridSize, "easy");
        int[][] puzzleMedium = generatePuzzle(gridSize, "medium");
        int[][] puzzleHard = generatePuzzle(gridSize, "hard");
            // learned how to write something into a text file and save it from Chatgpt
            // write the puzzles to a text file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("SudokuFileRandomized.txt"))) {
                for (int row = 0; row < puzzleEasy.length; row++) {
                    for (int col = 0; col < puzzleEasy[row].length; col++) {
                        writer.write(puzzleEasy[row][col] +" "+ puzzleMedium[row][col] +" "+ puzzleHard[row][col]+ "\n");
                    }
                }
                writer.write("[EASY, MEDIUM, HARD]");

                System.out.println("Puzzle saved to SudokuFileRandomized.txt");
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
            }
        }


    public static int[][] generatePuzzle(int gridSize, String difficulty) {
        // determine the number of blank spaces based on difficulty
        int blankSpaces = switch (difficulty) {
            case "easy" -> gridSize * 3;
            case "medium" -> gridSize * 5;
            case "hard" -> gridSize * 9;
            default -> 0;
        };

        int[][] grid = new int[gridSize][gridSize];
        Random rand = new Random();
        // fill the grid with valid numbers
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                boolean found = false;
                int attempts = 0;

                // try to place a valid number in the current cell
                while (attempts <100) {
                    int numToPlace = rand.nextInt(gridSize) + 1;
                    if (isValid2(grid, row, col, numToPlace)) {
                        grid[row][col] = numToPlace;
                        found = true;
                        break;
                    }
                    attempts++;
                }

                // if unable to place a number after many attempts, regenerate the grid
                if (!found) {
                    return generatePuzzle(gridSize, difficulty);
                }
            }
        }

        // randomly remove numbers to create blank spaces
        for (int i = 0; i < blankSpaces; i++) {
            int row = rand.nextInt(gridSize);
            int col = rand.nextInt(gridSize);
            grid[row][col] = 0; // Set the cell to blank
        }

        return grid;
    }

    // helper method to check if placing a number is valid
    private static boolean isValid2(int[][] board, int row, int col, int num) {
        int gridSize = board.length;
        int subGridSize = (int) Math.sqrt(gridSize);

        // check row and column for conflicts
        for (int i = 0; i < gridSize; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }

        // check the sub-grid for conflicts
        int startRow = (row / subGridSize) * subGridSize;
        int startCol = (col / subGridSize) * subGridSize;
        for (int i = startRow; i < startRow + subGridSize; i++) {
            for (int j = startCol; j < startCol + subGridSize; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

}