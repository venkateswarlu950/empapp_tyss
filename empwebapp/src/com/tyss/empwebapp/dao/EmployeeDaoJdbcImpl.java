package com.tyss.empwebapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.tyss.empwebapp.dto.EmployeeInfo;

public class EmployeeDaoJdbcImpl implements EmployeeDAO {

	private static final String url = "jdbc:mysql://localhost:3306/ust_ty_web_db?user=root&password=root";


	@Override
	public EmployeeInfo auth(int id, String password) {

		EmployeeInfo info = search(id);


		if(info!=null) {
			String pwd = info.getPassword();
			if(pwd.equals(password)) {
				return info;
			}else {
				return null;
			}
		}

		return null;
	}

	@Override
	public EmployeeInfo search(int id) {

		String sql = "select * from employees_db where eid=?";

		try {

			Class.forName("com.mysql.jdbc.Driver");

			try(Connection conn = DriverManager.getConnection(url);
					PreparedStatement stm = conn.prepareStatement(sql)){

				stm.setInt(1, id);
				try(ResultSet res = stm.executeQuery()){
					if(res.next()) {
						EmployeeInfo info = new EmployeeInfo();

						info.setEid(res.getInt("eid"));
						info.setEname(res.getString("ename"));
						info.setEmail(res.getString("email"));
						info.setPassword(res.getString("password"));


						return info;
					}else {
						return null;
					}
				}


			}

		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public boolean changePassword(int id, String password) {

		String sql = " update employees_db set password=? where eid=?";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			try(Connection conn = DriverManager.getConnection(url);
					PreparedStatement stm = conn.prepareStatement(sql)){

				stm.setString(1, password);
				stm.setInt(2, id);
				int count = stm.executeUpdate();

				boolean check = count>0 ? true : false;
				return check;
			}

		}catch (Exception e) {

			e.printStackTrace();

		}


		return false;
	}

	@Override
	public boolean registerEmp(EmployeeInfo info) {

		String sql = " insert into employees_db values(?,?,?,?) ";

		try {
			Class.forName("com.mysql.jdbc.Driver");

			try(Connection conn = DriverManager.getConnection(url);
					PreparedStatement stm = conn.prepareStatement(sql)){

				stm.setInt(1, info.getEid());
				stm.setString(2, info.getEname());
				stm.setString(3, info.getEmail());
				stm.setString(4, info.getPassword());

				int count = stm.executeUpdate();
				if(count>0) {
					return true;
				}else {
					return false;
				}

			}


		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;




	}// register

}
