<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>

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



form {
	text-align: center;
	margin-top: 20px;
}

input[type="submit"] {
	background-color: #4CAF50;
	color: white;
	padding: 12px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

input[type="submit"]:hover {
	background-color: #45a049;
}

button {
	background-color: #4CAF50;
	color: white;
	padding: 12px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

button:hover {
	background-color: #45a049;
}


table {
	width: 60%;
	margin: auto;
	border-collapse: collapse;
		color:#ECECEC;
	
}

td, th {
	border: 2px solid #7E6B8E;
	text-align: left;
	padding: 8px;
	color:#ECECEC;
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

</style>
</head>
<body>
	<jsp:include page="/viewGoalsServlet" />
	<h1>My Goals</h1>
	<%
	List<String> goals = (ArrayList<String>) request.getAttribute("setted_goals");
	%>

	<%
	if (goals != null) {
	%>
	<table>
		<%
		int i = 1;
		for (String goal : goals) {
		%>
		<tr>
			<td><%=i%></td>
			<td><%=goal%></td>
		</tr>
		<%
		i++;
		}
		%>
	</table>
	<%
	} else {
	%>
	<p>No goals have been set yet.</p>
	<a href="employeeGoals.jsp">Set goals for the year</a>
	<%
	}
	%>
	<form action="goBackServlet" method="get">
		<button type="submit">Go Back</button>
	</form>
</body>
</html>