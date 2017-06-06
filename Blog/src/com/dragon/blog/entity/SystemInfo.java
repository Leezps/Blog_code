package com.dragon.blog.entity;

/**
 * @for: This is entity class, describe System Information
 * @version: 1.0
 */

public class SystemInfo {
	/**
	 * define variable
	 */
	private String blogName;
	private int pageView = 0;
	 
	 
	/**
	 * for: constructor
	 */
	public SystemInfo() {
		
	}
	
	public SystemInfo(String blogName, int pageView) {
		this.blogName = blogName;
		this.pageView = pageView;
	}
	
	
	/**
	 * @for: Getter
	 * @return
	 */
	public String getBlogName() {
		return blogName;
	}

	public int getPageView() {
		return pageView;
	}
	 
}
