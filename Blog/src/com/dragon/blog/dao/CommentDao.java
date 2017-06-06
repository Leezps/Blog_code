package com.dragon.blog.dao;

import java.util.List;

import com.dragon.blog.entity.Comment;

public interface CommentDao {
	public int insert(Comment comment);
	public int deleteById(int id);
	public List<Comment> findCommentByArticleId(int id);
}
