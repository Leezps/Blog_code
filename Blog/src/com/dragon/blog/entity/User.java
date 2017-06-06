package com.dragon.blog.entity;

/**
 * @for: This is entity class, describe User
 * @version: 1.0
 */

public class User {
	/**
	 * define variable
	 */
	private int id;
	private String username;
	private String password;
	private String showName;
	private String iconPath;
	private String gitHubUrl;
	private String blogName;
	private int pageView;		//

	/**
	 * for: constructor
	 */
	public User() {
		
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public User(int id, String username, String password, String showName,
			String iconPath, String gitHubUrl, String blogName, int pageView) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.showName = showName;
		this.iconPath = iconPath;
		this.gitHubUrl = gitHubUrl;
		this.blogName = blogName;
		this.pageView = pageView;
	}


	/**
	 * @for: Getter
	 * @return
	 */

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getShowName() {
		return showName;
	}

	public String getIconPath() {
		return iconPath;
	}

	public String getGitHubUrl() {
		return gitHubUrl;
	}

	public String getBlogName() {
		return blogName;
	}

	public int getPageView() {
		return pageView;
	}
	
}
