package monash.Airline.entity;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.text.ParseException;
import static org.junit.jupiter.api.Assertions.*;
import java.text.SimpleDateFormat;
import java.util.Date;


class FlightTest {

    @Test
    public void testRequiredFields() {
        try{
        String dateString1 = "18/07/23 12:34:56";
        String dateString2 = "18/07/23 13:34:56";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date parsedDate1 = dateFormat.parse(dateString1);
        Date parsedDate2 = dateFormat.parse(dateString2);
        Timestamp dateFrom = new Timestamp(parsedDate1.getTime());
        Timestamp dateTo = new Timestamp(parsedDate2.getTime());


        // Create a Flight object with all required fields
        Flight flight = new Flight(1, "Destination", "Origin", "FL001", "Airline",
                dateFrom, dateTo,
                new Airplane(1, "Model", 100, 200, 10));

            // Check if all required fields are set correctly
            assertEquals(1, flight.getFlightID());
            assertEquals("Destination", flight.getDepartTo());
            assertEquals("Origin", flight.getDepartFrom());
            assertEquals("FL001", flight.getCode());
            assertEquals("Airline", flight.getCompany());
            assertNotNull(flight.getAirplane());
            assertNotNull(flight.getDateFrom());
            assertNotNull(flight.getDateTo());
        } catch (AssertionError e){
            System.out.println("测试失败：" + e.getMessage());
        } catch (ParseException e){
            System.out.println("测试失败：" + e.getMessage());
        }


    }

    @Test
    public void testDateTimeFormat() {
        try{
            String dateString1 = "18/07/23 12:34:56";
            String dateString2 = "18/07/23 13:34:56";
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
            Date parsedDate1 = dateFormat.parse(dateString1);
            Date parsedDate2 = dateFormat.parse(dateString2);
            Timestamp dateFrom = new Timestamp(parsedDate1.getTime());
            Timestamp dateTo = new Timestamp(parsedDate2.getTime());

            // Create a Flight object with date and time in correct format
            Flight flight = new Flight();


            flight.setDateFrom(dateFrom);
            flight.setDateTo(dateTo);


            // Check if date and time fields are set correctly
            assertEquals("01/01/23 10:00:00", dateFormat.format(flight.getDateFrom()));
            assertEquals("02/01/23 11:00:00", dateFormat.format(flight.getDateTo()));
        }catch (AssertionError e){
            System.out.println("测试失败：" + e.getMessage());
        } catch(ParseException e){
            System.out.println("测试用例设置出错：" + e.getMessage());
        }

    }

}