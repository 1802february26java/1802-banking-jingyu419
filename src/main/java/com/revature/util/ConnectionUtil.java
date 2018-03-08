package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * 
 * @author jingyu
 * 
 * This utility class handles the connection to ORACLE DATABASE.
 *
 */
public class ConnectionUtil {

	  private static Logger logger = Logger.getLogger(ConnectionUtil.class);
	  
	  public static Connection getConnection() throws SQLException{
		  
		  String url = "jdbc:oracle:thin:@bankingappdbinstance.csj0uy543qxj.us-east-1.rds.amazonaws.com:1521:ORCL";
		  String username = "BANKADMIN";
		  String password = "password";
		  
		  logger.trace("We are connecting database now.");
		  
		  return DriverManager.getConnection(url,username,password);
	  }
}
