
package com.lt.crs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.lt.crs.exceptions.CourseDuplicationException;
import com.lt.crs.exceptions.CourseMapFoundException;
import com.lt.crs.exceptions.NoCourseRegisterException;
import com.lt.crs.exceptions.NoReportCardException;
import com.lt.crs.exceptions.NoStudentRegisteredException;
import com.lt.crs.exceptions.ProfessorNotFoundException;
import com.lt.crs.exceptions.StudentNotFoundException;
import com.lt.crs.utilsDB.DBUtils;

public class AdminDAOImpl implements AdminDAOInterface{
	
	Connection con=null;
	

static Scanner sc=new Scanner(System.in);
	
	public void addProfessor() throws Exception {
		
		con=DBUtils.getConnection();
		
		// TODO Auto-generated method stub


		PreparedStatement ps=null;
		PreparedStatement ps1=null;
		PreparedStatement ps2=null;
		PreparedStatement ps3=null;
		
		try {
			System.out.println("Do you want to add an existing professor to the user_role table Y or N ");			
			char addOrNot=sc.next().charAt(0);
			if(addOrNot=='y' || addOrNot=='Y') {
				
				System.out.println("Enter the existing  professor ID you want to add to the user_role");
		
				// TODO Auto-generated method stub
				int profId=sc.nextInt();
				
				ps=con.prepareStatement("select professor_id  from professor where professor_id=?");
				ps.setInt(1, profId);
				ps.execute();
				
				ResultSet rs=ps.executeQuery();
			if(rs.next()) {
			
				int profID=rs.getInt(1);
				
			
				

				ps1=con.prepareStatement("insert into user_role values(?,2)");
				ps1.setInt(1, profID);
				ps1.executeUpdate();
				System.out.println("Successfully added the professor   to the user_role");
				System.out.println("Please note the Professor ID for future reference : "+"professor-"+profID);
				//Query to insert into course_professor
				


				

			}
			else {throw new ProfessorNotFoundException( "Sorry No such professor data is available in professor table");}//throw 


			}
			else {
				System.out.println("Enter Professor Name");
				String professorName=sc.next();
				System.out.println("Enter Department of the Professor");
				String  professorDept=sc.next();
				System.out.println("Enter the password for the professor");
				String professorPassword=sc.next();
			
			
			
		ps2=con.prepareStatement("insert into professor (professor_name,professor_password,professor_department) values (?,?,?)",java.sql.Statement.RETURN_GENERATED_KEYS);
		ps2.setString(1, professorName);
		ps2.setString(2,professorPassword);
		ps2.setString(3,professorDept);
		ps2.executeUpdate();
		ResultSet rs=ps2.getGeneratedKeys();
	if(rs.next()) {
		System.out.println("Hello Admin the Professor ID for the added professor  is : "+ rs.getInt(1)+"and now adding to the USER_ROLE table");
		int profID=rs.getInt(1);
		

		ps3=con.prepareStatement("insert into user_role values(?,2)");
		ps3.setInt(1, profID);
		ps3.executeUpdate();
		System.out.println("Successfully added the professor ID to the user_role");
		System.out.println("Please note the Professor ID for future reference : "+"professor-"+profID);
		//Query to insert into course_professor
		
	
	}
	
			}
	
		}
		
		catch(Exception e) {throw e;}	finally {try {
			if(ps!=null) {ps.close();}		
			if(ps2!=null) {ps2.close();}
			if(ps3!=null) {ps3.close();}
		con.close();}catch(Exception e) {throw e;}		}


		
	}
	
	//Remove Professor
	
	public void removeProfessor() throws Exception {
		
		
		con=DBUtils.getConnection();


		PreparedStatement ps=null;
		PreparedStatement ps1=null;
		PreparedStatement ps2=null;
		
		try {
			
			System.out.println("Enter the professor ID you want to remove");
	
			// TODO Auto-generated method stub
			int profId=sc.nextInt();
			
			ps=con.prepareStatement("select * from course_professor_student where professor_id=?");
			ps.setInt(1,profId);
			ResultSet rsp=ps.executeQuery();
			if(rsp.next()) {
				throw new CourseMapFoundException("Sorry you cant remove the Professor who has been assigned to teach course");
				
			}
			else {
			
			ps1=con.prepareStatement("select professor_id  from professor where professor_id=?");
			ps1.setInt(1, profId);
			ps1.execute();
			
			ResultSet rs=ps1.executeQuery();
		if(rs.next()) {
		
			int profID=rs.getInt(1);
			

			ps2=con.prepareStatement("delete from  user_role where user_id=? and role_id=2");
			ps2.setInt(1, profID);
			ps2.executeUpdate();
			System.out.println("Successfully deleted the professor ID"+profID+" from the user_role");

		}
		else {throw new ProfessorNotFoundException( "Sorry No such professor data is available in professor table");}
		


		}
			}
		catch(Exception e) {throw e;}	finally {try {if(ps!=null) {ps.close();}if(ps1!=null) {ps1.close();}if(ps2!=null) {ps2.close();}con.close();}catch(Exception e) {throw e;}		}

		
	}
	
	
	
	

	//ADD Student
	
	public void addStudent() throws Exception {
		// TODO Auto-generated method stub
	
		con=DBUtils.getConnection();

		PreparedStatement ps=null;
		PreparedStatement ps1=null;
		PreparedStatement ps2=null;
	
		try {
		ps=con.prepareStatement("select * from student");
		ResultSet rs=ps.executeQuery();
		int counter=0;
		while(rs.next()) {
			counter+=1;
			try {
			System.out.println("Do you want to approve student whose student ID is : "+rs.getInt(1));
			
			ps2=con.prepareStatement("select * from user_role where user_id=? and role_id=3");
			ps2.setInt(1, rs.getInt(1));
			ResultSet rs1=ps2.executeQuery();
			
			if(rs1.next())
			{ System.out.println("strudent ID "+rs.getInt(1)+" is already approved");}
			else {
			char addOrNot=sc.next().charAt(0);
			if(addOrNot=='y' || addOrNot=='Y') {
			ps1=con.prepareStatement("insert into user_role values(?,3)");
			ps1.setInt(1, rs.getInt(1));
			ps1.executeUpdate();
			System.out.println("Student Id "+rs.getInt(1)+" inserted successfully");
			
			}
			else {System.out.println("Admin rejected the student with student ID"+rs.getInt(1));}}}
			catch(Exception e) {e.printStackTrace();}
			
		}
		if(counter==0) {
			throw new NoStudentRegisteredException("Zero student registered till now");
		}
		
		
		
		
		
		}
		catch(Exception e) {throw e;}	finally {try {if(ps!=null) {ps.close();}if(ps1!=null) {ps1.close();}if(ps2!=null) {ps2.close();}con.close();}catch(Exception e) {throw e;}		}

		
}
		
		
	
	
	
	//Remove Student
	
	public void removeStudent() throws Exception {
		// TODO Auto-generated method stub
		
		con=DBUtils.getConnection();
		


		PreparedStatement ps=null;
		PreparedStatement ps1=null;
		PreparedStatement ps2=null;
	
		try {
			
			System.out.println("Enter the Student ID you want to remove");
	
			// TODO Auto-generated method stub
			int studId=sc.nextInt();
			
			ps=con.prepareStatement("select * from course_professor_student where student_id=?");
			ps.setInt(1,studId);
			ResultSet rsp=ps.executeQuery();
			if(rsp.next()) {
				throw new CourseMapFoundException("Sorry you cant remove the Student  who has been assigned to take the  course");
				
			}
			else {
			
			ps1=con.prepareStatement("select student_id  from student where student_id=?");
			ps1.setInt(1, studId);
			ps1.execute();
			
			ResultSet rs=ps1.executeQuery();
		if(rs.next()) {
		
			int studID=rs.getInt(1);
			

			ps2=con.prepareStatement("delete from  user_role where user_id=? and role_id=3");
			ps2.setInt(1, studID);
			ps2.executeUpdate();
			System.out.println("Successfully deleted the Student ID"+studID+" from the user_role");

		}
		else {throw new StudentNotFoundException( "Sorry No such student  data is available in student  table");}
		
		

			}		
			}
		catch(Exception e) {throw e;}	finally {try {if(ps!=null) {ps.close();}if(ps1!=null) {ps1.close();}if(ps2!=null) {ps2.close();}con.close();}catch(Exception e) {throw e;}		}

			
		
	}
	
	
	//ADD Course

	
	public void addCourse() throws Exception {
		// TODO Auto-generated method stub
		
		con=DBUtils.getConnection();

		PreparedStatement ps=null;
		PreparedStatement ps1=null;
		try {

			System.out.println("Enter Course Name");
			String courseName=sc.next();
			System.out.println("Enter Course Cost");
			double courseCost=sc.nextDouble();
			
		ps=con.prepareStatement("select * from course where course_name=? ");
		ps.setString(1, courseName.toLowerCase());;
	ResultSet rs=ps.executeQuery();
		if(!rs.next()) {
		
			ps1=con.prepareStatement("insert into course(course_name,course_cost) values(?,?)");
			ps1.setString(1, courseName.toLowerCase());;
			ps1.setDouble(2,courseCost);
			
			ps1.executeUpdate();
			System.out.println(" Course Added Successfully3.");
		}
		else {
			throw new CourseDuplicationException("Course Already present So not able to add course which is already existed ");
		}
		}
	
		
		
		catch(Exception e) {throw e;}	finally {try {if(ps!=null) {ps.close();}if(ps1!=null) {ps1.close();}con.close();}catch(Exception e) {throw e;}		}


		
		
	
	}

	
	public void removeCourse() throws Exception {
		// TODO Auto-generated method stub
		
		con=DBUtils.getConnection();

		PreparedStatement ps=null;
		PreparedStatement ps1=null;
	
		try {
			ps=con.prepareStatement("select course_id,professor_id,count(*) from course_professor_student group by 1,2 order by 1");
			ResultSet rs=ps.executeQuery();
			int counter=0;
			
			while(rs.next()) {
				counter+=1;
				int coId=rs.getInt(1);
				int profId=rs.getInt(2);
				int count=rs.getInt(3);
				if(count<3) {
					System.out.println("Do you want to remove Y or N    "+coId);
					char ask=sc.next().charAt(0);
					if(ask=='y' || ask=='Y') {
					
					
					System.out.println("Removing the course with students less than the desired student");
					ps1=con.prepareStatement("select * from course_professor_student where course_id=? and professor_id=?");
					ps1.setInt(1,coId);
					ps1.setInt(2,profId);
					ResultSet rs1=ps1.executeQuery();
					System.out.println("Append all the corresponding students ID in the list "
							+ "and send them notification regard choose of another course");
					while(rs1.next()) {
						
						System.out.println("student ID: "+rs1.getInt(3));
					}
					//Delete Query
					
				}
				}
				
				
				
			}
			
			if(counter==0) {
				throw new NoCourseRegisterException("There is no courses registered till now");
			}
		
			
		}
	
		
		catch(Exception e) {throw e;}	finally {try {if(ps!=null) {ps.close();}if(ps1!=null) {ps1.close();}con.close();}catch(Exception e) {throw e;}		}


		
		
		
	}

	
	public void reportCardGeneration() throws Exception {
		// TODO Auto-generated method stub

	
		con=DBUtils.getConnection();
		

		PreparedStatement ps=null;
		
		
		try {
		
			ps=con.prepareStatement("  select c.course_id,c.course_name,s.student_name,sg.mark,sg.grade,s.student_id from student_grade sg inner join student s on sg.student_id=s.student_id inner join course c on sg.course_id=c.course_id group by 1,2,3,4,5,6 order by 6 ");

			ResultSet rs=ps.executeQuery();
			int counter=0;
			while(rs.next()) {
				counter+=1;
				System.out.println("******************* Displaying report card for Student ID:" +rs.getInt(6)+" and Student Name :"+rs.getString(3)+"******************  ");
			System.out.println("\t\t course ID        "+rs.getInt(1)+"\t\t\t Course Name      "+rs.getString(2)+"\t\t\t\tMark     "+rs.getDouble(4)+"\t\tGrade       "+rs.getString(5));
			}
			
			if(counter==0) {
				throw new NoReportCardException("There is no report card available");
			}
			
		
			
			
			
		}
		catch(Exception e) {throw e;}	finally {try {if(ps!=null) {ps.close();}con.close();}catch(Exception e) {throw e;}		}


		
		
	}
}