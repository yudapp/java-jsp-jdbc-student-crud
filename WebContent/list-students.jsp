<%@ page import="java.util.*, com.example.web.jdbc.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student App</title>
<link type="text/css" rel="stylesheet" href="css/style.css"/>
</head>
<%
	//get students fron the request object sent by servlet

List<Student> theStudents = (List<Student>) request.getAttribute("student_list");
%>
<body>

	<div id="wrapper">
		<div id="header">
			<h2>Student App</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">
			<table>
				<thead>
					<th>FirstName</th>
					<th>Last Name</th>
					<th>Email</th>
				</thead>
				<tbody>

					<%
						for (Student tempStudent : theStudents) {
					%>
					<tr>
						<td><%=tempStudent.getFirstName()%></td>
						<td><%=tempStudent.getLastName()%></td>
						<td><%=tempStudent.getEmail()%></td>
					</tr>

					<%
						}
					%>
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>