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


import static org.junit.jupiter.api.Assertions.assertEquals ;
import static org.junit.jupiter.api.Assertions.assertFalse ;
import static org.junit.jupiter.api.Assertions.assertNotNull ;
import static org.junit.jupiter.api.Assertions.assertNull ;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively ;
import static org.junit.jupiter.api.Assertions.assertTrue ;
import static org.junit.jupiter.api.Assertions.fail ;

import org.junit.jupiter.api.Disabled ;
import org.junit.jupiter.api.DisplayName ;
import org.junit.jupiter.api.Order ;
import org.junit.jupiter.api.Test ;
import org.junit.jupiter.api.TestInfo ;
import org.junit.jupiter.params.ParameterizedTest ;
import org.junit.jupiter.params.provider.CsvFileSource ;

import edu.wit.dcsn.comp2000.bagadt.BagInterface ;
import edu.wit.dcsn.comp2000.bagadt.LinkedBag ;

import edu.wit.dcsn.dmr.testing.junit.DMRJUnitTestsBase ;


/**
 * JUnit tests for the LinkedList class. All public and package visible methods
 * are tested. These tests require the API for the LinkedBag class implement
 * BagInterface&lt;T&gt;.
 * 
 * @author David M Rosenberg
 * @version 1.0.0 2018-05-25 initial set of tests<br>
 * @version 1.1.0 2018-06-09 revise structure to use TestInfo instead of certain
 *          hard-coded text
 * @version 1.2.0 2018-09-02 add timeouts
 * @version 1.3.0 2019-01-14 more implementation
 * @version 1.3.1 2019-01-17 cosmetic changes
 * @version 2.0.0 2019-05-12
 *          <ul>
 *          <li>restructure tests
 *          <li>disable System.exit() during testing
 *          <li>start making each subtest independent so they'll all run even if
 *          one fails
 *          </ul>
 * @version 2.1.0 2019-05-17
 *          <ul>
 *          <li>rename class
 *          <li>remove unnecessary throws clauses from @BeforeXxx and @AfterXxx
 *          <li>more fully utilize JUnit 5.4 features
 *          <li>switch tests to data-driven
 *          </ul>
 * @version 3.0.0 2019-06-27
 *          <ul>
 *          <li>complete re-write with reusable testing infrastructure
 *          <li>tests are now data-driven
 *          <li>add summary test results
 *          </ul>
 * @version 3.1.0 2019-06-28 move detailed activity to log file
 * @version 4.0.0 2019-07-04 split general purpose utilities methods into
 *          separate class
 */
@DisplayName( "LinkedBag" )
//@TestInstance( Lifecycle.PER_CLASS )
//@TestMethodOrder( OrderAnnotation.class )
class LinkedBagDMRTests extends DMRJUnitTestsBase
	{
	// all state variables and support methods moved to end of class
	
	
	/**
	 * Test method for
	 * {@link edu.wit.dcsn.comp2000.bagadt.LinkedBag#LinkedBag()}.
	 * 
	 * @param testInfo
	 *        info about the test
	 */
	@Test
	@DisplayName( "no-arg constructor" )
	@Order( 100100 )
	void testNoArgConstructor( TestInfo testInfo )
		{
	    assertTimeoutPreemptively( testTimeLimit, 
		    () ->	{
                	// count this test
                	currentTestsAttempted++ ;
                	
                	// display message describing this test
                   writeLog( "[%,d, %,d] Testing: new LinkedBag()%n" +
                             "\texpect: []%n",
                             currentTestGroup,
                             currentTestsAttempted ) ;
            		
                	// instantiate testBag
            		BagInterface<Object> testBag =	null ;
            		testBag =				new LinkedBag<>() ;
            		
            		// non-null reference?;
            		assertNotNull( testBag ) ;
            		
            		// empty?
            		assertTrue( testBag.isEmpty() );
            		
            		// no entries?"
            		assertEquals( 0,
            		              testBag.getCurrentSize() );
            		
            		
            		// retrieve the contents of the test bag
               		Object[] testBagContents =	copyBagIntoArray( testBag ) ;
               		
           	
                   	// display message describing the actual result of this test
                   writeLog( "\tactual: %s%n",
                             arrayToString( testBagContents ) ) ;

            		currentTestPassed =		true ;
    	    		}
				) ;
   		               		
   		}	// end testNoArgConstructor()


    /**
     * Test method for
     * {@link edu.wit.dcsn.comp2000.bagadt.LinkedBag#LinkedBag(edu.wit.dcsn.comp2000.bagadt.BagInterface)}.
     * 
     * @param isLastTest flag to indicate that this is the last dataset for this
     * test
     * @param isStubBehavior flag to indicate that the result of testing this
     * dataset matches the stubbed behavior
     * @param anotherBagContentsArgument contents for anotherBag
     * @param testInfo info about the test
     */
	@ParameterizedTest( name = "{displayName}:: [{index}] {arguments}" )
	@CsvFileSource( resources = "./test-data/test-cloning-constructor.data",
					numLinesToSkip = 1 )
	@DisplayName( "1-arg, cloning constructor" )
	@Order( 100200 )
	void testCloningConstructor( boolean isLastTest,
	                             boolean isStubBehavior,
	                             String anotherBagContentsArgument,
	                             TestInfo testInfo )
		{
		Object[][] anotherBagContents =	startTest( testInfo,
		                             	           isLastTest,
		                             	           isStubBehavior,
		                             	           anotherBagContentsArgument
		                             	           ) ;
       	
       	
    	// execute the test
	    assertTimeoutPreemptively( testTimeLimit, 
		    () -> {
       	
               	// display message describing the expected result of this test
               writeLog( "\texpect: %s%n",
                         anotherBagContents[ 0 ] == null
                             ? "[]"
                             : arrayToString( anotherBagContents[ 0 ] ) ) ;
                
               	BagInterface<Object> anotherBag =	null ;
               	if ( anotherBagContentsArgument == null )
               		{
               		// anotherBag is null
               		}
               	else
               		{
               		// instantiate and populate anotherBag
                   	anotherBag =				new LinkedBag<>() ;
                   	
                   	populateBag( anotherBag, anotherBagContents[ 0 ] ) ;
               		}
           		
               	// instantiate testBag
           		BagInterface<Object> testBag =	new LinkedBag<>( anotherBag ) ;
           		
           		// non-null reference?
           		assertNotNull( testBag ) ;
           		
           		// empty?
           		if ( anotherBagContents[ 0 ] == null )
           			{
           			assertTrue( testBag.isEmpty() ) ;
           			}
           		else {
               		assertEquals( anotherBagContents[ 0 ].length == 0,
               		              testBag.isEmpty() 
               		              );
               		}
           		
           		// correct number of entries?
           		assertEquals( anotherBag == null
           						? 0
           						: anotherBag.getCurrentSize(),
           		              testBag.getCurrentSize() );
           		
           		// correct entries?
           		Object[] testBagContents =		copyBagIntoArray( testBag ) ;
       	
               	// display message describing the actual result of this test
               writeLog( "\tactual: %s%n",
                         arrayToString( testBagContents ) ) ;
               	

           		// verify the test bag's contents
           		compareArrays( anotherBag == null
           						? new Object[ 0 ]
           						: anotherBagContents[ 0 ], 
           		               testBagContents, 
           		               IS_UNORDERED ) ;
           		

           		// verify another bag's contents
           		// - must be a non-destructive operation
           		
           		// correct entries?
           		Object[] retrievedAnotherBagContents =
       										copyBagIntoArray( anotherBag ) ;
           		
           		compareArrays( anotherBagContents[ 0 ], 
           		               retrievedAnotherBagContents, 
           		               IS_UNORDERED ) ;

           		currentTestPassed =		true ;
   	    		} ) ;
   		               		
   		}	// end testCloningConstructor()
	

	/**
	 * Test method for
	 * {@link edu.wit.dcsn.comp2000.bagadt.LinkedBag#add(java.lang.Object)}.
	 * 
	 * @param isLastTest
	 *        flag to indicate that this is the last dataset for this test
	 * @param isStubBehavior
	 *        flag to indicate that the result of testing this dataset matches
	 *        the stubbed behavior
	 * @param bagContentsArgument
	 *        contents to add to the bag
	 * @param testInfo
	 *        info about the test
	 */
	@ParameterizedTest( name = "{displayName}:: [{index}] {arguments}" )
	@CsvFileSource( resources = "./test-data/test-add.data",
					numLinesToSkip = 1 )
	@DisplayName( "add()" )
	@Order( 400100 )
	void testAdd( boolean isLastTest,
	              boolean isStubBehavior,
	              String bagContentsArgument,
	              TestInfo testInfo )
		{
		Object[][] bagContents =	startTest( testInfo,
		                      		           isLastTest,
		                      		           isStubBehavior,
		                      		           bagContentsArgument
		                      		           ) ;
       	
       	
    	// execute the test
	    assertTimeoutPreemptively( testTimeLimit, 
		    () -> {
               	// display message describing the expected result of this test
               writeLog( "\texpect: %s%n",
                         arrayToString( bagContents[ 0 ] ) ) ;
                
               	// instantiate testBag
               	BagInterface<Object> testBag =	new LinkedBag<>() ;
                
               	// populate it
               	populateBag( testBag, 
               	             bagContents[ 0 ] ) ;
           		
               	// correct number of entries?
           		assertEquals( bagContents[ 0 ].length,
           		              testBag.getCurrentSize() );
           		
           		// correct entries?
           		Object[] testBagContents =	copyBagIntoArray( testBag ) ;
       	
               	// display message describing the actual result of this test
               writeLog( "\tactual: %s%n",
                         arrayToString( testBagContents ) ) ;
           		
           		// verify the bag's contents
           		compareArrays( bagContents[ 0 ], 
           		               testBagContents, 
           		               IS_UNORDERED );

           		currentTestPassed =			true ;
           		} ) ;
       		
   		}	// end testAdd()


	/**
	 * Test method for {@link edu.wit.dcsn.comp2000.bagadt.LinkedBag#clear()}.
	 * 
	 * @param isLastTest
	 *        flag to indicate that this is the last dataset for this test
	 * @param isStubBehavior
	 *        flag to indicate that the result of testing this dataset matches
	 *        the stubbed behavior
	 * @param bagContentsArgument
	 *        contents to add to the bag
	 * @param testInfo
	 *        info about the test
	 */
	@ParameterizedTest( name = "{displayName}:: [{index}] {arguments}" )
	@CsvFileSource( resources = "./test-data/test-clear.data",
					numLinesToSkip = 1 )
	@DisplayName( "clear()" )
	@Order( 200100 )
	void testClear( boolean isLastTest,
	                boolean isStubBehavior,
	                String bagContentsArgument,
	                TestInfo testInfo )
		{
		Object[][] bagContents =	startTest( testInfo,
		                      		           isLastTest,
		                      		           isStubBehavior,
		                      		           bagContentsArgument
		                      		           ) ;
       	
       	
    	// execute the test
	    assertTimeoutPreemptively( testTimeLimit, 
		    () -> {
               	// display message describing the expected result of this test
               writeLog( "\texpect: %s%n",
                         "[]" ) ;
                
               	// instantiate testBag
               	BagInterface<Object> testBag =	new LinkedBag<>() ;
                
               	// populate it
               	populateBag( testBag, 
               	             bagContents[ 0 ] ) ;
           		
               	// correct number of entries?
           		assertEquals( bagContents[ 0 ] == null
           						? 0
           						: bagContents[ 0 ].length,
           		              testBag.getCurrentSize() );
           		
           		// clear it
           		testBag.clear() ;
           		
           		// empty?
           		assertTrue( testBag.isEmpty() ) ;
           		
           		// make sure it is
           		assertNull( testBag.remove() ) ;
           		
           		// display message describing the actual result of this test
               writeLog( "\tactual: %s%n",
                         "[]" ) ;
           		
           		currentTestPassed =		true ;
           		} ) ;
   		               		
   		}	// end testClear()


	/**
	 * Test method for
	 * {@link edu.wit.dcsn.comp2000.bagadt.LinkedBag#contains(java.lang.Object)}.
	 * 
	 * @param isLastTest
	 *        flag to indicate that this is the last dataset for this test
	 * @param isStubBehavior
	 *        flag to indicate that the result of testing this dataset matches
	 *        the stubbed behavior
	 * @param bagContentsArgument
	 *        contents to add to the bag
	 * @param notContainedArgument
	 *        items that aren't contained in the bag
	 * @param testInfo
	 *        info about the test
	 */
	@ParameterizedTest( name = "{displayName}:: [{index}] {arguments}" )
	@CsvFileSource( resources = "./test-data/test-contains.data",
					numLinesToSkip = 1 )
	@DisplayName( "contains()" )
	@Order( 200200 )
	void testContains( boolean isLastTest,
	                boolean isStubBehavior,
	                String bagContentsArgument,
	                String notContainedArgument,
	                TestInfo testInfo )
		{
		Object[][] contents =		startTest( testInfo,
		                     		           isLastTest,
		                      		           isStubBehavior,
		                      		           bagContentsArgument,
		                      		           notContainedArgument
		                      		           ) ;
       	
       	
    	// execute the test
	    assertTimeoutPreemptively( testTimeLimit, 
   		    () -> {
   		    	boolean expectedResult =	arrayContains( contents[ 0 ], 
   		    	                        	               contents[ 1 ][ 0 ] ) ;
   		    	
              	// display message describing the expected result of this test
               writeLog( "\texpect: %b%n",
                         expectedResult ) ;
               
              	// instantiate testBag
              	BagInterface<Object> testBag =	new LinkedBag<>() ;
               
              	// populate it
              	populateBag( testBag, 
              	             contents[ 0 ] ) ;
          		
              	// test contains()
              	boolean actualResult =	testBag.contains( contents[ 1 ][ 0 ] ) ;
          		
          		// display message describing the actual result of this test
               writeLog( "\tactual: %b%n",
                         actualResult ) ;
              	
              	// make sure we got the correct result
              	assertEquals( expectedResult, 
              	              actualResult );
          		
          		currentTestPassed =		true ;
          		} ) ;
   		               		
   		}	// end testContains()


	/**
	 * Test method for
	 * {@link edu.wit.dcsn.comp2000.bagadt.LinkedBag#difference(edu.wit.dcsn.comp2000.bagadt.BagInterface)}.
	 * 
	 * @param isLastTest
	 *        flag to indicate that this is the last dataset for this test
	 * @param isStubBehavior
	 *        flag to indicate that the result of testing this dataset matches
	 *        the stubbed behavior
	 * @param testBagContentsArgument
	 *        contents to add to the testBag
	 * @param anotherBagContainsArgument
	 *        contents to add to anotherBag
	 * @param differenceBagContainsArgument
	 *        contents expected in bag from testBag.difference(anotherBag)
	 * @param testInfo
	 *        info about the test
	 */
	@ParameterizedTest( name = "{displayName}:: [{index}] {arguments}" )
	@CsvFileSource( resources = "./test-data/test-difference.data",
					numLinesToSkip = 1 )
	@DisplayName( "difference()" )
	@Order( 500100 )
	void testDifference( boolean isLastTest,
	                     boolean isStubBehavior,
	                     String testBagContentsArgument,
	                     String anotherBagContainsArgument,
	                     String differenceBagContainsArgument,
	                     TestInfo testInfo )
		{
		Object[][] contents =		startTest( testInfo,
		                     		           isLastTest,
		                      		           isStubBehavior,
		                      		           testBagContentsArgument,
		                      		           anotherBagContainsArgument,
		                      		           differenceBagContainsArgument
		                      		           ) ;
       	
       	
    	// execute the test
	    assertTimeoutPreemptively( testTimeLimit, 
   		    () -> {
              	// display message describing the expected result of this test
               writeLog( "\texpect: %s%n",
                         arrayToString( contents[ 2 ] ) ) ;
               
              	// instantiate testBag and populate it
              	BagInterface<Object> testBag =	new LinkedBag<>() ;
              	populateBag( testBag, 
              	             contents[ 0 ] ) ;
              	
              	// instantiate anotherBag and populate it
              	BagInterface<Object> anotherBag =	new LinkedBag<>() ;
              	populateBag( anotherBag, 
              	             contents[ 1 ] );
          		
              	// calculate the difference
              	BagInterface<Object> differenceBag =
              								testBag.difference( anotherBag ) ;
              	
              	// display message describing the actual result of this test
               writeLog( "\tactual: %s%n",
                         arrayToString( differenceBag.toArray() ) ) ;
              	
              	// verify that the differenceBag's contents are correct
              	compareArrays( contents[ 2 ], 
              	               differenceBag.toArray(), 
              	               IS_UNORDERED 
              	               ) ;
              	
              	// make sure testBag's contents are unchanged
              	compareArrays( contents[ 0 ], 
              	               testBag.toArray(), 
              	               IS_UNORDERED 
              	               );
              	
              	// make sure anotherBag's contents are unchanged
              	compareArrays( contents[ 1 ], 
              	               anotherBag.toArray(), 
              	               IS_UNORDERED 
              	               );
              	
          		currentTestPassed =		true ;
          		} ) ;
    	               		
    	}	// end testDifference()


	/**
	 * Test method for {@link edu.wit.dcsn.comp2000.bagadt.LinkedBag#getCurrentSize()}.
	 * 
	 * @param isLastTest
	 *        flag to indicate that this is the last dataset for this test
	 * @param isStubBehavior
	 *        flag to indicate that the result of testing this dataset matches
	 *        the stubbed behavior
	 * @param bagContentsArgument
	 *        contents to add to the bag
	 * @param testInfo
	 *        info about the test
	 */
	@ParameterizedTest( name = "{displayName}:: [{index}] {arguments}" )
	@CsvFileSource( resources = "./test-data/test-get-current-size.data",
					numLinesToSkip = 1 )
	@DisplayName( "getCurrentSize()" )
	@Order( 200300 )
	void testGetCurrentSize( boolean isLastTest,
	                         boolean isStubBehavior,
	                         String bagContentsArgument,
	                         TestInfo testInfo )
		{
		Object[][] bagContents =	startTest( testInfo,
		                        	           isLastTest,
		                      		           isStubBehavior,
		                      		           bagContentsArgument
		                      		           ) ;
       	
       	
    	// execute the test
	    assertTimeoutPreemptively( testTimeLimit, 
		    () -> {
		    	// determine expected result
		    	int expectedResult =		bagContents[ 0 ].length ;
		    	
               	// display message describing the expected result of this test
               writeLog( "\texpect: %,d%n",
                         expectedResult ) ;
                
               	// instantiate testBag
               	BagInterface<Object> testBag =	new LinkedBag<>() ;
                
               	// populate it
               	populateBag( testBag, 
               	             bagContents[ 0 ] ) ;

              	// determine the number of entries in the bag
              	int actualResult =	testBag.getCurrentSize() ;
          		
          		// display message describing the actual result of this test
               writeLog( "\tactual: %,d%n",
                         actualResult ) ;
              	
              	// check for the correct result
              	assertEquals( expectedResult, actualResult );
           		
           		currentTestPassed =		true ;
           		} ) ;
   		               		
   		}	// end testGetCurrentSize()


	/**
	 * Test method for
	 * {@link edu.wit.dcsn.comp2000.bagadt.LinkedBag#getFrequencyOf(java.lang.Object)}.
	 * 
	 * @param isLastTest
	 *        flag to indicate that this is the last dataset for this test
	 * @param isStubBehavior
	 *        flag to indicate that the result of testing this dataset matches
	 *        the stubbed behavior
	 * @param bagContentsArgument
	 *        contents to add to the bag
	 * @param searchForEntryArgument
	 *        item for which we want its frequency in the bag
	 * @param testInfo
	 *        info about the test
	 */
	@ParameterizedTest( name = "{displayName}:: [{index}] {arguments}" )
	@CsvFileSource( resources = "./test-data/test-get-frequency-of.data",
					numLinesToSkip = 1 )
	@DisplayName( "getFrequencyOf()" )
	@Order( 200400 )
	void testGetFrequencyOf( boolean isLastTest,
	                         boolean isStubBehavior,
	                         String bagContentsArgument,
	                         String searchForEntryArgument,
	                         TestInfo testInfo )
		{
		Object[][] contents =		startTest( testInfo,
		                     		           isLastTest,
		                      		           isStubBehavior,
		                      		           bagContentsArgument,
		                      		           searchForEntryArgument
		                      		           ) ;
       	
       	
    	// execute the test
	    assertTimeoutPreemptively( testTimeLimit, 
   		    () -> {
   		    	// determine number of times the 'search for' entry should 
   		    	// appear in the bag
   		    	int expectedFrequency =	countOccurrences( contents[ 0 ], 
   		    	                     	                  contents[ 1 ][ 0 ] ) ;
   		    
              	// display message describing the expected result of this test
               writeLog( "\texpect: %,d%n",
                         expectedFrequency ) ;
               
              	// instantiate testBag
              	BagInterface<Object> testBag =	new LinkedBag<>() ;
               
              	// populate it
              	populateBag( testBag, 
              	             contents[ 0 ] ) ;
          		
              	// determine the number of times the 'search for' entry appears
              	// in the bag
              	int actualFrequency =
              					testBag.getFrequencyOf( contents[ 1 ][ 0 ] ) ;
          		
          		// display message describing the actual result of this test
               writeLog( "\tactual: %,d%n",
                         actualFrequency ) ;
              	
              	// check for the correct frequency
              	assertEquals( expectedFrequency, actualFrequency );
          		
          		currentTestPassed =		true ;
          		} ) ;
   		               		
   		}	// end testGetFrequencyOf()


	/**
	 * Test method for
	 * {@link edu.wit.dcsn.comp2000.bagadt.LinkedBag#intersection(edu.wit.dcsn.comp2000.bagadt.BagInterface)}.
	 * 
	 * @param isLastTest
	 *        flag to indicate that this is the last dataset for this test
	 * @param isStubBehavior
	 *        flag to indicate that the result of testing this dataset matches
	 *        the stubbed behavior
	 * @param testBagContentsArgument
	 *        contents to add to the testBag
	 * @param anotherBagContainsArgument
	 *        contents to add to anotherBag
	 * @param intersectionBagContainsArgument
	 *        contents expected in bag from testBag.intersection(anotherBag)
	 * @param testInfo
	 *        info about the test
	 */
	@ParameterizedTest( name = "{displayName}:: [{index}] {arguments}" )
	@CsvFileSource( resources = "./test-data/test-intersection.data",
					numLinesToSkip = 1 )
	@DisplayName( "intersection()" )
	@Order( 500200 )
	void intersection( boolean isLastTest,
	                   boolean isStubBehavior,
	                   String testBagContentsArgument,
	                   String anotherBagContainsArgument,
	                   String intersectionBagContainsArgument,
	                   TestInfo testInfo )
		{
		Object[][] contents =		startTest( testInfo,
		                     		           isLastTest,
		                      		           isStubBehavior,
		                      		           testBagContentsArgument,
		                      		           anotherBagContainsArgument,
		                      		           intersectionBagContainsArgument
		                      		           ) ;
       	
       	
    	// execute the test
	    assertTimeoutPreemptively( testTimeLimit, 
   		    () -> {
              	// display message describing the expected result of this test
               writeLog( "\texpect: %s%n",
                         arrayToString( contents[ 2 ] ) ) ;
               
              	// instantiate testBag and populate it
              	BagInterface<Object> testBag =	new LinkedBag<>() ;
              	populateBag( testBag, 
              	             contents[ 0 ] ) ;
              	
              	// instantiate anotherBag and populate it
              	BagInterface<Object> anotherBag =	new LinkedBag<>() ;
              	populateBag( anotherBag, 
              	             contents[ 1 ] );
          		
              	// calculate the intersection
              	BagInterface<Object> intersectionBag =
              								testBag.intersection( anotherBag ) ;
              	
              	// display message describing the actual result of this test
               writeLog( "\tactual: %s%n",
                         arrayToString( intersectionBag.toArray() ) ) ;
              	
              	// verify that the intersectionBag's contents are correct
              	compareArrays( contents[ 2 ], 
              	               intersectionBag.toArray(), 
              	               IS_UNORDERED 
              	               ) ;
              	
              	// make sure testBag's contents are unchanged
              	compareArrays( contents[ 0 ], 
              	               testBag.toArray(), 
              	               IS_UNORDERED 
              	               ) ;
              	
              	// make sure anotherBag's contents are unchanged
              	compareArrays( contents[ 1 ], 
              	               anotherBag.toArray(), 
              	               IS_UNORDERED 
              	               ) ;
              	
          		currentTestPassed =		true ;
          		} ) ;
   		               		
   		}	// end testIntersection()


	/**
	 * Test method for {@link edu.wit.dcsn.comp2000.bagadt.LinkedBag#isEmpty()}.
	 * 
	 * @param isLastTest
	 *        flag to indicate that this is the last dataset for this test
	 * @param isStubBehavior
	 *        flag to indicate that the result of testing this dataset matches
	 *        the stubbed behavior
	 * @param bagContentsArgument
	 *        contents to add to the bag
	 * @param testInfo
	 *        info about the test
	 */
	@ParameterizedTest( name = "{displayName}:: [{index}] {arguments}" )
	@CsvFileSource( resources = "./test-data/test-is-empty.data",
					numLinesToSkip = 1 )
	@DisplayName( "isEmpty()" )
	@Order( 200500 )
	void testIsEmpty( boolean isLastTest,
	                boolean isStubBehavior,
	                String bagContentsArgument,
	                TestInfo testInfo )
		{
		Object[][] bagContents =	startTest( testInfo,
		                        	           isLastTest,
		                      		           isStubBehavior,
		                      		           bagContentsArgument
		                      		           ) ;
       	
       	
    	// execute the test
	    assertTimeoutPreemptively( testTimeLimit, 
		    () -> {
		    	// determine expected result
		    	boolean expectedResult =		bagContents[ 0 ].length == 0 ;
		    	
               	// display message describing the expected result of this test
               writeLog( "\texpect: %b%n",
                         expectedResult ) ;
                
               	// instantiate testBag
               	BagInterface<Object> testBag =	new LinkedBag<>() ;
                
               	// populate it
               	populateBag( testBag, 
               	             bagContents[ 0 ] ) ;

              	// determine the number of times the 'search for' entry appears
              	// in the bag
              	boolean actualResult =	testBag.isEmpty() ;
          		
          		// display message describing the actual result of this test
               writeLog( "\tactual: %b%n",
                         actualResult ) ;
              	
              	// check for the correct result
              	assertEquals( expectedResult, 
              	              actualResult ) ;
           		
           		currentTestPassed =		true ;
           		} ) ;
		    
   		}	// end testIsEmpty()


	/**
	 * Test method for {@link edu.wit.dcsn.comp2000.bagadt.LinkedBag#remove()}.
	 * 
	 * @param isLastTest
	 *        flag to indicate that this is the last dataset for this test
	 * @param isStubBehavior
	 *        flag to indicate that the result of testing this dataset matches
	 *        the stubbed behavior
	 * @param bagContentsArgument
	 *        contents to add to the bag
	 * @param testInfo
	 *        info about the test
	 */
	@ParameterizedTest( name = "{displayName}:: [{index}] {arguments}" )
	@CsvFileSource( resources = "./test-data/test-remove-unspecified.data",
					numLinesToSkip = 1 )
	@DisplayName( "remove()" )
	@Order( 400200 )
	void remove( boolean isLastTest,
	             boolean isStubBehavior,
	             String bagContentsArgument,
	             TestInfo testInfo )
		{
		Object[][] contents =		startTest( testInfo,
		                     		           isLastTest,
		                      		           isStubBehavior,
		                      		           bagContentsArgument
		                      		           ) ;
       	
    	// execute the test
	    assertTimeoutPreemptively( testTimeLimit, 
   		    () -> {
              	// instantiate testBag and populate it
              	BagInterface<Object> testBag =	new LinkedBag<>() ;
  				populateBag( testBag, 
              	             contents[ 0 ] ) ;
          		
  				// remove every item from the bag
  				Object[] removedContents =	new Object[ contents[ 0 ].length ] ;
  				
  				for ( int i = 0; i < contents[ 0 ].length; i++ )
  					{
  					removedContents[ i ] =	testBag.remove() ;
  					}
  				
  				
              	// display message describing the actual result of this test
               writeLog( "\tactual: %s%n",
                         arrayToString( removedContents ) ) ;
               
  				// at this point the bag must be empty
  				assertNull( testBag.remove() ) ;
  				
  				// make sure the items removed match the items added
  				compareArrays( contents[ 0 ], 
  				               removedContents, 
  				               IS_UNORDERED
  				               );
          		
          		currentTestPassed =		true ;
          		} ) ;
   		               		
   		}	// end testRemoveUnspecified() unspecified entry


	/**
	 * Test method for
	 * {@link edu.wit.dcsn.comp2000.bagadt.LinkedBag#remove(java.lang.Object)}.
	 * 
	 * @param isLastTest
	 *        flag to indicate that this is the last dataset for this test
	 * @param isStubBehavior
	 *        flag to indicate that the result of testing this dataset matches
	 *        the stubbed behavior
	 * @param testBagContentsArgument
	 *        contents to add to the testBag
	 * @param testBagContainsArgument
	 *        entries to remove successfully from testBag
	 * @param testBagDoesNotContainsArgument
	 *        entries we can't remove from testBag
	 * @param testBagRemainderContentsArgument
	 *        the expected entries remaining in testBag
	 * @param testInfo
	 *        info about the test
	 */
	@ParameterizedTest( name = "{displayName}:: [{index}] {arguments}" )
	@CsvFileSource( resources = "./test-data/test-remove-specified.data",
					numLinesToSkip = 1 )
	@DisplayName( "remove( anEntry )" )
	@Order( 400300 )
	void testRemoveSpecified( boolean isLastTest,
	                          boolean isStubBehavior,
	                          String testBagContentsArgument,
	                          String testBagContainsArgument,
	                          String testBagDoesNotContainsArgument,
	                          String testBagRemainderContentsArgument,
	                          TestInfo testInfo )
		{
		Object[][] contents =		startTest( testInfo,
		                     		           isLastTest,
		                      		           isStubBehavior,
		                      		           testBagContentsArgument,
		                      		           testBagContainsArgument,
		                      		           testBagDoesNotContainsArgument,
		                      		           testBagRemainderContentsArgument
		                      		           ) ;

		// execute the test
	    assertTimeoutPreemptively( testTimeLimit, 
  		    () -> {
             	// instantiate testBag and populate it
             	BagInterface<Object> testBag =	new LinkedBag<>() ;
             	populateBag( testBag, 
             	             contents[ 0 ] ) ;
             	
             	// display message describing the expected result of this test
               writeLog( "\texpect can be removed: %s%n",
                         arrayToString( contents[ 1 ] ) ) ;
              
             	// remove entries the testBag contains
             	for ( int i = 0; i < contents[ 1 ].length; i++ )
             		{
             		assertTrue( testBag.remove( contents[ 1 ][ i ] ) ) ;
             		}
             	
             	
             	// display message describing the expected result of this test
               writeLog( "\texpect cannot be removed: %s%n",
                         arrayToString( contents[ 2 ] ) ) ;
              
             	// remove entries the testBag doesn't contain
             	for ( int i = 0; i < contents[ 2 ].length; i++ )
             		{
             		assertFalse( testBag.remove( contents[ 2 ][ i ] ) ) ;
             		}
             	
             	
             	// display message describing the expected result of this test
               writeLog( "\texpect what's left: %s%n",
                         arrayToString( contents[ 3 ] ) ) ;
              
             	// display message describing the actual result of this test
               writeLog( "\tactual what's left: %s%n",
                         arrayToString( testBag.toArray() ) ) ;
              
             	// verify that testBag contains the correct entries
             	compareArrays( contents[ 3 ], 
             	               testBag.toArray(), 
             	               IS_UNORDERED );
             	
         		currentTestPassed =		true ;
         		} ) ;
   		               		
   		}	// end testRemoveSpecified() specified entry


	/**
	 * Test method for {@link edu.wit.dcsn.comp2000.bagadt.LinkedBag#toArray()}.
	 * 
	 * @param isLastTest
	 *        flag to indicate that this is the last dataset for this test
	 * @param isStubBehavior
	 *        flag to indicate that the result of testing this dataset matches
	 *        the stubbed behavior
	 * @param bagContentsArgument
	 *        contents to add to the bag
	 * @param testInfo
	 *        info about the test
	 */
	@ParameterizedTest( name = "{displayName}:: [{index}] {arguments}" )
	@CsvFileSource( resources = "./test-data/test-to-array.data",
					numLinesToSkip = 1 )
	@DisplayName( "toArray()" )
	@Order( 300100 )
	void testToArray( boolean isLastTest,
	                  boolean isStubBehavior,
	                  String bagContentsArgument,
	                  TestInfo testInfo )
		{
		Object[][] bagContents =	startTest( testInfo,
		                        	           isLastTest,
		                      		           isStubBehavior,
		                      		           bagContentsArgument
		                      		           ) ;
       	
       	
    	// execute the test
	    assertTimeoutPreemptively( testTimeLimit, 
		    () -> {
               	// display message describing the expected result of this test
               writeLog( "\texpect: %s%n",
                         arrayToString( bagContents[ 0 ] ) ) ;
                
               	// instantiate testBag
               	BagInterface<Object> testBag =	new LinkedBag<>() ;
                
               	// populate it
               	populateBag( testBag, 
               	             bagContents[ 0 ] ) ;
           		
           		// retrieve the contents of the test bag in an array
           		Object[] testBagContents =	testBag.toArray() ;
       	
               	// display message describing the actual result of this test
               writeLog( "\tactual: %s%n",
                         arrayToString( testBagContents ) ) ;
           		
           		// verify the bag's contents
           		compareArrays( bagContents[ 0 ], 
           		               testBagContents, 
           		               IS_UNORDERED );
           		
           		// this operation is non-destructive so must be repeatable
           		// - do it again to make sure...
           		
           		// retrieve the contents of the test bag in an array
           		testBagContents =			testBag.toArray() ;
       	
               	// display message describing the actual result of this test
               writeLog( "\tactual: %s%n",
                         arrayToString( testBagContents ) ) ;
           		
           		// verify the bag's contents
           		compareArrays( bagContents[ 0 ], 
           		               testBagContents, 
           		               IS_UNORDERED );

           		currentTestPassed =			true ;
           		} ) ;
		               		
		}	// end testToArray()


	/**
	 * Test method for {@link edu.wit.dcsn.comp2000.bagadt.LinkedBag#toString()}.
	 */
	@Test
	@DisplayName( "toString()" )
	@Order( 300200 )
	@Disabled			// do not enable without implementing...
	void testToString()
		{
		fail( "No testing necessary" ) ;
		
		}	// end testToString()


	/**
	 * Test method for {@link edu.wit.dcsn.comp2000.bagadt.LinkedBag#union(edu.wit.dcsn.comp2000.bagadt.BagInterface)}.
	 * 
	 * @param isLastTest
	 *        flag to indicate that this is the last dataset for this test
	 * @param isStubBehavior
	 *        flag to indicate that the result of testing this dataset matches
	 *        the stubbed behavior
	 * @param testBagContentsArgument
	 *        contents to add to the testBag
	 * @param anotherBagContainsArgument
	 *        contents to add to anotherBag
	 * @param unionBagContainsArgument
	 *        contents expected in bag from testBag.union(anotherBag)
	 * @param testInfo
	 *        info about the test
	 */
	@ParameterizedTest( name = "{displayName}:: [{index}] {arguments}" )
	@CsvFileSource( resources = "./test-data/test-union.data",
					numLinesToSkip = 1 )
	@DisplayName( "union()" )
	@Order( 500300 )
	void union( boolean isLastTest,
	            boolean isStubBehavior,
	            String testBagContentsArgument,
	            String anotherBagContainsArgument,
	            String unionBagContainsArgument,
	            TestInfo testInfo )
		{
		Object[][] contents =		startTest( testInfo,
		                     		           isLastTest,
		                      		           isStubBehavior,
		                      		           testBagContentsArgument,
		                      		           anotherBagContainsArgument,
		                      		           unionBagContainsArgument
		                      		           ) ;
       	
       	
    	// execute the test
	    assertTimeoutPreemptively( testTimeLimit, 
   		    () -> {
              	// display message describing the expected result of this test
               writeLog( "\texpect: %s%n",
                         arrayToString( contents[ 2 ] ) ) ;
               
              	// instantiate testBag and populate it
              	BagInterface<Object> testBag =	new LinkedBag<>() ;
              	populateBag( testBag, 
              	             contents[ 0 ] ) ;
              	
              	// instantiate anotherBag and populate it
              	BagInterface<Object> anotherBag =	new LinkedBag<>() ;
              	populateBag( anotherBag, 
              	             contents[ 1 ] );
          		
              	// calculate the union
              	BagInterface<Object> unionBag =	testBag.union( anotherBag ) ;
              	
              	// display message describing the actual result of this test
               writeLog( "\tactual: %s%n",
                         arrayToString( unionBag.toArray() ) ) ;
              	
              	// verify that the intersectionBag's contents are correct
              	compareArrays( contents[ 2 ], 
              	               unionBag.toArray(), 
              	               IS_UNORDERED 
              	               ) ;
              	
              	// make sure testBag's contents are unchanged
              	compareArrays( contents[ 0 ], 
              	               testBag.toArray(), 
              	               IS_UNORDERED 
              	               );
              	
              	// make sure anotherBag's contents are unchanged
              	compareArrays( contents[ 1 ], 
              	               anotherBag.toArray(), 
              	               IS_UNORDERED 
              	               );
              	
          		currentTestPassed =		true ;
          		} ) ;
		
		}	// end testUnion()

	
	// --------------------------------------------------
	//
	// The following utilities are used by the test methods
	//
	// --------------------------------------------------
	
	
	/**
	 * Utility to copy a bag's contents into an array
	 * 
	 * @param bagToCopy
	 *        the bag to copy
	 * @return array of the contents of bagToCopy or null if bagToCopy is null
	 */
	Object[] copyBagIntoArray( BagInterface<Object> bagToCopy )
    	{
    	Object[] bagContents =		null ;
    	
    	if ( bagToCopy != null )
    		{
    		bagContents =			new Object[ bagToCopy.getCurrentSize() ] ;
    		
//    		// retrieve the bag's backing store
//    		Field firstNodeField =			null ;
//    		Object firstNodeReflection =	null ;
//    		
//    		Class<?> nodeClass =			null ;
//    		
//    		Field numberOfEntriesField =	null ;
//    		int numberOfEntriesReflection =	0 ;
//    		
//    		Object currentNode =			null ;
//    		
//    		if ( bagToCopy instanceof LinkedBag )
//				{
//				// find the Node inner class
//				for ( Class< ? > aClass : LinkedBag.class.getDeclaredClasses() )
//					{
//					if ( aClass.getSimpleName().equals( "Node" ) )
//						{
//						nodeClass = aClass ;
//
//						break ;		// we have what we want
//						}
//					}
//
//				// retrieve the LinkedBag instance variables
//    			try
//					{
//					firstNodeField =	LinkedBag.class.getDeclaredField( "firstNode" ) ;
//					}
//				catch ( NoSuchFieldException |
//				        SecurityException e )
//					{
//					e.printStackTrace();
//					}
//    			}
//    		else	// don't handle alternate implementations for now
//    			{
//    			
//    			}
    		
    		// collect the contents of the bag
    		for ( int i = 0; i < bagContents.length; i++ )
    			{
    			bagContents[ i ] =	bagToCopy.remove() ;
    			}
    		
    		// restore the contents of the bag
    		for ( int i = 0; i < bagContents.length; i++ )
    			{
    			bagToCopy.add( bagContents[ i ] ) ;
    			}
    		}
    	
    	return bagContents ;
    	
    	}	// end copyBagIntoArray()
	
	
	/**
	 * Utility to populate a bag from the contents of an array
	 * 
	 * @param bagToFill
	 *        the bag to populate
	 * @param entries
	 *        the entries to add to the bagToFill
	 */
	private void populateBag( BagInterface<Object> bagToFill,
	                          Object[] entries )
    	{
    	if ( entries != null )
    		{
    		for ( Object anEntry : entries )
    			{
    			bagToFill.add( anEntry ) ;
    			}
    		}
    	
    	}	// end populateBag()

		
	}	// end class LinkedBagDMRTests
