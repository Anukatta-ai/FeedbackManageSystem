/**
* This is the DAO layer that implements the functionality that a coordinator is accessible to.
* A coordinator can :-
* 	Add a Training Program to database
*	Retrieve the data of a particular Training Program
*	Retrieve the data of all the Training Programs
*	Modify the data of a particular Training Program
*	Delete the data of a particular Training Program
*	Add Participant Enrollment Record
*	Retrieve the average feedback
*  Retrieve report of all the faculty
*	 Retrieve defaulter list
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.feedback.bean.FeedbackAverage;
import com.feedback.bean.FeedbackDefaulters;
import com.feedback.bean.FeedbackFaculty;
import com.feedback.bean.Participant;
import com.feedback.bean.TrainingProgram;
import com.feedback.exception.TrainingNotFoundException;
import com.feedback.util.ConnectionFactory;

public class CoordinatorRoleDAOImpl implements CoordinatorRoleDAO  {
	
	ConnectionFactory conFactory = ConnectionFactory.getInstance();
	
	final Logger logger;
	public CoordinatorRoleDAOImpl() {
		PropertyConfigurator.configure(".\\resources\\log4j.properties");
		logger = Logger.getLogger(CoordinatorRoleDAOImpl.class);
	}

	private boolean checkCourseCode(int code) {
		boolean bn=false;
		String sql="Select  COURSE_ID from COURSE_MASTER where  COURSE_ID=? ";
		try {
			Connection connection = ConnectionFactory.getInstance().getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setInt(1, code);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int course_code=rs.getInt("COURSE_ID");
				if(course_code==code) {
					return true;
				}
				else {
					bn=false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bn;
	}
	
	private boolean checkFacultyCode(int code) {
		boolean bn=false;
		String sql="Select  EMPLOYEE_ID from  EMPLOYEE_MASTER where EMPLOYEE_ID=? ";
		try {
			Connection connection = ConnectionFactory.getInstance().getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setInt(1, code);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int empId=rs.getInt("EMPLOYEE_ID");
				if(empId==code) {
					
					return true;
				}
				else
					bn=false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bn;
	}
	
	private boolean checkFacultySkill(int courseCode, int facultyCode) {
		String SQL1="SELECT COURSE_NAME FROM COURSE_MASTER WHERE COURSE_ID=?";
		String SQL2="SELECT SKILL_SET FROM FACULTY_SKILL WHERE FACULTY_ID=?";
		List<String> skills=new ArrayList<>();
		String skill_set=null,course_name=null;
		try {
			Connection connection = ConnectionFactory.getInstance().getConnection();
			PreparedStatement ps1 = connection.prepareStatement(SQL1);
			PreparedStatement ps2 = connection.prepareStatement(SQL2);
			ps1.setInt(1, courseCode);
			ps2.setInt(1, facultyCode);
			ResultSet rs = ps1.executeQuery();
			while (rs.next()) {
				course_name=rs.getString("course_name");
				
			}
			ResultSet rs1 = ps2.executeQuery();
			while (rs1.next()) {
				skill_set=rs1.getString("skill_set");
				skills.add(skill_set);
			}
			if(skills!=null && course_name!=null && skills.contains(course_name)) 
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private int checkDate(int code) {
		int days=0;
		String sql="SELECT NO_OF_DAYS from COURSE_MASTER WHERE COURSE_ID=?";
		try {
			Connection connection = ConnectionFactory.getInstance().getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setInt(1, code);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				days=rs.getInt("NO_OF_DAYS");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return days;
	}
	
	@Override
	public void addTrainingProgram(TrainingProgram trainingProgram) throws TrainingNotFoundException {

		int trainingSeqId = 0;
		String sqlSequence = "SELECT training_code_seq.NEXTVAL from dual";
		String SQL = "INSERT INTO  TRAINING_PROGRAM(TRAINING_CODE, COURSE_CODE, FACULTY_CODE, START_DATE, END_DATE) VALUES (?, ?, ?,?,?)";
		
		if(checkCourseCode(trainingProgram.getCourseCode())&& checkFacultyCode(trainingProgram.getFacultyCode())) {
			if(checkFacultySkill(trainingProgram.getCourseCode(), trainingProgram.getFacultyCode())) {
		try {
			Connection connection = ConnectionFactory.getInstance().getConnection();
			PreparedStatement pst = connection.prepareStatement(sqlSequence);
			PreparedStatement ps = connection.prepareStatement(SQL);
			synchronized (this) {
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					trainingSeqId = rs.getInt(1);
					trainingProgram.setTrainingCode(trainingSeqId);
				}
			}
			ps.setInt(1, trainingSeqId);
			ps.setInt(2, trainingProgram.getCourseCode());
			ps.setInt(3, trainingProgram.getFacultyCode());
			int days=checkDate(trainingProgram.getCourseCode());
			java.sql.Date mySqlDate = java.sql.Date.valueOf(trainingProgram.getStartLocalDate());
			ps.setDate(4, mySqlDate);
			System.out.println(days);
			System.out.println(trainingProgram.getStartLocalDate().plusDays(days));
			ps.setDate(5, java.sql.Date.valueOf(trainingProgram.getStartLocalDate().plusDays(days)));
			trainingProgram.setEndLocalDate(trainingProgram.getStartLocalDate().plusDays(days));
			int row = ps.executeUpdate();

			if (row == 1) {
				logger.info("DAO Training Program Added ");
				System.out.println("Training Program has been added.");
				System.out.println(trainingProgram.toString());
			} 
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		}
		}
		else
		{
			logger.error("DAO Faculty isn't mapped with skill");
			throw new TrainingNotFoundException("Faculty doesn't map with the skill");
		}
		}
		else
		{
			logger.info("Either course code or Faculty Code is absent, Couldn't be inserted");
			throw new TrainingNotFoundException("Either course code or Faculty Code is absent");
		}

	}

	@Override
	public List<TrainingProgram> getAllTrainingPrograms() {

		List<TrainingProgram> list = new ArrayList<>();
		String SQL = "SELECT * FROM TRAINING_PROGRAM";

		try {
			Connection connection = ConnectionFactory.getInstance().getConnection();
			PreparedStatement ps = connection.prepareStatement(SQL);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TrainingProgram trainingProgram = new TrainingProgram();
				trainingProgram.setTrainingCode(rs.getInt("TRAINING_CODE"));
				trainingProgram.setCourseCode(rs.getInt("COURSE_CODE"));
				trainingProgram.setFacultyCode(rs.getInt("FACULTY_CODE"));
				LocalDate sDate = rs.getDate("START_DATE").toLocalDate();
				trainingProgram.setStartLocalDate(sDate);
				LocalDate eDate = rs.getDate("END_DATE").toLocalDate();
				trainingProgram.setEndLocalDate(eDate);

				list.add(trainingProgram);
			}

			for (TrainingProgram retrievedProgram : list) {
				System.out.println(retrievedProgram);
			}
			logger.info("Retrived ALL Training Programs from DAO");

		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public TrainingProgram getTrainingProgram(int training_id_to_retrieve) throws TrainingNotFoundException {

		String SQL = "SELECT * FROM Training_Program WHERE TRAiNING_CODE = ?";
		TrainingProgram trainingProgram = null;
		try {
			Connection connection = ConnectionFactory.getInstance().getConnection();
			PreparedStatement ps = connection.prepareStatement(SQL);

			
			ps.setInt(1, training_id_to_retrieve);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				trainingProgram = new TrainingProgram();
				trainingProgram.setTrainingCode(rs.getInt("TRAINING_CODE"));
				trainingProgram.setCourseCode(rs.getInt("COURSE_CODE"));
				trainingProgram.setFacultyCode(rs.getInt("FACULTY_CODE"));
				LocalDate sDate = rs.getDate("START_DATE").toLocalDate();
				trainingProgram.setStartLocalDate(sDate);
				LocalDate eDate = rs.getDate("END_DATE").toLocalDate();
				trainingProgram.setEndLocalDate(eDate);
				System.out.println(trainingProgram.toString());
				logger.info("Training Program retrieved with the given id");
			}
			if(trainingProgram == null)
				throw new TrainingNotFoundException("No training exists with that id");
			
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		}

		return trainingProgram;
	
	}
	//look into this
	@Override
	public boolean modifyTrainingProgram(TrainingProgram trainingProgram) throws TrainingNotFoundException {
		int result = 0;
		String SQL = "UPDATE TRAINING_PROGRAM SET COURSE_CODE = ?, FACULTY_CODE = ?, START_DATE = ?, END_DATE = ? WHERE TRAiNING_CODE = ?";
		if(checkCourseCode(trainingProgram.getCourseCode())&& checkFacultyCode(trainingProgram.getFacultyCode())) {
			if(checkFacultySkill(trainingProgram.getCourseCode(), trainingProgram.getFacultyCode())) {
		try {
			Connection connection = ConnectionFactory.getInstance().getConnection();
			PreparedStatement ps = connection.prepareStatement(SQL);
			
			ps.setInt(1, trainingProgram.getCourseCode());
			ps.setInt(2, trainingProgram.getFacultyCode());
			java.sql.Date mySqlDate = java.sql.Date.valueOf(trainingProgram.getStartLocalDate());
			ps.setDate(3, mySqlDate);
			int days=checkDate(trainingProgram.getCourseCode());
			ps.setDate(4, java.sql.Date.valueOf(trainingProgram.getStartLocalDate().plusDays(days)));
			ps.setInt(5, trainingProgram.getTrainingCode());
			trainingProgram.setEndLocalDate(trainingProgram.getStartLocalDate().plusDays(days));
			result = ps.executeUpdate();
			System.out.println("The updated data for the Training ID "+ trainingProgram.getTrainingCode() +" is : \n" + trainingProgram.toString());
			logger.info("Modified Training Program from DAO");		
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		}
			}
		else
		{
			throw new TrainingNotFoundException("Faculty doesn't map with the skill");
		}
		}else
			throw new TrainingNotFoundException("Either course code or Faculty Code is absent");
		if (result ==1)
			return true;
		else
			return false;
	}

	@Override
	public boolean removeTrainingProgram(TrainingProgram trainingProgram) {
		int result = 0;
		String SQL = "DELETE FROM TRAINING_PROGRAM WHERE TRAINING_CODE = ?";
		
		try {
			Connection connection = ConnectionFactory.getInstance().getConnection();
			PreparedStatement ps = connection.prepareStatement(SQL);
			
			ps.setInt(1, trainingProgram.getTrainingCode());
			
			result = ps.executeUpdate();
			
			
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		}
		if(result == 1) {
			System.out.println("Data for the Training ID "+ trainingProgram.getTrainingCode() +" has been deleted.");
			logger.info("Training program removed from DAO");
			return true;
		}
		else {
			logger.info("Training program not removed from DAO");
			return false;
		}
	}


	private boolean checkTrainingCode(int tCode) {
		boolean bn=false;
		String sql="Select  TRAINING_CODE from TRAINING_PROGRAM where  TRAINING_CODE=? ";
		try {
			Connection connection = ConnectionFactory.getInstance().getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setInt(1, tCode);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int code=rs.getInt("TRAINING_CODE");
				if(code==tCode)
					return true;
				else
					bn=false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bn;
		
	}
	
	private boolean checkParticipantId(int id) {
		boolean bn=false;
		String sql="Select  EMPLOYEE_ID from EMPLOYEE_MASTER where  EMPLOYEE_ID= ? ";
		try {
			Connection connection = ConnectionFactory.getInstance().getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int empId=rs.getInt("EMPLOYEE_ID");
				if(id==empId)
					return true;
				else
					bn=false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bn;
		
	}
	@Override
	public boolean insertParticipantEnrollmentRecord(Participant participant) throws TrainingNotFoundException {
		
		int result=0;
		String SQL = "INSERT INTO TRAINING_PARTICIPANT(TRAINING_CODE, PARTICIPANT_ID ) VALUES (?, ?)";
		if(checkTrainingCode(participant.getTrainingProgramId()) && checkParticipantId(participant.getParticipantId())) {

		try {
			Connection connection = ConnectionFactory.getInstance().getConnection();
			PreparedStatement ps = connection.prepareStatement(SQL);
			
			
			ps.setLong(1, participant.getTrainingProgramId());
			ps.setLong(2, participant.getParticipantId());
	
			
			result = ps.executeUpdate();
			
			
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		}
		if(result==1) {
			System.out.println(participant.toString());
			logger.info("ParticipantEnrollmentRecord Added from DAO");
		return true;
		} else {
			logger.info("ParticipantEnrollmentRecord NOT Added from DAO");
			return false;
		}}
		else
		{
			throw new TrainingNotFoundException("Either training or employee doesn't exist. Can't be enrolled");
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
				
			}for (FeedbackAverage fa : list) {
				System.out.println(fa);
			}
			logger.info("Retrived Feedback Average from DAO");
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
			logger.info("Got All faculty Report from DAO");
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<FeedbackDefaulters> retrieveDefaulters() {
		List<FeedbackDefaulters> list = new ArrayList<FeedbackDefaulters>();
		String queryString =  "WITH R AS (select * from  TRAINING_PARTICIPANT   where (TRAINING_CODE,PARTICIPANT_ID)  NOT IN (SELECT TRAINING_CODE,PARTICIPANT_ID FROM FEEDBACK_MASTER)\r\n" + 
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
			logger.info("Retrieved all defaulters Report from DAO");
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		}
		return list;
	}


}
