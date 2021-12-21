package com.lt.crs.business;

import com.lt.crs.dao.AdminDAOInterface;
import com.lt.crs.dao.AdminDAOImpl;

public class AdminOperation implements AdminInterface {
	AdminDAOInterface admin=null;

	
	public void addProfessor() {
		// TODO Auto-generated method stub
		admin=new AdminDAOImpl();
		try {
			admin.addProfessor();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getLocalizedMessage());
		}
		
	}

	 
	public void removeProfessor() {
		// TODO Auto-generated method stub
		admin=new AdminDAOImpl();
		try {
			admin.removeProfessor();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getLocalizedMessage());
		}
		
	}

	 
	public void addStudent() {
		// TODO Auto-generated method stub
		admin=new AdminDAOImpl();
		try {
			admin.addStudent();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getLocalizedMessage());
		}
		
		
		
	}

	  
	public void removeStudent() {
		// TODO Auto-generated method stub
		admin=new AdminDAOImpl();
		try {
			admin.removeStudent();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getLocalizedMessage());
		}
		
	}

	 
	public void addCourse() {
		// TODO Auto-generated method stub
		admin=new AdminDAOImpl();
		try {
			admin.addCourse();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getLocalizedMessage());
		}
		
	}

	 
	public void removeCourse() {
		// TODO Auto-generated method stub
		admin=new AdminDAOImpl();
		try {
			admin.removeCourse();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}

	 
	public void reportCardGeneration() {
		// TODO Auto-generated method stub
		admin=new AdminDAOImpl();
		try {
			admin.reportCardGeneration();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getLocalizedMessage());
		}
		
		
	}
	

}
