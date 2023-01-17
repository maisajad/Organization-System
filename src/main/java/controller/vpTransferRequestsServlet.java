package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Transfer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class vpTransferRequestsServlet
 */
public class vpTransferRequestsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public vpTransferRequestsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int manager_id = (int) request.getSession().getAttribute("employee_id");
		List<Transfer> transfers = new ArrayList<>();
		List<String> employee_names = new ArrayList<>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_promotion_system?useSSL=false","root","");
			String sql = "SELECT t.*, e.first_name, e.last_name, d1.department_name as current_department, d2.department_name as new_department\r\n"
					+ "FROM transfers t\r\n"
					+ "JOIN employees e ON t.employee_id = e.employee_id\r\n"
					+ "JOIN departments d1 ON e.department_id = d1.department_id\r\n"
					+ "JOIN departments d2 ON t.new_department_id = d2.department_id\r\n"
					+ "WHERE t.status = 'InProcess' AND t.new_director_response = 1 AND t.director_response = 1;\r\n"
					+ "";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Transfer transfer=new Transfer();
				int transfer_id=rs.getInt("transfer_id");
				transfer.setTransfer_id(transfer_id);
				int department_id=rs.getInt("new_department_id");
				transfer.setNew_department_id(department_id);
				String new_department=rs.getString("new_department");
				transfer.setNew_department_name(new_department);
				transfer.setStatus(rs.getString("status"));
				String firstName=rs.getString("first_name");
				String lastName=rs.getString("last_name");
				String employee_name=firstName+" "+lastName;
				employee_names.add(employee_name);
				transfers.add(transfer);
				System.out.println(employee_name);
				System.out.println(transfer.getNew_department_id());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		request.setAttribute("transfers", transfers);
		request.setAttribute("employee_names", employee_names);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
