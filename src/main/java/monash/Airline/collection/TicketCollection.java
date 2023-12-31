package monash.Airline.collection;

import monash.Airline.dao.TicketDao;
import monash.Airline.entity.Ticket;

import java.util.ArrayList;


public class TicketCollection {
	
	public static ArrayList<Ticket> tickets;
	public static TicketDao ticketDao = new TicketDao();;

	public static ArrayList<Ticket> getTickets() {
		return tickets;
	}

	public static void addTickets(ArrayList<Ticket> tickets_db) {
		TicketCollection.tickets.addAll(tickets_db);
	}
	
	public static ArrayList<Ticket> getAllTickets() {
    	//display all available tickets from the Ticket collection
		ArrayList<Ticket> allTickets = ticketDao.getAllTickets();
		return allTickets;
	}
	public static Ticket getTicketInfo(int ticket_id) {
    	//SELECT a ticket where ticket id = ticket_id
		Ticket ticket = ticketDao.getTicketByID(ticket_id);
		return ticket;
    }
}
