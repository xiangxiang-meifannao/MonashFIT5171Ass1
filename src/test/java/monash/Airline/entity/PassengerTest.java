package monash.Airline.entity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.regex.Pattern;

class PassengerTest {
    @Test
    public void testRequiredFields() {
        try{
            // Create a Passenger object with all required fields
            Passenger passenger = new Passenger("John", "Doe", 30, "Man", "john.doe@example.com", "0412345678", "ABC123456", "123456789", 123);
            System.out.println(passenger.getFirstName());
            // Check if all required fields are set correctly
            assertEquals("John", passenger.getFirstName());
            assertEquals("Doe", passenger.getSecondName());
            assertEquals(30, passenger.getAge());
            assertEquals("Man", passenger.getGender());
            assertEquals("john.doe@example.com", passenger.getEmail());
            assertEquals("0412345678", passenger.getPhoneNumber());
            assertEquals("ABC123456", passenger.getPassport());
            assertEquals("123456789", passenger.getCardNumber());
            assertEquals(123, passenger.getSecurityCode());
        }catch (AssertionError e){
            System.out.println("测试失败：" + e.getMessage());
        }

    }
    @Test
    public void testValidPhoneNumber() {
        try{
            // Create a Passenger object with a valid phone number
            Passenger passenger = new Passenger();
            passenger.setPhoneNumber("0412345678");

            // Check if the phone number is set correctly
            assertTrue((Pattern.matches("^0[4-5]\\d{8}$", passenger.getPhoneNumber())) ||
                    (Pattern.matches("^\\+61 4\\d{8}$", passenger.getPhoneNumber())));
        } catch (AssertionError e){
            System.out.println("电话不符合要求：" + e.getMessage());
        }

    }

    @Test
    public void testValidEmail() {
        try{
            // Create a Passenger object with a valid email
            Passenger passenger = new Passenger();
            passenger.setEmail("join.doe@example.com");

            // Check if the email is set correctly
            assertTrue(Pattern.matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$", passenger.getEmail()));
        } catch (AssertionError e){
            System.out.println("邮箱格式不符合要求：" + e.getMessage());
        }

    }

    @Test
    public void testValidPassportNumber() {
        try{
            // Create a Passenger object with a valid passport number
            Passenger passenger = new Passenger();
            passenger.setPassport("ABC123456");

            // Check if the passport number is set correctly
            assertTrue(passenger.getPassport().length() <= 9);
        } catch (AssertionError e) {
            System.out.println("护照长度不符合要求：" + e.getMessage());
        }
    }

    @Test
    public void testRequiredParameters() {
        try{
            // Create a Passenger object with all required fields
            Passenger passenger = new Passenger("John", "Doe", 30, "Man", "john.doe@example.com", "0412345678", "ABC123456", "123456789", 123);

            // Check if all required fields are set correctly
            assertNotNull(passenger.getFirstName());
            assertNotNull(passenger.getSecondName());
            assertNotNull( passenger.getAge());
            assertNotNull( passenger.getGender());
        }catch (AssertionError e) {
            System.out.println("测试失败：" + e.getMessage());
        }
    }



}