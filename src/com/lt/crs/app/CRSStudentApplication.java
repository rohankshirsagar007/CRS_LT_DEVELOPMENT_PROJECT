package com.lt.crs.app;

import java.util.Scanner;

import com.lt.crs.business.PaymentInterface;
import com.lt.crs.business.PaymentOperations;
import com.lt.crs.business.StudentInterface;
import com.lt.crs.business.StudentOperation;

/**
 * 
 * @author Diwakar,Sai,Rohan
 * This Class is Related Student Menu 
 *
 */

public class CRSStudentApplication {
	static Scanner sc = new Scanner(System.in);
	StudentInterface studSer = null;
	PaymentInterface po = null;

	public void studentMenu(int studentId) {
		// TODO Auto-generated method stub

		System.out
				.println("********************* STUDENT  MENU ************************");

		boolean studentMenu = true;
		while (studentMenu) {

			System.out
					.println("\n\t 1. Enroll Courses \n\t 2. Drop Course \n\t 3. View Enrolled Courses \n\t 4. View Report Card \n\t 5. Payment \n\t 6. Logout");
			int studentSelect = sc.nextInt();
			switch (studentSelect) {
			case 1:
				studSer = new StudentOperation();
				studSer.addCourse(studentId);
				break;
			case 2:
				studSer = new StudentOperation();
				studSer.dropCourse(studentId);
				break;
			case 3:
				studSer = new StudentOperation();
				studSer.viewEnrolledCourse(studentId);
				break;
			case 4:

				studSer = new StudentOperation();
				studSer.viewReportCard(studentId);
				break;
			case 5:
				po=new PaymentOperations();

				po.makePayment(studentId);
				break;

			case 6:
				System.out
						.println(" Are you sure you want to log out Y or N: ");
				char ask = sc.next().charAt(0);
				if (ask == 'y' || ask == 'Y') {
					studentMenu = false;
				}
				break;
			default:
				System.out
						.println("User Entered Wrong Choice !!! \n \t Plese use Right Choice. ");
				break;

			}

		}
	}

}