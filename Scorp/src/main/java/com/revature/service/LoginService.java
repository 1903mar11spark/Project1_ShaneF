package com.revature.service;

import com.revature.beans.Employee;
import com.revature.beans.Login;
import com.revature.dao.ShaneCorpDAO;
import com.revature.dao.ShaneCorpDAOImpl;

public class LoginService {
	
ShaneCorpDAO sc = new ShaneCorpDAOImpl();
	
	public Employee isValidEmployee(Login login) {
		Employee E = null;
		String username = login.getUsername();
		String password = login.getPassword();
		if (username != null && password != null) {
			// this is fake authentication!
			if (sc.getLogin(username, password)) {
				int id = sc.getEmployeeId(username, password);
				E = sc.getEmployee(id);
			}
		}
		return E;
	}

}
