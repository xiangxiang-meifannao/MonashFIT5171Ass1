package monash.Airline.collection;

import monash.Airline.entity.Flight;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class FlightCollectionTest {

    @Test
    public void testGetFlightInfoByCities() {
        String city1 = "ShangHai";
        String city2 = "BeiJing";

        // 调用被测试的方法
        Flight flight = FlightCollection.getFlightInfo(city1, city2);

        // 使用断言验证返回的航班一致
        assertEquals(1, flight.getFlightID());
        assertEquals("ShangHai", flight.getDepartFrom(), "出发城市不匹配");
        assertEquals("BeiJing", flight.getDepartTo(), "到达城市不匹配");
        assertEquals("1", flight.getCode(), "航班号不匹配");
        assertEquals("WHUT", flight.getCompany(), "航空公司不匹配");
    }

    @Test
    public void testGetFlightInfoByCity() {
        String city = "BeiJing";
        // 调用被测试的方法
        Flight flight = FlightCollection.getFlightInfo(city);

        // 使用断言验证返回的航班一致
        assertEquals(1, flight.getFlightID());
        assertEquals("ShangHai", flight.getDepartFrom(), "出发城市不匹配");
        assertEquals("BeiJing", flight.getDepartTo(), "到达城市不匹配");
        assertEquals("1", flight.getCode(), "航班号不匹配");
        assertEquals("WHUT", flight.getCompany(), "航空公司不匹配");

    }

    @Test
    public void testGetFlightInfoByID() {
        int flightID = 1;
        // 调用被测试的方法
        Flight flight = FlightCollection.getFlightInfo(flightID);

        // 使用断言验证返回的航班一致
        assertEquals(1, flight.getFlightID());
        assertEquals("ShangHai", flight.getDepartFrom(), "出发城市不匹配");
        assertEquals("BeiJing", flight.getDepartTo(), "到达城市不匹配");
        assertEquals("1", flight.getCode(), "航班号不匹配");
        assertEquals("WHUT", flight.getCompany(), "航空公司不匹配");

    }
}
