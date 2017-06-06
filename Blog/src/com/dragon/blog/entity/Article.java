package com.dragon.blog.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @for: This is entity class, describe Article
 * @version: 1.0
 */

public class Article {
	/**
	 * define variable
	 */
	private int id;
	private String title;
	private String content;
	private String datetime;
	private Tag tag;
	
	/**
	 * for: constructor
	 */
	public Article() {
		
	}
	
	public Article(String title, String content, Tag tag) {
		this.title = title;
		this.content = content;
		
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.datetime = simpleDateFormat.format(date);

		this.tag = tag;
	}
	
	public Article(int id, String title, String content,
			String date, Tag tag) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.datetime = date;
		this.tag = tag;
	}

	/**
	 * @for: Getter
	 * @return
	 */
	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getDatetime() {
		return datetime;
	}

	public Tag getTag() {
		return tag;
	}
	
	public String getSimpleContent() {
		int simpleLength = 150;
		if(this.content.length() > simpleLength) {
			String simpleContent = this.content.substring(0, simpleLength);
			simpleContent = simpleContent + "......";
			return simpleContent;
		}
		else {
			return this.content;
		}
	}


	/**
	 * @for: Setter
	 * @return
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public void setTag(Tag tag) {
		this.tag = tag;
	}

}
