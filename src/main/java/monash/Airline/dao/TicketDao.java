package monash.Airline.dao;

import monash.Airline.entity.Flight;
import monash.Airline.entity.Passenger;
import monash.Airline.entity.Ticket;
import monash.Airline.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDao {
    public List<Ticket> getAllTickets() {
        List<Ticket> tickets = new ArrayList<>();

        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "SELECT * FROM ticket";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int ticket_id = resultSet.getInt("ticket_id");
                int price = resultSet.getInt("price");
                boolean classVip = resultSet.getBoolean("classVip");
                boolean status = resultSet.getBoolean("status");

                // Retrieve flight and passenger information
                FlightDao flightDao = new FlightDao();
                Flight flight = flightDao.getFlightByID(resultSet.getInt("flight_id"));
                PassengerDao passengerDao = new PassengerDao();
                Passenger passenger = passengerDao.getPassengerByID(resultSet.getInt("passenger_id"));

                Ticket ticket = new Ticket(ticket_id, price, flight, classVip, passenger);
                ticket.setTicketStatus(status);
                tickets.add(ticket);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tickets;
    }

    public Ticket getTicketByID(int ticketID) {
        Ticket ticket = null;

        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "SELECT * FROM ticket WHERE ticket_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, ticketID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int price = resultSet.getInt("price");
                boolean classVip = resultSet.getBoolean("classVip");
                boolean status = resultSet.getBoolean("status");

                // Retrieve flight and passenger information
                FlightDao flightDao = new FlightDao();
                Flight flight = flightDao.getFlightByID(resultSet.getInt("flight_id"));
                PassengerDao passengerDao = new PassengerDao();
                Passenger passenger = passengerDao.getPassengerByID(resultSet.getInt("passenger_id"));

                ticket = new Ticket(ticketID, price, flight, classVip, passenger);
                ticket.setTicketStatus(status);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ticket;
    }
}
