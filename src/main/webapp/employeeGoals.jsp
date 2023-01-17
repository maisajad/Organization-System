<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Goals Screen</title>
<style type="text/css">
body {
	font-family:  Calibri;
	background-color: #453C67;
}

h1 {
	text-align: center;
	color: #FFFFFF;
}

h2 {
	color: #ECECEC;
}

form {
	color: #ECECEC;
	text-align: center;
	margin-top: 20px;
}

textarea {
color:#C0C0C0;
	width: 50%;
	height: 150px;
	padding: 12px 20px;
	box-sizing: border-box;
	border: 2px solid #7E6B8E;
	border-radius: 4px;
	background-color: #36313D;
	font-size: 18px;
	resize: none;
}

input[type="submit"] {
	background-color: #F8BBD0;
	color: 453C67;
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
	color: 453C67;
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
	<h1>Enter your GOALS for the next year:</h1>
	<br>
	<form action="employeeGoalsServlet" method="post">
		<textarea id="goalsList" name="goalsList" rows="10" cols="50"></textarea>
		<br> <input type="submit" value="Submit">
	</form>
	<%
	String msg = (String) request.getAttribute("msg");
	if (msg != null) {
	%>
	<div class="alert <%= request.getAttribute("alertClass") %>">
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