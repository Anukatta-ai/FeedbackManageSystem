/**
* This is the bean class for Faculty_Skill database with
* Faculty ID and Skill  as instances.
* Faculty ID references Employee ID from Employee_Master database. 
*
* @author  Anjali Shaw, Anudeep Reddy Katta, Amit Kumar Baghel, Vanpriya Shukla
* @since   2019-09-28 
*/
package com.feedback.bean;

public class FacultySkill {
	
	int facultyId;
	String skill;
	public int getFacultyId() {
		return facultyId;
	}
	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	@Override
	public String toString() {
		return "FacultySkill [facultyId=" + facultyId + ", skill=" + skill + "]";
	}
}
