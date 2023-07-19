package monash.Airline.dao;

import monash.Airline.entity.Passenger;
import monash.Airline.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PassengerDao {
    public ArrayList<Passenger> getAllPassengers() {
        ArrayList<Passenger> passengers = new ArrayList<>();

        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "SELECT * FROM passenger";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int passengerID = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("phoneNumber");
                String cardNumber = resultSet.getString("cardNumber");
                int securityCode = resultSet.getInt("securityCode");
                String passport = resultSet.getString("passport");
                String firstName = resultSet.getString("firstName");
                String secondName = resultSet.getString("secondName");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");

                Passenger passenger = new Passenger(firstName, secondName, age, gender, email, phoneNumber, passport, cardNumber, securityCode);
                passengers.add(passenger);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return passengers;
    }

    public Passenger getPassengerByID(int passengerID) {
        Passenger passenger = null;

        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "SELECT * FROM passenger WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, passengerID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("phoneNumber");
                String cardNumber = resultSet.getString("cardNumber");
                int securityCode = resultSet.getInt("securityCode");
                String passport = resultSet.getString("passport");
                String firstName = resultSet.getString("firstName");
                String secondName = resultSet.getString("secondName");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                passenger = new Passenger(firstName, secondName, age, gender, email, phoneNumber, passport, cardNumber, securityCode);
                passenger.setPassengerID(passengerID);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return passenger;
    }

    public int addPassenger(Passenger passenger) {
        int generatedID = -1;

        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "INSERT INTO passenger (firstName, secondName, age, gender, email, phoneNumber, cardNumber, securityCode, passport) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, passenger.getFirstName());
            statement.setString(2, passenger.getSecondName());
            statement.setInt(3, passenger.getAge());
            statement.setString(4, passenger.getGender());
            statement.setString(5, passenger.getEmail());
            statement.setString(6, passenger.getPhoneNumber());
            statement.setString(7, passenger.getCardNumber());
            statement.setInt(8, passenger.getSecurityCode());
            statement.setString(9, passenger.getPassport());

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                generatedID = generatedKeys.getInt(1);
            }

            generatedKeys.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return generatedID;
    }

    public void deletePassenger(int passengerID) {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "DELETE FROM passenger WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, passengerID);

            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

