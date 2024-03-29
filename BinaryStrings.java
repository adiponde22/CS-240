import java.util.Iterator;
import stdlib.StdOut;

// An immutable data type to systematically iterate over binary strings of length n.
public class BinaryStrings implements Iterable<String> {
    private int n; // need all binary strings of length n

    // Constructs a BinaryStrings object given the length of binary strings needed.
    public BinaryStrings(int n) {
        this.n = n;
    }

    // Returns an iterator to iterate over binary strings of length n.
    public Iterator<String> iterator() {
        return new BinaryStringsIterator(n);
    }

    // Binary strings iterator.
    private class BinaryStringsIterator implements Iterator<String> {
        private int count; // number of binary strings returned so far
        private int p; // current number in decimal
        private int numberOfStrs; // number of binary strings 2^n
        private String[] binaryStringsArr; // array of binary strings

        // Constructs an iterator.
        public BinaryStringsIterator(int n) {
            p = n;
            numberOfStrs = (int) Math.pow(2, p);
            binaryStringsArr = new String[numberOfStrs];

            for (int i = 0; i < numberOfStrs; i++) {
                binaryStringsArr[i] = Integer.toBinaryString(i);
            }

        }

        // Returns true if there are anymore binary strings to be iterated, and false
        // otherwise.
        public boolean hasNext() {
            return count < numberOfStrs;
        }

        // Returns the next binary string.
        public String next() {
            while (binaryStringsArr[count].length() < p) {
                binaryStringsArr[count] = "0" + binaryStringsArr[count];
            }
            return binaryStringsArr[count++];
        }

        // Returns the n-bit binary representation of x.
        private String binary(int x) {
            String s = Integer.toBinaryString(x);
            int padding = n - s.length();
            for (int i = 1; i <= padding; i++) {
                s = "0" + s;
            }
            return s;
        }
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        for (String s : new BinaryStrings(n)) {
            StdOut.println(s);
        }
    }
}
