package com.lt.crs.business;

import com.lt.crs.bean.Student;
import com.lt.crs.dao.StudentRegisterationDAOInterface;
import com.lt.crs.dao.StudentRegisterationDAOImpl;

public class StudentRegistrationOperation implements StudentRegistrationInterface{
	
	StudentRegisterationDAOInterface str=null;
	
	public void register(Student student) {
		str=new StudentRegisterationDAOImpl();
		str.register(student);
		
		
		
	}

}
