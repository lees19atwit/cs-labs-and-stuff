/*
 * Dave Rosenberg
 * Comp 2000 - Data Structures
 * Lab: Dictionary ADT
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


package edu.wit.dcsn.comp2000.dictionaryadt ;

import java.io.FileNotFoundException ;
import java.util.Iterator ;


/**
 * Test of a Dictionary (key/value pair) implementation using an array of
 * LinkedLists as the backing store.
 * 
 * @author David M Rosenberg
 * @version 1.0.0 Extracted from Dictionary.java
 */
public class HashTableDictionaryDMRTests
	{	

	/**
	 * Unit test driver
	 * 
	 * @param args
	 *        -unused-
	 * @throws FileNotFoundException
	 *         if the metrics log can't be created
	 */
	public static void main( String[] args ) throws FileNotFoundException
		{
		System.out.println( "Starting Dictionary tests\n" ) ;
		
		// start up the metrics logger
		MetricsLogger.create() ;
		MetricsLogger.write( "Starting Dictionary tests" ) ;
		
		/*
		 * test 1
		 */
		System.out.println( "Starting test 1\n" ) ;
		
		MetricsLogger.write( "\n\nTest 1:\n" );
		MetricsLogger.writeColumnHeaders() ;
		
		// create an instance of the Dictionary
		HashTableDictionary< String, Integer > testDictionary =
								new HashTableDictionary<>( 3 ) ;
		
		// load it
		testLoadDictionary( testDictionary ) ;

		// dump it - exercises iterators
		testDumpDictionary( testDictionary ) ;
   	
		// make believe we're doing stuff...
		System.out.println( testDictionary.dumpStructure() + "\n" ) ;
		
		// let the dictionary know we're done putting key/value pairs into it
		testDictionary.doneLoading() ;
		
		System.out.println( "Finished test 1\n" ) ;
		
		
		/*
		 * test 2
		 */
		System.out.println( "Starting test 2\n" ) ;
		
		MetricsLogger.write( "\n\nTest 2:\n" );
		MetricsLogger.writeColumnHeaders() ;
		
		// re-instantiate the Dictionary with other settings
		testDictionary =		new HashTableDictionary<>( 123, 2.0F, true ) ;

		// load it
		testLoadDictionary( testDictionary ) ;

		// dump it - exercises iterators
		testDumpDictionary( testDictionary ) ;
  	
		// make believe we're doing stuff...
		System.out.println( testDictionary.dumpStructure() + "\n" ) ;
		
		// let the dictionary know we're done putting key/value pairs into it
		testDictionary.doneLoading() ;
		
		System.out.println( "Finished test 2\n" ) ;
		
		
		/*
		 * test 3
		 */
		System.out.println( "Starting test 3\n" ) ;
		
		MetricsLogger.write( "\n\nTest 3:\n" );
		MetricsLogger.writeColumnHeaders() ;
		
		// re-instantiate the Dictionary with other settings
		testDictionary =		new HashTableDictionary<>( 8, 4.0F, false ) ;

		// load it
		testLoadDictionary( testDictionary ) ;

		// dump it - exercises iterators
		testDumpDictionary( testDictionary ) ;
    	
		// make believe we're doing stuff...
		System.out.println( testDictionary.dumpStructure() + "\n" ) ;
		
		// let the dictionary know we're done putting key/value pairs into it
		testDictionary.doneLoading() ;
		
		System.out.println( "Finished test 3\n" ) ;
		
		
		/*
		 * test 4
		 */
		System.out.println( "Starting test 4\n" ) ;
		
		MetricsLogger.write( "\n\nTest 4:\n" );
		MetricsLogger.writeColumnHeaders() ;
		
		// clear the dictionary's contents
		System.out.println( "clear()ing the dictionary" ) ;
		testDictionary.clear() ;
		
		// print it
		testDumpStructure( testDictionary ) ;
		
		// and dump it
		testDumpDictionary( testDictionary ) ;

		// reset the configuration parameters
		System.out.println( "Resetting load factor threshold and force to prime table size" ) ;
		testDictionary.setLoadFactorThreshold( 0.01F ) ;
		testDictionary.setPrimeTableSize( true ) ;

		// load it
		testLoadDictionary( testDictionary ) ;

		// dump it - exercises iterators
		testDumpDictionary( testDictionary ) ;
    	
		// make believe we're doing stuff...
		System.out.println( testDictionary.dumpStructure() + "\n" ) ;
		
		// let the dictionary know we're done putting key/value pairs into it
		testDictionary.doneLoading() ;
		
		System.out.println( "Finished test 4\n" ) ;
		
		
		/*
		 * test 5
		 */
		System.out.println( "Starting test 5\n" ) ;
		
		MetricsLogger.write( "\n\nTest 5:\n" );
		MetricsLogger.writeColumnHeaders() ;
		
		// re-instantiate the Dictionary with other settings
		testDictionary =		new HashTableDictionary<>( 8, 0.01F, true ) ;
    	
		// see what it looks like 'under the hood' initially
		System.out.println( testDictionary.dumpStructure() + "\n" ) ;

		// load it
		testLoadDictionary( testDictionary ) ;

		// dump it - exercises iterators
		testDumpDictionary( testDictionary ) ;
    	
		// make believe we're doing stuff...
		System.out.println( testDictionary.dumpStructure() + "\n" ) ;
		
		// let the dictionary know we're done putting key/value pairs into it
		testDictionary.doneLoading() ;
		
		System.out.println( "Finished test 5\n" ) ;
		
		
		/*
		 * test 6
		 */
		System.out.println( "Starting test 6\n" ) ;
		
		MetricsLogger.write( "\n\nTest 6:\n" );
		MetricsLogger.writeColumnHeaders() ;
		
		// re-instantiate the Dictionary with other settings
		testDictionary =		new HashTableDictionary<>( 13, 10.0F, true ) ;
    	
		// see what it looks like 'under the hood' initially
		System.out.println( testDictionary.dumpStructure() + "\n" ) ;

		// load it
		testLoadDictionary( testDictionary ) ;

		// dump it - exercises iterators
		testDumpDictionary( testDictionary ) ;
    	
		// make believe we're doing stuff...
		System.out.println( testDictionary.dumpStructure() + "\n" ) ;
		
		// let the dictionary know we're done putting key/value pairs into it
		testDictionary.doneLoading() ;
		
		System.out.println( "Finished test 6\n" ) ;
		
		
		/*
		 * finished testing - wrap up/clean up
		 */
		
		// properly close the metrics logger
		MetricsLogger.write( "\nEnding Dictionary tests" );
		MetricsLogger.close() ;
		
		System.out.println( "\nFinished Dictionary tests" ) ;
		
		}	// end main()
	
	
	/**
	 * Unit test utility:
	 * <p>
	 * Iterate over both the keys and values ensuring they return the same
	 * values.
	 * 
	 * @param testDictionary
	 *        the dictionary to test
	 */
	private static void testDumpDictionary( DictionaryInterface<String,
	                                                            Integer> testDictionary )
		{
    	System.out.println( "\nDumping dictionary:\n" ) ;
    	
    	Iterator<String> keyIterator =		testDictionary.getKeyIterator() ;
    	Iterator<Integer> valueIterator =	testDictionary.getValueIterator() ;
    	
    	while ( keyIterator.hasNext() )
    		{
    		if( valueIterator.hasNext() )	// must always be true
    			{
    			String nextKey =			keyIterator.next() ;
    			Integer valueForKey =		testDictionary.getValue( nextKey ) ;
    			Integer nextValue =			valueIterator.next() ;
    			
    			if( valueForKey.equals( nextValue ) )	// must always be true
    				{
    				System.out.println( nextKey + " : " + valueForKey ) ;
    				}
    			else									// must never happen
    				{
    				System.out.println( "!!! " + nextKey + " : " + valueForKey + " : " + 
    									nextValue ) ;
    				}
    			}
			}
    	
		System.out.println( "\nFinished dumping dictionary\n" ) ;
        	
    	}	// end testDumpDictionary()
	
	
	/**
	 * Unit test utility:
	 * <p>
	 * Load a Dictionary with test data. Also exercises both iterators and
	 * {@code remove()} functionality.
	 * 
	 * @param testDictionary
	 *        the dictionary to test
	 */
	private static void testLoadDictionary( HashTableDictionary<String,
	                                                            Integer> testDictionary )
    	{
    	System.out.println( "\nLoading dictionary:\n" ) ;
    	
		int intValue =					1 ;
		
		System.out.println( "+5 entries" ) ;
		
    	testDictionary.add( "Apple", intValue++ ) ;
    	testDictionary.add( "apple", intValue++ ) ;
    	testDictionary.add( "boy", intValue++ ) ;
    	testDictionary.add( "circle", intValue++ ) ;
    	testDictionary.add( "circle", intValue++ ) ;
    	
    	// print the dictionary so far
    	testDumpStructure( testDictionary );
    	
    	// make the load factor threshold smaller - may force the table to resize immediately
    	System.out.println( "changing (halving) load factor threshold" ) ;
    	testDictionary.setLoadFactorThreshold( testDictionary.getLoadFactorThreshold() / 2.0F ) ;
    	
    	// print the dictionary again
    	testDumpStructure( testDictionary );

    	System.out.println( "+14 entries" ) ;
    	
    	testDictionary.add( "0123456789", intValue++ ) ;
    	testDictionary.add( "Dangerfield", intValue++ ) ;
    	testDictionary.add( "excellence", intValue++ ) ;
    	testDictionary.add( "Forget it!", intValue++ ) ;
    	testDictionary.add( "Googleplex", intValue++ ) ;
    	testDictionary.add( "helicopter", intValue++ ) ;
    	testDictionary.add( "Iterator", intValue++ ) ;
    	testDictionary.add( "Jeopardy!", intValue++ ) ;
    	testDictionary.add( "Kleenex", intValue++ ) ;
    	testDictionary.add( "Leopard", intValue++ ) ;
    	testDictionary.add( "misanthrope", intValue++ ) ;
    	testDictionary.add( "Not in my backyard", intValue++ ) ;
    	testDictionary.add( "orange is the new orange", intValue++ ) ;
    	testDictionary.add( "pumpkins", intValue++ ) ;
    	
    	// print the dictionary so far
    	testDumpStructure( testDictionary );
    	
    	// remove something in the dictionary
//    	System.out.println( "remove()ing \"Iterator\" from bucket " + 
//    						testDictionary.hashIndex( "Iterator" ) +
//    						" returned " + testDictionary.remove( "Iterator" ) ) ;
    	System.out.println( "remove()ing \"Iterator\" returned " + 
    						testDictionary.remove( "Iterator" ) ) ;
    	
    	// print the dictionary again
    	testDumpStructure( testDictionary  );
    	
    	// flip the state of the force prime table size switch
    	System.out.println( "reversing force prime table size" ) ;
    	testDictionary.setPrimeTableSize( !testDictionary.getPrimeTableSize() ) ;
    	
    	// print the dictionary yet again
    	testDumpStructure( testDictionary  );
    	
    	System.out.println( "+11 entries" ) ;
    	
    	testDictionary.add( "QED", intValue++ ) ;
    	testDictionary.add( "right turn", intValue++ ) ;
    	testDictionary.add( "Googleplex", intValue++ ) ;
    	testDictionary.add( "Sun", intValue++ ) ;
    	testDictionary.add( "T", intValue++ ) ;
    	testDictionary.add( "upside-down", intValue++ ) ;
    	testDictionary.add( "Victrola", intValue++ ) ;
    	testDictionary.add( "WBZ", intValue++ ) ;
    	testDictionary.add( "eXXcelent", intValue++ ) ;
    	testDictionary.add( "You don't say!", intValue++ ) ;
    	testDictionary.add( "Zoso", intValue++ ) ;
    	
    	// print the dictionary so far
    	testDumpStructure( testDictionary  );
    	
    	// remove something not in the dictionary
    	System.out.println( "remove()ing \"Bet you can't find me\" returned " +
    						testDictionary.remove( "Bet you can't find me" ) ) ;
    	
    	// print the dictionary again
    	testDumpStructure( testDictionary  );
    	
    	// flip the state of the force prime table size switch
    	System.out.println( "reversing force prime table size" ) ;
    	testDictionary.setPrimeTableSize( !testDictionary.getPrimeTableSize() ) ;
    	
    	// print the dictionary yet again
    	testDumpStructure( testDictionary  );
    	
    	System.out.println( "+6 entries" ) ;
    	
    	testDictionary.add( "AA", intValue++ ) ;
    	testDictionary.add( "BB", intValue++ ) ;
    	testDictionary.add( "CC", intValue++ ) ;
    	testDictionary.add( "DD", intValue++ ) ;
    	testDictionary.add( "EE", intValue++ ) ;
    	testDictionary.add( "FF", intValue++ ) ;
    	
		System.out.println( "\nFinished loading dictionary\n" ) ;
    	}	// end testLoadDictionary()
	
	
	/**
	 * Unit test utility:
	 * <p>
	 * Print the dictionary to the console.
	 * 
	 * @param testDictionary
	 *        the dictionary to test
	 */
	private static void testDumpStructure( HashTableDictionary<String,
	                                                           Integer> testDictionary )
    	{
    	System.out.printf( "%n%s%n%n",
    	                   testDictionary.dumpStructure() ) ;
        	
    	}	// end testDumpStructure()
	
	}	// end class HashTableDictionaryDMRTests
