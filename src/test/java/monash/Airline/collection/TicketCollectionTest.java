package monash.Airline.collection;

import monash.Airline.dao.TicketDao;
import monash.Airline.entity.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;


public class TicketCollectionTest {


    @Test
    public void testGetAllTickets() {
        // 调用被测试的方法
        ArrayList<Ticket> allTickets = TicketCollection.getAllTickets();
        Ticket ticket = allTickets.get(0);
        // 使用断言验证返回的票据列表与mock对象一致
        assertEquals(1, ticket.getTicket_id());
        assertEquals(200, ticket.getPrice());
    }

    @Test
    public void testGetTicketInfo() {
        int ticketID = 1;

        // 调用被测试的方法
        Ticket ticket = TicketCollection.getTicketInfo(ticketID);

        // 使用断言验证返回的票据与mock对象一致
        assertEquals(ticketID, ticket.getTicket_id());
        assertEquals(200, ticket.getPrice());
        assertEquals(1, ticket.getPassenger().getPassengerID());
        assertEquals(1, ticket.getFlight().getFlightID());
    }
}


