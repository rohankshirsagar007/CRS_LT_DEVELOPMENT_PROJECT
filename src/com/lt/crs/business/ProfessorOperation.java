package com.lt.crs.business;

import com.lt.crs.dao.ProfessorDAOImpl;
import com.lt.crs.dao.ProfessorDAOInterface;

public class ProfessorOperation implements ProfessorInterface{

	ProfessorDAOInterface prd=null;
	
	public void viewStudent(int profId) {
		// TODO Auto-generated method stub
		prd=new ProfessorDAOImpl();
		try {
			prd.viewStudent(profId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getLocalizedMessage());
		}
		
	}
	
	public void addGrade(int profId) {
		// TODO Auto-generated method stub
		prd=new ProfessorDAOImpl();
		try {
			prd.addGrade(profId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getLocalizedMessage());
		}
		
	}

}
