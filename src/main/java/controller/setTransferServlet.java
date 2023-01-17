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

import com.mysql.cj.xdevapi.Statement;

/**
 * Servlet implementation class setTransferServlet
 */
public class setTransferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public setTransferServlet() {
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
		String selected_department=request.getParameter("department");
		HttpSession session = request.getSession();
		int employee_id = (int) request.getSession().getAttribute("employee_id");
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_promotion_system?useSSL=false","root","");
			String query = "SELECT department_id FROM departments WHERE department_name = '" + selected_department + "'";
			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				int department_id = rs.getInt("department_id");
				String sql = "INSERT INTO `transfers`(`employee_id`, `new_department_id`, `status`) VALUES (?,?,?)";
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setInt(1,employee_id);
				statement.setInt(2,department_id);
				statement.setString(3, "InProcess");
				statement.executeUpdate();
			}}

		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		request.setAttribute("msg", "You applied for a transfer :)");
		request.getRequestDispatcher("applyTransfer.jsp").forward(request, response);
	}


}
