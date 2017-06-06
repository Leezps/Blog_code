package com.dragon.blog.entity;

/**
 * @for: This is entity class, describe Tag
 * @version: 1.0
 */

public class Tag {
	/**
	 * define variable
	 */
	private int id;
	private String tagName;

	
	/**
	 * function constructor
	 */
	public Tag() {
		
	}
	
	public Tag(String tagName) {
		this.tagName = tagName;
	}
	
	public Tag(int id, String tagName) {
		this.id = id;
		this.tagName = tagName;
	}
	
	
	/**
	 * @for: Getter
	 * @return
	 */
	public int getId() {
		return id;
	}

	public String getTagName() {
		return tagName;
	}

	
}
