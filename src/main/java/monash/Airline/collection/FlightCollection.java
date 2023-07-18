package monash.Airline.collection;

import monash.Airline.dao.FlightDao;
import monash.Airline.entity.Flight;

import java.util.ArrayList;

public class FlightCollection {
	
	public static ArrayList<Flight> flights;
	public static FlightDao flightDao;

	public static ArrayList<Flight> getFlights() {
		return flights;
	}

	public static void addFlights(ArrayList<Flight> flights) {
		FlightCollection.flights.addAll(flights);
	}
	
	public static Flight getFlightInfo(String city1, String city2) {
    	//display the flights where there is a direct flight from city 1 to city2
		flights = flightDao.getFlightsByDepartFromAndDepartTo(city1, city2);
		if (flights != null && !flights.isEmpty())
			return flights.get(0);
		return null;
    }
    
    public static Flight getFlightInfo(String city) {
    	//SELECT a flight where depart_to = city
		flights = flightDao.getFlightsByDepartTo(city);
		if (flights != null && !flights.isEmpty())
			return flights.get(0);
		return null;
    }

    public static Flight getFlightInfo(int flight_id) {
    	//SELECT a flight with a particular flight id
		Flight result = flightDao.getFlightByID(flight_id);
    	return result;
    }
    

}
