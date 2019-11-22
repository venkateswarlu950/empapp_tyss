package com.tyss.jdbc;

import java.sql.*;

import com.mysql.jdbc.Driver;

public class MyFirstJDBC {
	
	public static void main(String[] args) {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet res = null;
		
		try {
			//step 1- load the driver
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			
			//step-2 get the connection
			String url = "jdbc:mysql://localhost:3306/ust_ty_db";
			conn = DriverManager.getConnection(url, "root", "root");
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
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			// close all the objects
			if(res!=null) {
				try {
					res.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}//end of main

}//end of myjdbc class
