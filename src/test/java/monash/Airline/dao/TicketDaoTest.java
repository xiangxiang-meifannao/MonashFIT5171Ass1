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
        assertEquals(3, tickets.size());

        // 可以进一步对返回的票据列表进行更详细的断言
        // 使用assertEquals比较每个Ticket对象的属性值是否符合预期
        Ticket ticket1 = tickets.get(0);
        assertEquals(1, ticket1.getTicket_id());
        assertEquals(100, ticket1.getPrice());
        // ... 其他属性的断言

        Ticket ticket2 = tickets.get(1);
        assertEquals(2, ticket2.getTicket_id());
        assertEquals(200, ticket2.getPrice());
        // ...

        Ticket ticket3 = tickets.get(2);
        assertEquals(3, ticket3.getTicket_id());
        assertEquals(300, ticket3.getPrice());
        // ...
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

        // 可以进一步对返回的票据进行更详细的断言
        // 使用assertEquals比较每个属性值是否符合预期
        assertEquals(100, ticket.getPrice());
        // ... 其他属性的断言
    }

    @Test
    public void testUpdateTicket() {
        // 创建一个TicketDao对象
        TicketDao ticketDao = new TicketDao();

        // 假设要测试的票据信息
        Ticket ticket = new Ticket(1, 150, new Flight(), true, new Passenger());

        // 调用updateTicket方法更新票据
        ticketDao.updateTicket(ticket);

        // 可以根据需要编写额外的断言来验证更新操作的结果
        // 例如，可以再次调用getTicketByID方法来获取指定ID的票据，
        // 然后使用assertEquals比较属性值是否已更新为预期值
        Ticket updatedTicket = ticketDao.getTicketByID(1);
        assertEquals(150, updatedTicket.getPrice());
        // ... 其他属性的断言
    }
}


