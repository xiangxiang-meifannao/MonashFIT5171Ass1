package monash.Airline.dao;

import monash.Airline.entity.Airplane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AirplaneDaoTest {
    private AirplaneDao airlineDao;

    @BeforeEach
    void setUp() {
        airlineDao = new AirplaneDao();
    }

    @Test
    void testGetAllAirplanes() {
        try {
            List<Airplane> airplanes = airlineDao.getAllAirplanes();
            assertNotNull(airplanes);
            assertTrue(airplanes.size() > 0);
        } catch (SQLException e) {
            fail("发生SQL异常: " + e.getMessage());
        }
    }

    @Test
    void testGetAirplaneByID() {
        int airplaneID = 1;
        try {
            Airplane airplane = airlineDao.getAirplaneByID(airplaneID);
            assertNotNull(airplane);
            assertEquals(airplaneID, airplane.getAirplaneID());
        } catch (SQLException e) {
            fail("发生SQL异常: " + e.getMessage());
        }
    }

    @Test
    void testAddAndDeleteAirplane() {
        int airplaneID = 100;
        Airplane airplane = new Airplane(airplaneID, "Test", 20, 150, 8);

        try {
            // Add airplane
            airlineDao.addAirplane(airplane);

            // Verify that the airplane is added
            Airplane retrievedAirplane = airlineDao.getAirplaneByID(airplaneID);
            assertNotNull(retrievedAirplane);
            assertEquals(airplaneID, retrievedAirplane.getAirplaneID());

            // Update airplane
            airplane.setAirplaneModel("Updated Model");
            airlineDao.updateAirplane(airplane);

            // Verify that the airplane is updated
            retrievedAirplane = airlineDao.getAirplaneByID(airplaneID);
            assertNotNull(retrievedAirplane);
            assertEquals("Updated Model", retrievedAirplane.getAirplaneModel());

        } catch (SQLException e) {
            fail("发生SQL异常: " + e.getMessage());
        }
    }
}
