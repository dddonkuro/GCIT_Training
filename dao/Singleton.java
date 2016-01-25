package com.gcit.training.lms.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Singleton {
	
	// this class provide a single object for connecting to the database
	//It also decouples the implementation of the getConnection method from the client code.
	private static Connection conn; 
	private static Singleton OneTimeConnection;
	
	public static Singleton getOneTimeConnection() {
		
		if(OneTimeConnection == null)
			OneTimeConnection = new Singleton();
		else
			System.err.println("Multiple objects of this Singleton class is not allowed. One object already exists");
		return OneTimeConnection;
	}


	private Singleton(){
		 conn = null;
		 OneTimeConnection = null;
	 }
	
	public static Connection getConnection(){
		if(conn == null){
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
	
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library",
						"root", "root");
	
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
		return conn;
	}
	public void closeConnection(){
		
	}
		
}
