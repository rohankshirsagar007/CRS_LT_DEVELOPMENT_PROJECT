package com.lt.crs.constants;

public interface SqlQueryConstants {
	String student_login_query="select * from student where student_id=? and student_password=?";
	String user_role_student="select * from user_role where user_id=? and role_id=3";
	String user_role_professor="select * from user_role where user_id=? and role_id=2";
	String professor_login_query="select * from professor where professor_id=? and professor_password=?";
	String user_role_admin="select * from user_role where user_id=? and role_id=1";
	String admin_login_query="select * from admin where admin_id=? and admin_password=?";
	String admin_password_update="update admin set admin_password=? where admin_id=?";
	String professor_password_update="update professor set professor_password=? where professor_id=?";
	String student_password_update="update student set student_password=? where student_id=?";
	String student_registeration="insert into student (student_name,student_password,student_department) values (?,?,?)";
	String student_course_check="select * from course_professor_student where student_id=? and course_id=?";
	String get_course_cost="select course_cost from course where course_id=?";
	String payment_details = "insert into payment (student_id,course_id,payment_status,amount) values(?,?,?,?)";

}
