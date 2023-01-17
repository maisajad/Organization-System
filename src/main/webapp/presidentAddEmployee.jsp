<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add new employee</title>

<style type="text/css">
body {
	font-family: Calibri;
	background-color: #453C67;
	font-size: 20px
}

h1 {
	text-align: center;
	color: #FFFFFF;
}

form {
	text-align: center;
	margin: 20px auto;
	width: 50%;
	padding: 20px;
	border: 1px solid #ccc;
	border-radius: 4px;
	background-color: #fff;
}

label {
	display: block;
	margin: 10px 0;
}

input[type="text"], input[type="number"], input[type="password"],
	textarea, select {
	width: 50%;
	padding: 12px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	margin-top: 6px;
	margin-bottom: 16px;
	resize: vertical;
	font-size: 16px
}

input[type="submit"] {
	background-color: #F8BBD0;
	color: #453C67;
	padding: 12px 20px;
	border: #7E6B8E;
	border-radius: 4px;
	cursor: pointer;
}

input[type="submit"]:hover {
	background-color: #F06292;
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

.alert {
	padding: 20px;
	background-color: #24CCBB;
	color: black;
	margin-bottom: 15px;
	margin-top: 15px;
	text-align: center;
}

.alert.success {
	background-color: #4CAF50;
}

.closebtn {
	margin-left: 15px;
	color: white;
	font-weight: bold;
	float: right;
	font-size: 22px;
	line-height: 20px;
	cursor: pointer;
	transition: 0.3s;
}

.closebtn:hover {
	color: black;
}

select {
	width: 50%;
	padding: 8px 12px;
	margin: 8px 0;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}
</style>
</head>
<body>

	<form action="addNewEmployeeServlet" method="post">
		<label for="username">Username:</label> <input type="text"
			id="username" name="username" required> <br> <label
			for="first_name">First Name:</label> <input type="text"
			id="first_name" name="first_name" required> <br> <label
			for="last_name">Last Name:</label> <input type="text" id="last_name"
			name="last_name" required> <br> <label
			for="department_id">Department name:</label> <select
			id="department_id" name="department_id" required>
			<option value="default">--Select--</option>
			<option value="1">1- Human Resources</option>
			<option value="2">2- Sales and Marketing</option>
			<option value="3">3- Accounting</option>
			<option value="4">4- Engineering</option>
		</select> <br> <label for="job_category">Job Category:</label> <select
			id="job_category" name="job_category" required>
			<option value="default">--Select--</option>
			<option value="ProfessionalEmployee">Employee</option>
			<option value="Manager">Manager</option>
			<option value="Director">Director</option>
			<option value="VicePresident">Vice President</option>

		</select> <br> <label for="job_title">Job Title:</label> <input
			type="text" id="job_title" name="job_title" required> <br>

		<label for="rank">Rank:</label> <select id="rank" name="rank" required>
			<option value="default">--Select--</option>
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
		</select> <br> <label for="monthly_salary">Monthly Salary:</label> <input
			type="number" id="monthly_salary" name="monthly_salary" required>
		<br> <label for="password">Password:</label> <input
			type="password" id="password" name="password" required> <br>
		<input type="submit" value="Add Employee"> <br>
	</form>
<%
String msg = (String) request.getAttribute("msg");
if (msg != null) {
	%>
	<div class="alert <%=request.getAttribute("alertClass")%>">
		<span class="closebtn"
			onclick="this.parentElement.style.display='none';">&times;</span>
		<%=msg%>
	</div>
	<%
	}
	%>
	<form action="goBackServlet" method="get">
		<button type="submit">Go Back</button>
	</form>

</body>
</html>