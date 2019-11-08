/**
* This EmployeeNotFoundException class will come into picture 
* when no employee exists for a given username & password.
* 
* @author  Anjali Shaw, Anudeep Reddy Katta, Amit Kumar Baghel, Vanpriya Shukla
* @since   2019-09-28 
*/
package com.feedback.exception;

public class EmployeeNotFoundException extends Exception{
	public EmployeeNotFoundException(String message) {
		super(message);
	}
}
