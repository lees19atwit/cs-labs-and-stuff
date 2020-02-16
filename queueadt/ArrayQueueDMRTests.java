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

import edu.wit.dcsn.comp2000.queueadt.ArrayQueue ;
import static edu.wit.dcsn.comp2000.queueadt.ArrayQueue.DEFAULT_CAPACITY ;
import static edu.wit.dcsn.comp2000.queueadt.ArrayQueue.MAX_CAPACITY ;
import edu.wit.dcsn.comp2000.queueadt.QueueInterface ;
import edu.wit.dcsn.dmr.testing.junit.DMRJUnitTestsBase ;


/**
 * JUnit tests for the ArrayQueue class. All public and package visible methods
 * are tested. These tests require the API for the ArrayQueue class implement
 * {@code QueueInterface<T>}.
 * 
 * @author David M Rosenberg
 * @version 1.0.0 2018-06-27 initial set of tests
 * @version 1.1.0 2018-09-02 add timeouts
 * @version 2.0.0 2019-05-07
 *              <ul>
 *              <li>start restructuring tests
 *              <li>disable System.exit() during testing
 *              <li>start making each subtest independent so they'll all run
 *              even if one fails
 *              </ul>
 * @version 3.0.0 2019-10-22 switch to new infrastructure
 */
@DisplayName( "ArrayQueue" )
@TestInstance( Lifecycle.PER_CLASS )
@TestMethodOrder( OrderAnnotation.class )
class ArrayQueueDMRTests extends DMRJUnitTestsBase
	{
	
	/*
	 * local constants
	 */
	private static final int SMALL_CAPACITY =	13 ;
	private static final int MEDIUM_CAPACITY =	SMALL_CAPACITY + ( SMALL_CAPACITY / 2 ) ;
	private static final int LARGE_CAPACITY =	MAX_CAPACITY / 2 ;
	
	private static final int QUEUE_1_BASE =		100 ;
	private static final int QUEUE_2_BASE =		100000 ;

	// ----------


	/**
	 * Test method for instantiating queues.
	 */
	@Test
	@Order( 1 )
	@DisplayName( "Instantiate Queue" )
	void testInstantiateQueue()
		{
    	assertAll(
		    () -> {
    		    assertTimeoutPreemptively( testTimeLimit, 
        		    () ->	{
                			QueueInterface<Object> testQueue =	null ;
        			
                    		System.out.println( "Testing: DEFAULT_CAPACITY queue (no arg)" ) ;
                    		currentTestsAttempted++ ;
                    		
                    		testQueue =					new ArrayQueue<>() ;
                    		
                    		assertNotNull( testQueue ) ;
                    		assertTrue( testQueue.isEmpty() );

                    		final ArrayQueue<Object> finalQueue =	(ArrayQueue<Object>) testQueue ;
                    		
                    		assertThrows( EmptyQueueException.class,
                    		              () -> finalQueue.getFront() ) ;
                    		
                    		assertThrows( EmptyQueueException.class,
                    		              () -> finalQueue.dequeue() ) ;
                    		
                    		testPassed() ;
    	    	    		}
        				) ;
           			},
		    () -> {
    		    assertTimeoutPreemptively( testTimeLimit, 
        		    () ->	{
                			QueueInterface<Object> testQueue =	null ;
        			
                    		System.out.println( "Testing: DEFAULT_CAPACITY queue (explicit)" ) ;
                    		currentTestsAttempted++ ;
                    		
                    		int testSize =				DEFAULT_CAPACITY ;
                    		
                    		testQueue =					new ArrayQueue<>( testSize ) ;
                    		
                    		assertNotNull( testQueue ) ;
                    		assertTrue( testQueue.isEmpty() );

                    		final ArrayQueue<Object> finalQueue =	(ArrayQueue<Object>) testQueue ;
                    		
                    		assertThrows( EmptyQueueException.class,
                    		              () -> finalQueue.getFront() ) ;
                    		
                    		assertThrows( EmptyQueueException.class,
                    		              () -> finalQueue.dequeue() ) ;
                    		
                    		testPassed() ;
    	    	    		}
        				) ;
           			},
            () -> {
        	    assertTimeoutPreemptively( testTimeLimit, 
        		    () ->	{
                			QueueInterface<Object> testQueue =	null ;
			
                    		System.out.println( "Testing: SMALL_CAPACITY queue" ) ;
                    		currentTestsAttempted++ ;
                    		
                    		int testSize =				SMALL_CAPACITY ;
                    		
                    		testQueue =					new ArrayQueue<>( testSize ) ;
                    		
                    		assertNotNull( testQueue ) ;
                    		assertTrue( testQueue.isEmpty() );

                    		final ArrayQueue<Object> finalQueue =	(ArrayQueue<Object>) testQueue ;
                    		
                    		assertThrows( EmptyQueueException.class,
                    		              () -> finalQueue.getFront() ) ;
                    		
                    		assertThrows( EmptyQueueException.class,
                    		              () -> finalQueue.dequeue() ) ;
                    		
                    		testPassed() ;
    	    	    		}
        				) ;
           			},
            () -> {
        	    assertTimeoutPreemptively( testTimeLimit, 
        		    () ->	{
                			QueueInterface<Object> testQueue =	null ;
	
                    		System.out.println( "Testing: LARGE_CAPACITY queue" ) ;
                    		currentTestsAttempted++ ;
                    		
                    		int testSize =				LARGE_CAPACITY ;
                    		
                    		testQueue =					new ArrayQueue<>( testSize ) ;
                    		
                    		assertNotNull( testQueue ) ;
                    		assertTrue( testQueue.isEmpty() );

                    		final ArrayQueue<Object> finalQueue =	(ArrayQueue<Object>) testQueue ;
                    		
                    		assertThrows( EmptyQueueException.class,
                    		              () -> finalQueue.getFront() ) ;
                    		
                    		assertThrows( EmptyQueueException.class,
                    		              () -> finalQueue.dequeue() ) ;
                    		
                    		testPassed() ;
    	    	    		}
        				) ;
           			},
            () -> {
        	    assertTimeoutPreemptively( testTimeLimit, 
        		    () ->	{
        					QueueInterface<Object> testQueue =	null ;
	
                    		System.out.println( "Testing: MAX_CAPACITY queue" ) ;
                    		currentTestsAttempted++ ;
                    		
                    		int testSize =				MAX_CAPACITY ;
                    		
                    		testQueue =					new ArrayQueue<>( testSize ) ;
                    		
                    		assertNotNull( testQueue ) ;
                    		assertTrue( testQueue.isEmpty() );

                    		final ArrayQueue<Object> finalQueue =
                    									(ArrayQueue<Object>) testQueue ;
                    		
                    		assertThrows( EmptyQueueException.class,
                    		              () -> finalQueue.getFront() ) ;
                    		
                    		assertThrows( EmptyQueueException.class,
                    		              () -> finalQueue.dequeue() ) ;
                    		
                    		testPassed() ;
    	    	    		}
        				) ;
           			},
            () -> {
        	    assertTimeoutPreemptively( testTimeLimit, 
        		    () ->	{
                    		System.out.println( "Testing: over MAX_CAPACITY queue" ) ;
                    		currentTestsAttempted++ ;
                    		
                    		int testSize =				MAX_CAPACITY + 1 ;
                    		
                    		assertThrows( IllegalStateException.class,
                    		              () -> new ArrayQueue<>( testSize ) ) ;
                    		
//                    		ArrayQueue<Object> testQueue =	null ;
//                    		final ArrayQueue<Object> finalQueue ;
//                    		try
//                    			{
//                        			testQueue =	new ArrayQueue<>( testSize ) ;
//                    			}
//                    		catch ( @SuppressWarnings( "unused" ) IllegalStateException e )
//                    			{
//                    			// ignore it
//                    			}
//                    		finally
//                        		{
//                        		finalQueue =		testQueue;
//                        		}
//                    		
//                    		//make sure we get a security exception
//                    		if ( finalQueue == null )
//                    			{
//                    			writeSyserr( "Can't test for SecurityException: finalQueue is null%n" );
//                    			
//                    			// DMR should this affect test's success/failure?
//                    			}
//                    		else
//                    			{
//                    			assertThrows( SecurityException.class,
//                    			              () -> finalQueue.isEmpty() ) ;
//                    			}
                    		
//                    		assertThrows( SecurityException.class,
//                    		              () -> {
//                    		              	ArrayQueue<Object> failedQueue =	null ;
//                                    		try
//                                    			{
//                                    			failedQueue =	new ArrayQueue<>( testSize ) ;
//                                    			}
//                                    		catch ( @SuppressWarnings( "unused" ) IllegalStateException e )
//                                    			{
//                                    			// expected - ok - ignore
//                                    			}
//                                    		failedQueue.isEmpty() ;
//                    		              	} ) ;
                    		              
                    		testPassed() ;
	    	    			}
    					) ;
           			},
            () -> {
        	    assertTimeoutPreemptively( testTimeLimit,  
        		    () ->	{
                    		System.out.println( "Testing: under DEFAULT_CAPACITY queue" ) ;
                    		currentTestsAttempted++ ;
                    		
                    		int testSize =				DEFAULT_CAPACITY - 1 ;
                    		
                    		assertThrows( IllegalStateException.class,
                    		              () -> new ArrayQueue<>( testSize ) ) ;
                    		
//                    		assertThrows( SecurityException.class,
//                    		              () -> {
//                    		              	ArrayQueue<Object> failedQueue =	null ;
//                                    		try
//                                    			{
//                                    			failedQueue =	new ArrayQueue<>( testSize ) ;
//                                    			}
//                                    		catch ( @SuppressWarnings( "unused" ) IllegalStateException e )
//                                    			{
//                                    			// expected - ok - ignore
//                                    			}
//                                    		failedQueue.isEmpty() ;
//                    		              	} ) ;
                    		              
                    		testPassed() ;
	    	    			}
    					) ;
           			}
 		    ) ;	// end assertAll
		
		}	// end testInstantiateQueue()


	/**
	 * Test method for empty queue.
	 */
	@Test
	@Order( 2 )
	@DisplayName( "Empty Queue" )
	void testEmptyQueue()
		{
		assertTimeoutPreemptively( testTimeLimit,  
		    () -> {
        		QueueInterface<String> testQueue ;
        		
        		int testSize ;
        		
        		boolean sawException ;
        		
        		/* ----- */
        		
        		System.out.println( "Using DEFAULT_CAPACITY queue:" ) ;
        		
        		testSize =					DEFAULT_CAPACITY ;
        		
        		testQueue =					null ;	// reset the pointer
        		testQueue =					new ArrayQueue<>() ;
        		
        		System.out.println( "Testing: isEmpty()" ) ;
        		currentTestsAttempted++ ;
        		
        		assertTrue( testQueue.isEmpty() );
        		
        		currentTestsSucceeded++ ;
        		System.out.println( "...passed" ) ;
        		
        		/* ----- */
        		
        		System.out.println( "Testing: clear()" ) ;
        		currentTestsAttempted++ ;
        		
        		testQueue.clear() ;
        		assertTrue( testQueue.isEmpty() );
        		
        		currentTestsSucceeded++ ;
        		System.out.println( "...passed" ) ;
        		
        		/* ----- */
        		
        		System.out.println( "Testing: getFront()" ) ;
        		currentTestsAttempted++ ;
        		
        		sawException =				false ;
        		try
        			{
        			testQueue.getFront() ;
        			}
        		catch ( @SuppressWarnings( "unused" ) EmptyQueueException e )
        			{
        			sawException =			true ;
        			}
        		assertTrue( sawException ) ;	// make sure we got the (right) exception
        		
        		currentTestsSucceeded++ ;
        		System.out.println( "...passed" ) ;
        		
        		/* ----- */
        		
        		System.out.println( "Testing: dequeue()" ) ;
        		currentTestsAttempted++ ;
        		
        		sawException =				false ;
        		try
        			{
        			testQueue.dequeue() ;
        			}
        		catch ( @SuppressWarnings( "unused" ) EmptyQueueException e )
        			{
        			sawException =			true ;
        			}
        		assertTrue( sawException ) ;	// make sure we got the (right) exception
        		
        		currentTestsSucceeded++ ;
        		System.out.println( "...passed" ) ;
        		
        		/* ----- */
        		/* ----- */
        		
        		System.out.println( "\nUsing SMALL_CAPACITY queue:" ) ;
        		
        		testSize =					SMALL_CAPACITY ;
        		
        		testQueue =					null ;	// reset the pointer
        		testQueue =					new ArrayQueue<>( testSize ) ;
        		
        		System.out.println( "Testing: isEmpty()" ) ;
        		currentTestsAttempted++ ;
        		
        		assertTrue( testQueue.isEmpty() );
        		
        		currentTestsSucceeded++ ;
        		System.out.println( "...passed" ) ;
        		
        		/* ----- */
        		
        		System.out.println( "Testing: clear()" ) ;
        		currentTestsAttempted++ ;
        		
        		testQueue.clear() ;
        		assertTrue( testQueue.isEmpty() );
        		
        		currentTestsSucceeded++ ;
        		System.out.println( "...passed" ) ;
        		
        		/* ----- */
        		
        		System.out.println( "Testing: getFront()" ) ;
        		currentTestsAttempted++ ;
        		
        		sawException =				false ;
        		try
        			{
        			testQueue.getFront() ;
        			}
        		catch ( @SuppressWarnings( "unused" ) EmptyQueueException e )
        			{
        			sawException =			true ;
        			}
        		assertTrue( sawException ) ;	// make sure we got the (right) exception
        		
        		currentTestsSucceeded++ ;
        		System.out.println( "...passed" ) ;
        		
        		/* ----- */
        		
        		System.out.println( "Testing: dequeue()" ) ;
        		currentTestsAttempted++ ;
        		
        		sawException =				false ;
        		try
        			{
        			testQueue.dequeue() ;
        			}
        		catch ( @SuppressWarnings( "unused" ) EmptyQueueException e )
        			{
        			sawException =			true ;
        			}
        		assertTrue( sawException ) ;	// make sure we got the (right) exception
        		
        		currentTestsSucceeded++ ;
        		System.out.println( "...passed" ) ;
        		
        		/* ----- */
        		/* ----- */
        		
        		System.out.println( "\nUsing LARGE_CAPACITY queue:" ) ;
        		
        		testSize =					LARGE_CAPACITY ;
        		
        		testQueue =					null ;	// reset the pointer
        		testQueue =					new ArrayQueue<>( testSize ) ;
        		
        		System.out.println( "Testing: isEmpty()" ) ;
        		currentTestsAttempted++ ;
        		
        		assertTrue( testQueue.isEmpty() );
        		
        		currentTestsSucceeded++ ;
        		System.out.println( "...passed" ) ;
        		
        		/* ----- */
        		
        		System.out.println( "Testing: clear()" ) ;
        		currentTestsAttempted++ ;
        		
        		testQueue.clear() ;
        		assertTrue( testQueue.isEmpty() );
        		
        		currentTestsSucceeded++ ;
        		System.out.println( "...passed" ) ;
        		
        		/* ----- */
        		
        		System.out.println( "Testing: getFront()" ) ;
        		currentTestsAttempted++ ;
        		
        		sawException =				false ;
        		try
        			{
        			testQueue.getFront() ;
        			}
        		catch ( @SuppressWarnings( "unused" ) EmptyQueueException e )
        			{
        			sawException =			true ;
        			}
        		assertTrue( sawException ) ;	// make sure we got the (right) exception
        		
        		currentTestsSucceeded++ ;
        		System.out.println( "...passed" ) ;
        		
        		/* ----- */
        		
        		System.out.println( "Testing: dequeue()" ) ;
        		currentTestsAttempted++ ;
        		
        		sawException =				false ;
        		try
        			{
        			testQueue.dequeue() ;
        			}
        		catch ( @SuppressWarnings( "unused" ) EmptyQueueException e )
        			{
        			sawException =			true ;
        			}
        		assertTrue( sawException ) ;	// make sure we got the (right) exception
        		
        		currentTestsSucceeded++ ;
        		System.out.println( "...passed" ) ;
        		
        		/* ----- */
        		/* ----- */
        		
        		System.out.println( "\nUsing MAX_CAPACITY queue:" ) ;
        		
        		testSize =					MAX_CAPACITY ;
        		
        		testQueue =					null ;	// reset the pointer
        		testQueue =					new ArrayQueue<>( testSize ) ;
        		
        		System.out.println( "Testing: isEmpty()" ) ;
        		currentTestsAttempted++ ;
        		
        		assertTrue( testQueue.isEmpty() );
        		
        		currentTestsSucceeded++ ;
        		System.out.println( "...passed" ) ;
        		
        		/* ----- */
        		
        		System.out.println( "Testing: clear()" ) ;
        		currentTestsAttempted++ ;
        		
        		testQueue.clear() ;
        		assertTrue( testQueue.isEmpty() );
        		
        		currentTestsSucceeded++ ;
        		System.out.println( "...passed" ) ;
        		
        		/* ----- */
        		
        		System.out.println( "Testing: getFront()" ) ;
        		currentTestsAttempted++ ;
        		
        		sawException =				false ;
        		try
        			{
        			testQueue.getFront() ;
        			}
        		catch ( @SuppressWarnings( "unused" ) EmptyQueueException e )
        			{
        			sawException =			true ;
        			}
        		assertTrue( sawException ) ;	// make sure we got the (right) exception
        		
        		currentTestsSucceeded++ ;
        		System.out.println( "...passed" ) ;
        		
        		/* ----- */
        		
        		System.out.println( "Testing: dequeue()" ) ;
        		currentTestsAttempted++ ;
        		
        		sawException =				false ;
        		try
        			{
        			testQueue.dequeue() ;
        			}
        		catch ( @SuppressWarnings( "unused" ) EmptyQueueException e )
        			{
        			sawException =			true ;
        			}
        		assertTrue( sawException ) ;	// make sure we got the (right) exception
        		
        		currentTestsSucceeded++ ;
        		System.out.println( "...passed" ) ;
        		
		    	} ) ;
		
		}	// end testEmptyQueue()


	/**
	 * Test method for full queue.
	 */
	@Test
	@Order( 3 )
	@DisplayName( "Full Queue" )
	void testFullQueue()
		{
		assertTimeoutPreemptively( testTimeLimit,  
		    () -> {
		       QueueInterface<Integer> testQueue ;
            
               int testSize ;
            
               /* ----- */
            
               System.out.println( "Testing: DEFAULT_CAPACITY queue" ) ;
               currentTestsAttempted++ ;
            
               testSize = DEFAULT_CAPACITY ;
            
               testQueue = null ;	// reset the pointer
               testQueue = new ArrayQueue<>() ;
            
               // fill it
               for ( int i = 0 ; i < testSize ; i++ )
                   {
                   testQueue.enqueue( i ) ;
                   }
            
               // empty it
               for ( int i = 0 ; i < testSize ; i++ )
                   {
                   assertEquals( i,
                                 ( int ) testQueue.getFront() ) ;
                   assertEquals( i,
                                 ( int ) testQueue.dequeue() ) ;
                   }
            
               assertTrue( testQueue.isEmpty() ) ;
            
               currentTestsSucceeded++ ;
               System.out.println( "...passed" ) ;
            
               /* ----- */
            
               System.out.println( "Testing: SMALL_CAPACITY queue" ) ;
               currentTestsAttempted++ ;
            
               testSize = SMALL_CAPACITY ;
            
               testQueue = null ;	// reset the pointer
               testQueue = new ArrayQueue<>( testSize ) ;
            
               // fill it
               for ( int i = 0 ; i < testSize ; i++ )
                   {
                   testQueue.enqueue( i ) ;
                   }
            
               // empty it
               for ( int i = 0 ; i < testSize ; i++ )
                   {
                   assertEquals( i,
                                 ( int ) testQueue.getFront() ) ;
                   assertEquals( i,
                                 ( int ) testQueue.dequeue() ) ;
                   }
            
               assertTrue( testQueue.isEmpty() ) ;
            
               currentTestsSucceeded++ ;
               System.out.println( "...passed" ) ;
            
               /* ----- */
            
               System.out.println( "Testing: LARGE_CAPACITY queue" ) ;
               currentTestsAttempted++ ;
            
               testSize = LARGE_CAPACITY ;
            
               testQueue = null ;	// reset the pointer
               testQueue = new ArrayQueue<>( testSize ) ;
            
               // fill it
               for ( int i = 0 ; i < testSize ; i++ )
                   {
                   testQueue.enqueue( i ) ;
                   }
            
               // empty it
               for ( int i = 0 ; i < testSize ; i++ )
                   {
                   assertEquals( i,
                                 ( int ) testQueue.getFront() ) ;
                   assertEquals( i,
                                 ( int ) testQueue.dequeue() ) ;
                   }
            
               assertTrue( testQueue.isEmpty() ) ;
            
               currentTestsSucceeded++ ;
               System.out.println( "...passed" ) ;
            
               /* ----- */
            
               System.out.println( "Testing: MAX_CAPACITY queue" ) ;
               currentTestsAttempted++ ;
            
               testSize = MAX_CAPACITY ;
            
               testQueue = null ;	// reset the pointer
               testQueue = new ArrayQueue<>( testSize ) ;
            
               // fill it
               for ( int i = 0 ; i < testSize ; i++ )
                   {
                   testQueue.enqueue( i ) ;
                   }
            
               // empty it
               for ( int i = 0 ; i < testSize ; i++ )
                   {
                   assertEquals( i,
                                 ( int ) testQueue.getFront() ) ;
                   assertEquals( i,
                                 ( int ) testQueue.dequeue() ) ;
                   }
            
               assertTrue( testQueue.isEmpty() ) ;
            
               currentTestsSucceeded++ ;
               System.out.println( "...passed" ) ;
		
		    } ) ;
		
		}	// end testFullQueue()


	/**
	 * Test method for queue growth.
	 */
	@Test
	@Order( 4 )
	@DisplayName( "Queue Growth" )
	void testQueueGrowth()
		{
		assertTimeoutPreemptively( testTimeLimit, 
		    () -> {
        		QueueInterface<Integer> testQueue ;
        		
        		int testSize ;
        		
        		boolean sawException ;
        		
        		/* ----- */
        		
        		System.out.println( "Testing: SMALL_CAPACITY queue" ) ;
        		currentTestsAttempted++ ;
        		
        		testSize =					SMALL_CAPACITY ;
        		
        		testQueue =					null ;	// reset the pointer
        		testQueue =					new ArrayQueue<>( testSize ) ;
        		
        		// fill it
        		System.out.println( "...filling queue" ) ;
        		for ( int i = 0; i < MEDIUM_CAPACITY; i++ )
        			{
        			testQueue.enqueue( i );
        			}
        		
        		// empty it
        		System.out.println( "...emptying queue" ) ;
        		for ( int i = 0; i < MEDIUM_CAPACITY; i++ )
        			{
        			assertEquals( i, (int) testQueue.getFront() ) ;
        			assertEquals( i, (int) testQueue.dequeue() );
        			}
        		
        		assertTrue( testQueue.isEmpty() );
        		
        		currentTestsSucceeded++ ;
        		System.out.println( "...passed" ) ;
        		
        		/* ----- */
        		
        		System.out.println( "Testing: SMALL_CAPACITY queue with offset" ) ;
        		currentTestsAttempted++ ;
        		
        		testSize =					SMALL_CAPACITY ;
        		
        		testQueue =					null ;	// reset the pointer
        		testQueue =					new ArrayQueue<>( testSize ) ;
        		
        		// advance the internal pointers 1/2 way
        		System.out.println( "...advancing pointers" ) ;
        		for ( int i = 0; i < ( testSize / 2 ); i++ )
        			{
        			testQueue.enqueue( i );
        			testQueue.dequeue() ;
        			}
        		
        		assertTrue( testQueue.isEmpty() );
        		
        		// fill it
        		System.out.println( "...filling queue" ) ;
        		for ( int i = 0; i < MEDIUM_CAPACITY; i++ )
        			{
        			testQueue.enqueue( i );
        			}
        		
        		// empty it
        		System.out.println( "...emptying queue" ) ;
        		for ( int i = 0; i < MEDIUM_CAPACITY; i++ )
        			{
        			assertEquals( i, (int) testQueue.getFront() ) ;
        			assertEquals( i, (int) testQueue.dequeue() );
        			}
        		
        		assertTrue( testQueue.isEmpty() );
        		
        		currentTestsSucceeded++ ;
        		System.out.println( "...passed" ) ;
        		
        		/* ----- */
        		
        		System.out.println( "Testing: DEFAULT_CAPACITY queue" ) ;
        		currentTestsAttempted++ ;
        		
        		testSize =					DEFAULT_CAPACITY ;
        		
        		testQueue =					null ;	// reset the pointer
        		testQueue =					new ArrayQueue<>() ;
        		
        		// fill it
        		System.out.println( "...filling queue" ) ;
        		for ( int i = 0; i < LARGE_CAPACITY; i++ )
        			{
        			testQueue.enqueue( i );
        			}
        		
        		// empty it
        		System.out.println( "...emptying queue" ) ;
        		for ( int i = 0; i < LARGE_CAPACITY; i++ )
        			{
        			assertEquals( i, (int) testQueue.getFront() ) ;
        			assertEquals( i, (int) testQueue.dequeue() );
        			}
        		
        		assertTrue( testQueue.isEmpty() );
        		
        		currentTestsSucceeded++ ;
        		System.out.println( "...passed" ) ;
        		
        		/* ----- */
        		
        		System.out.println( "Testing: DEFAULT_CAPACITY queue with offset" ) ;
        		currentTestsAttempted++ ;
        		
        		testSize =					DEFAULT_CAPACITY ;
        		
        		testQueue =					null ;	// reset the pointer
        		testQueue =					new ArrayQueue<>() ;
        		
        		// advance the internal pointers 1/2 way
        		System.out.println( "...advancing pointers" ) ;
        		for ( int i = 0; i < ( testSize / 2 ); i++ )
        			{
        			testQueue.enqueue( i );
        			testQueue.dequeue() ;
        			}
        		
        		assertTrue( testQueue.isEmpty() );
        		
        		// fill it
        		System.out.println( "...filling queue" ) ;
        		for ( int i = 0; i < LARGE_CAPACITY; i++ )
        			{
        			testQueue.enqueue( i );
        			}
        		
        		// empty it
        		System.out.println( "...emptying queue" ) ;
        		for ( int i = 0; i < LARGE_CAPACITY; i++ )
        			{
        			assertEquals( i, (int) testQueue.getFront() ) ;
        			assertEquals( i, (int) testQueue.dequeue() );
        			}
        		
        		assertTrue( testQueue.isEmpty() );
        		
        		currentTestsSucceeded++ ;
        		System.out.println( "...passed" ) ;
        		
        		/* ----- */
        		
        		System.out.println( "Testing: LARGE_CAPACITY queue" ) ;
        		currentTestsAttempted++ ;
        		
        		testSize =					LARGE_CAPACITY ;
        		
        		testQueue =					null ;	// reset the pointer
        		testQueue =					new ArrayQueue<>( testSize ) ;
        		
        		// fill it
        		System.out.println( "...filling queue" ) ;
        		for ( int i = 0; i < MAX_CAPACITY; i++ )
        			{
        			testQueue.enqueue( i );
        			}
        		
        		// empty it
        		System.out.println( "...emptying queue" ) ;
        		for ( int i = 0; i < MAX_CAPACITY; i++ )
        			{
        			assertEquals( i, (int) testQueue.getFront() ) ;
        			assertEquals( i, (int) testQueue.dequeue() );
        			}
        		
        		assertTrue( testQueue.isEmpty() );
        		
        		currentTestsSucceeded++ ;
        		System.out.println( "...passed" ) ;
        		
        		/* ----- */
        		
        		System.out.println( "Testing: LARGE_CAPACITY queue with offset" ) ;
        		currentTestsAttempted++ ;
        		
        		testSize =					LARGE_CAPACITY ;
        		
        		testQueue =					null ;	// reset the pointer
        		testQueue =					new ArrayQueue<>( testSize ) ;
        		
        		// advance the internal pointers 1/2 way
        		System.out.println( "...advancing pointers" ) ;
        		for ( int i = 0; i < ( testSize / 2 ); i++ )
        			{
        			testQueue.enqueue( i );
        			testQueue.dequeue() ;
        			}
        		
        		assertTrue( testQueue.isEmpty() );
        		
        		// fill it
        		System.out.println( "...filling queue" ) ;
        		for ( int i = 0; i < MAX_CAPACITY; i++ )
        			{
        			testQueue.enqueue( i );
        			}
        		
        		// empty it
        		System.out.println( "...emptying queue" ) ;
        		for ( int i = 0; i < MAX_CAPACITY; i++ )
        			{
        			assertEquals( i, (int) testQueue.getFront() ) ;
        			assertEquals( i, (int) testQueue.dequeue() );
        			}
        		
        		assertTrue( testQueue.isEmpty() );
        		
        		currentTestsSucceeded++ ;
        		System.out.println( "...passed" ) ;
        		
        		/* ----- */
        		
        		System.out.println( "Testing: MAX_CAPACITY queue" ) ;
        		currentTestsAttempted++ ;
        		
        		testSize =					MAX_CAPACITY ;
        		
        		testQueue =					null ;	// reset the pointer
        		testQueue =					new ArrayQueue<>( testSize ) ;
        		
        		// fill it
        		System.out.println( "...filling queue" ) ;
        		for ( int i = 0; i < testSize; i++ )
        			{
        			testQueue.enqueue( i );
        			}
        		
        		// empty it
        		System.out.println( "...emptying queue" ) ;
        		for ( int i = 0; i < testSize; i++ )
        			{
        			assertEquals( i, (int) testQueue.getFront() ) ;
        			assertEquals( i, (int) testQueue.dequeue() );
        			}
        		
        		assertTrue( testQueue.isEmpty() );
        		
        		currentTestsSucceeded++ ;
        		System.out.println( "...passed" ) ;
        		
        		/* ----- */
        		
        		System.out.println( "Testing: MAX_CAPACITY queue with offset" ) ;
        		currentTestsAttempted++ ;
        		
        		testSize =					MAX_CAPACITY ;
        		
        		testQueue =					null ;	// reset the pointer
        		testQueue =					new ArrayQueue<>( testSize ) ;
        		
        		// advance the internal pointers 1/2 way
        		System.out.println( "...advancing pointers" ) ;
        		for ( int i = 0; i < ( testSize / 2 ); i++ )
        			{
        			testQueue.enqueue( i );
        			testQueue.dequeue() ;
        			}
        		
        		assertTrue( testQueue.isEmpty() );
        		
        		// fill it
        		System.out.println( "...filling queue" ) ;
        		for ( int i = 0; i < testSize; i++ )
        			{
        			testQueue.enqueue( i );
        			}
        		
        		// empty it
        		System.out.println( "...emptying queue" ) ;
        		for ( int i = 0; i < testSize; i++ )
        			{
        			assertEquals( i, (int) testQueue.getFront() ) ;
        			assertEquals( i, (int) testQueue.dequeue() );
        			}
        		
        		assertTrue( testQueue.isEmpty() );
        		
        		currentTestsSucceeded++ ;
        		System.out.println( "...passed" ) ;
        		
        		/* ----- */
        		
        		System.out.println( "Testing: over-fill a LARGE_CAPACITY queue" ) ;
        		currentTestsAttempted++ ;
        		
        		testSize =					LARGE_CAPACITY ;
        		
        		testQueue =					null ;	// reset the pointer
        		testQueue =					new ArrayQueue<>( testSize ) ;
        		
        		// fill it
        		System.out.println( "...filling queue" ) ;
        		for ( int i = 0; i < MAX_CAPACITY; i++ )
        			{
        			testQueue.enqueue( i );
        			}
        		
        		// try to over-fill it
        		System.out.println( "...over-filling queue" ) ;
        		sawException =				false ;
        		try
        			{
        			testQueue.enqueue( MAX_CAPACITY ) ;
        			}
        		catch ( @SuppressWarnings( "unused" ) IllegalStateException e )
        			{
        			sawException =			true ;
        			}
        		assertTrue( sawException ) ;	// make sure we got the (right) exception
        		
        		// empty it
        		System.out.println( "...emptying queue" ) ;
        		for ( int i = 0; i < MAX_CAPACITY; i++ )
        			{
        			assertEquals( i, (int) testQueue.getFront() ) ;
        			assertEquals( i, (int) testQueue.dequeue() );
        			}
        		
        		assertTrue( testQueue.isEmpty() );
        		
        		currentTestsSucceeded++ ;
        		System.out.println( "...passed" ) ;
        		
        		/* ----- */
        		
        		System.out.println( "Testing: over-fill a LARGE_CAPACITY queue with offset" ) ;
        		currentTestsAttempted++ ;
        		
        		testSize =					LARGE_CAPACITY ;
        		
        		testQueue =					null ;	// reset the pointer
        		testQueue =					new ArrayQueue<>( testSize ) ;
        		
        		// advance the internal pointers 1/2 way
        		System.out.println( "...advancing pointers" ) ;
        		for ( int i = 0; i < ( testSize / 2 ); i++ )
        			{
        			testQueue.enqueue( i );
        			testQueue.dequeue() ;
        			}
        		
        		assertTrue( testQueue.isEmpty() );
        		
        		// fill it
        		System.out.println( "...filling queue" ) ;
        		for ( int i = 0; i < MAX_CAPACITY; i++ )
        			{
        			testQueue.enqueue( i );
        			}
        		
        		// try to over-fill it
        		System.out.println( "...over-filling queue" ) ;
        		sawException =				false ;
        		try
        			{
        			testQueue.enqueue( MAX_CAPACITY ) ;
        			}
        		catch ( @SuppressWarnings( "unused" ) IllegalStateException e )
        			{
        			sawException =			true ;
        			}
        		assertTrue( sawException ) ;	// make sure we got the (right) exception
        		
        		// empty it
        		System.out.println( "...emptying queue" ) ;
        		for ( int i = 0; i < MAX_CAPACITY; i++ )
        			{
        			assertEquals( i, (int) testQueue.getFront() ) ;
        			assertEquals( i, (int) testQueue.dequeue() );
        			}
        		
        		assertTrue( testQueue.isEmpty() );
        		
        		currentTestsSucceeded++ ;
        		System.out.println( "...passed" ) ;
        		
        		/* ----- */
        		
        		System.out.println( "Testing: over-fill a MAX_CAPACITY queue" ) ;
        		currentTestsAttempted++ ;
        		
        		testSize =					MAX_CAPACITY ;
        		
        		testQueue =					null ;	// reset the pointer
        		testQueue =					new ArrayQueue<>( testSize ) ;
        		
        		// fill it
        		System.out.println( "...filling queue" ) ;
        		for ( int i = 0; i < testSize; i++ )
        			{
        			testQueue.enqueue( i );
        			}
        		
        		// try to over-fill it
        		System.out.println( "...over-filling queue" ) ;
        		final QueueInterface<Integer> overfillingQueue =	testQueue ; 
        		assertThrows( IllegalStateException.class,
        		              () -> overfillingQueue.enqueue( MAX_CAPACITY + 1 ) ) ;
//        		sawException =				false ;
//        		try
//        			{
//        			testQueue.enqueue( MAX_CAPACITY ) ;
//        			}
//        		catch ( @SuppressWarnings( "unused" ) IllegalStateException e )
//        			{
//        			sawException =			true ;
//        			}
//        		assertTrue( sawException ) ;	// make sure we got the right exception
        		
        		// empty it
        		System.out.println( "...emptying queue" ) ;
        		for ( int i = 0; i < testSize; i++ )
        			{
        			assertEquals( i, (int) testQueue.getFront() ) ;
        			assertEquals( i, (int) testQueue.dequeue() );
        			}
        		
        		assertTrue( testQueue.isEmpty() );
        		
        		currentTestsSucceeded++ ;
        		System.out.println( "...passed" ) ;
        		
        		/* ----- */
        		
        		System.out.println( "Testing: over-fill a MAX_CAPACITY queue with offset" ) ;
        		currentTestsAttempted++ ;
        		
        		testSize =					MAX_CAPACITY ;
        		
        		testQueue =					null ;	// reset the pointer
        		testQueue =					new ArrayQueue<>( testSize ) ;
        		
        		// advance the internal pointers 1/2 way
        		System.out.println( "...advancing pointers" ) ;
        		for ( int i = 0; i < ( testSize / 2 ); i++ )
        			{
        			testQueue.enqueue( i );
        			testQueue.dequeue() ;
        			}
        		
        		assertTrue( testQueue.isEmpty() );
        		
        		// fill it
        		System.out.println( "...filling queue" ) ;
        		for ( int i = 0; i < testSize; i++ )
        			{
        			testQueue.enqueue( i );
        			}
        		
        		// try to over-fill it
        		System.out.println( "...over-filling queue" ) ;
        		sawException =				false ;
        		try
        			{
        			testQueue.enqueue( MAX_CAPACITY ) ;
        			}
        		catch ( @SuppressWarnings( "unused" ) IllegalStateException e )
        			{
        			sawException =			true ;
        			}
        		assertTrue( sawException ) ;	// make sure we got the (right) exception
        		
        		// empty it
        		System.out.println( "...emptying queue" ) ;
        		for ( int i = 0; i < testSize; i++ )
        			{
        			assertEquals( i, (int) testQueue.getFront() ) ;
        			assertEquals( i, (int) testQueue.dequeue() );
        			}
        		
        		assertTrue( testQueue.isEmpty() );
        		
        		currentTestsSucceeded++ ;
        		System.out.println( "...passed" ) ;
        		
		    } ) ;
		
		}	// end testQueueGrowth()


	/**
	 * Test method for multiple queues.
	 */
	@Test
	@Order( 5 )
	@DisplayName( "Multiple Queues" )
	void testMultipleQueues()
		{
		assertTimeoutPreemptively( testTimeLimit, 
           () -> {
        		QueueInterface<Integer> testQueue1 ;
        		QueueInterface<Integer> testQueue2 ;
        		
        		Integer testValue37 = new Integer( 37 ) ;
        		Integer testValue42 = new Integer( 42 ) ;
        		
        		/* ----- */
        		
        		/*
        		 * - instantiate 2 queues
        		 * - add an item to one queue
        		 * - make sure it contains the item and other is still empty
        		 * - repeat test with opposite queues
        		 * - repeat test with both queues simultaneously
        		 * - remove the items and make sure both queues are empty
        		 */
        		System.out.println( "Testing: multiple queue instances (1)" ) ;
        		currentTestsAttempted++ ;
        		
        		System.out.println( "...instantiate 2 queues" ) ;
        		testQueue1 =				null ;	// reset the pointer
        		testQueue1 =				new ArrayQueue<>() ;
        		
        		testQueue2 =				null ;	// reset the pointer
        		testQueue2 =				new ArrayQueue<>() ;
        		
        		// add an item to testQueue1
        		System.out.println( "...enqueue 1 item onto queue 1" ) ;
        		testQueue1.enqueue( testValue42 );
        		
        		System.out.println( "...test for item on queue 1" ) ;
        		assertFalse( testQueue1.isEmpty() ) ;
        		assertEquals( testValue42, testQueue1.getFront() ) ;
        		
        		// testQueue2 must still be empty
        		System.out.println( "...test queue 2 for empty" ) ;
        		assertTrue( testQueue2.isEmpty() ) ;
        		
        		// we can remove the item from testQueue1 and both queues are now empty
        		System.out.println( "...dequeue item from queue 1" ) ;
        		assertEquals( testValue42, testQueue1.dequeue() ) ;
        		
        		System.out.println( "...verify both queues empty" ) ;
        		assertTrue( testQueue1.isEmpty() ) ;
        		assertTrue( testQueue2.isEmpty() ) ;
        		
        		// add an item to testQueue2
        		System.out.println( "...enqueue 1 item onto queue 2" ) ;
        		testQueue2.enqueue( testValue37 );
        
        		System.out.println( "...test for item on queue 2" ) ;
        		assertFalse( testQueue2.isEmpty() ) ;
        		assertEquals( testValue37, testQueue2.getFront() ) ;
        
        		// testQueue1 must still be empty
        		System.out.println( "...test queue 1 for empty" ) ;
        		assertTrue( testQueue1.isEmpty() ) ;
        
        		// we can remove the item from testQueue2 and both queues are now empty
        		System.out.println( "...dequeue item from queue 2" ) ;
        		assertEquals( testValue37, testQueue2.dequeue() ) ;
        
        		System.out.println( "...verify both queues empty" ) ;
        		assertTrue( testQueue1.isEmpty() ) ;
        		assertTrue( testQueue2.isEmpty() ) ;
        
        		// add an item to testQueue1
        		System.out.println( "...enqueue 1 item onto each queue" ) ;
        		testQueue1.enqueue( testValue42 );
        		testQueue2.enqueue( testValue37 );
        
        		System.out.println( "...test for correct items on each queue" ) ;
        		assertFalse( testQueue1.isEmpty() ) ;
        		assertEquals( testValue42, testQueue1.getFront() ) ;
        		assertFalse( testQueue2.isEmpty() ) ;
        		assertEquals( testValue37, testQueue2.getFront() ) ;
        
        		// we can remove the items from each and both queues are now empty
        		System.out.println( "...dequeue items from each queue" ) ;
        		assertEquals( testValue42, testQueue1.dequeue() ) ;
        		assertEquals( testValue37, testQueue2.dequeue() ) ;
        
        		System.out.println( "...verify both queues empty" ) ;
        		assertTrue( testQueue1.isEmpty() ) ;
        		assertTrue( testQueue2.isEmpty() ) ;
        
        		currentTestsSucceeded++ ;
        		System.out.println( "...passed" ) ;
        		
        		/* ----- */
        		
        		/*
        		 * - instantiate queue 1
        		 * - add an item to one queue
        		 * - instantiate queue 2
        		 * - make sure queue 1 contains the item and queue 2 is empty
        		 * - remove the item from queue 1 and make sure both queues are empty
        		 */
        		System.out.println( "Testing: multiple queue instances (2)" ) ;
        		currentTestsAttempted++ ;
        		
        		System.out.println( "...instantiate queue 1" ) ;
        		testQueue1 =				null ;	// reset the pointer
        		testQueue1 =				new ArrayQueue<>() ;
        		
        		// add an item to testQueue1
        		System.out.println( "...enqueue 1 item onto queue 1" ) ;
        		testQueue1.enqueue( testValue42 );
        		
        		System.out.println( "...test for item on queue 1" ) ;
        		assertFalse( testQueue1.isEmpty() ) ;
        		assertEquals( testValue42, testQueue1.getFront() ) ;
        		
        		System.out.println( "...instantiate queue 2" ) ;
        		testQueue2 =				null ;	// reset the pointer
        		testQueue2 =				new ArrayQueue<>() ;
        		
        		// testQueue2 must be empty
        		System.out.println( "...test queue 2 for empty" ) ;
        		assertTrue( testQueue2.isEmpty() ) ;
        		
        		// we can remove the item from testQueue1 and both queues are now empty
        		System.out.println( "...dequeue item from queue 1" ) ;
        		assertEquals( testValue42, testQueue1.dequeue() ) ;
        		
        		System.out.println( "...verify both queues empty" ) ;
        		assertTrue( testQueue1.isEmpty() ) ;
        		assertTrue( testQueue2.isEmpty() ) ;
        		
        		currentTestsSucceeded++ ;
        		System.out.println( "...passed" ) ;
        		
        		/* ----- */
        		
        		/*
        		 * - instantiate 2 queues
        		 * - add items to each queue
        		 * - make sure each queue contains the correct items
        		 * - remove the items from both queues and make sure they are both empty
        		 */
        		System.out.println( "Testing: multiple queue instances (3)" ) ;
        		currentTestsAttempted++ ;
        		
        		System.out.println( "...instantiate 2 queues" ) ;
        		testQueue1 =				null ;	// reset the pointer
        		testQueue1 =				new ArrayQueue<>( SMALL_CAPACITY ) ;
        		
        		testQueue2 =				null ;	// reset the pointer
        		testQueue2 =				new ArrayQueue<>( SMALL_CAPACITY ) ;
        		
        		// add items to testQueue1
        		System.out.println( "...enqueue multiple items onto queue 1" ) ;
        		for ( int i = 0; i < MEDIUM_CAPACITY; i++ )
        			{
        			testQueue1.enqueue( QUEUE_1_BASE + i );
        			}
        		
        		// add an items to testQueue2
        		System.out.println( "...enqueue multiple items onto queue 2" ) ;
        		for ( int i = 0; i < MEDIUM_CAPACITY; i++ )
        			{
        			testQueue2.enqueue( QUEUE_2_BASE + i );
        			}
        		
        		// remove items from testQueue1
        		System.out.println( "...test for items on queue 1" ) ;
        		assertFalse( testQueue1.isEmpty() ) ;
        
        		for ( int i = 0; i < MEDIUM_CAPACITY; i++ )
        			{
        			assertEquals( new Integer( QUEUE_1_BASE + i ), testQueue1.dequeue() ) ;
        			}
        		
        		assertTrue( testQueue1.isEmpty() ) ;
        		
        		// remove items from testQueue2
        		System.out.println( "...test for items on queue 2" ) ;
        		assertFalse( testQueue2.isEmpty() ) ;
        
        		for ( int i = 0; i < MEDIUM_CAPACITY; i++ )
        			{
        			assertEquals( new Integer( QUEUE_2_BASE + i ), testQueue2.dequeue() ) ;
        			}
        
        		assertTrue( testQueue2.isEmpty() ) ;
        		
        		currentTestsSucceeded++ ;
        		System.out.println( "...passed" ) ;
        		
        		/* ----- */
        		
        		/*
        		 * - instantiate 2 queues
        		 * - add items to each queue
        		 * - make sure each queue contains the correct items
        		 * - remove the items from both queues and make sure they are both empty
        		 */
        		System.out.println( "Testing: multiple queue instances (4)" ) ;
        		currentTestsAttempted++ ;
        		
        		System.out.println( "...instantiate 2 queues" ) ;
        		testQueue1 =				null ;	// reset the pointer
        		testQueue1 =				new ArrayQueue<>( SMALL_CAPACITY ) ;
        		
        		testQueue2 =				null ;	// reset the pointer
        		testQueue2 =				new ArrayQueue<>( SMALL_CAPACITY ) ;
        		
        		// add items to testQueue1
        		System.out.println( "...enqueue multiple items onto queue 1" ) ;
        		for ( int i = 0; i < MEDIUM_CAPACITY; i++ )
        			{
        			testQueue1.enqueue( QUEUE_1_BASE + i );
        			}
        		
        		// add an items to testQueue2
        		System.out.println( "...enqueue multiple items onto queue 2" ) ;
        		for ( int i = 0; i < MEDIUM_CAPACITY; i++ )
        			{
        			testQueue2.enqueue( QUEUE_2_BASE + i );
        			}
        		
        		// remove items from testQueue2
        		System.out.println( "...test for items on queue 2" ) ;
        		assertFalse( testQueue2.isEmpty() ) ;
        
        		for ( int i = 0; i < MEDIUM_CAPACITY; i++ )
        			{
        			assertEquals( new Integer( QUEUE_2_BASE + i ), testQueue2.dequeue() ) ;
        			}
        
        		assertTrue( testQueue2.isEmpty() ) ;
        		
        		// remove items from testQueue1
        		System.out.println( "...test for items on queue 1" ) ;
        		assertFalse( testQueue1.isEmpty() ) ;
        
        		for ( int i = 0; i < MEDIUM_CAPACITY; i++ )
        			{
        			assertEquals( new Integer( QUEUE_1_BASE + i ), testQueue1.dequeue() ) ;
        			}
        		
        		assertTrue( testQueue1.isEmpty() ) ;
        		
        		currentTestsSucceeded++ ;
        		System.out.println( "...passed" ) ;
        		
        		/* ----- */
        		
           } ) ;
				
		}	// end testMultipleQueues()

	
//	/**
//	 * Test method for {@link edu.wit.dcsn.comp2000.queueadt.ArrayQueue#ArrayQueue()}.
//	 */
//	@Test
//	@Order( 6 )
//	final void testArrayQueue()
//		{
//		fail( "Not yet implemented" ) ;
//		}
//
//
//	/**
//	 * Test method for {@link edu.wit.dcsn.comp2000.queueadt.ArrayQueue#ArrayQueue(int)}.
//	 */
//	@Test
//	@Order( 6 )
//	final void testArrayQueueInt()
//		{
//		fail( "Not yet implemented" ) ;
//		}
//
//
//	/**
//	 * Test method for {@link edu.wit.dcsn.comp2000.queueadt.ArrayQueue#enqueue(java.lang.Object)}.
//	 */
//	@Test
//	@Order( 6 )
//	final void testEnqueue()
//		{
//		fail( "Not yet implemented" ) ;
//		}
//
//
//	/**
//	 * Test method for {@link edu.wit.dcsn.comp2000.queueadt.ArrayQueue#dequeue()}.
//	 */
//	@Test
//	@Order( 6 )
//	final void testDequeue()
//		{
//		fail( "Not yet implemented" ) ;
//		}
//
//
//	/**
//	 * Test method for {@link edu.wit.dcsn.comp2000.queueadt.ArrayQueue#getFront()}.
//	 */
//	@Test
//	@Order( 6 )
//	final void testGetFront()
//		{
//		fail( "Not yet implemented" ) ;
//		}
//
//
//	/**
//	 * Test method for {@link edu.wit.dcsn.comp2000.queueadt.ArrayQueue#isEmpty()}.
//	 */
//	@Test
//	@Order( 6 )
//	final void testIsEmpty()
//		{
//		fail( "Not yet implemented" ) ;
//		}
//
//
//	/**
//	 * Test method for {@link edu.wit.dcsn.comp2000.queueadt.ArrayQueue#clear()}.
//	 */
//	@Test
//	@Order( 6 )
//	final void testClear()
//		{
//		fail( "Not yet implemented" ) ;
//		}

	}	// end class ArrayQueueDMRTests
