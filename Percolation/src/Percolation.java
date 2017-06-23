/*-----------------------------------------------------------------------------
*
*  Author:          Sergey Kleymenov
*  Written:         6/22/2017
*  Last Updated:    6/23/2017
*
*  Percolation problem model for the Programming Assignment 1
*
------------------------------------------------------------------------------*/

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation
{
    private final int size;             // grid size
    private final int top;              // virtual top site number
    private boolean[] open;             // sites states
    private final int bottom;           // virtual bottom site number
    private int openCount;              // open site counter

    private WeightedQuickUnionUF unionFind;

    /**
     * Create n-by-n grid, with all sites blocked
     * @param n grid size
     */
    public Percolation(int n)
    {
        if (n <= 0)
        {
            throw new IllegalArgumentException("Grid size must be greater then zero");
        } else if (n > java.lang.Math.sqrt(Integer.MAX_VALUE - 2))
        {
            throw new IllegalArgumentException("Take it easy");
        }

        this.size = n;
        this.top = 0;
        this.bottom = n * n + 1;
        this.open = new boolean[n * n];
        this.openCount = 0;
        this.unionFind = new WeightedQuickUnionUF(n * n + 2);
    }

    /**
     * check that row and col arguments are within bounds.
     * Throws IndexOutOfBoundsException otherwise.
     * @param row row number
     * @param col column nunber
     */
    private void checkArgs(int row, int col)
    {
        if (row <= 0 || row > this.size)
            throw new IndexOutOfBoundsException("row index out of bounds");
        if (col <= 0 || col > this.size)
            throw new IndexOutOfBoundsException("col index out of bound");
    }

    /**
     * convert 2d (row,col) coordinates to site number
     * @param row row number
     * @param col column number
     * @return site number
     */
    private int xyTo1D(int row, int col)
    {
        checkArgs(row, col);
        return (row - 1) * size + col;
    }

    /**
     * open the site specified by 2d (row, col) coordinates
     * @param row row number
     * @param col column munber
     */
    public void open(int row, int col)
    {
        int i = xyTo1D(row, col);

        if (isOpen(row, col)) return;
        open[i - 1] = true;
        openCount += 1;

        if (row > 1)
        {
            int above = xyTo1D(row - 1, col);
            if (isOpen(above)) unionFind.union(above, i);
        } else
        {
            unionFind.union(top, i);
        }

        if (row < size)
        {
            int below = xyTo1D(row + 1, col);
            if (isOpen(below)) unionFind.union(below, i);
        } else
        {
            unionFind.union(bottom, i);
        }

        if (col > 1)
        {
            int left = xyTo1D(row, col - 1);
            if (isOpen(left)) unionFind.union(left, i);
        }
        if (col < size)
        {
            int right = xyTo1D(row, col + 1);
            if (isOpen(right)) unionFind.union(right, i);
        }

    }

    /**
     * check if site specified by 2d (row,col) coordinates is open
     * @param row row number
     * @param col column number
     * @return true if site is open
     */
    public boolean isOpen(int row, int col)
    {
        int i = xyTo1D(row, col);

        return isOpen(i);
    }

    /**
     * check if site specified by site number is open
     * @param i site number
     * @return true if site is open
     */
    private boolean isOpen(int i)
    {
        return open[i - 1];
    }

    /**
     * check if site is full (connected to the top)
     * @param row row number
     * @param col column number
     * @return true if site if full
     */
    public boolean isFull(int row, int col)
    {
        int i = xyTo1D(row, col);

        return unionFind.connected(top, i);
    }

    /**
     * @return number of open sites
     */
    public int numberOfOpenSites()
    {
        return openCount;
    }

    /**
     * @return true if grid percolates
     */
    public boolean percolates()
    {
        return unionFind.connected(top, bottom);
    }
}
