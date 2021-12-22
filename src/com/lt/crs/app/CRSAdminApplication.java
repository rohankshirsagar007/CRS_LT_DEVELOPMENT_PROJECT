package com.lt.crs.app;

import java.util.Scanner;

import com.lt.crs.business.AdminInterface;
import com.lt.crs.business.AdminOperation;

/**
 * 
 * @author Diwakar,Sai,Rohan
 * This Class is Related Admin Menu 
 *
 */

public class CRSAdminApplication {
	static Scanner sc=new Scanner(System.in);
	AdminInterface adSer=null;
	public void adminMenu(int adminId) {
		System.out.println("\n \n ********************* ADMIN MENU ************************ \n \n");
		
		boolean adminMenu=true;
		while(adminMenu) {
			System.out.println("\n\t 1. Add Professor\n\t 2. Remove Professor \n\t 3. Add Course \n\t 4. Remove Course \n\t 5. Approve Student \n\t 6. Reject Student \n\t 7. Report Card Generation \n\t 8. Logout ");
			int adminSelect=sc.nextInt();
			switch(adminSelect) {
			case 1:
				adSer=new AdminOperation();
				adSer.addProfessor();
				break;
			case 2:
				adSer=new AdminOperation();
				adSer.removeProfessor();
				
				break;
			case 3:
				adSer=new AdminOperation();
				adSer.addCourse();
				break;
			case 4:
				adSer=new AdminOperation();
				adSer.removeCourse();
				break;
			case 5:
				adSer=new AdminOperation();
				adSer.addStudent();
				break;
			case 6:
				adSer=new AdminOperation();
				adSer.removeStudent();
				break;
			case 7:
				adSer=new AdminOperation();
				adSer.reportCardGeneration();
				break;
			case 8:
				System.out.println(" Are you sure you want to log out Y or N: ");
				char ask=sc.next().charAt(0);
				if(ask=='y' || ask=='Y') {adminMenu=false;}
				break;
				
			default:
				System.out.println("User Entered Wrong Choice !!! \n \t Plese use Right Choice. ");
				break;
			
			}
			
		}
		
		
	}

}
