package monash.Airline;

import monash.Airline.server.ChooseTicket;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.fail;

public class TicketSystemTest {

    @Test
    public void testTicketSystem() {
        // 创建ChooseTicket的实例
        ChooseTicket chooseTicket = new ChooseTicket();

        try {
            // 设置模拟输入值
            String userInput = "CityA\nCityB\n1\n";
            InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
            System.setIn(inputStream);

            // 调用TicketSystem的main方法
            TicketSystem.main(new String[]{});

        } catch (Exception e) {
            fail("发生异常：" + e.getMessage());
        } finally {
            // 重置System.in
            System.setIn(System.in);
        }
    }
}
