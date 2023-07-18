package monash.Airline.dao;

import monash.Airline.entity.Airplane;
import monash.Airline.entity.Flight;
import monash.Airline.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


//用于实现对实体类Flight的相关数据库操作
public class FlightDao {
    public List<Flight> getAllFlights() {
        List<Flight> flights = new ArrayList<>();

        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "SELECT * FROM flight";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int flightID = resultSet.getInt("flightID");
                String departTo = resultSet.getString("departTo");
                String departFrom = resultSet.getString("departFrom");
                String code = resultSet.getString("code");
                String company = resultSet.getString("company");
                Timestamp dateFrom = resultSet.getTimestamp("dateFrom");
                Timestamp dateTo = resultSet.getTimestamp("dateTo");
                int airplaneID = resultSet.getInt("airplaneID");

                Airplane airplane = getAirplaneByID(airplaneID);

                Flight flight = new Flight(flightID, departTo, departFrom, code, company, dateFrom, dateTo, airplane);
                flights.add(flight);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flights;
    }

    public Flight getFlightByID(int flightID) {
        Flight flight = null;

        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "SELECT * FROM flight WHERE flightID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, flightID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String departTo = resultSet.getString("departTo");
                String departFrom = resultSet.getString("departFrom");
                String code = resultSet.getString("code");
                String company = resultSet.getString("company");
                Timestamp dateFrom = resultSet.getTimestamp("dateFrom");
                Timestamp dateTo = resultSet.getTimestamp("dateTo");
                int airplaneID = resultSet.getInt("airplaneID");

                Airplane airplane = getAirplaneByID(airplaneID);

                flight = new Flight(flightID, departTo, departFrom, code, company, dateFrom, dateTo, airplane);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flight;
    }

    public List<Flight> getFlightsByDepartTo(String departTo) {
        List<Flight> flights = new ArrayList<>();

        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "SELECT * FROM flight WHERE departTo = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, departTo);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int flightID = resultSet.getInt("flightID");
                String departFrom = resultSet.getString("departFrom");
                String code = resultSet.getString("code");
                String company = resultSet.getString("company");
                Timestamp dateFrom = resultSet.getTimestamp("dateFrom");
                Timestamp dateTo = resultSet.getTimestamp("dateTo");
                int airplaneID = resultSet.getInt("airplaneID");

                Airplane airplane = getAirplaneByID(airplaneID);

                Flight flight = new Flight(flightID, departTo, departFrom, code, company, dateFrom, dateTo, airplane);
                flights.add(flight);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flights;
    }

    public void addFlight(Flight flight) {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "INSERT INTO flight (departTo, departFrom, code, company, dateFrom, dateTo, airplaneID) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, flight.getDepartTo());
            statement.setString(2, flight.getDepartFrom());
            statement.setString(3, flight.getCode());
            statement.setString(4, flight.getCompany());
            statement.setTimestamp(5, flight.getDateFrom());
            statement.setTimestamp(6, flight.getDateTo());
            statement.setInt(7, flight.getAirplane().getAirplaneID());

            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateFlight(Flight flight) {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "UPDATE flight SET departTo = ?, departFrom = ?, code = ?, company = ?, dateFrom = ?, dateTo = ?, airplaneID = ? WHERE flightID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, flight.getDepartTo());
            statement.setString(2, flight.getDepartFrom());
            statement.setString(3, flight.getCode());
            statement.setString(4, flight.getCompany());
            statement.setTimestamp(5, flight.getDateFrom());
            statement.setTimestamp(6, flight.getDateTo());
            statement.setInt(7, flight.getAirplane().getAirplaneID());
            statement.setInt(8, flight.getFlightID());
            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Airplane getAirplaneByID(int airplaneID) {
        Airplane airplane = null;

        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "SELECT * FROM airplane WHERE airplaneID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, airplaneID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String airplaneModel = resultSet.getString("airplaneModel");
                int businessSitsNumber = resultSet.getInt("businessSitsNumber");
                int economySitsNumber = resultSet.getInt("economySitsNumber");
                int crewSitsNumber = resultSet.getInt("crewSitsNumber");

                airplane = new Airplane(airplaneID, airplaneModel, businessSitsNumber, economySitsNumber, crewSitsNumber);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return airplane;
    }
}
