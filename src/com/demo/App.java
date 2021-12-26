package com.demo;

import java.sql.SQLException;

public class App {
	public static void main(String[] args) {
		EmpPayrollService service = new EmpPayrollService();
		try {
			service.updateBasePay();
			service.showAll();
			service.showByDate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
