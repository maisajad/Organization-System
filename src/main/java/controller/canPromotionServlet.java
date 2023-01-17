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
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;

/**
 * Servlet implementation class canPromotionServlet
 */
public class canPromotionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public canPromotionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int employee_id = (int) request.getSession().getAttribute("employee_id");
		boolean canApply=false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_promotion_system?useSSL=false","root","");
			String sql = "SELECT last_promotion, (SELECT rate FROM reviews WHERE employee_id = employees.employee_id) as rate FROM employees WHERE employee_id=?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, employee_id);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				Date date=resultSet.getDate("last_promotion");
				int rate =resultSet.getInt("rate");
				if(rate>80) {
					if(date!=null) {
						Calendar calendar  = Calendar.getInstance();
						calendar.add(Calendar.YEAR, -3);
						Date d1 = calendar.getTime();
						if(date.before(d1)) {
							canApply=true;
						}

					}else {
						canApply=true;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		request.setAttribute("canApply", canApply);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
