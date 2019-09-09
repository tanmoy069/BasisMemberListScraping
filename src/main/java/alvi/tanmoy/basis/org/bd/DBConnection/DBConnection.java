package alvi.tanmoy.basis.org.bd.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	public Connection getDBConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Connection conn = null;
		String url = "jdbc:sqlserver://DESKTOP-NETG37O\\SQLEXPRESS:1433;databaseName=WSCRAPER;"; //DESKTOP-NETG37O\SQLEXPRESS
		String userName = "sa";
		String password = "admin@321";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			conn = DriverManager.getConnection(url, userName, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
