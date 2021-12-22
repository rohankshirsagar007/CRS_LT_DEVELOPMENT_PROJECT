package com.lt.crs.business;

import com.lt.crs.dao.StudentDAOImpl;
import com.lt.crs.dao.StudentDAOInterface;

/**
 * 
 * @author Sai
 * This Class is related all the Student Operation
 *
 */
public class StudentOperation implements StudentInterface{
	
	StudentDAOInterface stud=null;

	
	public void addCourse(int studentId) {
		// TODO Auto-generated method stub
		stud=new StudentDAOImpl();
		try {
			stud.addCourse(studentId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getLocalizedMessage());
		}
		
	}

	
	public void dropCourse(int studentId) {
		// TODO Auto-generated method stub
		stud=new StudentDAOImpl();
		try {
			stud.dropCourse(studentId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getLocalizedMessage());
		}
	}

	
	public void viewEnrolledCourse(int studentId) {
		// TODO Auto-generated method stub
		stud=new StudentDAOImpl();
		try {
			stud.viewEnrolledCourses(studentId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getLocalizedMessage());
		}
	
		
	}

	
	public void viewReportCard(int studentId) {
		// TODO Auto-generated method stub
		stud=new StudentDAOImpl();
		stud.viewReportCard(studentId);
		
	}

}
