package com.dragon.blog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dragon.blog.dao.impl.TagDaoImpl;
import com.dragon.blog.entity.Tag;

public class GetAllTagNameServlet extends HttpServlet {
	/**
	 * @function: constructor
	 */
	public GetAllTagNameServlet() {
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
		TagDaoImpl tagDao = new TagDaoImpl();
		
		List<Tag> tagList = tagDao.findAllTag();

		request.setAttribute("tagList", tagList);
		request.getRequestDispatcher("showTag.jsp").forward(request, response);
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
