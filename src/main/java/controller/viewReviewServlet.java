package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Review;
import model.Transfer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class viewReviewServlet
 */
public class viewReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public viewReviewServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int employee_id = (int) request.getSession().getAttribute("employee_id");
		List<Review> reviews = getReviews(employee_id);
		int manager_id=(int)request.getSession().getAttribute("manager_id");
		String managerName=getManagerName(manager_id);
		request.setAttribute("reviews", reviews);
		request.setAttribute("manager_name",managerName);
	}

	public String getManagerName(int manager_id) {
		String manager_name="";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_promotion_system?useSSL=false","root","");
			String sql="SELECT  `first_name`, `last_name` FROM `employees` WHERE employee_id=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, manager_id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				manager_name+=rs.getString("first_name")+" ";
				manager_name+=rs.getString("last_name");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return manager_name;
	}


	public List<Review> getReviews(int employee_id) {
		List<Review> reviews = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_promotion_system?useSSL=false","root","");
			String sql = "SELECT manager_id, review_msg, review_year, rate, status FROM reviews WHERE employee_id=?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, employee_id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Review review=new Review();
				review.setReview_msg(rs.getString("review_msg"));
				review.setRate(rs.getInt("rate"));
				review.setStatus(rs.getString("status"));
				review.setReview_year(rs.getInt("review_year"));
				review.setManager_id(rs.getInt("manager_id"));
				reviews.add(review);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return reviews;
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
