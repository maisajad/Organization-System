<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*"%>
<%@ page import="model.Transfer"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All transfers</title>
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
	<jsp:include page="/viewAllTransferServlet" />
	<h1>Transfer requests</h1>
	<%
	List<Transfer> transfers = (ArrayList<Transfer>) request.getAttribute("transfers");
	List<String> employee_names = (ArrayList<String>) request.getAttribute("employee_names");
	String msg = (String) request.getAttribute("msg");

	%>

	<%
	if (transfers != null && !transfers.isEmpty()) {
	%>

		<table>
			<tr>
				<th>#</th>
				<th>Employee Name</th>
				<th>Requested department</th>
				<th>Status</th>
			</tr>
			<%
			for (int i = 0; i < transfers.size(); i++) {
			%>
			<tr>
				<td><%=i + 1%></td>
				<td><%=employee_names.get(i)%></td>
				<td><%=transfers.get(i).getNew_department_name()%></td>
				<td><%=transfers.get(i).getStatus()%></td>
			</tr>
			<%
			}
			%>
		</table>
		<br>


	<%
	} else if(msg==null &&transfers == null){
	%>
	<p>No transfers have been set yet.</p>
	<%} %>



	<form action="goBackServlet" method="get">
		<button type="submit">Go Back</button>
	</form>
	
	
	
</body>
</html>