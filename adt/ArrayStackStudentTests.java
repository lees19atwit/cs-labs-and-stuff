/*
 * Dave Rosenberg
 * Comp 2000 - Data Structures
 * Lab: Stack ADT
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

package edu.wit.dcsn.comp2000.stack.adt;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import edu.wit.dcsn.comp2000.stack.common.TestableStackInterface;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestInstance.Lifecycle;

/**
 * @author Sunny Lee
 * @version 1.0.0 tests for class ArrayStack
 */
@DisplayName("Testing ArrayStack")
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class ArrayStackStudentTests {

	/*
	 * test constructors
	 */

	/**
	 * Test method for
	 * {@link edu.wit.dcsn.comp2000.stack.adt.ArrayStack#ArrayStack()}.
	 */
	@Test
	@Order(100)
	final void testArrayStack() {
		TestableStackInterface<Integer> testStack = new ArrayStack<>(); 
		assertEquals(0, testStack.size()); //check testStack size is zero 
		testStack.push(null); 
		testStack.push(2); 
		assertEquals(2, testStack.size()); //check testStack size is two 
		assertEquals(2, testStack.pop()); //check testStack popped value is two
		
	} // end testArrayStack()

	/**
	 * Test method for
	 * {@link edu.wit.dcsn.comp2000.stack.adt.ArrayStack#ArrayStack(int)}.
	 */
	@Test
	@Order(200)
	final void testArrayStackInt() {
		TestableStackInterface<Integer> testStack = new ArrayStack<>(5000); 
		assertEquals(0, testStack.size()); //check testStack size is zero 
		testStack.push(1); 
		testStack.push(2); 
		assertEquals(2, testStack.size()); //check testStack size is two 
		assertEquals(2, testStack.pop()); //check testStack popped value is two
		
	} // end testArrayStackInt()

	/*
	 * test API methods
	 */

	/**
	 * Test method for {@link edu.wit.dcsn.comp2000.stack.adt.ArrayStack#clear()}.
	 */
	@Test
	@Order(300)
	final void testClear() {
		System.out.println("Testing clear()");
		TestableStackInterface<Integer> testStack = new ArrayStack<>(); 
		testStack.push(3); 
		assertFalse(testStack.isEmpty()); //see if testStack is not empty
		testStack.clear(); 
		assertTrue(testStack.isEmpty()); //see if testStack is empty
		assertEquals(0, testStack.size()); //see if testStack size is zero
		
		System.out.println("Test passed.");
	} // end testClear()

	/**
	 * Test method for {@link edu.wit.dcsn.comp2000.stack.adt.ArrayStack#isEmpty()}.
	 */
	@Test
	@Order(400)
	final void testIsEmpty() {
		System.out.println("Testing isEmpty()");
		TestableStackInterface<Integer> testStack = new ArrayStack<>(); 
		assertTrue(testStack != null); //see if testStack is not null
		assertEquals(0, testStack.size()); //test to see if testStack.size is 0
		assertTrue(testStack.isEmpty()); //test to see if isEmpty is true
		
		Object[] objectArray = testStack.toArray(); 
		assertEquals(0, objectArray.length); //test to see if the toarray is equal to zero 
		
		testStack.push(3); 
		assertFalse(testStack.isEmpty());
		
		testStack.pop(); 
		assertTrue(testStack.isEmpty()); 
		
		System.out.println("Test passed.");
	} // end testIsEmpty()

	/**
	 * Test method for {@link edu.wit.dcsn.comp2000.stack.adt.ArrayStack#peek()}.
	 */
	@Test
	@Order(500)
	final void testPeek() {
		System.out.println("Testing peek()");
		TestableStackInterface<Integer> testStack = new ArrayStack<>(); 
		testStack.push(3); 
		assertEquals(3, testStack.peek()); //check to see if testStack at topIndex is equal to 3
		assertFalse(testStack.isEmpty()); //check to see if testStack is not empty 
		
		System.out.println("Test passed.");
	} // end testPeek()

	/**
	 * Test method for {@link edu.wit.dcsn.comp2000.stack.adt.ArrayStack#pop()}.
	 */
	@Test
	@Order(600)
	final void testPop() {
		System.out.println("Testing pop()");
		TestableStackInterface<Integer> testStack = new ArrayStack<>(); 
		testStack.push(1); 
		testStack.push(2); 
		assertEquals(2, testStack.pop()); //check to see if popped object equals 2
		assertEquals(1, testStack.size()); //check to see if testStack still has one object in it
		
		System.out.println("Test passed");

	} // end testPop()

	/**
	 * Test method for
	 * {@link edu.wit.dcsn.comp2000.stack.adt.ArrayStack#push(java.lang.Object)}.
	 */
	@Test
	@Order(700)
	final void testPush() {
		System.out.println("Testing push()");
		TestableStackInterface<Integer> testStack = new ArrayStack<>(); 
		testStack.push(5); 
		assertEquals(1, testStack.size()); //check if size is one
		assertEquals(5, testStack.pop()); //check if popped object is five 
		
		System.out.println("Test passed.");

	} // end testPush()

	/**
	 * Test method for {@link edu.wit.dcsn.comp2000.stack.adt.ArrayStack#size()}.
	 */
	@Test
	@Order(800)
	final void testSize() {
		System.out.println("Testing size()");
		TestableStackInterface<Integer> testStack = new ArrayStack<>(); 
		testStack.push(1);
		assertEquals(1, testStack.size()); //checks size to be one
		testStack.push(2); 
		assertEquals(2, testStack.size()); //checks size to be two
		testStack.push(3);
		assertEquals(3, testStack.size()); //checks size to be three
		testStack.push(4); 
		assertEquals(4, testStack.size()); //checks size to be four
		testStack.clear(); 
		assertEquals(0, testStack.size()); //checks size to be five
		
		System.out.println("Test passed.");
	} // end testSize()

	/**
	 * Test method for {@link edu.wit.dcsn.comp2000.stack.adt.ArrayStack#toArray()}.
	 */
	@Test
	@Order(900)
	final void testToArray() {
		System.out.println("Testing toArray()");
		TestableStackInterface<Integer> testStack = new ArrayStack<>(); 
		Object[] objectArray = testStack.toArray(); 
		
		assertEquals(0, objectArray.length); // check if array length is zero 
		
		testStack.push(1); 
		objectArray = testStack.toArray(); 
		assertEquals(1, objectArray.length); //check if array length is one
		assertEquals(1, objectArray[0]); //check if first 
		
		testStack.push(2); 
		objectArray = testStack.toArray(); 
		assertEquals(2, objectArray.length); //check if objectarray length is two
		assertEquals(2, objectArray[0]); //check if the first object is two
		assertEquals(1, objectArray[1]); //check if the second object is one
		
		testStack.clear(); 
		objectArray = testStack.toArray(); 
		assertEquals(0, objectArray.length); //check objectarray length is zero
		assertTrue(testStack.isEmpty()); //check testStack is empty
		
		System.out.println("Test passed.");
	} // end testToArray()

} // end class ArrayStackStudentTests
