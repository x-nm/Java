/******************************************************************************
 *  Compilation:  javac RandomizedQueue.java
 *  Execution:    in use: 
 *  				RandomizedQueue<String> rand = new RandomizedQueue<String>()
 *  
 *  2016.03.16 by xnm 
 ******************************************************************************/


import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

public class RandomizedQueue<Item> implements Iterable<Item>
{
	private int N; // number of items on the queue;
	private Item[] a; // array of items;

	public RandomizedQueue() // construct an empty randomized queue;
	{
		N = 0;
		a = (Item[]) new Object[2]; // cast is required.
	}

	public boolean isEmpty() // is the queue empty?
	{
		return N == 0;
	}

	public int size() // return the number of items on the queue
	{
		return N;
	}

	private void resize(int capacity)
	{
		assert capacity >= N;
		Item[] temp = (Item[]) new Object[capacity];
		for (int i = 0, j = 0; i< N; i++)
		{
			while(a[j] == null) j++;
			temp[i] = a[j];
			j++;
		}
		a = temp;
	}

	public void enqueue(Item item) // add the item
	{
		if (item == null) throw new NullPointerException();
		if (N == a.length) resize(2*a.length);
		a[N++] = item;
		//a[N] = item;
		//N++;
	}

	public Item dequeue() // remove and return a random item
	{
		if (isEmpty()) throw new NoSuchElementException("The queue is Empty."); 
		int p = StdRandom.uniform(a.length); // note it is not uniform(N)
		while (a[p] == null) p = StdRandom.uniform(a.length);
		N--;
		Item item = a[p];
		a[p] = null;
		if (N > 0 && N == a.length/4) resize(a.length/2);
		return item;
	}

	public Item sample() // return (but do not remove) a random item
	{
		if (isEmpty()) throw new NoSuchElementException("The queue is Empty."); 
		int p = StdRandom.uniform(a.length);
		while (a[p] == null) p = StdRandom.uniform(a.length);
		return a[p];
	}

	public Iterator<Item> iterator() // return a iterator over items in random
	{ return new RandomizedQueueIterator(); }

	private class RandomizedQueueIterator implements Iterator<Item>
	{
		private int size;
		private Item[] temp;
		private int count;
		public RandomizedQueueIterator()
		{
			size = N;
			temp = (Item[]) new Object[size];
			count = N;
			for (int i = 0, j = 0; i < size; i++)
			{
				while(a[j] == null) j++;
				temp[i] = a[j];
				j++;
			}
		}
		public boolean hasNext() { return count!=0; }
		public void remove() { throw new UnsupportedOperationException(); } // ???
		public Item next() 
		{
			if (!hasNext()) throw new NoSuchElementException();
			int p = StdRandom.uniform(size);
			while (temp[p] == null) p = StdRandom.uniform(N);
			Item item = temp[p];
			temp[p] = null;
			count--;
			return item;
		}
	}

	public static void main(String[] args) // unit testing
	{
		RandomizedQueue<Integer> rand = new RandomizedQueue<Integer>();
		for (int i = 1; i < 10; i++) rand.enqueue(i);
		for (int i = 0; i < 5; i++) StdOut.print(rand.dequeue()+",");
		StdOut.println();
		StdOut.print("n = " + rand.size());
		StdOut.println();
		for (int i = 2; i < 10; i++) rand.enqueue(i);
		Iterator<Integer> i = rand.iterator();
		Iterator<Integer> p = rand.iterator();
		StdOut.print("n = " + rand.size()+"\n");
		while (i.hasNext())
		{
			Integer test = i.next();
			StdOut.print(test+",");
		}
		StdOut.println();
		while (p.hasNext())
		{
			Integer test = p.next();
			StdOut.print(test+",");
		}

	}





}
