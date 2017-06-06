package com.dragon.blog.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dragon.blog.dao.BaseDao;
import com.dragon.blog.dao.CommentDao;
import com.dragon.blog.dao.RSProcessor;
import com.dragon.blog.entity.Comment;

public class CommentDaoImpl extends BaseDao implements CommentDao {

	@Override
	public int insert(Comment comment) {
		String sql = "insert into comment(email, username, content, date, articleId) "
				+ "values(?, ?, ?, ?, ?)";
		Object[] params = {comment.getEmail(), comment.getUsername(), 
				comment.getContent(), comment.getDatetime(), comment.getArticleId()};

		return this.exceuteUpdate(sql, params);
	}

	@Override
	public int deleteById(int id) {
		String sql = "delete from comment where id = ?";
		Object[] params = {id};

		return this.exceuteUpdate(sql, params);
	}

	@Override
	public List<Comment> findCommentByArticleId(int id) {
		String sql = "select * from comment where articleId = ?";
		Object[] params = {id};
		
		RSProcessor findCommentByArticleIdProcessor = new RSProcessor() {

			@Override
			public Object process(ResultSet rs) throws SQLException {
				List<Comment> list = new ArrayList<Comment>();
				
				if(rs != null) {
					while(rs.next()) {
						int id = rs.getInt("id");
						String email = rs.getString("email");
						String username = rs.getString("username");
						String content = rs.getString("content");
						String datetime = rs.getString("date");
						int articleId = rs.getInt("articleId");
						
						list.add(new Comment(id, email, username, 
								content, datetime, articleId));
					}
				}
				return list;
			}
		};

		return (List<Comment>)this.executeQuery(findCommentByArticleIdProcessor, sql, params);
	}

}
