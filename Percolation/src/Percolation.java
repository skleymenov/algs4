/*
  Percolation problem solution.

  Created by Sergey Kleymenov on 21/06/2017.
 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.lang.IndexOutOfBoundsException;
import java.lang.IllegalArgumentException;



public class Percolation {
    private final int size;
    private final int top;
    private boolean[] open;
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
        this.open = new boolean[n*n];
        this.openCount = 0;
        this.unionFind = new WeightedQuickUnionUF(n*n + 2);


    }
    public void open(int row, int col)    // open site (row, col) if it is not open already
    {
        int i = xyTo1D(row, col);

        if (isOpen(row,col)) return;
        open[i-1] = true;
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

        return open[i-1];
    }
    private boolean isOpen(int i)
    {
        return open[i-1];
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
        if (args.length != 1) return;

        int size = Integer.valueOf(args[0]);
        Percolation percolation = new Percolation(size);

        for (int i = 1; i <= size; i++)
        {
            for( int j=1; j<= size; j++)
            {
                System.out.print("\t" + percolation.xyTo1D(i,j));
            }
            System.out.println();
        }




    }
}
