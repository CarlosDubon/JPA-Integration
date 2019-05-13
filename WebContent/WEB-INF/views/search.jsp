<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/searchById" method="POST">
		<label>Student id:</label>
		<input type='text' name='id' required> 
		<button>Send</button>
	</form>
	<br>
	<h3>${student.sName}</h3>
	<p>${student.lName}</p>
	<p>${student.sAge}</p>
	<p>${student.activoDelegate}</p>
</body>
</html>