package no.ntnu.fp.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

class DatabaseConnection {
	private String mysqlDriver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://mysql.stud.ntnu.no/heddano_fp43";
	private Connection conn;

	public void initializeDB(){
		try {
			Class.forName(mysqlDriver);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Properties props = new Properties();
		props.setProperty("user", "heddano_fp43");
		props.setProperty("password", "fp43");
		try {
			conn = DriverManager.getConnection(url, props);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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