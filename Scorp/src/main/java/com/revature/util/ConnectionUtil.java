package com.revature.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;



public class ConnectionUtil {
	
	public static Connection getConnectionFromFile() throws SQLException, IOException {
		Properties prop = new Properties();
		InputStream in = ConnectionUtil.class.getClassLoader().getResourceAsStream("Connection.properties");
		prop.load(in);
		try {
			   Class.forName("oracle.jdbc.driver.OracleDriver");
			}
			catch(ClassNotFoundException ex) {
			   System.out.println("Error: unable to load driver class!");
			}
		return DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
	}

}
