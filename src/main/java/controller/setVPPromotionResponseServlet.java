package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Promotion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;

import enums.JobCategory;

/**
 * Servlet implementation class setVPPromotionResponseServlet
 */
public class setVPPromotionResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public setVPPromotionResponseServlet() {
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
		int vp_response;
		if (decision.equals("approve")) {
			status = "Approved";
			vp_response = 1;
		} else if (decision.equals("decline")) {
			status = "Declined";
			vp_response = -1;
		} else {
			status = "InProcess";
			vp_response = 0;
		}
		System.out.println(vp_response+" "+promotion_id);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_promotion_system?useSSL=false","root","");
			String sql = "UPDATE promotions SET vp_response = ?, status= ? WHERE promotion_id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, vp_response);
			statement.setString(2, status);
			statement.setInt(3, promotion_id);
			statement.executeUpdate();
			if(vp_response==1) {
				String sql2="SELECT p.*,e.employee_id , e.job_category, e.rank, e.monthly_salary, r.rate \r\n"
						+ "FROM employees e \r\n"
						+ "JOIN promotions p ON e.employee_id = p.employee_id \r\n"
						+ "JOIN reviews r ON e.employee_id = r.employee_id WHERE p.vp_response = 1 AND p.promotion_id=?;";
				statement = connection.prepareStatement(sql2);
				statement.setInt(1, promotion_id);
				ResultSet rs = statement.executeQuery();
				if (rs.next()) {
					String job_category=rs.getString("job_category");
					int rank=rs.getInt("rank");
					double monthly_salary=rs.getDouble("monthly_salary");
					int rate=rs.getInt("rate");
					double increase=0.1*rate/100*(monthly_salary*12);
					monthly_salary+=increase;
					//Performance_Increase = 0.1 * evaluation_percentage * yearly_salary
					if(rank<5) {
						rank++;
					}else if(rank==5) {
						rank=1;
						if (job_category.equals("ProfessionalEmployee")) {
							job_category="Manager";
						} else if (job_category.equals("Manager")) {
							job_category="Director";
						} else if (job_category.equals("Director")) {
							job_category="VicePresident";
						} 
					}
					String sql3="UPDATE employees SET rank = ?, job_category = ?, monthly_salary = ?, last_promotion=?\r\n"
							+ "WHERE employee_id = (SELECT employee_id FROM promotions WHERE promotion_id = ?); ";
					statement = connection.prepareStatement(sql3);
					statement.setInt(1, rank);
					statement.setString(2, job_category);
					statement.setDouble(3, increase);
					Calendar cal = Calendar.getInstance();
					java.util.Date utilDate = cal.getTime();
					java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

					statement.setDate(4, sqlDate);
					statement.setInt(5,promotion_id);

					
					statement.executeUpdate();
					System.out.println(rank+ " "+job_category+ " "+ increase+" "+sqlDate +" "+promotion_id);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		request.setAttribute("msg", "Your response saved :)");
		request.getRequestDispatcher("vpPromotionRequests.jsp").forward(request, response);

	}

}
