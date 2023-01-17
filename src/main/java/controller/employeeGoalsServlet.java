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
import java.sql.PreparedStatement;
import java.util.Calendar;

/**
 * Servlet implementation class employeeGoalsServlet
 */
public class employeeGoalsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public employeeGoalsServlet() {
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
		HttpSession session = request.getSession();
		String goalsList=request.getParameter("goalsList");
		Calendar calendar = Calendar.getInstance();
		int currentYear = calendar.get(Calendar.YEAR);
		if(goalsList!=null) {
			try {
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_promotion_system?useSSL=false","root","");
				String sql = "INSERT INTO goals (employee_id,goal_year,goal_description) VALUES (?,?,?)";
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setInt(1,(int)session.getAttribute("employee_id"));
				statement.setInt(2,currentYear);
				statement.setString(3, goalsList);
				statement.executeUpdate();
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
			request.setAttribute("msg", "Thank You! you can view your goals from the home page :)");
			request.getRequestDispatcher("employeeGoals.jsp").forward(request, response);

		}
	}

















}
