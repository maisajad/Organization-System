package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import enums.JobCategory;

/**
 * Servlet implementation class addNewEmployeeServlet
 */
public class addNewEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public addNewEmployeeServlet() {
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name");
		String job_title = request.getParameter("job_title");
		String password = request.getParameter("password");
		JobCategory job_category = JobCategory.valueOf(request.getParameter("job_category"));
		int rank =Integer.parseInt(request.getParameter("rank"));
		double monthly_salary =Double.parseDouble(request.getParameter("monthly_salary"));
		int department_id=Integer.parseInt(request.getParameter("department_id"));
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_promotion_system?useSSL=false","root","");
			String sql = "INSERT INTO employees (username, first_name, last_name, job_title, password, job_category, rank,monthly_salary,department_id)\r\n"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?,?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, first_name);
			statement.setString(3, last_name);
			statement.setString(4, job_title);
			statement.setString(5, password);
			statement.setString(6, job_category.name());
			statement.setInt(7, rank);
			statement.setDouble(8, monthly_salary);
			statement.setInt(9, department_id);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		request.setAttribute("msg", "Employee added successfuly :)");
		request.getRequestDispatcher("presidentAddEmployee.jsp").forward(request, response);

	}

}
