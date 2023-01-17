<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="model.Employee"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manager Review Screen</title>
<style type="text/css">
body {
	font-family:  Calibri;
	background-color: #453C67;
	font-size: 18px
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

input[type="text"], input[type="number"], textarea, select {
	width: 100%;
	padding: 12px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	margin-top: 6px;
	margin-bottom: 16px;
	resize: vertical;
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
</style>
</head>
<body>
	<jsp:include page="/getEmployeesServlet" />
	<h1>Write a Review for Employee</h1>
	<%
	List<Employee> employees = (List<Employee>) request.getAttribute("employees");
	if (employees != null && !employees.isEmpty()) {
	%>
	<form action="managerReviewServlet" method="post">
		<label for="employee_select">Select an employee to review:</label><br>
		<select name="employee_select" id="employee_select">
			<option value="default">--Select an employee--</option>
			<%
			for (Employee employee : employees) {
			%>
			<option value="<%=employee.getEmployeeId()%>"><%=employee.getFirstName()%>
				<%=employee.getLastName()%></option>
			<%
			}
			%>
		</select><br> <label for="rating">Rating (%):</label><br> <input
			type="number" id="rating" name="rating" min="1" max="100"><br>
		<label for="review_description">Review Description:</label><br>
		<textarea id="review_description" name="review_description" rows="5"
			cols="50"></textarea>
		<br> <br> <input type="submit" value="Submit">
	</form>
	<%
	} else {
	%>
	There is no employees!
	<%
	}
	
	String msg = (String) request.getAttribute("msg");
	if (msg != null) {
	%>
<div class="alert <%= request.getAttribute("alertClass") %>">
		<span class="closebtn"
			onclick="this.parentElement.style.display='none';">&times;</span>
		<%=msg%>
	</div>	<%
	}
	%>

	<form action="goBackServlet" method="get">
		<button type="submit">Go Back</button>
	</form>
</body>
</html>