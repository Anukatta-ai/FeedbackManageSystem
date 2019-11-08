/**
* This is the DAO layer that implements the login facility that an employee is entitled to.
*  If successfully logged in, then only the employee will be able to access the various operations 
*  depending upon his/her role in the company. 
*
* @author  Anjali Shaw, Anudeep Reddy Katta, Amit Kumar Baghel, Vanpriya Shukla
* @since   2019-09-28 
*/
package com.feedback.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.feedback.util.ConnectionFactory;

public class EmployeeLoginDaoImpl implements EmployeeLoginDao {

	ConnectionFactory conFactory = ConnectionFactory.getInstance();
	final Logger logger;

	// default constructor
	public EmployeeLoginDaoImpl() {
		PropertyConfigurator.configure(".\\resources\\log4j.properties");
		logger = Logger.getLogger(EmployeeLoginDaoImpl.class);
	}

	@Override
	public String validateLogin(int username, String password) {
		String role=null;
		String queryString = "Select Role,Employee_Id,password  from Employee_Master ";

		try (Connection conn = conFactory.getConnection();
				PreparedStatement ptmt = conn.prepareStatement(queryString);) {

			ResultSet rst = ptmt.executeQuery();
			while (rst.next()) {

				int userid = rst.getInt("Employee_Id");
				String password1 = rst.getString("password");
				if ((username == userid) && (password1.equals(password))) {
					role=rst.getString("role");
					logger.info("DAO Login Validated");
					return role;
				}
			}

		} catch (SQLException err) {
			logger.error(err);
		}
		return "Employee Not Found";
	}

}
