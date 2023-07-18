package monash.Airline.collection;

import monash.Airline.dao.FlightDao;
import monash.Airline.entity.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FlightCollectionTest {

    private ArrayList<Flight> mockFlights;
    private FlightDao mockFlightDao;

    @BeforeEach
    public void setUp() {
        mockFlights = new ArrayList<>();
        mockFlightDao = new FlightDao() {
            @Override
            public ArrayList<Flight> getAllFlights() {
                return mockFlights;
            }

            @Override
            public ArrayList<Flight> getFlightsByDepartFromAndDepartTo(String departFrom, String departTo) {
                return mockFlights;
            }

            @Override
            public ArrayList<Flight> getFlightsByDepartTo(String departTo) {
                return mockFlights;
            }

            @Override
            public Flight getFlightByID(int flightID) {
                return null;
            }
        };
        FlightCollection.flights = mockFlights;
        FlightCollection.flightDao = mockFlightDao;
    }

    @Test
    public void testGetFlights() {
        // 调用被测试的方法
        ArrayList<Flight> flights = FlightCollection.getFlights();

        // 使用断言验证返回的航班列表与mock对象一致
        assertEquals(mockFlights, flights);
    }

    @Test
    public void testAddFlights() {
        // 创建要添加的航班列表
        ArrayList<Flight> flightsToAdd = new ArrayList<>();
        Flight flight1 = new Flight();
        Flight flight2 = new Flight();
        flightsToAdd.add(flight1);
        flightsToAdd.add(flight2);

        // 调用被测试的方法
        FlightCollection.addFlights(flightsToAdd);

        // 使用断言验证航班列表已成功添加到FlightCollection.flights中
        assertTrue(FlightCollection.flights.containsAll(flightsToAdd));
    }

    @Test
    public void testGetFlightInfoByCities() {
        String city1 = "City1";
        String city2 = "City2";
        Flight expectedFlight = new Flight();

        // 设置mock返回值
        mockFlights.add(expectedFlight);

        // 调用被测试的方法
        Flight flight = FlightCollection.getFlightInfo(city1, city2);

        // 使用断言验证返回的航班与mock对象一致
        assertEquals(expectedFlight, flight);
    }

    @Test
    public void testGetFlightInfoByCity() {
        String city = "City";
        Flight expectedFlight = new Flight();

        // 设置mock返回值
        mockFlights.add(expectedFlight);

        // 调用被测试的方法
        Flight flight = FlightCollection.getFlightInfo(city);

        // 使用断言验证返回的航班与mock对象一致
        assertEquals(expectedFlight, flight);
    }

    @Test
    public void testGetFlightInfoByID() {
        int flightID = 1;
        Flight expectedFlight = new Flight();

        // 设置mock返回值
        mockFlightDao = new FlightDao() {
            @Override
            public Flight getFlightByID(int flightID) {
                return expectedFlight;
            }
        };
        FlightCollection.flightDao = mockFlightDao;

        // 调用被测试的方法
        Flight flight = FlightCollection.getFlightInfo(flightID);

        // 使用断言验证返回的航班与mock对象一致
        assertEquals(expectedFlight, flight);
    }
}
