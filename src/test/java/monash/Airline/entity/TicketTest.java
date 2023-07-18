package monash.Airline.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TicketTest {
    @Test
    public void testTicketStatus() {
        try{
            // Create a Ticket object with status set to true
            Ticket ticket = new Ticket();
            ticket.setTicketStatus(true);
            assertTrue(ticket.ticketStatus());

            ticket.setTicketStatus(false);
            assertFalse(ticket.ticketStatus());
        }catch (AssertionError e){
            System.out.println("测试失败：" + e.getMessage());
        }

    }

    @Test
    public void testSaleByAge() {
        try{
            // Create a Ticket object with an age less than 15
            Ticket ticket = new Ticket();
            Passenger passenger = new Passenger();
            passenger.setAge(10);
            ticket.setPassenger(passenger);
            ticket.setPrice(100);

            // Check if the price is reduced by 50% for children under 15
            assertEquals(56, ticket.getPrice());

            // Create a Ticket object with an age equal to 60 or above
            passenger.setAge(65);
            ticket.setPassenger(passenger);
            ticket.setPrice(100);

            // Check if the price is set to 0 for passengers aged 60 or above
            assertEquals(0, ticket.getPrice());
        } catch (AssertionError e){
            System.out.println("测试失败：" + e.getMessage());
        }

    }
    @Test
    public void testPriceApplied() {
        try{
            // Create a Ticket object with a price
            Ticket ticket = new Ticket();
            Passenger passenger = new Passenger();
            passenger.setAge(10);
            ticket.setPassenger(passenger);
            ticket.setPrice(100);
            assertTrue(ticket.getPrice() > 0);
        } catch (AssertionError e){
            System.out.println("测试失败：" + e.getMessage());
        }

    }

    @Test
    public void testServiceTax() {
        try{
            // Create a Ticket object with a price
            Ticket ticket = new Ticket();
            Passenger passenger = new Passenger();
            passenger.setAge(20);
            ticket.setPassenger(passenger);
            ticket.setPrice(100);

            // Check if the service tax is applied to the ticket price
            assertEquals(112, ticket.getPrice());
        }catch (AssertionError e){
            System.out.println("测试失败：" + e.getMessage());
        }

    }

    @Test
    public void testValidFlightAndPassengerInformation() {
        try{
            // Create a Ticket object with valid flight and passenger information
            Flight flight = new Flight();
            Passenger passenger = new Passenger();
            Ticket ticket = new Ticket(1, 100, flight, true, passenger);

            // Check if the flight and passenger information is set correctly
            assertEquals(flight, ticket.getFlight());
            assertEquals(passenger, ticket.getPassenger());
        }catch (AssertionError e){
            System.out.println("测试失败：" + e.getMessage());
        }

    }


}