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


import java.io.File ;
import java.io.FileNotFoundException ;
import java.io.IOException ;
import java.io.PrintStream ;
import java.nio.file.Files ;
import java.nio.file.Path ;
import java.util.Calendar ;


/**
 * Logger utility.
 * <p>
 * Note: You must use this class, without modification, in your Comp 2000,
 * Dictionary/Hash Table ADT solution.
 * 
 * @author David M Rosenberg
 * @version 1.0.0 initial version - based on my Train Simulation Logger
 * @version 1.1.0 2018-07-26 updates for consistency with other code for this
 *     course
 * @version 1.2.0 2019-07-19
 *     <ul>
 *     <li>minor cosmetic fixes
 *     <li>change load factor and load factor threshold to float to match
 *     Dictionary implementation
 *     </ul>
 * @version 1.3.0 2019-11-25
 *     <ul>
 *     <li>some restructuring in preparation for more extensive reworking
 *     <li>track changes to {@code HashTableDictionary} and
 *     {@code MetricsInterface}
 *     <li>move log file to {@code test-logs} folder
 *     <li>add timestamp to log file name
 *     </ul>
 */
public final class MetricsLogger
	{

	// NOTE: only one log can be open at a time
	private static PrintStream log =   System.out ;


	/**
	 * prevent instantiation
	 */
	private MetricsLogger()
		{
		}


	/**
	 * Close the log file, releasing all allocated resources.
	 */
	public static void close()
		{
		log.close() ;
		log =                             null ;
		
		}	// end close()


    /**
     * Creates and opens the metrics log file in the test-logs folder.
     * <p>
     * Note: If the folder or file cannot be created or accessed, logging will
     * go to the console (System.out)
     */
	public static void create()
		{
		// NOTE: the test-logs folder will be created if it's not already there
		
		// create the detailed log - name is HashTable-metrics--yyyy-mm-dd-hhmmss.log
        Calendar now =                  Calendar.getInstance() ;
        String timestamp =              String.format( "%TF-%<TH%<TM%<TS",
                                                       now
                                                       ) ;

        Path testLogsPath =             new File( "./test-logs" ).toPath()
                                                            .toAbsolutePath()
                                                            .normalize() ;
        
        String outputFilename =         String.format( "%s%c%s--%s.log",
                                                       testLogsPath,
                                                       File.separatorChar,
                                                       "HashTable-metrics",
                                                       timestamp ) ;
        
        try
            {
            testLogsPath =
                Files.createDirectories( testLogsPath ) ;
            
            log =                       new PrintStream( outputFilename ) ;
            }
        catch ( FileNotFoundException e )
            {
            System.err.printf( "Unable to create log file: %s%n\t%s%n" +
                                   "\tusing System.out%n",
                               e.getMessage(),
                               outputFilename ) ;
            }
        catch ( IOException e )
            {
            System.err.printf( "Unable to create log folder: %s%n\t%s%n" +
                                   "\tusing System.out%n",
                               e.getMessage(),
                               testLogsPath ) ;
            }
        
		}	// end create()


    /**
     * Convenience method - logs metrics after hash table resizing
     * 
     * @param bucketCount
     *     the size of the hash table array (the maximum number of buckets the
     *     hash table can currently hold
     * @param bucketsInUse
     *     the number of buckets (elements in the array) which have been
     *     instantiated (note that a bucket can be in use and contain no entries
     *     if the last one has been remove()d.)
     * @param entriesInTable
     *     the current number of entries add()ed to the table (less the number
     *     remove()d).
     * @param minimumBucketSize
     *     the number of entries in the smallest bucket
     * @param maximumBucketSize
     *     the number of entries in the largest bucket
     * @param meanBucketSize
     *     the average (mean) number of entries across all in-use buckets
     * @param medianBucketSize
     *     the average (median) number of entries across all in-use buckets
     * @param loadFactor
     *     the current load factor (lambda)
     * @param loadFactorThreshold
     *     a non-negative value
     * @param primeBucketCount
     *     if true, the next table size will be twice the current size increased
     *     to the next higher prime number; if false, the next table size will
     *     simply be twice the current size.
     */
	public static void logAfterResizeMetrics( int bucketCount,
	                                          int bucketsInUse,
	                                          int entriesInTable,
	                                          int minimumBucketSize,
	                                          int maximumBucketSize,
	                                          int meanBucketSize,
	                                          int medianBucketSize,
	                                          double loadFactor,
	                                          double loadFactorThreshold,
	                                          boolean primeBucketCount )
		{
		logMetrics( "After resizing",
		            bucketCount,
		            bucketsInUse,
		            entriesInTable,
		            minimumBucketSize,
		            maximumBucketSize,
		            meanBucketSize,
		            medianBucketSize,
		            loadFactor,
		            loadFactorThreshold,
					primeBucketCount ) ;
		
		}	// end logAfterResizeMetrics()


    /**
     * Convenience method - logs metrics prior to hash table resizing
     * 
     * @param bucketCount
     *     the size of the hash table array (the maximum number of buckets the
     *     hash table can currently hold
     * @param bucketsInUse
     *     the number of buckets (elements in the array) which have been
     *     instantiated (note that a bucket can be in use and contain no entries
     *     if the last one has been remove()d.)
     * @param entriesInTable
     *     the current number of entries add()ed to the table (less the number
     *     remove()d).
     * @param minimumBucketSize
     *     the number of entries in the smallest bucket
     * @param maximumBucketSize
     *     the number of entries in the largest bucket
     * @param meanBucketSize
     *     the average (mean) number of entries across all in-use buckets
     * @param medianBucketSize
     *     the average (median) number of entries across all in-use buckets
     * @param loadFactor
     *     the current load factor (lambda)
     * @param loadFactorThreshold
     *     a non-negative value
     * @param primeBucketCount
     *     if true, the next table size will be twice the current size increased
     *     to the next higher prime number; if false, the next table size will
     *     simply be twice the current size.
     */
	public static void logBeforeResizeMetrics( int bucketCount,
	                                           int bucketsInUse,
	                                           int entriesInTable,
	                                           int minimumBucketSize,
	                                           int maximumBucketSize,
	                                           int meanBucketSize,
	                                           int medianBucketSize,
	                                           double loadFactor,
	                                           double loadFactorThreshold,
	                                           boolean primeBucketCount )
		{
		logMetrics( "Before resizing",
		            bucketCount,
		            bucketsInUse,
		            entriesInTable,
		            minimumBucketSize,
		            maximumBucketSize,
		            meanBucketSize,
		            medianBucketSize,
		            loadFactor,
		            loadFactorThreshold,
					primeBucketCount ) ;
		
		}	// end logBeforeResizeMetrics()


    /**
     * Convenience method - logs final metrics for fully populated hash table
     * 
     * @param bucketCount
     *     the size of the hash table array (the maximum number of buckets the
     *     hash table can currently hold
     * @param bucketsInUse
     *     the number of buckets (elements in the array) which have been
     *     instantiated (note that a bucket can be in use and contain no entries
     *     if the last one has been remove()d.)
     * @param entriesInTable
     *     the current number of entries add()ed to the table (less the number
     *     remove()d).
     * @param minimumBucketSize
     *     the number of entries in the smallest bucket
     * @param maximumBucketSize
     *     the number of entries in the largest bucket
     * @param meanBucketSize
     *     the average (mean) number of entries across all in-use buckets
     * @param medianBucketSize
     *     the average (median) number of entries across all in-use buckets
     * @param loadFactor
     *     the current load factor (lambda)
     * @param loadFactorThreshold
     *     a non-negative value
     * @param primeBucketCount
     *     if true, the next table size will be twice the current size increased
     *     to the next higher prime number; if false, the next table size will
     *     simply be twice the current size.
     */
	public static void logFinalMetrics( int bucketCount,
	                                    int bucketsInUse,
	                                    int entriesInTable,
										int minimumBucketSize,
										int maximumBucketSize,
										int meanBucketSize,
										int medianBucketSize,
										double loadFactor,
										double loadFactorThreshold,
										boolean primeBucketCount )
		{
		logMetrics( "Final table",
		            bucketCount,
		            bucketsInUse,
		            entriesInTable,
		            minimumBucketSize,
		            maximumBucketSize,
		            meanBucketSize,
		            medianBucketSize,
		            loadFactor,
		            loadFactorThreshold,
					primeBucketCount ) ;
		
		}	// end logFinalMetrics()


    /**
     * Convenience method - logs metrics upon hash table instantiation
     * 
     * @param bucketCount
     *     the size of the hash table array (the maximum number of buckets the
     *     hash table can currently hold
     * @param loadFactorThreshold
     *     a non-negative value
     * @param primeBucketCount
     *     if true, the next table size will be twice the current size increased
     *     to the next higher prime number; if false, the next table size will
     *     simply be twice the current size.
     */
	public static void logInitialMetrics( int bucketCount,
	                                      double loadFactorThreshold,
										  boolean primeBucketCount )
		{
		logMetrics( "Initial table",
		            bucketCount,
		            0, 0, 0, 0, 0, 0, 0.0F,
		            loadFactorThreshold,
					primeBucketCount ) ;
		
		}	// end logInitialMetrics()


    /**
     * Add a table metrics entry to the log, including a brief description
     * 
     * @param description
     *     brief text describing this log entry
     * @param bucketCount
     *     the size of the hash table array (the maximum number of buckets the
     *     hash table can currently hold
     * @param bucketsInUse
     *     the number of buckets (elements in the array) which have been
     *     instantiated (note that a bucket can be in use and contain no entries
     *     if the last one has been remove()d.)
     * @param entriesInTable
     *     the current number of entries add()ed to the table (less the number
     *     remove()d).
     * @param minimumBucketSize
     *     the number of entries in the smallest bucket
     * @param maximumBucketSize
     *     the number of entries in the largest bucket
     * @param meanBucketSize
     *     the average (mean) number of entries across all in-use buckets
     * @param medianBucketSize
     *     the average (median) number of entries across all in-use buckets
     * @param loadFactor
     *     the current load factor (lambda)
     * @param loadFactorThreshold
     *     a non-negative value
     * @param primeBucketCount
     *     if true, the next table size will be twice the current size increased
     *     to the next higher prime number; if false, the next table size will
     *     simply be twice the current size.
     */
	public static void logMetrics( String description,
	                               int bucketCount,
	                               int bucketsInUse,
								   int entriesInTable,
								   int minimumBucketSize,
								   int maximumBucketSize,
								   int meanBucketSize,
								   int medianBucketSize,
								   double loadFactor,
								   double loadFactorThreshold,
								   boolean primeBucketCount )
		{
		write( String.format( "%-17s %,8d %,8d %,8d %,8d %,8d %,8d %,8d %,8.3f  %,8.3f   %b",
		                      description,
		                      bucketCount,
                              bucketsInUse,
							  entriesInTable,
							  minimumBucketSize,
							  maximumBucketSize,
							  meanBucketSize,
							  medianBucketSize,
							  loadFactor,
							  loadFactorThreshold,
							  primeBucketCount
		                      ) ) ;
		
		}	// end logMetrics()


	/**
	 * Appends the provided text to the log, including a terminating newline.
	 * 
	 * @param logEntry
	 *        the text to append to the log.
	 */
	public static void write( String logEntry )
		{
		log.println( logEntry ) ;
		
		}	// end write()
	
	
	/**
	 * Inserts column headings into the log
	 */
	public static void writeColumnHeaders()
    	{
		write( "                                              Minimum  Maximum  Mean     Median            Load      Prime" ) ;
		write( "                   Bucket   Buckets  Entries  Bucket   Bucket   Bucket   Bucket   Load     Factor    Bucket" ) ;
		write( "Description        Count    In Use   In Table Size     Size     Size     Size     Factor   Threshold Count" ) ;
		write( "------------------ -------- -------- -------- -------- -------- -------- -------- -------- --------- --------" ) ;
		
    	}	// end writeColumnHeaders()


    /**
     * Unit test driver.
     * 
     * @param args
     *     -unused-
     *     
     * @throws FileNotFoundException
     *     refer to {@link #create()}
     */
	public static void main( String[] args ) throws FileNotFoundException
		{
		System.out.println( "Starting Logger testing\n" ) ;

		System.out.println( "create the log and insert an intro message" ) ;

		MetricsLogger.create() ;		// create the file
		MetricsLogger.write( "Start MetricsLogger test\n" ) ;
		MetricsLogger.writeColumnHeaders();
		
		// log initial metrics
		MetricsLogger.logInitialMetrics( 101,
		                                 0.75,
		                                 true );
		
		// log pre-resize metrics
		MetricsLogger.logBeforeResizeMetrics( 101,
		                                      15,
		                                      123,
		                                      0,
		                                      12,
		                                      5,
		                                      4,
		                                      0.81,
		                                      0.75,
		                                      true );
		
		// log pre-resize metrics
		MetricsLogger.logAfterResizeMetrics( 203,
		                                     17,
		                                     123,
		                                     2,
		                                     8,
		                                     4,
		                                     4,
		                                     0.6,
		                                     0.75,
		                                     true );
		
		// log final metrics
		MetricsLogger.logFinalMetrics( 203,
		                               101,
		                               123456,
		                               1,
		                               123,
		                               5,
		                               6,
		                               0.57,
		                               0.75,
		                               true );

		// wrap it up
		System.out.println( "insert concluding message and close the log" ) ;
		MetricsLogger.write( "\nFinish MetricsLogger test" ) ;
		MetricsLogger.close() ;			// release the resources

		System.out.println( "\nFinished MetricsLogger testing" ) ;
		
		}	// end main()

	}	// end class MetricsLogger
