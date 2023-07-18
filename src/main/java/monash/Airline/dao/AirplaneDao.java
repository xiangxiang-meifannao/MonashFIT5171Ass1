package monash.Airline.dao;

import monash.Airline.entity.Airplane;
import monash.Airline.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


//用于实现对实体类Airplane的相关数据库操作
public class AirplaneDao {
    public List<Airplane> getAllAirplanes() throws SQLException {
        List<Airplane> airplanes = new ArrayList<>();

        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "SELECT * FROM airplane";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int airplaneID = resultSet.getInt("airplaneID");
                String airplaneModel = resultSet.getString("airplaneModel");
                int businessSitsNumber = resultSet.getInt("businessSitsNumber");
                int economySitsNumber = resultSet.getInt("economySitsNumber");
                int crewSitsNumber = resultSet.getInt("crewSitsNumber");

                Airplane airplane = new Airplane(airplaneID, airplaneModel, businessSitsNumber, economySitsNumber, crewSitsNumber);
                airplanes.add(airplane);
            }

            resultSet.close();
            statement.close();
        }

        return airplanes;
    }

    public Airplane getAirplaneByID(int airplaneID) throws SQLException {
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
        }

        return airplane;
    }

    public void addAirplane(Airplane airplane) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "INSERT INTO airplane (airplaneID, airplaneModel, businessSitsNumber, economySitsNumber, crewSitsNumber) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, airplane.getAirplaneID());
            statement.setString(2, airplane.getAirplaneModel());
            statement.setInt(3, airplane.getBusinessSitsNumber());
            statement.setInt(4, airplane.getEconomySitsNumber());
            statement.setInt(5, airplane.getCrewSitsNumber());

            statement.executeUpdate();

            statement.close();
        }
    }

    public void updateAirplane(Airplane airplane) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "UPDATE airplane SET airplaneModel = ?, businessSitsNumber = ?, economySitsNumber = ?, crewSitsNumber = ? WHERE airplaneID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, airplane.getAirplaneModel());
            statement.setInt(2, airplane.getBusinessSitsNumber());
            statement.setInt(3, airplane.getEconomySitsNumber());
            statement.setInt(4, airplane.getCrewSitsNumber());
            statement.setInt(5, airplane.getAirplaneID());

            statement.executeUpdate();

            statement.close();
        }
    }
}
