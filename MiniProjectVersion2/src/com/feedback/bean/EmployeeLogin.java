/**
* This is the bean class for employee login. 
* 
* @author  Anjali Shaw, Anudeep Reddy Katta, Amit Kumar Baghel, Vanpriya Shukla
* @since   2019-09-28 
*/

package com.feedback.bean;

public class EmployeeLogin {
 
	private int username;
	private String password ;
	public int getUsername() {
		return username;
	}
	public void setUsername(int username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public EmployeeLogin() {
		super();
	
	}
	public EmployeeLogin(int username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	@Override
	public String toString() {
		return "EmployeeLogin [username=" + username + ", password=" + password + "]";
	}
	
	
	
	
	
}
