/**
* This is the Service layer that implements the login facility that an employee is entitled to.
*  If successfully logged in, then only the employee will be able to access the various operations 
*  depending upon his/her role in the company. 
*  
*
* @author  Anjali Shaw, Anudeep Reddy Katta, Amit Kumar Baghel, Vanpriya Shukla
* @since   2019-09-28 
*/
package com.feedback.service;

import com.feedback.dao.EmployeeLoginDao;
import com.feedback.dao.EmployeeLoginDaoImpl;

public class EmployeeLoginServiceImpl implements EmployeeLoginService {
	EmployeeLoginDao  dao=new EmployeeLoginDaoImpl();

	@Override
	public String checkLogin(int username, String password) {
						return dao.validateLogin(username, password );
	}

}
