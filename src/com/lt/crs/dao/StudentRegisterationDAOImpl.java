package com.lt.crs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lt.crs.bean.Student;
import com.lt.crs.constants.SqlQueryConstants;
import com.lt.crs.utilsDB.DBUtils;

public class StudentRegisterationDAOImpl implements StudentRegisterationDAOInterface{

	Connection con=null;
	
	
	
public void register(Student student) {
		con=DBUtils.getConnection();
		PreparedStatement ps=null;
		try {
			//ps=con.prepareStatement("insert into student (student_name,student_password,student_department) values (?,?,?)",java.sql.Statement.RETURN_GENERATED_KEYS);
			ps=con.prepareStatement(SqlQueryConstants.student_registeration,java.sql.Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, student.getStudentName());
		ps.setString(2,student.getStudentPassword());
		ps.setString(3,student.getStudentDepartment());
		ps.executeUpdate();
		ResultSet rs=ps.getGeneratedKeys();
	if(rs.next()) {
		System.out.println("your student ID is : 		Please note this for future reference"+ "student-"+rs.getInt(1));
	}
	

	
		}
		catch(Exception e) {e.printStackTrace();}	finally {try {if(ps!=null) {ps.close();}con.close();}catch(Exception e) {e.printStackTrace();}		}
			
		
	}

}
