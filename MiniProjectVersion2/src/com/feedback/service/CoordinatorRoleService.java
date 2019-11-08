package com.feedback.service;

import java.util.List;

import com.feedback.bean.FeedbackAverage;
import com.feedback.bean.FeedbackDefaulters;
import com.feedback.bean.FeedbackFaculty;
import com.feedback.bean.Participant;
import com.feedback.bean.TrainingProgram;
import com.feedback.exception.TrainingNotFoundException;

public interface CoordinatorRoleService {
	public void createTrainingProgram(TrainingProgram trainingProgram) throws TrainingNotFoundException;
	public List<TrainingProgram> retrieveAllTrainingPrograms();
	public TrainingProgram retrieveTrainingProgram(int  training_id_to_retrieve) throws TrainingNotFoundException;
	public boolean updateTrainingProgram(TrainingProgram  trainingProgram) throws TrainingNotFoundException;
	public boolean deleteTrainingProgram(TrainingProgram  trainingProgram);
	
	public boolean addParticipantEnrollmentRecord(Participant participant) throws TrainingNotFoundException;
	List<FeedbackAverage> getAvg();
	List<FeedbackDefaulters> getAllDefaulters();
	public List<FeedbackFaculty> facultyreport();

}
