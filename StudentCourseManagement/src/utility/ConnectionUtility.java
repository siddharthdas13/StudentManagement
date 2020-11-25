package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtility {

	static private final String URL = "jdbc:mysql://localhost:3306/courses?" + "autoReconnect=true&useSSL=false";
	static private final String USERNAME = "root";
	static private final String PASSWORD = "siddharth";

	public Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		return connection;
	}
}
