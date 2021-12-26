package com.demo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmpPayrollService {
	
	GetConnection connection = new GetConnection();
	
	public void showAll() throws SQLException {
		Connection conn = connection.getConnection();
		String query = "SELECT * FROM employee_payroll";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		while(rs.next()) {
			String id = rs.getString(1);
			String name = rs.getString(2);
			String gender = rs.getString(3);
			String salary = rs.getString(4);
			String startdate = rs.getString(5);
			String phone = rs.getString(6);
			String address = rs.getString(7);
			String dept = rs.getString(8);
			String basic = rs.getString(9);
			String deductions = rs.getString(10);
			String taxablepay = rs.getString(11);
			String incometax = rs.getString(12);
			String netpay = rs.getString(13);
			System.out.println("ID : "+ id + ", Name : "+ name + ", Gender : "+ gender + ", Salary : "+salary+
					", StartDate : " + startdate + ", Phone : "+phone+ ", Dept : " + dept);
		}
		
		connection.closeConnection();
	}

}
