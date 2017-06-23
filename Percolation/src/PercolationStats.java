/*
 * Created by Sergey Kleymenov on 23/06/2017.
 */
import java.lang.IllegalArgumentException;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

import java.lang.Math;
public class PercolationStats {
    private double[] pStars;
    private final double magicConst;
    private final int size;

    private double doTrial()
    {
        Percolation model = new Percolation(size);
        StdRandom.setSeed(java.lang.System.currentTimeMillis());
        while (! model.percolates())
        {
            int i = StdRandom.uniform(1, size + 1);
            int j = StdRandom.uniform(1, size + 1);
            model.open(i, j);
        }
        return ((double)model.numberOfOpenSites() / (double)(size*size));

    }
    public PercolationStats(int n, int trials) // perform trials independent experiments on an n-by-n grid
    {
        if (n<=0 || trials <= 0) throw new IllegalArgumentException();

        this.pStars = new double[trials];
        this.magicConst = 1.96 / Math.sqrt(trials);
        this.size = n;

        for ( int i = 0; i < trials; i++)
        {
            pStars[i] = doTrial();
        }
    }
    public double mean()                         // sample mean of percolation threshold
    {
        return StdStats.mean(pStars);
    }
    public double stddev()                        // sample standard deviation of percolation threshold
    {
        return StdStats.stddev(pStars);
    }
    public double confidenceLo()                  // low  endpoint of 95% confidence interval
    {
        return mean() - magicConst * stddev();
    }
    public double confidenceHi()                  // high endpoint of 95% confidence interval
    {
        return mean() + magicConst * stddev();
    }

    public static void main(String[] args)        // test client (described below)
    {
        if (args.length != 2) throw new IllegalArgumentException("Provide exactly 2 int args");
        int size = Integer.valueOf(args[0]);
        int trials = Integer.valueOf(args[0]);

        PercolationStats stats = new PercolationStats(size, trials);
        System.out.println("mean\t\t\t\t\t= " + stats.mean());
        System.out.println("stddev\t\t\t\t\t= " + stats.stddev());
        System.out.println("95% confidence interval\t= [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");


    }
}
