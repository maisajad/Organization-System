<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="model.Transfer"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>Transfer requests</title>

<style type="text/css">
body {
	font-family:  Calibri;
	background-color: #453C67;
}

h1 {
	text-align: center;
	color: #FFFFFF;
}

table {
	margin: 0 auto;
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
	color:#fff;
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
  width: 150px;
  padding: 8px 12px;
  margin: 8px 0;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
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

</style>
</head>
<body>
	<jsp:include page="/vpTransferRequestsServlet" />
	<h1>Transfer requests</h1>
	<%
	List<Transfer> transfers = (ArrayList<Transfer>) request.getAttribute("transfers");
	List<String> employee_names = (ArrayList<String>) request.getAttribute("employee_names");
	String msg = (String) request.getAttribute("msg");

	%>

	<%
	if (transfers != null && !transfers.isEmpty()) {
	%>
	<form action="setVPTransferResponseServlet" method="post">

		<table>
			<tr>
				<th>#</th>
				<th>Employee Name</th>
				<th>Requested department</th>
				<th>Status</th>
				<th>Approve/Decline</th>
			</tr>
			<%
			for (int i = 0; i < transfers.size(); i++) {
			%>
			<tr>
				<td><%=i + 1%></td>
				<td><%=employee_names.get(i)%></td>
				<td><%=transfers.get(i).getNew_department_name()%></td>
				<td><%=transfers.get(i).getStatus()%></td>
				<td><input type="hidden" name="transfer_id"
					value="<%=transfers.get(i).getTransfer_id()%>"> <select
					name="decision">
						<option value="default">--Select--</option>
						<option value="approve">Approve</option>
						<option value="decline">Decline</option>
				</select></td>

			</tr>
			<%
			}
			%>
		</table>
		<br>
		<button type="submit">Submit</button>

	</form>

	<%
	} else if(msg==null &&transfers == null){
	%>
	<p>No transfers have been set yet.</p>
	<%
	}
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