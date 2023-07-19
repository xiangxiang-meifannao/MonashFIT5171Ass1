package monash.Airline.dao;

import monash.Airline.entity.Airplane;
import monash.Airline.entity.Flight;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.ArrayList;

public class FlightDaoTest {

    @Test
    public void testAddFlight() {
        FlightDao flightDao = new FlightDao();
        // 创建一个Flight对象并设置相应的属性
        Flight flight = new Flight();
        flight.setDepartTo("Destination");
        flight.setDepartFrom("Origin");
        flight.setCode("FL001");
        flight.setCompany("Example Airlines");
        flight.setDateFrom(new Timestamp(System.currentTimeMillis()));
        flight.setDateTo(new Timestamp(System.currentTimeMillis() + 3600000)); // 添加1小时航班时长
        Airplane airplane = new Airplane(); // 根据需要设置对应的Airplane对象
        flight.setAirplane(airplane);

        // 添加航班
        int flightID = flightDao.addFlight(flight);

        // 查询添加的航班是否存在
        Flight addedFlight = flightDao.getFlightByID(flightID);

        // 进行断言，确保添加成功或进行其他适当的验证
        Assertions.assertNotNull(addedFlight);
        Assertions.assertEquals("Destination", addedFlight.getDepartTo());
        Assertions.assertEquals("Origin", addedFlight.getDepartFrom());
        // 其他适当的断言
    }

    @Test
    public void testGetAllFlights() {
        FlightDao flightDao = new FlightDao();
        ArrayList<Flight> flights = flightDao.getAllFlights();

        // 进行断言，确保获取到的航班列表不为空
        Assertions.assertNotNull(flights);
        // 其他适当的断言
    }

    @Test
    public void testGetFlightByID() {
        FlightDao flightDao = new FlightDao();
        int flightID = 1; // 根据需要设置要查询的航班ID

        Flight flight = flightDao.getFlightByID(flightID);
        // 进行断言，确保获取到的航班不为null
        Assertions.assertNotNull(flight);
        Assertions.assertEquals(1, flight.getFlightID());
    }

    @Test
    public void testGetFlightsByDepartTo() {
        FlightDao flightDao = new FlightDao();
        String departTo = "BeiJing"; // 根据需要设置要查询的目的地

        ArrayList<Flight> flights = flightDao.getFlightsByDepartTo(departTo);
        // 进行断言，确保获取到的航班列表不为空
        Assertions.assertNotNull(flights);
        Assertions.assertEquals("BeiJing", flights.get(0).getDepartTo());
    }

    @Test
    public void testGetFlightsByDepartFromAndDepartTo() {
        FlightDao flightDao = new FlightDao();
        String departFrom = "Shanghai"; // 根据需要设置要查询的起始地
        String departTo = "BeiJing"; // 根据需要设置要查询的目的地

        ArrayList<Flight> flights = flightDao.getFlightsByDepartFromAndDepartTo(departFrom, departTo);

        // 进行断言，确保获取到的航班列表不为空
        Assertions.assertNotNull(flights);
        Assertions.assertEquals("BeiJing", flights.get(0).getDepartTo());
        Assertions.assertEquals("ShangHai", flights.get(0).getDepartFrom());
    }



    @Test
    public void testUpdateFlight() {
        FlightDao flightDao = new FlightDao();
        // 创建一个Flight对象并设置相应的属性
        Flight flight = new Flight();
        flight.setFlightID(6); // 根据需要设置要更新的航班ID
        flight.setDepartTo("New Destination");
        flight.setDepartFrom("New Origin");
        flight.setCode("FL001");
        flight.setCompany("Example Airlines");
        flight.setDateFrom(new Timestamp(System.currentTimeMillis()));
        flight.setDateTo(new Timestamp(System.currentTimeMillis() + 3600000)); // 添加1小时航班时长
        Airplane airplane = new Airplane(); // 根据需要设置对应的Airplane对象
        flight.setAirplane(airplane);

        // 更新航班
        flightDao.updateFlight(flight);

        // 查询更新后的航班是否正确
        Flight updatedFlight = flightDao.getFlightByID(flight.getFlightID());

        // 进行断言，确保更新成功或进行其他适当的验证
        Assertions.assertNotNull(updatedFlight);
        Assertions.assertEquals("New Destination", updatedFlight.getDepartTo());
        Assertions.assertEquals("New Origin", updatedFlight.getDepartFrom());
    }
}

