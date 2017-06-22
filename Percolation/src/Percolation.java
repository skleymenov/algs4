/*
  Percolation problem solution.

  Created by Sergey Kleymenov on 21/06/2017.
 */
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.lang.IndexOutOfBoundsException;
import java.lang.IllegalArgumentException;



public class Percolation {
    private final int size;
    private final int top;
    private final int open;
    private final int bottom;
    private int openCount;

    private WeightedQuickUnionUF unionFind;

    private void checkArgs(int row, int col)
    {
        if (row <= 0 || row > this.size) throw new IndexOutOfBoundsException("row index out of bounds");
        if (col <= 0 || col > this.size) throw new IndexOutOfBoundsException("col index aout of bound");
    }
    private int xyTo1D(int row, int col)
    {
        checkArgs(row,col);
        return (row - 1) * size + col;
    }

    public Percolation(int n) throws IllegalArgumentException               // create n-by-n grid, with all sites blocked
    {
        if ( n <= 0)
        {
            throw new IllegalArgumentException("Grid size must be greater then zero");
        }
        else if (n > java.lang.Math.sqrt(Integer.MAX_VALUE - 2))
        {
            throw new IllegalArgumentException("Take it easy");
        }

        this.size = n;
        this.top = 0;
        this.bottom = n*n +1;
        this.open = n*n + 2;
        this.openCount = 0;
        this.unionFind = new WeightedQuickUnionUF(n*n + 3);


    }
    public void open(int row, int col)    // open site (row, col) if it is not open already
    {
        int i = xyTo1D(row, col);

        if (isOpen(row,col)) return;
        unionFind.union(open,i);
        openCount += 1;

        if (row > 1)
        {
            int above = xyTo1D(row - 1, col);
            if (isOpen(above)) unionFind.union(above, i);
        }
        else
        {
            unionFind.union(top, i);
        }

        if ( row < size )
        {
            int below = xyTo1D(row +1, col);
            if (isOpen(below)) unionFind.union(below,i);
        }
        else
        {
            unionFind.union(bottom,i);
        }

        if ( col > 1)
        {
            int left = xyTo1D(row, col -1);
            if (isOpen(left)) unionFind.union(left, i);
        }
        if (col < size)
        {
            int right = xyTo1D(row, col+1);
            if (isOpen(right)) unionFind.union(right, i);
        }

    }
    public boolean isOpen(int row, int col)  // is site (row, col) open?
    {
        int i = xyTo1D(row, col);

        return unionFind.connected(open, i);
    }
    private boolean isOpen(int i)
    {
        return unionFind.connected(open,i);
    }
    public boolean isFull(int row, int col)  // is site (row, col) full?
    {
        int i = xyTo1D(row, col);

        return unionFind.connected(top, i);
    }
    public int numberOfOpenSites()       // number of open sites
    {
        return openCount;
    }
    public boolean percolates()              // does the system percolate?
    {
        return unionFind.connected(top, bottom);
    }

    public static void main(String[] args)   // test client (optional)
    {
        if (args.length != 2) return;

        int size = Integer.valueOf(args[0]);
        int experiments = Integer.valueOf(args[1]);




    }
}
