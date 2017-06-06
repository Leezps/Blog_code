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
import com.dragon.blog.entity.Article;

public class GetSearchServlet extends HttpServlet {
/**
	 * @function: constructor
	 */
	public GetSearchServlet() {
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
		
		String keyWord = request.getParameter("searchText");
		String currentPage = request.getParameter("currentPage");
		String isTagSearch = request.getParameter("isTagSearch");
		
		//judge parameters is null or not
		if(keyWord != null || isTagSearch != null || currentPage != null) {
			
			int currentPageInt = 0;
			
			//if currentPage is not number, currentPageInt = 1
			try {
				currentPageInt = Integer.parseInt(currentPage);
			}
			catch(Exception e) {
				currentPageInt = 1;
			}
			
			//common search function
			if(isTagSearch.equals("0")) {
			//
				ArticleDaoImpl articleDao = new ArticleDaoImpl();
			
				int totalCount = articleDao.countArticleByKey(keyWord);
				
				//Ensuring the right currentPage
				if(currentPageInt < 1) {
					currentPageInt = 1;
				}
				if(currentPageInt > (totalCount - 1) / 10 + 1) {
					currentPageInt = (totalCount - 1) / 10 + 1;
				}
				
				List<Article> articleList = articleDao.findArticleByKey(keyWord, currentPageInt);

				request.setAttribute("searchText", keyWord);
				request.setAttribute("isTagSearch", 0);
				request.setAttribute("currentPage", currentPageInt);
				request.setAttribute("totalCount", totalCount);
				request.setAttribute("articleList", articleList);
				request.getRequestDispatcher("search.jsp").forward(request, response);
			}
			//search by tagId
			else if(isTagSearch.equals("1")) {
				ArticleDaoImpl articleDao = new ArticleDaoImpl();
				int totalCount = articleDao.countArticleByTag(keyWord);
				
				//Ensuring the right currentPage
				if(currentPageInt < 1) {
					currentPageInt = 1;
				}
				if(currentPageInt > (totalCount - 1) / 10 + 1) {
					currentPageInt = (totalCount - 1) / 10 + 1;
				}
				List<Article> articleList = articleDao.findArticleByTag(keyWord, currentPageInt);

				request.setAttribute("searchText", keyWord);
				request.setAttribute("isTagSearch", 1);
				request.setAttribute("currentPage", currentPageInt);
				request.setAttribute("totalCount", totalCount);
				request.setAttribute("articleList", articleList);
				request.getRequestDispatcher("search.jsp").forward(request, response);
			}
			else {
				request.setAttribute("errorMessage", "Parameters is error...");
				request.getRequestDispatcher("search.jsp").forward(request, response);
			}
		}
		else {
			request.setAttribute("errorMessage", "Please input something...");
			request.getRequestDispatcher("search.jsp").forward(request, response);
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
