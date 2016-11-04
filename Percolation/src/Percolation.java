/******************************************************************************
 *  Compilation:  javac Percolation.java
 *  Execution:    it's used in PercolationStats (java PercolationStats N T) 
 *  
 *  2016.03.08 by xnm 
 *  (refined at 2016.10.9)
 ******************************************************************************/

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
   /*
    * 1-based (1~n) is demanded; but the array index is still 0~n-1;
    * transformation is operated to achieve the consensus:
    *
    * for all methods, the inputs are 1-based;
    * (indexValidation: [1,n])
    * only in grid, we have grid[i-1][j-i].
    * (the 2-d array grid[][] is 0-based)
    * 
    * UF size: 2+n^2;
    * top and bottom virtual node: top = 0 and bottom = n^2+1;
    * when we change x,y to the 1-d array in UF, we make it 1-based, from 1~n^2;
    * (xyto1d input: 1-based x,y; output: 1~n^2)
    * 
    * deal with corner in managePeriphery(i, j, p, q),
    * as p, q (periphery coordinate) might be 0 or n+1;
	*/
   private int n; // the dim of the grid
   private WeightedQuickUnionUF UF; // an union and find data structure, size n^2+2
   private int[][] grid; // Whether the grid is open
   private int top , bottom; //the top and bottom virtual node 
   
   public Percolation(int N)               // create N-by-N grid, with all sites blocked
   {
	   if (N <= 0) throw new IllegalArgumentException("N is less than 1.");   
   	   n = N;
   	   UF = new WeightedQuickUnionUF(n * n + 2);
   	   top = 0;
   	   bottom = n * n + 1;
	   grid = new int[n][n];
	   for (int i = 0; i < n; i++ )
	   {
		   for (int j = 0; j < n; j++){
			   grid[i][j] = 0; //0 means block, 1 means open.
		   }
	   }	   	   
   }

   private int xyto1d(int i, int j) // change the 2-dim value to 1-dim, inputs are 1-based; and change to 1~n^2 
   {
   	   return (n * (i - 1) + j);
   }
   
   private void indexValidation(int i, int j) // throw an exception for invalid indices; 1-based;
   {
   	   if (i <= 0 || i > n ) throw new IndexOutOfBoundsException("row index i out of bounds");
       if (j <= 0 || j > n ) throw new IndexOutOfBoundsException("column index j out of bounds");
   }
   
   private void managePeriphery(int i, int j, int p, int q) //deal with the 4 grids around; 0-based;
   {
	   if (p <= 0 || p > n || q <= 0 || q > n ) return;
	   
   	   switch(grid[p - 1][q - 1]){
   	   	   case 0: break; //if the grid is block, skip
   	   	   case 1: UF.union(xyto1d(i,j),xyto1d(p,q)); break; //if the grid is open, union them
   	   	   default: break; //in the default case, the grid is block
   	   }
   }

   public void open(int i, int j)          // open site (row i, column j) if it is not open already
   {		
       indexValidation(i,j);
 	   if (!isOpen(i,j))
   	   {
   	   	   grid[i - 1][j - 1] = 1;
   	   	   if (i == 1) UF.union(xyto1d(i, j), top);
   	   	   if (i == n) UF.union(xyto1d(i, j), bottom);
   	   	   managePeriphery(i, j, i-1, j);
   	   	   managePeriphery(i, j, i+1, j);
   	   	   managePeriphery(i, j, i, j-1);
   	   	   managePeriphery(i, j, i, j+1);     
   	   } 
   }

   public boolean isOpen(int i, int j)     // is site (row i, column j) open?
   {
	  indexValidation(i,j);
      if (grid[i - 1][j - 1] == 1) return true;
      return false;
   }

   public boolean isFull(int i, int j)     // is site (row i, column j) full?
   {
	  indexValidation(i,j);
      if (UF.connected(top, xyto1d(i, j))) return true;
      return false;
   }
   
   public boolean percolates()             // does the system percolate?
   {
   	   if (UF.connected(top,bottom)) return true;
   	   return false;
   }
   
   public static void main(String[] args)  // test client (optional)
   {
	   int n = 4;
	   Percolation perco = new Percolation(n); 	 
   	   perco.open(2,2);
   	   perco.open(3,2);
   	   System.out.println("A:");
   	   if (perco.UF.connected(perco.xyto1d(2, 2), perco.xyto1d(3, 2))) System.out.println("is connected");
   	   else System.out.println("is not connected");
   	   perco.open(1, 1);
   	   perco.open(4, 4);
   	   System.out.println("B:");
   	   if (perco.UF.connected(perco.xyto1d(1, 1), perco.xyto1d(4, 4))) System.out.println("is connected");
	   else System.out.println("is not connected");
   }
}

