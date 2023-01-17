package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Promotion;
import model.Transfer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class managerPromotionRequestsServlet
 */
public class managerPromotionRequestsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public managerPromotionRequestsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int manager_id = (int) request.getSession().getAttribute("employee_id");
		List<Promotion> promotions = new ArrayList<>();
		List<String> employee_names = new ArrayList<>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_promotion_system?useSSL=false","root","");
			String sql = "SELECT p.*, e.first_name, e.last_name, e.job_category, e.rank \r\n"
					+ "FROM promotions p\r\n"
					+ "JOIN employees e ON p.employee_id=e.employee_id\r\n"
					+ "WHERE e.manager_id = ? AND p.status = 'InProcess' AND p.manager_response = 0;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, manager_id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Promotion promotion=new Promotion();
				int promotion_id=rs.getInt("promotion_id");
				promotion.setPromotion_id(promotion_id);
				String job_category=rs.getString("job_category");
				promotion.setCurrent_job_category(job_category);
				int rank=rs.getInt("rank");
				promotion.setCurrent_rank(rank);
				promotion.setStatus(rs.getString("status"));
				String firstName=rs.getString("first_name");
				String lastName=rs.getString("last_name");
				String employee_name=firstName+" "+lastName;
				employee_names.add(employee_name);
				promotions.add(promotion);
				System.out.println(employee_name);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		request.setAttribute("promotions", promotions);
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
