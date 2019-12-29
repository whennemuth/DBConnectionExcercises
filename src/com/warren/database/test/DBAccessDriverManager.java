package com.warren.database.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBAccessDriverManager extends DBAccess {

	@Override
	public Connection getConnection() throws Exception {

        try {
			Class.forName("com.mysql.jdbc.Driver");
		}
        catch (ClassNotFoundException e) {
			e.printStackTrace(System.out);
		}
        
        Connection ctn = null;
		try {
			ctn = DriverManager.getConnection(
					"jdbc:" +
					"mysql://" + SERVER + 
					"/" + DATABASE + "?" +
					"user=" + USER + "&" +
					"password=" + PASSWORD);
		} 
		catch (SQLException e) {
			e.printStackTrace(System.out);
		}
		finally {
			return ctn;
		}
	}

	public static void main(String[] args) {
		
		DBAccessDriverManager dbaccess = new DBAccessDriverManager();
		dbaccess.printDeckmanTableRow();
	}
}
