package com.dragon.blog.dao;

import java.util.List;

import com.dragon.blog.entity.Article;

public interface ArticleDao {
	
	//
	public int insert(Article article);
	
	//
	public Article findArticleById(int id);
	
	//
	public List<Article> findLatestArticle(int number);
	
	//
	public int countArticleByKey(String keyWord);
	
	//
	public List<Article> findArticleByKey(String keyWord, int currentPage);
	
	//
	public int countArticleByTag(String tagId);
	
	//
	public List<Article> findArticleByTag(String tagId, int currentPage);
	
	//
	public int updateArticleById(Article article);
	
	//
	public int deleteArticleById(int id);
}
