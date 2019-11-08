/**
* This is the bean class for Feedback_Master database with
* training code, participant ID & feedback for Pres&comm, Clarifying doubts, Time
* management, hand-outs, hardware/software/network & comments, suggestion 
* as instances. 
* It aims to display the average of the feedback given by the participants for a particular faculty.
*
* @author  Anjali Shaw, Anudeep Reddy Katta, Amit Kumar Baghel, Vanpriya Shukla
* @since   2019-09-28 
*/
package com.feedback.bean;

public class FeedbackAverage {

	int trainingCode;
	int facultyCode;
	double fbPrsComm;
	double fbClrfyDbts;
	double fbTm;
	double fbHndOut;
	double fbHwSwNtwrk;
	
	
	
	public FeedbackAverage() {
		super();
	}
	public FeedbackAverage(int trainingCode, int facultyCode, double fbPrsComm, double fbClrfyDbts, double fbTm,
			double fbHndOut, double fbHwSwNtwrk) {
		super();
		this.trainingCode = trainingCode;
		this.facultyCode = facultyCode;
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
	public int getFacultyCode() {
		return facultyCode;
	}
	public void setFacultyCode(int facultyCode) {
		this.facultyCode = facultyCode;
	}
	public double getFbPrsComm() {
		return fbPrsComm;
	}
	public void setFbPrsComm(double fbPrsComm) {
		this.fbPrsComm = fbPrsComm;
	}
	public double getFbClrfyDbts() {
		return fbClrfyDbts;
	}
	public void setFbClrfyDbts(double fbClrfyDbts) {
		this.fbClrfyDbts = fbClrfyDbts;
	}
	public double getFbTm() {
		return fbTm;
	}
	public void setFbTm(double fbTm) {
		this.fbTm = fbTm;
	}
	public double getFbHndOut() {
		return fbHndOut;
	}
	public void setFbHndOut(double fbHndOut) {
		this.fbHndOut = fbHndOut;
	}
	public double getFbHwSwNtwrk() {
		return fbHwSwNtwrk;
	}
	public void setFbHwSwNtwrk(double fbHwSwNtwrk) {
		this.fbHwSwNtwrk = fbHwSwNtwrk;
	}
	@Override
	public String toString() {
		return "FeebackAverage [trainingCode=" + trainingCode + ", facultyCode=" + facultyCode + ", fbPrsComm="
				+ fbPrsComm + ", fbClrfyDbts=" + fbClrfyDbts + ", fbTm=" + fbTm + ", fbHndOut=" + fbHndOut
				+ ", fbHwSwNtwrk=" + fbHwSwNtwrk + "]";
	}
	
	
}
