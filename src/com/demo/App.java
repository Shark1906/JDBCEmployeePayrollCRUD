package com.demo;

import java.sql.SQLException;
import java.util.Scanner;

public class App {
	public static void main(String[] args) {
		EmpPayrollService service = new EmpPayrollService();
		Scanner sc3 = new Scanner(System.in);
		try {
		int option;
		do {
			System.out.println("Select a option");
			System.out.println("1. Update Salary\n2. Retrive all Employees\n3. Retrive Active Employees\n4. Retrive Employees who"
					+ " joined beetween specific dates\n5. Analyse Employee Salary group by gender\n6. Add Employee"
					+ "\n7. Delete Employee\n8. Exit");
			option = sc3.nextInt();
			switch (option) {
			case 1:
				service.updateSalary();
				break;
				
			case 2:
				service.showAll();
				break;
				
			case 3:
				service.showAllActive();
				break;
				
			case 4:
				service.showByDate();
				break;
				
			case 5:
				service.analyseEmployeeSalary();
				break;
				
			case 6:
				service.addEmployeePayroll();
				break;

			case 7:
				service.deleteEmployee();
				break;

			default:
				break;
			}
		}while(option != 8);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
