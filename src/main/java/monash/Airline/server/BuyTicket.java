package monash.Airline.server;

import monash.Airline.collection.FlightCollection;
import monash.Airline.collection.TicketCollection;
import monash.Airline.dao.AirplaneDao;
import monash.Airline.dao.PassengerDao;
import monash.Airline.dao.TicketDao;
import monash.Airline.entity.Airplane;
import monash.Airline.entity.Flight;
import monash.Airline.entity.Passenger;
import monash.Airline.entity.Ticket;

import java.util.*;
import java.util.regex.PatternSyntaxException;

public class BuyTicket <T>
{
    Passenger passenger = new Passenger();
    Ticket ticket = new Ticket();
    Flight flight = new Flight();
    Scanner in = new Scanner(System.in);

    AirplaneDao airplaneDao = new AirplaneDao();

    public BuyTicket()
    {
         passenger = new Passenger();
         ticket = new Ticket();
         flight = new Flight();
    }

    public void showTicket()
    {
        try
        {
            System.out.println("You have bought a ticket for flight " + ticket.getFlight().getDepartFrom() + " - " + ticket.getFlight().getDepartTo() + "\n\nDetails:");
            System.out.println(this.ticket.toString());
        }
        catch (NullPointerException e)
        {
            return;
        }
    }

    public boolean buyTicket(int ticket_id) throws Exception
        //method for buying one ticket with direct flight
    {
        int flight_id = 0;

        //select ticket where ticket_id="+ticket_id"
        Ticket validTicket = TicketCollection.getTicketInfo(ticket_id);
       
        //if there is a valid ticket id was input then we buy it, otherwise show message
        if(validTicket == null)
        {
            System.out.println("This ticket does not exist.");
            return false;
        }
        else{
        	//select flight_id from ticket where ticket_id=" + ticket_id
        
            flight_id = validTicket.getFlight().getFlightID();
            
            try
            {
                System.out.println("Enter your First Name: ");
                String firstName = in.nextLine();
                passenger.setFirstName(firstName);
                
                
                System.out.println("Enter your Second name:");
                String secondName = in.nextLine();
                passenger.setSecondName(secondName); //setting passengers info

                System.out.println("Enter your age:");
                Integer age = Integer.parseInt(in.nextLine());
                passenger.setAge(age);

                System.out.println("Enter your gender: ");
                String gender = in.nextLine();
                passenger.setGender(gender);

                System.out.println("Enter your e-mail address");
                String email = in.nextLine();
                passenger.setEmail(email);

                System.out.println("Enter your phone number (+7):");
                String phoneNumber = in.nextLine();
                passenger.setPhoneNumber(phoneNumber);

                System.out.println("Enter your passport number:");
                String passportNumber = in.nextLine();
                passenger.setPassport(passportNumber);

                System.out.println("Do you want to purchase?\n 1-YES 0-NO");
                int purch = Integer.parseInt(in.nextLine());
                if (purch == 0)
                    return false;

                flight = FlightCollection.getFlightInfo(flight_id);
                int airplane_id = flight.getAirplane().getAirplaneID();
                Airplane airplane = airplaneDao.getAirplaneByID(airplane_id);
                ticket = TicketCollection.getTicketInfo(ticket_id);

                if (ticket.getClassVip() == true)
                    airplane.setBusinessSitsNumber(airplane.getBusinessSitsNumber() - 1);
                else
                    airplane.setEconomySitsNumber(airplane.getEconomySitsNumber() - 1);

                ticket.setTicket_id(ticket_id);
                flight.setAirplane(airplane);
                ticket.setFlight(flight);
                ticket.setPrice(ticket.getPrice());
                ticket.setClassVip(ticket.getClassVip());
                ticket.setTicketStatus(true);

                System.out.println("Your bill: " + ticket.getPrice() + "\n");

                System.out.println("Enter your card number:");
                String cardNumber = in.nextLine();
                passenger.setCardNumber(cardNumber);

                System.out.println("Enter your security code:");
                Integer securityCode = Integer.parseInt(in.nextLine());
                passenger.setSecurityCode(securityCode);

                //更新数据
                if (passenger!=null){
                    PassengerDao passengerDao=new PassengerDao();
                    int id = passengerDao.addPassenger(passenger);
                    passenger.setPassengerID(id);
                    ticket.setPassenger(passenger);
                }
                if (airplane!=null){
                    AirplaneDao airplaneDao=new AirplaneDao();
                    airplaneDao.updateAirplane(airplane);
                }
                TicketDao ticketDao=new TicketDao();
                ticketDao.updateTicket(ticket);
                return true;
            } catch (PatternSyntaxException patternException)
            {
                patternException.printStackTrace();
            }
        }
        return false;
    }
    
	@SuppressWarnings("null")
	public boolean buyTicket(int ticket_id_first, int ticket_id_second) throws Exception{
		 //method for buying two tickets with transfer flight
        int flight_id_first = 0;
        
        int flight_id_second = 0;

        System.out.println(ticket_id_first + " " + ticket_id_second);
        
        Ticket validTicketfirst = TicketCollection.getTicketInfo(ticket_id_first);
        
        Ticket validTicketSecond = TicketCollection.getTicketInfo(ticket_id_first);
        
      
        System.out.println("Processing...");
        
        //if there is a valid ticket id was input then we buy it, otherwise show message
        
         if(validTicketfirst==null || validTicketSecond==null)
        {
            System.out.println("This ticket does not exist.");
            return false;
        }

        else
        {
        	flight_id_first = validTicketfirst.getFlight().getFlightID();
        	
        	flight_id_second = validTicketfirst.getFlight().getFlightID();

            try
            {
                System.out.println("Enter your First Name: ");
                String firstName = in.nextLine();
                passenger.setFirstName(firstName);


                System.out.println("Enter your Second name:");
                String secondName = in.nextLine();
                passenger.setSecondName(secondName); //setting passengers info

                System.out.println("Enter your age:");
                Integer age = Integer.parseInt(in.nextLine());
                passenger.setAge(age);

                System.out.println("Enter your gender: ");
                String gender = in.nextLine();
                passenger.setGender(gender);

                System.out.println("Enter your e-mail address");
                String email = in.nextLine();
                passenger.setEmail(email);

                System.out.println("Enter your phone number (+7):");
                String phoneNumber = in.nextLine();
                passenger.setPhoneNumber(phoneNumber);

                System.out.println("Enter your passport number:");
                String passportNumber = in.nextLine();
                passenger.setPassport(passportNumber);

                System.out.println("Do you want to purchase?\n 1-YES 0-NO");
                int purch = Integer.parseInt(in.nextLine());
                if (purch == 0)
                    return false;

              //  "select * from flight, airplane where flight_id=" + flight_id_first + " and flight.airplane_id=airplane.airplane_id");
                Flight flight_first = FlightCollection.getFlightInfo(flight_id_first);

                int airplane_id_first = flight_first.getAirplane().getAirplaneID();

                Airplane airplane_first = airplaneDao.getAirplaneByID(airplane_id_first);

                Flight flight_second = FlightCollection.getFlightInfo(flight_id_second);

                int airplane_id_second = flight_second.getAirplane().getAirplaneID();

                Airplane airplane_second  = airplaneDao.getAirplaneByID(airplane_id_second);

                Ticket ticket_first = TicketCollection.getTicketInfo(ticket_id_first);

                Ticket ticket_second = TicketCollection.getTicketInfo(ticket_id_second);

                if (ticket_first.getClassVip() == true)
                    airplane_first.setBusinessSitsNumber(airplane_first.getBusinessSitsNumber() - 1);
                else
                    airplane_first.setEconomySitsNumber(airplane_first.getEconomySitsNumber() - 1);

                if (ticket_second.getClassVip() == true)
                    airplane_second.setBusinessSitsNumber(airplane_second.getBusinessSitsNumber() - 1);
                else
                    airplane_second.setEconomySitsNumber(airplane_second.getEconomySitsNumber() - 1);

                System.out.println("--*-*-");

                ticket_first.setPassenger(passenger);
                ticket_first.setTicket_id(ticket_id_first);
                flight_first.setAirplane(airplane_first);
                ticket_first.setFlight(flight_first);
                ticket_first.setPrice(ticket_first.getPrice());
                ticket_first.setClassVip(ticket_first.getClassVip());
                ticket_first.setTicketStatus(true);

                ticket_second.setPassenger(passenger);
                ticket_second.setTicket_id(ticket_id_second);
                flight_second.setAirplane(airplane_second);
                ticket_second.setFlight(flight_first);
                ticket_second.setPrice(ticket_second.getPrice());
                ticket_second.setClassVip(ticket_second.getClassVip());
                ticket_second.setTicketStatus(true);

                ticket.setPrice(ticket_first.getPrice() + ticket_second.getPrice());

                System.out.println("Your bill: " + ticket.getPrice() + "\n");

                System.out.println("Enter your card number:");

                String cardNumber = in.nextLine();
                passenger.setCardNumber(cardNumber);

                System.out.println("Enter your security code:");
                Integer securityCode = Integer.parseInt(in.nextLine());
                passenger.setSecurityCode(securityCode);

                //更新数据
                if (passenger!=null){
                    PassengerDao passengerDao=new PassengerDao();
                    int id = passengerDao.addPassenger(passenger);
                    passenger.setPassengerID(id);
                    ticket_first.setPassenger(passenger);
                    ticket_second.setPassenger(passenger);
                }
                if (airplane_first!=null){
                    AirplaneDao airplaneDao=new AirplaneDao();
                    airplaneDao.updateAirplane(airplane_first);
                }
                if (airplane_second!=null){
                    AirplaneDao airplaneDao=new AirplaneDao();
                    airplaneDao.updateAirplane(airplane_second);
                }
                TicketDao ticketDao=new TicketDao();
                ticketDao.updateTicket(ticket_first);
                ticketDao.updateTicket(ticket_second);
                return true;

            } catch (PatternSyntaxException patternException)
            {
                patternException.printStackTrace();
            }
        }

        return false;
    }
}
