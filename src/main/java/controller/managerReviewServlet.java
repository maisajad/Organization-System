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
import java.sql.ResultSet;
import java.util.Calendar;

/**
 * Servlet implementation class managerReviewServlet
 */
public class managerReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public managerReviewServlet() {
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
		int manager_id = (int) request.getSession().getAttribute("employee_id");
		String review_msg=(String)request.getParameter("review_description");
		int rate =Integer.parseInt( request.getParameter("rating"));
		int selectedEmployee = Integer.parseInt( request.getParameter("employee_select"));
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_promotion_system?useSSL=false","root","");
			String query ="INSERT INTO `reviews`(`employee_id`, `review_year`, `status`, `manager_id`,`review_msg`,`rate`) VALUES (?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1,selectedEmployee);
			Calendar calendar = Calendar.getInstance();
			int currentYear = calendar.get(Calendar.YEAR);
			statement.setInt(2,currentYear);
			statement.setString(3, "Completed");
			statement.setInt(4, manager_id);
			statement.setString(5, review_msg);
			statement.setInt(6, rate);
			statement.executeUpdate();
			if(rate>=60) {

			query="SELECT monthly_salary from employees where employee_id=?";
			statement = connection.prepareStatement(query);
			statement.setInt(1,selectedEmployee);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				double monthly_salary=rs.getDouble("monthly_salary");
				double increase=0.1*rate/100*(monthly_salary*12);
				monthly_salary=increase;
				query="UPDATE employees SET monthly_salary= ? where employee_id = ?;";
				statement = connection.prepareStatement(query);
				statement.setDouble(1, monthly_salary);
				statement.setInt(2,selectedEmployee);
				statement.executeUpdate();
			}
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		request.setAttribute("msg", "The review sent to the employee :)");
		request.getRequestDispatcher("managerReview.jsp").forward(request, response);
	}

}
















