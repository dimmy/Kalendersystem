package no.ntnu.fp.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

class DatabaseConnection {
	private String mysqlDriver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://mysql.stud.ntnu.no";
	private Connection conn;

	private void initializeDB() throws ClassNotFoundException, SQLException {
		Class.forName(mysqlDriver);
		Properties props = new Properties();
		props.setProperty("user", "heddano_fp43");
		props.setProperty("password", "heddano_fp43");
		conn = DriverManager.getConnection(url, props);
	}

	public ResultSet makeSingleQuery(String query) throws SQLException {
		Statement st = conn.createStatement();
		// st.setQueryTimeout(10800);
		ResultSet rs = st.executeQuery(query);
		return rs;
	}

	public int makeUpdate(String query) throws SQLException {
		Statement st = conn.createStatement();
		int res = st.executeUpdate(query);
		return res;
	}

}