package com.lt.crs.app;

import java.util.Scanner;

import com.lt.crs.bean.Student;
import com.lt.crs.business.LoginUserInterface;
import com.lt.crs.business.LoginUserOperation;
import com.lt.crs.business.StudentRegistrationInterface;
import com.lt.crs.business.StudentRegistrationOperation;

public class CRSApplication {
	
	
	static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("\n*************** WELCOME TO CRS APPLICATION **************");
		mainMenu();

	}
	public static void mainMenu()  {
		LoginUserInterface user=null;
		StudentRegistrationInterface str=null;
		Student student=null;

		Boolean mainMenu=true;
		while(mainMenu) {
		
		System.out.println(" \n \t1. Login \n\t2. Student Registeration  \n\t3. Update Password \n\t4. Exit From APP  \n");
		int menu=sc.nextInt();
		switch(menu) {
		case 1:
			user=new LoginUserOperation();
			System.out.println("\n Enter User ID : ");
			String userId=sc.next();
			System.out.println("\n Enter password : ");
			String  password=sc.next();
			user.userLogin(userId, password);
		break;
			
		case 2:
			str=new StudentRegistrationOperation();
			student=new Student();
			System.out.println("Enter Student Name");
			String studentName=sc.next();
			System.out.println("Enter Department of the Student");
			String  studentDept=sc.next();
			System.out.println("Enter your password");
			String  studentPassword=sc.next();
			boolean pass=false;
			while(!pass) {
				System.out.println("Confirm  your password");
				String  confirmPassword=sc.next();
				if(studentPassword.equals(confirmPassword)) {pass=true;}
				
			}
			
			student.setStudentName(studentName);
			student.setStudentDepartment(studentDept);
			student.setStudentPassword(studentPassword);
	
			str.register(student);
			break;
		case 3:
			user=new LoginUserOperation();
			System.out.println("Enter User ID : ");
			String userID=sc.next();
			System.out.println("Enter Your Old password : ");
			String oldpw=sc.next();
			System.out.println("Enter Your NEW password : ");
			String newpw=sc.next();
			user.updatePassword(userID,oldpw,newpw);
			
			break;
			
		case 4:
			System.out.println(" Are you sure you want to EXIT FROM CRS APP  Y or N: ");
			char ask=sc.next().charAt(0);
			if(ask=='y' || ask=='Y')
			{
				System.exit(0);
			}
			
		}
			
		}
		
	}
	
 
}
