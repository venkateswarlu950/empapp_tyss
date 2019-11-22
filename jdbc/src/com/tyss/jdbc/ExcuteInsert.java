package com.tyss.jdbc;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

//import com.mysql.jdbc.Driver;

public class ExcuteInsert {

	public static void main(String[] args) {

		Connection conn = null;
		//Statement stm = null;
		PreparedStatement ptm = null;
		FileReader reader = null;

		try {
			
			reader = new FileReader("db.properties");
			Properties prop = new Properties();
			prop.load(reader);
//			Driver driver = new Driver();
//			DriverManager.registerDriver(driver);
			Class.forName(prop.getProperty("driver-class"));

			String url = prop.getProperty("url");
			conn = DriverManager.getConnection(url,prop);

			String sql = prop.getProperty("insert-query");

			//stm = conn.createStatement();
			ptm = conn.prepareStatement(sql);
			String empId = args[0];
			int id = Integer.parseInt(empId);
			ptm.setInt(1, id);
			
			String name = args[1];
			ptm.setString(2, name);
			
			String sal = args[2];
			
			int salary = Integer.parseInt(sal);
			ptm.setInt(3, salary);
			
			String gen = args[3];
			ptm.setString(4, gen);
			
			int count = ptm.executeUpdate();
            
			System.out.println(count +":: rows inserted");

		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (ptm != null) {
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


	}//end of main

}//end of class
