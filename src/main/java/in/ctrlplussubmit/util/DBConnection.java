package in.ctrlplussubmit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static Connection conn;
	public static void openConnection(String url, String username, String password) throws SQLException{
		conn = DriverManager.getConnection(url,username,password);
		System.out.println("Connection Done");
	}
	public static Connection getConnection() {
		return conn;
	}
	public static void closeConnection() throws SQLException{
		conn.close();
	}
}
