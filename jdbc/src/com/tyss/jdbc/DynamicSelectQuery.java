package com.tyss.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;

public class DynamicSelectQuery {
	
	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3306/ust_ty_db?user=root&password=root";
		
		String sql = " select * from employee_info where id=? ";
		
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet res = null;
		
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			
			conn = DriverManager.getConnection(url);
			
			pre = conn.prepareStatement(sql);
			
			String empId = args[0];
			
			int id = Integer.parseInt(empId);
			
			pre.setInt(1, id);
			
			res = pre.executeQuery();
			
			if(res.next()) {
				
				int id1 = res.getInt("id");
				String name1 = res.getString("name");
				int salary1 = res.getInt("sal");
				String gender1 = res.getString("gender");
				
				System.out.println("id is  "+id1);
				System.out.println("name is  "+name1);
				System.out.println("sal is  "+salary1);
				System.out.println("gender is  "+gender1);
				
			}
			
		}catch (Exception e) {
			
			e.printStackTrace();
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pre!=null) {
				try {
					pre.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(res!=null) {
				try {
					res.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	

}
