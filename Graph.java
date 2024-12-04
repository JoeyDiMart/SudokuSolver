/*
Names: Joseph DiMartino, Nicole Scott, Daniel Jaffe
Program: BredathFirstSearch: This class will contain methods for doing a BFS
Description: This Graph class was made instead of using Java's Graph data structure since this one is fully customized
for our Sudoku problem. It only contains the methods we need such as getMatrix, goPrev, insert, display, and insertMatrix.
the graph has a current and a matrix property. The current is just a pointer and matrix is just a way for us to
display each instance of a graph easier.
 */
package SudokuSolver;

public class Graph {

    private Vertex curr;
    private final int[][] matrix;

    public Graph() {
        this.curr = new Vertex(-1, 0, 0, null);  // curr node starts at root (height 0)
        this.matrix = new int[9][9];
    }

    public Graph(int[][] board) {
        this.curr = new Vertex(-1, 0, 0, null);
        this.matrix = board;
    }


    public boolean goPrev() {  // back track
        if (this.curr.getHeight() == 0) {
            return false; // cannot go to a previous node since already at root (print in main cannot sole)
        }
        else {
            this.curr = this.curr.getPrev();
            return true;  // successfully went to previous node
        }
    }

    public void insert() {  // insert a node to the next height
        // start with the lowest value in the set then increase
        // create new vertex and set the previous to the current, change current to new vertex
        Vertex temp = new Vertex();
        temp.setPrev(this.curr);
        this.curr = temp;
    }


    public void insertMatrix(int s, int pos) {  // method to create change values in the matrix
        int i =  (pos-1) / 9;
        int j = (pos-1) % 9;
        this.matrix[i][j] = s;
    }


    public void display() {  // print matrix
        int j = 0;
        int k = 0;
        for (int i = 0; i < (this.matrix.length * this.matrix.length); i++) {
            System.out.print(matrix[j][k] + " ");
            k++;
            if (k >= 9) {
                System.out.println("");
                j++;
                k = 0;
            }
        }
        System.out.println();
    }
    // overload display() method since BFS does things differently, display with taking in a 2d array
    public void display(int[][] board) {  // print matrix
        int j = 0;
        int k = 0;
        for (int i = 0; i < (board.length * board.length); i++) {
            System.out.print(board[j][k] + " ");
            k++;
            if (k >= 9) {
                System.out.println("");
                j++;
                k = 0;
            }
        }
        System.out.println();
    }

    public int[][] getMatrix() {    // return matrix
        return this.matrix;
    }
}