/******************************************************************************
 *  Compilation:  javac PercolationStats.java
 *  Execution:    java PercolationStats N T 
 *  Dependencies: Percolation.java
 *  
 *  grid size N;
 *  perform times T.
 *  
 *  2016.03.08 by xnm 
 *  (refined at 2016.10.9)
 ******************************************************************************/


import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;
import java.lang.Math; 

public class PercolationStats {
   private int n; //grid size
   private int t; //perform times
   private double[] perco_thres; //array stores the percolation threshold of each stimulation

   public PercolationStats(int N, int T)     // perform T independent experiments on an N-by-N grid
   {
   	   if (N <= 0 || T <= 0) throw new IllegalArgumentException("N or T is less than 1.");
   	   n = N;
   	   t = T;
   	   perco_thres = new double[t];
   	   for (int i = 0; i < t; i++)
   	   {
   	   	   Percolation perco = new Percolation(n);
   	   	   int count = 0;
   	   	   while (!perco.percolates())
   	   	   {
   	   	   	   int p, q; // or just do perco.open(StdRandom.uniform(1, n + 1),...)
   	   	   	   p = StdRandom.uniform(1, n+1);
   	   	   	   q = StdRandom.uniform(1, n+1);
   	   	   	   if (!perco.isOpen(p,q)) {
   	   	   		   perco.open(p , q);
   	   	   		   count++;
   	   	   	   }
  	   	   }
  	   	   perco_thres[i] = count * 1.0 / (n*n);
   	   } 
   }
   public double mean()                      // sample mean of percolation threshold
   {
   	   return StdStats.mean(perco_thres);
   }
   public double stddev()                    // sample standard deviation of percolation threshold
   {
   	   return StdStats.stddev(perco_thres);
   }
   public double confidenceLo()              // low  endpoint of 95% confidence interval
   {
   	   return mean() - 1.96 * stddev() / Math.sqrt(t);
   }
   public double confidenceHi()              // high endpoint of 95% confidence interval
   {
   	   return mean() + 1.96 * stddev() / Math.sqrt(t);
   }

   public static void main(String[] args)    // test client (described below)
   {
   	   int n = Integer.parseInt(args[0]);
   	   int t = Integer.parseInt(args[1]);
	   PercolationStats percoStats = new PercolationStats(n, t);
   	   System.out.println("mean = " + percoStats.mean());
   	   System.out.println("stddev = " + percoStats.stddev());
   	   System.out.println("95% confidence interval = " + percoStats.confidenceLo() + ", " + percoStats.confidenceHi());   	 
   }
}
