package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import enums.JobCategory;

/**
 * Servlet implementation class goBackServlet
 */
public class goBackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public goBackServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		JobCategory jobCategory=(JobCategory) session.getAttribute("job_category");				
		if (jobCategory.equals(JobCategory.ProfessionalEmployee)) {
			response.sendRedirect("employeeHome.jsp");
		} else if (jobCategory.equals(JobCategory.Manager)) {
			response.sendRedirect("managerHome.jsp");
		} else if (jobCategory.equals(JobCategory.Director)) {
			response.sendRedirect("directorHome.jsp");
		} else if (jobCategory.equals(JobCategory.VicePresident)) {
			response.sendRedirect("vicePresidentHome.jsp");
		} else if (jobCategory.equals(jobCategory.President)) {
			response.sendRedirect("presidentHome.jsp");				
		} 
		 }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
