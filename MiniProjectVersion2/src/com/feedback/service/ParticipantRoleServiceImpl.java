/**
* This is the Service layer that implements the functionality that a participant is accessible to.
* A participant can give feedback for a particular training program.
*
* @author  Anjali Shaw, Anudeep Reddy Katta, Amit Kumar Baghel, Vanpriya Shukla
* @since   2019-09-28 
*/
package com.feedback.service;

import com.feedback.bean.Feedback;
import com.feedback.dao.ParticipantRoleDAO;
import com.feedback.dao.ParticipantRoleDAOImpl;
import com.feedback.exception.ParticipantNotInEnrolledInCourse;


public class ParticipantRoleServiceImpl implements ParticipantRoleService{

	ParticipantRoleDAO dao=new ParticipantRoleDAOImpl();
	@Override
	public int addFeedback(Feedback fb) {
		return dao.insert(fb);
	}
	
	@Override
	public void checkParticipant(int tCode,int pid) throws ParticipantNotInEnrolledInCourse{
		if(!dao.verifyParticipant(tCode, pid))
			throw new ParticipantNotInEnrolledInCourse("Participant not enrolled for this course");
	}

}
