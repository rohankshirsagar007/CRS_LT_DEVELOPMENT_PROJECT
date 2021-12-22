package com.lt.crs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.lt.crs.bean.Course;
import com.lt.crs.bean.Professor;
import com.lt.crs.bean.Student;
import com.lt.crs.dto.StudentCourseDetails;
import com.lt.crs.exceptions.NoStudentRegisteredException;
import com.lt.crs.utilsDB.DBUtils;

public class ProfessorDAOImpl implements ProfessorDAOInterface {

	static Scanner sc = new Scanner(System.in);

	Connection con = null;

	Student st = null;
	Course cs = null;
	Professor pf = null;
	StudentCourseDetails scd = null;
	LinkedList<StudentCourseDetails> list1=new LinkedList<StudentCourseDetails>();

	public void viewStudent(int profId) throws Exception {
		// TODO Auto-generated method stub

		con = DBUtils.getConnection();
		PreparedStatement ps = null;

		try {
			ps = con.prepareStatement("\r\n"
					+ "select s.student_id,s.student_name,c.course_name,p.professor_name,c.course_id  from course_professor_student cps "
					+ "inner join course c on c.course_id=cps.course_id inner join professor p "
					+ "on p.professor_id=cps.professor_id inner join student s on cps.student_id=s.student_id where cps.professor_id=? group by 1,2,3,4,5 order by 5,1");
			ps.setInt(1, profId);
			ResultSet rs = ps.executeQuery();
			

			while (rs.next()) {
				Student st=new Student();
				st.setStudentID(rs.getInt(1));
				st.setStudentName(rs.getString(2));
				Course c=new Course();
				c.setCourseId(rs.getInt(5));
				c.setCourseName(rs.getString(3));
				Professor p=new Professor();
				p.setProfessorName(rs.getString(4));
				
				StudentCourseDetails scd=new StudentCourseDetails();
				scd.setCourse(c);
				scd.setProfessor(p);
				scd.setStudent(st);
				list1.add(scd);			}

			if(list1.isEmpty()) {throw new NoStudentRegisteredException("No students registered for your course");}
			else {
				System.out.println("\t Student ID \t Course ID \t Student Name \t Course Name \t \t Professor Name");
				list1.stream().forEach(s->System.out.println("\t\t"+s.getStudent().getStudentID()+"\t\t\t"+s.getCourse().getCourseId()+"\t\t\t"+s.getStudent().getStudentName()+"\t\t\t\t"+s.getCourse().getCourseName()+"\t\t\t\t"+s.getProfessor().getProfessorName())
						);
			}
		}
			
           catch (Exception e) {
			throw e;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				con.close();
			} catch (Exception e) {
				throw e;
			}
		}

	}

	public void addGrade(int profId) throws Exception {
		// TODO Auto-generated method stub

		con = DBUtils.getConnection();
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		try {
			ps = con.prepareStatement("\r\n"
					+ "select s.student_id,s.student_name,c.course_name,p.professor_name,c.course_id  from course_professor_student cps "
					+ "inner join course c on c.course_id=cps.course_id inner join professor p "
					+ "on p.professor_id=cps.professor_id inner join student s on cps.student_id=s.student_id where cps.professor_id=? group by 1,2,3,4,5 order by 5");
			ps.setInt(1, profId);
			ResultSet rs = ps.executeQuery();
			int count = 0;
			while (rs.next()) {
				count += 1;
				System.out
						.println("Do you want to add Grade to the Following Student: ");
				System.out.println("Student ID : 	" + rs.getInt(1)
						+ "		Student Name:		" + rs.getString(2)
						+ "		Course Name: 		" + rs.getString(3) + " 	"
						+ "Professor Name :	" + rs.getString(4)
						+ "		Course ID :	 " + rs.getInt(5));
				char ask = sc.next().charAt(0);
				if (ask == 'y' || ask == 'Y') {
					System.out.println("Adding Grade :");
					try {
						System.out.println("Enter Mark : ");
						double mark = sc.nextDouble();
						String grade = findingGrade(mark);

						ps1 = con
								.prepareStatement("insert into student_grade values(?,?,?,?,?)");
						ps1.setInt(1, rs.getInt(5));
						ps1.setInt(2, profId);
						ps1.setInt(3, rs.getInt(1));
						ps1.setDouble(4, mark);
						ps1.setString(5, grade);
						ps1.executeUpdate();
						System.out.println("Grade Added Successfully");

					} catch (Exception e) {
						throw e;
					}
				}

			}
			if (count == 0) {
				throw new NoStudentRegisteredException(
						"No students registered for your course");
			}

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (ps1 != null) {
					ps1.close();
				}
				con.close();
			} catch (Exception e) {
				throw e;
			}
		}

	}

	private static String findingGrade(double mark) {
		// TODO Auto-generated method stub
		if (mark >= 0 && mark < 50) {
			return "Fail";
		} else if (mark >= 50 && mark < 60) {
			return "E";
		} else if (mark >= 60 && mark < 70) {
			return "D";
		} else if (mark >= 70 && mark < 80) {
			return "C";
		} else if (mark >= 80 && mark < 90) {
			return "B";
		} else {
			return "A";
		}

	}

}
