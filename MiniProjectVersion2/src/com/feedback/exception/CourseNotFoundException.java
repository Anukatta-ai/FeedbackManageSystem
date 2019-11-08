/**
* This CourseNotFoundException class will come into picture 
*  when no course exists for a given course code.
* 
* @author  Anjali Shaw, Anudeep Reddy Katta, Amit Kumar Baghel, Vanpriya Shukla
* @since   2019-09-28 
*/
package com.feedback.exception;

public class CourseNotFoundException extends Exception{
	public CourseNotFoundException(String message) {
		super(message);
	}

}
