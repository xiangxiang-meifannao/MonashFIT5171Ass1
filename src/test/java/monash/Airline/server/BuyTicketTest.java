package monash.Airline.server;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class BuyTicketTest {

    @Test
    public void testBuyTicketDirectFlight() {
        // 创建BuyTicket的实例
        BuyTicket<Object> buyTicket = new BuyTicket<>();

        try {
            // 使用有效的直达航班票号调用buyTicket方法
            int ticketId = 1;

            // 模拟用户输入
//            String userInput = "Touch\nLee\n29\nFemale\njohn.doe@example.com\n1234567890\nABC123456\n1\n";
//            InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
//            System.setIn(inputStream);

            // 调用buyTicket方法
            boolean isPurchased = buyTicket.buyTicket(ticketId);

            // 断言票是否成功购买
            assertTrue(isPurchased, "直达航班票购买失败");

        } catch (Exception e) {
            fail("发生异常：" + e.getMessage());
        } finally {
            // 重置System.in
            System.setIn(System.in);
        }
    }

    @Test
    public void testBuyTicketTransferFlight() {
        // 创建BuyTicket的实例
        BuyTicket<Object> buyTicket = new BuyTicket<>();

        try {
            // 使用有效的中转航班票号调用buyTicket方法
            int ticketIdFirst = 1;
            int ticketIdSecond = 2;

            // 模拟用户输入
//            String userInput = "John\nDoe\n25\nMale\njohn.doe@example.com\n1234567890\nABC123456\n1\n";
//            InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
//            System.setIn(inputStream);

            // 调用buyTicket方法
            boolean arePurchased = buyTicket.buyTicket(ticketIdFirst, ticketIdSecond);

            // 断言两张票是否成功购买
            assertTrue(arePurchased, "中转航班票购买失败");

        } catch (Exception e) {
            fail("发生异常：" + e.getMessage());
        } finally {
            // 重置System.in
            System.setIn(System.in);
        }
    }
}


