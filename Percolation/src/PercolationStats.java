/*-----------------------------------------------------------------------------
*
*  Author:          Sergey Kleymenov
*  Written:         6/22/2017
*  Last Updated:    6/23/2017
*
*  Execute:         java PercolationStats n T
*
*  Percolation problem Monte Carlo simulation for the Programming Assignment 1
*  n - grid size
*  T - number of trials
*
------------------------------------------------------------------------------*/
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

import java.lang.Math;

public class PercolationStats
{
    private double[] pStars;                // array of p* from each trial
    private final double magicConst;        // to simplify 95% confidence interval
    private final int size;                 // grid size

    /**
     * perform trials independent experiments on an n-by-n grid
     * @param n grid size
     * @param trials number of trials
     */
    public PercolationStats(int n, int trials)
    {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();

        this.pStars = new double[trials];
        this.magicConst = 1.96 / Math.sqrt(trials);
        this.size = n;

        for (int i = 0; i < trials; i++)
        {
            pStars[i] = doTrial();
        }
    }

    /**
     * do a trial
     * @return p* of current trial
     */
    private double doTrial()
    {
        Percolation model = new Percolation(size);
        StdRandom.setSeed(java.lang.System.currentTimeMillis());
        while (!model.percolates())
        {
            int i = StdRandom.uniform(1, size + 1);
            int j = StdRandom.uniform(1, size + 1);
            model.open(i, j);
        }
        return ((double) model.numberOfOpenSites() / (double) (size * size));

    }

    /**
     * @return sample mean of percolation threshold
     */
    public double mean()
    {
        return StdStats.mean(pStars);
    }

    /**
     * @return sample standard deviation of percolation threshold
     */
    public double stddev()
    {
        return StdStats.stddev(pStars);
    }

    /**
     * @return low  endpoint of 95% confidence interval
     */
    public double confidenceLo()
    {
        return mean() - magicConst * stddev();
    }

    /**
     * @return high endpoint of 95% confidence interval
     */
    public double confidenceHi()
    {
        return mean() + magicConst * stddev();
    }

    /**
     * Test client. performs trials, prints results
     * @param args grid size and number of trials
     */
    public static void main(String[] args)
    {
        if (args.length != 2) throw new IllegalArgumentException("Provide exactly 2 int args");
        int size = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[0]);

        PercolationStats stats = new PercolationStats(size, trials);
        System.out.println("mean\t\t\t\t\t= " + stats.mean());
        System.out.println("stddev\t\t\t\t\t= " + stats.stddev());
        System.out.println("95% confidence interval\t= [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");


    }
}
