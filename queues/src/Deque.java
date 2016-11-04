/******************************************************************************
 *  Compilation:  javac Deque.java
 *  Execution:    in use:
 *  				Deque<type> myDeque = new Deque<type>()
 *  
 *  2016.03.16 by xnm 
 *  (refined at 2016.10.10)
 ******************************************************************************/



import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {
	/*
	 *linked-list implementation is required.
	 */

	private Node first;
	private Node last;
	private int N; // size of the deque

	private class Node{
		private Item item;
		private Node next;
	}

	public Deque() // construct an empty deque
	{
		first = null;
		last = null;
		N = 0;
	}

	public boolean isEmpty() // is the deque empty?
	{
		return (first == null || last == null); //= =
	}

	public int size() // return the number of items on the deque
	{
		return N;
	}

	public void addFirst(Item item) // add the item to the front
	{
		if (item == null) throw new NullPointerException();
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		if (isEmpty()) last = first;
		N++;
	}

	public void addLast(Item item) // add the item to the end
	{
		if (item == null) throw new NullPointerException();
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if (isEmpty()) first = last;
		else oldlast.next = last;
		N++;
	}

	public Item removeFirst() // remove and return the item from the front
	{
		if (isEmpty()) throw new java.util.NoSuchElementException("The deque is empty.");
		Item item = first.item;
		first = first.next;
		if (isEmpty()) last = first;
		N--;
		return item;
	}

	public Item removeLast() // remove the item from the addLast
	{
		if (isEmpty()) throw new java.util.NoSuchElementException("The deque is empty.");
		Item item = last.item;
		if (last == first) first = last = null; //
		else
		{
			Node i = first;
			while (i.next != last) i = i.next;
			last = i;
		}
		N--;
		return item;
	}

	public Iterator<Item> iterator()  // return an iterator over items in order from front to end
	{
		return new DequeIterator();
	}

	private class DequeIterator implements Iterator<Item> {
		private Node current = first;
		public boolean hasNext() { return current != null; }
		public void remove() { throw new UnsupportedOperationException(); }
		public Item next()
		{
			if (!hasNext()) throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}

	}

	public static void main(String[] args) // unit testing
	{
		Deque<Integer> deque = new Deque<Integer>();
		for (int i = 0; i < 10; i++) deque.addFirst(i);
		for (int i = 0; i < 10; i++) StdOut.print(deque.removeLast());
		StdOut.println("\nn = " + deque.size());
		for (int i = 0; i < 10; i++) deque.addFirst(i);
		//for (int i = 0; i < 10; i++) StdOut.print(deque.removeFirst());
		Iterator<Integer> i = deque.iterator();
		StdOut.println("n = " + deque.size());
		while (i.hasNext())
		{
			Integer test = i.next();
			StdOut.print(test);
		}
	}

}
