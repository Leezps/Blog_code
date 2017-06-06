package com.dragon.blog.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Comment {
	/**
	 * define variable
	 */
	private int id;
	private String email;
	private String username;
	private String content;
	private String datetime;
	private int articleId;
	
	
	/**
	 * function constructor
	 */
	public Comment() {
		
	}
	
	public Comment(String email, String username, String content, int articleId) {
		this.email = email;
		this.username = username;
		this.content = content;
		
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.datetime = simpleDateFormat.format(date);

		this.articleId = articleId;
	}
	
	public Comment(int id, String email, String username,
			String content, String date, int articleId) {
		this.id = id;
		this.email = email;
		this.username = username;
		this.content = content;
		this.datetime = date;
		this.articleId = articleId;
	}
	
	
	/**
	 * @for: Getter
	 * @return
	 */

	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getUsername() {
		return username;
	}

	public String getContent() {
		return content;
	}

	public String getDatetime() {
		return datetime;
	}
	
	public int getArticleId() {
		return articleId;
	}
	
}
