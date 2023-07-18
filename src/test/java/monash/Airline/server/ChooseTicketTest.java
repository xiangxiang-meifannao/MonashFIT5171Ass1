package monash.Airline.server;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChooseTicketTest {

    @Test
    public void testChooseDirectTicket() {
        // 创建ChooseTicket的实例
        ChooseTicket chooseTicket = new ChooseTicket();
        BuyTicket buyTicket = new BuyTicket();

        try {
            // 使用有效的城市名称调用chooseTicket方法
            String city1 = "CityA";
            String city2 = "CityB";

            // 设置模拟输入值
            String userInput = "1\n";
            InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
            System.setIn(inputStream);

            // 调用chooseTicket方法
            boolean isChosen = chooseTicket.chooseTicket(city1, city2);

            // 断言选择票是否成功
            assertTrue(isChosen, "直达航班票选择失败");

        } catch (Exception e) {
            fail("发生异常：" + e.getMessage());
        } finally {
            // 重置System.in
            System.setIn(System.in);
        }
    }

    @Test
    public void testChooseTransferTicket() {
        // 创建ChooseTicket的实例
        ChooseTicket chooseTicket = new ChooseTicket();
        BuyTicket buyTicket = new BuyTicket();

        try {
            // 使用无效的城市名称调用chooseTicket方法
            String city1 = "CityA";
            String city2 = "CityC";

            // 设置模拟输入值
            String userInput = "1\n";
            InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
            System.setIn(inputStream);

            // 调用chooseTicket方法
            boolean isChosen = chooseTicket.chooseTicket(city1, city2);

            // 断言选择票是否成功
            assertTrue(isChosen, "中转航班票选择失败");

        } catch (Exception e) {
            fail("发生异常：" + e.getMessage());
        } finally {
            // 重置System.in
            System.setIn(System.in);
        }
    }
}

