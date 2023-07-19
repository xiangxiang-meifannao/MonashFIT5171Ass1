package monash.Airline.dao;

import monash.Airline.entity.Flight;
import monash.Airline.entity.Passenger;
import monash.Airline.entity.Ticket;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TicketDaoTest {

    @Test
    public void testGetAllTickets() {
        // 创建一个TicketDao对象
        TicketDao ticketDao = new TicketDao();

        // 调用getAllTickets方法获取所有票据
        ArrayList<Ticket> tickets = ticketDao.getAllTickets();

        // 断言返回的票据列表不为空
        assertNotNull(tickets);

        // 断言返回的票据数量符合预期
        assertEquals(6, tickets.size());

        Ticket ticket1 = tickets.get(0);
        assertEquals(1, ticket1.getTicket_id());
        assertEquals(200, ticket1.getPrice());

        Ticket ticket2 = tickets.get(1);
        assertEquals(2, ticket2.getTicket_id());
        assertEquals(400, ticket2.getPrice());
    }

    @Test
    public void testGetTicketByID() {
        // 创建一个TicketDao对象
        TicketDao ticketDao = new TicketDao();

        // 假设要测试的票据ID为1
        int ticketID = 1;

        // 调用getTicketByID方法获取指定ID的票据
        Ticket ticket = ticketDao.getTicketByID(ticketID);
        // 断言返回的票据不为空
        assertNotNull(ticket);

        // 断言返回的票据的ID符合预期
        assertEquals(ticketID, ticket.getTicket_id());
        assertEquals(200, ticket.getPrice());
        assertEquals(1, ticket.getPassenger().getPassengerID());
        assertEquals(1, ticket.getFlight().getFlightID());
    }

    @Test
    public void testUpdateTicket() {
        // 创建一个TicketDao对象
        TicketDao ticketDao = new TicketDao();
        Flight flight = new Flight();
        flight.setFlightID(1);
        Passenger passenger = new Passenger();
        passenger.setPassengerID(1);

        // 假设要测试的票据信息
        Ticket ticket = new Ticket(1, 200, flight, true, passenger);

        // 调用updateTicket方法更新票据
        ticketDao.updateTicket(ticket);

        Ticket updatedTicket = ticketDao.getTicketByID(1);
        System.out.println(updatedTicket.getPassenger().toString());
        assertEquals(1, updatedTicket.getPassenger().getPassengerID());
        assertEquals(1, updatedTicket.getFlight().getFlightID());
    }
}


