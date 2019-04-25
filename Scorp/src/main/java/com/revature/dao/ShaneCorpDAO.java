package com.revature.dao;

import java.util.List;

import com.revature.beans.Employee;
import com.revature.beans.Request;

public interface ShaneCorpDAO {
	
	public boolean getLogin(String first, String second);
	public Employee getEmployee(int id);
	public int getEmployeeId(String first, String second);
	void updateEmployee(int reportsto, String fname, String lname, String email, int id);
	public List<Employee> getEmployeeManager(int reportsto);
	public void createRequest(String type, double amount, int id);
	public List<Request> getYourRequests(int id);
	public List<Employee> getEmployeeRequests(int reportsto);

}
