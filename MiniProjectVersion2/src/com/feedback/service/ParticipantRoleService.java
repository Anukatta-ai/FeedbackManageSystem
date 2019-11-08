package com.feedback.service;

import com.feedback.bean.Feedback;
import com.feedback.exception.ParticipantNotInEnrolledInCourse;

public interface ParticipantRoleService {

	public int addFeedback(Feedback fb);
	public void checkParticipant(int tCode,int pid) throws ParticipantNotInEnrolledInCourse;
}
