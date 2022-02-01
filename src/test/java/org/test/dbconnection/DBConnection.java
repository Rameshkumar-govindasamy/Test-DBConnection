package org.test.dbconnection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBConnection {
	Connection conn = null;
	ResultSet rs2 = null;
	
	public void getConnection() {
		
		Properties prop = new Properties();
		
		
		 try (InputStream input = DBConnection.class.getResourceAsStream("/properties/config.properties")) {

	            if (input == null) {
	                System.out.println("unable to find config.properties");
	            }

	            prop.load(input);

	            System.out.println(prop.getProperty("db.url"));
	            System.out.println(prop.getProperty("db.user"));
	            System.out.println(prop.getProperty("db.password"));

	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
		
		
		try {
				Class.forName("com.mysql.jdbc.Driver");
				conn =  DriverManager.getConnection(prop.getProperty("db.url"), prop.getProperty("db.user"), prop.getProperty("db.password"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getCustomerLevel() {
		
	    try {

	        CallableStatement cs;
	        cs = conn.prepareCall("{CALL GetCustomerLevel(?,?)}");
	        cs.setString(1,"141" );
	        cs.setString(2, "@level");
	        rs2 = cs.executeQuery();

	        if(rs2.next()){
	            System.out.println(true);
	        }
	        
	        conn.close();

	    } catch (Exception e) {

	    }
	  }
	
	public ResultSet getResult() {
		return rs2;
	}
	
	
}
