/**
* The Feedback Management System implements an application that
* allows a participant to enter feedback for a particular trainer and displays
*  the same to the Admin & Co-ordinator.
*
* @author  Anjali Shaw, Anudeep Reddy Katta, Amit Kumar Baghel, Vanpriya Shukla
* @since   2019-09-28 
*/

package com.feedback.main;

//import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.feedback.bean.Course;
import com.feedback.bean.FacultySkill;
import com.feedback.bean.Feedback;
import com.feedback.bean.Participant;
import com.feedback.bean.TrainingProgram;
import com.feedback.exception.CourseNotFoundException;
import com.feedback.exception.EmployeeNotFoundException;
import com.feedback.exception.ParticipantNotInEnrolledInCourse;
import com.feedback.exception.TrainingNotFoundException;
import com.feedback.service.CoordinatorRoleService;
import com.feedback.service.CoordinatorRoleServiceImpl;
import com.feedback.service.EmployeeLoginService;
import com.feedback.service.EmployeeLoginServiceImpl;
import com.feedback.service.ParticipantRoleService;
import com.feedback.service.ParticipantRoleServiceImpl;
import com.feedback.service.TrainingAdminRoleService;
import com.feedback.service.TrainingAdminRoleServiceImpl;
import com.feedback.util.Validations;

public class Main {
	
	static  Logger logger;

	// default constructor
	public Main() {
		PropertyConfigurator.configure(".\\resources\\log4j.properties");
		logger = Logger.getLogger(Main.class);
	}
	
	public static void main(String[] args) {
		
		
		
		String role = "";
		EmployeeLoginService service = new EmployeeLoginServiceImpl();
		// TrainingProgram trainingProgram = new TrainingProgram();
		Course course = new Course();
		System.out.println("Login to access the Feedback Management System\n");
		Scanner s = new Scanner(System.in);
		System.out.println("Enter your Employee ID : ");
		int id = s.nextInt();
		System.out.println("Enter your Password : ");
		String password = s.next();
		role = service.checkLogin(id, password);

		switch (role) {
		case "Admin":
			char c = 0;
			// System.out.println("enter 1...");
			
			do{
			int adminTask = 0;
			System.out.println("Hello Admin!\nWelcome to the Feedback Management System\n\n");
			System.out.println("Press 1 for: Adding a course to database");
			System.out.println("Press 2 for: Retrieving the data of a particular course");
			System.out.println("Press 3 for: Retrieving the data of all the courses");
			System.out.println("Press 4 for: Modifying the data of a particular course");
			System.out.println("Press 5 for: Deleting the data of a particular course");
			System.out.println("Press 6 for: Retrieving the average feedback");
			System.out.println("Press 7 for: Retrieving report of all the faculty");
			System.out.println("Press 8 for: Retrieving Defaulters list");
			System.out.println("Press 9 for: Adding a faculty-skill");
			System.out.println("Press 10 for: To exit");
			
			adminTask = s.nextInt();
			TrainingAdminRoleService trainingService = new TrainingAdminRoleServiceImpl();
			switch (adminTask) {
			case 1: 
				String course_name=null;
				int numberOfDays=0;
				System.out.println("Enter course name : ");
				s.nextLine();
				course_name = s.nextLine();
				System.out.println("Enter number of days to be assigned on the given course : ");
				numberOfDays = s.nextInt();
				course.setCourseName(course_name);
				course.setNoOfDays(numberOfDays);

				trainingService.createCourse(course);

				break;

			case 2: //exception
				try {
					System.out.println("Enter Course ID : ");
					int course_id_to_retrieve = s.nextInt();
					//course.setCourseId(course_id_to_retrieve);
					trainingService.retrieveCourse(course_id_to_retrieve);
				} catch (CourseNotFoundException e) {
					logger.error(e);
					System.err.println(e);
				}

				break;

			case 3:
				trainingService.retrieveAllCourse();

				break;

			case 4: 
				
				try {
					System.out.println("Enter the Course ID of the course you want to update : ");
					int course_id_to_update = s.nextInt();
					System.out.println("The course you want to update is : ");
					trainingService.retrieveCourse(course_id_to_update);

					System.out.println("\n");

					System.out.println("Enter the new updated name for the course : ");
					String updatedCourseName = s.next();
					System.out.println("Enter the updated number of days to be assigned for the course : ");
					int updatedNoOfDays = s.nextInt();
					course.setCourseId(course_id_to_update);
					course.setCourseName(updatedCourseName);
					course.setNoOfDays(updatedNoOfDays);

					trainingService.updateCourse(course);
				} catch (CourseNotFoundException e2) {
					logger.error(e2);
					System.err.println(e2);
				}


				break;

			case 5: 
				
				try {
					System.out.println("Enter the Course ID of the course you want to delete : ");
					int course_id_to_delete = s.nextInt();
					//course.setCourseId(course_id_to_delete);
					System.out.println("The course you want to delete is : ");
					Course course1=trainingService.retrieveCourse(course_id_to_delete);
					trainingService.deleteCourse(course1);
				} catch (CourseNotFoundException e2) {
					logger.error(e2);
					System.err.println(e2);
				}

				break;

			case 6:
				trainingService.getAvg();

				break;
			case 7:
				trainingService.facultyreport(); // print in daoImpl
				break;

			case 8:
				trainingService.getAllDefaulters();
				break;
			case 9:
				try {
					FacultySkill fs=new FacultySkill();
					System.out.println("Enter Faculty ID");
					int facultyId=s.nextInt();
					fs.setFacultyId(facultyId);
					System.out.println("Enter the skill of the faculty");
					s.nextLine();
					String facultySkill=s.nextLine();
					fs.setSkill(facultySkill);
					trainingService.addFacultySkill(fs);
				} catch (EmployeeNotFoundException e2) {
					logger.error(e2);
					System.err.println(e2);
				}
				break;
			case 10:
				System.exit(0);
				break;
			default:
				System.out.println("Press a number between 1 to 10");

			}
			System.out.println("Enter Y or y if you want to continue");
			c=s.next().charAt(0);
			}while(c=='Y'||c=='y');
			break;
		case "Coordinator":
			do {
			int coordinatorTask = 0;
			System.out.println("Hello Coordinator!\nWelcome to the Feedback Management System\n\n");
			System.out.println("Press 1 for: Adding a Training Program to database");
			System.out.println("Press 2 for: Retrieving the data of a particular Training Program");
			System.out.println("Press 3 for: Retrieving the data of all the Training Programs");
			System.out.println("Press 4 for: Modifying the data of a particular Training Program");
			System.out.println("Press 5 for: Deleting the data of a particular Training Program");
			System.out.println("Press 6 for: Adding Participant Enrollment Record");
			System.out.println("Press 7 for: Retrieving the average feedback");
			System.out.println("Press 8 for: Retrieving report of all the faculty");
			System.out.println("Press 9 for: Retrieving Defaulters list");
			System.out.println("Press 10 for: To exit");

			coordinatorTask = s.nextInt();

			System.out.println("");
			CoordinatorRoleService coordinatorService = new CoordinatorRoleServiceImpl();
			TrainingProgram trainingProgram = new TrainingProgram();
			switch (coordinatorTask) {
			case 1: 
				LocalDate date2=null;
				System.out.println("Enter course code : ");
				int cc = s.nextInt();
				/* String course_name = s.next(); */
				System.out.println("Enter faculty code : ");
				int fc = s.nextInt();
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				String startDate=null;
				//String endDate=null;
				do {
				System.out.println("enter start date : ");
				startDate = s.next();
				}while(!Validations.checkDate(startDate));
				LocalDate date1 = LocalDate.parse(startDate, dtf);
//				try {
//					do {
//					do {
//					System.out.println("enter end date : ");
//					endDate = s.next();
//					}while(!Validations.checkDate(endDate));
//					date2 = LocalDate.parse(endDate, dtf);
//					}while(!Validations.checkEndDate(date1, date2));
//				} catch (ParseException e1) {
//					e1.printStackTrace();
//				}
				
				

				trainingProgram.setCourseCode(cc);
				trainingProgram.setFacultyCode(fc);
				trainingProgram.setStartLocalDate(date1);
				trainingProgram.setEndLocalDate(date2);
				try {
					coordinatorService.createTrainingProgram(trainingProgram);
				} catch (TrainingNotFoundException e) {
					logger.error(e);
					System.err.println(e);
				}

				break;

			case 2:
				System.out.println("Enter Training ID : ");
				int training_id_to_retrieve = s.nextInt();
				//trainingProgram.setTrainingCode(training_id_to_retrieve);

				try {
					coordinatorService.retrieveTrainingProgram(training_id_to_retrieve);
				} catch (TrainingNotFoundException e) {
					logger.error(e);
					System.err.println(e);
				}

				break;

			case 3:
				coordinatorService.retrieveAllTrainingPrograms();

				break;

			case 4://validation date
				System.out.println("Enter the Training ID of the training program you want to update : ");
				int training_id_to_update = s.nextInt();	
				trainingProgram.setTrainingCode(training_id_to_update);
				try {
					coordinatorService.retrieveTrainingProgram(training_id_to_update);
					System.out.println("The training program you want to update is : ");
					System.out.println("\nEnter the new updated course code : ");
					int updatedCourseCode = s.nextInt();
					System.out.println("Enter the updated faculty code : ");
					int updatedFacCode = s.nextInt();
					String updatedStartDate=null;
					do {
					System.out.println("Enter the updated start date int the format  dd-MM-yyyy : ");
					updatedStartDate = s.next();
					}while(!Validations.checkDate(updatedStartDate));
					DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					LocalDate updatedSdate = LocalDate.parse(updatedStartDate, dtf1);
					//String updatedEndDate=null;
					LocalDate updatedEdate=null;
//					do {
//						do {
//					System.out.println("enter end date : ");
//					updatedEndDate = s.next();
//					}while(!Validations.checkDate(updatedEndDate));
//						updatedEdate = LocalDate.parse(updatedEndDate, dtf1);
//					}while(!Validations.checkEndDate(updatedSdate, updatedEdate));
					

					trainingProgram.setCourseCode(updatedCourseCode);
					trainingProgram.setFacultyCode(updatedFacCode);
					trainingProgram.setStartLocalDate(updatedSdate);
					trainingProgram.setEndLocalDate(updatedEdate);

					coordinatorService.updateTrainingProgram(trainingProgram);
				} catch (TrainingNotFoundException e) {
					logger.error(e);
					System.err.println(e);
//				} catch (ParseException e) {
//					e.printStackTrace();
				}

				break;

			case 5:
				System.out.println("Enter the Training ID of the course you want to delete : ");
				int training_id_to_delete = s.nextInt();
				//trainingProgram.setTrainingCode(training_id_to_delete);
				//System.out.println("The training program you want to delete is : ");
				try {
					trainingProgram=coordinatorService.retrieveTrainingProgram(training_id_to_delete);
					System.out.println("\n");
					coordinatorService.deleteTrainingProgram(trainingProgram);
				} catch (TrainingNotFoundException e) {
					logger.error(e);
					System.err.println(e);
					System.err.println("Enter a valid training Id");
				}
				

				break;
			case 6:
				try {
					Participant participant = new Participant();

					System.out.println("Enter Training Program ID : ");
					int trainingId = s.nextInt();
					System.out.println("Enter Participant ID : ");
					int participantId = s.nextInt();

					participant.setTrainingProgramId(trainingId);
					participant.setParticipantId(participantId);
					coordinatorService.addParticipantEnrollmentRecord(participant);
				} catch (TrainingNotFoundException e) {
					logger.error(e);
					System.err.println(e);
				}

				break;
			case 7:
				coordinatorService.getAvg();

				break;
			case 8:
				//System.out.println(coordinatorService.facultyreport()); // print in daoImpl
				coordinatorService.facultyreport();
				break;

			case 9:
				//System.out.println(coordinatorService.getAllDefaulters());
				coordinatorService.getAllDefaulters();
				break;
			case 10:
				System.exit(0);
				break;

			default:
				System.out.println("Press a number between 1 to 9.");

			}
			System.out.println("Enter Y or y if you want to continue");
			c=s.next().charAt(0);
			}while(c=='Y'||c=='y');
			break;

		case "Participant":  
			do {
			int participantTask = 0;
			System.out.println("Hello Participant!\nWelcome to the Feedback Management System\n\n");
			System.out.println("Press 1 for: Giving feedback for a particular training program");
			
			participantTask = s.nextInt();

			ParticipantRoleService participantService = new ParticipantRoleServiceImpl();
			switch (participantTask) {

			case 1:
				Feedback fb = new Feedback();

				System.out.println("Enter Training code");
				int trainingCode = s.nextInt();
				fb.setTrainingCode(trainingCode); 				
				System.out.println("Enter participant id");
				int participantId = s.nextInt();
				if(id==participantId) 
				fb.setParticipantId(participantId);
				else {
					System.err.println("Access denied. Enter your own employee id");
					break;
				}
				try {
					participantService.checkParticipant(trainingCode, participantId);
					int fbClrfyDbts =0,fbHwSwNtwrk=0,fbPrsComm=0,fbTm=0,fbHndOut=0;
					do {
					System.out.println("Enter rating for Doubt Clarification between 1-5");
					fbClrfyDbts = s.nextInt();
					fb.setFbClrfyDbts(fbClrfyDbts);
					}while(!Validations.checkRating(fbClrfyDbts));
					do {
					System.out.println("Enter rating for Hand Outs between 1-5");
					fbHndOut = s.nextInt();
					fb.setFbHndOut(fbHndOut);
					}while(!Validations.checkRating(fbHndOut));
					do {
					System.out.println("Enter rating H/S and N/W availability between 1-5");
					fbHwSwNtwrk = s.nextInt();
					fb.setFbHwSwNtwrk(fbHwSwNtwrk);
					}while(!Validations.checkRating(fbHwSwNtwrk));
					do {
					System.out.println("Enter rating for Presentation and Communication between 1-5");
					fbPrsComm = s.nextInt();
					fb.setFbPrsComm(fbPrsComm);
					}while(!Validations.checkRating(fbPrsComm));
					do {
					System.out.println("Enter rating for Time Management between 1-5");
					fbTm = s.nextInt();
					fb.setFbTm(fbTm);
					}while(!Validations.checkRating(fbTm));
					System.out.println("Enter Comments");
					s.nextLine();
					String comments = s.nextLine();
					fb.setComments(comments);
					System.out.println("Enter Suggestions");
					String suggestions = s.nextLine();
					fb.setSuggestions(suggestions);
					
						participantService.addFeedback(fb);
				} catch (ParticipantNotInEnrolledInCourse e) {
					logger.error(e);
					System.err.println(e);
				}
				
			}
			System.out.println("Enter Y or y if you want to continue");
			c=s.next().charAt(0);
			}while(c=='Y'||c=='y');
			break;
		default:
			System.out.println("No such Employee or Role exists");
		}
		
		s.close();	
	}
}
