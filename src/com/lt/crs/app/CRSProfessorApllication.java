package com.lt.crs.app;

import java.util.Scanner;

import com.lt.crs.business.ProfessorInterface;
import com.lt.crs.business.ProfessorOperation;

/**
 * 
 * @author Diwakar,Sai,Rohan
 * This Class is Related Professor Menu 
 *
 */

public class CRSProfessorApllication {
	static Scanner sc=new Scanner(System.in);
	ProfessorInterface profser=null;
	

	public void profMenu(int profId) {
		// TODO Auto-generated method stub
		
System.out.println("\n\n ********************* Professor  MENU ************************ \n\n");
		
		boolean profMenu=true;
		while(profMenu) {
		
		System.out.println("\n\t1. View Enrolled Students \n\t2. Add Grades to the Student \n\t3. Logout");
		int profSelect=sc.nextInt();
		switch(profSelect) {
		case 1:
			profser=new ProfessorOperation();
			profser.viewStudent(profId);
			
			break;
		case 2:
			profser=new ProfessorOperation();
			profser.addGrade(profId);
			break;
		
		case 3:
			System.out.println(" Are you sure you want to log out Y or N: ");
			char ask=sc.next().charAt(0);
			if(ask=='y' || ask=='Y') {profMenu=false;}
			break;
		default:
			System.out.println("User Entered Wrong Choice !!! \n \t Plese use Right Choice. ");
			break;
		
		}

		
		
		
		
		}
		}
	

}
