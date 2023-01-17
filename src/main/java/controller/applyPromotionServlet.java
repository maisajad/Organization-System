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

/**
 * Servlet implementation class apllyPromotionServlet
 */
@WebServlet(name="applyPromotion",value="/applyPromotion")
public class applyPromotionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public applyPromotionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("applyPromotion.jsp").include(request,response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg =request.getParameter("message");
		int employee_id = (int) request.getSession().getAttribute("employee_id");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_promotion_system?useSSL=false","root","");
			String sql="INSERT INTO `promotions`(`employee_id`,`status`, `employee_msg`) VALUES (?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, employee_id);
			statement.setString(2, "InProcess");
			statement.setString(3, msg);
			statement.executeUpdate();}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		request.setAttribute("msg", "You applied for a promotion :)");
		request.getRequestDispatcher("applyPromotion.jsp").forward(request, response);
	}
	
}
