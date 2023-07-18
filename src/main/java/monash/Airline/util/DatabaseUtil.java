package monash.Airline.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;




// 将使用JDBC连接MySQL数据库的相关操作封装到一个静态方法中，
// 以便在需要的地方调用该方法而无需重复编写连接代码。
public class DatabaseUtil {
    private static final String url = "jdbc:mysql://localhost:3306/fit5171a1"; // 数据库URL
    private static final String username = "root"; // 数据库用户名
    private static final String password = "123456"; // 数据库密码

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    // 可以添加其他数据库操作的静态方法...
}
