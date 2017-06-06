package com.dragon.blog.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dragon.blog.dao.BaseDao;
import com.dragon.blog.dao.RSProcessor;
import com.dragon.blog.dao.TagDao;
import com.dragon.blog.entity.Tag;

public class TagDaoImpl extends BaseDao implements TagDao {

	@Override
	public int insert(Tag tag) {
		String sql = "insert into tag(tagName) values(?)";
		Object[] params = {tag.getTagName()};
		
		return this.executeInsertReturnId(sql, params);
		//return this.exceuteUpdate(sql, params);
	}

	@Override
	public Tag findTagById(int id) {
		String sql = "select * from tag where id = ?";
		Object[] params = {id};
		
		RSProcessor findTagByIdProcessor = new RSProcessor() {

			@Override
			public Object process(ResultSet rs) throws SQLException {
				Tag tag = null;
				
				if(rs != null) {
					if(rs.next()) {
						int id = rs.getInt("id");
						String tagName = rs.getString("tagName");
						tag = new Tag(id, tagName);
					}
				}
				return tag;
			}
		};

		return (Tag)this.executeQuery(findTagByIdProcessor, sql, params);
	}
	
	@Override
	public Tag findTagByName(String tagName) {
		String sql = "select * from tag where tagName = ?";
		Object[] params = {tagName};
		
		RSProcessor findTagByNameProcessor = new RSProcessor() {
			
			@Override
			public Object process(ResultSet rs) throws SQLException {
				Tag tag = null;
				if(rs != null) {
					if(rs.next()) {
						int id = rs.getInt("id");
						String tagName = rs.getString("tagName");
						tag = new Tag(id, tagName);
					}
				}
				return tag;
			}
		};
		return (Tag)this.executeQuery(findTagByNameProcessor, sql, params);
	}

	@Override
	public List<Tag> findAllTag() {
		String sql = "select * from tag";
		Object[] params = {};
		
		RSProcessor findAllTagProcessor = new RSProcessor() {

			@Override
			public Object process(ResultSet rs) throws SQLException {
				List<Tag> list = new ArrayList<Tag>();

				if(rs != null) {
					while(rs.next()) {
						int id = rs.getInt("id");
						String tagName = rs.getString("tagName");
						
						list.add(new Tag(id, tagName));
					}
				}

				return list;
			}
		};
		return (List<Tag>)this.executeQuery(findAllTagProcessor, sql, params);
	}

	@Override
	public int countTagByName(String tagName) {
		String sql = "select count(*) as num from tag where tagName = ?";
		Object[] params = {tagName};
		
		RSProcessor countTagByNameProcessor = new RSProcessor() {

			@Override
			public Object process(ResultSet rs) throws SQLException {
				int count = 0;
				
				if(rs != null) {
					if(rs.next()) {
						count = rs.getInt("num");
					}
				}

				return count;
			}
		};

		return (Integer)this.executeQuery(countTagByNameProcessor, sql, params);

	}

}
