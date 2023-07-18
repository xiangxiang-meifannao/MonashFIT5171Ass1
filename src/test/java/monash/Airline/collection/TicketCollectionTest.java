package monash.Airline.collection;

import monash.Airline.dao.TicketDao;
import monash.Airline.entity.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;


public class TicketCollectionTest {

    private ArrayList<Ticket> mockTickets;
    private TicketDao mockTicketDao;

    @BeforeEach
    public void setUp() {
        mockTickets = new ArrayList<>();
        mockTicketDao = new TicketDao() {
            @Override
            public ArrayList<Ticket> getAllTickets() {
                return mockTickets;
            }

            @Override
            public Ticket getTicketByID(int ticketID) {
                return null;
            }
        };
        TicketCollection.tickets = mockTickets;
        TicketCollection.ticketDao = mockTicketDao;
    }

    @Test
    public void testGetTickets() {
        // 调用被测试的方法
        ArrayList<Ticket> tickets = TicketCollection.getTickets();

        // 使用断言验证返回的票据列表与mock对象一致
        assertEquals(mockTickets, tickets);
    }

    @Test
    public void testAddTickets() {
        // 创建要添加的票据列表
        ArrayList<Ticket> ticketsToAdd = new ArrayList<>();
        Ticket ticket1 = new Ticket();
        Ticket ticket2 = new Ticket();
        ticketsToAdd.add(ticket1);
        ticketsToAdd.add(ticket2);

        // 调用被测试的方法
        TicketCollection.addTickets(ticketsToAdd);

        // 使用断言验证票据列表已成功添加到TicketCollection.tickets中
        assertTrue(TicketCollection.tickets.containsAll(ticketsToAdd));
    }

    @Test
    public void testGetAllTickets() {
        // 调用被测试的方法
        ArrayList<Ticket> allTickets = TicketCollection.getAllTickets();

        // 使用断言验证返回的票据列表与mock对象一致
        assertEquals(mockTickets, allTickets);
    }

    @Test
    public void testGetTicketInfo() {
        int ticketID = 1;
        Ticket expectedTicket = new Ticket();

        // 设置mock返回值
        mockTicketDao = new TicketDao() {
            @Override
            public Ticket getTicketByID(int ticketID) {
                return expectedTicket;
            }
        };
        TicketCollection.ticketDao = mockTicketDao;

        // 调用被测试的方法
        Ticket ticket = TicketCollection.getTicketInfo(ticketID);

        // 使用断言验证返回的票据与mock对象一致
        assertEquals(expectedTicket, ticket);
    }
}


