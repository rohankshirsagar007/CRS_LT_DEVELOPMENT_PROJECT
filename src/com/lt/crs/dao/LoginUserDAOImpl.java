package com.lt.crs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;

import com.lt.crs.app.CRSAdmin;
import com.lt.crs.app.CRSProfessor;
import com.lt.crs.app.CRSStudent;
import com.lt.crs.constants.SqlQueryConstants;
import com.lt.crs.exceptions.UserNotFoundException;
import com.lt.crs.utilsDB.DBUtils;
	
public class LoginUserDAOImpl implements LoginUserDAOInterface{
	
	
	Connection con=null;
	CRSAdmin admin=null;
	CRSStudent student=null;
	CRSProfessor professor=null;
	
public void userLogin(String userId,String userPassword) throws Exception  {
	con=DBUtils.getConnection();
	String[] a=userId.split("-");
	if(a[0].trim().toLowerCase().equals("student".trim())) {
		int studID=Integer.parseInt(a[1]);

		PreparedStatement ps1=null;
		PreparedStatement ps2=null;
		try {
	
		//	ps1=con.prepareStatement("select * from user_role where user_id=? and role_id=3");
			ps1=con.prepareStatement(SqlQueryConstants.user_role_student);
			ps1.setInt(1, studID);
			ResultSet rs1=ps1.executeQuery();
			if(rs1.next()) {
				//ps2=con.prepareStatement("select * from student where student_id=? and student_password=?");
				ps2=con.prepareStatement(SqlQueryConstants.student_login_query);
				ps2.setInt(1, studID);
				ps2.setString(2, userPassword);
				ResultSet rs2=ps2.executeQuery();
				if(rs2.next()) {
					int studentId=rs2.getInt(1);
					System.out.println("Hi "+rs2.getString(2)+", login successfull @"+LocalDate.now()+" and Time "+LocalTime.now());
					
					student=new CRSStudent();
					student.studentMenu(studentId);
								
				}
				else {throw  new UserNotFoundException("Please enter correct password for 	"+userId);}

				
			}
			else {				throw  new UserNotFoundException("User ID  "+userId+"  is not yet approved by admin");
			}

			
			
						
			}

		catch(Exception e) {throw e;}	finally {try {
			ps1.close();if(ps2!=null) {ps2.close();}			
		con.close();}catch(Exception e) {throw e;}		}


	}
	else if(a[0].toLowerCase().equals("professor".trim())) {
		int profID=Integer.parseInt(a[1]);

		PreparedStatement ps3=null;
		PreparedStatement ps4=null;	
		try {			
			
			//ps3=con.prepareStatement("select * from user_role where user_id=? and role_id=2");
			ps3=con.prepareStatement(SqlQueryConstants.user_role_professor);
			ps3.setInt(1, profID);
			ResultSet rs3=ps3.executeQuery();

			if(rs3.next()) {
				
				
			//	ps4=con.prepareStatement("select * from professor where professor_id=? and professor_password=?");
				ps4=con.prepareStatement(SqlQueryConstants.professor_login_query);
				ps4.setInt(1, profID);
				ps4.setString(2, userPassword);
				
				ResultSet rs4=ps4.executeQuery();
				if(rs4.next()) {
					int profId=rs4.getInt(1);
					System.out.println("Hi "+rs4.getString(2)+", login successfull @"+LocalDate.now()+" and Time "+LocalTime.now());
					professor=new CRSProfessor();
					professor.profMenu(profId);

							
			}else {
				throw  new UserNotFoundException("User ID does not exist");
			}
				
			
				
			}
			else {
				throw  new UserNotFoundException("User ID  "+userId+"  is not yet approved by admin");
			}

		}
		catch(Exception e) {throw e;}	finally {try {
		
			ps3.close();if(ps4!=null) {ps4.close();}		
		con.close();}catch(Exception e) {throw e;}		}


	}
	else if(a[0].toLowerCase().equals("admin".trim())) {
		int adminID=Integer.parseInt(a[1]);

		PreparedStatement ps5=null;
		PreparedStatement ps6=null;
		
		try {
			//ps5=con.prepareStatement("select * from user_role where user_id=? and role_id=1");
			ps5=con.prepareStatement(SqlQueryConstants.user_role_admin);
			ps5.setInt(1, adminID);
			ResultSet rs5=ps5.executeQuery();
			if(rs5.next()) {
			//	ps6=con.prepareStatement("select * from admin where admin_id=? and admin_password=?");
				ps6=con.prepareStatement(SqlQueryConstants.admin_login_query);
				ps6.setInt(1, adminID);
				ps6.setString(2, userPassword);
				ResultSet rs6=ps6.executeQuery();
				if(rs6.next()) {
					int adminId=rs6.getInt(1);
			//new prepared statement 
					System.out.println("Hi "+rs6.getString(2)+", login successfull @"+LocalDate.now()+" and Time "+LocalTime.now());
					admin=new CRSAdmin();
					admin.adminMenu(adminId);
				}
				else {
					throw  new UserNotFoundException("Please enter correct password for 	"+userId);
				}
			
				
				
		
			}
			else {
				throw  new UserNotFoundException("Please enter valid credentials");
			}
									
			}

	
		catch(Exception e) {
			throw e;
		}	finally {try {
			ps5.close();if(ps6!=null) {ps6.close();}				
				con.close();}catch(Exception e) {throw e;}		}

	}
	else {
		throw  new UserNotFoundException("User with this credential is not found		"+userId);
	}
	

	
		
		
	}

public void updatePassword(String userId, String userPassword, String newpw) throws Exception {
	// TODO Auto-generated method stub
	con=DBUtils.getConnection();
	String[] a=userId.split("-");
	if(a[0].trim().toLowerCase().equals("student".trim())) {
		int studID=Integer.parseInt(a[1]);

		PreparedStatement ps1=null;
		PreparedStatement ps2=null;
		PreparedStatement ps7=null;
		try {
	
			ps1=con.prepareStatement(SqlQueryConstants.user_role_student);
			ps1.setInt(1, studID);
			ResultSet rs1=ps1.executeQuery();
			if(rs1.next()) {
				ps2=con.prepareStatement(SqlQueryConstants.student_login_query);
				ps2.setInt(1, studID);
				ps2.setString(2, userPassword);
				ResultSet rs2=ps2.executeQuery();
				if(rs2.next()) {
					int studentId=rs2.getInt(1);
					//ps7=con.prepareStatement("update student set student_password=? where student_id=?");
					ps7=con.prepareStatement(SqlQueryConstants.student_password_update);
					ps7.setString(1,newpw);
					ps7.setInt(2,studentId);
					ps7.executeUpdate();
					System.out.println("Updated Password Successfully in student table");
								
				}
				else {throw  new UserNotFoundException("Please enter correct password for 	"+userId);}

				
			}
			else {				throw  new UserNotFoundException("User ID  "+userId+"  is not yet approved by admin");
			}

			
			
						
			}

		catch(Exception e) {throw e;}	finally {try {
			ps1.close();if(ps2!=null) {ps2.close();}
			if(ps7!=null) {ps7.close();}			
		con.close();}catch(Exception e) {throw e;}		}


	}
	else if(a[0].toLowerCase().equals("professor".trim())) {
		int profID=Integer.parseInt(a[1]);

		PreparedStatement ps3=null;
		PreparedStatement ps4=null;	
		PreparedStatement ps8=null;
		try {			
			
			ps3=con.prepareStatement(SqlQueryConstants.user_role_professor);
			ps3.setInt(1, profID);
			ResultSet rs3=ps3.executeQuery();

			if(rs3.next()) {
				
				
				ps4=con.prepareStatement(SqlQueryConstants.professor_login_query);
				ps4.setInt(1, profID);
				ps4.setString(2, userPassword);
				
				ResultSet rs4=ps4.executeQuery();
				if(rs4.next()) {
					int profId=rs4.getInt(1);
					//ps8=con.prepareStatement("update professor set professor_password=? where professor_id=?");
					ps8=con.prepareStatement(SqlQueryConstants.professor_password_update);
					ps8.setString(1,newpw);
					ps8.setInt(2,profId);
					ps8.executeUpdate();
					System.out.println("Updated Password Successfully in Professor table");
							
			}else {
				throw  new UserNotFoundException("User ID does not exist");
			}
				
			
				
			}
			else {
				throw  new UserNotFoundException("User ID  "+userId+"  is not yet approved by admin");
			}

		}
		catch(Exception e) {throw e;}	finally {try {
		
			ps3.close();if(ps4!=null) {ps4.close();			if(ps8!=null) {ps8.close();}			}		
		con.close();}catch(Exception e) {throw e;}		}


	}
	else if(a[0].toLowerCase().equals("admin".trim())) {
		int adminID=Integer.parseInt(a[1]);

		PreparedStatement ps5=null;
		PreparedStatement ps6=null;
		PreparedStatement ps9=null;
		
		try {
			ps5=con.prepareStatement(SqlQueryConstants.user_role_admin);
			ps5.setInt(1, adminID);
			ResultSet rs5=ps5.executeQuery();
			if(rs5.next()) {
				ps6=con.prepareStatement(SqlQueryConstants.admin_login_query);
				ps6.setInt(1, adminID);
				ps6.setString(2, userPassword);
				ResultSet rs6=ps6.executeQuery();
				if(rs6.next()) {
					int adminId=rs6.getInt(1);
			//new prepared statement 
		//			ps9=con.prepareStatement("update admin set admin_password=? where admin_id=?");
					ps9=con.prepareStatement(SqlQueryConstants.admin_password_update);
					ps9.setString(1,newpw);
					ps9.setInt(2,adminId);
					ps9.executeUpdate();
					System.out.println("Updated Password Successfully in Admin table");
				}
				else {
					throw  new UserNotFoundException("Please enter correct password for 	"+userId);
				}
			
				
				
		
			}
			else {
				throw  new UserNotFoundException("Please enter valid credentials");
			}
									
			}

	
		catch(Exception e) {
			throw e;
		}	finally {try {
			ps5.close();if(ps6!=null) {ps6.close();}				if(ps9!=null) {ps9.close();}				
				con.close();}catch(Exception e) {throw e;}		}

	}
	else {
		throw  new UserNotFoundException("User with this credential is not found		"+userId);
	}
	
	
	
}


}
