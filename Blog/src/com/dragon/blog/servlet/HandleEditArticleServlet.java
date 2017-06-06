package com.dragon.blog.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dragon.blog.business.GetInformation;
import com.dragon.blog.business.HandleBusiness;
import com.dragon.blog.entity.Article;
import com.dragon.blog.entity.Tag;

public class HandleEditArticleServlet extends HttpServlet {
	/**
	 * @function: constructor
	 */
	public HandleEditArticleServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// debug
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		int flag = 0; // update or insert
		String title = request.getParameter("title");
		String tag = request.getParameter("tag");
		String content = request.getParameter("editor");
		int id = Integer.valueOf(request.getParameter("article-id"));
		int tagId = -1;
		Tag t = null;
		GetInformation getInfo = new GetInformation();
		HandleBusiness handleBiz = new HandleBusiness();
		if (id == -1) { // insert
			if (getInfo.isNewTag(tag)) {// check tag
				t = new Tag(tag);
				tagId = handleBiz.handleInsertTag(t);// insert tag get id
				t = new Tag(tagId, tag);
			} else {
				t = getInfo.getTagByName(tag);
			}
			Article article = new Article(title, content, t);
			if (handleBiz.handleEditArticle(article, flag)) {
				// success
				//out.println("insert success");
				request.getRequestDispatcher("index.html").forward(request, response);
			} else {
				// failed
				out.println("insert failed");
			//	request.getRequestDispatcher("index.html").forward(request, response);
			}
		} else { // update
			flag = 1;
			Article article = null;
			article = getInfo.getEditArticle(id);
			if (getInfo.isNewTag(tag)) {// check tag
				t = new Tag(tag);
				tagId = handleBiz.handleInsertTag(t);// insert tag get id
				t = new Tag(tagId, tag);
			} else {
				t = getInfo.getTagByName(tag);
			}
			article.setContent(content);
			article.setTag(t);
			article.setTitle(title);
			if (handleBiz.handleEditArticle(article, flag)) {
				// success
//				out.println("update success");
				request.getRequestDispatcher("index.html").forward(request, response);
			} else {
				// failed
				out.println("update failed");
			}
		}

	}

	/**
	 * @function: destory
	 */
	public void destroy() {

	}
}
