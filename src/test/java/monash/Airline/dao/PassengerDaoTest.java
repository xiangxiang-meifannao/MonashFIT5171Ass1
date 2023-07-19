package monash.Airline.dao;

import monash.Airline.entity.Passenger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class PassengerDaoTest {

    @Test
    public void testGetAllPassengers() {
        PassengerDao passengerDao = new PassengerDao();
        ArrayList<Passenger> passengers = passengerDao.getAllPassengers();
        // 进行断言，确保获取到的乘客列表不为空
        Assertions.assertNotNull(passengers);
    }

    @Test
    public void testGetPassengerByID() {
        PassengerDao passengerDao = new PassengerDao();
        int passengerID = 1; // 根据需要设置要查询的乘客ID

        Passenger passenger = passengerDao.getPassengerByID(passengerID);

        // 进行断言，确保获取到的乘客不为null
        Assertions.assertNotNull(passenger);
    }

    @Test
    public void testAddPassenger() {
        PassengerDao passengerDao = new PassengerDao();
        // 创建一个Passenger对象并设置相应的属性
        Passenger passenger = new Passenger();
        passenger.setFirstName("John");
        passenger.setSecondName("Doe");
        passenger.setAge(30);
        passenger.setGender("Male");
        passenger.setEmail("john.doe@example.com");
        passenger.setPhoneNumber("1234567890");
        passenger.setCardNumber("1234567890123456");
        passenger.setSecurityCode(123);
        passenger.setPassport("AB123456");

        // 添加乘客
        int generatedID = passengerDao.addPassenger(passenger);

        // 进行断言，确保添加成功或进行其他适当的验证
        Assertions.assertNotEquals(-1, generatedID);
    }

}

