package com.rootservice.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnection {

	private static String drivername = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost/rootservice";
	private static String username = "root";
	private static String password = "root";
	
	private static Connection connection;

	public static Connection getConnection(){
		try{
			Class.forName(drivername);
			connection = DriverManager.getConnection(url,username,password);
		}catch(Exception e){
			e.printStackTrace();
		}
		return connection;
	}
	public static void closeConnection(){
		try{
			connection.close();
		}catch(Exception e){
			
		}
	}
}
