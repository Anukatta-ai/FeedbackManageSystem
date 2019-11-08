/**
* This is the Service layer that implements the functionality that an Admin is accessible to.
* An admin can :-
*	Add a course to database
*	Retrieve the data of a particular course
*	Retrieve the data of all the courses
*	Modify the data of a particular course
*	Delete the data of a particular course
*	Retrieve the average feedback
*	Retrieve report of all the faculty
*	Retrieve Defaulters list
*	Add a faculty-skill
*
* @author  Anjali Shaw, Anudeep Reddy Katta, Amit Kumar Baghel, Vanpriya Shukla
* @since   2019-09-28 
*/
package com.feedback.service;

import java.util.List;

import com.feedback.bean.Course;
import com.feedback.bean.FacultySkill;
import com.feedback.bean.FeedbackAverage;
import com.feedback.bean.FeedbackDefaulters;
import com.feedback.bean.FeedbackFaculty;
import com.feedback.dao.TrainingAdminRoleDAO;
import com.feedback.dao.TrainingAdminRoleDAOImpl;
import com.feedback.exception.CourseNotFoundException;
import com.feedback.exception.EmployeeNotFoundException;

public class TrainingAdminRoleServiceImpl implements TrainingAdminRoleService {

		TrainingAdminRoleDAO dao=new TrainingAdminRoleDAOImpl();
		@Override
		public int addFacultySkill(FacultySkill fs) throws EmployeeNotFoundException {
			return dao.insert(fs);
		}

		@Override
		public void createCourse(Course course) {
			 dao.addCourse(course);
		}

		@Override
		public List<Course> retrieveAllCourse() {
			return dao.getAllCourse();
		}

		@Override
		public Course retrieveCourse(int course_id_to_retrieve) throws CourseNotFoundException {
			return dao.getCourse(course_id_to_retrieve);
		}

		@Override
		public boolean updateCourse(Course course) {
			return dao.modifyCourse(course);
		}

		@Override
		public boolean deleteCourse(Course course) {
			return dao.removeCourse(course);
		}

		@Override
		public List<FeedbackAverage> getAvg() {
			List<FeedbackAverage> fba=dao.retriveAvg();
			return fba;
		}
		
		@Override
		public List<FeedbackDefaulters> getAllDefaulters() {
			return dao.retrieveDefaulters();
		}

		@Override
		public List<FeedbackFaculty> facultyreport() {
			return dao.getAllFacultyReport();
		}

}
