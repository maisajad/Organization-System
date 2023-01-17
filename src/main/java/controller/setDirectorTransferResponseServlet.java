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
 * Servlet implementation class setDirectorTransferResponseServlet
 */
public class setDirectorTransferResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public setDirectorTransferResponseServlet() {
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
		System.out.println(decision);
		int director_response;
		if (decision.equals("Approve")) {
			System.out.println("Director approve");
		    director_response = 1;
		} else if (decision.equals("Decline")) {
		    director_response = -1;
		} else {
		    director_response = 0;
		}
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_promotion_system?useSSL=false","root","");
			String sql = "UPDATE transfers ";
			int director_department_id=(int)request.getSession().getAttribute("department_id");
			int new_department_id=Integer.parseInt(request.getParameter("department_id"));
			System.out.println("Test servlet "+director_department_id+" "+ new_department_id);
			boolean current_user_is_new_director= (director_department_id==new_department_id);
			if (current_user_is_new_director) {
			    sql += "SET new_director_response = ? WHERE transfer_id = ?";
			} else {
			    sql += "SET director_response = ? WHERE transfer_id = ?";
			}
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, director_response);
			statement.setInt(2, transfer_id);
			statement.execute();
			System.out.println(sql);
			System.out.println(director_response);
			System.out.println(transfer_id);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		request.setAttribute("msg", "Your response saved :)");
		request.getRequestDispatcher("directorTransferRequests.jsp").forward(request, response);


	}

}
