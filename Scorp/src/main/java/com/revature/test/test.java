package com.revature.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.revature.beans.Login;
import com.revature.service.LoginService;
import com.revature.util.ConnectionUtil;



public class test {
	
public static void main(String[] args) {
		
		//String plug = "//Users//shaneflynn//GitRepos//Flynn_Project1//ShaneCorp//src//main//resources//Connection.prop";
		
		  try 
		  { 
		  //Create connection with user. Do some exception handling.
		  Connection con = ConnectionUtil.getConnectionFromFile();
		  System.out.println(con); 
		  }
		  catch (SQLException e) 
		  { 
			  e.printStackTrace(); 
		  }
		  catch (IOException e) 
		  {
			  e.printStackTrace(); 
		  }
		  LoginService ls = new LoginService();
		  Login login = new Login("shanef3", "shane");
		  System.out.println(ls.isValidEmployee(login));
		  //ShaneCorpDAO sc = new ShaneCorpDAOImpl();
		  //System.out.println(sc.getLogin("shanef3", "shane"));
	}

}

