package com.dragon.blog.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dragon.blog.dao.impl.ArticleDaoImpl;

public class HandleDeleteArticleServlet extends HttpServlet {
/**
	 * @function: constructor
	 */
	public HandleDeleteArticleServlet() {
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
		String articleId = request.getParameter("articleId");
		String referPage = request.getParameter("referPage");
		
		if(articleId != null) {
			try {
				int articleIdInt = 0;
				articleIdInt = Integer.parseInt(articleId);
				
				ArticleDaoImpl articleDao = new ArticleDaoImpl();
				int result = articleDao.deleteArticleById(articleIdInt);
				if(result > 0) {
					request.getRequestDispatcher(referPage).forward(request, response);
				}
				else {
					request.setAttribute("errorMessage", "delete failed");
					request.getRequestDispatcher("index.html").forward(request, response);
				}
			}
			catch(Exception e) {
				request.setAttribute("errorMessage", "parameter is error");
				request.getRequestDispatcher("index.html").forward(request, response);
			}
		}
		else {
			request.setAttribute("errorMessage", "parameter is empty");
			request.getRequestDispatcher("index.html").forward(request, response);
		}
	}

	/**
	 * @function: destory
	 */
	public void destroy() {

	}
}
