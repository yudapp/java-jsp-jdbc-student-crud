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
				</thead>
				<tbody>
					<c:forEach var="tempStudent" items="${student_list}">
						<tr>
							<td>${tempStudent.firstName}</td>
							<td>${tempStudent.lastName}</td>
							<td>${tempStudent.email}</td>
						</tr>

					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>