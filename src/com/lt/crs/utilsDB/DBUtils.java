package com.lt.crs.utilsDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBUtils {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/test_1";
	    static final String USER = "root";
	   static final String PASSWORD = "true";
	   
	   public static Connection getConnection() {
		   Connection con=null;
		   try {
			   Class.forName(JDBC_DRIVER);
			  
			   con=DriverManager.getConnection(DB_URL,USER,PASSWORD);
	
			      }
		   catch(ClassNotFoundException e) {;e.printStackTrace();}
		   catch(SQLException e){e.printStackTrace();}
		   catch(Exception e){e.printStackTrace();}
		   
		  return con;
		   
		   
	   }
	   
	   
	   
	   
}
