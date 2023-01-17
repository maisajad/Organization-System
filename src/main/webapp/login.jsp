<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.naming.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>

<style type="text/css">
body {
	font-family: Calibri;
	background-color: #453C67;
	text-align: center;
	font-size: 20px;
}

h1, h2, h4 {
	text-align: center;
	color: #ECECEC;
}

form {
	max-width: 300px;
	margin: 10px auto;
	background-color: #fff;
	border: 1px solid #7E6B8E;
	border-radius: 5px;
	padding: 20px;
}

input[type="text"], input[type="password"] {
	width: 100%;
	padding: 12px;
	border: 1px solid #7E6B8E;
	border-radius: 4px;
	box-sizing: border-box;
	margin-top: 6px;
	margin-bottom: 16px;
	resize: vertical;
	color: #C0C0C0;
	background-color: #36313D
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

.error {
	color: #E57373;
}

table {
	width: 40%;
	margin: auto;
	border-collapse: collapse;
}

td, th {
	border: 1px solid #7E6B8E;
	text-align: center;
	padding: 8px;
	color: #C0C0C0
}

tr:nth-child(even) {
	background-color: #36313D;
}

tr:nth-child(odd) {
	background-color: #36313D;
}
</style>
</head>
<body>
	<h1>Enter Username and password</h1>

	<form action="loginServlet" method="post">
		Username:<br> <input type="text" id="username" name="username"><br>
		Password:<br> <input type="password" id="password"
			name="password"><br> <br> <input type="submit"
			value="Submit">
	</form>
	<%
	String notValid = (String) request.getAttribute("notValid");
	if (notValid != null && !notValid.isEmpty()) {
	%>
	<%=notValid%>
	<%
	}
	%>
	<br>
	<h4>Made with (&#x1F49B) By: Maisaa Jadallah And Noor Alomari</h4>
	<br>
		<br>
	<br>	<br>
	<br>	<br>
	<br>

	<h2>Hint</h2>
	<table>
		<tr>
			<th>Username</th>
			<th>Role</th>
		</tr>
		<tr>
			<td>yildiz_yilmaz</td>
			<td>Professional Employee (ENG)</td>
		</tr>
		<tr>
			<td>david_jackson</td>
			<td>Professional Employee (HR)</td>
		</tr>
		<tr>
			<td>elijah_davis</td>
			<td>Professional Employee (ACC)</td>
		</tr>
		<tr>
			<td>leila_omar</td>
			<td>Professional Employee (S&M)</td>
		</tr>
		<tr>
			<td>john_doe</td>
			<td>Manager (HR)</td>
		</tr>
		<tr>
			<td>emma_williams</td>
			<td>Manager (ACC)</td>
		</tr>
		<tr>
			<td>fatima_yilmaz</td>
			<td>Manager (ENG)</td>
		</tr>
		<tr>
			<td>fatima_ali</td>
			<td>Manager (S&M)</td>
		</tr>
		<tr>
			<td>khalid_argun</td>
			<td>Director (ENG)</td>
		</tr>

		<tr>
			<td>sophia_johnson</td>
			<td>Director (ACC)</td>
		</tr>
		<tr>
			<td>bob_williams</td>
			<td>Director (HR)</td>
		</tr>
		<tr>
			<td>salman_ahmed</td>
			<td>Director (S&M)</td>
		</tr>
		<tr>
			<td>maisaa_jadallah</td>
			<td>VP (ENG & SM)</td>
		</tr>
		<tr>
			<td>nour_alomari</td>
			<td>VP (HR & Acc)</td>
		</tr>
		<tr>
			<td>lionel_messi</td>
			<td>President</td>
		</tr>
	</table>

</body>
</html>