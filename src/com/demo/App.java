package com.demo;

import java.sql.SQLException;

public class App
{
    public static void main( String[] args )
    {
            
//            try {
//                        Class.forName("com.mysql.cj.jdbc.Driver");
//                        System.out.println("Loaded Driver class");
//                } catch (ClassNotFoundException e) {
//                        e.printStackTrace();
//                }
//            Connection connection = null;
//			Statement stmt = null;
//            
//            try {
//                        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_service", "root", "1234");
//                        System.out.println("Connection Established");
//                       
//                } catch (SQLException e) {
//                        e.printStackTrace();
//                }finally {
//                        if(stmt != null) {
//                                try {
//                                        stmt.close();
//                                } catch (SQLException e) {
//                                        e.printStackTrace();
//                                }
//                        }
//                        if(connection != null) {
//                                try {
//                                        connection.close();
//                                } catch (SQLException e) {
//                                        e.printStackTrace();
//                                }
//                        }
//                }
    	EmpPayrollService service = new EmpPayrollService();
    	try {
			service.updateBasePay();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
