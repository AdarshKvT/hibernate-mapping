package com.kvtsoft.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcConnection {
	public static void main(String[] agrs) {

		// using ssl false to get rid of ssl warning during connection
		String jdbcUrl = "jdbc:mysql://localhost:3306/hb-01-one-to-one-uni?useSSL=false&serverTimeZone=UTC";
		String user = "hbstudent";
		String pass = "hbstudent";

		try {
			System.out.println("Connecting to database: " + jdbcUrl);
			Connection conn = DriverManager.getConnection(jdbcUrl, user, pass);

			System.out.println("connection is successful");
		} catch (Exception e) {
			System.out.println("connection is unsuccessful !!");
			e.printStackTrace();
		}
	}
}
