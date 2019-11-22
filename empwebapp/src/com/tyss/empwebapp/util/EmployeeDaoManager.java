package com.tyss.empwebapp.util;

import com.tyss.empwebapp.dao.EmployeeDAO;
import com.tyss.empwebapp.dao.EmployeeDaoJdbcImpl;

public class EmployeeDaoManager {
	
	private EmployeeDaoManager() {
		
	}

	public static EmployeeDAO getEmployeeDAO() {
		
		return new EmployeeDaoJdbcImpl();
	}
}
