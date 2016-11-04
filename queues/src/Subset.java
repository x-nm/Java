/******************************************************************************
 *  Compilation:  javac Subset.java
 *  Execution:  echo A B C D E F G | java Subset k  
 *  Dependencies: 
 *  
 *  2016.03.16 by xnm 
 ******************************************************************************/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset {
   public static void main(String[] args){
	   int k = Integer.parseInt(args[0]);
	   RandomizedQueue<String> rand = new RandomizedQueue<String>();
	   String[] data;
	   data = StdIn.readAllStrings();
	   int N = data.length;
	   for (int i = 0; i < N; i++)
	   {
		   rand.enqueue(data[i]);
	   }
	   for (int i = 0; i < k; i++)
	   {
		   StdOut.println(rand.dequeue());
	   }
   }
   

}
