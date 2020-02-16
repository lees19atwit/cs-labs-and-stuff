/*
 * Dave Rosenberg
 * Comp 2000 - Data Structures
 * Lab: Bag ADT
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

/*
 * Note:
 *  - This project requires Eclipse 2019-03 or later.
 * 
 * You must:
 *  - replace 'Your Name' with your actual name in the Javadoc comment block
 *      describing the class (below)
 *  - add version information to the @version tag in the same comment block
 *  - rename the project:
 *      - replace 'username' with your WIT username (the part of your email
 *          address to the left of @WIT.edu [for example, my project will be
 *              DS - 1 Bag ADT - RosenbergD (2019-3fa)
 *  - implement all methods
 *  - delete the TODO comments
 *  - use Node's methods to access and manipulate the Node's instance variables
 *      (data, next) - you are not permitted to access them directly:
 *          use currentNode.getData() instead of currentNode.data
 *  - qualify all instance variable references with 'this.'
 *  
 *  You may not:
 *  - use any Java Class Library (JCL) classes or methods other than:
 *      StringBuilder - in toString() to construct the output
 *      String - you may construct and use strings but you aren't permitted to
 *              use any String methods
 *      Arrays - you need to instantiate and manipulate arrays in toArray()
 *              but you aren't permitted to use any Array/Arrays methods
 *  
 *  You should:
 *  - find the appropriate code in the textbook and copy it
 *  - the cloning constructor, difference(), intersection(), and union() are
 *      not in the book
 */

package edu.wit.dcsn.comp2000.bagadt;

/**
 * A class of bags whose entries are stored in a chain of linked nodes. The bag
 * is never full.
 *
 * @author Frank M. Carrano
 * @author Timothy M. Henry
 * @version 4.1
 * 
 * @author David M Rosenberg
 * @version 4.2.0 2016-01-10 Reformat and revise
 * @version 4.3.0 2019-05-12 Add 'cloning' constructor, difference(),
 *          intersection(), union()
 * 
 * @author Sunny Lee
 * @version 5.0.0 2019-09-26 implements intersection/union/difference and the 1
 *          arg constructor
 *
 * @param <T> The class of items the LinkedBag will hold.
 */
public class LinkedBag<T> implements BagInterface<T> {

	/*
	 * instance variables
	 */
	private Node firstNode; // reference to first node
	private int numberOfEntries; // count of entries in use

	/*
	 * constructors
	 */

	/**
	 * Initializes an empty bag
	 */
	public LinkedBag() {

	} // end no-arg constructor

	/**
	 * Initialize a new LinkedBag and populate it with the contents of
	 * {@code sourceBag}
	 * 
	 * @param sourceBag another bag containing zero or more entries to copy to the
	 *                  newly instantiated LinkedBag <br>
	 *                  Note: {@code sourceBag} is an instance of any class that
	 *                  implements {@code BagInterface} - it doesn't have to be
	 *                  another {@code LinkedBag}
	 */
	public LinkedBag(BagInterface<T> sourceBag) {
		this(); // default constructor

		if (!(sourceBag == null) && sourceBag.getCurrentSize() >= 0) // checks to see if the Bag parameter is empty or
																		// null
		{
			// variables
			LinkedBag<T> newBag = new LinkedBag<T>();
			T[] sourceBagContents = sourceBag.toArray();

			// add whatever is in the sourceBag to this bag
			for (int i = 0; i < sourceBagContents.length; i++) {
				this.add(sourceBagContents[i]); // add the element in sourceBagContents
			}
		}
	} // end 1-arg 'cloning' constructor

	/*
	 * public LinkedBag API methods
	 */

	/**
	 * Adds a new entry to this bag.
	 * 
	 * @param newEntry The object to be added as a new entry.
	 * @return True.
	 */
	@Override
	public boolean add(T newEntry) {
		Node newNode = new Node(newEntry);
		newNode.next = firstNode;

		firstNode = newNode;
		numberOfEntries++;

		return true;

	} // end add()

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.wit.dcsn.comp2000.bagadt.BagInterface#clear()
	 */
	@Override
	public void clear() {
		while (!isEmpty())
			remove();

	} // end clear()

	/**
	 * Checks to see if anEntry equals is found in currentNode
	 * 
	 * @param anEntry
	 * @return found
	 */
	@Override
	public boolean contains(T anEntry) {
		boolean found = false;
		Node currentNode = firstNode;
		while (!found && (currentNode != null)) {
			if (anEntry.equals(currentNode.data))
				found = true;
			else
				currentNode = currentNode.next;
		}

		return found;

	} // end contains()

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.wit.dcsn.comp2000.bagadt.BagInterface#difference(edu.wit.dcsn.
	 * comp2000.bagadt.BagInterface)
	 */
	@Override
	public BagInterface<T> difference(BagInterface<T> anotherBag) {

		// variables
		BagInterface<T> resultBag = new LinkedBag<T>(this);
		T[] anotherBagContents = anotherBag.toArray();

		// check this bag and anotherBag to see which paramters are the same
		for (int i = 0; i < anotherBagContents.length; i++) {
			if (resultBag.contains(anotherBagContents[i])) // check if result bag contains
															// element from anotherBagContents
				resultBag.remove(anotherBagContents[i]); // removes the element
		}

		return resultBag;

	} // end difference()

	/**
	 * returns the size of the bag
	 * 
	 * @return numberOfEntries
	 */
	@Override
	public int getCurrentSize() {

		return numberOfEntries;

	} // end getCurrentSize()

	/**
	 * located a given entry within this bag returns a reference to the node
	 * containing the entry, if located, or null otherwise.
	 * 
	 * @param anEntry The entry to be counted.
	 * @return The number of times anEntry appears in the bag.
	 */
	@Override
	public int getFrequencyOf(T anEntry) {
		int frequency = 0;
		int loopCounter = 0;
		Node currentNode = firstNode;
		while ((loopCounter < numberOfEntries) && (currentNode != null)) {
			if (anEntry.equals(currentNode.data))
				frequency++;
			loopCounter++;
			currentNode = currentNode.next;
		}

		return frequency;

	} // end getFrequencyOf()

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.wit.dcsn.comp2000.bagadt.BagInterface#intersection(edu.wit.dcsn.
	 * comp2000.bagadt.BagInterface)
	 */
	@Override
	public BagInterface<T> intersection(BagInterface<T> anotherBag) {
		// variables
		LinkedBag<T> resultBag = new LinkedBag<T>();
		LinkedBag<T> cloneBag = new LinkedBag<T>(anotherBag);
		Node node = firstNode;

		// if the cloneBag contains something from this bag, remove it from cloneBag
		// and add it to resultBag
		for (int i = 0; i < numberOfEntries; i++) {
			if (cloneBag.contains(node.data)) // checks if current node is in cloneBag
			{
				cloneBag.remove(node.data); // remove node from cloneBag
				resultBag.add(node.data); // add node to resultBag
			}
			node = node.getNextNode();
		}

		return resultBag;

	} // end intersection()

	/**
	 * returns true if the bag is empty, false if not
	 */
	@Override
	public boolean isEmpty() {
		return numberOfEntries == 0;

	} // end isEmpty()

	/**
	 * Check if the chain is empty, if it is not empty, access the first node and
	 * decrement the number of entries
	 * 
	 * @return result
	 */
	@Override
	public T remove() {
		T result = null;
		if (firstNode != null) {
			result = firstNode.data;
			firstNode = firstNode.next;
			numberOfEntries--;
		}

		return result;

	} // end no-arg remove()

	/**
	 * remove the node which contains anEntry, if possible
	 * 
	 * @param anEntry
	 * @return result
	 */
	@Override
	public boolean remove(T anEntry) {
		boolean result = false;
		Node nodeN = getReferenceTo(anEntry);

		if (nodeN != null) {
			nodeN.data = firstNode.data;

			firstNode = firstNode.next; // replace located entry with entry
										// in first node
										// remove first node
			numberOfEntries--;
			result = true;
		}
		return result;

	} // end 1-arg remove()

	/**
	 * Retrieves all entries that are in this bag.
	 * 
	 * @return A newly allocated array of all the entries in the bag.
	 */
	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[numberOfEntries];

		int index = 0;
		Node currentNode = firstNode;
		while ((index < numberOfEntries) && (currentNode != null)) {
			result[index] = currentNode.data;
			index++;
			currentNode = currentNode.next;
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
		/*
		 * we'll build a string of comma-separated text representations of each entry
		 * and surround them with square brackets
		 */

		// varaibles
		StringBuilder resultingString = new StringBuilder();
		Node node = firstNode;

		// append everything inbetween two brackets
		// if there is nothing in the bag, add nothing between the brackets
		resultingString.append("[");
		if (numberOfEntries > 0) {
			for (int i = 0; i < numberOfEntries - 1; i++) {
				resultingString.append(node.data + ", "); // append the data
															// with a comma at the end
				node = node.next; // changes to the next node
			}
			resultingString.append(node.data + "]"); // append the last node and closing brackets
		} else
			resultingString.append("]");

		return resultingString.toString();

	} // end toString()

	/*
	 * 
	 * 
	 * @see edu.wit.dcsn.comp2000.bagadt.BagInterface#union(edu.wit.dcsn.comp2000.
	 * bagadt.BagInterface)
	 */
	@Override
	public BagInterface<T> union(BagInterface<T> anotherBag) {
		LinkedBag<T> resultBag = new LinkedBag<T>(this); // current bag
		T[] anotherBagContents = anotherBag.toArray(); // copy anotherBag into an array

		// loop adds everything from anotherbag to resultbag
		for (int i = 0; i < anotherBagContents.length; i++) {
			resultBag.add(anotherBagContents[i]);
		}

		return resultBag;

	} // end union()

	// end of LinkedBag API methods

	/*
	 * private utility methods
	 */

	/**
	 * Find a node in the chain with data that matches the parameter.
	 *
	 * @param anEntry the entry to match
	 * @return a reference to a node containing a matching entry or {@code null} if
	 *         not found
	 */
	private Node getReferenceTo(T anEntry) {
		boolean found = false;
		Node currentNode = firstNode;
		while (!found && (currentNode != null)) {
			if (anEntry.equals(currentNode.data))
				found = true;
			else
				currentNode = currentNode.next;
		}

		return currentNode;

	} // end getReferenceTo()

	/*
	 * private utility class(es)
	 */

	/**
	 * Inner class Node provides all linked-list functionality
	 */
	private class Node {
		/*
		 * instance variables
		 */
		private T data; // Entry in bag
		private Node next; // Link to next node

		/**
		 * Sets up a node given supplied data; next pointer is set to null.
		 * 
		 * @param dataPortion the information this node holds
		 */
		private Node(T dataPortion) {
			this(dataPortion, null);

		} // end 1-arg constructor

		/**
		 * Sets up a node given supplied data and next pointer.
		 * 
		 * @param dataPortion the information this node holds
		 * @param nextNode    reference to the next node in the linked list
		 */
		private Node(T dataPortion, Node nextNode) {
			data = dataPortion;
			next = nextNode;

		} // end 2-arg constructor

		/**
		 * Get a reference to the entry data
		 * 
		 * @return a reference to the data stored in this node
		 */
		private T getData() {
			return data;

		} // end getData()

		/**
		 * Set the reference to entry data
		 * 
		 * @param newData the application data to store in this Node
		 */
		private void setData(T newData) {
			data = newData;

		} // end setData()

		/**
		 * Get a reference to the next Node in the chain
		 * 
		 * @return the reference to the next Node or {@code null} if this is the end of
		 *         the chain
		 */
		private Node getNextNode() {
			return next;

		} // end getNextNode()

		/**
		 * Set the reference to the next Node in the chain
		 * 
		 * @param nextNode a reference to the next Node or {@code null} if this is the
		 *                 end of the chain
		 */
		private void setNextNode(Node nextNode) {
			next = nextNode;

		} // end setNextNode()

	} // end inner class Node

	/**
	 * (optional) test driver for LinkedBag
	 * 
	 * @param args -unused-
	 */
	public static void main(String[] args) {

	} // end main()

} // end class LinkedBag
