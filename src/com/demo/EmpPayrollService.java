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
			boolean is_active = rs.getBoolean(10);
//			String dept = rs.getString(8);
//			String basic = rs.getString(9);
//			String deductions = rs.getString(10);
//			String taxablepay = rs.getString(11);
//			String incometax = rs.getString(12);
//			String netpay = rs.getString(13);
			System.out.println("Emp ID : " + id + ", Name : " + name + ", Gender : " + gender + ", Salary : " + salary
					+ ", StartDate : " + startdate + ", Phone : " + phone + ", Add : "+address + ", is_active : "+is_active );//+ ", Dept : " + dept+"//, Basic Pay : "+basic);
		}
		stmt.close();
		connection.closeConnection();
	}

	public void updateSalary() throws SQLException {
	
		Scanner sc = new Scanner(System.in);
		conn = connection.getConnection();
		System.out.println("Enter the Emp ID");
		int empId = sc.nextInt();
		System.out.println("Enter the new Salary");
		double empSalary = sc.nextDouble();
		try {
		conn.setAutoCommit(false);
		String query1 = "update employee_payroll set salary=? where id=?";
		String query2 = "update payroll_detail set basicpay=?,deductions=?,taxablepay=?,incometax=?,netpay=? where id=?";
		preparedStatement = conn.prepareStatement(query1);
		preparedStatement.setDouble(1, empSalary);
		preparedStatement.setInt(2, empId);
		preparedStatement.executeUpdate();
		preparedStatement = conn.prepareStatement(query2);
		preparedStatement.setDouble(1, empSalary);
		preparedStatement.setDouble(2, (0.2*empSalary));
		preparedStatement.setDouble(3, (empSalary-(0.2*empSalary)));
		preparedStatement.setDouble(4, (empSalary-(0.2*empSalary))*0.1);
		preparedStatement.setDouble(5, empSalary-((empSalary-(0.2*empSalary))*0.1));
		preparedStatement.setInt(6, empId);
		preparedStatement.executeUpdate();
		conn.commit();
		System.out.println("Salary Updated Successfully");
		}catch(Exception ex) {
			conn.rollback();
		}finally {
			connection.closeConnection();
		}
	
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
			System.out.println("Emp Name : "+rs.getString(2)+", Is Active : "+rs.getBoolean(10));
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
	
	public void addEmployeePayroll() throws SQLException {
		Scanner sc4 = new Scanner(System.in);
		Scanner sc5 = new Scanner(System.in);
		System.out.println("Enter Employee Name");
		String empName = sc4.nextLine();
		System.out.println("Enter Gender(M/F)");
		String empSex = sc4.nextLine();
		System.out.println("Enter Employee Salary");
		double empSalary = sc4.nextDouble();
		System.out.println("Enter Start Date");
		String startDate = sc5.nextLine();
		System.out.println("Enter Employee Phone");
		long empPhone = sc4.nextLong();
		System.out.println("Enter Employee Address");
		String empAdd = sc5.nextLine();
		System.out.println("Enter Company ID");
		int comId = sc4.nextInt();
		System.out.println("Enter Dept. ID");
		int deptId = sc4.nextInt();
		conn = connection.getConnection();
		try {
			conn.setAutoCommit(false);
			String query1 = "insert into employee_payroll(name,gender,salary,startdate,phone,address,company_id,dept_id) values (?,?,?,?,?,?,?,?)";
			String query2 = "insert into payroll_detail(basicpay,deductions,taxablepay,incometax,netpay) values (?,?,?,?,?)";
			preparedStatement = conn.prepareStatement(query1);
			preparedStatement.setString(1, empName);
			preparedStatement.setString(2, empSex);
			preparedStatement.setDouble(3, empSalary);
			preparedStatement.setString(4, startDate);
			preparedStatement.setLong(5, empPhone);
			preparedStatement.setString(6, empAdd);
			preparedStatement.setInt(7, comId);
			preparedStatement.setInt(8, deptId);
			
			preparedStatement.executeUpdate();
			
			preparedStatement = conn.prepareStatement(query2);
			preparedStatement.setDouble(1, empSalary);
			preparedStatement.setDouble(2, (0.2*empSalary));
			preparedStatement.setDouble(3, (empSalary-(0.2*empSalary)));
			preparedStatement.setDouble(4, (empSalary-(0.2*empSalary))*0.1);
			preparedStatement.setDouble(5, empSalary-((empSalary-(0.2*empSalary))*0.1));
			preparedStatement.executeUpdate();
			conn.commit();
			System.out.println("Data Inserted Successfully");
		}catch(Exception ex) {
			conn.rollback();
		}finally {
			connection.closeConnection();
		}
		
	}
	
	public void deleteEmployee() throws SQLException {
		Scanner sc6 = new Scanner(System.in);
		System.out.println("Enter the Emp ID to delete");
		int empId = sc6.nextInt();
		
		conn = connection.getConnection();
		try {
		conn.setAutoCommit(false);
		String query1 = "update employee_payroll set is_active=false where id =?";
		
		
		preparedStatement = conn.prepareStatement(query1);
		preparedStatement.setInt(1, empId);
		preparedStatement.executeUpdate();
		conn.commit();
		System.out.println("Employee deleted successfully");
		
		}catch(Exception ex) {
		conn.rollback();
		}finally {
			connection.closeConnection();
		}
	}
	
	public void showAllActive() throws SQLException {

		conn = connection.getConnection();
		String query = "SELECT * FROM employee_payroll where is_active=true";
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
			boolean is_active = rs.getBoolean(10);
			System.out.println("Emp ID : " + id + ", Name : " + name + ", Gender : " + gender + ", Salary : " + salary
					+ ", StartDate : " + startdate + ", Phone : " + phone + ", Add : "+address);
		}
		stmt.close();
		connection.closeConnection();
	}
}
