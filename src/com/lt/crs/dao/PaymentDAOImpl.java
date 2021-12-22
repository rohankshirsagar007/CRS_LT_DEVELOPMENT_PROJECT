package com.lt.crs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.lt.crs.constants.SqlQueryConstants;
import com.lt.crs.exceptions.CourseNotRegisteredException;
import com.lt.crs.utilsDB.DBUtils;

public class PaymentDAOImpl implements PaymentDAOInterface {
	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;

	public void makePayment(int studentId, int courseId) throws CourseNotRegisteredException
			 {
		con = DBUtils.getConnection();
		try {
			stmt = con.prepareStatement(SqlQueryConstants.student_course_check);
			stmt.setInt(1, studentId);
			stmt.setInt(2, courseId);
			rs = stmt.executeQuery();
			if (rs.next()) {
				rs.close();
				stmt = con.prepareStatement(SqlQueryConstants.get_course_cost);
				stmt.setInt(1, courseId);
				rs = stmt.executeQuery();
				int courseFee = 0;
				int count = 0;
				while (rs.next()) {
					count++;
					courseFee = rs.getInt("course_cost");
				}
				rs.close();
				stmt = con.prepareStatement(SqlQueryConstants.payment_details);
				stmt.setInt(1, studentId);
				stmt.setInt(2, courseId);
				stmt.setString(3, "paid");
				stmt.setInt(4, courseFee * count);
				int result = stmt.executeUpdate();
				if (result != 0) {

					System.out.println("PAID SUCCESSFULLY" +result);

				} else {
					System.out.println("Transaction is unsucessful !");
				}

			} else {
				throw new CourseNotRegisteredException(
						"Course not Registered or Please Enter valid course Id");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
