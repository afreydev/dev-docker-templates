package core.db;

import java.sql.*;
import java.sql.SQLException;

public class JDBCConnection {

    private Connection conn;

    public Connection getConn() throws SQLException {
        try {
            conn = getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            throw new SQLException(e);
        }
        return conn;
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        String host = System.getenv("MYSQL_HOST");
        String port = System.getenv("MYSQL_PORT");
        String db = System.getenv("MYSQL_DATABASE");
        String user = System.getenv("MYSQL_USER");
        String password = System.getenv("MYSQL_PASSWORD");
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            String connectionUrl = "jdbc:mysql://" + host + ":" + port + "/" + db + "?user=" + user
                    + "&serverTimezone=UTC" + "&password=" + password;

            conn = DriverManager.getConnection(connectionUrl);
            conn.setAutoCommit(false);

        } catch (SQLException | ClassNotFoundException e) {
            throw new SQLException(e);
        }

        return conn;
    }

    public void close() throws Exception {
        try {
            if (null != conn) {
                conn.close();
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}
