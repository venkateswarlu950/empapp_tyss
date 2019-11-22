package com.tyss.jdbc;

import java.io.FileReader;
import java.sql.DriverManager;
import java.util.Properties;

import com.mysql.jdbc.Connection;

public class TryResources {
	
	public static void main(String[] args) {
		
		
		try(FileReader reader = new FileReader("db.properties")) {
			Properties prop = new Properties();
			prop.load(reader);
			
		  Class.forName(prop.getProperty("driver-class"));
		  String ur = prop.getProperty("url");
		  String sql = prop.getProperty("update-query");
		 
			  
		  }
			catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}


