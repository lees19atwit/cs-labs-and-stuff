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

package edu.wit.dcsn.comp2000.bagadt;

/**
 * @author Sunny Lee
 * @version 1.0.0 2019-09-26 tests all methods in LinkedBag
 *
 */
public class LinkedBagStudentTests {

	/**
	 * Test driver
	 * 
	 * @param args -unused-
	 */
	public static void main(String[] args) {
		// SUGGESTION you may want to rearrange these invocations so you can
		// test as you go: implement a method or two then test before
		// implementing the next method or two

		// QUESTION What order did you implement methods?

		testConstructors();
		testAdd();
		testClear();
		testContains();
		testDifference();
		testGetCurrentSize();
		testGetFrequencyOf();
		testIntersection();
		testIsEmpty();
		testRemove();
		testToArray();
		testToString();
		testUnion();
	} // end main()

	/**
	 * Tests of the LinkedBag constructors
	 */
	private static void testConstructors() {
		System.out.println("Starting constructor tests\n");

		// variables
		String[] str = { "123", "456", "789" };
		LinkedBag<String> bag = new LinkedBag<String>();

		// add strings to the bag
		for (int i = 0; i < str.length; i++) {
			bag.add(str[i]);
		}

		// test to see if the 1 arg constructor works
		LinkedBag<String> newBag = new LinkedBag<>(bag);
		System.out.println(newBag.toString());
		System.out.println();

	} // end testConstructors()

	/**
	 * Tests of the add() method
	 */
	private static void testAdd() {
		System.out.println("Starting add() tests\n");

		// variables
		String[] str = { "123", "456", "789" };
		LinkedBag<String> bag = new LinkedBag<String>();

		// add strings to the bag
		for (int i = 0; i < str.length; i++) {
			bag.add(str[i]);
		}

		// test add
		System.out.println("Before adding \"101112\": " + bag.toString());
		bag.add("101112");
		System.out.println("After adding \"101112\": " + bag.toString());
		System.out.println();
	} // end testAdd()

	/**
	 * Tests of the clear() method
	 */
	private static void testClear() {
		System.out.println("Starting clear() tests\n");

		// variables
		String[] str = { "123", "456", "789" };
		LinkedBag<String> bag = new LinkedBag<String>();

		// add strings to the bag
		for (int i = 0; i < str.length; i++) {
			bag.add(str[i]);
		}

		// test clear
		System.out.println("Before clear: " + bag.toString());
		bag.clear();
		System.out.println("After clear: " + bag.toString());
		System.out.println();

	} // end testClear()

	/**
	 * Tests of the contains() method
	 */
	private static void testContains() {
		System.out.println("Starting contains() tests\n");

		// variables
		String[] str = { "123", "456", "789" };
		LinkedBag<String> bag = new LinkedBag<String>();

		// add strings to the bag
		for (int i = 0; i < str.length; i++) {
			bag.add(str[i]);
		}

		// test contains
		System.out.println("Tests to see if 789 is in the bag");
		System.out.println(bag.contains("789"));
		System.out.println("Test to see if 150 is in the bag");
		System.out.println(bag.contains("150"));
		System.out.println();
	} // end testContains()

	/**
	 * Tests of the difference() method
	 */
	private static void testDifference() {
		System.out.println("Starting difference() tests\n");

		// variables
		String[] str = { "123", "456", "789" };
		String[] str1 = { "123", "3258", "hu2ht2" };
		LinkedBag<String> bag = new LinkedBag<String>();
		LinkedBag<String> bag1 = new LinkedBag<String>();

		// add strings to the bag
		for (int i = 0; i < str.length; i++) {
			bag.add(str[i]);
			bag1.add(str1[i]);
		}

		// test the difference between the two bags
		System.out.println("Bag 1 contains: " + bag.toString());
		System.out.println("Bag 2 contains: " + bag1.toString());
		System.out.println("The difference is " + bag.difference(bag1));
		System.out.println();
	} // end testDifference()

	/**
	 * Tests of the getCurrentSize() method
	 */
	private static void testGetCurrentSize() {
		System.out.println("Starting getCurrentSize() tests\n");

		// variables
		String[] str = { "123", "456", "789" };
		String[] str1 = { "123", "3258", "hu2ht2" };
		LinkedBag<String> bag = new LinkedBag<String>();
		LinkedBag<String> bag1 = new LinkedBag<String>();

		// add strings to the bag
		for (int i = 0; i < str.length; i++) {
			bag.add(str[i]);
			bag1.add(str1[i]);
		}

		// test getCurrentSize
		System.out.println("size of array that is put into the bag " + str.length);
		System.out.println("size of linkedbag " + bag.getCurrentSize());
		System.out.println();

	} // end testGetCurrentSize()

	/**
	 * Tests of the getFrequencyOf() method
	 */
	private static void testGetFrequencyOf() {
		System.out.println("Starting getFrequencyOf() tests\n");

		// variables
		String[] str = { "123", "456", "789" };
		String[] str1 = { "123", "123", "123" };
		LinkedBag<String> bag = new LinkedBag<String>();
		LinkedBag<String> bag1 = new LinkedBag<String>();

		// add strings to the bag
		for (int i = 0; i < str.length; i++) {
			bag.add(str[i]);
			bag1.add(str1[i]);
		}

		// test getFrequencyOf
		System.out.println("bag contents: " + bag.toString());
		System.out.println("frequency of \" 123 \" : " + bag.getFrequencyOf("123"));
		System.out.println("bag contents: " + bag1.toString());
		System.out.println("frequency of \" 123 \" : " + bag1.getFrequencyOf("123"));
		System.out.println();

	} // end testGetFrequencyOf()

	/**
	 * Tests of the intersection() method
	 */
	private static void testIntersection() {
		System.out.println("Starting intersection() tests\n");

		// variables
		String[] str = { "123", "456", "789" };
		String[] str1 = { "123", "3258", "hu2ht2" };
		LinkedBag<String> bag = new LinkedBag<String>();
		LinkedBag<String> bag1 = new LinkedBag<String>();

		// add strings to the bag
		for (int i = 0; i < str.length; i++) {
			bag.add(str[i]);
			bag1.add(str1[i]);
		}

		// test intersection of the two bags
		System.out.println("bag 1 entries: " + bag.toString());
		System.out.println("bag 2 entries: " + bag1.toString());
		System.out.println("intersection between the two bags" + bag.intersection(bag1));
		System.out.println();

	} // end testIntersection()

	/**
	 * Tests of the isEmpty() method
	 */
	private static void testIsEmpty() {
		System.out.println("Starting isEmpty() tests\n");

		// variables
		String[] str = { "123", "456", "789" };
		LinkedBag<String> bag = new LinkedBag<String>();
		LinkedBag<String> bag1 = new LinkedBag<String>();

		// add strings to the bag
		for (int i = 0; i < str.length; i++) {
			bag.add(str[i]);
		}

		// test isEmpty
		System.out.println("testing if bag 1 is empty " + bag.isEmpty());
		System.out.println("testing if bag 2 is empty " + bag1.isEmpty());
		System.out.println();

	} // end testIsEmpty()

	/**
	 * Tests of the remove() methods
	 */
	private static void testRemove() {
		System.out.println("Starting remove() tests\n");

		// variables
		String[] str = { "123", "456", "789" };
		String[] str1 = { "123", "3258", "hu2ht2" };
		LinkedBag<String> bag = new LinkedBag<String>();
		LinkedBag<String> bag1 = new LinkedBag<String>();

		// add strings to the bag
		for (int i = 0; i < str.length; i++) {
			bag.add(str[i]);
			bag1.add(str1[i]);
		}

		// test remove
		System.out.println("bag before removing \"123\" " + bag.toString());
		bag.remove("123");
		System.out.println("bag after removing \"123\" " + bag.toString());
		System.out.println();

	} // end testRemove()

	/**
	 * Tests of the toArray() method
	 */
	private static void testToArray() {
		System.out.println("Starting toArray() tests\n");

		// variables
		String[] str = { "123", "456", "789" };
		String[] str1 = { "123", "3258", "hu2ht2" };
		LinkedBag<String> bag = new LinkedBag<String>();
		LinkedBag<String> bag1 = new LinkedBag<String>();

		// add strings to the bag
		for (int i = 0; i < str.length; i++) {
			bag.add(str[i]);
			bag1.add(str1[i]);
		}

		// tests toarray
		System.out.println("contents of the bag " + bag.toString());
		System.out.println("contents of the bag after being turned into an array: ");
		Object[] str3 = bag.toArray();
		for (int i = 0; i < str3.length; i++)
			System.out.println(str3[i]);
		System.out.println();

	} // end testToArray()

	/**
	 * Tests of the toString() method
	 */
	private static void testToString() {
		System.out.println("Starting toString() tests\n");

		// variables
		String[] str = { "123", "456", "789" };
		LinkedBag<String> bag = new LinkedBag<String>();

		// add strings to the bag
		for (int i = 0; i < str.length; i++) {
			bag.add(str[i]);
		}

		// test toString
		System.out.println(bag.toString());
		System.out.println();

	} // end testToString()

	/**
	 * Tests of the union() method
	 */
	private static void testUnion() {
		System.out.println("Starting union() tests\n");

		// variables
		String[] str = { "123", "456", "789" };
		String[] str1 = { "123", "3258", "hu2ht2" };
		LinkedBag<String> bag = new LinkedBag<String>();
		LinkedBag<String> bag1 = new LinkedBag<String>();

		// add strings to the bag
		for (int i = 0; i < str.length; i++) {
			bag.add(str[i]);
			bag1.add(str1[i]);
		}

		// test union
		System.out.println("contents of bag 1 " + bag.toString());
		System.out.println("contents of bag 2 " + bag1.toString());
		System.out.println("union of bags 1 and 2 " + bag.union(bag1).toString());

	} // end testUnion()

} // end class MyLinkedBagTest
