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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class applyTransferServlet
 */
public class applyTransferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public applyTransferServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int employee_id = (int) request.getSession().getAttribute("employee_id");
		int department_id = (int) request.getSession().getAttribute("department_id");

		List<String> available_departments = getDepartements(employee_id,department_id);
		request.setAttribute("available_departments", available_departments);


	}

	public List<String> getDepartements(int employee_id, int department_id) {
		List<String> available_departments = new ArrayList<>();
		String departments="";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_promotion_system?useSSL=false","root","");
			String sql = "SELECT * FROM departments WHERE department_id != (SELECT department_id FROM employees WHERE employee_id = ?)"; 
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, employee_id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				departments+=","+(resultSet.getString("department_name"));
			}
			String [] available_departments_str=departments.split(",");
			for (String available_department_str : available_departments_str) {
				available_departments.add(available_department_str);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return available_departments;
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
