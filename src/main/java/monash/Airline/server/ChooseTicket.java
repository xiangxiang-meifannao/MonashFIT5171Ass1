package monash.Airline.server;
import monash.Airline.collection.FlightCollection;
import monash.Airline.collection.TicketCollection;
import monash.Airline.entity.Flight;
import monash.Airline.entity.Ticket;

import java.util.ArrayList;
import java.util.Scanner;

public class ChooseTicket{
   
	BuyTicket buyTicket = new BuyTicket();
    
	Scanner in = new Scanner(System.in);

    public boolean chooseTicket(String city1, String city2) throws Exception
    {
    	int counter = 1;
    	int idFirst = 0;
        int idSecond = 0;
    	
        Flight flight = new Flight();

        //search for direct flight from city1 to city2
        
        flight = FlightCollection.getFlightInfo(city1, city2);
        
        
        if(flight != null) {

			ArrayList<Ticket> tickets = TicketCollection.getAllTickets();
			for (Ticket ticket : tickets) {
				System.out.println("ticket_id=" + ticket.getTicket_id() +
						", price=" + ticket.getPrice() +
						", date=" + ticket.getFlight().getDateTo() +
						", classVip=" + ticket.getClassVip());
			}
			System.out.println("\nEnter ID of ticket you want to choose:");

	        int ticket_id = in.nextInt();
	        
	        //validate ticker here
			for (Ticket ticket : tickets) {
				if (ticket.getTicket_id() == ticket_id){
					//buy ticket here
					buyTicket.buyTicket(ticket_id);
					return true;
				}
			}
        }
        else
            //in case there is no direct ticket from city1 to city2
        {
        	//SELECT a flight where depart_to = city2
        	
        	Flight depart_to = FlightCollection.getFlightInfo(city2);
        	
        	//and search for city with depart_from as connector city

        	String connectCity = depart_to.getDepartFrom();
        	
        	//SELECT * from flight where depart_to = '" + connectCity + "' and depart_from = '" + city1+"'"
        	
        	Flight flightConnectingTwoCities = FlightCollection.getFlightInfo(city1, connectCity);
        	
        	if(flightConnectingTwoCities != null){
        		
        		System.out.println("There is special way to go there. And it is transfer way, like above. Way №" + counter);
         
        		idFirst = depart_to.getFlightID();
        	
        		idSecond = flightConnectingTwoCities.getFlightID();
        	
          
            }
        	
        	counter++;
            
        	buyTicket.buyTicket(idFirst, idSecond); //pass two tickets and buy them
            
        	if(counter == 1)
            {
                System.out.println("There is no possible variants.");
				return false;
            }
            return true;
        }

		return false;
	}
	
}