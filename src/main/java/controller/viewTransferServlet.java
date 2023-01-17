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
 * Servlet implementation class viewTransferServlet
 */
public class viewTransferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewTransferServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int employee_id = (int) request.getSession().getAttribute("employee_id");
		List<Transfer> transfers = getTransfers(employee_id);
		request.setAttribute("setted_transfers", transfers);
	}


	public List<Transfer> getTransfers(int employee_id) {
		List<Transfer> transfers = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_promotion_system?useSSL=false","root","");
			String sql = "SELECT transfers.status, departments.department_name, transfers.new_department_id\r\n"
					+ "FROM transfers\r\n"
					+ "INNER JOIN departments\r\n"
					+ "ON transfers.new_department_id = departments.department_id\r\n"
					+ "WHERE transfers.employee_id = ?\r\n"
					+ "";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, employee_id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Transfer transfer=new Transfer();
				int department_id=rs.getInt("new_department_id");
				transfer.setNew_department_id(department_id);
			    String departement_name=rs.getString("department_name");
				transfer.setNew_department_name(departement_name);
				transfer.setStatus(rs.getString("status"));
				transfers.add(transfer);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return transfers;
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
