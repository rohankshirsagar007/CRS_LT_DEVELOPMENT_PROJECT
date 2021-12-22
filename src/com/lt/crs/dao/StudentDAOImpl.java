package com.lt.crs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.Scanner;

import com.lt.crs.bean.Course;
import com.lt.crs.bean.Professor;
import com.lt.crs.bean.Student;
import com.lt.crs.bean.StudentGrade;
import com.lt.crs.dto.StudentCourseDetails;
import com.lt.crs.exceptions.DuplicateCourseSelectionException;
import com.lt.crs.exceptions.MaxCourseRegistered;
import com.lt.crs.exceptions.NoCourseInRegisteryException;
import com.lt.crs.exceptions.NoReportCardException;
import com.lt.crs.exceptions.NoVacanyException;
import com.lt.crs.utilsDB.DBUtils;

public class StudentDAOImpl implements StudentDAOInterface{
	static Scanner sc=new Scanner(System.in);
	LinkedList<StudentCourseDetails> list2=new LinkedList<StudentCourseDetails>();
	LinkedList<StudentCourseDetails> list3=new LinkedList<StudentCourseDetails>();
	Connection con=null;

	
	public void addCourse(int studentId) throws Exception {
		// TODO Auto-generated method stub
		
		con=DBUtils.getConnection();
		PreparedStatement ps=null;
		PreparedStatement ps1=null;
		PreparedStatement ps2=null;
		PreparedStatement ps3=null;
		PreparedStatement ps4=null;
		PreparedStatement ps5=null;
	
		try {
			ps=con.prepareStatement("select cp.course_id,c.course_name from course_professor cp "
					+ "inner join course c on c.course_id=cp.course_id inner join professor p on p.professor_id=cp.professor_id group by 1,2 order by 1");
			ResultSet rs=ps.executeQuery();

			int counter=0;
			System.out.println("\t\tCourse Id" + " \t "+"CourseName");
			while(rs.next()) {
				counter+=1;
				System.out.println("\t\t"+rs.getInt(1)+"\t\t\t"+rs.getString(2).trim());
	
			}
			
			if(counter==0){throw new NoCourseInRegisteryException("There is no courses in the course table");}
	
			ps1=con.prepareStatement("select distinct cps.student_id,cps.course_id,cps.professor_id"
					+ " from course_professor_student cps where cps.student_id=?");
			ps1.setInt(1,studentId);
			ResultSet rs1=ps1.executeQuery();
			int noOfCourse=0;
			while(rs1.next()) {
				noOfCourse+=1;
			}
			if(noOfCourse!=4) {
				System.out.println("You are eligible to select course");
				System.out.println("Enter Course ID from the above listed details");
				int couId=sc.nextInt();
				ps2=con.prepareStatement("select * from course_professor where course_id=? group by 1,2 order by 2");//bug fix if rise
				ps2.setInt(1,couId);
				ResultSet rs2=ps2.executeQuery();
				boolean inserted=false;
				while(rs2.next()) {
					int profId=rs2.getInt(2);
					ps3=con.prepareStatement("select count(*) from course_professor_student where course_id=? and professor_id=?");
					ps3.setInt(1,couId);
					ps3.setInt(2,profId);
					
					ResultSet rs3=ps3.executeQuery();
					int rows=0;
					if(rs3.next()) {
						rows=rs3.getInt(1);
										}
					if(rows>=10) {}
					else {
					ps4=con.prepareStatement("select * from course_professor_student "
							+ "where course_id=? and student_id=?");	
					ps4.setInt(1,couId);
					ps4.setInt(2,studentId);
					ResultSet rs4=ps4.executeQuery();
					if(rs4.next()) {
						throw new DuplicateCourseSelectionException("Sorry you have already registered this course So please select another");
					}
					else {
						ps5=con.prepareStatement("insert into course_professor_student values(?,?,?)");
						ps5.setInt(1,couId);
						ps5.setInt(2,profId);
						ps5.setInt(3,studentId);
						ps5.executeUpdate();
						System.out.println("Inserted Successfully");
						inserted=true;
						break;
					}
					
					}
					
				
				}//while
				if(inserted==false) {throw new NoVacanyException(" The course ID you selected "
						+ " has no vacancy So please select another course ID");}
				
		
			}
				
			if(noOfCourse==4) {throw new MaxCourseRegistered("Sorry you have already registered four courses");}
		
			
		}
		catch(Exception e) {throw e;}	finally {try {if(ps!=null) {ps.close();}
		if(ps1!=null) {ps1.close();}
		if(ps2!=null) {ps2.close();}
		if(ps3!=null) {ps3.close();}
		if(ps4!=null) {ps4.close();}
		if(ps5!=null) {ps5.close();}
		con.close();}catch(Exception e) {throw e;}		}


		}
	
	public void dropCourse(int studentId) throws Exception {
		// TODO Auto-generated method stub
		
		con=DBUtils.getConnection();
		PreparedStatement ps=null;
		PreparedStatement ps1=null;
		
		
		try {
	ps=con.prepareStatement("select *  from course_professor_student where student_id=? group by 1,2,3 order by 1 ");
	ps.setInt(1,studentId);
	ResultSet rs=ps.executeQuery();
	int counter=0;
	while(rs.next()){
		counter+=1;
		int couId=rs.getInt(1);
		int profId=rs.getInt(2);
		int studId=rs.getInt(3);
		System.out.println("Do you want to Drop  the course ID : "+couId);
		char yesOrNo=sc.next().charAt(0);
		if(yesOrNo=='y' || yesOrNo=='Y'){
		ps1=con.prepareStatement("delete from course_professor_student where course_id=? and professor_id=? and student_id=?");
		ps1.setInt(1,couId);
		ps1.setInt(2,profId);
		ps1.setInt(3,studId);
		ps1.executeUpdate();
		System.out.println("Dropped the course ID "+couId);
		}
		
	}if(counter==0) {throw new com.lt.crs.exceptions.NoCourseRegisterException("No course registered yet");}
	
			
		}
		catch(Exception e) {throw e;}	finally {try {if(ps!=null) {ps.close();}if(ps1!=null) {ps1.close();}con.close();}catch(Exception e) {throw e;}		}

		
	}
	
	public void viewEnrolledCourses(int studentId) throws Exception {
		// TODO Auto-generated method stub
		
		con=DBUtils.getConnection();
		PreparedStatement ps=null;
		
		try {
			ps=con.prepareStatement("select s.student_id,s.student_name,c.course_name,p.professor_name,c.course_id,p.professor_id  from"
					+ " course_professor_student cps inner join course c on c.course_id=cps.course_id inner join "
					+ "professor p on p.professor_id=cps.professor_id inner join student s on cps.student_id=s.student_id where cps.student_id=? group by 1,2,3,4,5,6 order by 5,1");
			ps.setInt(1,studentId);
			ResultSet rs=ps.executeQuery();
				while(rs.next()){

					int couId=rs.getInt(5);
					int profId=rs.getInt(6);
					int studId=rs.getInt(1);
					
					Student st=new Student();
					st.setStudentID(studId);
					st.setStudentName(rs.getString(2));
					Course c=new Course();
					c.setCourseId(couId);
					c.setCourseName(rs.getString(3));
					c.setCourseName(rs.getString(3));
						Professor p=new Professor();
						p.setProfessorId(profId);
						p.setProfessorName(rs.getString(4));
					StudentCourseDetails scd=new StudentCourseDetails();
					scd.setCourse(c);
				scd.setProfessor(p);
					scd.setStudent(st);
					list2.add(scd);
						
	}
				
				if(list2.isEmpty()) {throw new com.lt.crs.exceptions.NoCourseRegisterException("No course registered yet");}
				else {
					
					System.out.println("\t Student ID \t Course ID \t Student Name \t Course Name \t \t Professor Name \t\tProfessor ID");
					list2.stream().forEach(s->System.out.println("\t\t"+s.getStudent().getStudentID()+"\t\t\t"+s.getCourse().getCourseId()
							+"\t\t\t"+s.getStudent().getStudentName()+"\t\t\t\t"+s.getCourse().getCourseName()
							+"\t\t\t\t"+s.getProfessor().getProfessorName()+"\t\t\t\t"+s.getProfessor().getProfessorId())
							);
				
					
				}
				
	
			
		}
		catch(Exception e) {throw e;}	finally {try {if(ps!=null) {ps.close();}con.close();}catch(Exception e) {throw e;}		}

		

		
	}
	
	public void viewReportCard(int studentId) {
		// TODO Auto-generated method stub

		
		con=DBUtils.getConnection();
		PreparedStatement ps=null;
		
		try {
		
			
			ps=con.prepareStatement("select c.course_id,c.course_name,s.student_name,sg.mark,sg.grade from "
					+ "student_grade sg inner join"
					+ " student s on sg.student_id=s.student_id inner join course c on sg.course_id=c.course_id where sg.student_id=? group by 1,2,3,4,5 order by 1");
			ps.setInt(1,studentId);
			ResultSet rs=ps.executeQuery();

			while(rs.next()) {
				Student s=new Student();
				s.setStudentName(rs.getString(3));
				Course c=new Course();
				c.setCourseId(rs.getInt(1));
				c.setCourseName(rs.getString(2));
				StudentGrade sg=new StudentGrade();
				sg.setMark(rs.getDouble(4));
				sg.setGrade(rs.getString(5));
				StudentCourseDetails scd=new StudentCourseDetails();
				scd.setCourse(c);
				scd.setStudent(s);
				scd.setStudentGrade(sg);
				list3.add(scd);
			
			}
			if(list3.isEmpty()) {
				throw new NoReportCardException("No report Found");
			}else {
				System.out.println("Displaying report card for the student ID:  "+studentId);
				System.out.println("\tCourse ID \tCourse Name \tMark \tGrade");
				list3.stream().forEach(s->System.out.println("\t\t"+s.getCourse().getCourseId()+
						"\t\t"+s.getCourse().getCourseName()+"\t\t\t\t"+s.getStudentGrade().getMark()+"\t\t\t"+s.getStudentGrade().getGrade()));
			}
		
			
			
		}
		catch(Exception e) {e.printStackTrace();}	finally {try {if(ps!=null) {ps.close();}con.close();}catch(Exception e) {e.printStackTrace();}		}

		
	}
		
		
	
}
