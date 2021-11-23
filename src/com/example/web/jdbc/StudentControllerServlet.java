package com.example.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private StudentDBUtil studentDBUtil;

	@Resource(name = "jdbc/web_student_tracker")
	private DataSource datasource;

	@Override
	public void init() throws ServletException {
		super.init();
		// create out student db util and pass in the conn pool
		try {
			studentDBUtil = new StudentDBUtil(datasource);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// read "command" parameter ( we used in add-student etc..)
		String command = request.getParameter("command");

		// list student in MVC way
		if (command == null) {
			listStudents(request, response);
		} else {
			switch (command) {
			case "LIST":
				listStudents(request, response);
				break;

			case "ADD":
				addStudent(request, response);
				break;
			case "LOAD":
				loadStudent(request, response);
				break;
			case "UPDATE":
				updateStudent(request, response);
				break;
			default:
				listStudents(request, response);
			}
		}

	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response)  {
		 // read student info form form data, create new student object, perform update on database
		int id = Integer.parseInt(request.getParameter("studentId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		Student student = new Student(id, firstName, lastName, email);
		
		
		try {
			studentDBUtil.updateStudent(student);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//send user bas to list
		listStudents(request, response);
	}

	private void loadStudent(HttpServletRequest request, HttpServletResponse response) {

		// read student id from form, get student from db and send to update form
		try {
		String studentId = request.getParameter("studentId");
		Student student = studentDBUtil.getStudent(studentId);
		request.setAttribute("STUDENT", student);
		request.getRequestDispatcher("/update-student-form.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

	private void addStudent(HttpServletRequest request, HttpServletResponse response) {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		Student theStudent = new Student(firstName, lastName, email);
		try {
			studentDBUtil.addStudent(theStudent);
			listStudents(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void listStudents(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Student> students = studentDBUtil.getStudents();
			request.setAttribute("student_list", students);
			request.getRequestDispatcher("/list-students.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
