/**
* This is the bean class for Training_Program database with
* training code, courseCode,  facultyCode, startLocalDate, endLocalDate as instances. 
* Here, facultyCode references employee ID in the Employee_Master database & courseCode
* references course_Id in course_master database. 
* 
* @author  Anjali Shaw, Anudeep Reddy Katta, Amit Kumar Baghel, Vanpriya Shukla
* @since   2019-09-28 
*/

package com.feedback.bean;

import java.time.LocalDate;

public class TrainingProgram {

	private int trainingCode;
	private int courseCode;
	private int facultyCode;
	private LocalDate startLocalDate;
	private LocalDate endLocalDate;

	public TrainingProgram() {
	}

	public TrainingProgram(int trainingCode, int courseCode, int facultyCode, LocalDate startLocalDate, LocalDate endLocalDate) {
		super();
		this.trainingCode = trainingCode;
		this.courseCode = courseCode;
		this.facultyCode = facultyCode;
		this.startLocalDate = startLocalDate;
		this.endLocalDate = endLocalDate;
	}

	public int getTrainingCode() {
		return trainingCode;
	}

	public void setTrainingCode(int trainingCode) {
		this.trainingCode = trainingCode;
	}

	public int getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(int courseCode) {
		this.courseCode = courseCode;
	}

	public int getFacultyCode() {
		return facultyCode;
	}

	public void setFacultyCode(int facultyCode) {
		this.facultyCode = facultyCode;
	}

	public LocalDate getStartLocalDate() {
		return startLocalDate;
	}

	public void setStartLocalDate(LocalDate startLocalDate) {
		this.startLocalDate = startLocalDate;
	}

	public LocalDate getEndLocalDate() {
		return endLocalDate;
	}

	public void setEndLocalDate(LocalDate endLocalDate) {
		this.endLocalDate = endLocalDate;
	}

	@Override
	public String toString() {
		return "TrainingProgram [trainingCode=" + trainingCode + ", courseCode=" + courseCode + ", facultyCode="
				+ facultyCode + ", startLocalDate=" + startLocalDate + ", endLocalDate=" + endLocalDate + "]";
	}

	
	
	
}
