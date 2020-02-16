/*
 * Dave Rosenberg
 * Comp 2000 - Data Structures
 * Lab: Queue ADT
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

package edu.wit.dcsn.comp2000.queueadt;

import static org.junit.jupiter.api.Assertions.* ;

import org.junit.jupiter.api.DisplayName ;
import org.junit.jupiter.api.Test ;
import org.junit.jupiter.api.TestInstance ;
import org.junit.jupiter.api.TestMethodOrder ;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation ;
import org.junit.jupiter.api.Order ;
import org.junit.jupiter.api.TestInstance.Lifecycle ;


/**
 * @author Sunny Lee
 * @version 1.0.0 2019-10-31 initial implementation
 */
@DisplayName( "Testing ArrayQueue" )
@TestInstance( Lifecycle.PER_CLASS )
@TestMethodOrder( OrderAnnotation.class )
class ArrayQueueStudentTests
	{
	
	/*
	 * test constructors
	 */

	/**
	 * Test method for {@link edu.wit.dcsn.comp2000.queueadt.ArrayQueue#ArrayQueue()}.
	 */
	@Test
	@Order( 100 )
	final void testArrayQueue()
		{
		ArrayQueue<Integer> arr = new ArrayQueue<>(); 
		assertTrue(arr.isEmpty()); 
		
		arr.enqueue(0); 
		arr.enqueue(0); 
		
		assertFalse(arr.isEmpty()); 
		
		}	// end testArrayQueue()


	/**
	 * Test method for {@link edu.wit.dcsn.comp2000.queueadt.ArrayQueue#ArrayQueue(int)}.
	 */
	@Test
	@Order( 200 )
	final void testArrayQueueInt()
		{
		ArrayQueue<Integer> arr = new ArrayQueue<>(50); 
		
		arr.enqueue(0); 
		assertEquals(0, arr.getFront()); 
		arr.enqueue(0); 
		assertFalse(arr.isEmpty()); 
		
		}	// end testArrayQueueInt()
	
	
	/*
	 * test API methods
	 */


	/**
	 * Test method for {@link edu.wit.dcsn.comp2000.queueadt.ArrayQueue#clear()}.
	 */
	@Test
	@Order( 300 )
	final void testClear()
		{
		ArrayQueue<Integer> arr = new ArrayQueue<>(); 
		
		for(int i = 0; i < 5; i++)
			arr.enqueue(i);
		
		assertEquals(0, arr.dequeue()); 
		assertEquals(1, arr.dequeue()); 
		assertEquals(2, arr.dequeue()); 
		assertEquals(3, arr.dequeue()); 
		assertEquals(4, arr.dequeue()); 
		assertTrue(arr.isEmpty()); 
		
		}	// end testClear()


	/**
	 * Test method for {@link edu.wit.dcsn.comp2000.queueadt.ArrayQueue#isEmpty()}.
	 */
	@Test
	@Order( 400 )
	final void testIsEmpty()
		{
		ArrayQueue<Integer> arr = new ArrayQueue<>(); 
		
		assertTrue(arr.isEmpty()); 
		
		for(int i = 0; i < 5; i++)
			arr.enqueue(i);
		
		assertFalse(arr.isEmpty()); 
		}	// end testIsEmpty()


	/**
	 * Test method for {@link edu.wit.dcsn.comp2000.queueadt.ArrayQueue#getFront()}.
	 */
	@Test
	@Order( 500 )
	final void testGetFront()
		{
		ArrayQueue<Integer> arr = new ArrayQueue<>(); 
		
		for(int i = 0; i < 5; i++)
			arr.enqueue(i);
		
		for(int i = 0; i < 5; i++) {
			assertEquals(i, arr.getFront());
			arr.dequeue(); 
		}
		
		}	// end testGetFront()


	/**
	 * Test method for {@link edu.wit.dcsn.comp2000.queueadt.ArrayQueue#dequeue()}.
	 */
	@Test
	@Order( 600 )
	final void testDequeue()
		{
		ArrayQueue<Integer> arr = new ArrayQueue<>(); 
		
		for(int i = 0; i < 5; i++)
			arr.enqueue(i);
		
		for(int i = 0; i < 5; i++) {
			assertEquals(i, arr.dequeue());
		}
		
		}	// end testDequeue()


	/**
	 * Test method for {@link edu.wit.dcsn.comp2000.queueadt.ArrayQueue#enqueue(java.lang.Object)}.
	 */
	@Test
	@Order( 700 )
	final void testEnqueue()
		{
		ArrayQueue<Integer> arr = new ArrayQueue<>(); 
		
		assertTrue(arr.isEmpty()); 
		
		for(int i = 0; i < 5; i++) {
			arr.enqueue(i);
		}
		
		assertFalse(arr.isEmpty()); 
		
		}	// end testEnqueue()

	}	// end class ArrayQueueStudentTests
