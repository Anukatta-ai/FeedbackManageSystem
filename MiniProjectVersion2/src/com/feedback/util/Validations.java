/**
* This is the validation class.
* Here, validations have been defined to keep a check on name, date, rating being entered by the user.
* 
* @author  Anjali Shaw, Anudeep Reddy Katta, Amit Kumar Baghel, Vanpriya Shukla
* @since   2019-09-28 
*/
package com.feedback.util;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class Validations {
	
	public static boolean checkName(String name) {
		String regex;
		boolean matches;		
		regex="[A-Z][a-zA-Z]{1,20}$";
		matches=Pattern.matches(regex,name);
		return matches;
	}
	
//	public static boolean checkNumber(int num) {
//		String newString=Integer.toString(num);
//		String regex;
//		boolean matches;		
//		regex="([0-9]+)";
//		matches=Pattern.matches(regex,newString);
//		return matches;
//	}
	
	public static boolean checkDate(String date) {
		String regex;
		boolean matches;		
		regex="(((0[1-9]|[12][0-9]|3[01])([-])(0[13578]|10|12)([-])(\\d{4}))|(([0][1-9]|[12][0-9]|30)([-])(0[469]|11)([-])(\\d{4}))|((0[1-9]|1[0-9]|2[0-8])([-])(02)([-])(\\d{4}))|((29)(\\.|-|\\/)(02)([-])([02468][048]00))|((29)([-])(02)([-])([13579][26]00))|((29)([-])(02)([-])([0-9][0-9][0][48]))|((29)([-])(02)([-])([0-9][0-9][2468][048]))|((29)([-])(02)([-])([0-9][0-9][13579][26])))";
		matches=Pattern.matches(regex,date);
		return matches;
	}
	
	
	public static boolean checkRating(int rating){
		String newString=Integer.toString(rating);
		String regex;
		boolean matches;		
		regex="([1-5])";
		matches=Pattern.matches(regex,newString);
		return matches;
	}
	
	public static boolean checkEndDate(LocalDate date1,LocalDate date2) throws ParseException{
		 
		if(date1.isBefore(date2)){
            return true;
        }
		return false;
	}
	
}
