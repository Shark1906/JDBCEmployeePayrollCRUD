package com.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class EmpPayrollService {

	GetConnection connection = new GetConnection();
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement preparedStatement = null;

	public void showAll() throws SQLException {

		conn = connection.getConnection();
		String query = "SELECT * FROM employee_payroll";
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);

		while (rs.next()) {
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
			System.out.println("ID : " + id + ", Name : " + name + ", Gender : " + gender + ", Salary : " + salary
					+ ", StartDate : " + startdate + ", Phone : " + phone + ", Dept : " + dept);
		}
		stmt.close();
		connection.closeConnection();
	}

	public void updateBasePay() throws SQLException {

		Scanner sc = new Scanner(System.in);
		conn = connection.getConnection();
		System.out.println("Enter the employee Name");
		String name = sc.nextLine();
		System.out.println("Enter the new BasePay");
		double basepay = sc.nextDouble();
		String query = "update employee_payroll set basicpay=? where name=?";
		preparedStatement = conn.prepareStatement(query);
		preparedStatement.setDouble(1, basepay);
		preparedStatement.setString(2, name);
		int i = preparedStatement.executeUpdate();
		if (i > 0) {
			System.out.println("Employee updated successsfully");
		}

		preparedStatement.close();
		connection.closeConnection();	
	}

	public void showByDate() throws SQLException {
		Scanner sc1 = new Scanner(System.in);
		conn = connection.getConnection();
		System.out.println("Enter the Start Date(yyyy-mm-dd)");
		String startdate = sc1.nextLine();
		System.out.println("Enter the End Date(yyyy-mm-dd)");
		String enddate = sc1.nextLine();
		String query = "select * from employee_payroll where startdate between cast(? as date)"
				+ " and cast(? as date)";
		preparedStatement = conn.prepareStatement(query);
		preparedStatement.setString(1, startdate);
		preparedStatement.setString(2, enddate);

		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getString(2));
		}

		preparedStatement.close();
		connection.closeConnection();
	}
	
	public void analyseEmployeeSalary() throws SQLException {
		Scanner sc2 = new Scanner(System.in);
		conn = connection.getConnection();
		String query;
		ResultSet rs;
		System.out.println("Select any option");
		int option;
		do {
		System.out.println("1. Sum\n2. Avg\n3. Min\n4. Max\n5. Count\n6. Exit ");
		option = sc2.nextInt();
		switch (option) {
		case 1:
			query = "select gender,sum(salary) from employee_payroll group by gender";
			preparedStatement = conn.prepareStatement(query);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				System.out.print(rs.getString("gender")+" ");
				System.out.println(rs.getString("sum(salary)"));	
			}
			break;
		
		case 2:
			query = "select gender,avg(salary) from employee_payroll group by gender";
			preparedStatement = conn.prepareStatement(query);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				System.out.print(rs.getString("gender")+" ");
				System.out.println(rs.getString("avg(salary)"));	
			}
			break;
		
		case 3:
			query = "select gender,min(salary) from employee_payroll group by gender";
			preparedStatement = conn.prepareStatement(query);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				System.out.print(rs.getString("gender")+" ");
				System.out.println(rs.getString("min(salary)"));	
			}
			break;
		
		case 4:
			query = "select gender,max(salary) from employee_payroll group by gender";
			preparedStatement = conn.prepareStatement(query);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				System.out.print(rs.getString("gender")+" ");
				System.out.println(rs.getString("max(salary)"));	
			}
			break;
		
		case 5:
			query = "select gender,count(salary) from employee_payroll group by gender";
			preparedStatement = conn.prepareStatement(query);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				System.out.print(rs.getString("gender")+" ");
				System.out.println(rs.getString("count(salary)"));	
			}
			break;
		case 6:
			preparedStatement.close();
			connection.closeConnection();
			break;
		}}while(option!=6);	
	}
}
