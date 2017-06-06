package com.dragon.blog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dragon.blog.dao.impl.ArticleDaoImpl;
import com.dragon.blog.dao.impl.UserDaoImpl;
import com.dragon.blog.entity.Article;
import com.dragon.blog.entity.SystemInfo;
import com.dragon.blog.entity.User;

public class GetIndexServlet extends HttpServlet {
/**
	 * @function: constructor
	 */
	public GetIndexServlet() {
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
		//Get systemInfo
		UserDaoImpl userDao = new UserDaoImpl();
		User user = userDao.findUserBaseInfo("admin");
		SystemInfo systemInfo = new SystemInfo(user.getBlogName(), user.getPageView());
		
		//Get simpleinfo of article
		ArticleDaoImpl articleDao = new ArticleDaoImpl();
		List<Article> articleList = articleDao.findLatestArticle(10);
		
		request.setAttribute("systemInfo", systemInfo);
		request.setAttribute("articleList", articleList);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
	/**
	 * @function: doPost
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * @function: destory
	 */
	public void destroy() {

	}
}
