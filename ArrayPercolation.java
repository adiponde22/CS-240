import stdlib.In;
import stdlib.StdOut;

// An implementation of the Percolation API using a 2D array.
public class ArrayPercolation implements Percolation {
    // Instance variable n
    private int n;
    // Instance variable open
    private boolean[][] open;
    // Instance variable openSites
    private int openSites;

    // Constructs an n x n percolation system, with all sites blocked.
    public ArrayPercolation(int n) {
        // checking corner cases if n is less than 1
        if (n <= 0) {
            throw new IllegalArgumentException("Illegal n");
        }
        // initialize instance variables
        this.n = n;
        this.open = new boolean[n][n];
        this.openSites = 0;
    }

    // Opens site (i, j) if it is not already open.
    public void open(int i, int j) {
        // checking corner cases if i or j is less than 0 or greater than n
        if (i < 0 || i >= n || j < 0 || j >= n) {
            throw new IndexOutOfBoundsException("Illegal i or j");
        }
        // checking if the site is already open and if it is not then open it
        if (!this.isOpen(i, j)) {
            this.openSites++;
            this.open[i][j] = true;
        }
    }

    // Returns true if site (i, j) is open, and false otherwise.
    public boolean isOpen(int i, int j) {
        // checking corner cases if i or j is less than 0 or greater than n
        if (i < 0 || i >= n || j < 0 || j >= n) {
            throw new IndexOutOfBoundsException("Illegal i or j");
        }
        // return true if site is open and false otherwise
        return this.open[i][j];
    }

    // Returns true if site (i, j) is full, and false otherwise.
    public boolean isFull(int i, int j) {
        // checking corner cases if i or j is less than 0 or greater than n
        if (i < 0 || i >= n || j < 0 || j >= n) {
            throw new IndexOutOfBoundsException("Illegal i or j");
        }
        // declare and initialize boolean array of length n * n
        boolean[][] full = new boolean[n][n];

        // fill the first row
        for (int k = 0; k < n; k++) {
            floodFill(full, 0, k);
        }
        return full[i][j];
    }

    // Returns the number of open sites.
    public int numberOfOpenSites() {
        // returns the number of open sites 
        return this.openSites;
    }

    // Returns true if this system percolates, and false otherwise.
    public boolean percolates() {
        // declare and initialize boolean array of length n * n
        boolean[][] full = new boolean[n][n];

        // fill the firsr row
        for (int k = 0; k < n; k++) {
            floodFill(full, 0, k);
        }
        // if the last row is full then return true
        for (int i = 0; i < n; i++) {
            if (full[n - 1][i]) {
                return true;
            }
        }
        // return false otherwise
        return false;
    }

    // Recursively flood fills full[][] using depth-first exploration, starting at
    // (i, j).
    private void floodFill(boolean[][] full, int i, int j) {

        // checking corner cases if i or j is less than 0 or greater than n
        if (i < 0 || i >= n || j < 0 || j >= n || !open[i][j] || full[i][j]) {
            return;
        }

        // open the site
        full[i][j] = true;

        // recursively fill the surrounding sites
        floodFill(full, i - 1, j);
        floodFill(full, i + 1, j);
        floodFill(full, i, j - 1);
        floodFill(full, i, j + 1);
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        int n = in.readInt();
        ArrayPercolation perc = new ArrayPercolation(n);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            perc.open(i, j);
        }
        StdOut.printf("%d x %d system:\n", n, n);
        StdOut.printf("  Open sites = %d\n", perc.numberOfOpenSites());
        StdOut.printf("  Percolates = %b\n", perc.percolates());
        if (args.length == 3) {
            int i = Integer.parseInt(args[1]);
            int j = Integer.parseInt(args[2]);
            StdOut.printf("  isFull(%d, %d) = %b\n", i, j, perc.isFull(i, j));
        }
    }
}