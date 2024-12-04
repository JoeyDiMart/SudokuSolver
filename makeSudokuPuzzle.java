package SudokuSolver;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

// makes a sudoku box that is even
public class makeSudokuPuzzle {
    public static void main(String[] args) {
        // ask for size
        Scanner sc1 = new Scanner(System.in);
        System.out.print("Enter how big the sub box should be. For example a 3 x3 matrices as a sub box will make a 9x9 total box: ");
        int size = sc1.nextInt();

        // ask for difficulty
        Scanner sc2 = new Scanner(System.in);
        System.out.println("Choose difficulty: easy, medium, or hard");
        String difficulty = sc2.nextLine().toLowerCase();
        System.out.println(difficulty);

        // if they choose one of the 3 difficulties
        if (difficulty.equals("easy") || difficulty.equals("medium") || difficulty.equals("hard")) {
            System.out.println(size);
            int[] puzzle = generatePuzzle(size, difficulty);
            System.out.println(Arrays.toString(puzzle));
        }
        // otherwise not easy medium or hard report error
        else {
            System.out.println("Invalid difficulty");
        }
    }

    public static int[] generatePuzzle(int gridSize, String difficulty){
        int blankSpaces = 0;
        if (difficulty.equals("easy")){
            blankSpaces = 2;
        }
        // gets the total amount of boxes
        int[] grid = new int[gridSize * gridSize];
        Random rand = new Random();

        // fill the grid with random numbers
        for (int i = 0; i < gridSize * gridSize; i++) {
            int row = i / gridSize;
            int col = i % gridSize;

            // places numbers
            // checks if number is a valid placement
            int attempts = 0;
            while (attempts < gridSize) {
                int numToPlace = rand.nextInt(gridSize) + 1;
                if (isValidPlacement(grid, gridSize, row, col, numToPlace)) {
                    grid[i] = numToPlace;
                    break;
                }
                attempts++;
            }
        }

        // randomly remove numbers to create blank spaces
        for (int i = 0; i < blankSpaces; i++) {
            int indexToBlank = rand.nextInt(gridSize * gridSize);
            grid[indexToBlank] = 0;
        }

        return grid;
    }

    // checks if is valid placement
    // return false means it can't go there returning true means it can go there
    public static boolean isValidPlacement(int[] grid, int gridSize, int row, int col, int numToPlace) {
        // check row and column
        for (int i = 0; i < gridSize; i++) {
            if (grid[row*gridSize + i] != numToPlace || grid[i*gridSize + col] == numToPlace) {
                return false;
            }
        }
        // check inner box
        int innerBoxSize = (int) Math.sqrt(gridSize);
        int boxRowStart = (row/innerBoxSize)*innerBoxSize;
        int boxColStart = (col/innerBoxSize)*innerBoxSize;

        // now check inner box to find invalidations
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (grid[boxRowStart + i] != grid[boxColStart + j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
