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
import com.dragon.blog.dao.impl.TagDaoImpl;
import com.dragon.blog.entity.Article;
import com.dragon.blog.entity.Tag;


public class GetEditArticleServlet extends HttpServlet {
	/**
	 * @function: constructor
	 */
	public GetEditArticleServlet() {
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
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		int id = Integer.valueOf(request.getParameter("articleId"));
		TagDaoImpl tdl = new TagDaoImpl();
		List<Tag> tList = tdl.findAllTag();
		if(id != -1) {//更新
			ArticleDaoImpl aDao = new ArticleDaoImpl();
			Article art = new Article();
			art = aDao.findArticleById(id);
			request.setAttribute("article", art);
			request.setAttribute("articleId", id);
			request.setAttribute("tList", tList);
			request.getRequestDispatcher("editArticle.jsp").forward(request, response);
		} else {//插入
			request.setAttribute("article", null);
			request.setAttribute("articleId", -1);
			request.setAttribute("tList", tList);
			request.getRequestDispatcher("editArticle.jsp").forward(request, response);
		}
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
