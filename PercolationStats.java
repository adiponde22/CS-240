import stdlib.StdOut;
import stdlib.StdRandom;
import stdlib.StdStats;

public class PercolationStats {
    // Instance variables
    private final int m;
    // Instance variables x
    private final double[] x;

    // Performs m independent experiments on an n x n percolation system.
    public PercolationStats(int n, int m) {
        // check corner cases
        if (n <= 0 || m <= 0) {
            throw new IllegalArgumentException("Illegal n or m");
        }

        // initialize instance variables
        this.m = m;
        x = new double[m];
        for (int z = 0; z < m; z++) {
            UFPercolation perc = new UFPercolation(n);
            int count = 0;
            while (!perc.percolates()) {
                int i = StdRandom.uniform(n);
                int j = StdRandom.uniform(n);
                if (!perc.isOpen(i, j)) {
                    perc.open(i, j);
                    count++;
                }
            }
            x[z] = (double) count / (n * n);
        }

    }

    // Returns sample mean of percolation threshold.
    public double mean() {
        // return the mean of the percolation threshold
        return StdStats.mean(x);
    }

    // Returns sample standard deviation of percolation threshold.
    public double stddev() {
        // return the standard deviation of the percolation threshold
        return StdStats.stddev(x);
    }

    // Returns low endpoint of the 95% confidence interval.
    public double confidenceLow() {
        return mean() - (1.96 * stddev()) / Math.sqrt(m);
    }

    // Returns high endpoint of the 95% confidence interval.
    public double confidenceHigh() {
        return mean() + (1.96 * stddev()) / Math.sqrt(m);
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, m);
        StdOut.printf("Percolation threshold for a %d x %d system:\n", n, n);
        StdOut.printf("  Mean                = %.3f\n", stats.mean());
        StdOut.printf("  Standard deviation  = %.3f\n", stats.stddev());
        StdOut.printf("  Confidence interval = [%.3f, %.3f]\n", stats.confidenceLow(),
                stats.confidenceHigh());
    }
}