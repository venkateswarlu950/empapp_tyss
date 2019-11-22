package com.tyss.jdbc;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.mysql.jdbc.Driver;

public class Updatequery {
	
	public static void main(String[] args) {
		
		Connection conn = null;
		//Statement st = null;
		PreparedStatement pre = null;
		FileReader reader = null;
		try {
			reader = new FileReader("db.properties");
			Properties prop = new Properties();
			prop.load(reader);
			
//			Driver d = new Driver();
//			DriverManager.registerDriver(d);
			Class.forName(prop.getProperty("driver-class"));

			String u = prop.getProperty("url");
			
			conn = DriverManager.getConnection(u, prop);
			
			String sql = prop.getProperty("update-query");
			
			pre = conn.prepareStatement(sql);
			
			String name = args[1];
			pre.setString(1, name);
			
			String sal = args[2];
			int salr = Integer.parseInt(sal);
			pre.setInt(2, salr);
			
			String gender = args[3];
			pre.setString(3, gender);
			
			String empId = args[0];
			int id = Integer.parseInt(empId);
			pre.setInt(4, id);
			
			
			int upd = pre.executeUpdate();
			
			
			System.out.println(upd +"::row has been updated");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(pre!=null) {
				try {
					pre.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(reader!=null) {
					try {
						reader.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
		
	}

}
