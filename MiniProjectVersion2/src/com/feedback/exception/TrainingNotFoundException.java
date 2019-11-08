/**
* This TrainingNotFoundException class extends Exception & comes into picture 
* when no training program exists for a given training code.
* 
* @author  Anjali Shaw, Anudeep Reddy Katta, Amit Kumar Baghel, Vanpriya Shukla
* @since   2019-09-28 
*/
package com.feedback.exception;

public class TrainingNotFoundException extends Exception{
	public TrainingNotFoundException(String message) {
		super(message);
	}
}
