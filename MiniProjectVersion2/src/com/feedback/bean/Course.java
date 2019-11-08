/**
* This is the bean class for Course_Master database with
* Course ID, Course Name and Number of days assigned for the course as instances. 
*
* @author  Anjali Shaw, Anudeep Reddy Katta, Amit Kumar Baghel, Vanpriya Shukla
* @since   2019-09-28 
*/

package com.feedback.bean;

public class Course {
	
	private int courseId;
	private String courseName;
	private int noOfDays;
	
	public Course() {
	}

	public Course(int courseId, String courseName, int noOfDays) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.noOfDays = noOfDays;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getNoOfDays() {
		return noOfDays;
	}

	public void setNoOfDays(int noOfDays) {
		this.noOfDays = noOfDays;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", noOfDays=" + noOfDays + "]";
	}
	
	
	

}
