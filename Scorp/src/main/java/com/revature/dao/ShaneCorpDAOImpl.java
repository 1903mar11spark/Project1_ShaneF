package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Employee;
import com.revature.beans.Request;
import com.revature.util.ConnectionUtil;



public class ShaneCorpDAOImpl implements ShaneCorpDAO{
	
	@Override
	public boolean getLogin(String first, String second) {
		PreparedStatement myStmt = null;
		boolean x = false;
		ResultSet rs = null;
		
		try 
		  { 
			
		  Connection con = ConnectionUtil.getConnectionFromFile();
		  myStmt = con.prepareStatement("SELECT USERNAME, PASSWORD FROM LOGIN WHERE USERNAME = ? AND PASSWORD =?");
		  myStmt.setString(1, first);
		  myStmt.setString(2, second);
		  rs = myStmt.executeQuery();
		  
		  if (!rs.next()) {
			  x = false;
			 } 
			  else {
				  x = true;
				   }
				
		  }
			  catch (SQLException e) 
			  {e.printStackTrace();}
			  catch (IOException e) 
			  { e.printStackTrace();}
		  return x;
	}
	
	@Override
	public Employee getEmployee(int id) {
		PreparedStatement stmt = null ;
		Connection con = null;
		ResultSet rs = null;
		int reportsto = 1;
		String email ="" , fName = "", lName = "" ;
		try 
		  { 
		  con = ConnectionUtil.getConnectionFromFile();
		  String sql = "SELECT EMPLOYEE_ID, EMAIL, REPORTSTO, FIRST_NAME, LAST_NAME FROM EMPLOYEE WHERE EMPLOYEE_ID = ?";
		  stmt = con.prepareStatement(sql);
		  stmt.setInt(1, id);
		  
		  
			 rs = stmt.executeQuery();
			while (rs.next()) {
				 id = rs.getInt("EMPLOYEE_ID");
				 reportsto = rs.getInt("REPORTSTO");
				 email = rs.getString("EMAIL");
				 fName = rs.getString("FIRST_NAME");
				 lName = rs.getString("LAST_NAME");
		  }
		  }
		  catch (SQLException e) 
		  { 
			  e.printStackTrace(); 
		  }
		  catch (IOException e) 
		  {
			  e.printStackTrace(); 
		  }
		catch (NullPointerException e) 
		  { 
			  e.printStackTrace(); 
		  }
		finally {
		    try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { con.close(); } catch (Exception e) { /* ignored */ }
		}
			Employee thisEmp = new Employee(id,reportsto,fName,lName, email);
			return thisEmp;
	}

	@Override
	public int getEmployeeId(String first, String second) {
		PreparedStatement myStmt = null;
		int x = 0;
		ResultSet rs = null;
		
		try 
		  { 
			
		  Connection con = ConnectionUtil.getConnectionFromFile();
		  myStmt = con.prepareStatement("SELECT EMPLOYEE_ID FROM LOGIN WHERE USERNAME = ? AND PASSWORD =?");
		  myStmt.setString(1, first);
		  myStmt.setString(2, second);
		  rs = myStmt.executeQuery();
		  while (rs.next()) {
				 x = rs.getInt("EMPLOYEE_ID");
		  }
	
		  }
			  catch (SQLException e) 
			  {e.printStackTrace();}
			  catch (IOException e) 
			  { e.printStackTrace();}
		  return x;
	}
	
	@Override
	public void updateEmployee(int reportsto, String fname, String lname, String email, int id) {
		PreparedStatement stmt = null;
        //Some exception handling with connecting to a file.
		try ( Connection con = ConnectionUtil.getConnectionFromFile()) {
			
			//Writing DML query, then using the PreparedStatement helper methods to later execute the query.
			stmt = con.prepareStatement("UPDATE EMPLOYEE\n" + 
										"SET FIRST_NAME = ?, LAST_NAME = ?, REPORTSTO = ?, EMAIL =?\n" + 
										"WHERE EMPLOYEE_ID = ?");
			stmt.setString(1, fname);
			stmt.setString(2, lname);
			stmt.setInt(3, reportsto);
			stmt.setString(4, email);
			stmt.setInt(5, id);
			
		    stmt.execute();
           //More exception handling.
	      } catch (SQLException sqlEx) {
	             sqlEx.printStackTrace();
	             System.exit(1);  
	      } catch (IOException e1) {
			e1.printStackTrace();
			} finally {
		             try { 	//Ideally would close connection here.
		                    stmt.close();  
			             } catch (Exception e) {
			                    System.exit(1); 
			             } finally {
			 			    //try { rs.close(); } catch (Exception e) { /* ignored */ }
						    try { stmt.close(); } catch (Exception e) { /* ignored */ }
						    
						}
		      }

		
	}
	public List<Employee> getEmployeeManager(int reportsto){
		List<Employee> emp = new ArrayList<Employee>();
		PreparedStatement stmt = null ;
		Connection con = null;
		ResultSet rs = null;
		int id = 1;
		String email ="" , fName = "", lName = "" ;
		try 
		  { 
			con = ConnectionUtil.getConnectionFromFile();
			  String sql = "SELECT EMPLOYEE_ID, EMAIL, REPORTSTO, FIRST_NAME, LAST_NAME FROM EMPLOYEE WHERE REPORTSTO = ?";
			  stmt = con.prepareStatement(sql);
			  stmt.setInt(1, reportsto);
			  
			  
				 rs = stmt.executeQuery();
				while (rs.next()) {
					 id = rs.getInt("EMPLOYEE_ID");
					 reportsto = rs.getInt("REPORTSTO");
					 email = rs.getString("EMAIL");
					 fName = rs.getString("FIRST_NAME");
					 lName = rs.getString("LAST_NAME");
					emp.add(new Employee(id,reportsto,fName,lName, email));
			  }
		  }
		  catch (SQLException e) 
		  { 
			  e.printStackTrace(); 
		  }
		  catch (IOException e) 
		  {
			  e.printStackTrace(); 
		  }finally {
			    try { rs.close(); } catch (Exception e) { /* ignored */ }
			    try { con.close(); } catch (Exception e) { /* ignored */ }
			}
		
			return emp;
	}
	
	public void createRequest(String type, double amount ,int id) {
		
		PreparedStatement stmt = null;
			
		try ( Connection con = ConnectionUtil.getConnectionFromFile()) {
	
		
		stmt = con.prepareStatement("INSERT INTO REIMBURSMENT (REQUEST_TYPE, AMOUNT, EMPLOYEE_ID) VALUES (?,?,?)");
		stmt.setString(1, type);
		stmt.setDouble(2, amount);
		stmt.setInt(3, id);
		
		
		stmt.execute();
		} catch (SQLException sqlEx) {
             sqlEx.printStackTrace();
             System.exit(1);  
      } catch (IOException e1) {
		e1.printStackTrace();
		} finally {
	             try { 	//Ideally would close connection here.
	                    stmt.close();  
		             } catch (Exception e) {
		                    System.exit(1); 
		             }finally {
					    try { stmt.close(); } catch (Exception e) { /* ignored */ }
					    
					}
}
}
	public List<Request> getYourRequests(int id){
		List<Request> req = new ArrayList<Request>();
		PreparedStatement stmt = null ;
		Connection con = null;
		ResultSet rs = null;
		int rid = 1;
		double amount = 0;
		String type ="" , status = "" ;
		try 
		  { 
			con = ConnectionUtil.getConnectionFromFile();
			  String sql = "SELECT REQUEST_ID, REQUEST_TYPE, AMOUNT, STATUS FROM REIMBURSMENT WHERE EMPLOYEE_ID = ?";
			  stmt = con.prepareStatement(sql);
			  stmt.setInt(1, id);
			  
			  
				 rs = stmt.executeQuery();
				while (rs.next()) {
					 rid = rs.getInt("REQUEST_ID");
					 type = rs.getString("REQUEST_TYPE");
					 amount = rs.getDouble("AMOUNT");
					  status = rs.getString("STATUS");
					req.add(new Request(rid,type,amount,status));
			  }
		  }
		  catch (SQLException e) 
		  { 
			  e.printStackTrace(); 
		  }
		  catch (IOException e) 
		  {
			  e.printStackTrace(); 
		  }finally {
			    try { rs.close(); } catch (Exception e) { /* ignored */ }
			    try { con.close(); } catch (Exception e) { /* ignored */ }
			}
		
			return req;
	}
	public List<Employee> getEmployeeRequests(int reportsto){
		List<Employee> emp = new ArrayList<Employee>();
		PreparedStatement stmt = null ;
		Connection con = null;
		ResultSet rs = null;
		int id = 1;
		String email ="" , fName = "", lName = "" ;
		try 
		  { 
			con = ConnectionUtil.getConnectionFromFile();
			  String sql = "SELECT EMPLOYEE_ID, EMAIL, REPORTSTO, FIRST_NAME, LAST_NAME FROM EMPLOYEE WHERE REPORTSTO = ?";
			  stmt = con.prepareStatement(sql);
			  stmt.setInt(1, reportsto);
			  
			  
				 rs = stmt.executeQuery();
				while (rs.next()) {
					 id = rs.getInt("EMPLOYEE_ID");
					 reportsto = rs.getInt("REPORTSTO");
					 email = rs.getString("EMAIL");
					 fName = rs.getString("FIRST_NAME");
					 lName = rs.getString("LAST_NAME");
					emp.add(new Employee(id,reportsto,fName,lName, email));
			  }
		  }
		  catch (SQLException e) 
		  { 
			  e.printStackTrace(); 
		  }
		  catch (IOException e) 
		  {
			  e.printStackTrace(); 
		  }finally {
			    try { rs.close(); } catch (Exception e) { /* ignored */ }
			    try { con.close(); } catch (Exception e) { /* ignored */ }
			}
		
			return emp;
}
}	
