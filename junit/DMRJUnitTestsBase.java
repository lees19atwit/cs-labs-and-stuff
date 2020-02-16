/*
 * Dave Rosenberg
 * Comp 1050 - Computer Science II
 * Comp 2000 - Data Structures
 * Lab: Testing infrastructure
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


package edu.wit.dcsn.dmr.testing.junit;


import static org.junit.jupiter.api.Assertions.assertArrayEquals ;
import static org.junit.jupiter.api.Assertions.assertEquals ;
import static org.junit.jupiter.api.Assertions.fail ;

import org.junit.jupiter.api.AfterAll ;
import org.junit.jupiter.api.AfterEach ;
import org.junit.jupiter.api.BeforeAll ;
import org.junit.jupiter.api.BeforeEach ;
import org.junit.jupiter.api.DisplayName ;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation ;
import org.junit.jupiter.api.TestInfo ;
import org.junit.jupiter.api.TestInstance ;
import org.junit.jupiter.api.TestInstance.Lifecycle ;
import org.junit.jupiter.api.TestMethodOrder ;

import java.io.File ;
import java.io.FileNotFoundException ;
import java.io.IOException ;
import java.io.PrintStream ;
import java.lang.reflect.Field ;
import java.nio.file.Files ;
import java.nio.file.Path ;
import java.security.Permission ;
import java.time.Duration ;
import java.util.ArrayList ;
import java.util.Arrays ;
import java.util.Calendar ;
import java.util.LinkedList ;
import java.util.List ;


/**
 * JUnit tests for the LinkedList class. All public and package visible methods
 * are tested. These tests require the API for the ArrayStack class implement
 * {@code TestableStackInterface<T>}.
 * 
 * @author David M Rosenberg
 * @version 1.0.0 2018-05-25 initial set of tests<br>
 * @version 1.1.0 2018-06-09 revise structure to use TestInfo instead of certain
 *              hard-coded text
 * @version 1.2.0 2018-09-02 add timeouts
 * @version 1.3.0 2019-01-14 more implementation
 * @version 1.3.1 2019-01-17 cosmetic changes
 * @version 2.0.0 2019-05-12
 *              <ul>
 *              <li>restructure tests
 *              <li>disable System.exit() during testing
 *              <li>start making each subtest independent so they'll all run
 *              even if one fails
 *              </ul>
 * @version 2.1.0 2019-05-17
 *              <ul>
 *              <li>rename class
 *              <li>remove unnecessary throws clauses from @BeforeXxx
 *              and @AfterXxx
 *              <li>more fully utilize JUnit 5.4 features
 *              <li>switch tests to data-driven
 *              </ul>
 * @version 3.0.0 2019-06-27
 *              <ul>
 *              <li>complete re-write with reusable testing infrastructure
 *              <li>tests are now data-driven
 *              <li>add summary test results
 *              </ul>
 * @version 3.1.0 2019-06-28 move detailed activity to log file
 * @version 4.0.0 2019-07-04 split general purpose utilities methods into
 *              separate class
 * @version 5.0.0 2019-10-07 revise for Stack ADT
 */
@DisplayName( "DMR JUnit Tests Base" )
@TestInstance( Lifecycle.PER_CLASS )
@TestMethodOrder( OrderAnnotation.class )
public class DMRJUnitTestsBase
	{
	// --------------------------------------------------
	//
	// The following utilities are primarily used by the test methods
	//
	// --------------------------------------------------
	
	
	/*
	 * constants for use with arrayToString()
	 */
	/** default maximum length (in characters) a string version of an array can 
	 * take before the string is truncated in the middle */
	protected final static int DEFAULT_ARRAY_TO_STRING_LENGTH =	200 ;
	/** the default number of elements arrayToString() will include in a 
	 * truncated string */
	protected final static int DEFAULT_ARRAY_TO_STRING_ELEMENTS =	50 ;
	

	/**
	 * Return a limited portion of a string representation of an array
	 * 
	 * @param anArray
	 *        the array to convert to string
	 * @return the result of
	 *         {@code arrayToString( anArray, DEFAULT_ARRAY_TO_STRING_LENGTH )}
	 */
	protected String arrayToString( Object[] anArray )
    	{
    	return arrayToString( anArray, 
    	                      DEFAULT_ARRAY_TO_STRING_LENGTH,
    	                      DEFAULT_ARRAY_TO_STRING_ELEMENTS ) ;
    	}	// end 1-arg arrayToString()
	
	
	/**
	 * Return a limited portion of a string representation of an array
	 * 
	 * @param anArray
	 *        the array to convert to string
	 * @param maximumLength
	 *        the maximum number of characters to return
	 * @param maximumElements
	 *        the maximum number of elements to return
	 * @return if the full string representation has no more than maximumLength
	 *         character, the entire string; otherwise the first maximumElements
	 *         / 2 elements followed by " .. " then the last maximumElements / 2
	 *         elements
	 */
	protected String arrayToString( Object[] anArray,
	                                int maximumLength,
	                                int maximumElements )
    	{
    	String fullString =		Arrays.toString( anArray ) ;
    	if ( fullString.length() <= maximumLength )
    		{
    		return fullString ;
    		}
    	
    	int halfCount =			maximumElements / 2 ;
    	StringBuilder partsString =	new StringBuilder( "[" ) ;
    	
    	for ( int i = 0; i < halfCount; i++ )
    		{
    		partsString.append( anArray[ i ].toString() + ", " ) ;
    		}
    	partsString.append( "..." ) ;
    	for ( int i = anArray.length - halfCount; i < anArray.length; i++ )
    		{
    		partsString.append( ", " + anArray[ i ].toString() ) ;
    		}
    	partsString.append( "]" ) ;
    	
    	return partsString.toString() ;
    	
    	}	// end 3-arg arrayToString()
	
	
	/*
	 * constants for use with compareArrays()
	 */
	/** flag that dataset is ordered and may not be reordered for comparison */
	protected final static boolean IS_ORDERED =	true ;
	/** flag that dataset is unordered and may be reordered for comparison */
	protected final static boolean IS_UNORDERED =	false ;
	
	/**
	 * Determine if two arrays contain the same contents
	 * 
	 * @param expected
	 *        the array of elements as they should appear
	 * @param actual
	 *        the array of elements to be verified against expected
	 * @param ordered
	 *        if true, elements of expected and actual must appear in the same
	 *        order; if false, the contents may appear in any order
	 */
	@SuppressWarnings( "null" )			// compiler bug? - NPE not possible
	protected static void compareArrays( Object[] expected,
	                                     Object[] actual,
	                                     boolean ordered )
    	{
		// if both array references are null, succeed
    	if ( ( expected == null ) && ( actual == null ) )		// both null
    		{
    		return ;
    		}
    	
		// if one array reference is null and the other is empty, fail
    	if ( ( ( expected == null ) && ( actual.length == 0 ) ) ||
    								// expected is null, actual is empty
			 ( ( actual == null ) && ( expected.length == 0 ) ) )
    								// actual is null, expected is empty
    		{
    		fail( "Trying to compare null to empty []" ) ;
    		}
    	
    	// if either reference is null, the other can't be null
    	else if ( ( expected == null ) || ( actual == null ) )
    		{
			fail( "bad test data detected: one dataset is null and the other is neither null nor empty" ) ;
			
			// unreachable
//			throw new IllegalStateException( "bad test data detected: one dataset is null and the other is neither null nor empty" ) ;
    		}

    	// make sure the two arrays are the same length
    	assertEquals( expected.length,
    	              actual.length ) ;
    	
		// make copies of the arrays so we don't affect the contents/order of
		// the originals
    	Object[] workingExpected =		Arrays.copyOf( expected,
    	                          		               expected.length ) ;
    	Object[] workingActual =		Arrays.copyOf( actual,
    	                        		               actual.length ) ;
    	
    	// if the order of the contents of the arrays isn't ordered, sort them
    	if ( ! ordered )
    		{
    		Arrays.sort( workingExpected ) ;
    		Arrays.sort( workingActual ) ;
    		}
    	
    	// compare the contents of the arrays
    	assertArrayEquals( workingExpected, 
    	                   workingActual ) ;
    	
    	}	// end compareArrays()
	
	
	/**
	 * Count the number of occurrences of a value in an array
	 * 
	 * @param values
	 *        the collection of values
	 * @param testValue
	 *        the test value
	 * @return the number of time testValue occurred in values
	 */
	protected static int countOccurrences( Object[] values,
	                             Object testValue )
    	{
    	int occurrences = 0 ;
    	for ( int i = 0; i < values.length; i++ )
    		{
    		if( values[ i ].equals( testValue ) )
    			{
    			occurrences++ ;
    			}
    		}
    	
    	return occurrences ;
    	
    	}	// end countOccurrences()
	
	
	/**
	 * Determine if {@code testValue} occurs at least once in an array
	 * 
	 * @param values
	 *        the collection of values
	 * @param testValue
	 *        the test value
	 * @return true if at least one occurrence; false if no occurrences
	 */
	protected static boolean arrayContains( Object[] values,
	                                        Object testValue )
    	{
    	boolean found =		false ;	// we haven't found it yet
    	
    	for ( int i = 0; i < values.length; i++ )
    		{
    		if ( values[ i ] != null )
        		{
        		found =       values[ i ].equals( testValue ) ;
        		}
    		
    		if ( found )
    			{
    			break ;
    			}
    		}
    	
    	return found ;
    	
    	}	// end arrayContains()
	
	
	/** default display flag to indicate stub behavior */
	protected final static String DEFAULT_STUB_BEHAVIOR_INDICATOR =	" s" ;
	
	
	/**
	 * Handle stub behavior using default indicator
	 * 
	 * @param isStubBehavior
	 *        flag to indicate that the result of testing this dataset matches
	 */
	protected void determineStubBehavior( boolean isStubBehavior )
    	{
		determineStubBehavior( isStubBehavior,
		                       DEFAULT_STUB_BEHAVIOR_INDICATOR ) ;
    	
    	}	// end determineStubBehavior()
	
	
	/**
	 * Handle stub behavior indicator
	 * 
	 * @param isStubBehavior
	 *        flag to indicate that the result of testing this dataset matches
	 * @param stubBehaviorIndicator
	 *        text to flag that the current test data matches the expected
	 *        behavior from stubbed methods
	 */
	protected void determineStubBehavior( boolean isStubBehavior,
	                                      String stubBehaviorIndicator )
    	{
		// if the expected behavior of this test will match the stub behavior,
		// count it
    	if ( isStubBehavior )
    		{
    		stubBehaviorSeenCount++ ;
    		
    		// and set the tag
    		stubBehaviorTag =			stubBehaviorIndicator ;
    		}
    	
    	}	// end determineStubBehavior()
	
	
	/**
	 * Utility to parse a string of arguments into an array of corresponding
	 * entries - for parameterized tests
	 * 
	 * @param arguments
	 *        the string to parse
	 * @return an array containing Longs or Strings or a zero-length array of
	 *         Objects representing the entries in arguments or null if
	 *         arguments is null
	 */
	protected Object[] parseArguments( String arguments )
    	{
    	// convert the arguments string to an array of its component entries
       	List<Object> parsedArguments =		null ;
       	
       	// parse the parameter if it's not null
       	if ( arguments != null )
       		{
       		parsedArguments =				new ArrayList<>() ;
       		
       		String[] collectionContentsStrings ;
       		
       		if ( arguments.trim().length() == 0 )
       			{
       			collectionContentsStrings =	new String[ 0 ] ;
       			}
       		else
       			{
	       		collectionContentsStrings =	arguments.split( "[|]" ) ;
       			}
		
   			// trim the strings
			for ( int i = 0; i < collectionContentsStrings.length; i++ )
				{
				collectionContentsStrings[ i ] =
										collectionContentsStrings[ i ].trim() ;
				}

			
   			// convert the elements to an appropriate type
   			for ( int i = 0; i < collectionContentsStrings.length; i++ )
   				{
   				// check for a 0-length string
   				if ( collectionContentsStrings[ i ].length() == 0 )
   					{
   					parsedArguments.add( "" ) ;
   					}
   				// check for an explicit null (case sensitive)
   				else if ( collectionContentsStrings[ i ].equals( "null" ) )
   					{
   					parsedArguments.add( null ) ;
   					}
       			// try to convert to integers (long actually)
   				else if ( Character.isDigit( collectionContentsStrings[ i ].charAt( 0 ) ) ||
   						  ( ( collectionContentsStrings[ i ].length() >= 2 ) &&
   							( collectionContentsStrings[ i ].charAt( 0 ) == '-' ) &&
   							Character.isDigit( collectionContentsStrings[ i ].charAt( 1 ) ) )
   							) 
       				{
               		parsedArguments.add( Long.parseLong( collectionContentsStrings[ i ] ) ) ;
       				}
   				// see if we want a range of numbers
	   			else if ( collectionContentsStrings[ i ].charAt( 0 ) == '[' )
       				{	// add elements lowerBound..upperBound
					String[] parts =	collectionContentsStrings[ i ]
											.substring( 1,
	                                                    collectionContentsStrings[ i ]
                                            				.length() - 1 )
	                                        .split( "[:]" ) ;

           			long lowerBound =	Integer.parseInt( parts[ 0 ] ) ;
           			long upperBound =	Integer.parseInt( parts[ 1 ] ) ;
           			int step =			lowerBound <= upperBound ? 1 : -1 ;

					for ( long rangeI = lowerBound ;
					      step == 1
			                ? rangeI <= upperBound
			                : rangeI >= upperBound ;
					      rangeI += step )
           				{
           				parsedArguments.add( rangeI ) ;
           				}
       				}
   				// everything else we leave as a string
	   			else
	   				{
	   				parsedArguments.add( collectionContentsStrings[ i ] ) ;
	   				}
       			}	// end for parse each element
       		
       		}	// end arguments isn't null
       		
		// assertion: parsedArguments is either null or points to an array of
		// Longs, Strings, nulls - may be a zero-length array
       	
    	return parsedArguments == null
				? null
				: parsedArguments.toArray() ;
    	
    	}	// end parseArguments
	
	
	/**
	 * Utility to pre-process test parameters
	 * 
	 * @param testInfo
	 *        info about the test
	 * @param isLastTest
	 *        flag to indicate that this is the last dataset for this test
	 * @param isStubBehavior
	 *        flag to indicate that the result of testing this dataset matches
	 *        the stubbed behavior
	 * @param collectionContentsArguments
	 *        contents of one or more collections to populate
	 * @return the parsed stackContentsArguments in order of appearance in the
	 *         argument list
	 */
	protected Object[][] startTest( TestInfo testInfo,
	                                boolean isLastTest,
	                                boolean isStubBehavior,
	                                String... collectionContentsArguments )
    	{
    	lastTestInGroupIsRunning =	isLastTest ;
    	
		// if the expected behavior of this test will match the stub behavior,
		// count it
    	determineStubBehavior( isStubBehavior ) ;
    	
    	
       	// count this test
       	currentTestsAttempted++ ;

       	Object[][] populatedCollections =
       						new Object[ collectionContentsArguments.length ][] ;
       	
    	// convert the arguments representing the stack contents to an array
       	for ( int i = 0; i < populatedCollections.length; i++ )
           	{
           	populatedCollections[ i ] =
           					parseArguments( collectionContentsArguments[ i ] ) ;
           	}
       	

       	// display message describing this test
		writeLog( "[%,d, %,d%s] Testing: %s%n\twith %s%n",
		          currentTestGroup,
		          currentTestsAttempted,
		          stubBehaviorTag,
		          currentTestGroupName,
		          arrayToString( populatedCollections[ 0 ] ) ) ;
       	
       	for( int i = 1; i < populatedCollections.length; i++ )
       		{
			writeLog( "\t\tand %s%n",
			          arrayToString( populatedCollections[ i ] ) ) ;
       		}
       	
		return populatedCollections ;
    	
    	}	// end startTest() with 0 or more collections contents
	
	
	/**
	 * Utility method to perform steps to conclude an unsuccessful test
	 */
	protected void testFailed()
    	{
    	// display message indicating unsuccessful completion
		writeLog( "[%,d, %,d%s] Test failed%n%n",
		          currentTestGroup,
		          currentTestsAttempted,
		          stubBehaviorTag ) ;
		
    	}	// end testFailed()
	
	
	/**
	 * Utility method to perform steps to conclude a successful test
	 */
	protected void testPassed()
    	{
    	// count this test success
    	currentTestsSucceeded++ ;
    	
    	// display message indicating successful completion
		writeLog( "[%,d, %,d%s] Test passed%n%n",
		          currentTestGroup,
		          currentTestsAttempted,
		          stubBehaviorTag ) ;
		
    	}	// end testPassed()

	
	// --------------------------------------------------
	//
	// The next section contains testing infrastructure declarations 
	// and code
	//
	// --------------------------------------------------
	
	/*
	 * State instance variables and support methods follow:
	 */

	/*
	 * test constants, counters and labels
	 */
    /** default timeout: 2 second */
	protected static final long TEST_TIME_LIMIT_DEFAULT_SECONDS =	2 ;
	/** default timeout: 2 seconds */
	protected static final Duration TEST_TIME_LIMIT_DEFAULT =
						Duration.ofSeconds( TEST_TIME_LIMIT_DEFAULT_SECONDS ) ;
	
	/** effectively disable timeout for debugging */
	protected static final long TEST_TIME_LIMIT_DEBUG_SECONDS =
												Integer.MAX_VALUE ;
	/** effectively disable timeout for debugging */
	protected static final Duration TEST_TIME_LIMIT_DEBUG =
						Duration.ofSeconds( TEST_TIME_LIMIT_DEBUG_SECONDS ) ;
	
	/** current timeout duration */
	protected static Duration testTimeLimit =		TEST_TIME_LIMIT_DEFAULT ;
	
	// overall totals
	
	/** total number of tests attempted */
	protected int totalTestsAttempted ;
	/** total number of tests that completed successfully */
	protected int totalTestsSucceeded ;
	
	/** accumulates test results for summary display once all tests finish */
	protected List<String> summaryTestResults ;
	
	// current test group (method)
	
	/** current test group (method) counter */
	protected int currentTestGroup ;
	/** current test group (method) name */
	protected String currentTestGroupName ;

	/** flag that the last test in a test group is executing */
	protected boolean lastTestInGroupIsRunning ;
	
	/** number of tests attempted in current test group (method) */
	protected int currentTestsAttempted ;
	/** number of tests that completed successfully in current test group 
	 	(method) */
	protected int currentTestsSucceeded ;
	
	/** flag that the currently executing test completed successfully */
	protected boolean currentTestPassed ;
	
	/** counter for the number of tests within a test group that match the
	 	expected stubbed method behavior */
	protected int stubBehaviorSeenCount ;
	/** text appended to individual test id for tests that match the expected 
	 	stubbed method behavior */
	protected String stubBehaviorTag ;
	
	
	/** for test 'full' logging */
	protected static PrintStream detailedLogStream ;

	
	/** saves the active security manager when testing starts */ 
	protected SecurityManager savedSecurityManager ;
	
	
	/**
	 * Disable debugging mode by enabling test timeouts
	 * 
	 * @return true if debugging was previously enabled, false otherwise
	 */
	public static boolean disableDebugging()
		{
		return setDebug( false ) ;
		
		}	// end disableDebugging()


	/**
	 * Enable debugging mode by suppressing test timeouts
	 * 
	 * @return true if debugging was previously enabled, false otherwise
	 */
	public static boolean enableDebugging()
		{
		return setDebug( true ) ;
		
		}	// end enableDebugging()


	/**
	 * Enable or disable debugging by adjusting test timeouts.
	 * 
	 * @param wantToDebug
	 *        true disables test timeouts; false (default) enables timeouts.
	 * @return true if debugging was previously enabled, false otherwise
	 */
	protected static boolean setDebug( boolean wantToDebug )
		{
		boolean wasDebugging =	testTimeLimit.equals( TEST_TIME_LIMIT_DEBUG ) ;

		testTimeLimit =				wantToDebug
    		                            ? TEST_TIME_LIMIT_DEBUG
    		                            : TEST_TIME_LIMIT_DEFAULT ;

		if ( wantToDebug )
			{
			writeSyserr( "\n\n----------\n\n%s: %s\n\n----------\n\n",
			             "WARNING",
			             "Debugging mode enabled\n\tMust submit with debugging mode disabled!" ) ;
			}

		return wasDebugging ;

		}	// end setDebug()
	
	
	/**
	 * Display a log message to the console and detailed log file
	 * 
	 * @param format
	 *        to {@code printf()}
	 * @param parameters
	 *        to {@code printf()}
	 */
	protected static void writeConsole( String format, Object... parameters )
    	{
    	System.out.printf( format, parameters ) ;
    	
    	if ( detailedLogStream != System.out )
        	{
        	writeLog( format, parameters ) ;
        	}
    	
    	}	// end writeConsole()
	
	
	/**
	 * Display a log message to the detailed log file
	 * 
	 * @param format
	 *        to {@code printf()}
	 * @param parameters
	 *        to {@code printf()}
	 */
	protected static void writeLog( String format, Object... parameters )
    	{
    	detailedLogStream.printf( format, parameters ) ;
    	
    	}	// end writeLog()
	
	
	/**
	 * Print a formatted message to System.err in its proper sequence wrt
	 * System.out - limited effectiveness when running multiple threads
	 * 
	 * @param format
	 *        to {@code printf()}
	 * @param parameters
	 *        to {@code printf()}
	 */
	protected static void writeSyserr( String format, Object... parameters )
    	{
    	System.out.flush() ;
    	System.err.printf( format, parameters ) ;
    	System.err.flush() ;
    	
    	}	// end writeSyserr()

	
	// --------------------------------------------------
	//
	// This section contains utilities to use reflect to interrogate 
	// the class under test
	//
	// --------------------------------------------------
	

	/**
	 * Determine if a field (instance or class variable) is of an annotation
	 * type
	 * 
	 * @param field
	 *        the field to test
	 * @return true if {@code field} is of an annotation type; false otherwise
	 */
	protected static boolean isAnnotation( Field field )
		{
		return typeOf( field ).isAnnotation() ;

		}	// end isAnnotation()

	
	/**
	 * Determine if a class is an array
	 * 
	 * @param aClass
	 *        the class to test
	 * @return true if {@code aClass} is an array class; false otherwise
	 */
	protected static boolean isArray( Class<?> aClass )
		{
		return aClass.isArray() ;

		}	// end isArray()


	/**
	 * Determine if a field (instance or class variable) is of an array type
	 * 
	 * @param field
	 *        the field to test
	 * @return true if {@code field} is of an array class type; false otherwise
	 */
	protected static boolean isArray( Field field )
		{
		return isArray( field.getType() ) ;

		}	// end isArray()


	/**
	 * Determine if a field (instance or class variable) is of a class reference
	 * type
	 * 
	 * @param field
	 *        the field to test
	 * @return true if {@code field} is of a class reference type; false
	 *         otherwise
	 */
	protected static boolean isClassReference( Field field )
		{
		return typeOf( field ).toString().startsWith( "class" ) ;

		}	// end isClassReference()


	/**
	 * Determine if a field (instance or class variable) is of a class reference
	 * type which is a collection ({@code implements java.util.Collection} or
	 * is a subclass of a class which does)
	 * 
	 * @param field
	 *        the field to test
	 * @return true if {@code field} is of a class reference type; false
	 *         otherwise
	 */
	protected static boolean isCollection( Field field )
		{
		try
			{
			return Class.forName( "java.util.Collection" )
							.isInstance( field.get( field.getDeclaringClass()
							                        	.newInstance() ) ) ;
			}
		catch ( ClassNotFoundException | 
				InstantiationException | 
				IllegalAccessException e )
			{
			e.printStackTrace();
			
			return false ;
			}

		}	// end isCollection()


	/**
	 * Determine if a field (instance or class variable) is of an enumeration
	 * type
	 * 
	 * @param field
	 *        the field to test
	 * @return true if {@code field} is of an enumeration type; false otherwise
	 */
	protected static boolean isEnumeration( Field field )
		{
		return typeOf( field ).isEnum() ;

		}	// end isEnumeration()


	/**
	 * Determine if a field (instance or class variable) is of an interface
	 * reference type
	 * 
	 * @param field
	 *        the field to test
	 * @return true if {@code field} is of an interface reference type; false
	 *         otherwise
	 */
	protected static boolean isInterfaceReference( Field field )
		{
		return typeOf( field ).isInterface() ;

		}	// end isInterfaceReference()


	/**
	 * Determine if a field (instance or class variable) is of a primitive type
	 * 
	 * @param field
	 *        the field to test
	 * @return true if {@code field} is of a primitive type; false otherwise
	 */
	protected static boolean isPrimitive( Field field )
		{
		return typeOf( field ).isPrimitive() ;

		}	// end isPrimitive()


	/**
	 * Determine if a field is a class variable
	 * 
	 * @param field
	 *        the field to test
	 * @return true if {@code field} is a class variable; false otherwise
	 */
	protected static boolean isStatic( Field field )
		{
		String[] parts =			field.toGenericString().split( " " ) ;
		boolean isStatic =			false ;
		
		switch ( parts[ 0 ] )
    		{
    		case "private":
    		case "protected":
    		case "public":
    			isStatic =			parts[ 1 ].equals( "static" ) ;
    			break ;
			default:
				isStatic =			parts[ 0 ].equals( "static" ) ;
    		}

		return isStatic ;

		}	// end isStatic()
	
	
    /**
     * Determine if a field is of a primitive type
     * 
     * @param field
     *            the field to test
     * @return true if {@code field} is of a primitive type; false otherwise
     */
	protected static Class<?> primitiveTypeOf( Field field )
    	{
    	Class<?> type =			typeOf( field ) ;
    	
    	while ( type.isArray() )
    		{
    		type =				type.getComponentType() ;
    		}
    	
    	if ( ! type.isPrimitive() )
    		{
    		type =				null ;
    		}
    	
    	return type ;
    	
    	}	// end primitiveTypeOf()
	
	
    /**
     * Determine the class of a field
     * 
     * @param field
     *            the field to test
     * @return the field's class
     */
	protected static Class<?> typeOf( Field field )
    	{
		Class<?> type ;
		
		if ( isArray( field ) )
    		{
    		type =				field.getType().getComponentType() ;
    		}
		else
			{
			type =				field.getType() ;
			}

		return type ;
		
    	}	// end typeOf()


    /**
     * Determine the visibility of a field
     * 
     * @param field
     *            the field to test
     * @return the field's visibility
     */
	protected static String visibilityOf( Field field )
		{
		String[] parts =			field.toGenericString().split( " " ) ;
		String visibility =			null ;
		
		switch ( parts[ 0 ] )
    		{
    		case "private":
    		case "protected":
    		case "public":
    			visibility =		parts[ 0 ] ;
    			break ;
			default:
				visibility =		"package" ;
    		}

		return visibility ;

		}	// end visibilityOf()


	
	// --------------------------------------------------
	//
	// The rest of this file contains JUnit setup/tear down declarations 
	// and code
	//
	// --------------------------------------------------
	

	/**
	 * @param testInfo
	 *        the current test environment
	 */
	@BeforeAll
	protected void setUpBeforeClass( TestInfo testInfo )
		{
//		enableDebugging() ;	// DEBUG
		
		// by default, send detailed log entries to the standard output
		detailedLogStream =				System.out ;

		// create the detailed log - name is TestClass-yyyy-mm-dd-hhmmss.log
		Calendar now =					Calendar.getInstance() ;
        String timestamp =				String.format( "%TF-%<TH%<TM%<TS",
                          				               now
                          				               ) ;

        Path testLogsPath =		new File( "./test-logs" ).toPath().toAbsolutePath().normalize() ;
		
        String outputFilename =	String.format( "%s%c%s-%s.log",
                               	               testLogsPath,
                               	               File.separatorChar,
                               	               this.getClass().getSimpleName(),
                               	               timestamp ) ;
		
		try
			{
			testLogsPath =
				Files.createDirectories( testLogsPath ) ;
			
			detailedLogStream =			new PrintStream( outputFilename ) ;
			writeConsole( "Detailed log in: %s%n%n",
			              outputFilename ) ;
			}
		catch ( FileNotFoundException e )
			{
			writeSyserr( "Unable to create log file: %s%n\t%s%n\tusing System.out%n",
			             e.getMessage(),
			             outputFilename );
			}
		catch ( IOException e )
			{
			writeSyserr( "Unable to create log folder: %s%n\t%s%n\tusing System.out%n",
			             e.getMessage(),
			             testLogsPath );
			}

		
		// display start of testing (class)
		writeConsole( "Starting tests of class %s%n",
		              testInfo.getDisplayName() ) ;
		
		// initialize testing-wide counters
		totalTestsAttempted =				0 ;
		totalTestsSucceeded =				0 ;
		
		summaryTestResults =				new LinkedList<>() ;
		
		currentTestGroup =					0 ;
		currentTestGroupName =				"" ;

		// assume single tests (not repeating nor parameterized)
		lastTestInGroupIsRunning =			true ;
		
		// there is no current test - indicate didn't pass
		currentTestPassed =					false ;

		// there are no stub values seen yet
		stubBehaviorSeenCount =				0 ;
		
		/*
		 * prevent System.exit() from terminating tests
		 */
		// save the current security manager
		savedSecurityManager =				System.getSecurityManager() ;
		
		// enable ours
        System.setSecurityManager( new NoExitSecurityManager() ) ;
        
		}	// end setUpBeforeClass()


	/**
	 * @param testInfo
	 *        the current test environment
	 */
	@AfterAll
	protected void tearDownAfterClass( TestInfo testInfo )
		{
		// display summary results
		if ( totalTestsAttempted > 0 )
			{
			writeConsole( "%nSummary Test Results%n%n" ) ;
			
			for ( String testResult : summaryTestResults )
				{
				writeConsole( "%s%n",
				              testResult ) ;
				}
			
			writeConsole( "%nSuccessfully completed %,d of %,d tests (%d%%) attempted for class %s%n",
			              totalTestsSucceeded,
			              totalTestsAttempted,
			              ( totalTestsSucceeded * 100 ) / totalTestsAttempted,
			              testInfo.getDisplayName() ) ;
			}
		else
			{
			writeConsole( "%nNo tests attempted for class %s%n",
			              testInfo.getDisplayName() ) ;
			}
		
		
		// close the detailed log
		detailedLogStream.close() ;
		

		/*
		 * re-enable System.exit()
		 */
		// restore the saved security manager
		System.setSecurityManager( savedSecurityManager ) ;
        
		}	// end tearDownAfterClass()


	/**
	 * @param testInfo
	 *        the current test environment
	 */
	@BeforeEach
	protected void setUpBeforeEachTest( TestInfo testInfo )
		{
		String baseName =				testInfo.getDisplayName() ;
		int colonColonIndex =			baseName.indexOf( "::" ) ;
		if ( colonColonIndex != -1 )
			{
			baseName =		baseName.substring( 0, colonColonIndex ).trim() ;
			}
		
		stubBehaviorTag =				"" ;	// assume not a stub behavior
		
		if ( ! currentTestGroupName.equals( baseName ) )
			{
			// count this test group
    		currentTestGroup++ ;
    		currentTestGroupName =		baseName ;
    		
    		// reset current test counters
    		currentTestsAttempted =		0 ;
    		currentTestsSucceeded =		0 ;

    		// assume single test (not repeating nor parameterized)
    		lastTestInGroupIsRunning =	true ;

    		// there are no stub values seen yet
    		stubBehaviorSeenCount =				0 ;
    		
    		// display start of testing (method or category/group of operations)
			writeConsole( "%n[%,d] Starting tests of %s%n%n",
			              currentTestGroup,
			              currentTestGroupName ) ;
			}
		
		// reset test passed flag
		currentTestPassed =				false ;
		
		}	// end setUpBeforeEachTest()


	/**
	 * @param testInfo the current test environment
	 */
	@AfterEach
	protected void tearDownAfterEachTest( TestInfo testInfo )
		{
		if ( currentTestPassed )
			{
			testPassed() ;
			}
		else
			{
			testFailed() ;
			}
		
		if ( lastTestInGroupIsRunning )
			{
    		// display stats for this test group

    		// filter for stubbed return values
    		if ( currentTestsSucceeded == stubBehaviorSeenCount )
    			{
    			// only saw correct responses which matched the stub values
    			// consider this a total failure rather than a (misleading)
    			// correct percentage
				writeConsole( "[%,d] The only tests which passed matched stub behaviors - ignoring them (%,d)%n",
				              currentTestGroup,
				              stubBehaviorSeenCount ) ;
    			
    			currentTestsSucceeded =		0 ;	// clear the success count
    			}
		
    		String testSummary =
                   String.format( "[%,d] Successfully completed %,d of %,d tests (%d%%) of %s",
                                  currentTestGroup,
                                  currentTestsSucceeded,
                                  currentTestsAttempted,
                                  ( currentTestsAttempted == 0
                                       ? 0
                                       : ( currentTestsSucceeded *
                                           100 ) /
                                         currentTestsAttempted ),
                                  currentTestGroupName ) ;
    		summaryTestResults.add( testSummary ) ;
    		writeConsole( "%s%n%n----------%n", 
    		              testSummary ) ;
    		
    		// accumulate this test group's results
    		totalTestsAttempted +=		currentTestsAttempted ;
    		totalTestsSucceeded +=		currentTestsSucceeded ;
    		
    		// reset current test counters
    		currentTestsAttempted =		0 ;
    		currentTestsSucceeded =		0 ;
			}
		
		}	// end tearDownAfterEachTest()
	
	
	// ----------

	/*
	 * private utility classes
	 */
	
	/**
	 * Support System.exit() interception
	 */
	protected static class ExitException extends SecurityException
		{

		/** for serialization */
		static final long serialVersionUID = 1L ;


		/**
		 * default constructor with no status
		 */
		public ExitException()
			{
			super() ;

			}	// end no-arg constructor


		/**
		 * constructor with status
		 * 
		 * @param status
		 *        the status given to {@code System.exit()}
		 */
		ExitException( int status )
			{
			super( Integer.toString( status ) ) ;

			}	// end 1-arg constructor
		
		}	// end inner class ExitException


	/**
	 * Support System.exit() interception
	 */
	protected static class NoExitSecurityManager extends SecurityManager
		{
        /**
         * 
         */
        public NoExitSecurityManager()
            {
            super() ;
            
            }	// end no-arg constructor

        
		@Override
		public void checkPermission( Permission perm )
			{}


		@Override
		public void checkPermission( Permission perm,
		                             Object context 
		                             )
			{}


		@Override
		public void checkExit( int status )
			{
			super.checkExit( status ) ;
			
			writeSyserr( "%nSystem.exit( %,d ) intercepted%n%n",
			             status
			             ) ;

			throw new ExitException( status ) ;
			
			}	// end checkExit()
		
		}	// end inner class NoExitSecurityManager
	
	}	// end class DMRJUnitTestsBase
