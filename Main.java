package SudokuSolver;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String filePath = "SudokuPuzzles.txt"; // Replace with your file path
        Graph easy_puzzle = new Graph();
        Graph medium_puzzle = new Graph();
        Graph hard_puzzle = new Graph();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String[] line;
            for (int i = 1; i < 82; i++) {
                line = reader.readLine().split(" ");
                easy_puzzle.insertMatrix(Integer.parseInt(line[0]), i);
                medium_puzzle.insertMatrix(Integer.parseInt(line[1]), i);
                hard_puzzle.insertMatrix(Integer.parseInt(line[2]), i);
            }
            medium_puzzle.display();

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }


    }
}

