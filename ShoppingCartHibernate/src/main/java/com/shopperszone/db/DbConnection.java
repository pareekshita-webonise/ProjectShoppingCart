package com.shopperszone.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	private Connection connection = null;
	private static final String COM_MYSQL_JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String JDBC_MYSQL_LOCALHOST_3306_SHOPPING_APP = "jdbc:mysql://localhost:3306/shopping_app";
	private static final String USER = "root";
	private static final String PASS = "root";

	public Connection getConnection() {
		if (connection == null) {
			try {
				Class.forName(COM_MYSQL_JDBC_DRIVER);
				connection = DriverManager.getConnection(JDBC_MYSQL_LOCALHOST_3306_SHOPPING_APP, USER, PASS);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}

	public void closeConnection() {
		try {
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		DbConnection db = new DbConnection();
		Connection connection = db.getConnection();
		System.out.println(connection == null ? "Not connected" : "Connected");
		db.closeConnection();
	}
}
