/**
* This bean class aims to display the list of defaulters who haven't given the feedback.
*
* @author  Anjali Shaw, Anudeep Reddy Katta, Amit Kumar Baghel, Vanpriya Shukla
* @since   2019-09-28 
*/

package com.feedback.bean;

public class FeedbackDefaulters {
	
	int trainingCode;
	int participantId;
	int fbPrsComm;
	int fbClrfyDbts;
	int fbTm;
	int fbHndOut;
	int fbHwSwNtwrk;

	
	public FeedbackDefaulters(int trainingCode, int participantId, int fbPrsComm, int fbClrfyDbts, int fbTm,
			int fbHndOut, int fbHwSwNtwrk) {
		super();
		this.trainingCode = trainingCode;
		this.participantId = participantId;
		this.fbPrsComm = fbPrsComm;
		this.fbClrfyDbts = fbClrfyDbts;
		this.fbTm = fbTm;
		this.fbHndOut = fbHndOut;
		this.fbHwSwNtwrk = fbHwSwNtwrk;
	}
	public int getTrainingCode() {
		return trainingCode;
	}
	public void setTrainingCode(int trainingCode) {
		this.trainingCode = trainingCode;
	}
	public int getParticipantId() {
		return participantId;
	}
	public void setParticipantId(int participantId) {
		this.participantId = participantId;
	}
	public int getFbPrsComm() {
		return fbPrsComm;
	}
	public void setFbPrsComm(int fbPrsComm) {
		this.fbPrsComm = fbPrsComm;
	}
	public int getFbClrfyDbts() {
		return fbClrfyDbts;
	}
	public void setFbClrfyDbts(int fbClrfyDbts) {
		this.fbClrfyDbts = fbClrfyDbts;
	}
	public int getFbTm() {
		return fbTm;
	}
	public void setFbTm(int fbTm) {
		this.fbTm = fbTm;
	}
	public int getFbHndOut() {
		return fbHndOut;
	}
	public void setFbHndOut(int fbHndOut) {
		this.fbHndOut = fbHndOut;
	}
	public int getFbHwSwNtwrk() {
		return fbHwSwNtwrk;
	}
	public void setFbHwSwNtwrk(int fbHwSwNtwrk) {
		this.fbHwSwNtwrk = fbHwSwNtwrk;
	}

	@Override
	public String toString() {
		return "Feedback [trainingCode=" + trainingCode + ", participantId=" + participantId + ", fbPrsComm="
				+ fbPrsComm + ", fbClrfyDbts=" + fbClrfyDbts + ", fbTm=" + fbTm + ", fbHndOut=" + fbHndOut
				+ ", fbHwSwNtwrk=" + fbHwSwNtwrk + "]";
	}
	
	
}
