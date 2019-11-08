/**
* This is the DAO layer that implements the functionality that a participant is accessible to.
* A participant can give feedback for a particular training program.
*
* @author  Anjali Shaw, Anudeep Reddy Katta, Amit Kumar Baghel, Vanpriya Shukla
* @since   2019-09-28 
*/
package com.feedback.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.feedback.bean.Feedback;
import com.feedback.util.ConnectionFactory;

public class ParticipantRoleDAOImpl implements ParticipantRoleDAO{

ConnectionFactory conFactory = ConnectionFactory.getInstance();
final Logger logger;

//default constructor
	public ParticipantRoleDAOImpl() {
		PropertyConfigurator.configure(".\\resources\\log4j.properties");
		logger = Logger.getLogger(ParticipantRoleDAOImpl.class);
	}

@Override
public boolean verifyParticipant(int tCode,int pid) {
	boolean bn=false;
	String sql="Select PARTICIPANT_ID  from TRAINING_PARTICIPANT where  TRAINING_CODE=? ";
	try {
		Connection connection = ConnectionFactory.getInstance().getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);

		ps.setInt(1, tCode);

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			int code=rs.getInt("PARTICIPANT_ID");
			if(code==pid) {
				logger.info(" Participant Verified from DAO");
				return true;
			}
			else {
				bn=false;
				logger.info(" Participant Not Verified from DAO");
			}
		}
	} catch (SQLException e) {
		logger.error(e);
		e.printStackTrace();
	}

	return bn;
	
}
	
	@Override
	public int insert(Feedback fb) {
		int rows=0;
	
		String SQL = "INSERT INTO FEEDBACK_MASTER(TRAINING_CODE, PARTICIPANT_ID, FB_Prs_comm, FB_Clrfy_dbts, FB_TM,FB_HND_OUT, FB_HW_SW_NTWRK, COMMENTS, SUGGESTION) VALUES (?,?,?,?,?,?,?,?,?)";
		try (Connection conn = conFactory.getConnection();
				PreparedStatement pstat = conn.prepareStatement(SQL);) {
			pstat.setInt(1,fb.getTrainingCode());
			pstat.setInt(2,fb.getParticipantId());
			pstat.setInt(3,fb.getFbPrsComm());
			pstat.setInt(4,fb.getFbClrfyDbts());
			pstat.setInt(5,fb.getFbTm());
			pstat.setInt(6,fb.getFbHndOut());
			pstat.setInt(7,fb.getFbHwSwNtwrk());
			pstat.setString(8,fb.getComments());	
			pstat.setString(9,fb.getSuggestions());	
			rows = pstat.executeUpdate();
			
			System.out.println("Your feedback has been recorded.");
			logger.info("Feedback Recorded from DAO");
			System.out.println(fb.toString());
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		}
		return rows;
		
	}
}
