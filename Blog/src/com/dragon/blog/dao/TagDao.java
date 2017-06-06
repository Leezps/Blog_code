package com.dragon.blog.dao;

import java.util.List;

import com.dragon.blog.entity.Tag;

public interface TagDao {
	public int insert(Tag tag);
	public Tag findTagById(int id);
	public Tag findTagByName(String tagName);
	public int countTagByName(String tagName);
	public List<Tag> findAllTag();
}
