package com.dragon.blog.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dragon.blog.dao.ArticleDao;
import com.dragon.blog.dao.BaseDao;
import com.dragon.blog.dao.RSProcessor;
import com.dragon.blog.entity.Article;
import com.dragon.blog.entity.Tag;

public class ArticleDaoImpl extends BaseDao implements ArticleDao {

	@Override
	public int insert(Article article) {
		String sql = "insert into article(title, content, date, tagId) values(?, ?, ?, ?)";
		Object[] params = {article.getTitle(), article.getContent(), 
				article.getDatetime(), article.getTag().getId()};
		return this.exceuteUpdate(sql, params);
	}

	@Override
	public Article findArticleById(int id) {
		String sql = "select * from article where id = ?";
		Object[] params = {id};

		RSProcessor findArticleByIdProcessor = new RSProcessor() {

			@Override
			public Object process(ResultSet rs) throws SQLException {
				Article article = null;
				
				if(rs != null) {
					if(rs.next()) {
						int id = rs.getInt("id");
						String title = rs.getString("title");
						String content = rs.getString("content");
						String datetime = rs.getString("date");
						int tagId = rs.getInt("tagId");
						TagDaoImpl tagDao = new TagDaoImpl();
						Tag tag = tagDao.findTagById(tagId);
						
						article = new Article(id, title, content, datetime, tag);
					}
				}
				return article;
			}
		};
		
		return (Article)this.executeQuery(findArticleByIdProcessor, sql, params);
	}
	
	@Override
	public List<Article> findLatestArticle(int number) {
		String sql = "select * from article order by date desc limit 0, ?";
		Object[] params = {number};
		
		RSProcessor findLatestArticleProcessor = new RSProcessor() {

			@Override
			public Object process(ResultSet rs) throws SQLException {
				List<Article> list = new ArrayList<Article>();
				
				if(rs != null) {
					while(rs.next()) {
						int id = rs.getInt("id");
						String title = rs.getString("title");
						String content = rs.getString("content");
						String datetime = rs.getString("date");
						int tagId = rs.getInt("tagId");
						
						TagDaoImpl tagDao = new TagDaoImpl();
						Tag tag = tagDao.findTagById(tagId);

						list.add(new Article(id, title, content, 
								datetime, tag));
					}
				}
				return list;
			}
		};
		return (List<Article>)this.executeQuery(findLatestArticleProcessor, sql, params);
	}

	@Override
	public int updateArticleById(Article article) {
		String sql = "update article set title = ?, content = ?, tagId = ? where id = ?";
		Object[] params = {article.getTitle(), article.getContent(), 
				article.getTag().getId(), article.getId()};

		return this.exceuteUpdate(sql, params);
	}

	@Override
	public int deleteArticleById(int id) {
		//delete comment
		String sql = "delete from comment where articleId = ?";
		Object[] params = {id};
		
		this.exceuteUpdate(sql, params);
		
		//delete article
		sql = "delete from article where id = ?";

		return this.exceuteUpdate(sql, params);
	}

	@Override
	public int countArticleByKey(String keyWord) {
		String sql = "select count(*) as num from article where title like ?";
		Object[] params = {"%" + keyWord + "%"};
		
		RSProcessor countArticleByKeyProcessor = new RSProcessor() {

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

		return (Integer)this.executeQuery(countArticleByKeyProcessor, sql, params);
	}

	@Override
	public List<Article> findArticleByKey(String keyWord, int currentPage) {
		//The number of page display
		int number = 10;
		
		String sql = "select * from article where title like ? limit ?, ?";
		Object[] params = {"%" + keyWord + "%", (currentPage - 1) * number, number};
		
		RSProcessor findArticleByKeyProcessor = new RSProcessor() {

			@Override
			public Object process(ResultSet rs) throws SQLException {
				List<Article> list = new ArrayList<Article>();
				
				if(rs != null) {
					while(rs.next()) {
						int id = rs.getInt("id");
						String title = rs.getString("title");
						String content = rs.getString("content");
						String datetime = rs.getString("date");
						int tagId = rs.getInt("tagId");

						TagDaoImpl tagDao = new TagDaoImpl();
						Tag tag = tagDao.findTagById(tagId);

						list.add(new Article(id, title, content, datetime, tag));
					}
				}

				return list;
			}
			
		};

		return (List<Article>)this.executeQuery(findArticleByKeyProcessor, sql, params);
	}

	@Override
	public int countArticleByTag(String tagId) {
		String sql = "select count(*) as num from article where tagId = ?";
		Object[] params = {tagId};
		
		RSProcessor countArticleByKeyProcessor = new RSProcessor() {

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

		return (Integer)this.executeQuery(countArticleByKeyProcessor, sql, params);
	}

	@Override
	public List<Article> findArticleByTag(String tagId, int currentPage) {
		//The number of page display
		int number = 10;
		
		String sql = "select * from article where tagId = ? limit ?, ?";
		Object[] params = {tagId, (currentPage - 1) * number, number};
		
		RSProcessor findArticleByTagProcessor = new RSProcessor() {

			@Override
			public Object process(ResultSet rs) throws SQLException {
				List<Article> list = new ArrayList<Article>();
				
				if(rs != null) {
					while(rs.next()) {
						int id = rs.getInt("id");
						String title = rs.getString("title");
						String content = rs.getString("content");
						String datetime = rs.getString("date");
						int tagId = rs.getInt("tagId");

						TagDaoImpl tagDao = new TagDaoImpl();
						Tag tag = tagDao.findTagById(tagId);

						list.add(new Article(id, title, content, datetime, tag));
					}
				}

				return list;
			}
			
		};

		return (List<Article>)this.executeQuery(findArticleByTagProcessor, sql, params);
	}

}
