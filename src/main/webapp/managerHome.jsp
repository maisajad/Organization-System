<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manager Home Screen</title>
<style type="text/css">
body {
	font-family:  Calibri;
	background-color: #453C67;
	text-align: center;
}

h1 {
	text-align: center;
	color: #FFFFFF;
}

h2 {
	color: #ECECEC;
	margin-bottom: 20px;
}

ul {
	list-style: none;
	padding: 0;
	margin: 0;
}

li {
	margin-bottom: 10px;
}

a {
	text-decoration: none;
	color: #1e90ff;
}

a:hover {
	color: #FFFFFF;
}

form {
	text-align: center;
	margin-top: 20px;
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

.grid-container {
	display: grid;
	grid-template-columns: repeat(2, 1fr);
	grid-template-rows: repeat(2, 1fr);
	grid-gap: 40px;
	padding: 10px;
}

.grid-item {
	border: 1px solid #7E6B8E;
	border-radius: 6px; 
	background-color: #BECCF3;
	padding: 20px;
	font-size: 20px;
	text-align: center;
}
.grid-item:hover {
  background-color: #93A9EB;
}

.grid-item a {
	text-decoration: none;
	color: #000;
}
</style>
</head>
<body>
	<h1>
		Welcome,
		<%=session.getAttribute("first_name")%></h1>
	<h2>
		Role:
		<%=session.getAttribute("job_category")%></h2>
	<div class="grid-container">
		<div class="grid-item">
			<a href="employeeInformation.jsp">View your information</a>
		</div>
		<div class="grid-item">
			<a href="employeeGoals.jsp">Set goals for the year</a>
		</div>
		<div class="grid-item">
			<a href="viewGoalsScreen.jsp">View your goals</a>
		</div>
		<div class="grid-item">
			<a href="employeeAchievements.jsp">Set achievements for the year</a>
		</div>
		<div class="grid-item">
			<a href="viewAchievementsScreen.jsp">View your achievements</a>
		</div>
		<div class="grid-item">
			<a href="applyPromotion">Apply for a promotion</a>
		</div>
		<div class="grid-item">
			<a href="viewPromotionsScreen.jsp">View your promotions</a>
		</div>
		<div class="grid-item">
			<a href="applyTransfer.jsp">Apply for a transfer</a>
		</div>
		<div class="grid-item">
			<a href="viewTransferScreen.jsp">View your transfer</a>
		</div>
	</div>
	<h2>Manager Features</h2>
	<div class="grid-container">
		<div class="grid-item">
			<a href="managerReview.jsp">Write reviews</a>
		</div>
		<div class="grid-item">
			<a href="managerTransferRequests.jsp">View transfer requests</a>
		</div>
		<div class="grid-item">
			<a href="managerPromotionRequests.jsp">View promotion requests</a>
		</div>
	</div>
	<form action="logoutServlet" method="post">
		<input type="submit" value="Logout">
	</form>
</body>
</html>