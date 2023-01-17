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
 * Servlet implementation class viewAllTransferServlet
 */
public class viewAllTransferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewAllTransferServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Transfer> transfers = new ArrayList<>();
		List<String> employee_names = new ArrayList<>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_promotion_system?useSSL=false","root","");
			String sql = "SELECT employees.first_name, employees.last_name, transfers.*, departments.department_name FROM employees JOIN transfers ON employees.employee_id = transfers.employee_id JOIN departments ON transfers.new_department_id = departments.department_id;";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Transfer transfer=new Transfer();
				int transfer_id=rs.getInt("transfer_id");
				transfer.setTransfer_id(transfer_id);
				int department_id=rs.getInt("new_department_id");
				transfer.setNew_department_id(department_id);
				String new_department=rs.getString("department_name");
				transfer.setNew_department_name(new_department);
				transfer.setStatus(rs.getString("status"));
				String firstName=rs.getString("first_name");
				String lastName=rs.getString("last_name");
				String employee_name=firstName+" "+lastName;
				employee_names.add(employee_name);
				transfers.add(transfer);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		request.setAttribute("transfers", transfers);
		request.setAttribute("employee_names", employee_names);	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
