package com.tyss.empwebapp.dao;

import com.tyss.empwebapp.dto.EmployeeInfo;

public interface EmployeeDAO {
	
	
	public EmployeeInfo auth(int id, String password);
	
	public EmployeeInfo search(int id);
	
	public boolean changePassword(int id, String password);
	
	public boolean registerEmp(EmployeeInfo info);
	

}
