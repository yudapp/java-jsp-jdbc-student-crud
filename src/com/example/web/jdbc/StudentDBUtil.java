package com.example.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDBUtil {

	private DataSource datasource;

	public StudentDBUtil(DataSource datasource) {
		this.datasource = datasource;
	}

	public List<Student> getStudents() throws Exception {

		List<Student> students = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			// get a connection, create sql statement, execute query
			// process result set, close JDBC object.
			myConn = datasource.getConnection();
			String sql = "select * from student order by last_name";
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);

			while (myRs.next()) {
				int id = myRs.getInt("id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");

				// create student object and add them to the list.
				Student tempStudent = new Student(id, firstName, lastName, email);
				students.add(tempStudent);
			}

			return students;

		} finally {

			close(myConn, myStmt, myRs);
		}

	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		try {
			if (myRs != null)
				myRs.close();
			if (myStmt != null)
				myStmt.close();
			if (myConn != null)
				myConn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addStudent(Student student) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = datasource.getConnection();

			String sql = "insert into student (first_name, last_name, email) values(?,?,?)";

			// set param values
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, student.getFirstName());
			myStmt.setString(2, student.getLastName());
			myStmt.setString(3, student.getEmail());

			myStmt.execute();
			
		} finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}
}