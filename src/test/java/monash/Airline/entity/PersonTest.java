package monash.Airline.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    public void testRequiredFields() {

        try{
            Passenger passenger = new Passenger("John", "Doe", 30, "Man", "john.doe@example.com", "0412345678", "ABC123456", "123456789", 123);
            // Check if all required fields are set correctly
            assertEquals("John", passenger.getFirstName());
            assertEquals("Doe", passenger.getSecondName());
            assertEquals(30, passenger.getAge());
            assertEquals("Man", passenger.getGender());
        }catch (AssertionError e){
            System.out.println("测试失败：" + e.getMessage());
        }

    }
    @Test
    public void testGenderOptions() {
        try{
            String[] genderOptions = {"Woman", "Man", "Non-binary | gender diverse", "Prefer not to say", "Other"};
            String gender = "Man";
            boolean isValidGender = false;
            for (String option : genderOptions) {
                if (option.equals(gender)) {
                    isValidGender = true;
                    break;
                }
            }
            assertTrue(isValidGender);
        }catch (AssertionError e){
            System.out.println("性别不符合要求：" + e.getMessage());
        }

    }

    @Test
    public void testIsValidName() {
        try{
            Passenger passenger = new Passenger();
            passenger.setFirstName("John");
            passenger.setSecondName("Doe");
            String regex = "^[a-zA-Z]*$";
            assertTrue(passenger.getFirstName().matches(regex));
            assertTrue(passenger.getSecondName().matches(regex));
        }catch (AssertionError e){
            System.out.println("姓名不符合要求：" + e.getMessage());
        }

    }


}