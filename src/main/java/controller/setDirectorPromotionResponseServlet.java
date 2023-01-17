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

/**
 * Servlet implementation class setDirectorPromotionResponseServlet
 */
public class setDirectorPromotionResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public setDirectorPromotionResponseServlet() {
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
		int promotion_id = Integer.parseInt(request.getParameter("promotion_id"));
		String decision = request.getParameter("decision");
		System.out.println(decision);
		String status;
		int director_response;
		if (decision.equals("approve")) {
			status = "Approved";
			director_response = 1;
		} else if (decision.equals("decline")) {
			status = "Declined";
			director_response = -1;
		} else {
			status = "InProcess";
			director_response = 0;
		}
		System.out.println(director_response+" "+promotion_id);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_promotion_system?useSSL=false","root","");
			String sql = "UPDATE promotions SET director_response = ? WHERE promotion_id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, director_response);
			statement.setInt(2, promotion_id);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		request.setAttribute("msg", "Your response saved :)");
		request.getRequestDispatcher("directorPromotionRequests.jsp").forward(request, response);

	}

}
