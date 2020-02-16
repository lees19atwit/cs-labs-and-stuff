/*
 * Dave Rosenberg
 * Comp 2000 - Data Structures
 * Lab: List ADT
 * Fall, 2019
 * 
 * Usage restrictions:
 * 
 * You may use this code for exploration, experimentation, and furthering your
 * learning for this course. You may not use this code for any other
 * assignments, in my course or elsewhere, without explicit permission, in
 * advance, from myself (and the instructor of any other course).
 * 
 * Further, you may not post or otherwise share this code with anyone other than
 * current students in my sections of this course. Violation of these usage
 * restrictions will be considered a violation of the Wentworth Institute of
 * Technology Academic Honesty Policy.
 *
 * Do not remove this notice.
 */

package edu.wit.dcsn.comp2000.listadt;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A class that implements the ADT list by using a chain of nodes that has both
 * a head reference and a tail reference. This implementation is
 * {@code Iterable} using an inner class iterator. Two additional methods are
 * required: {@code sort()} and {@code shuffle()}.
 * 
 * @author Frank M. Carrano
 * @author Timothy M. Henry
 * @version 4.0
 * @version 5.0
 * 
 * @author David M Rosenberg
 * @version 4.1.0.0 2018-07-11
 *          <ul>
 *          <li>initial version based upon Carrano and Henry implementation in
 *          the 4th edition of the textbook
 *          <li>modified per assignment
 *          </ul>
 * @version 5.1.0.0 2019-07-14
 *          <ul>
 *          <li>revise to match the 5th edition of the textbook
 *          <li>revise to match this semester's assignment
 *          <li>switch from 1-based to 0-based positions
 *          </ul>
 * 
 * @author Sunny Lee
 * @version 5.2.0.0 2019-11-12 implement sort() and shuffle()
 * 
 * @param <T> the class of objects to be stored in the list such that T
 *            implements the Comparable interface.
 */
public class IterableLinkedList<T extends Comparable<? super T>> implements ListInterface<T>, Iterable<T> {

	/*
	 * instance variables
	 */
	private Node firstNode; // Head reference to first node
	private Node lastNode; // Tail reference to last node
	private int numberOfEntries; // Number of entries in list

	/*
	 * constructor(s)
	 */

	/**
	 * 
	 */
	public IterableLinkedList() {
		initializeDataFields();

	} // end default constructor

	/*
	 * public API methods
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.wit.dcsn.comp2000.listadt.ListInterface#add(java.lang.Comparable)
	 */
	@Override
	public void add(T newEntry) {
		// rather than duplicating code, invoke the positional method specifying
		// the position immediately after the end of the list
		add(this.numberOfEntries, newEntry);

	} // end add()

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.wit.dcsn.comp2000.listadt.ListInterface#add(int,
	 * java.lang.Comparable)
	 */
	@Override
	public void add(int givenPosition, T newEntry) {
		if ((givenPosition >= 0) && (givenPosition <= this.numberOfEntries)) {
			Node newNode = new Node(newEntry);

			if (isEmpty()) { // only (first and last) entry in empty list
				this.firstNode = newNode;
				this.lastNode = newNode;
			} else if (givenPosition == 0) { // first entry in non-empty list
				newNode.setNextNode(this.firstNode);
				this.firstNode = newNode;
			} else if (givenPosition == this.numberOfEntries) { // last entry in non-empty list
				this.lastNode.setNextNode(newNode);
				this.lastNode = newNode;
			} else { // intermediate entry in non-empty list
				Node nodeBefore = getNodeAt(givenPosition - 1);
				Node nodeAfter = nodeBefore.getNextNode();
				newNode.setNextNode(nodeAfter);
				nodeBefore.setNextNode(newNode);
			} // end if

			this.numberOfEntries++;
		} else {
			// givenPosition was out of range
			throw new IndexOutOfBoundsException("Illegal position given to add operation.");
		}

	} // end add()

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.wit.dcsn.comp2000.listadt.ListInterface#clear()
	 */
	@Override
	public void clear() {
		initializeDataFields();

	} // end clear()

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.wit.dcsn.comp2000.listadt.ListInterface#contains(java.lang.
	 * Comparable)
	 */
	@Override
	public boolean contains(T anEntry) {
		boolean found = false;
		Node currentNode = this.firstNode;

		// loop until we've found a matching entry or run out of entries to
		// check
		while (!found && (currentNode != null)) {
			if (currentNode.getData().equals(anEntry)) {
				found = true;
			} else {
				currentNode = currentNode.getNextNode();
			}
		} // end while

		return found;

	} // end contains()

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.wit.dcsn.comp2000.listadt.ListInterface#getEntry(int)
	 */
	@Override
	public T getEntry(int givenPosition) {
		if ((givenPosition >= 0) && (givenPosition < this.numberOfEntries)) {
			// return the data stored at the specified position
			return getNodeAt(givenPosition).getData();

		}

		// givenPosition was out of range
		throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");

	} // end getEntry()

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.wit.dcsn.comp2000.listadt.ListInterface#getLength()
	 */
	@Override
	public int getLength() {
		return this.numberOfEntries;

	} // end getLength()

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.wit.dcsn.comp2000.listadt.ListInterface#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		// since we have the count of entries, simple to test it
		return this.numberOfEntries == 0;

	} // end isEmpty()

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<T> iterator() {
		return new IterableLinkedListIterator();

	} // end iterator()

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.wit.dcsn.comp2000.listadt.ListInterface#remove(int)
	 */
	@Override
	public T remove(int givenPosition) {
		// the entry we'll return; initially none
		T result = null;

		if ((givenPosition >= 0) && (givenPosition < this.numberOfEntries)) {
			if (givenPosition == 0) { // removing first entry
				// capture the data to return to the caller
				result = this.firstNode.getData();

				// move past the first entry to remove it from the chain
				this.firstNode = this.firstNode.getNextNode();

				// if this is the only entry, clear the last pointer too
				if (this.numberOfEntries == 1) {
					this.lastNode = null;
				}
			} else { // removing an entry that isn't first
				// get a reference to the node before the one we're removing
				Node nodeBefore = getNodeAt(givenPosition - 1);

				// capture the data to return to the caller
				Node nodeToRemove = nodeBefore.getNextNode();
				result = nodeToRemove.getData();

				// update the node before's next pointer to skip the one we're
				// removing
				nodeBefore.setNextNode(nodeToRemove.getNextNode());

				// are we removing the last entry?
				if (givenPosition == this.numberOfEntries - 1) { // yes - need to update the reference to the now last
																	// node
					this.lastNode = nodeBefore;
				}
			} // end if

			// we removed an entry - adjust the counter
			this.numberOfEntries--;
		} else {
			// givenPosition was out of range
			throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
		}

		// return removed entry's data
		return result;

	} // end remove()

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.wit.dcsn.comp2000.listadt.ListInterface#replace(int,
	 * java.lang.Comparable)
	 */
	@Override
	public T replace(int givenPosition, T newEntry) {
		if ((givenPosition >= 0) && (givenPosition < this.numberOfEntries)) {
			// locate the specified node
			Node desiredNode = getNodeAt(givenPosition);

			// capture the original/prior data to return to the caller
			T originalEntry = desiredNode.getData();

			// save the new data
			desiredNode.setData(newEntry);

			// return the prior data
			return originalEntry;

		}

		// givenPosition was out of range
		throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
	} // end replace()

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.wit.dcsn.comp2000.listadt.ListInterface#shuffle()
	 */
	public void shuffle() {
		if(isEmpty() || numberOfEntries == 1) {}
		else 
		{	
			int firstHalf = (numberOfEntries/2)-1; 
			int secondHalf = firstHalf+1; 
			System.out.println(firstHalf);
			Node first = firstNode; 
			Node second = getNodeAt(secondHalf); 
			
			System.out.println(first.getData());
			System.out.println(second.getData());
			getNodeAt(firstHalf).setNextNode(null); 
			firstNode = null; 
			lastNode = null; 
			interleave(first, second); 
		}
		
	} // end shuffle()
	
	public void interleave(Node first, Node second)
	{
		firstNode = first; 
		lastNode = first; 
		
		Node firstHalf = first.getNextNode(); 
		Node secondHalf = second; 
		
		System.out.println(first.getData());
		first.setNextNode(null); 
		first.setNextNode(secondHalf); 
		System.out.println(first.getNextNode().getData());
		secondHalf = secondHalf.getNextNode(); 
		lastNode = first.getNextNode();
		first = first.getNextNode(); 
		
		if(numberOfEntries % 2 == 0)
		{
			while( (firstHalf.getNextNode() != null) || (secondHalf.getNextNode() != null) )
			{
				System.out.println(firstHalf.getNextNode());
				System.out.println(secondHalf.getNextNode());
				first.setNextNode(null); 
				first.setNextNode(firstHalf); 
				//System.out.println(first.getNextNode().getData());
				firstHalf = firstHalf.getNextNode(); 
				lastNode = first.getNextNode(); 
				first = first.getNextNode(); 
				first.setNextNode(null); 
				first.setNextNode(secondHalf); 
				//System.out.println(first.getNextNode().getData());
				secondHalf = secondHalf.getNextNode(); 
				lastNode = first.getNextNode();
				first = first.getNextNode(); 
			}
		} else 
		{
			System.out.println("odd"); 
			while( (firstHalf.getNextNode() != null) || (secondHalf.getNextNode() != null) )
			{
				System.out.println(firstHalf.getNextNode());
				System.out.println(secondHalf.getNextNode());
				first.setNextNode(null); 
				first.setNextNode(firstHalf); 
				System.out.println(first.getNextNode().getData());
				firstHalf = firstHalf.getNextNode(); 
				lastNode = first.getNextNode(); 
				first = first.getNextNode(); 
				first.setNextNode(null); 
				first.setNextNode(secondHalf); 
				System.out.println(first.getNextNode().getData());
				secondHalf = secondHalf.getNextNode(); 
				lastNode = first.getNextNode();
				first = first.getNextNode(); 
			}
			
		}
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.wit.dcsn.comp2000.listadt.ListInterface#sort()
	 */
	@Override
	public void sort() {
		if(numberOfEntries > 1)
		{
			Node unsortedPart = firstNode.getNextNode(); 
			firstNode.setNextNode(null); 
			while(unsortedPart != null)
			{
				Node nodeToInsert = unsortedPart; 
				unsortedPart = unsortedPart.getNextNode(); 
				insertInOrder(nodeToInsert); 
			}
		}
		
	} // end sort()

	private void insertInOrder(Node nodeToInsert)
	{
		T item = nodeToInsert.getData(); 
		Node currentNode = firstNode; 
		Node previousNode = null; 
		while( (currentNode != null) && (item.compareTo(currentNode.getData()) >= 0) )
		{
			previousNode = currentNode; 
			currentNode = currentNode.getNextNode(); 
		}
		
		if(previousNode != null)
		{
			previousNode.setNextNode(nodeToInsert); 
			nodeToInsert.setNextNode(currentNode); 
		} else 
		{
			nodeToInsert.setNextNode(firstNode); 
			firstNode = nodeToInsert; 
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.wit.dcsn.comp2000.listadt.ListInterface#toArray()
	 */
	@Override
	public T[] toArray() {
		// The cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Comparable<?>[this.numberOfEntries];

		// start at the first position in the chain and in the array
		Node currentNode = this.firstNode;
		int index = 0;

		while ((index < this.numberOfEntries) && (currentNode != null)) {
			// copy the data from the node into the array
			result[index] = currentNode.getData();

			// move to the next node and next array index
			currentNode = currentNode.getNextNode();
			index++;
		} // end while

		return result;

	} // end toArray()

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return Arrays.toString(toArray());

	} // end toString()

	/*
	 * private utility methods
	 */

	/**
	 * Returns a reference to the node at a given position.
	 * <p>
	 * Precondition: The chain is not empty;
	 * {@code 0 <= givenPosition < numberOfEntries}.
	 * 
	 * @param givenPosition in the range 0..number of entries - 1
	 * @return reference to the {@code Node} at the specified position in the
	 *         {@code List}
	 */
	private Node getNodeAt(int givenPosition) {
		// position to first node
		Node currentNode = this.firstNode;

		if (givenPosition == this.numberOfEntries - 1) {
			// last node is requested
			currentNode = this.lastNode;
		} else if (givenPosition > 0) {
			// desired node isn't at either end
			// traverse the chain to locate the desired node
			for (int counter = 0; counter < givenPosition; counter++) {
				currentNode = currentNode.getNextNode();
			}
		} // end if

		return currentNode;

	} // end getNodeAt()

	/**
	 * Initializes the class's data fields to indicate an empty list.
	 */
	private void initializeDataFields() {
		this.firstNode = null;
		this.lastNode = null;
		this.numberOfEntries = 0;

	} // end initializeDataFields()

	/**
	 * A link in the chain.
	 */
	private class Node {
		/*
		 * instance variables
		 */
		private T data; // Data portion
		private Node next; // Next to next node

		// Note: the constructors can be public or private

		/**
		 * @param dataPortion the application data
		 */
		private Node(T dataPortion) {
			this(dataPortion, null);

		} // end 1-arg constructor

		/**
		 * @param dataPortion the application data
		 * @param nextNode    reference to the next {@code Node}
		 */
		private Node(T dataPortion, Node nextNode) {
			this.data = dataPortion;
			this.next = nextNode;

		} // end 2-arg constructor

		/**
		 * @return the application data
		 */
		private T getData() {
			return this.data;

		} // end getData()

		/**
		 * @param newData the application data
		 */
		private void setData(T newData) {
			this.data = newData;

		} // end setData()

		/**
		 * @return reference to the next {@code Node}
		 */
		private Node getNextNode() {
			return this.next;

		} // end getNextNode()

		/**
		 * @param nextNode reference to the new next {@code Node}
		 */
		private void setNextNode(Node nextNode) {
			this.next = nextNode;

		} // end setNextNode()

	} // end inner class Node

	/**
	 * Basic Iterator
	 */
	private class IterableLinkedListIterator implements Iterator<T> {

		/*
		 * instance variable(s)
		 */
		private Node nextNode;

		/**
		 * Set initial position to the beginning of the (@code List}
		 */
		private IterableLinkedListIterator() {

			this.nextNode = IterableLinkedList.this.firstNode;

		} // end constructor

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			return this.nextNode != null;

		} // end hasNext()

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#next()
		 */
		@Override
		public T next() {
			T result = null;

			if (hasNext()) {
				result = this.nextNode.getData();
				this.nextNode = this.nextNode.getNextNode();
			} else {
				throw new NoSuchElementException("Illegal call to next(); " + "iterator is after end of list.");
			}

			return result;

		} // end next()

	} // end inner class IterableLinkedListIterator

	/**
	 * Test driver - you may enhance or replace these tests
	 * 
	 * @param args -unused-
	 */
	public static void main(String[] args) {
		System.out.println("instantiate the list");
		IterableLinkedList<Integer> testILL = new IterableLinkedList<>();

		System.out.println("populate the list (1..20)");
		for (int i = 1; i <= 20; i++) {
			testILL.add(i);
		}
		
		System.out.println(testILL);
		
		testILL.shuffle();
		System.out.println(testILL);
		/*
		
		try {
			System.out.println("toArray():");
			Comparable<Integer>[] testILLContents = testILL.toArray();
			System.out.println(Arrays.toString(testILLContents));
			System.out.println("1st element: " + testILLContents[0]);

			boolean firstTime = true;
			System.out.print("[");
			for (Integer anInteger : testILL) {
				System.out
						.print((firstTime ? "" : ", ") + anInteger + " <" + anInteger.getClass().getSimpleName() + ">");
				firstTime = false;
			}
			System.out.println("]");
		} catch (Exception e) {
			System.out.println("Exception saving toArray() to Integer[]: " + e.getMessage());
		}

		System.out.println("The 1st entry is 1: " + (testILL.getEntry(0) == 1));
		System.out.println("The 10th entry is 10: " + (testILL.getEntry(9) == 10));
		System.out.println("The last entry is 20: " + (testILL.getEntry(testILL.getLength() - 1) == 20));

		System.out.println("Sorted:");
		testILL.sort();
		System.out.println(testILL);

		System.out.println("Shuffled:");
		testILL.shuffle();
		System.out.println(testILL);

		System.out.println("Sorted:");
		testILL.sort();
		System.out.println(testILL);

		System.out.println("Shuffled:");
		testILL.shuffle();
		System.out.println(testILL);

		System.out.println("Sorted:");
		testILL.sort();
		System.out.println(testILL);

		System.out.println("remove last entry (20): " + testILL.remove(19));
		System.out.println(testILL);

		System.out.println("remove middle entry (10): " + testILL.remove(9));
		System.out.println(testILL);

		System.out.println("remove last entry (19): " + testILL.remove(testILL.getLength() - 1));
		System.out.println(testILL);

		System.out.println("Sorted:");
		testILL.sort();
		System.out.println(testILL);

		System.out.println("Shuffled:");
		testILL.shuffle();
		System.out.println(testILL);

		System.out.println("Sorted:");
		testILL.sort();
		System.out.println(testILL);

		System.out.println("testing iterator");
		boolean firstTime = true;
		System.out.print("[");
		for (Integer anInteger : testILL) {
			System.out.print((firstTime ? "" : ", ") + anInteger);
			firstTime = false;
		}
		System.out.println("]");

		System.out.println("insert in first position (0)");
		testILL.add(0, 0);
		System.out.println(testILL);

		System.out.println("insert in middle position (-1)");
		testILL.add(testILL.getLength() / 2, -1);
		System.out.println(testILL);

		System.out.println("clear the list");
		testILL.clear();
		System.out.println(testILL);

		System.out.println("Sorted:");
		testILL.sort();
		System.out.println(testILL);

		System.out.println("Shuffled:");
		testILL.shuffle();
		System.out.println(testILL);

		System.out.println("Sorted:");
		testILL.sort();
		System.out.println(testILL);

		System.out.println("testing iterator");
		firstTime = true;
		System.out.print("[");
		for (Integer anInteger : testILL) {
			System.out.print((firstTime ? "" : ", ") + anInteger);
			firstTime = false;
		}
		System.out.println("]");

		System.out.println("insert in first position (555)");
		testILL.add(0, 555);
		System.out.println(testILL);

		System.out.println("Sorted:");
		testILL.sort();
		System.out.println(testILL);

		System.out.println("Shuffled:");
		testILL.shuffle();
		System.out.println(testILL);

		System.out.println("Sorted:");
		testILL.sort();
		System.out.println(testILL);

		System.out.println("testing iterator");
		firstTime = true;
		System.out.print("[");
		for (Integer anInteger : testILL) {
			System.out.print((firstTime ? "" : ", ") + anInteger);
			firstTime = false;
		}
		System.out.println("]");

		System.out.println("insert in first position (-555)");
		testILL.add(0, -555);
		System.out.println(testILL);

		System.out.println("Sorted:");
		testILL.sort();
		System.out.println(testILL);

		System.out.println("Shuffled:");
		testILL.shuffle();
		System.out.println(testILL);

		System.out.println("Sorted:");
		testILL.sort();
		System.out.println(testILL);

		System.out.println("testing iterator");
		firstTime = true;
		System.out.print("[");
		for (Integer anInteger : testILL) {
			System.out.print((firstTime ? "" : ", ") + anInteger);
			firstTime = false;
		}
		System.out.println("]");

		System.out.println("clear the list");
		testILL.clear();
		System.out.println(testILL);

		System.out.println("repopulate the list with 13 elements inserted in the middle");
		for (int i = 1; i <= 13; i++) {
			testILL.add(testILL.getLength() / 2, i);
		}
		System.out.println(testILL);

		System.out.println("Sorted:");
		testILL.sort();
		System.out.println(testILL);

		System.out.println("Shuffled:");
		testILL.shuffle();
		System.out.println(testILL);

		System.out.println("Sorted:");
		testILL.sort();
		System.out.println(testILL);

		System.out.println("testing iterator");
		firstTime = true;
		System.out.print("[");
		for (Integer anInteger : testILL) {
			System.out.print((firstTime ? "" : ", ") + anInteger);
			firstTime = false;
		}
		System.out.println("]");

		System.out.println("clear the list");
		testILL.clear();
		System.out.println(testILL);

		System.out.println("repopulate the list with 7 17s inserted in the middle");
		for (int i = 1; i <= 7; i++) {
			testILL.add(testILL.getLength() / 2, 17);
		}
		System.out.println(testILL);

		System.out.println("Sorted:");
		testILL.sort();
		System.out.println(testILL);

		System.out.println("Shuffled:");
		testILL.shuffle();
		System.out.println(testILL);

		System.out.println("Sorted:");
		testILL.sort();
		System.out.println(testILL);
		
		*/
	} // end main()

} // end class IterableLinkedList
