package com.revature.servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.Employee;
import com.revature.beans.Login;
import com.revature.service.LoginService;



/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private LoginService ls = new LoginService();
	//private ShaneCorpDAO sc = new ShaneCorpDAOImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("projectOneLogin.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// check whether session exists, and create one if not
				// overloaded version takes a boolean parameter, if false returns null when there is none
				HttpSession session = request.getSession();
				//grab credentials from request
				Login login = new Login(request.getParameter("username"), request.getParameter("password"));
				//attempt to authenticate user
				Employee E = ls.isValidEmployee(login);
				if (E != null) {
					//set user information as session attributes (not request attributes!)
					//HttpSession session = request.getSession();
					session.setAttribute("userId", E.getId());
					session.setAttribute("manager", E.getReportsto());
					session.setAttribute("firstname", E.getFirstname());
					session.setAttribute("lastname", E.getLastname());
					session.setAttribute("email", E.getEmail());
					//session.setAttribute("problem", null);
					//redirect user to profile page if authenticated
					
					
					response.sendRedirect("profile");
				} else {
					//session.setAttribute("problem", "invalid credentials");
					//otherwise redirect to login page
					
					response.sendRedirect("login");
				}
			}
	


}
