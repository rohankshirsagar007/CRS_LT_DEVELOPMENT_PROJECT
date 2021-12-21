package com.lt.crs.dao;

public interface StudentDAOInterface {

	public void addCourse(int studentId) throws Exception;

	public void dropCourse(int studentId) throws Exception;

	public void viewEnrolledCourses(int studentId) throws Exception;

	public void viewReportCard(int studentId);

}
