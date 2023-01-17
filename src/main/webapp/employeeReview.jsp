<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="model.Review"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Review Screen</title>

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
	<jsp:include page="/viewReviewServlet" />
	<h1>My Reviews</h1>
	<%
	List<Review> reviews = (ArrayList<Review>) request.getAttribute("reviews");
	%>

	<%
	if (reviews != null ) {
	%>

	<table>
		<tr>
			<th>#</th>
			<th>Manager name</th>
			<th>Review year</th>
			<th>Review MSG</th>
			<th>Rate (%)</th>
			<th>Status</th>
		</tr>
		<%
		int i = 1;
		for (Review review : reviews) {
		%>
		<tr>
			<td><%=i%></td>
			<td><%=request.getAttribute("manager_name")%></td>
			<td><%=review.getReview_year()%></td>
			<td><%=review.getReview_msg()%></td>
			<td><%=review.getRate()%> %</td>
			<td><%=review.getStatus()%></td>
		</tr>
		<%
		i++;
		}
		%>
	</table>
	<%
	} else {
	%>
	<p>No reviews have been set yet.</p>
	<%
	}
	%>
	<form action="goBackServlet" method="get">
		<button type="submit">Go Back</button>
	</form>

</body>
</html>