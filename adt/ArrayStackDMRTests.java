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

package edu.wit.dcsn.comp2000.stack.adt ;

import static org.junit.jupiter.api.Assertions.* ;

import org.junit.jupiter.api.Disabled ;
import org.junit.jupiter.api.DisplayName ;
import org.junit.jupiter.api.Order ;
import org.junit.jupiter.api.Test ;
import org.junit.jupiter.api.TestInstance ;
import org.junit.jupiter.api.TestMethodOrder ;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation ;
import org.junit.jupiter.api.TestInstance.Lifecycle ;

import java.util.EmptyStackException ;

import static edu.wit.dcsn.comp2000.stack.adt.ArrayStack.DEFAULT_CAPACITY ;
import static edu.wit.dcsn.comp2000.stack.adt.ArrayStack.MAX_CAPACITY ;

import edu.wit.dcsn.comp2000.stack.common.TestableStackInterface ;
import edu.wit.dcsn.dmr.testing.junit.DMRJUnitTestsBase ;


/**
 * JUnit tests for the ArrayStack class. All public and package visible methods
 * are tested. These tests require the API for the ArrayStack class implement
 * {@code TestableArrayStackInterface<T>}.
 * 
 * @author David M Rosenberg
 * @version 1.0.0 2018-06-08 initial set of tests<br>
 * @version 1.1.0 2018-06-09 revise structure to use TestInfo instead of certain
 *          hard-coded text<br>
 * @version 1.1.1 2018-06-27 enhanced tearDownAfterClass()
 * @version 1.2.0 2018-09-02 add timeouts
 * @version 1.3.0 2019-02-08 add tests of toArray() via toString() to
 *          testFullStack() and testStackGrowth()
 * @version 2.0.0 2019-05-07
 *          <ul>
 *          <li>start restructuring tests
 *          <li>disable System.exit() during testing
 *          <li>start making each subtest independent so they'll all run even if
 *          one fails
 *          <li>add tests for instantiating Stacks with initial capacity of 0
 *          and negative
 *          </ul>
 * @version 2.1.0 2019-06-30
 *          <ul>
 *          <li>replace infrastructure with enhanced code
 *          <li>switch interface to use TestableArrayStackInterface
 *          </ul>
 */
@DisplayName( "ArrayStack" )
@TestInstance( Lifecycle.PER_CLASS )
@TestMethodOrder( OrderAnnotation.class )
class ArrayStackDMRTests extends DMRJUnitTestsBase
	{

	/*
	 * local constants
	 */
	private static final int SMALL_CAPACITY	 = 13 ;
	private static final int MEDIUM_CAPACITY = SMALL_CAPACITY +
	                                           ( SMALL_CAPACITY / 2 ) ;
	private static final int LARGE_CAPACITY	 = MAX_CAPACITY / 2 ;

	private static final int STACK_1_BASE	 = 100 ;
	private static final int STACK_2_BASE	 = 100000 ;

	/*
	 * Per-API method tests
	 */


	/*
	 * test constructors
	 */

	/**
	 * Test method for
	 * {@link edu.wit.dcsn.comp2000.stack.adt.ArrayStack#ArrayStack()}.
	 */
	@Test
	@Order( 1100 )
	@DisplayName( "new ArrayStack( default capacity )" )
	@Disabled
	final void testArrayStack()
		{
		fail( "Not yet implemented" ) ; // TODO

		}	// end testArrayStack()


	/**
	 * Test method for
	 * {@link edu.wit.dcsn.comp2000.stack.adt.ArrayStack#ArrayStack(int)}.
	 */
	@Test
	@Order( 1200 )
	@DisplayName( "new ArrayStack( specified initial capacity )" )
	@Disabled
	final void testArrayStackInt()
		{
		fail( "Not yet implemented" ) ; // TODO

		}	// end testArrayStackInt()


	/*
	 * test API methods
	 */

	/**
	 * Test method for
	 * {@link edu.wit.dcsn.comp2000.stack.adt.ArrayStack#clear()}.
	 */
	@Test
	@Order( 1300 )
	@DisplayName( "clear()" )
	@Disabled
	final void testClear()
		{
		fail( "Not yet implemented" ) ; // TODO

		}	// end testClear()


	/**
	 * Test method for
	 * {@link edu.wit.dcsn.comp2000.stack.adt.ArrayStack#isEmpty()}.
	 */
	@Test
	@Order( 1400 )
	@DisplayName( "isEmpty()" )
	@Disabled
	final void testIsEmpty()
		{
		fail( "Not yet implemented" ) ; // TODO

		}	// end testIsEmpty()


	/**
	 * Test method for
	 * {@link edu.wit.dcsn.comp2000.stack.adt.ArrayStack#peek()}.
	 */
	@Test
	@Order( 1500 )
	@DisplayName( "peek()" )
	@Disabled
	final void testPeek()
		{
		fail( "Not yet implemented" ) ; // TODO

		}	// end testPeek()


	/**
	 * Test method for {@link edu.wit.dcsn.comp2000.stack.adt.ArrayStack#pop()}.
	 */
	@Test
	@Order( 1600 )
	@DisplayName( "pop()" )
	@Disabled
	final void testPop()
		{
		fail( "Not yet implemented" ) ; // TODO

		}	// end testPop()


	/**
	 * Test method for
	 * {@link edu.wit.dcsn.comp2000.stack.adt.ArrayStack#push(java.lang.Object)}.
	 */
	@Test
	@Order( 1700 )
	@DisplayName( "push()" )
	@Disabled
	final void testPush()
		{
		fail( "Not yet implemented" ) ; // TODO

		}	// end testPush()


    /**
     * Test method for
     * {@link edu.wit.dcsn.comp2000.stack.adt.ArrayStack#size()}.
     */
    @Test
    @Order( 1800 )
    @DisplayName( "size()" )
    @Disabled
    final void testSize()
        {
        fail( "Not yet implemented" ) ; // TODO

        }   // end testSize()


    /**
     * Test method for
     * {@link edu.wit.dcsn.comp2000.stack.adt.ArrayStack#toArray()}.
     */
    @Test
    @Order( 1900 )
    @DisplayName( "toArray()" )
    @Disabled
    final void testToArray()
        {
        fail( "Not yet implemented" ) ; // TODO

        }   // end testToArray()
	

	// ----------


	/*
	 * behavioral tests
	 */

	/**
	 * Test instantiating stacks.
	 */
	@Test
	@Order( 10100 )
	@DisplayName( "Instantiate Stack" )
	void testInstantiateStack()
		{
		assertAll( () ->
			{
			assertTimeoutPreemptively( testTimeLimit,
               () ->
                   {
                   lastTestInGroupIsRunning =   false ;
                   currentTestPassed =          false ;

                   TestableStackInterface<Object> testStack =
                                                null ;

                   int testSize =               DEFAULT_CAPACITY ;

                   currentTestsAttempted++ ;
                   writeLog( "[%,d, %,d] Testing: DEFAULT_CAPACITY (%,d) stack (no arg)%n",
                             currentTestGroup, currentTestsAttempted,
                             testSize ) ;

                   testStack =                  new ArrayStack<>() ;

                   assertNotNull( testStack ) ;
                   assertTrue( testStack.isEmpty() ) ;

                   final ArrayStack<Object> finalStack =
                                              ( ArrayStack<Object> ) testStack ;

                   assertEquals( 0,
                                 finalStack.size() ) ;

                   assertThrows( EmptyStackException.class,
                                 () -> finalStack.peek() ) ;

                   assertThrows( EmptyStackException.class,
                                 () -> finalStack.pop() ) ;

                   currentTestPassed =          true ;
                   testPassed() ;
                   } ) ;
			},
       () ->
           {
           assertTimeoutPreemptively( testTimeLimit,
              () ->
                  {
                  lastTestInGroupIsRunning =    false ;
                  currentTestPassed =           false ;

                  TestableStackInterface<Object> testStack =
                                                null ;

                  int testSize =                DEFAULT_CAPACITY ;

                  currentTestsAttempted++ ;
                  writeLog( "[%,d, %,d] Testing: DEFAULT_CAPACITY (%,d) stack (explicit)%n",
                            currentTestGroup, currentTestsAttempted,
                            testSize ) ;

                  testStack =                   new ArrayStack<>( testSize ) ;

                  assertNotNull( testStack ) ;
                  assertTrue( testStack.isEmpty() ) ;

                  final ArrayStack<Object> finalStack =
                                              ( ArrayStack<Object> ) testStack ;

                  assertThrows( EmptyStackException.class,
                                () -> finalStack.peek() ) ;

                  assertThrows( EmptyStackException.class,
                                () -> finalStack.pop() ) ;

                  assertEquals( 0,
                                finalStack.toArray().length ) ;

                  currentTestPassed =           true ;
                  testPassed() ;
                  } ) ;
           },
       () ->
           {
           assertTimeoutPreemptively( testTimeLimit,
              () ->
                  {
                  lastTestInGroupIsRunning =    false ;
                  currentTestPassed =           false ;

                  TestableStackInterface<Object> testStack =    null ;

                  int testSize =                SMALL_CAPACITY ;

                  currentTestsAttempted++ ;
                  writeLog( "[%,d, %,d] Testing: SMALL_CAPACITY (%,d) stack%n",
                            currentTestGroup, currentTestsAttempted,
                            testSize ) ;

                  testStack =                   new ArrayStack<>( testSize ) ;

                  assertNotNull( testStack ) ;
                  assertTrue( testStack.isEmpty() ) ;

                  final ArrayStack<Object> finalStack =
                                              ( ArrayStack<Object> ) testStack ;

                  assertThrows( EmptyStackException.class,
                                () -> finalStack.peek() ) ;

                  assertThrows( EmptyStackException.class,
                                () -> finalStack.pop() ) ;

                  assertEquals( 0,
                                finalStack.toArray().length ) ;

                  currentTestPassed =           true ;
                  testPassed() ;
                  } ) ;
	           },
           () ->
	           {
	           assertTimeoutPreemptively( testTimeLimit,
                  () ->
                      {
                      lastTestInGroupIsRunning =    false ;
                      currentTestPassed =       false ;

                      TestableStackInterface<Object> testStack =    null ;

                      int testSize =            MEDIUM_CAPACITY ;

                      currentTestsAttempted++ ;
                      writeLog( "[%,d, %,d] Testing: MEDIUM_CAPACITY (%,d) stack%n",
                                currentTestGroup, currentTestsAttempted,
                                testSize ) ;

                      testStack =               new ArrayStack<>( testSize ) ;

                      assertNotNull( testStack ) ;
                      assertTrue( testStack.isEmpty() ) ;

                      final ArrayStack<Object> finalStack =
                                              ( ArrayStack<Object> ) testStack ;

                      assertThrows( EmptyStackException.class,
                                    () -> finalStack.peek() ) ;

                      assertThrows( EmptyStackException.class,
                                    () -> finalStack.pop() ) ;

                      assertEquals( 0,
                                    finalStack.toArray().length ) ;

                      currentTestPassed =       true ;
                      testPassed() ;
                      } ) ;
	           },
           () ->
	           {
	           assertTimeoutPreemptively( testTimeLimit,
                  () ->
                      {
                      lastTestInGroupIsRunning =    false ;
                      currentTestPassed =       false ;

                      TestableStackInterface<Object> testStack =    null ;

                      int testSize =            LARGE_CAPACITY ;

                      currentTestsAttempted++ ;
                      writeLog( "[%,d, %,d] Testing: LARGE_CAPACITY (%,d) stack%n",
                                currentTestGroup, currentTestsAttempted,
                                testSize ) ;

                      testStack =               new ArrayStack<>( testSize ) ;

                      assertNotNull( testStack ) ;
                      assertTrue( testStack.isEmpty() ) ;

                      final ArrayStack<Object> finalStack =
                                              ( ArrayStack<Object> ) testStack ;

                      assertThrows( EmptyStackException.class,
                                    () -> finalStack.peek() ) ;

                      assertThrows( EmptyStackException.class,
                                    () -> finalStack.pop() ) ;

                      assertEquals( 0,
                                    finalStack.toArray().length ) ;

                      currentTestPassed =       true ;
                      testPassed() ;
                      } ) ;
	           },
           () ->
	           {
	           assertTimeoutPreemptively( testTimeLimit,
                  () ->
                      {
                      lastTestInGroupIsRunning =    false ;
                      currentTestPassed =       false ;

                      TestableStackInterface<Object> testStack =    null ;

                      int testSize =            MAX_CAPACITY ;

                      currentTestsAttempted++ ;
                      writeLog( "[%,d, %,d] Testing: MAX_CAPACITY (%,d) stack%n",
                                currentTestGroup, currentTestsAttempted,
                                testSize ) ;

                      testStack =               new ArrayStack<>( testSize ) ;

                      assertNotNull( testStack ) ;
                      assertTrue( testStack.isEmpty() ) ;

                      final ArrayStack<Object> finalStack =
                                              ( ArrayStack<Object> ) testStack ;

                      assertThrows( EmptyStackException.class,
                                    () -> finalStack.peek() ) ;

                      assertThrows( EmptyStackException.class,
                                    () -> finalStack.pop() ) ;

                      assertEquals( 0,
                                    finalStack.toArray().length ) ;

                      currentTestPassed =       true ;
                      testPassed() ;
                      } ) ;
	           },
           () ->
	           {
	           assertTimeoutPreemptively( testTimeLimit,
                  () ->
                      {
                      lastTestInGroupIsRunning =    false ;
                      currentTestPassed =       false ;

                      int testSize =            MAX_CAPACITY + 1 ;

                      currentTestsAttempted++ ;
                      writeLog( "[%,d, %,d] Testing: over MAX_CAPACITY (%,d) stack%n",
                                currentTestGroup, currentTestsAttempted,
                                testSize ) ;

                      assertThrows( IllegalStateException.class,
                                    () -> new ArrayStack<>( testSize ) ) ;

                      currentTestPassed =       true ;
                      testPassed() ;
                      } ) ;
	           },
           () ->
	           {
	           assertTimeoutPreemptively( testTimeLimit,
                  () ->
                      {
                      lastTestInGroupIsRunning =    false ;
                      currentTestPassed =       false ;

                      int testSize =            0 ;

                      currentTestsAttempted++ ;
                      writeLog( "[%,d, %,d] Testing: zero initial capacity (%,d) stack%n",
                                currentTestGroup, currentTestsAttempted,
                                testSize ) ;

                      assertThrows( IllegalStateException.class,
                                    () -> new ArrayStack<>( testSize ) ) ;

                      currentTestPassed =       true ;
                      testPassed() ;
                      } ) ;
	           },
           () ->
	           {
	           assertTimeoutPreemptively( testTimeLimit,
                  () ->
                      {
                      lastTestInGroupIsRunning =    true ;
                      currentTestPassed =       false ;

                      int testSize =            -1 ;

                      currentTestsAttempted++ ;
                      writeLog( "[%,d, %,d] Testing: negative initial capacity (%,d) stack%n",
                                currentTestGroup, currentTestsAttempted,
                                testSize ) ;

                      assertThrows( IllegalStateException.class,
                                    () -> new ArrayStack<>( testSize ) ) ;

                      currentTestPassed =       true ;
                      } ) ;
           } ) ;	// end assertAll

		}	// end testInstantiateStack()


	/**
	 * Test empty stack.
	 */
	@Test
	@Order( 10200 )
	@DisplayName( "Empty Stack" )
	void testEmptyStack()
		{
		assertTimeoutPreemptively( testTimeLimit,
           () ->
               {
               lastTestInGroupIsRunning =       true ;	// temp location

               TestableStackInterface<String> testStack ;

               int testSize ;

               boolean sawException ;

               /* ----- */

               testSize = DEFAULT_CAPACITY ;

               writeLog( "[%,d] Using DEFAULT_CAPACITY (%,d) stack:%n",
                         currentTestGroup,
                         testSize ) ;

// lastTestInGroupIsRunning = false ;
               currentTestPassed =          false ;

               testStack =                  null ;	// reset the pointer
               testStack =                  new ArrayStack<>( testSize ) ;

               currentTestsAttempted++ ;
               writeLog( "[%,d, %,d] Testing: isEmpty()%n",
                         currentTestGroup, currentTestsAttempted ) ;

               assertTrue( testStack.isEmpty() ) ;

               currentTestPassed =          true ;
               testPassed() ;

               /* ----- */

// lastTestInGroupIsRunning = false ;
               currentTestPassed =          false ;

               currentTestsAttempted++ ;
               writeLog( "[%,d, %,d] Testing: clear()%n",
                         currentTestGroup, currentTestsAttempted ) ;

               testStack.clear() ;
               assertTrue( testStack.isEmpty() ) ;

               currentTestPassed =          true ;
               testPassed() ;

               /* ----- */

// lastTestInGroupIsRunning = false ;
               currentTestPassed =          false ;

               currentTestsAttempted++ ;
               writeLog( "[%,d, %,d] Testing: peek()%n",
                         currentTestGroup, currentTestsAttempted ) ;

               sawException =               false ;
               try
                   {
                   testStack.peek() ;
                   }
               catch ( @SuppressWarnings( "unused" ) EmptyStackException e )
                   {
                   sawException =           true ;
                   }
               assertTrue( sawException ) ;
                                       // make sure we got the (right) exception

               currentTestPassed =          true ;
               testPassed() ;

               /* ----- */

// lastTestInGroupIsRunning = false ;
               currentTestPassed =          false ;

               currentTestsAttempted++ ;
               writeLog( "[%,d, %,d] Testing: pop()%n",
                         currentTestGroup, currentTestsAttempted ) ;

               sawException =               false ;
               try
                   {
                   testStack.pop() ;
                   }
               catch ( @SuppressWarnings( "unused" ) EmptyStackException e )
                   {
                   sawException =           true ;
                   }
               assertTrue( sawException ) ;
                                       // make sure we got the (right) exception

               currentTestPassed =          true ;
               testPassed() ;

               /* ----- */
               /* ----- */

               testSize = SMALL_CAPACITY ;

               writeLog( "[%,d] Using SMALL_CAPACITY (%,d) stack:%n",
                         currentTestGroup, 
                         testSize ) ;

// lastTestInGroupIsRunning = false ;
               currentTestPassed =          false ;

               testStack =                  null ;	// reset the pointer
               testStack =                  new ArrayStack<>( testSize ) ;

               currentTestsAttempted++ ;
               writeLog( "[%,d, %,d] Testing: isEmpty()%n",
                         currentTestGroup, currentTestsAttempted ) ;

               assertTrue( testStack.isEmpty() ) ;

               currentTestPassed =          true ;
               testPassed() ;

               /* ----- */

// lastTestInGroupIsRunning = false ;
               currentTestPassed = false ;

               currentTestsAttempted++ ;
               writeLog( "[%,d, %,d] Testing: clear()%n",
                         currentTestGroup, currentTestsAttempted ) ;

               testStack.clear() ;
               assertTrue( testStack.isEmpty() ) ;

               currentTestPassed =          true ;
               testPassed() ;

               /* ----- */

// lastTestInGroupIsRunning = false ;
               currentTestPassed =          false ;

               currentTestsAttempted++ ;
               writeLog( "[%,d, %,d] Testing: peek()%n",
                         currentTestGroup, currentTestsAttempted ) ;

               sawException =               false ;
               try
                   {
                   testStack.peek() ;
                   }
               catch ( @SuppressWarnings( "unused" ) EmptyStackException e )
                   {
                   sawException =           true ;
                   }
               assertTrue( sawException ) ;
                                       // make sure we got the (right) exception

               currentTestPassed =          true ;
               testPassed() ;

               /* ----- */

// lastTestInGroupIsRunning = false ;
               currentTestPassed =          false ;

               currentTestsAttempted++ ;
               writeLog( "[%,d, %,d] Testing: pop()%n",
                         currentTestGroup, currentTestsAttempted ) ;

               sawException =               false ;
               try
                   {
                   testStack.pop() ;
                   }
               catch ( @SuppressWarnings( "unused" ) EmptyStackException e )
                   {
                   sawException =           true ;
                   }
               assertTrue( sawException ) ;
                                       // make sure we got the (right) exception

               currentTestPassed =          true ;
               testPassed() ;

               /* ----- */
               /* ----- */

               testSize =                   MEDIUM_CAPACITY ;

               writeLog( "[%,d] Using MEDIUM_CAPACITY (%,d) stack:%n",
                         currentTestGroup, 
                         testSize ) ;

// lastTestInGroupIsRunning = false ;
               currentTestPassed =          false ;

               testStack =                  null ;	// reset the pointer
               testStack =                  new ArrayStack<>( testSize ) ;

               currentTestsAttempted++ ;
               writeLog( "[%,d, %,d] Testing: isEmpty()%n",
                         currentTestGroup, currentTestsAttempted ) ;

               assertTrue( testStack.isEmpty() ) ;

               currentTestPassed =          true ;
               testPassed() ;

               /* ----- */

// lastTestInGroupIsRunning = false ;
               currentTestPassed =          false ;

               currentTestsAttempted++ ;
               writeLog( "[%,d, %,d] Testing: clear()%n",
                         currentTestGroup, currentTestsAttempted ) ;

               testStack.clear() ;
               assertTrue( testStack.isEmpty() ) ;

               currentTestPassed =          true ;
               testPassed() ;

               /* ----- */

// lastTestInGroupIsRunning = false ;
               currentTestPassed =          false ;

               currentTestsAttempted++ ;
               writeLog( "[%,d, %,d] Testing: peek()%n",
                         currentTestGroup, currentTestsAttempted ) ;

               sawException =               false ;
               try
                   {
                   testStack.peek() ;
                   }
               catch ( @SuppressWarnings( "unused" ) EmptyStackException e )
                   {
                   sawException =           true ;
                   }
               assertTrue( sawException ) ;
                                       // make sure we got the (right) exception

               currentTestPassed =          true ;
               testPassed() ;

               /* ----- */

// lastTestInGroupIsRunning = false ;
               currentTestPassed =          false ;

               currentTestsAttempted++ ;
               writeLog( "[%,d, %,d] Testing: pop()%n",
                         currentTestGroup, currentTestsAttempted ) ;

               sawException =               false ;
               try
                   {
                   testStack.pop() ;
                   }
               catch ( @SuppressWarnings( "unused" ) EmptyStackException e )
                   {
                   sawException =           true ;
                   }
               assertTrue( sawException ) ;
                                       // make sure we got the (right) exception

               currentTestPassed =          true ;
               testPassed() ;

               /* ----- */
               /* ----- */

               testSize =                   LARGE_CAPACITY ;

               writeLog( "[%,d] Using LARGE_CAPACITY (%,d) stack:%n",
                         currentTestGroup,
                         testSize ) ;

// lastTestInGroupIsRunning = false ;
               currentTestPassed =          false ;

               testStack =                  null ;	// reset the pointer
               testStack =                  new ArrayStack<>( testSize ) ;

               currentTestsAttempted++ ;
               writeLog( "[%,d, %,d] Testing: isEmpty()%n",
                         currentTestGroup, currentTestsAttempted ) ;

               assertTrue( testStack.isEmpty() ) ;

               currentTestPassed =          true ;
               testPassed() ;

               /* ----- */

// lastTestInGroupIsRunning = false ;
               currentTestPassed =          false ;

               currentTestsAttempted++ ;
               writeLog( "[%,d, %,d] Testing: clear()%n",
                         currentTestGroup, currentTestsAttempted ) ;

               testStack.clear() ;
               assertTrue( testStack.isEmpty() ) ;

               currentTestPassed =          true ;
               testPassed() ;

               /* ----- */

// lastTestInGroupIsRunning = false ;
               currentTestPassed =          false ;

               currentTestsAttempted++ ;
               writeLog( "[%,d, %,d] Testing: peek()%n",
                         currentTestGroup, currentTestsAttempted ) ;

               sawException =               false ;
               try
                   {
                   testStack.peek() ;
                   }
               catch ( @SuppressWarnings( "unused" ) EmptyStackException e )
                   {
                   sawException =           true ;
                   }
               assertTrue( sawException ) ;
                                       // make sure we got the (right) exception

               currentTestPassed =          true ;
               testPassed() ;

               /* ----- */

// lastTestInGroupIsRunning = false ;
               currentTestPassed =          false ;

               currentTestsAttempted++ ;
               writeLog( "[%,d, %,d] Testing: pop()%n",
                         currentTestGroup, currentTestsAttempted ) ;

               sawException =               false ;
               try
                   {
                   testStack.pop() ;
                   }
               catch ( @SuppressWarnings( "unused" ) EmptyStackException e )
                   {
                   sawException =           true ;
                   }
               assertTrue( sawException ) ;
                                       // make sure we got the (right) exception

               currentTestPassed =          true ;
               testPassed() ;

               /* ----- */
               /* ----- */

               testSize =                   MAX_CAPACITY ;

               writeLog( "[%,d] Using MAX_CAPACITY (%,d) stack:%n",
                         currentTestGroup,
                         testSize ) ;

// lastTestInGroupIsRunning = false ;
               currentTestPassed =          false ;

               testStack =                  null ;	// reset the pointer
               testStack =                  new ArrayStack<>( testSize ) ;

               currentTestsAttempted++ ;
               writeLog( "[%,d, %,d] Testing: isEmpty()%n",
                         currentTestGroup, currentTestsAttempted ) ;

               assertTrue( testStack.isEmpty() ) ;

               currentTestPassed =          true ;
               testPassed() ;

               /* ----- */

// lastTestInGroupIsRunning = false ;
               currentTestPassed =          false ;

               currentTestsAttempted++ ;
               writeLog( "[%,d, %,d] Testing: clear()%n",
                         currentTestGroup, currentTestsAttempted ) ;

               testStack.clear() ;
               assertTrue( testStack.isEmpty() ) ;

               currentTestPassed =          true ;
               testPassed() ;

               /* ----- */

// lastTestInGroupIsRunning = false ;
               currentTestPassed =          false ;

               currentTestsAttempted++ ;
               writeLog( "[%,d, %,d] Testing: peek()%n",
                         currentTestGroup, currentTestsAttempted ) ;

               sawException =               false ;
               try
                   {
                   testStack.peek() ;
                   }
               catch ( @SuppressWarnings( "unused" ) EmptyStackException e )
                   {
                   sawException =           true ;
                   }
               assertTrue( sawException ) ;
                                       // make sure we got the (right) exception

               currentTestPassed =          true ;
               testPassed() ;

               /* ----- */

// lastTestInGroupIsRunning = true ;
               currentTestPassed =          false ;

               currentTestsAttempted++ ;
               writeLog( "[%,d, %,d] Testing: pop()%n",
                         currentTestGroup, currentTestsAttempted ) ;

               sawException =               false ;
               try
                   {
                   testStack.pop() ;
                   }
               catch ( @SuppressWarnings( "unused" ) EmptyStackException e )
                   {
                   sawException =           true ;
                   }
               assertTrue( sawException ) ;
                                       // make sure we got the (right) exception

               currentTestPassed =          true ;
               } ) ;

		}	// end testEmptyStack()


	/**
	 * Test full stack.
	 */
	@Test
	@Order( 10300 )
	@DisplayName( "Full Stack" )
	void testFullStack()
		{
		assertTimeoutPreemptively( testTimeLimit,
           () ->
               {
               lastTestInGroupIsRunning =   true ;	// temp location

               ArrayStack<Integer> testStack ;

               int testSize ;

               /* ----- */

// lastTestInGroupIsRunning = false ;
               currentTestPassed =          false ;

               testSize =                   DEFAULT_CAPACITY ;

               currentTestsAttempted++ ;
               writeLog( "[%,d, %,d] Testing: DEFAULT_CAPACITY (%,d) stack%n",
                         currentTestGroup, currentTestsAttempted,
                         testSize ) ;

               testStack =                  null ;	// reset the pointer
               testStack =                  new ArrayStack<>( testSize ) ;

               Integer[] testArray =        new Integer[ testSize ] ;

               // fill it
               writeLog( "...filling stack%n" ) ;
               for ( int i = 0 ; i < testSize ; i++ )
                   {
                   testStack.push( i ) ;
                   testArray[ testArray.length - 1 - i ] = i ;

                   assertEquals( i,
                                 ( int ) testStack.peek() ) ;
                   }

               // verify contents of the stack
               writeLog( "...verifying stack contents%n" ) ;
               assertArrayEquals( testArray,
                                  testStack.toArray() ) ;

               // empty it
               writeLog( "...emptying stack%n" ) ;
               for ( int i = testSize - 1 ; i >= 0 ; i-- )
                   {
                   assertEquals( i,
                                 ( int ) testStack.pop() ) ;
                   }

               assertTrue( testStack.isEmpty() ) ;

               testPassed() ;

               /* ----- */

// lastTestInGroupIsRunning = false ;
               currentTestPassed =          false ;

               testSize =                   SMALL_CAPACITY ;

               currentTestsAttempted++ ;
               writeLog( "[%,d, %,d] Testing: SMALL_CAPACITY (%,d) stack%n",
                         currentTestGroup, currentTestsAttempted,
                         testSize ) ;

               testStack =                  null ;	// reset the pointer
               testStack =                  new ArrayStack<>( testSize ) ;

               testArray =                  new Integer[ testSize ] ;

               // fill it
               writeLog( "...filling stack%n" ) ;
               for ( int i = 0 ; i < testSize ; i++ )
                   {
                   testStack.push( i ) ;
                   testArray[ testArray.length - 1 - i ] = i ;

                   assertEquals( i,
                                 ( int ) testStack.peek() ) ;
                   }

               // verify contents of the stack
               writeLog( "...verifying stack contents%n" ) ;
               assertArrayEquals( testArray,
                                  testStack.toArray() ) ;

               // empty it
               writeLog( "...emptying stack%n" ) ;
               for ( int i = testSize - 1 ; i >= 0 ; i-- )
                   {
                   assertEquals( i,
                                 ( int ) testStack.peek() ) ;
                   assertEquals( i,
                                 ( int ) testStack.pop() ) ;
                   }

               assertTrue( testStack.isEmpty() ) ;

               testPassed() ;

               /* ----- */

// lastTestInGroupIsRunning = false ;
               currentTestPassed =          false ;

               testSize =                   MEDIUM_CAPACITY ;

               currentTestsAttempted++ ;
               writeLog( "[%,d, %,d] Testing: MEDIUM_CAPACITY (%,d) stack%n",
                         currentTestGroup, currentTestsAttempted,
                         testSize ) ;

               testStack =                  null ;	// reset the pointer
               testStack =                  new ArrayStack<>( testSize ) ;

               testArray =                  new Integer[ testSize ] ;

               // fill it
               writeLog( "...filling stack%n" ) ;
               for ( int i = 0 ; i < testSize ; i++ )
                   {
                   testStack.push( i ) ;
                   testArray[ testArray.length - 1 - i ] = i ;

                   assertEquals( i,
                                 ( int ) testStack.peek() ) ;
                   }

               // verify contents of the stack
               writeLog( "...verifying stack contents%n" ) ;
               assertArrayEquals( testArray,
                                  testStack.toArray() ) ;

               // empty it
               writeLog( "...emptying stack%n" ) ;
               for ( int i = testSize - 1 ; i >= 0 ; i-- )
                   {
                   assertEquals( i,
                                 ( int ) testStack.peek() ) ;
                   assertEquals( i,
                                 ( int ) testStack.pop() ) ;
                   }

               assertTrue( testStack.isEmpty() ) ;

               testPassed() ;

               /* ----- */

// lastTestInGroupIsRunning = false ;
               currentTestPassed =          false ;

               testSize =                   LARGE_CAPACITY ;

               currentTestsAttempted++ ;
               writeLog( "[%,d, %,d] Testing: LARGE_CAPACITY (%,d) stack%n",
                         currentTestGroup, currentTestsAttempted,
                         testSize ) ;

               testStack =                  null ;	// reset the pointer
               testStack =                  new ArrayStack<>( testSize ) ;

               testArray =                  new Integer[ testSize ] ;

               // fill it
               writeLog( "...filling stack%n" ) ;
               for ( int i = 0 ; i < testSize ; i++ )
                   {
                   testStack.push( i ) ;
                   testArray[ testArray.length - 1 - i ] = i ;

                   assertEquals( i,
                                 ( int ) testStack.peek() ) ;
                   }

               // verify contents of the stack
               writeLog( "...verifying stack contents%n" ) ;
               assertArrayEquals( testArray,
                                  testStack.toArray() ) ;

               // empty it
               writeLog( "...emptying stack%n" ) ;
               for ( int i = testSize - 1 ; i >= 0 ; i-- )
                   {
                   assertEquals( i,
                                 ( int ) testStack.peek() ) ;
                   assertEquals( i,
                                 ( int ) testStack.pop() ) ;
                   }

               assertTrue( testStack.isEmpty() ) ;

               testPassed() ;

               /* ----- */

// lastTestInGroupIsRunning = true ;
               currentTestPassed =          false ;

               testSize =                   MAX_CAPACITY ;

               currentTestsAttempted++ ;
               writeLog( "[%,d, %,d] Testing: MAX_CAPACITY (%,d) stack%n",
                         currentTestGroup, currentTestsAttempted,
                         testSize ) ;

               testStack =                  null ;	// reset the pointer
               testStack =                  new ArrayStack<>( testSize ) ;

               testArray = new Integer[ testSize ] ;

               // fill it
               writeLog( "...filling stack%n" ) ;
               for ( int i = 0 ; i < testSize ; i++ )
                   {
                   testStack.push( i ) ;
                   testArray[ testArray.length - 1 - i ] = i ;

                   assertEquals( i,
                                 ( int ) testStack.peek() ) ;
                   }

               // verify contents of the stack
               writeLog( "...verifying stack contents%n" ) ;
               assertArrayEquals( testArray,
                                  testStack.toArray() ) ;

               // empty it
               writeLog( "...emptying stack%n" ) ;
               for ( int i = testSize - 1 ; i >= 0 ; i-- )
                   {
                   assertEquals( i,
                                 ( int ) testStack.peek() ) ;
                   assertEquals( i,
                                 ( int ) testStack.pop() ) ;
                   }

               assertTrue( testStack.isEmpty() ) ;

               currentTestPassed =          true ;
               } ) ;

		}	// end testFullStack()


	/**
	 * Test stack growth.
	 */
	@Test
	@Order( 10400 )
	@DisplayName( "Stack Growth" )
	void testStackGrowth()
		{
		assertTimeoutPreemptively( testTimeLimit,
           () ->
               {
               lastTestInGroupIsRunning =   true ;	// temp location

               ArrayStack<Integer> testStack ;

               int testSize ;

               boolean sawException ;

               /* ----- */

// lastTestInGroupIsRunning = false ;
               currentTestPassed =          false ;

               testSize = DEFAULT_CAPACITY ;

               currentTestsAttempted++ ;
               writeLog( "[%,d, %,d] Testing: DEFAULT_CAPACITY (%,d) stack%n",
                         currentTestGroup, currentTestsAttempted,
                         testSize ) ;

               testStack =                  null ;	// reset the pointer
               testStack =                  new ArrayStack<>( testSize ) ;

               Integer[] testArray =        new Integer[ LARGE_CAPACITY + 1 ] ;

               // fill it
               for ( int i = 0 ; i <= LARGE_CAPACITY ; i++ )
                   {
                   testStack.push( i ) ;
                   testArray[ testArray.length - 1 - i ] = i ;

                   assertEquals( i,
                                 ( int ) testStack.peek() ) ;
                   }

               // verify contents of the stack
               writeLog( "...verifying stack contents%n" ) ;
               assertArrayEquals( testArray,
                                  testStack.toArray() ) ;

               // empty it
               for ( int i = LARGE_CAPACITY ; i >= 0 ; i-- )
                   {
                   assertEquals( i,
                                 ( int ) testStack.peek() ) ;
                   assertEquals( i,
                                 ( int ) testStack.pop() ) ;
                   }

               assertTrue( testStack.isEmpty() ) ;

               testPassed() ;

               /* ----- */

// lastTestInGroupIsRunning = false ;
               currentTestPassed =          false ;

               testSize =                   SMALL_CAPACITY ;

               currentTestsAttempted++ ;
               writeLog( "[%,d, %,d] Testing: SMALL_CAPACITY (%,d) stack%n",
                         currentTestGroup, currentTestsAttempted,
                         testSize ) ;

               testStack =                  null ;	// reset the pointer
               testStack =                  new ArrayStack<>( testSize ) ;

               testArray =                  new Integer[ LARGE_CAPACITY + 1 ] ;

               // fill it
               for ( int i = 0 ; i <= LARGE_CAPACITY ; i++ )
                   {
                   testStack.push( i ) ;
                   testArray[ testArray.length - 1 - i ] = i ;

                   assertEquals( i,
                                 ( int ) testStack.peek() ) ;
                   }

               // verify contents of the stack
               writeLog( "...verifying stack contents%n" ) ;
               assertArrayEquals( testArray,
                                  testStack.toArray() ) ;

               // empty it
               for ( int i = LARGE_CAPACITY ; i >= 0 ; i-- )
                   {
                   assertEquals( i,
                                 ( int ) testStack.peek() ) ;
                   assertEquals( i,
                                 ( int ) testStack.pop() ) ;
                   }

               assertTrue( testStack.isEmpty() ) ;

               testPassed() ;

               /* ----- */

// lastTestInGroupIsRunning = false ;
               currentTestPassed =          false ;

               testSize =                   MEDIUM_CAPACITY ;

               currentTestsAttempted++ ;
               writeLog( "[%,d, %,d] Testing: MEDIUM_CAPACITY (%,d) stack%n",
                         currentTestGroup, currentTestsAttempted,
                         testSize ) ;

               testStack =                  null ;	// reset the pointer
               testStack = new ArrayStack<>( testSize ) ;

               testArray = new Integer[ LARGE_CAPACITY + 1 ] ;

               // fill it
               for ( int i = 0 ; i <= LARGE_CAPACITY ; i++ )
                   {
                   testStack.push( i ) ;
                   testArray[ testArray.length - 1 - i ] = i ;

                   assertEquals( i,
                                 ( int ) testStack.peek() ) ;
                   }

               // verify contents of the stack
               writeLog( "...verifying stack contents%n" ) ;
               assertArrayEquals( testArray,
                                  testStack.toArray() ) ;

               // empty it
               for ( int i = LARGE_CAPACITY ; i >= 0 ; i-- )
                   {
                   assertEquals( i,
                                 ( int ) testStack.peek() ) ;
                   assertEquals( i,
                                 ( int ) testStack.pop() ) ;
                   }

               assertTrue( testStack.isEmpty() ) ;

               testPassed() ;

               /* ----- */

// lastTestInGroupIsRunning = false ;
               currentTestPassed =          false ;

               testSize =                   LARGE_CAPACITY ;

               currentTestsAttempted++ ;
               writeLog( "[%,d, %,d] Testing: LARGE_CAPACITY (%,d) stack%n",
                         currentTestGroup, currentTestsAttempted,
                         testSize ) ;

               testStack =                  null ;	// reset the pointer
               testStack =                  new ArrayStack<>( testSize ) ;

               testArray =                  new Integer[ MAX_CAPACITY ] ;

               // fill it
               for ( int i = 0 ; i < MAX_CAPACITY ; i++ )
                   {
                   testStack.push( i ) ;
                   testArray[ testArray.length - 1 - i ] = i ;

                   assertEquals( i,
                                 ( int ) testStack.peek() ) ;
                   }

               // verify contents of the stack
               writeLog( "...verifying stack contents%n" ) ;
               assertArrayEquals( testArray,
                                  testStack.toArray() ) ;

               // empty it
               for ( int i = MAX_CAPACITY - 1 ; i >= 0 ; i-- )
                   {
                   assertEquals( i,
                                 ( int ) testStack.peek() ) ;
                   assertEquals( i,
                                 ( int ) testStack.pop() ) ;
                   }

               assertTrue( testStack.isEmpty() ) ;

               testPassed() ;

               /* ----- */

// lastTestInGroupIsRunning = false ;
               currentTestPassed =          false ;

               testSize =                   MAX_CAPACITY ;

               currentTestsAttempted++ ;
               writeLog( "[%,d, %,d] Testing: MAX_CAPACITY (%,d) stack%n",
                         currentTestGroup, currentTestsAttempted,
                         testSize ) ;

               testStack =                  null ;	// reset the pointer
               testStack =                  new ArrayStack<>( testSize ) ;

               testArray =                  new Integer[ MAX_CAPACITY ] ;

               // fill it
               for ( int i = 0 ; i < testSize ; i++ )
                   {
                   testStack.push( i ) ;
                   testArray[ testArray.length - 1 - i ] = i ;

                   assertEquals( i,
                                 ( int ) testStack.peek() ) ;
                   }

               // verify contents of the stack
               writeLog( "...verifying stack contents%n" ) ;
               assertArrayEquals( testArray,
                                  testStack.toArray() ) ;

               // empty it
               for ( int i = testSize - 1 ; i >= 0 ; i-- )
                   {
                   assertEquals( i,
                                 ( int ) testStack.peek() ) ;
                   assertEquals( i,
                                 ( int ) testStack.pop() ) ;
                   }

               assertTrue( testStack.isEmpty() ) ;

               testPassed() ;

               /* ----- */

// lastTestInGroupIsRunning = false ;
               currentTestPassed =          false ;

               testSize =                   LARGE_CAPACITY ;

               currentTestsAttempted++ ;
               writeLog( "[%,d, %,d] Testing: over-fill a LARGE_CAPACITY (%,d) stack%n",
                         currentTestGroup, currentTestsAttempted,
                         testSize ) ;

               testStack =                  null ;	// reset the pointer
               testStack =                  new ArrayStack<>( testSize ) ;

               testArray =                  new Integer[ MAX_CAPACITY ] ;

               // fill it
               for ( int i = 0 ; i < MAX_CAPACITY ; i++ )
                   {
                   testStack.push( i ) ;
                   testArray[ testArray.length - 1 - i ] = i ;

                   assertEquals( i,
                                 ( int ) testStack.peek() ) ;
                   }

               // try to over-fill it
               sawException =               false ;
               try
                   {
                   testStack.push( MAX_CAPACITY ) ;
                   }
               catch ( @SuppressWarnings( "unused" ) IllegalStateException e )
                   {
                   sawException =           true ;
                   }
               assertTrue( sawException ) ;
                                       // make sure we got the (right) exception

               // verify contents of the stack
               writeLog( "...verifying stack contents%n" ) ;
               assertArrayEquals( testArray,
                                  testStack.toArray() ) ;

               // empty it
               for ( int i = MAX_CAPACITY - 1 ; i >= 0 ; i-- )
                   {
                   assertEquals( i,
                                 ( int ) testStack.peek() ) ;
                   assertEquals( i,
                                 ( int ) testStack.pop() ) ;
                   }

               assertTrue( testStack.isEmpty() ) ;

               testPassed() ;

               /* ----- */

// lastTestInGroupIsRunning = true ;
               currentTestPassed =          false ;

               testSize =                   MAX_CAPACITY ;

               currentTestsAttempted++ ;
               writeLog( "[%,d, %,d] Testing: over-fill a MAX_CAPACITY (%,d) stack%n",
                         currentTestGroup, currentTestsAttempted,
                         testSize ) ;

               testStack =                  null ;	// reset the pointer
               testStack =                  new ArrayStack<>( testSize ) ;

               testArray = new Integer[ MAX_CAPACITY ] ;

               // fill it
               for ( int i = 0 ; i < testSize ; i++ )
                   {
                   testStack.push( i ) ;
                   testArray[ testArray.length - 1 - i ] = i ;

                   assertEquals( i,
                                 ( int ) testStack.peek() ) ;
                   }

               // verify contents of the stack
               writeLog( "...verifying stack contents%n" ) ;
               assertArrayEquals( testArray,
                                  testStack.toArray() ) ;

               // try to over-fill it
               sawException =               false ;
               try
                   {
                   testStack.push( MAX_CAPACITY ) ;
                   }
               catch ( @SuppressWarnings( "unused" ) IllegalStateException e )
                   {
                   sawException =           true ;
                   }
               assertTrue( sawException ) ;
                                       // make sure we got the (right) exception

               compareArrays( testStack.toArray(), 
                              testArray, 
                              IS_UNORDERED ) ;

               // empty it
               for ( int i = MAX_CAPACITY - 1 ; i >= 0 ; i-- )
                   {
                   assertEquals( i,
                                 ( int ) testStack.peek() ) ;
                   assertEquals( i,
                                 ( int ) testStack.pop() ) ;
                   }

               assertTrue( testStack.isEmpty() ) ;

               currentTestPassed =          true ;
               } ) ;

		}	// end testStackGrowth()


	/**
	 * Test multiple stacks.
	 */
	@Test
	@Order( 10500 )
	@DisplayName( "Multiple Stacks" )
	void testMultipleStacks()
		{
		assertTimeoutPreemptively( testTimeLimit,
           () ->
               {
               lastTestInGroupIsRunning =   true ;	// temp location

               ArrayStack<Integer> testStack1 ;
               ArrayStack<Integer> testStack2 ;

               Integer testValue37 =        new Integer( 37 ) ;
               Integer testValue42 =        new Integer( 42 ) ;

               /* ----- */

// lastTestInGroupIsRunning = false ;
               currentTestPassed =          false ;

               currentTestsAttempted++ ;
               writeLog( "[%,d, %,d] Testing: multiple stack instances (1)%n",
                         currentTestGroup, currentTestsAttempted ) ;

               /*
                * - instantiate 2 stacks
                * - add an item to one stack
                * - make sure it contains the item and other is still empty
                * - repeat test with opposite stacks
                * - repeat test with both stacks simultaneously
                * - remove the items and make sure both stacks are empty
                */

               writeLog( "...instantiate 2 stacks" ) ;
               testStack1 =                 null ;	// reset the pointer
               testStack1 =               new ArrayStack<>( DEFAULT_CAPACITY ) ;

               testStack2 =                 null ;	// reset the pointer
               testStack2 =               new ArrayStack<>( DEFAULT_CAPACITY ) ;

               // add an item to testStack1
               writeLog( "...push 1 item onto stack 1%n" ) ;
               testStack1.push( testValue42 ) ;

               writeLog( "...test for item on stack 1%n" ) ;
               assertFalse( testStack1.isEmpty() ) ;
               assertEquals( testValue42,
                             testStack1.peek() ) ;

               // testStack2 must still be empty
               writeLog( "...test stack 2 for empty%n" ) ;
               assertTrue( testStack2.isEmpty() ) ;

               // we can remove the item from testStack1
               // and both stacks are now empty
               writeLog( "...pop item from stack 1%n" ) ;
               assertEquals( testValue42,
                             testStack1.pop() ) ;

               writeLog( "...verify both stacks empty%n" ) ;
               assertTrue( testStack1.isEmpty() ) ;
               assertTrue( testStack2.isEmpty() ) ;

               // add an item to testStack2
               writeLog( "...push 1 item onto stack 2%n" ) ;
               testStack2.push( testValue37 ) ;

               writeLog( "...test for item on stack 2%n" ) ;
               assertFalse( testStack2.isEmpty() ) ;
               assertEquals( testValue37,
                             testStack2.peek() ) ;

               // testStack1 must still be empty
               writeLog( "...test stack 1 for empty%n" ) ;
               assertTrue( testStack1.isEmpty() ) ;

               // we can remove the item from testStack2
               // and both stacks are now empty
               writeLog( "...pop item from stack 2%n" ) ;
               assertEquals( testValue37,
                             testStack2.pop() ) ;

               writeLog( "...verify both stacks empty%n" ) ;
               assertTrue( testStack1.isEmpty() ) ;
               assertTrue( testStack2.isEmpty() ) ;

               // add an item to testStack1
               writeLog( "...push 1 item onto each stack%n" ) ;
               testStack1.push( testValue42 ) ;
               testStack2.push( testValue37 ) ;

               writeLog( "...test for correct items on each stack%n" ) ;
               assertFalse( testStack1.isEmpty() ) ;
               assertEquals( testValue42,
                             testStack1.peek() ) ;
               assertFalse( testStack2.isEmpty() ) ;
               assertEquals( testValue37,
                             testStack2.peek() ) ;

               // we can remove the items from each and
               // both stacks are now empty
               writeLog( "...pop items from each stack%n" ) ;
               assertEquals( testValue42,
                             testStack1.pop() ) ;
               assertEquals( testValue37,
                             testStack2.pop() ) ;

               writeLog( "...verify both stacks empty%n" ) ;
               assertTrue( testStack1.isEmpty() ) ;
               assertTrue( testStack2.isEmpty() ) ;

               currentTestPassed =          true ;
               testPassed() ;

               /* ----- */

// lastTestInGroupIsRunning = false ;
               currentTestPassed =          false ;

               currentTestsAttempted++ ;
               writeLog( "[%,d, %,d] Testing: multiple stack instances (2)%n",
                         currentTestGroup, currentTestsAttempted ) ;

               /*
                * - instantiate stack 1
                * - add an item to one stack
                * - instantiate stack 2
                * - make sure stack 1 contains the item and stack 2 is empty
                * - remove the item from stack 1 and make sure both stacks are 
                *       empty
                */

               writeLog( "...instantiate stack 1%n" ) ;
               testStack1 =                 null ;	// reset the pointer
               testStack1 =               new ArrayStack<>( DEFAULT_CAPACITY ) ;

               // add an item to testStack1
               writeLog( "...push 1 item onto stack 1%n" ) ;
               testStack1.push( testValue42 ) ;

               writeLog( "...test for item on stack 1%n" ) ;
               assertFalse( testStack1.isEmpty() ) ;
               assertEquals( testValue42,
                             testStack1.peek() ) ;

               writeLog( "...instantiate stack 2%n" ) ;
               testStack2 =                 null ;	// reset the pointer
               testStack2 =               new ArrayStack<>( DEFAULT_CAPACITY ) ;

               // testStack2 must be empty
               writeLog( "...test stack 2 for empty%n" ) ;
               assertTrue( testStack2.isEmpty() ) ;

               // we can remove the item from testStack1
               // and both stacks are now empty
               writeLog( "...pop item from stack 1%n" ) ;
               assertEquals( testValue42,
                             testStack1.pop() ) ;

               writeLog( "...verify both stacks empty%n" ) ;
               assertTrue( testStack1.isEmpty() ) ;
               assertTrue( testStack2.isEmpty() ) ;

               currentTestPassed =          true ;
               testPassed() ;

               /* ----- */

// lastTestInGroupIsRunning = false ;
               currentTestPassed =          false ;

               currentTestsAttempted++ ;
               writeLog( "[%,d, %,d] Testing: multiple stack instances (3)%n",
                         currentTestGroup, currentTestsAttempted ) ;

               /*
                * - instantiate 2 stacks
                * - add items to each stack
                * - make sure each stack contains the correct items
                * - remove the items from both stacks and make sure they are
                *       both empty
                */

               writeLog( "...instantiate 2 stacks%n" ) ;
               testStack1 =                 null ;	// reset the pointer
               testStack1 =                 new ArrayStack<>( SMALL_CAPACITY ) ;

               testStack2 =                 null ;	// reset the pointer
               testStack2 =                 new ArrayStack<>( SMALL_CAPACITY ) ;

               // add items to testStack1
               writeLog( "...push multiple items onto stack 1%n" ) ;
               for ( int i = 0 ; i < MEDIUM_CAPACITY ; i++ )
                   {
                   testStack1.push( STACK_1_BASE + i ) ;
                   }

               // add an items to testStack2
               writeLog( "...push multiple items onto stack 2%n" ) ;
               for ( int i = 0 ; i < MEDIUM_CAPACITY ; i++ )
                   {
                   testStack2.push( STACK_2_BASE + i ) ;
                   }

               // remove items from testStack1
               writeLog( "...test for items on stack 1%n" ) ;
               assertFalse( testStack1.isEmpty() ) ;

               for ( int i = MEDIUM_CAPACITY - 1 ; i >= 0 ; i-- )
                   {
                   assertEquals( new Integer( STACK_1_BASE + i ),
                                 testStack1.pop() ) ;
                   }

               assertTrue( testStack1.isEmpty() ) ;

               // remove items from testStack2
               writeLog( "...test for items on stack 2%n" ) ;
               assertFalse( testStack2.isEmpty() ) ;

               for ( int i = MEDIUM_CAPACITY - 1 ; i >= 0 ; i-- )
                   {
                   assertEquals( new Integer( STACK_2_BASE + i ),
                                 testStack2.pop() ) ;
                   }

               assertTrue( testStack2.isEmpty() ) ;

               currentTestPassed =          true ;
               testPassed() ;

               /* ----- */

// lastTestInGroupIsRunning = true ;
               currentTestPassed =          false ;

               currentTestsAttempted++ ;
               writeLog( "[%,d, %,d] Testing: multiple stack instances (4)%n",
                         currentTestGroup, currentTestsAttempted ) ;

               /*
                * - instantiate 2 stacks
                * - add items to each stack
                * - make sure each stack contains the correct items
                * - remove the items from both stacks and make sure they are 
                *       both empty
                */
               writeLog( "...instantiate 2 stacks%n" ) ;
               testStack1 =                 null ;	// reset the pointer
               testStack1 =                 new ArrayStack<>( SMALL_CAPACITY ) ;

               testStack2 = null ;	// reset the pointer
               testStack2 = new ArrayStack<>( SMALL_CAPACITY ) ;

               // add items to testStack1
               writeLog( "...push multiple items onto stack 1%n" ) ;
               for ( int i = 0 ; i < MEDIUM_CAPACITY ; i++ )
                   {
                   testStack1.push( STACK_1_BASE + i ) ;
                   }

               // add an items to testStack2
               writeLog( "...push multiple items onto stack 2%n" ) ;
               for ( int i = 0 ; i < MEDIUM_CAPACITY ; i++ )
                   {
                   testStack2.push( STACK_2_BASE + i ) ;
                   }

               // remove items from testStack2
               writeLog( "...test for items on stack 2%n" ) ;
               assertFalse( testStack2.isEmpty() ) ;

               for ( int i = MEDIUM_CAPACITY - 1 ; i >= 0 ; i-- )
                   {
                   assertEquals( new Integer( STACK_2_BASE + i ),
                                 testStack2.pop() ) ;
                   }

               assertTrue( testStack2.isEmpty() ) ;

               // remove items from testStack1
               writeLog( "...test for items on stack 1%n" ) ;
               assertFalse( testStack1.isEmpty() ) ;

               for ( int i = MEDIUM_CAPACITY - 1 ; i >= 0 ; i-- )
                   {
                   assertEquals( new Integer( STACK_1_BASE + i ),
                                 testStack1.pop() ) ;
                   }

               assertTrue( testStack1.isEmpty() ) ;

               currentTestPassed =          true ;

               /* ----- */

               } ) ;

		}	// end testMultipleStacks()

	
	// --------------------------------------------------
	//
	// The following utilities are used by the test methods
	//
	// --------------------------------------------------


	/**
	 * Utility to copy a stack's contents into an array
	 * 
	 * @param stackToCopy
	 *        the stack to copy
	 * @return array of the contents of stackToCopy or null if stackToCopy is
	 *         null
	 */
	Object[] copyStackIntoArray( TestableStackInterface<Object> stackToCopy )
		{
		Object[] stackContents =            null ;

		if ( stackToCopy !=                 null )
			{
			stackContents =                 new Object[ stackToCopy.size() ] ;

			// collect the contents of the stack
			for ( int i = 0 ; i < stackContents.length ; i++ )
				{
				stackContents[ i ] =        stackToCopy.pop() ;
				}

			// restore the contents of the stack
			for ( int i = 0 ; i < stackContents.length ; i++ )
				{
				stackToCopy.push( stackContents[ i ] ) ;
				}
			}

		return stackContents ;

		}	// end copyStackIntoArray()


	/**
	 * Utility to populate a stack from the contents of an array
	 * 
	 * @param stackToFill
	 *        the stack to populate
	 * @param entries
	 *        the entries to add to the stackToFill
	 */
	private void populateStack( TestableStackInterface<Object> stackToFill,
	                            Object[] entries )
		{
		if ( entries != null )
			{
			for ( Object anEntry : entries )
				{
				stackToFill.push( anEntry ) ;
				}
			}

		}	// end populateStack()

	}	// end class ArrayStackDMRTests
