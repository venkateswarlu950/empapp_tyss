package com.tyss.jdbc;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.mysql.jdbc.Driver;

public class MyFirstJDBCProperties {
	
	public static void main(String[] args) {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet res = null;
		FileReader reader = null;
		try {
			reader = new FileReader("db.properties");
			Properties prop = new Properties();
			prop.load(reader);
			//step 1- load the driver
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			
			//step-2 get the connection
			String url = prop.getProperty("url");
			conn = DriverManager.getConnection(url, prop);
			stmt = conn.createStatement();
			
			//issue sql query
			String sql = "select * from employee_info";
			res = stmt.executeQuery(sql);
			
			while(res.next()) {
				
				//Read the results
				int id = res.getInt("id");
				String name = res.getString("name");
				int sal = res.getInt("sal");
				String gender = res.getString("gender");
				
				System.out.println("id::"+id);
				System.out.println("name::"+name);
				System.out.println("salary::"+sal);
				System.out.println("gender is ::"+gender);
				System.out.println("===============================");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			// close all the objects
			if(res!=null) {
				try {
					res.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(reader!=null) {
				try {
					reader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}
