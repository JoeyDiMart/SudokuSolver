package SudokuSolver;
import java.util.Set;
import java.util.TreeSet;

public class Vertex {
    private int data;
    private int height;
    private Set<Integer> treeSet;
    private Vertex prev;


    public Vertex() {  // default constructor
        this.data = -1;
        this.height = 0;
        this.treeSet = new TreeSet<>();
        this.prev = null;
        for (int i = 1; i <= 9; i++) {
            this.treeSet.add(i);
        }
    }
    public Vertex(int data, int height, Vertex prev) {
        this(data, height, 0, null); // call the constructor with prevData set to 0 (default)
    }
    public Vertex(int data, int height, int prevData, Vertex prev) {  // main constructor taking in all 3 parameters
        this.data = data;
        this.height = height;
        this.treeSet = new TreeSet<>();
        this.prev = null;
        for (int i = prevData++; i <= 9; i++) {
            this.treeSet.add(i);
        }
    }


    public void setData(int d) {
        this.data = d;
    }
    public int getData() {
        return this.data;
    }

    public void setHeight(int h) {
        this.height = h;
    }
    public int getHeight() {
        return this.height;
    }

    public void setTreeSet(Set<Integer> treeSet) {
        this.treeSet = treeSet;
    }
    public Set<Integer> getTreeSet() {
        return this.treeSet;
    }

    public void setPrev(Vertex p) {
        this.prev = p;
    }
    public Vertex getPrev() {
        return this.prev;
    }


}
