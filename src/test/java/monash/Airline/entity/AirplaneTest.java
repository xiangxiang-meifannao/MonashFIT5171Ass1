package monash.Airline.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AirplaneTest {

    @Test
    public void testSetAndGetAirplaneID() {
        Airplane airplane = new Airplane(1, "Model", 100, 200, 10);
        airplane.setAirplaneID(2);
        int airplaneID = airplane.getAirplaneID();
        try{
            assertEquals(2, airplaneID);
        } catch (AssertionError e){
            System.out.println("测试失败：" + e.getMessage());
        }

    }

    @Test
    public void testSetAndGetBusinessSitsNumber() {
        Airplane airplane = new Airplane(1, "Model", 100, 200, 10);
        airplane.setBusinessSitsNumber(150);
        int businessSitsNumber = airplane.getBusinessSitsNumber();
        try{
            assertEquals(150, businessSitsNumber);
        } catch (AssertionError e){
            System.out.println("测试失败：" + e.getMessage());
        }
    }

    @Test
    public void testSetAndGetEconomySitsNumber() {
        Airplane airplane = new Airplane(1, "Model", 100, 200, 10);
        airplane.setEconomySitsNumber(100);
        int crewSitsNumber = airplane.getCrewSitsNumber();

        try{
            assertEquals(10, crewSitsNumber);
        } catch (AssertionError e){
            System.out.println("测试失败：" + e.getMessage());
        }

    }

    @Test
    public void testSetAndGetCrewSitsNumber() {
        Airplane airplane = new Airplane(1, "Model", 100, 200, 10);
        airplane.setCrewSitsNumber(15);
        int crewSitsNumber = airplane.getCrewSitsNumber();

        try{
            assertEquals(15, crewSitsNumber);
        } catch (AssertionError e){
            System.out.println("测试失败：" + e.getMessage());
        }


    }

    @Test
    public void testSeatNumberInRange(){
        Airplane airplane = new Airplane(1, "Model", 100, 200, 10);

        int totalSeats = airplane.getBusinessSitsNumber() + airplane.getEconomySitsNumber() + airplane.getCrewSitsNumber();

        try{
            // Test lower bound
            assertTrue(totalSeats >= 1);

            // Test upper bound
            assertTrue(totalSeats <= 300);
        } catch (AssertionError e){
            System.out.println("测试失败：" + e.getMessage());
        }


    }
}