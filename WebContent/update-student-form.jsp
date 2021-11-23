<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add a student</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<link type="text/css" rel="stylesheet" href="css/add-student-style.css" />
</head>
<body>

	<div id="wrapper">
		<div id="header">
			<h2>Student App</h2>
		</div>
	</div>

	<div id="container">
		<h3>Update Student</h3>
		<form action="StudentControllerServlet" method="GET">
			<input type="hidden" name="command" value="UPDATE" />
			<input type="hidden" name="studentId" value="${STUDENT.id}" />
			<table>
				<tr>
					<td>First Name:</td>
					<td><input type="text" name="firstName" value="${STUDENT.firstName}"/> <br /> <br /></td>
				</tr>
				<tr>
					<td>Last Name:</td>
					<td><input type="text" name="lastName" value="${STUDENT.lastName}"/> <br /> <br /></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><input type="text" name="email" value="${STUDENT.email}"/> <br /> <br /></td>
				</tr>
			</table>
			<input type="submit" value="Save" class="save" />
		</form>
		
		<div class="clear"></div>
		
		<p>
		 <a href="StudentControllerServlet">Back to List</a>
		</p>

	</div>

</body>
</html>