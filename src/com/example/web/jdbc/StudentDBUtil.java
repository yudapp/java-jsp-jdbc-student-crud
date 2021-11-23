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

	public Student getStudent(String theStudentId) throws Exception {
		Student student = null;
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int studentId;
		
		try {
			 studentId = Integer.parseInt(theStudentId);
			 myConn = datasource.getConnection();
			 String sql = "select * from student where id=?";
			 myStmt = myConn.prepareStatement(sql);
			 myStmt.setInt(1,studentId);
			 
			 myRs = myStmt.executeQuery();
			 
			 if(myRs.next()) {
				 String firstName = myRs.getString("first_name");
				 String lastName = myRs.getString("last_name");
				 String email = myRs.getString("email");
				 student = new Student(studentId, firstName, lastName, email);
				 return student;
			 } else {
				 throw new Exception("could not find the student with id: "+ theStudentId);
			 }
			 
			
		} finally {
			close(myConn, myStmt, null);
		}
		 
	}

	public void updateStudent(Student student) throws SQLException {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		//get connection, create sql Update statement, prepare it and set params and execute it.
		 
		try {
			myConn = datasource.getConnection();
			String sql ="update student set first_name=?, last_name=?, email=? WHERE id=?";
			
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, student.getFirstName());
			myStmt.setString(2, student.getLastName());
			myStmt.setString(3, student.getEmail());
			myStmt.setInt(4, student.getId());
			
			myStmt.execute();
			
		} finally  {
			close(myConn, myStmt, null);
		}
 
	}

	public void deleteStudent(int studentId) throws SQLException {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			myConn = datasource.getConnection();
			String sql ="DELETE FROM student WHERE id=?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setInt(1, studentId);
			myStmt.execute();
		} finally {
			close(myConn, myStmt, null);
		}
		 
		
	}
}