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
 * Servlet implementation class directorViewAchievementsServlet
 */
public class directorViewAchievementsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public directorViewAchievementsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int director_id = (int) request.getSession().getAttribute("employee_id");
		List<String> achievements = new ArrayList<>();
		List<String> employee_names = new ArrayList<>();
		List<Integer> years = new ArrayList<>();

		int director_department_id=(int)request.getSession().getAttribute("department_id");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_promotion_system?useSSL=false","root","");
			String sql = "SELECT a.achievement_description, a.achievement_year, a.employee_id, e.first_name, e.last_name FROM achievements a JOIN employees e ON a.employee_id = e.employee_id WHERE e.department_id = ?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, director_department_id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String achievement=rs.getString("achievement_description");
				String name= rs.getString("first_name")+" "+rs.getString("last_name");
				int year=rs.getInt("achievement_year");
				achievements.add(achievement);
				employee_names.add(name);
				years.add(year);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		request.setAttribute("achievements", achievements);
		request.setAttribute("employee_names", employee_names);
		request.setAttribute("years", years);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
