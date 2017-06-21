/*
  Percolation problem solution.

  Created by Sergey Kleymenov on 21/06/2017.
 */
import java.lang.IndexOutOfBoundsException;
import java.lang.IllegalArgumentException;


public class Percolation {
    private final int size;

    private void checkArgs(int row, int col)
    {
        if ( row <= 0 || row > this.size) throw new IndexOutOfBoundsException("row index out of bounds");
        if ( col <= 0 || col > this.size) throw new IndexOutOfBoundsException("col index aout of bound");
    }
    public Percolation(int n)                // create n-by-n grid, with all sites blocked
    {
        if ( n <= 0)
        {
            throw new IllegalArgumentException("Grid size must be greater then zero");
        }

        this.size = n;

    }
    public void open(int row, int col)    // open site (row, col) if it is not open already
    {
        checkArgs(row, col);

    }
    public boolean isOpen(int row, int col)  // is site (row, col) open?
    {
        checkArgs(row, col);
        return true;
    }
    public boolean isFull(int row, int col)  // is site (row, col) full?
    {
        checkArgs(row, col);

        return false;
    }
    public int numberOfOpenSites()       // number of open sites
    {
        return 0;
    }
    public boolean percolates()              // does the system percolate?
    {
        return true;
    }

    public static void main(String[] args)   // test client (optional)
    {

    }
}
