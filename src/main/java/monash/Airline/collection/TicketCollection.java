package monash.Airline.collection;

import monash.Airline.dao.TicketDao;
import monash.Airline.entity.Flight;
import monash.Airline.entity.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketCollection {
	
	public static ArrayList<Ticket> tickets;
	public static TicketDao ticketDao;

	public static ArrayList<Ticket> getTickets() {
		return tickets;
	}

	public static void addTickets(ArrayList<Ticket> tickets_db) {
		TicketCollection.tickets.addAll(tickets_db);
	}
	
	public static void getAllTickets() {
    	//display all available tickets from the Ticket collection
		ArrayList<Ticket> allTickets = ticketDao.getAllTickets();
		for (Ticket ticket : allTickets) {
			System.out.println(ticket.toString());
		}
	}
	public static Ticket getTicketInfo(int ticket_id) {
    	//SELECT a ticket where ticket id = ticket_id
		Ticket ticket = ticketDao.getTicketByID(ticket_id);
		return ticket;
    }
}
