package com.feedback.dao;

import java.util.List;

import com.feedback.bean.FeedbackAverage;
import com.feedback.bean.FeedbackDefaulters;
import com.feedback.bean.FeedbackFaculty;
import com.feedback.bean.Participant;
import com.feedback.bean.TrainingProgram;
import com.feedback.exception.TrainingNotFoundException;

public interface CoordinatorRoleDAO {
	
	public void addTrainingProgram(TrainingProgram trainingProgram) throws TrainingNotFoundException;
	public List<TrainingProgram> getAllTrainingPrograms();
	public TrainingProgram getTrainingProgram(int training_id_to_retrieve) throws TrainingNotFoundException;
	public boolean modifyTrainingProgram(TrainingProgram trainingProgram) throws TrainingNotFoundException;
	public boolean removeTrainingProgram(TrainingProgram trainingProgram);
	public boolean insertParticipantEnrollmentRecord(Participant participant) throws TrainingNotFoundException;
	 List<FeedbackAverage> retriveAvg();
	 public List<FeedbackFaculty> getAllFacultyReport();
	 List<FeedbackDefaulters> retrieveDefaulters();
	

}
