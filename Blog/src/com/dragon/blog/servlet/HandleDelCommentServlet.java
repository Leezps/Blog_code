package com.dragon.blog.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dragon.blog.dao.impl.CommentDaoImpl;

public class HandleDelCommentServlet extends HttpServlet {
/**
	 * @function: constructor
	 */
	public HandleDelCommentServlet() {
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
		doPost(request, response);
	}
	
	/**
	 * @function: doPost
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commentId = request.getParameter("commentId");
		String referPage = request.getParameter("referPage");
		
		if(commentId != null) {
			try {
				int commentIdInt = 0;
				commentIdInt = Integer.parseInt(commentId);
				
				CommentDaoImpl commentDao = new CommentDaoImpl();
				int result = commentDao.deleteById(commentIdInt);
				if(result > 0) {
					request.getRequestDispatcher(referPage).forward(request, response);
				}
				else {
					request.setAttribute("errorMessage", "delete failed");
					request.getRequestDispatcher("GetIndexServlet.action").forward(request, response);
				}
			}
			catch(Exception e) {
				request.setAttribute("errorMessage", "parameter is error");
				request.getRequestDispatcher("GetIndexServlet.action").forward(request, response);
			}
		}
		else {
			request.setAttribute("errorMessage", "parameter is empty");
			request.getRequestDispatcher("GetIndexServlet.action").forward(request, response);
		}
	}

	/**
	 * @function: destory
	 */
	public void destroy() {

	}
}
