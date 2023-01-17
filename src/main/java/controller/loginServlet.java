package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.Statement;

import enums.JobCategory;
import enums.Rank;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/login")

public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public loginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_promotion_system?useSSL=false","root","");
			java.sql.Statement statement =  connection.createStatement();
			String sqlQuery="select * from employees where username = " +"'"+username +"'"+ " and password = "+"'"+password+"'";
			ResultSet resultSet = statement.executeQuery(sqlQuery);
			if (resultSet.next()) {
				int employee_id = resultSet.getInt("employee_id");
				String firstName=resultSet.getString("first_name");
				String lastName=resultSet.getString("last_name");
				String jobCategorystr = resultSet.getString("job_category");
				JobCategory jobCategory=JobCategory.valueOf(jobCategorystr);
				int rank = resultSet.getInt("rank");
				String jobTitle=resultSet.getString("job_title");
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				session.setAttribute("last_name", lastName);
				session.setAttribute("first_name", firstName);
				session.setAttribute("employee_id", employee_id);
				session.setAttribute("job_category", jobCategory);				
				session.setAttribute("job_title", jobTitle);
				session.setAttribute("rank", rank);
				int department_id = resultSet.getInt("department_id");
				session.setAttribute("department_id", department_id);

				int manager_id = resultSet.getInt("manager_id");

				if (resultSet.wasNull()) {
					session.setAttribute("manager_id", "No Manager");
				} else {
					session.setAttribute("manager_id", manager_id);
				}

				if (jobCategory.equals(JobCategory.ProfessionalEmployee)) {
					response.sendRedirect("employeeHome.jsp");
				} else if (jobCategory.equals(JobCategory.Manager)) {
					response.sendRedirect("managerHome.jsp");
				} else if (jobCategory.equals(JobCategory.Director)) {
					response.sendRedirect("directorHome.jsp");
				} else if (jobCategory.equals(JobCategory.VicePresident)) {
					response.sendRedirect("vicePresidentHome.jsp");
				} else if (jobCategory.equals(jobCategory.President)) {
					response.sendRedirect("presidentHome.jsp");				
				} 
			}else {
				request.setAttribute("notValid", "Invalid username or password");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
