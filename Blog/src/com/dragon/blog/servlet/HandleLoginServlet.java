package com.dragon.blog.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dragon.blog.business.HandleBusiness;
import com.dragon.blog.entity.User;

public class HandleLoginServlet extends HttpServlet {
/**
	 * @function: constructor
	 */
	public HandleLoginServlet() {
		super();
	}
	
	/**
	 * @function: init
	 */
	public void init(ServletConfig config) throws ServletException {

	}

	/**
	 * @function: doGet
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		//super.doGet(request, response);
		this.doPost(request, response);
	}
	
	/**
	 * @function: doPost
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String referPage = request.getParameter("referPage");
		
		if(username != null && password != null) {
			HandleBusiness handle = new HandleBusiness();
			User user = handle.handleLogin(username, password);
			
			if(user != null) {
				session.setAttribute("Login", user);
				request.setAttribute("loginMessage", "Login Success");
				request.getRequestDispatcher("index.html").forward(request, response);
			}
			else {
				request.setAttribute("loginMessage", "username or password error");
				request.getRequestDispatcher(referPage).forward(request, response);
			}
		}
		else {
			request.setAttribute("loginMessage", "username or password is empty");
			request.getRequestDispatcher(referPage).forward(request, response);
		}
	}

	/**
	 * @function: destory
	 */
	public void destroy() {

	}
}
