/**
* This is the bean class which aims to retrieve the report of all the faculties.
*
* @author  Anjali Shaw, Anudeep Reddy Katta, Amit Kumar Baghel, Vanpriya Shukla
* @since   2019-09-28 
*/
package com.feedback.bean;

public class FeedbackFaculty {

	int facultyCode;
	double fbPrsComm;
	double fbClrfyDbts;
	double fbTm;
	double fbHndOut;
	double fbHwSwNtwrk;
	public int getTrainingCode() {
		return facultyCode;
	}
	public void setTrainingCode(int trainingCode) {
		this.facultyCode = trainingCode;
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
		return "FeedbackFaculty [FacultyCode=" + facultyCode + ", fbPrsComm=" + fbPrsComm + ", fbClrfyDbts="
				+ fbClrfyDbts + ", fbTm=" + fbTm + ", fbHndOut=" + fbHndOut + ", fbHwSwNtwrk=" + fbHwSwNtwrk + "]";
	}
	public FeedbackFaculty(int trainingCode, double fbPrsComm, double fbClrfyDbts, double fbTm, double fbHndOut,
			double fbHwSwNtwrk) {
		super();
		this.facultyCode = trainingCode;
		this.fbPrsComm = fbPrsComm;
		this.fbClrfyDbts = fbClrfyDbts;
		this.fbTm = fbTm;
		this.fbHndOut = fbHndOut;
		this.fbHwSwNtwrk = fbHwSwNtwrk;
	}
	
}
