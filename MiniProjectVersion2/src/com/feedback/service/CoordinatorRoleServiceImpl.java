/**
* This is the Service layer that implements the functionality that a coordinator is accessible to.
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
package com.feedback.service;

import java.util.List;

import com.feedback.bean.FeedbackAverage;
import com.feedback.bean.FeedbackDefaulters;
import com.feedback.bean.FeedbackFaculty;
import com.feedback.bean.Participant;
import com.feedback.bean.TrainingProgram;
import com.feedback.dao.CoordinatorRoleDAO;
import com.feedback.dao.CoordinatorRoleDAOImpl;
import com.feedback.exception.TrainingNotFoundException;

public class CoordinatorRoleServiceImpl implements CoordinatorRoleService{

	CoordinatorRoleDAO dao=new CoordinatorRoleDAOImpl();
	@Override
	public void createTrainingProgram(TrainingProgram trainingProgram) throws TrainingNotFoundException {
	dao.addTrainingProgram(trainingProgram);	
	}

	@Override
	public List<TrainingProgram> retrieveAllTrainingPrograms() {
		return dao.getAllTrainingPrograms();
	}

	@Override
	public TrainingProgram retrieveTrainingProgram(int  training_id_to_retrieve) throws TrainingNotFoundException {
		return dao.getTrainingProgram(training_id_to_retrieve);
	}

	@Override
	public boolean updateTrainingProgram(TrainingProgram trainingProgram) throws TrainingNotFoundException {
		return dao.modifyTrainingProgram(trainingProgram);
	}

	@Override
	public boolean deleteTrainingProgram(TrainingProgram trainingProgram) {
		return dao.removeTrainingProgram(trainingProgram);
	}

	@Override
	public boolean addParticipantEnrollmentRecord(Participant participant) throws TrainingNotFoundException {
		return dao.insertParticipantEnrollmentRecord(participant);
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
