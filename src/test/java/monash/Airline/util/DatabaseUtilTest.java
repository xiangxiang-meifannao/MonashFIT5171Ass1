package monash.Airline.util;

import monash.Airline.entity.Airplane;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;



//一个测试类，用于验证 DatabaseUtil 是否能够成功连接到数据库
class DatabaseUtilTest {
    @Test
    void testConnection() {
        try (Connection connection = DatabaseUtil.getConnection()) {
            assertNotNull(connection, "连接对象不应为空");

            // 执行查询语句
            String sql = "SELECT * FROM airplane";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            // 处理查询结果
            while (resultSet.next()) {
                int airplaneID = resultSet.getInt("airplaneID");
                String airplaneModel = resultSet.getString("airplaneModel");
                int businessSitsNumber = resultSet.getInt("businessSitsNumber");
                int economySitsNumber = resultSet.getInt("economySitsNumber");
                int crewSitsNumber = resultSet.getInt("crewSitsNumber");
                Airplane airplane = new Airplane(airplaneID, airplaneModel, businessSitsNumber, economySitsNumber, crewSitsNumber);
                System.out.println(airplane.toString());
            }
            // 关闭结果集和语句
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            fail("发生SQL异常: " + e.getMessage());
        }
    }
}
