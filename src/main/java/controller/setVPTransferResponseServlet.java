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
 * Servlet implementation class setVPTransferResponseServlet
 */
public class setVPTransferResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public setVPTransferResponseServlet() {
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
		int vp_response;
		if (decision.equals("approve")) {
		    status = "Approved";
		    vp_response = 1;
		} else if (decision.equals("decline")) {
		    status = "Declined";
		    vp_response = -1;
		} else {
		    status = "Pending";
		    vp_response = 0;
		}
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_promotion_system?useSSL=false","root","");
			String sql = "UPDATE transfers SET status = ?, vp_response = ? WHERE transfer_id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, status);
			statement.setInt(2, vp_response);
			statement.setInt(3, transfer_id);
			statement.executeUpdate();
			
			sql="UPDATE employees e\r\n"
					+ "INNER JOIN transfers t ON e.employee_id = t.employee_id\r\n"
					+ "SET e.department_id = t.new_department_id, e.job_title = 'Employee'\r\n"
					+ "WHERE t.transfer_id = ?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, transfer_id);
			statement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		request.setAttribute("msg", "Your response saved :)");
		request.getRequestDispatcher("vpTransferRequests.jsp").forward(request, response);

	}

}
