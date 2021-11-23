<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student App</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>

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
					<th>Action</th>
				</thead>
				<tbody>
					<c:forEach var="tempStudent" items="${student_list}">
					
					<c:url var="tempLink" value="StudentControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="studentId" value="${tempStudent.id}" />
					</c:url>
						<tr>
							<td>${tempStudent.firstName}</td>
							<td>${tempStudent.lastName}</td>
							<td>${tempStudent.email}</td>
							<td>
							<a href="${tempLink}">Update</a>
							| 
							<a href="${tempLink}">Delete</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<a href="add-student-form.jsp" class="btn btn-add">ADD STUDENT</a>
	</div>
</body>
</html>