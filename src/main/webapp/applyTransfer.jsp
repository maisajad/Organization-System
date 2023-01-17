<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Apply transfer screen</title>
<style type="text/css">
body {
	font-family:  Calibri;
	background-color: #453C67;
		font-size: 18px;
	
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
	font-size: 16px;
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
	<h1>The following departments are available to transfer to, select
		one of them:</h1>
	<jsp:include page="/applyTransferServlet" />

	<%
	List<String> available_departments = (ArrayList<String>) request.getAttribute("available_departments");
	%>
	<%
	if (available_departments != null) {
		available_departments.remove(0);
	%>
	<form action="setTransferServlet" method="post">
		<%
		for (String available_department : available_departments) {
		%>
		<input type="radio" id="<%=available_department%>" name="department"
			value="<%=available_department%>"> <label
			for="<%=available_department%>"><%=available_department%></label><br>
		<%
		}
		%>
		<br> <br> <br> <input type="submit" value="Submit">

	</form>
	<%
	} else {

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
	<%
	}
	%>
	<form action="goBackServlet" method="get">
		<button type="submit">Go Back</button>
	</form>

</body>
</html>