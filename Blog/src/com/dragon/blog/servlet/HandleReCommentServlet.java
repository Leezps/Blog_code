package com.dragon.blog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jdt.internal.compiler.apt.util.Util;

import com.dragon.blog.dao.impl.CommentDaoImpl;
import com.dragon.blog.entity.Comment;


public class HandleReCommentServlet extends HttpServlet {
/**
	 * @function: constructor
	 */
	public HandleReCommentServlet() {
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
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String emRegEx = "^[0-9a-zA-Z_]{1,40}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,3}$";
		Pattern pattern = Pattern.compile(emRegEx);
		HttpSession session = request.getSession();
		
		String touristName = new String(request.getParameter("touristName"));//.getBytes("ISO-8859-1"),"utf-8");
		String email = new String(request.getParameter("email"));//.getBytes("ISO-8859-1"),"utf-8");
		String comment = new String(request.getParameter("review"));//.getBytes("ISO-8859-1"),"utf-8");
		int articleId = (int) session.getAttribute("articleId");
		
		if (touristName == null||touristName.equals("")) {
			out.print("{\"touristStatus\":1,\"message\":\"touristName is null!\"}");
		} else if (touristName.length() > 50) {
			out.print("{\"touristStatus\":1,\"message\":\"touristName's length is over long!\"}");
		} else if (!pattern.matcher(email).matches()) {
			out.print("{\"touristStatus\":1,\"message\":\"email is wrong!\"}");
		} else if(comment == null||comment.equals("")) {
			out.print("{\"touristStatus\":1,\"message\":\"comment is null!\"}");
		} else if(articleId <= 0) {
			out.print("{\"touristStatus\":1,\"message\":\"article is null!\"}");
		} else {
			java.util.Date date = new java.util.Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = simpleDateFormat.format(date);
			
			Comment cmt = new Comment(email, touristName, comment, articleId);
			new CommentDaoImpl().insert(cmt);
			out.print("{\"touristStatus\":0,\"touristName\":\""+touristName+"\",\"time\":\""+time+"\",\"comment\":\""+comment+"\"}");
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
