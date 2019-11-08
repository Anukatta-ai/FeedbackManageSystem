package com.feedback.service;

import java.util.List;

import com.feedback.bean.Course;
import com.feedback.bean.FacultySkill;
import com.feedback.bean.FeedbackAverage;
import com.feedback.bean.FeedbackDefaulters;
import com.feedback.bean.FeedbackFaculty;
import com.feedback.exception.CourseNotFoundException;
import com.feedback.exception.EmployeeNotFoundException;

public interface TrainingAdminRoleService {
	public int addFacultySkill(FacultySkill fs) throws EmployeeNotFoundException;
	public void createCourse(Course course);
	public List<Course> retrieveAllCourse();
	public Course retrieveCourse(int course_id_to_retrieve) throws CourseNotFoundException;
	public boolean updateCourse(Course course);
	public boolean deleteCourse(Course course);
	List<FeedbackAverage> getAvg();
	List<FeedbackDefaulters> getAllDefaulters();
	public List<FeedbackFaculty> facultyreport(); 

}
