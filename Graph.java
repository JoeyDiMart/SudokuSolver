/*
Names: Joseph DiMartino, Nicole Scott, Daniel Jaffe
Program: BredthFirstSearch: This class will contain methods for doing a BFS
 */
package SudokuSolving;

public class Graph {

    private Vertex curr;
    private int[][] matrix;

    public Graph() {
        this.curr = new Vertex(-1, 0, 0, null);  // curr node starts at root (height 0)
        this.matrix = new int[9][9];
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

    public void display() {  // print matrix
        int j = 0;
        int k = 0;
        for (int i = 0; i < (this.matrix.length * this.matrix.length); i++) {
            System.out.print(matrix[j][k]);
            j++; k++;
            if (j > 9) {
                System.out.println();
                j = 0;
                k = 0;
            }
        }
    }

}
