package com.mphasis.dbutil;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import oracle.jdbc.driver.OracleDriver;

public class MyConnection {
	public static Connection getConnection() throws SQLException
	{
		ResourceBundle rb = ResourceBundle.getBundle("db");
		String url=rb.getString("url");
		String username=rb.getString("username");
		String password=rb.getString("password");
		Driver driver=new oracle.jdbc.driver.OracleDriver();
		return DriverManager.getConnection(url,username,password);
	}
}
