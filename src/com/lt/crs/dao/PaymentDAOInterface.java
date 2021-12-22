package com.lt.crs.dao;

import java.util.Map;

import com.lt.crs.exceptions.CourseNotRegisteredException;

public interface PaymentDAOInterface {
	void makePayment(int userId,int courseId) throws CourseNotRegisteredException ;

}
