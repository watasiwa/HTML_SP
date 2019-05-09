package com.patagonia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class JdbcTest01 {
	public static void main(String[] args) throws Exception{
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url    = "jdbc:oracle:thin:@localhost:1521:xe";
		String user   = "patagonia";
		String password = "q1w2e3r4";
		
		Class.forName(driver);
		Connection con =  DriverManager.getConnection(url, user, password);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from pata");
		
		while(rs.next()){
			String col1 = rs.getNString(1);
			String col2 = rs.getNString(2);
			String col3 = rs.getNString(3);
			
			System.out.println("col1 : " + col1);
			System.out.println("col2 : " + col2);
			System.out.println("col3 : " + col3);
		}
		rs.close();
		stmt.close();
		con.close();
	}
}
