/*
public class Test {
	
	private static int n;
	private static boolean indexValidation(int i, int j) // throw an exception for invalid indices 
	{
	   	   try
	   	   {
	   	   	   if (i <= 0 || i > n ) throw new IndexOutOfBoundsException("row index i out of bounds");
	   	       if (j <= 0 || j > n ) throw new IndexOutOfBoundsException("column index j out of bounds");
	   	   }
	   	   catch (Exception e) return false;
	   	   return true;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		n = 3;
		System.out.println("hello world!");
		//indexValidation(1,1);
		System.out.println("hello world1");
		for (int i = 1 ; i < 5; i++)
		{
			if (!indexValidation(1,i)) break;
			System.out.println("hello " + i);
		}
		
		System.out.println("hello world2");
		
	}
}
//import edu.princeton.cs.algs4.StdRandom;
   //import edu.princeton.cs.algs4.StdStats;
   //import edu.princeton.cs.algs4.WeightedQuickUnionUF;

*/

//import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Test{
	private int n;
	public Test (int N)
	{
		n = N;
	}
	private int xyto1d(int i, int j) // change the 2-dim value to 1-dim
	   {
	   	   return (n * i + j);
	   }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 3;
		Test test = new Test(n);
		
		System.out.println("hello world2  " + test.xyto1d(3, 4));
		
	}
}























