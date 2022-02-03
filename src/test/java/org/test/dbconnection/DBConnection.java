package org.test.dbconnection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBConnection {
	Connection conn = null;
	public static ResultSet rs = null;
	CallableStatement cs;
	
	public void getConnection(String dbName) {
		
		Properties prop = new Properties();
		
		
		 try (InputStream input = DBConnection.class.getResourceAsStream("/properties/config.properties")) {

	            if (input == null) {
	                System.out.println("unable to find config.properties");
	            }

	            prop.load(input);

	            System.out.println(prop.getProperty("db.url").replace("<dbname>",dbName));
	            System.out.println(prop.getProperty("db.user"));
	            System.out.println(prop.getProperty("db.password"));

	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
		
		
		try {
				Class.forName("com.mysql.jdbc.Driver");
				conn =  DriverManager.getConnection(prop.getProperty("db.url").replace("<dbname>",dbName), prop.getProperty("db.user"), prop.getProperty("db.password"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void runStoredProcedure(String spName,String params) {
		
	    try {

	        
	        String[] arrParams = params.split(",");
	        
	        String query = "{CALL " + spName + "(";
	        for(String parm : arrParams) {
	        	query = query + "?,";
	        }
	        query = query.substring(0,query.length()-1);
	        query = query + ")}";
	       
	        cs = conn.prepareCall(query);
	        
	        for(int indx=0;indx<arrParams.length;indx++) {
	        	if(arrParams[indx].contains("@")) {
	        		cs.registerOutParameter(indx+1, Types.VARCHAR);
	        	}
	        	else {
	        		cs.setString(indx+1,arrParams[indx] );
	        	}
	        }
	        
	        rs = cs.executeQuery();

	        if(rs.next()){
	            System.out.println(true);
	        }
	        
	        

	    } catch (Exception e) {

	    }
	  }
	
	public void printResult(String params) throws SQLException {
		
		String[] arrParams = params.split(",");
		String qry = "select ";
		if(params.contains("@")) {
			System.out.println("**** Print Output Parameters ****");
			for(int indx=0;indx<arrParams.length;indx++) {
				if(arrParams[indx].contains("@"))
					System.out.println(arrParams[indx] + " : " + cs.getString(indx+1));
	        }
			System.out.println("**** Print Output Parameters - END ****");
		}
		else if(rs!=null) {
			System.out.println("**** Print Output Table ****");
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			boolean header = true;
			while (rs.next()) {
				if(header) {
				    for (int i = 1; i <= columnsNumber; i++) {
				        if (i > 1) System.out.print(",  ");
				        String columnValue = rs.getString(i);
				        System.out.print(rsmd.getColumnName(i) + "");
				    }
				    System.out.println("");
				    header = false;
				}
			    for (int i = 1; i <= columnsNumber; i++) {
			        if (i > 1) System.out.print(",  ");
			        String columnValue = rs.getString(i);
			        System.out.print(columnValue + "\t");
			    }
			    System.out.println("");
			}
			System.out.println("**** Print Output Table - END ****");
		}
		
		cs.close();
		rs.close();
		conn.close();
		
	}
	
}
