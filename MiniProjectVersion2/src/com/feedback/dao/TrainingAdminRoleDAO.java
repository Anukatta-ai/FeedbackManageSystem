package com.feedback.dao;

import java.util.List;

import com.feedback.bean.Course;
import com.feedback.bean.FacultySkill;
import com.feedback.bean.FeedbackAverage;
import com.feedback.bean.FeedbackDefaulters;
import com.feedback.bean.FeedbackFaculty;
import com.feedback.exception.CourseNotFoundException;
import com.feedback.exception.EmployeeNotFoundException;

public interface TrainingAdminRoleDAO {
	
	int insert(FacultySkill fs) throws EmployeeNotFoundException;
	public void addCourse(Course course);
	public List<Course> getAllCourse();
	public Course getCourse(int course_id_to_retrieve) throws CourseNotFoundException;
	public boolean modifyCourse(Course course);
	public boolean removeCourse(Course course);
	 List<FeedbackAverage> retriveAvg();
	 public List<FeedbackFaculty> getAllFacultyReport();
	 List<FeedbackDefaulters> retrieveDefaulters();
	
	

}
