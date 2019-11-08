/**
* This is the bean class for Feedback_Master database with
* training code, participant ID & feedback for Pres&comm, Clarifying doubts, Time
* management, hand-outs, hardware/software/network & comments, suggestion 
* as instances. 
* It aims to take feedback from the participants.
*
* @author  Anjali Shaw, Anudeep Reddy Katta, Amit Kumar Baghel, Vanpriya Shukla
* @since   2019-09-28 
*/

package com.feedback.bean;

public class Feedback {
	
	int trainingCode;
	int participantId;
	int fbPrsComm;
	int fbClrfyDbts;
	int fbTm;
	int fbHndOut;
	int fbHwSwNtwrk;
	String comments;
	String suggestion;
	
	
	public Feedback() {
		super();
	}
	public Feedback(int trainingCode, int participantId, int fbPrsComm, int fbClrfyDbts, int fbTm, int fbHndOut,
			int fbHwSwNtwrk, String comments, String suggestion) {
		super();
		this.trainingCode = trainingCode;
		this.participantId = participantId;
		this.fbPrsComm = fbPrsComm;
		this.fbClrfyDbts = fbClrfyDbts;
		this.fbTm = fbTm;
		this.fbHndOut = fbHndOut;
		this.fbHwSwNtwrk = fbHwSwNtwrk;
		this.comments = comments;
		this.suggestion = suggestion;
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
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getSuggestions() {
		return suggestion;
	}
	public void setSuggestions(String suggestions) {
		this.suggestion = suggestions;
	}
	@Override
	public String toString() {
		return "Feedback [trainingCode=" + trainingCode + ", participantId=" + participantId + ", fbPrsComm="
				+ fbPrsComm + ", fbClrfyDbts=" + fbClrfyDbts + ", fbTm=" + fbTm + ", fbHndOut=" + fbHndOut
				+ ", fbHwSwNtwrk=" + fbHwSwNtwrk + ", comments=" + comments + ", suggestions=" + suggestion + "]";
	}
	
	
}
