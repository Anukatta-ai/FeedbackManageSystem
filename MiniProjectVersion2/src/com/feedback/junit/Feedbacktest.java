package com.feedback.junit;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.feedback.bean.Course;
import com.feedback.bean.Feedback;
import com.feedback.bean.TrainingProgram;
import com.feedback.dao.CoordinatorRoleDAOImpl;
import com.feedback.dao.EmployeeLoginDao;
import com.feedback.dao.EmployeeLoginDaoImpl;
import com.feedback.dao.TrainingAdminRoleDAOImpl;
import com.feedback.exception.CourseNotFoundException;
import com.feedback.exception.TrainingNotFoundException;



public class Feedbacktest {
	
	static TrainingAdminRoleDAOImpl tad;
	static CoordinatorRoleDAOImpl crd;
	static EmployeeLoginDao eld;
	@BeforeClass
	public static void beforeClass() {
		System.out.println("FeedbackTest->begin");
		tad=new TrainingAdminRoleDAOImpl();
		crd=new CoordinatorRoleDAOImpl();
		eld=new EmployeeLoginDaoImpl();		
	}
	
	@Test
	public void validateLogin() {
		int empid=998;
		String pass="anjali1"; 
		String actual="Coordinator";
		System.out.println(eld.validateLogin(empid, pass));
		String expected=eld.validateLogin(empid, pass);
		assertEquals(expected, actual);
	}

	@Test
	public void checkFeedbackRetrival() {
		int actual=5;
		int expected=crd.getAllFacultyReport().size();
		assertEquals(expected, actual);
	}
	@Test
	public void getCourse() throws CourseNotFoundException{
		Course actual=new Course();
		actual.setCourseId(124);
		actual.setCourseName("BI");
		actual.setNoOfDays(50);
		Course  expected=tad.getCourse(124);
		assertEquals(expected.toString(), actual.toString());
		
	}
	
	@Test(expected=CourseNotFoundException.class)
	public void DeleteCourseTest() throws CourseNotFoundException {
		Course course=new Course(200,"test",50);
		tad.addCourse(course);
		boolean  actual=tad.removeCourse(course);
		Course expected1=tad.getCourse(200);
		boolean expected=false;
		if(expected1==null) {
			expected=true;
		}else {
			expected=false;
		}
		assertEquals(expected, actual);
	}
	
	@Test(expected=CourseNotFoundException.class)
	public void DeleteCourseTest1() throws CourseNotFoundException {
		Course course=new Course(200,"test",50);
		tad.addCourse(course);
		boolean  actual=tad.removeCourse(course);
		Course expected1=tad.getCourse(200);
		boolean expected=false;
		if(expected1==null) {
			expected=true;
		}else {
			expected=false;
		}
		assertEquals(expected, actual);
	}
	
	
	
	@Test(expected=CourseNotFoundException.class)
	public void getCourse2() throws CourseNotFoundException{
		Course actual=new Course();
		actual.setCourseId(1000);
		int  expected=tad.getCourse(1000).getCourseId();
		assertEquals(expected, actual.getCourseId());
		
	}
	
	@Test
	public void getTrainingProgram() throws TrainingNotFoundException{
		TrainingProgram actual=new TrainingProgram();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		actual.setTrainingCode(1010);
		actual.setCourseCode(98);
		actual.setFacultyCode(997);
		actual.setStartLocalDate(LocalDate.parse("20-08-2019", dtf));
		actual.setEndLocalDate(LocalDate.parse("19-09-2019", dtf));
		TrainingProgram  expected=crd.getTrainingProgram(1010);
		assertEquals(expected.toString(), actual.toString());
		
	}
	
	@Test(expected=TrainingNotFoundException.class)
	public void getTrainingProgram2() throws TrainingNotFoundException{
		TrainingProgram actual=new TrainingProgram();
		actual.setTrainingCode(1000);
		int  expected=crd.getTrainingProgram(1000).getCourseCode();
		assertEquals(expected, actual.getTrainingCode());
		
	}
	
	@AfterClass
	public static void afterClass() {
		System.out.println("FeedbackTest->teardown");
	}
}
