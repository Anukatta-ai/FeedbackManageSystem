/**
* This is the bean class for Training_Participant database with
* training code, participant ID as instances. 
* Here, trainingProgramId references trainingCode instance of the TrainingProgram bean class &
* participantId references employee ID.
* 
* @author  Anjali Shaw, Anudeep Reddy Katta, Amit Kumar Baghel, Vanpriya Shukla
* @since   2019-09-28 
*/

package com.feedback.bean;

public class Participant {

	private int trainingProgramId;
	private int participantId;
	
	public Participant() {
	}

	public Participant(int trainingProgramId, int participantId) {
		super();
		this.trainingProgramId = trainingProgramId;
		this.participantId = participantId;
	}

	public int getTrainingProgramId() {
		return trainingProgramId;
	}

	public void setTrainingProgramId(int trainingId) {
		this.trainingProgramId = trainingId;
	}

	public int getParticipantId() {
		return participantId;
	}

	public void setParticipantId(int participantId2) {
		this.participantId = participantId2;
	}

	@Override
	public String toString() {
		return "ParticipantEnrollment [trainingProgramId=" + trainingProgramId + ", participantId=" + participantId
				+ "]";
	}
	
	
	
}
