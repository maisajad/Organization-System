<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Information Screen</title>
<style type="text/css">
body {
	font-family:  Calibri;
	background-color: #453C67;
}

h1 {
	text-align: center;
	color: #FFFFFF;
}



form {
	text-align: center;
	margin-top: 20px;
}



input[type="submit"]:hover {
	background-color: #F8BBD0;
	
}

button {
	background-color: #F8BBD0;
	color: #453C67;
	padding: 12px 20px;
	border: #7E6B8E;
	border-radius: 4px;
	cursor: pointer;
	
}
button:hover {
	background-color: #F06292;
}


table {
	width: 60%;
	margin: auto;
	border-collapse: collapse;
	color:#ECECEC;
	background-color: transparent;
	
}

td, th {
	border: 2px solid #7E6B8E;
	text-align: left;
	padding: 8px;
	color:#ECECEC;
}

</style>
</head>
<body>
	<h1>Basic Information:</h1>


	<table>
		<tr>
			<td>First Name:</td>
			<td><%=session.getAttribute("first_name")%></td>
		</tr>
		<tr>
			<td>Last Name:</td>
			<td><%=session.getAttribute("last_name")%></td>
		</tr>
		<tr>
			<td>Username:</td>
			<td><%=session.getAttribute("username")%></td>
		</tr>
		<tr>
			<td>Employee ID:</td>
			<td><%=session.getAttribute("employee_id")%></td>
		</tr>
		<tr>
			<td>Job Category:</td>
			<td><%=session.getAttribute("job_category")%></td>
		</tr>
		<tr>
			<td>Job Title:</td>
			<td><%=session.getAttribute("job_title")%></td>
		</tr>
		<tr>
			<td>Rank:</td>
			<td><%=session.getAttribute("rank")%></td>
		</tr>
	</table>
	<form action="goBackServlet" method="get">
		<button type="submit">Go Back</button>
	</form>
</body>
</html>