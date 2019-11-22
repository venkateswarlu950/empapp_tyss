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

public class DeletedQuery {
	
public static void main(String[] args) {
		
		Connection conn = null;
		//Statement st = null;
		PreparedStatement ptm = null;
		FileReader reader = null;
		try {
			reader = new FileReader("db.properties");
			Properties prop = new Properties();
			prop.load(reader);
//			Driver d = new Driver();
//			DriverManager.registerDriver(d);
			Class.forName(prop.getProperty("driver-class"));
			
			String u = prop.getProperty("url");
			
			conn = DriverManager.getConnection(u,prop);
			
			String sql = prop.getProperty("delete-query");
			
			ptm = conn.prepareStatement(sql);
			
			String emId = args[0];
			int id = Integer.parseInt(emId);
			ptm.setInt(1, id);
			int del = ptm.executeUpdate();
			
			
			System.out.println(del +"::row has been deleted");
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
			if(ptm!=null) {
				try {
					ptm.close();
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
