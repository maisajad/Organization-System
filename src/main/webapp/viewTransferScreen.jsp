<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="model.Transfer"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee transfer</title>

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

	<jsp:include page="/viewTransferServlet" />
	<h1>My Transfers</h1>
	<%
	List<Transfer> transfers = (ArrayList<Transfer>) request.getAttribute("setted_transfers");
	%>

	<%
	if (transfers != null) {
	%>

	<table>
		<tr>
			<th>#</th>
			<th>Requested department</th>
			<th>Status</th>
		</tr>
		<%
		int i = 1;
		for (Transfer transfer : transfers) {
		%>
		<tr>
			<td><%=i%></td>
			<td><%=transfer.getNew_department_name()%></td>
			<td><%=transfer.getStatus()%></td>
		</tr>
		<%
		i++;
		}
		%>
	</table>
	<%
	} else {
	%>
	<p>No Transfers have been set yet.</p>
	<a href="applyTransfer.jsp">Apply transfer here</a>
	<%
	}
	%>
	<form action="goBackServlet" method="get">
		<button type="submit">Go Back</button>
	</form>
</body>
</html>