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
			System.out.println("1. Update BasePay\n2. Retrive all Employees\n3. Retrive Employees who"
					+ " joined beetween specific dates\n4. Analyse Employee Salary group by gender");
			option = sc3.nextInt();
			switch (option) {
			case 1:
				service.updateBasePay();
				break;
				
			case 2:
				service.showAll();
				break;
				
			case 3:
				service.showByDate();
				break;
				
			case 4:
				service.analyseEmployeeSalary();
				break;

			default:
				break;
			}
		}while(option != 5);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
