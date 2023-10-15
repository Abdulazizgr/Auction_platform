import java.sql.*;

// this class covers database connection
// for more favourable outcome we should use the same password and database naming
// it uses the jdbc API
public class Database {
    // setup requirement
    private static String url = "jdbc:mysql://localhost:3306/auction?useSSL=false";
    private static String password = "123456789";// 123456789
    private static String user = "root";// root

    private Database() {
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    // closing
    public static void closePreparedStatement(PreparedStatement ps) throws SQLException {
        ps.close();
    }

    public static void closeConnection(Connection con) throws SQLException {
        con.close();
    }
}
