/**
* This is the DAO layer that implements the functionality that an Admin is accessible to.
* An admin can :-
*	Add a course to database
*	Retrieve the data of a particular course
*	Retrieve the data of all the courses
*	Modify the data of a particular course
*	Delete the data of a particular course
*	Retrieve the average feedback
*	Retrieve report of all the faculty
*	Retrieve Defaulters list
*	 Add a faculty-skill
*
* @author  Anjali Shaw, Anudeep Reddy Katta, Amit Kumar Baghel, Vanpriya Shukla
* @since   2019-09-28 
*/
package com.feedback.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.feedback.bean.Course;
import com.feedback.bean.FacultySkill;
import com.feedback.bean.FeedbackAverage;
import com.feedback.bean.FeedbackDefaulters;
import com.feedback.bean.FeedbackFaculty;
import com.feedback.exception.CourseNotFoundException;
import com.feedback.exception.EmployeeNotFoundException;
import com.feedback.util.ConnectionFactory;

public class TrainingAdminRoleDAOImpl implements TrainingAdminRoleDAO {


	ConnectionFactory conFactory = ConnectionFactory.getInstance();
	final Logger logger;

	// default constructor
		public TrainingAdminRoleDAOImpl() {
			PropertyConfigurator.configure(".\\resources\\log4j.properties");
			logger = Logger.getLogger(TrainingAdminRoleDAOImpl.class);
		}

	
	private boolean findId(int id) {
		String query="Select EMPLOYEE_ID from EMPLOYEE_MASTER where EMPLOYEE_ID=?";
		int count = 0;
		try (Connection conn = conFactory.getConnection();
				PreparedStatement ptmt = conn.prepareStatement(query);) {

			ptmt.setInt(1, id);
			ResultSet rs = ptmt.executeQuery();
			while (rs.next())
				count = rs.getInt("EMPLOYEE_ID");
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (count == id)
			return true;
		else {
			return false;
		}
		
	}
	
	@Override
	public int insert(FacultySkill fs) throws EmployeeNotFoundException {
		int rows=0;
		if(findId(fs.getFacultyId()))
		{
		String SQL = "INSERT INTO FACULTY_SKILL(FACULTY_ID,SKILL_SET) VALUES(?,?)";
		try (Connection conn = conFactory.getConnection();
				PreparedStatement pstat = conn.prepareStatement(SQL);) {
			pstat.setInt(1,fs.getFacultyId() );
			pstat.setString(2,fs.getSkill());	
			rows = pstat.executeUpdate();
			if(rows>0) {
				System.out.println("Faculty added");
				logger.info("Faculty added");
			}
			return rows;
			
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		}
		return 0;
	}
		else
			throw new EmployeeNotFoundException("No such Faculty exists, hence can't be added!");
	}

	@Override
	public void addCourse(Course course) {

		int courseSeqId = 0;
		String sqlSequence = "SELECT course_id_seq.NEXTVAL from dual";
		String SQL = "INSERT INTO  COURSE_MASTER(COURSE_ID, COURSE_NAME, NO_OF_DAYS) VALUES (?,?,?)";
		try {
			Connection connection = ConnectionFactory.getInstance().getConnection();
			PreparedStatement pst = connection.prepareStatement(sqlSequence);
			PreparedStatement ps = connection.prepareStatement(SQL);
			synchronized (this) {
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					courseSeqId = rs.getInt(1);
					course.setCourseId(courseSeqId);
					logger.info("Course Added from DAO");
				}
			}

			ps.setInt(1, courseSeqId);
			ps.setString(2, course.getCourseName());
			ps.setInt(3, course.getNoOfDays());

			int executeUpdate = ps.executeUpdate();

			if (executeUpdate == 1) {
				System.out.println("Course has been added.");
				System.out.println(course.toString());
			}
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		}

	}

	@Override
	public List<Course> getAllCourse() {

		List<Course> courseList = new ArrayList<Course>();
		String SQL = "SELECT * FROM COURSE_MASTER";

		try {
			Connection connection = ConnectionFactory.getInstance().getConnection();
			PreparedStatement ps = connection.prepareStatement(SQL);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Course course = new Course();
				course.setCourseId(rs.getInt("COURSE_ID"));
				course.setCourseName(rs.getString("COURSE_NAME"));
				course.setNoOfDays(rs.getInt("NO_OF_DAYS"));
				courseList.add(course);
			}

			for (Course retrievedCourse : courseList) {
				System.out.println(retrievedCourse);
			}

		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		}
		return courseList;
	}

	@Override
	public Course getCourse(int course_id_to_retrieve) throws CourseNotFoundException {

		String SQL = "SELECT * FROM COURSE_MASTER WHERE COURSE_ID = ?";
		Course course=null;

		try {
			Connection connection = ConnectionFactory.getInstance().getConnection();
			PreparedStatement ps = connection.prepareStatement(SQL);

			ps.setInt(1, course_id_to_retrieve);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				course=new Course();
				String course_Name = rs.getString("COURSE_NAME");
				int no_Of_Days = rs.getInt("NO_OF_DAYS");
				course.setCourseId(course_id_to_retrieve);
				course.setCourseName(course_Name);
				course.setNoOfDays(no_Of_Days);
			}
			if(course!=null) {
			System.out.println(course.toString());
			logger.info("Course retrieved Throuh DAO");
			}
			else {
				logger.info("Course not retrieved throuh DAO");
				throw new CourseNotFoundException("No such Course exists");
			}
		}
		 catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		}

		return course;
	}

	@Override
	public boolean modifyCourse(Course course) {
		
		int result = 0;
		String SQL = "UPDATE COURSE_MASTER SET COURSE_NAME = ?, NO_OF_DAYS = ? WHERE COURSE_ID = ?";
		
		try {
			Connection connection = ConnectionFactory.getInstance().getConnection();
			PreparedStatement ps = connection.prepareStatement(SQL);
			
			ps.setString(1, course.getCourseName());
			ps.setInt(2, course.getNoOfDays());
			ps.setInt(3, course.getCourseId());
			
			
			result = ps.executeUpdate();
			System.out.println("The updated data for the Course ID "+ course.getCourseId() +" is : \n" +course.toString());
					
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		}
		if (result ==1) {
			logger.info("Course Modified from DAO");
			return true;
		}
		else {
			logger.info("Course Not Modified from DAO");
			return false;
		}
	}

	@Override
	public boolean removeCourse(Course course) {
		
		int result = 0;
		String SQL = "DELETE FROM COURSE_MASTER WHERE COURSE_ID = ?";
		
		try {
			Connection connection = ConnectionFactory.getInstance().getConnection();
			PreparedStatement ps = connection.prepareStatement(SQL);
			
			ps.setInt(1, course.getCourseId());
			
			result = ps.executeUpdate();
			
			
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		}
		if(result == 1) {
			System.out.println("Data for the Course ID "+course.getCourseId()+" has been deleted.");
			logger.info("Course Removed");
			return true;
		}
		else {
			logger.info("Course could not be removed");
			return false;
		}
	}

	@Override
	public List<FeedbackAverage> retriveAvg() {
		List<FeedbackAverage> list = new ArrayList<FeedbackAverage>();
		String queryString = "select f.TRAINING_CODE as tc,t.FACULTY_CODE as fc, avg(f.FB_Prs_comm) as fpc,avg(f.FB_Clrfy_dbts) as fcd,avg(f.FB_TM) as ft,avg(f.FB_HND_OUT) as fho,avg(f.FB_HW_SW_NTWRK) as fhsn from FEEDBACK_MASTER f, TRAINING_PROGRAM t where t.TRAINING_CODE=f.TRAINING_CODE GROUP BY f.TRAINING_CODE,t.FACULTY_CODE";
		try (Connection conn = conFactory.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(queryString);) {
			while (rs.next()) {
				
				list.add(new FeedbackAverage(rs.getInt("tc"),rs.getInt("fc"), rs.getDouble("fpc"),rs.getDouble("fcd"), rs.getDouble("ft"),
						rs.getDouble("fho"),rs.getDouble("fhsn")));
				
			}
			for (FeedbackAverage fa : list) {
				System.out.println(fa);
			}
			logger.info("Average report generated");
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public List<FeedbackFaculty> getAllFacultyReport() {
		List<FeedbackFaculty> list = new ArrayList<FeedbackFaculty>();
		String queryString = "select t.Faculty_Code as tc, avg(f.FB_Prs_comm) as fpc,avg(f.FB_Clrfy_dbts) as fcd,avg(f.FB_TM) as ft,avg(f.FB_HND_OUT) as fho,avg(f.FB_HW_SW_NTWRK) as fhsn from FEEDBACK_MASTER f, TRAINING_PROGRAM t where t.TRAINING_CODE=f.TRAINING_CODE GROUP BY t.Faculty_Code";
		try (Connection conn = conFactory.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(queryString);) {
			while (rs.next()) {
				
				list.add(new FeedbackFaculty(rs.getInt("tc"), rs.getDouble("fpc"),rs.getDouble("fcd"), rs.getDouble("ft"),
						rs.getDouble("fho"),rs.getDouble("fhsn")));
				
			}
			for (FeedbackFaculty fa : list) {
				System.out.println(fa);
			}
			logger.info("Report generated");
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<FeedbackDefaulters> retrieveDefaulters() {
		List<FeedbackDefaulters> list = new ArrayList<FeedbackDefaulters>();
		String queryString =  
				"WITH R AS (select * from  TRAINING_PARTICIPANT   where (TRAINING_CODE,PARTICIPANT_ID)  NOT IN (SELECT TRAINING_CODE,PARTICIPANT_ID FROM FEEDBACK_MASTER)\r\n" + 
				") SELECT  R.TRAINING_CODE,R.PARTICIPANT_ID,F.FB_Prs_comm,F.FB_Clrfy_dbts\r\n" + 
				",F.FB_TM,F.FB_HND_OUT,F.FB_HW_SW_NTWRK FROM R \r\n" + 
				"LEFT JOIN  FEEDBACK_MASTER F ON F.TRAINING_CODE=R.TRAINING_CODE and F.PARTICIPANT_ID=R.PARTICIPANT_ID\r\n" + 
				"LEFT JOIN  FEEDBACK_MASTER F ON F.TRAINING_CODE=R.TRAINING_CODE and F.PARTICIPANT_ID=R.PARTICIPANT_ID\r\n" + 
				"UNION \r\n" + 
				"select  t.TRAINING_CODE,t.PARTICIPANT_ID,f.FB_Prs_comm,f.FB_Clrfy_dbts\r\n" + 
				",f.FB_TM,f.FB_HND_OUT,f.FB_HW_SW_NTWRK from TRAINING_PARTICIPANT t,FEEDBACK_MASTER f where f.TRAINING_CODE=t.TRAINING_CODE and f.PARTICIPANT_ID=t.PARTICIPANT_ID\r\n" + 
				"and \r\n" + 
				"(f.FB_Prs_comm is null or f.FB_Clrfy_dbts is null or f.FB_TM is null or f.FB_HND_OUT is null or f.FB_HW_SW_NTWRK is null)";
		
		try (Connection conn = conFactory.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(queryString);) {
			while (rs.next()) {
				
				list.add(new FeedbackDefaulters(rs.getInt("TRAINING_CODE"),rs.getInt("PARTICIPANT_ID"),rs.getInt("FB_Prs_comm"),rs.getInt("FB_Clrfy_dbts"),
						rs.getInt("FB_TM"),rs.getInt("FB_HND_OUT"),rs.getInt("FB_HW_SW_NTWRK")));
				
			}
			for (FeedbackDefaulters fa : list) {
				System.out.println(fa);
			}
			logger.info("Defaulters retrieved");
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		}
		return list;
	}

}
