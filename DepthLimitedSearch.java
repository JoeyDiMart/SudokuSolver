/*
Names: Joseph DiMartino, Nicole Scott, Daniel Jaffe
Program: DepthLimitedSearch: This class will contain methods for doing a DLS.
 */
package SudokuSolver;

public class DepthLimitedSearch {

    private final Graph graph;

    public DepthLimitedSearch(Graph graph) {
        this.graph = graph;
    }

    // base method for DLS
    public boolean solve() {
        return depthLimitedSearch(0);
    }

    // recursive method for DLS
    private boolean depthLimitedSearch(int depth) {
        // check if no empty spaces remain
        if (isPuzzleFinished(graph)) {
            return true;
        }

        // check if depth limit is reached
        if (depth >= 80) {                                  //DEPTH LIMIT @ 80 BECAUSE PAPER USED SAME
            return false;
        }

        // initialize next empty space in puzzle
        int[] emptySpace = nextEmptySpace();

        // get row & column position of empty space
        int row = emptySpace[0];
        int col = emptySpace[1];

        // get position value of empty space
        int pos = (row * 9) + col + 1;

        // try to insert all possible nums in puzzle (1-9)
        for (int value = 1; value <= 9; value++) {
            // check if value can be inserted without creating duplicates in row, col, or box
            if (notFound(row, col, value)) {
                graph.insertMatrix(value, pos);

                // recursive call to see if insertion leads to solution
                if (depthLimitedSearch(depth+1)) {
                    return true;
                }

                // insertion did not lead to solution, undo move and reset back to 0
                graph.insertMatrix(0, pos);
            }
        }
        // no solution found after insterting all nums
        return false;
    }

    // function to find first empty space in puzzle
    private int[] nextEmptySpace() {
        // go through all spaces in puzzle
        for (int i = 1; i <= 81; i++) {
            int row = (i - 1) / 9;
            int col = (i - 1) % 9;
            // return empty space position
            if (graph.getMatrix()[row][col] == 0) {
                return new int[]{row,col};
            }
        }
        // no more empty spaces in puzzle
        return null;
    }

    private boolean notFound(int row, int col, int value) {
        // check if value is already in row or column
        for (int i = 0; i < 9; i++) {
            if (graph.getMatrix()[row][i] == value || graph.getMatrix()[i][col] == value) {
                return false;
            }
        }

        // initalize beginning of 3x3 box
        int boxRowStart = (row / 3) * 3;
        int boxColStart = (col / 3) * 3;

        // check if value is already in 3x3 box
        for(int i = 0; i < 3; i ++) {
            for(int  j= 0; j < 3; j ++) {
                if (graph.getMatrix()[boxRowStart + i][boxColStart + j] == value){
                    return false;
                }
            }
        }

        // value is not found in row, column, or box
        return true;
    }

    // function to see if all puzzle spaces are filled
    private boolean isPuzzleFinished(Graph graph){
        // go through all spaces in puzzle
        for(int i = 1; i <= 81; i++) {
            int row = (i - 1) / 9;
            int col = (i - 1) % 9;

            // check if there is empty space in puzzle, if so puzzle is not complete
            if (graph.getMatrix()[row][col] == 0) {
                return false;
            }
        }
        // no empty spaces left, puzzle is complete
        return true;
    }
}