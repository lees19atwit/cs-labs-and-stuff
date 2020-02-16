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

/*
 * Your assignment:
 * 
 * (1) update the @author tag: replace "Your Name" with your full name
 * (2) complete the implementation of enlargeHashTable()
 *      - see the TODO comment for details
 * (3) finish the implementation of next() in TableEntryIterator
 *      - see the TODO comment for details
 * 
 * NOTE: Do not create any additional instance variables - all instance 
 *       variables must be used for their current purposes.
 * 
 * ----------
 * 
 * Style Requirements:
 * DO NOT change the formatting of this code.  Your additions must blend
 *       seamlessly with mine.
 * DO NOT remove any of my comments.
 * YOU MUST use "this." to qualify all references to instance variables.
 * YOU MUST use fully spelled out, mnemonic variable names.
 * YOU MUST use curly braces "{}" around any single statement controlled by a
 *      conditional (if) or loop (for, while).
 * 
 * WARNING: Failure to follow these style requirements precisely will result in
 *          significant point deductions!  I will run a differences comparison
 *          between your submitted code and the original version I provided -
 *          they may only differ where I indicated.
 */

package edu.wit.dcsn.comp2000.dictionaryadt ;

import java.util.Arrays ;
import java.util.Iterator ;
import java.util.LinkedList ;
import java.util.NoSuchElementException ;


/**
 * A Dictionary (key/value pair) implementation using an array of LinkedLists as
 * the backing store.
 * 
 * @author David M Rosenberg
 * @version 1.0.0
 * @version 2.0.0 2019-07-19
 *     <ul>
 *     <li>Moved constant declarations here from DictionaryInterface
 *     <li>Added {@code implements MetricsInterface} to track
 *     separation of those methods from {@code DictionaryInterface}
 *     <li>switch hashCode(String), isPrime(), nextPrime() to static
 *     <li>several corrections and enhancements to isPrime()
 *     <li>replace toString() with dumpStructure() to better reflect its
 *     function
 *     <li>add integrity checking
 *     <li>qualify all instance variable access with {@code this.}
 *     <li>modularize metrics reporting
 *     </ul>
 * @version 2.1.0 2019-11-24
 *     <ul>
 *     <li>remove some instance variables
 *          <ul>
 *          <li>move them to local variables where needed
 *          <li>remove getter methods
 *          </ul>
 *     <li>change hashCode() methods to static in HashTableDictionary
 *     <li>implement hashCode() in TableEntry
 *     </ul>
 * 
 * @author Your Name
 * @version 2.2.0 2019-11-26 complete implementation per assignment
 * 
 * @param <K>
 *     type for keys
 *     <p>
 *     Note: {@code K} must meaningfully implement {@code toString()}
 * @param <V>
 *     type for values
 */
public class HashTableDictionary<K, V>
                    implements DictionaryInterface<K, V>,
                                MetricsInterface,
                                DebuggingInterface
	{

    /*
     * public constants
     */
    /** Load factor threshold */
    public final static double DEFAULT_LOAD_FACTOR_THRESHOLD =      1.0 ;

    /**
     * Force the table size (number of buckets) to a prime number to improve key
     * distribution
     */
    public final static boolean	DEFAULT_FORCE_PRIME_TABLE_SIZE =    true ;
    
    /** Initial table size (number of buckets) */
    public final static int DEFAULT_TABLE_SIZE =                    10 ;
    

	/*
	 * instance variables
	 */
	private LinkedList<TableEntry>[]	hashTable ;
	private int							entryCount ;
	private int							bucketsInUse ;
	private int							timesResized ;
	
    /*
     * when the load factor (loadFactor) exceeds the threshold, the table must
     * be resized - could be zero or very small which will force the table to be
     * resized after every add()
     */
	private double						loadFactor ;
	private double						loadFactorThreshold ;

	/*
	 * true: table size will be set to the next prime (if not already prime)
	 * false: table size will be used as is (for initial allocation and when
	 * doubled)
	 */
	private boolean						forcePrimeTableSize ;

	/*
	 * true: prevent add() from checking instance integrity while enlarging the
	 * table (rehashing) - this enables add() to execute while the table is in
	 * an inconsistent state
	 * 
	 * false: add() will check instance integrity
	 */
	private boolean						resizing ;
	
	/*
	 * true if data structure is in a stable/usable state, false if corrupted
	 */
	private boolean						integrityOk ;


	/*
	 * constructors
	 */

	/**
	 * Set up the dictionary/hash table with the default configuration.
	 */
	public HashTableDictionary()
		{
		this( DEFAULT_TABLE_SIZE, 
		      DEFAULT_LOAD_FACTOR_THRESHOLD, 
		      DEFAULT_FORCE_PRIME_TABLE_SIZE 
		      ) ;
		}	// end no-argument constructor


	/**
	 * Set up the dictionary/hash table with the specified initial size and
	 * default loadFactor threshold and prime table size configuration.
	 * 
	 * @param initialTableSize
	 *        the initial number of buckets - if not prime, increased to the
	 *        next larger prime number - set to the default configuration value
	 *        if less than or equal to 0
	 */
	public HashTableDictionary( int initialTableSize )
		{
		this( initialTableSize, 
		      DEFAULT_LOAD_FACTOR_THRESHOLD,
		      DEFAULT_FORCE_PRIME_TABLE_SIZE 
		      ) ;
		}	// end 1-argument constructor


	/**
	 * Set up the dictionary/hash table with the specified initial size and
	 * loadFactor threshold and default prime table size configuration.
	 * 
	 * @param initialTableSize
	 *        initial number of buckets - if not prime, increased to the next
	 *        larger prime number - set to the default configuration value if
	 *        less than or equal to 0
	 * @param initialLambdaThreshold
	 *        a positive, non-zero value - when the load factor exceeds this
	 *        value, the table will be resized - set to the default
	 *        configuration value if less than or equal to 0
	 */
	public HashTableDictionary( int initialTableSize, 
	                            double initialLambdaThreshold )
		{
		this( initialTableSize, 
		      initialLambdaThreshold, 
		      DEFAULT_FORCE_PRIME_TABLE_SIZE 
		      ) ;
		}	// end 2-argument constructor


	/**
	 * Set up the dictionary/hash table with the specified initial size and
	 * loadFactor threshold and default prime table size configuration.
	 * 
	 * @param initialTableSize
	 *        initial number of buckets - if not prime, increased to the next
	 *        larger prime number (if prime table size enforced) otherwise used
	 *        as-is - set to the default configuration value if less than or
	 *        equal to 0
	 * @param initialLambdaThreshold
	 *        a positive, non-zero value - when the load factor exceeds this
	 *        value, the table will be resized - set to the default
	 *        configuration value if less than or equal to 0
	 * @param initialForcePrimeTableSize
	 *        if true, table size (number of buckets) will always be increased
	 *        to the next prime number (if not already prime) upon instantiation
	 *        and resizing - if false, table size is used as-is
	 *        (initialTableSize and new table size when resized)
	 */
	@SuppressWarnings( "unchecked" )
	public HashTableDictionary( int initialTableSize,
	                            double initialLambdaThreshold,
	                            boolean initialForcePrimeTableSize )
		{
		// instance isn't yet usable
		this.integrityOk =			false ;
		
		// must be set before initial table size is validated
		this.forcePrimeTableSize =	initialForcePrimeTableSize ;
		
		// interlock to prevent resizing while resizing
		this.resizing =				false ;

		// may be increased to the next prime number
		initialTableSize =		validateTableSize( initialTableSize ) ;

		// Load factor threshold will be set to the default if negative
		// Note: does not use SetLoadFactorThreshold() because it may try to 
		//		resize the table
		this.loadFactor =				0.0 ;
		this.loadFactorThreshold =
		                validateLoadFactorThreshold( initialLambdaThreshold ) ;
		
		// instantiate the array of LinkedLists
		this.hashTable =			new LinkedList[ initialTableSize ] ;


		// initialize counters
		this.entryCount =			0 ;
		this.bucketsInUse =			0 ;
		this.timesResized =			0 ;
		
		// instance is usable
		this.integrityOk =			true ;
		
		// Log the initial/empty table's metrics
		MetricsLogger.logInitialMetrics( this.hashTable.length,
										 this.loadFactorThreshold,
										 this.forcePrimeTableSize
										 );
		}	// end full constructor
	
	
	/*
	 * Public API methods: Dictionary interface
	 */


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.wit.dcsn.comp2000.dictionaryadt.DictionaryInterface#add(java.lang.
	 * Object, java.lang.Object)
	 */
	@Override
	public V add( K key, 
	              V value )
		{
        // key and value must both be non-null
        if ( ( key == null ) || ( value == null ) )
            {
            throw new IllegalArgumentException( "Cannot add null to a dictionary." ) ;
            }
        
		// if the table is resizing (rehashing), we can't check the integrity
		// Note: There is no mechanism for an application to directly set/clear
		//	the flag - it can only be set (and subsequently cleared) by 
		//	enlargeHashTable().
		if ( !this.resizing )
    		{
    		checkIntegrity() ;
    		}
		
		V savedValue =					null ;	// assume no prior value
		
		int bucketIndex =				hashIndex( key ) ;
		LinkedList<TableEntry> bucket ;
		
		if( this.hashTable[ bucketIndex ] == null )		// no bucket yet
			{
			makeBucket( bucketIndex ) ;				// create it
			}

		bucket =				this.hashTable[ bucketIndex ] ;	// refer to it
		
		// get the position/index in the bucket for this key
		// WARNING: Must search with a TableEntry rather than the key
		// because LinkedList uses the parameter's class' equals() method, not 
		// the element's!!!
		int bucketPosition =	bucket.indexOf( new TableEntry( key, 
		                    	                                null ) ) ;
		if( bucketPosition == -1 )	// key isn't already in the bucket - add it
			{
			// update counters/metrics
			this.entryCount++ ;

			bucket.add( new TableEntry( key, value ) ) ;	// add it
		
			calculateLoadFactor() ;				// recalculate the load factor
			
			// resize the table if we now exceed the load factor threshold
			if( this.loadFactor >= this.loadFactorThreshold )
				{
				enlargeHashTable() ;
				}
			}
		else
			{
			// key is already in the bucket - replace it
			savedValue =		bucket.set( bucketPosition,
			          					    new TableEntry( key, 
			          					                    value ) ).value ;
													// and save the prior value
			
			// no counters/metrics affected
			}
		
		// return the prior value corresponding to this key if replaced
		// otherwise null if added
		return savedValue ;
		
		}	// end add()
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.wit.dcsn.comp2000.dictionaryadt.DictionaryInterface#clear()
	 */
	@Override
	public void clear()
		{
		checkIntegrity() ;
		
		// release all buckets
		Arrays.fill( this.hashTable, null ) ;
		
		// reset the counters
		this.entryCount =             0 ;
		this.bucketsInUse =           0 ;
		
		// and the load factor
		this.loadFactor =             0.0 ;
		
		}	// end clear()
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.wit.dcsn.comp2000.dictionaryadt.DictionaryInterface#contains(java.
	 * lang.Object)
	 */
	@Override
	public boolean contains( K key )
		{
		checkIntegrity() ;
		
		boolean inTable =			false ;
		
		int bucketIndex =			hashIndex( key ) ;
		
		LinkedList<TableEntry> bucket =	this.hashTable[ bucketIndex ] ;
		
		if( bucket != null )		// check bucket if it's in the table
			{
			// WARNING: Must search with a TableEntry rather than the key
			// because LinkedList uses the parameter's class' equals() method,
			// not the element's!!!
			inTable =        bucket.contains( new TableEntry( key, null ) ) ;
			}
		
		return inTable ;
		
		}	// end contains()
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.wit.dcsn.comp2000.dictionaryadt.DictionaryInterface#getKeyIterator()
	 */
	@Override
	public Iterator<K> getKeyIterator()
		{
		checkIntegrity() ;
		
		return new KeyIterator() ;
		
		}	// end getKeyIterator()
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.wit.dcsn.comp2000.dictionaryadt.DictionaryInterface#getSize()
	 */
	@Override
	public int getSize()
		{
		checkIntegrity() ;
		
		return this.entryCount ;
		
		}	// end getSize()
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.wit.dcsn.comp2000.dictionaryadt.DictionaryInterface#getValue(java.
	 * lang.Object)
	 */
	@Override
	public V getValue( K key )
		{
		checkIntegrity() ;
		
		V savedValue =          null ;
		
		int bucketIndex =       hashIndex( key ) ;
		
		LinkedList<TableEntry> bucket =	this.hashTable[ bucketIndex ] ;
		
		if( bucket != null )				// there's a bucket
			{
			// get the position/index in the bucket for this key
			// WARNING: Must search with a TableEntry rather than the key
			// because LinkedList uses the parameter's class' equals() method, 
			// not the element's!!!
			int bucketPosition =
			                    bucket.indexOf( new TableEntry( key, null ) ) ;
			
			if( bucketPosition != -1 )
				{
				savedValue =    bucket.get( bucketPosition ).value ;
				}
			}
		
		return savedValue ;
		
		}	// end getValue()

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.wit.dcsn.comp2000.dictionaryadt.DictionaryInterface#getValueIterator(
	 * )
	 */
	@Override
	public Iterator<V> getValueIterator()
		{
		checkIntegrity() ;
		
		return new ValueIterator() ;
		
		}	// end getValueIterator()
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.wit.dcsn.comp2000.dictionaryadt.DictionaryInterface#isEmpty()
	 */
	@Override
	public boolean isEmpty()
		{
		checkIntegrity() ;
		
		return this.entryCount == 0 ;
		
		}	// end isEmpty()
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.wit.dcsn.comp2000.dictionaryadt.DictionaryInterface#remove(java.lang.
	 * Object)
	 */
	@Override
	public V remove( K key )
		{
		checkIntegrity() ;
		
		V savedValue =				null ;
		int bucketIndex =			hashIndex( key ) ;
		LinkedList<TableEntry> bucket =	this.hashTable[ bucketIndex ] ;
		
		if ( bucket != null )			// there is a bucket
			{
			// get the position/index in the bucket for this key
			// WARNING: Must search with a TableEntry rather than the key
			// because LinkedList uses the parameter's class' equals() method, 
			// not the element's!!!
			int bucketPosition =	bucket.indexOf( new TableEntry( key, 
			                    	                                null ) ) ;
			if( bucketPosition != -1 )				// found it so remove it
				{
				savedValue =	bucket.remove( bucketPosition ).value ;
				
				if( bucket.size() == 0 )
					{	// we remove()d the last entry in this bucket
					destroyBucket( bucketIndex ) ;	// get rid of the bucket
					}
				
				// update counters/metrics
				this.entryCount-- ;
				}

			// recalculate the load factor
			calculateLoadFactor() ;
			}	// end if there is a bucket
		
		return savedValue ;
		
		}	// end remove()

	
	/*
	 * Private utility methods
	 */
	
	
	/**
	 * (Re-)calculates the load factor:
	 * {@code
	 * 
	 * 	  loadFactor = # of entries in the dictionary /
	 *                     # of entries in the hash table array
	 * }
	 */
	private void calculateLoadFactor()
    	{
    	// delegate performing the calculation
    	this.loadFactor =      calculateLoadFactor( this.entryCount,
    	                                            this.hashTable.length ) ;
    	
    	}	// end calculateLoadFactor()
	
	
    /**
     * (Re-)calculates the load factor:
     * {@code
     * 
     * 	  loadFactor = # of entries in the dictionary /
     *                      # of entries in the hash table array
     * }
     * 
     * @param numberOfEntries
     *     the number of entries in the dictionary
     * @param tableLength
     *     the current/proposed length of the array
     *     
     * @return the calculated loadFactor
     */
    private static double calculateLoadFactor( int numberOfEntries,
                                               int tableLength )
    	{
    	// must perform calculation using floating point division
    	return ( (double) numberOfEntries ) / tableLength ;
    	
    	}	// end calculateLoadFactor()
    

    /**
     * Ensure the instance was properly initialized
     */
    private void checkIntegrity()
        {
        if ( !this.integrityOk )
            {
            throw new SecurityException( "Dictionary object is corrupt." ) ;
            }
        
        }   // end checkIntegrity()
	

	/**
	 * Remove a bucket (LinkedList) from the table, update the stats to account
	 * for the change
	 * 
	 * @param index
	 *        the position in the table array for the bucket to remove
	 */
	private void destroyBucket( int index )
    	{
    	this.hashTable[ index ] =	null ;
    	
    	// account for the removed bucket
    	this.bucketsInUse-- ;
    	
    	}	// end destroyBucket()
	
	
	/**
	 * Increase the size of the hash table to at least twice its old size (to
	 * the next prime number if necessary). This method must rehash the table
	 * entries.
	 */
	private void enlargeHashTable()
		{
		/*
		 * display metrics before resizing
		 */
        
        int[] bucketSizes =       getBucketSizes() ;
		
		// log the hash table metrics before resizing it
		MetricsLogger.logBeforeResizeMetrics( 
                                    this.hashTable.length,
                                    this.bucketsInUse,
                                    this.entryCount,
                                    getSmallestBucketSize( bucketSizes ),
                                    getLargestBucketSize( bucketSizes ),
                                    getMeanBucketSize( bucketSizes ),
                                    getMedianBucketSize( bucketSizes ),
                                    this.loadFactor,
                                    this.loadFactorThreshold,
                                    this.forcePrimeTableSize
                                    ) ;
		
		
		/*
		 * resize the table
		 */
		
		// save the current array
		LinkedList<TableEntry>[] oldTable =	this.hashTable ;
		
		
		// double the table size - may be increased to the next prime number
		int newTableSize =		validateTableSize( this.hashTable.length ) ;
		this.loadFactor =			calculateLoadFactor( this.entryCount,
			    		                         newTableSize ) ;
		
		while ( this.loadFactor >= this.loadFactorThreshold )
			{
			newTableSize =		validateTableSize( newTableSize * 2 ) ;
			this.loadFactor =		calculateLoadFactor( this.entryCount,
			    		                         newTableSize ) ;
			}
		
		// in case the allocation fails, indicate that the instance is unusable
		this.integrityOk =            false ;

		// reset/increment the counters
		this.bucketsInUse =           0 ;
		this.entryCount =             0 ;
		this.timesResized++ ;

		// re-instantiate the array of LinkedLists
		@SuppressWarnings( "unchecked" )
		LinkedList<TableEntry>[] newHashTable =	
                                      new LinkedList[ newTableSize ] ;
		this.hashTable =              newHashTable ;

		this.resizing =               true ;  // prevent resizing while resizing
		
		// walk the old table and add() each entry into the new table
		/*
		 * The structure is an array of LinkedList<TableEntry<K,V>>
		 * 
		 * The algorithm is:
		 * 
		 * 1) Iterate over each element of the oldTable array (it's pointing
		 *         to the original this.hashTable)
		 * 2)   If the array element isn't null, it's pointing at an in-use 
		 *           bucket so it's pointing to a LinkedList
		 * 3)     Iterate over each element of this bucket (LinkedList) using 
		 *             its iterator() - do not issue get(index)s for each 
		 *             element in the list
		 * 4)       You now have a TableEntry - add() the key and value from the 
		 *               TableEntry to the 'new' hash table
		 * 
		 * Excluding '{' and '}' lines, this entire implementation is 4 lines
		 * of code.  Use foreach loops for steps 1 and 3.
		 * 
		 * NOTE: Do not create any local variables except the two loop variables
		 *       in steps 1) and 3).
		 */
		// TODO your code goes here:

		for(LinkedList<TableEntry> bucket : oldTable)
		{
			if (bucket != null)
			{
				for(TableEntry a : bucket)
				{
					this.add(a.key, a.value); 
				}
			}
		}
		
		/*
		for( LinkedList<TableEntry> aBucket : this.hashTable )
		{
		if( aBucket != null )
			{
			bucketSizes[ bucketSizesIndex++ ] =     aBucket.size() ;
			}
		}
	*/
        // Do not modify anything else in this method.
		
		this.resizing =				false ;	// re-enable resizing
		
		// instance is usable
		this.integrityOk =			true ;
		
		/*
		 * display metrics after resizing
		 */
		bucketSizes =             getBucketSizes() ;
		
		// log the hash table metrics after resizing it
		MetricsLogger.logAfterResizeMetrics(
                                    this.hashTable.length,
                                    this.bucketsInUse,
                                    this.entryCount,
                                    getSmallestBucketSize( bucketSizes ),
                                    getLargestBucketSize( bucketSizes ),
                                    getMeanBucketSize( bucketSizes ),
                                    getMedianBucketSize( bucketSizes ),
                                    this.loadFactor,
                                    this.loadFactorThreshold,
                                    this.forcePrimeTableSize
                                    ) ;
		
		} // end enlargeHashTable()
	
	
    /**
     * Returns the sizes of the in-use buckets in non-descending order
     * 
     * @return a properly sized array of int such that
     *     {@code bucketSize[i] <= bucketSize[i + 1]}
     */
	private int[] getBucketSizes()
    	{
		// walk the table and add each bucket size into an array of bucket
		// sizes
    	int[] bucketSizes =            new int[ this.bucketsInUse ] ;
    	int bucketSizesIndex =	0 ;
    	
		for( LinkedList<TableEntry> aBucket : this.hashTable )
			{
			if( aBucket != null )
				{
				bucketSizes[ bucketSizesIndex++ ] =     aBucket.size() ;
				}
			}
		
		Arrays.sort( bucketSizes ) ;
		
		return bucketSizes ;
		
    	}	// end getBucketSizes()

    
    /**
     * Determine the largest bucket size in a sorted array of bucket sizes.
     * <p>
     * Note that this method does not verify that the array is sorted.
     * 
     * @param sortedValues
     *     the dataset sorted from smallest to largest value
     *     
     * @return if the array contains any values, the maximum value (greater than
     *     0); if the array is empty, -1
     */
    private static int getLargestBucketSize( int[] sortedValues )
        {
        int maximumValue =      -1 ;    // flag for no values
        
        if ( sortedValues.length != 0 )
            {
            // use the last value
            maximumValue =  sortedValues[ sortedValues.length - 1 ] ;
            }
        
        return maximumValue ;
        
        }   // end getLargestBucketSize()
    
    
    /**
     * Determine the mean bucket size in an array of bucket sizes.
     * 
     * @param values
     *        the dataset to evaluate
     * @return if the array contains any values, the mean; if the array is
     *         empty, -1
     *         <p>
     *         Note: in the context of this class, the values will always be
     *         non-negative so -1 can never be meaningful average value.
     */
    private static int getMeanBucketSize( int[] values )
        {
        int meanValue =         -1 ;    // flag for no values
        
        int numberOfValues =    values.length ;
        
        if ( numberOfValues != 0 )
            {
            // calculate the sum of all values
            int sum =           0 ;
            for ( int value : values )
                {
                sum +=          value ;
                }
            
            // calculate the mean (average)
            meanValue =         sum / numberOfValues ;
            }
        
        return meanValue ;
        
        }   // end getMeanBucketSize()
    
    
    /**
     * Determine the median bucket size in a sorted array of bucket sizes.
     * <p>
     * Note that this method does not verify that the array is sorted.
     * 
     * @param sortedValues
     *        the dataset to evaluate where all values are greater than or equal
     *        to 0
     *        
     * @return if the array contains any values, the median; if the array is
     *         empty, -1
     */
    private static int getMedianBucketSize( int[] sortedValues )
        {
        int medianValue =       -1 ;    // flag for no values
        
        int numberOfValues =    sortedValues.length ;
        
        if ( numberOfValues != 0 )
            {
            if ( numberOfValues % 2 == 0 )
                {
                // need the average of the two middle values
                medianValue =   ( sortedValues[ ( numberOfValues / 2 ) - 1 ] +
                                ( sortedValues[ numberOfValues / 2 ] ) / 2 ) ;
                }
            else
                {
                // use the middle value
                medianValue =   sortedValues[ numberOfValues / 2 ] ;
                }
            }
        
        return medianValue ;
        
        }   // end getMedianBucketSize()
    
    
    /**
     * Determine the smallest bucket size in a sorted array of bucket sizes.
     * <p>
     * Note that this method does not verify that the array is sorted.
     * 
     * @param sortedValues
     *        the dataset sorted from smallest to largest value
     *        
     * @return if the array contains any values, the minimum value; if the array
     *         is empty, -1
     */
    private static int getSmallestBucketSize( int[] sortedValues )
        {
        int minimumValue =      -1 ;    // flag for no values
        
        if ( sortedValues.length != 0 )
            {
            // use the first value
            minimumValue =  sortedValues[ 0 ] ;
            }
        
        return minimumValue ;
        
        }   // end getSmallestBucketSize()


    /**
     * Hashing function given a key of an arbitrary class. If the key isn't a
     * String, class K' toString() method provides a String representation which
     * is then hashed.
     * <p>
     * Note: This method is for educational purposes only - hashCode() properly
     * belongs in the key's class (K).
     * 
     * @param key
     *     the key to hash
     *     
     * @return hashed value
     * 
     * @param <K>
     *     type for keys
     */
	private static <K> int hashCode( K key )
    	{
    	return hashCode( key.toString() ) ;
    	
    	}	// end hashCode() given key of type K (other than String)
	
	
	/**
	 * Hashing function given a String.
	 * 
	 * <p>
	 * <cite>Based upon code in §21.8 of Carrano &amp; Henry's "Data Structures
	 * and Abstractions with Java, 4th edition"</cite>
	 * 
     * <p>
     * Note: This method is for educational purposes only - hashCode() properly
     * belongs in class K.
	 * 
	 * @param key
	 *        the key to hash as text
	 * @return hashed value
	 */
	private static int hashCode( String key )
    	{
    	int hashedKey =		0 ;
    	final int g =		31 ;	// per textbook
    	int keyLength =		key.length() ;
    	
    	for( int i = 0; i < keyLength; i++ )
    		{
    		hashedKey =		hashedKey * g + key.charAt( i ) ;
    		}
    	
    	return hashedKey ;
    	
    	}	// end hashCode() given key of type String
	
	
	/**
	 * Hash the key into a valid index into the hash table array.
	 * 
	 * <p>
	 * <cite>Based upon code in §21.11 of Carrano &amp; Henry's "Data Structures
	 * and Abstractions with Java, 4th edition"</cite>
	 * 
	 * @param key
	 *        the key to hash
	 * @return the hashed key mapped into the valid range of indices for the
	 *         hash table array
	 */
	private int hashIndex( K key )
    	{
    	int hashedIndex =      hashCode( key ) % this.hashTable.length ;
    	if( hashedIndex < 0 )
    		{
    		hashedIndex +=     this.hashTable.length ;
    		}
    	
    	return hashedIndex ;
    	
    	}	// end hashIndex()
	

	/**
	 * Test a natural number (integer) for prime-ness.
	 * 
	 * @param testValue
	 *        value to test
	 * @return true if testValue is prime; false otherwise
	 */
	private static boolean isPrime( int testValue )
		{
		boolean result;
		
		if ( testValue <= 1 )		// negative numbers, 0, and 1 are not prime
			{
			result =			false; 
			}
		else if ( testValue <= 3 )
			{						// 2 and 3 are prime
			result =			true;
			}
		else if ( testValue % 2 == 0 )
			{						// all other even numbers are not prime
			result =			false ;
			}
		else
			{						// integer is odd and >= 5; might be prime
			// remaining primes are odd and not divisible by every odd integer
			// up to its square root
			result =			true;		// assume prime
			
            for ( int divisor = 3 ;
                  ( divisor * divisor ) <= testValue ;
                  divisor += 2 )
				{
				if ( testValue % divisor == 0 )
					{
					result =	false;	// divisible; not prime

					break ;
					} // end if
				
				} // end for
			
			} // end if
	   	
		return result;
		
		}	// end isPrime()

	
	/**
	 * Create a bucket (LinkedList), insert it in the table, update the stats to
	 * account for it
	 * 
	 * @param index
	 *        the position in the table array for the new bucket
	 */
	private void makeBucket( int index )
    	{
    	// instantiate the linked list
    	this.hashTable[ index ] =	new LinkedList<>() ;
    	
		// count the new bucket and recreate the array of bucket sizes to
		// reflect
    	this.bucketsInUse++ ;
    	
    	}	// end makeBucket()
	
	
	/**
	 * Get the next prime number greater than or equal to the starting value
	 * 
	 * @param startValue
	 *        the initial value
	 * @return if startValue is already prime it is returned, otherwise the next
	 *         higher value which is prime
	 */
	private static int nextPrime( int startValue )
		{
		int nextPrimeCandidate =	startValue ;
		
		// skip to first odd prime (3) if less than 3
		// 2 is prime but we want the table size to be odd to better distribute
		// keys across buckets
		if ( nextPrimeCandidate < 3 )
			{
			nextPrimeCandidate =	3 ;
			}
		
		// if even (greater than 2), add 1 to make odd
		else if ( nextPrimeCandidate % 2 == 0)
			{
			nextPrimeCandidate++ ;
			}	// end if
		
		
	   	// test odd integers
		while( !isPrime( nextPrimeCandidate ) )
			{
			nextPrimeCandidate +=	2;
			} // end while

		return nextPrimeCandidate ;
		
		}	// end nextPrime()
	

	/**
     * Ensure the candidate load factor threshold is non-negative.
     * 
     * @param candidateThreshold
     *     value for consideration for load factor threshold
     * @return a positive threshold value
     */
    private static double
            validateLoadFactorThreshold( double candidateThreshold )
		{
		return ( candidateThreshold <= 0.0 )
				 ? DEFAULT_LOAD_FACTOR_THRESHOLD
				 : candidateThreshold ;
		
		}	// end validateLambdaThreshold()

	
	/**
	 * Ensure the candidate table size (number of buckets) is positive,
	 * non-zero, and prime (if necessary).
	 * 
	 * @param candidateTableSize
	 *        starting value for consideration for table size
	 * @return a positive, non-zero size which, if necessary, is prime
	 */
	private int validateTableSize( int candidateTableSize )
		{
		// ensure the supplied size is positive, non-zero
		int validatedTableSize =	( candidateTableSize <= 0 )
										? DEFAULT_TABLE_SIZE
										: candidateTableSize ;

		// if the size must be prime, make it so
		if ( this.forcePrimeTableSize )
			{
			validatedTableSize =	nextPrime( validatedTableSize ) ;
			}

		return validatedTableSize ;
		
		}	// end validateTableSize()
	
    
    /*
     * --------------------------------------------------
     * 
     * Public API utility methods: Metrics
     * 
     * --------------------------------------------------
     */
    

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.wit.dcsn.comp2000.dictionaryadt.DictionaryInterface#doneLoading()
     */
    @Override
    public void doneLoading()
        {
        checkIntegrity() ;
        
        int[] bucketSizes =       getBucketSizes() ;
        
        // Log the final/fully-loaded table's metrics
        MetricsLogger.logFinalMetrics( this.hashTable.length,
                                       this.bucketsInUse,
                                       this.entryCount,
                                       getSmallestBucketSize( bucketSizes ),
                                       getLargestBucketSize( bucketSizes ),
                                       getMeanBucketSize( bucketSizes ),
                                       getMedianBucketSize( bucketSizes ),
                                       this.loadFactor,
                                       this.loadFactorThreshold,
                                       this.forcePrimeTableSize ) ;
        
        }   // end doneLoading()
    
    
    /*
     * (non-Javadoc)
     * 
     * @see edu.wit.dcsn.comp2000.dictionaryadt.MetricsInterface#
     * getBucketsInUse()
     */
    @Override
    public int getBucketsInUse()
        {
        checkIntegrity() ;
        
        return this.bucketsInUse ;
        
        }   // end getBucketsInUse()

    
    /*
     * (non-Javadoc)
     * 
     * @see edu.wit.dcsn.comp2000.dictionaryadt.MetricsInterface#
     * getLoadFactor()
     */
    @Override
    public double getLoadFactor()
        {
        checkIntegrity() ;
        
        return this.loadFactor ;
        
        }   // end getLoadFactor()


    /*
     * (non-Javadoc)
     * 
     * @see edu.wit.dcsn.comp2000.dictionaryadt.MetricsInterface#
     * getLoadFactorThreshold()
     */
    @Override
    public double getLoadFactorThreshold()
        {
        checkIntegrity() ;
        
        return this.loadFactorThreshold ;
        
        }   // end getLoadFactorThreshold()
    
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.wit.dcsn.comp2000.dictionaryadt.MetricsInterface#getPrimeTableSize()
     */
    @Override
    public boolean getPrimeTableSize()
        {
        checkIntegrity() ;
        
        return this.forcePrimeTableSize ;
        
        }   // end getPrimeTableSize()
    
    
    /*
     * (non-Javadoc)
     * 
     * @see edu.wit.dcsn.comp2000.dictionaryadt.MetricsInterface#
     * getTableSize()
     */
    @Override
    public int getTableSize()
        {
        return this.hashTable.length ;
        
        }   // end getTableSize()
    

    /*
     * (non-Javadoc)
     * 
     * @see edu.wit.dcsn.comp2000.dictionaryadt.MetricsInterface#
     * getTimesResized()
     */
    @Override
    public int getTimesResized()
        {
        checkIntegrity() ;
        
        return this.timesResized ;
        
        }   // end getTimesResized()

    
    /*
     * (non-Javadoc)
     * 
     * @see edu.wit.dcsn.comp2000.dictionaryadt.MetricsInterface#
     * setLoadFactorThreshold(double)
     */
    @Override
    public double setLoadFactorThreshold( double newLoadFactor )
        {
        checkIntegrity() ;
        
        double savedLambdaThreshold =   this.loadFactorThreshold ;
        
        this.loadFactorThreshold =
                                validateLoadFactorThreshold( newLoadFactor ) ;
        
        // if the current load factor exceeds the new threshold, resize the 
        // table
        if( this.loadFactor >= this.loadFactorThreshold )
            {
            enlargeHashTable();
            }
        
        return savedLambdaThreshold ;
        
        }   // end setLoadFactorThreshold()


    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.wit.dcsn.comp2000.dictionaryadt.MetricsInterface#setPrimeTableSize
     * (boolean)
     */
    @Override
    public boolean setPrimeTableSize( boolean forceToPrime )
        {
        checkIntegrity() ;
        
        boolean savedForcePrimeTableSize =  this.forcePrimeTableSize ;
        
        this.forcePrimeTableSize =      forceToPrime ;
        
        if( this.forcePrimeTableSize && !isPrime( this.hashTable.length ) )
            {
            enlargeHashTable() ;
            }
        
        return savedForcePrimeTableSize ;
        
        }   // end setPrimeTableSize()
    
    
    /*
     * Public API utility methods: Testing and Debugging
     */
    

    /*
     * (non-Javadoc)
     * @see edu.wit.dcsn.comp2000.dictionaryadt.DebuggingInterface#
     * dumpStructure()
     */
    @Override
    public String dumpStructure()
        {
        checkIntegrity() ;
        
        StringBuilder contents =    new StringBuilder() ;
        
        for( int i = 0; i < this.hashTable.length; i++ )
            {
            LinkedList<TableEntry> bucket = this.hashTable[ i ] ;
            if( bucket != null )
                {
                if( contents.length() > 0 )
                        {
                        contents.append( "\n" ) ;
                        }
                contents.append( String.format( "\t[%,6d] ", i ) ) ;
                
                boolean first =     true ;
                for ( TableEntry anEntry : bucket )
                    {
                    contents.append( String.format( "%s%s", 
                                                    ( first ? "" : "  " ),
                                                    anEntry ) ) ;
                    
                    first =         false ;
                    }
                
                }
            }
        String format = "Dictionary [entryCount=%,d; hashTable.length=%,d; " +
                        "bucketsInUse=%,d; loadFactor=%,f; " + 
                        "loadFactorThreshold=%,f; forcePrimeTableSize=%b; " + 
                        "timesResized=%,d; hashTable:%n%s%n\t]" ;
        return String.format( format,
                              this.entryCount,
                              this.hashTable.length, 
                              this.bucketsInUse, 
                              this.loadFactor, 
                              this.loadFactorThreshold, 
                              this.forcePrimeTableSize, 
                              this.timesResized,
                              contents
                              ) ;
        
        }   // end dumpStructure()

	
	/*
     * --------------------------------------------------
     * 
	 * Private inner class: TableEntry
     * 
     * --------------------------------------------------
	 */


	/**
     * Utility construct to represent key/value pairs.
     * 
     * <p>
     * WARNING:<br>
     * The Java Class Library's LinkedList class uses K's equals() rather than
     * TableEntry's equals(). This means that we handle both
     * aTableEntry.equals(anotherTableEntry) and aTableEntry.equals(aKey) but
     * our equals() will only get called if we give LinkedList's contains() or
     * indexOf() a TableEntry as the parameter - calling them with a key as the
     * parameter always fails to match.
     * 
     * <p>
     * To use a TableEntry as the parameter to LinkedList methods, instantiate a
     * TableEntry with the key and specify {@code null} for the value:
     * 
     * {@code
     * 
     *     bucket = hashTable[ hashIndex ];
     *     if( bucket.contains( new TableEntry( key, null ) )
     *          {
     *          // the key is in the bucket
     *          }
     * }
     */
	private class TableEntry
    	{
    	/*
    	 * instance variables
    	 */
		private final K	key ;
    	private V		value ;
    	
    	
    	/*
    	 * constructors
    	 */
    	
		/**
		 * Set up the TableEntry - note that the key cannot be changed.
		 * 
		 * @param theKey
		 *        setting for the key (immutable) - never null
		 * @param initialValue
		 *        initial corresponding value - never null
		 */
    	private TableEntry( K theKey, V initialValue )
        	{
        	// assertion: neither theKey nor initialValue are null
        	
        	this.key =		theKey ;
        	this.value =	initialValue ;
        	
        	}	// end 2-arg constructor
    	
    	
    	/*
    	 * public API methods
    	 */
    	

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		@SuppressWarnings( "unchecked" )
		public boolean equals( Object otherObject )
			{
			// if same instance - consider it a match
			if ( this == otherObject )
				{
				return true ;
				}
			
			// if nothing to compare to - no match
			if ( otherObject == null )
				{
				return false ;
				}
			
			// WARNING: Must search with a TableEntry rather than the key
			// 		because LinkedList uses the key's class' equals() method, 
			//		not the element's!!! So, for now, the key handling portion 
			//		of this method will not execute
			
			K otherKey ;
			
			// determine whether otherObject is a TableEntry, a K, or neither
			if ( otherObject.getClass().equals( this.getClass() ) )
				{	// the parameter is a TableEntry - get the key from it
				otherKey =		( ( TableEntry ) otherObject ).key ;
				}
			else if ( otherObject.getClass().equals( this.key.getClass() ) )
				{	// the parameter is a key - use it
				otherKey =		( K ) otherObject ;
				}
			else	// the parameter is not a supported type - so no match
				{
				return false ;
				}

			// finally, we can compare this TableEntry's key and the parameter
			// assertion: key will never be null
			return this.key.equals( otherKey ) ;
			
			}	// end equals()


        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#hashCode()
         */
        @Override
        public int hashCode()
            {
            return HashTableDictionary.hashCode( this.key ) ;
            
            }   // end hashCode()


		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString()
			{
			return "<" + this.key.toString() + " : " + 
							this.value.toString() + ">" ;
			
			}	// end toString()
		
    	}	// end inner class TableEntry

    
    /*
     * --------------------------------------------------
     * 
     * Private inner class: TableEntryIterator
     * 
     * --------------------------------------------------
     */

	
	/**
	 * An iterator over the table entries stored in the hash table - for use by
	 * the key and value iterators.
	 */
	private class TableEntryIterator implements Iterator<TableEntry>
		{
		/*
		 * instance variables
		 */
		private int bucketIndex ;
		private Iterator<TableEntry> bucketIterator ;
		private int remainingEntryCount ;
		
		private LinkedList<TableEntry>[] theTable ;
		                                    // convenience/short-hand reference
		
		
		/**
		 * Set up an iterator over the table entries in the hash table
		 */
		private TableEntryIterator()
    		{
    		// capture the number of entries in the dictionary and a reference
    		// to the hashTable array (for convenience/short-hand)
    		this.remainingEntryCount =    HashTableDictionary.this.entryCount ;
    		this.theTable =               HashTableDictionary.this.hashTable ;
    		
    		if( HashTableDictionary.this.isEmpty() )
    			{
    			// the dictionary is empty - set remaining state to point past
    			// last bucket
    			this.bucketIndex =       this.theTable.length ;
    			
    			this.bucketIterator =    null ;
    			}
    		else
    			{
    			// at least one entry in the dictionary - position to the first
    			// entry
                for ( this.bucketIndex = 0 ;
                      this.bucketIndex < this.theTable.length ;
                      this.bucketIndex++ )
    				{
    				// find the first in-use bucket
    				if( this.theTable[ this.bucketIndex ] != null )
    					{
    					break ;
    					}
    				}
    			
    			// get an iterator to its LinkedList
    			this.bucketIterator =
			                    this.theTable[ this.bucketIndex ].iterator() ;
    			}
    		
    		}	// end no-arg constructor
		

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext()
			{
			// test for any remaining entries
			return this.remainingEntryCount > 0 ;
			
			}	// end hasNext()


    	/*
    	 * (non-Javadoc)
    	 * 
    	 * @see java.util.Iterator#next()
    	 */
    	@Override
    	public TableEntry next()
    		{
    		if( !hasNext() )
    			{
    			throw new NoSuchElementException() ;
    			}
    		
    		// TODO complete this:
    		//    if the current bucketIterator has another entry, stay in this
    		//        bucket
    		//    otherwise, move to the next in-use bucket and get an iterator 
    		//        for it
    		// Do not create any local variables
    		//    --> use what is already available in the instance state
    		
            // TODO your code goes here:

    		if(!bucketIterator.hasNext())
	            {
	            do
	                {        
	                this.bucketIndex++;        
	                }
	            while (this.theTable[this.bucketIndex] == null);
	        
	            this.bucketIterator = this.theTable[this.bucketIndex].iterator();
	            }

            // Do not modify anything else in this method.
    
    		// reduce the number of entries to return
    		this.remainingEntryCount-- ;
    		
    		// return the next entry from the bucket's iterator
    		return this.bucketIterator.next() ;
    		
    		}	// end next()
    
    	}	// end inner class TableEntryIterator

    
    /*
     * --------------------------------------------------
     * 
     * Private inner class: KeyIterator
     * 
     * --------------------------------------------------
     */


	/**
	 * An iterator over the keys stored in the hash table
	 */
	private class KeyIterator implements Iterator<K>
		{
		/*
		 * instance variables
		 */
		private Iterator<TableEntry> tableEntryIterator ;
		
		
		/**
		 * Sets up an iterator over the keys in the hash table
		 */
		private KeyIterator()
    		{
    		this.tableEntryIterator =	new TableEntryIterator() ;
    		
    		}	// end no-arg constructor
		

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext()
			{
			return this.tableEntryIterator.hasNext() ;
			
			}	// end hasNext()


		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#next()
		 */
		@Override
		public K next()
			{
			return this.tableEntryIterator.next().key ;
			
			}	// end next()

		}	// end inner class KeyIterator

    
    /*
     * --------------------------------------------------
     * 
     * Private inner class: ValueIterator
     * 
     * --------------------------------------------------
     */


	/**
	 * An iterator over the values stored in the hash table
	 */
	private class ValueIterator implements Iterator<V>
		{
		/*
		 * instance variables
		 */
		private Iterator<TableEntry> tableEntryIterator ;
		
		
		/**
		 * Sets up an iterator over the values in the hash table
		 */
		private ValueIterator()
    		{
    		this.tableEntryIterator =	new TableEntryIterator() ;
    		
    		}	// end no-arg constructor
		

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext()
			{
			return this.tableEntryIterator.hasNext() ;
			
			}	// end hasNext()


		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#next()
		 */
		@Override
		public V next()
			{
			return this.tableEntryIterator.next().value ;
			
			}	// end next()

		}	// end inner class ValueIterator

	
	/*
     * --------------------------------------------------
     * 
     * for local debugging
	 * 
     * --------------------------------------------------
	 */
	
	/**
	 * You may use main() to debug your code
	 * 
	 * @param args
	 *        -unused-
	 */
	public static void main( String[] args )
		{
		// TODO OPTIONAL stub - you may find this useful for debugging your code
		HashTableDictionary< String, Integer > testDictionary = new HashTableDictionary<>( 3 ) ;
		
		}	// end main()
	
	}	// end class HashTableDictionary
