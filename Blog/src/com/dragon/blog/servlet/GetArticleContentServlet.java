package com.dragon.blog.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.dragon.blog.dao.impl.ArticleDaoImpl;
import com.dragon.blog.dao.impl.CommentDaoImpl;
import com.dragon.blog.entity.Article;
import com.dragon.blog.entity.Comment;


public class GetArticleContentServlet extends HttpServlet {
	/**
	 * @function: constructor
	 */
	public GetArticleContentServlet() {
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
		try {
			int blogId = Integer.valueOf(request.getParameter("articleId"));
			HttpSession session = request.getSession();
			session.setAttribute("articleId", blogId);
			
			
			Article article = new ArticleDaoImpl().findArticleById(blogId);
			List<Comment> comments = new CommentDaoImpl().findCommentByArticleId(blogId);
			if (article == null) {
				request.setAttribute("articleMessage", "There isn't the article");
			} else {
				request.setAttribute("article", article);
				request.setAttribute("comments", comments);
			}
		} catch (NumberFormatException e) {
			request.setAttribute("articleMessage", "articleId isn't number");
		}
		request.getRequestDispatcher("article.jsp").forward(request, response);
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
