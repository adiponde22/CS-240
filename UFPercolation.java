import dsa.WeightedQuickUnionUF;
import stdlib.In;
import stdlib.StdOut;

// An implementation of the Percolation API using the UF data structure.
public class UFPercolation implements Percolation {
    // Instance variables n
    private int n;
    // Instance variables open
    private boolean[][] open;
    // Instance variables openSites
    private int openSites;
    // Instance variables uf
    private WeightedQuickUnionUF uf;
    // Instance variables ufBackwash
    private WeightedQuickUnionUF ufBackwash;

    // Constructs an n x n percolation system, with all sites blocked.
    public UFPercolation(int n) {
        // checking corner cases if n is less than 1
        if (n <= 0) {
            throw new IllegalArgumentException("Illegal n");
        }

        // initializing instance variables
        this.n = n;
        open = new boolean[n][n];
        openSites = 0;
        uf = new WeightedQuickUnionUF(n * n + 2);
        ufBackwash = new WeightedQuickUnionUF(n * n + 1);
    }

    // Opens site (i, j) if it is not already open.
    public void open(int i, int j) {

        // checking corner cases if i or j is less than 0 or greater than n
        if (i < 0 || i >= n || j < 0 || j >= n) {
            throw new IndexOutOfBoundsException("Illegal i or j");
        }

        // checking if site (i, j) is already open if not, then open it
        if (!open[i][j]) {
            open[i][j] = true;
            openSites++;


            if (i == 0) {
                uf.union(0, encode(i, j));
                ufBackwash.union(0, encode(i, j));
            }

            if (i == n - 1) {
                uf.union(n * n + 1, encode(i, j));
            }

            if (i > 0 && isOpen(i - 1, j)) {
                uf.union(encode(i, j), encode(i - 1, j));
                ufBackwash.union(encode(i, j), encode(i - 1, j));
            }

            if (i < n - 1 && isOpen(i + 1, j)) {
                uf.union(encode(i, j), encode(i + 1, j));
                ufBackwash.union(encode(i, j), encode(i + 1, j));
            }

            if (j > 0 && isOpen(i, j - 1)) {
                uf.union(encode(i, j), encode(i, j - 1));
                ufBackwash.union(encode(i, j), encode(i, j - 1));
            }

            if (j < n - 1 && isOpen(i, j + 1)) {
                uf.union(encode(i, j), encode(i, j + 1));
                ufBackwash.union(encode(i, j), encode(i, j + 1));
            }
        }
    }

    // Returns true if site (i, j) is open, and false otherwise.
    public boolean isOpen(int i, int j) {

        // checking corner cases if i or j is less than 0 or greater than n
        if (i < 0 || i >= n || j < 0 || j >= n) {
            throw new IndexOutOfBoundsException("Illegal i or j");
        }
        return open[i][j];
    }

    // Returns true if site (i, j) is full, and false otherwise.
    public boolean isFull(int i, int j) {
        // if site is full return false
        if (!isOpen(i, j)) {
            return false;
        }
        int site = encode(i, j);
        return ufBackwash.connected(site, 0);
    }

    // Returns the number of open sites.
    public int numberOfOpenSites() {
        // return the number of open sites
        return openSites;
    }

    // Returns true if this system percolates, and false otherwise.
    public boolean percolates() {
        return uf.connected(0, n * n + 1);
    }

    // Returns an integer ID (1...n) for site (i, j).
    private int encode(int i, int j) {
        return i * n + j + 1;
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        int n = in.readInt();
        UFPercolation perc = new UFPercolation(n);
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