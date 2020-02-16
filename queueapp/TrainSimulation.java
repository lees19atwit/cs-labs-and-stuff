/*
 * Dave Rosenberg
 * Comp 2000 - Data Structures
 * Lab: Queue App: Train Simulation
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

package edu.wit.dcsn.comp2000.queueapp;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;

import edu.wit.dcsn.comp2000.queueapp.Configuration.PairedLimit;
import edu.wit.dcsn.comp2000.queueapp.Configuration.RouteSpec;
import edu.wit.dcsn.comp2000.queueapp.Configuration.TrainSpec;

/**
 * @author Your Name
 * @version 1.0.0
 */
public class TrainSimulation {

	public static void createRoute(Configuration theConfig, TrainRoute theRoute)
	{
		RouteSpec theRouteSpec = theConfig.getRoute(); 
		
		System.out.printf("Using Route:%n\t%s%n", theRouteSpec);
		System.out.printf("The result is:%n\t%s is %s with length %,d%n", theRoute, theRoute.getStyle(),
				theRoute.getLength());
	}
	
	public static void createStation(Configuration theConfig, TrainRoute theRoute)
	{
		RouteSpec theRouteSpec = theConfig.getRoute(); 
		int[] theStationSpecs = theConfig.getStations(); 
		
		for(int stationLocation : theStationSpecs)
		{
			theRoute.addStation(new Station(theRoute, stationLocation));
		}
		System.out.printf("Using Stations:%n\t%s%n", Arrays.toString(theStationSpecs));
	}
	
	public static void createTrains(Configuration theConfig, TrainRoute theRoute)
	{
		RouteSpec theRouteSpec = theConfig.getRoute();
		TrainSpec[] theTrainSpecs = theConfig.getTrains();
		
		System.out.printf("Using Trains:%n\t%s%n", Arrays.toString(theTrainSpecs));

		System.out.println("The result is:");

		for (TrainSpec aTrainSpecification : theTrainSpecs) {
			Train aTrain = new Train(theRoute, aTrainSpecification);
			theRoute.addTrain(aTrain); 
		} // end foreach()
	}
	
	public static void createPassengers(Configuration theConfig, TrainRoute theRoute)
	{
		RouteSpec theRouteSpec = theConfig.getRoute();
		Random pseudoRandom = new Random(theConfig.getSeed());
		int[] theStationSpecs = theConfig.getStations(); 
		int newPassengerCount = 20; 
		
		System.out.printf("Generating %d passengers (initial):%n", newPassengerCount);
		
		for(int i = 0; i  < newPassengerCount; i++)
		{
			Passenger aPassenger = createAPassenger(theConfig, theRoute, pseudoRandom); 
			System.out.printf("\t%s%n", aPassenger.toStringFull());
			theRoute.getStation(aPassenger.getFrom()).enter(aPassenger); 
		}
	}
	
	public static Passenger createAPassenger(Configuration theConfig, TrainRoute theRoute, Random pseudoRandom)
	{
		int[] theStationSpecs = theConfig.getStations(); 
		
		Location from = new Location(theRoute, theStationSpecs[pseudoRandom.nextInt(theStationSpecs.length)],Direction.NOT_APPLICABLE);
		Location to = new Location(theRoute, theStationSpecs[pseudoRandom.nextInt(theStationSpecs.length)],Direction.NOT_APPLICABLE);
		
		do {
			from = new Location(theRoute, theStationSpecs[pseudoRandom.nextInt(theStationSpecs.length)],Direction.NOT_APPLICABLE);
			to = new Location(theRoute, theStationSpecs[pseudoRandom.nextInt(theStationSpecs.length)],Direction.NOT_APPLICABLE);
		}while(from.equals(to));
		
		return new Passenger(from, to, 0); // current time indicates that clock hasn't started
	}
	
	public static void setUp(Configuration theConfig, TrainRoute theRoute)
	{
		createRoute(theConfig, theRoute); 
		createStation(theConfig, theRoute); 
		createTrains(theConfig, theRoute); 
		createPassengers(theConfig, theRoute); 
	}
	
	public static void run(Configuration theConfig, RouteSpec theRouteSpec)
	{
		for(int i = 0; i < theConfig.getTicks(); i++)
		{
			//create more passengers
			//move the trains
			//see if passengers on train need to get off
			//take those passengers off the train
			//repeat?
			createPassengers(theConfig, theRouteSpec); 
		}
	}
	
	/**
	 * @param args -unused-
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Configuration theConfig = new Configuration();
		RouteSpec theRouteSpec = theConfig.getRoute();
		TrainRoute theRoute = new TrainRoute(theRouteSpec);
		
		setUp(theConfig, theRoute); 
		run(theConfig, theRouteSpec); 
		
	}

}
