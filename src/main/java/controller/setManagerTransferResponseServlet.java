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
 * Servlet implementation class setManagerTransferResponseServlet
 */
public class setManagerTransferResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public setManagerTransferResponseServlet() {
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
		int transfer_id = Integer.parseInt(request.getParameter("transfer_id"));
		String decision = request.getParameter("decision");
		String status;
		int manager_response;
		if (decision.equals("approve")) {
			status = "Approved";
			System.out.println("Manager approve");
			manager_response = 1;
		} else if (decision.equals("decline")) {
			status = "Declined";
			manager_response = -1;
		} else {
			status = "Pending";
			manager_response = 0;
		}
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_promotion_system?useSSL=false","root","");
			String sql = "UPDATE transfers SET manager_response = ? WHERE transfer_id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, manager_response);
			statement.setInt(2, transfer_id);

			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		request.setAttribute("msg", "Your response saved :)");
		request.getRequestDispatcher("managerTransferRequests.jsp").forward(request, response);

	}

}
