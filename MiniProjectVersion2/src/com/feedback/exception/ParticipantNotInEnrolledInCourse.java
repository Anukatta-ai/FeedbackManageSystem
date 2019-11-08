/**
* This ParticipantNotInEnrolledInCourse class extends Exception & comes into picture 
* when no participant exists for a given course code.
* 
* @author  Anjali Shaw, Anudeep Reddy Katta, Amit Kumar Baghel, Vanpriya Shukla
* @since   2019-09-28 
*/

package com.feedback.exception;

public class ParticipantNotInEnrolledInCourse extends Exception{
	public ParticipantNotInEnrolledInCourse(String message) {
		super(message);
	}
}
