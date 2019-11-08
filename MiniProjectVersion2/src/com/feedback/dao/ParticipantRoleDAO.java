package com.feedback.dao;

import com.feedback.bean.Feedback;


public interface ParticipantRoleDAO {

	int insert(Feedback fb);
	public boolean verifyParticipant(int tCode,int pid);

}
