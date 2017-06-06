package com.dragon.blog.business;

import java.util.ArrayList;
import java.util.List;

import com.dragon.blog.dao.impl.ArticleDaoImpl;
import com.dragon.blog.dao.impl.TagDaoImpl;
import com.dragon.blog.dao.impl.UserDaoImpl;
import com.dragon.blog.entity.*;

/**
 * @for: handle get information business from servlet
 * @version: 1.0
 * @time: 2016.12.24
 *
 */

public class GetInformation {
	/**
	 * [getSystemInfo description]
	 * @param  [type]                    [description]
	 * @return [SystemInfo]  systemInfo  [description]
	 */
	public SystemInfo getSystemInfo() {
		
		UserDaoImpl userDao = new UserDaoImpl();
		
		User user = userDao.findUserBaseInfo("admin");
		SystemInfo systemInfo = new SystemInfo(user.getBlogName(), user.getPageView());
		
		return systemInfo;
	}
	
	/**
	 * [getPersonInfo description]
	 * @param  [type]       [description]
	 * @return [User]  user [description]
	 */
	public User getPersonInfo() {
		User user = new User();
		
		return user;
	}
	
	/**
	 * [getArticleSimpleInfo description]
	 * @param  [type]                [description]
	 * @return [List<Article>] list	 [description]
	 */
	public List<Article> getArticleSimpleInfo() {
		List<Article> list = new ArrayList<Article>();
		
		return list;
	}
	
	/**
	 * [getArticleInfo description]
	 * @param  [type]            [description]
	 * @return [Article] article [description]
	 */
	public Article getArticleInfo() {
		Article article = new Article();
		
		return article;
	}
	
	/**
	 * [getSearchResult description]
	 * @param  [type]               [description]
	 * @return [List<Article>] list [description]
	 */
	public List<Article> getSearchResult() {
		List<Article> articleList = null; 

		return articleList;
	}

	/**
	 * [getTagSearchResult description]
	 * @param  [type]               [description]
	 * @return [List<Article>] list [description]
	 */
	public List<Article> getTagSearchResult() {
		List<Article> list = new ArrayList<Article>();
		
		return list;
	}

	/**
	 * [getEditArticle description]
	 * @param  [type]            [description]
	 * @return [Article] article [description]
	 */
	public Article getEditArticle(int id) {
		Article article = new Article();
		ArticleDaoImpl articleDao = new ArticleDaoImpl();
		article = articleDao.findArticleById(id);
		return article;
	}
	/**
	 * [isNewTag description]
	 * @param tag	[description]
	 * @return boolean		[description]
	 */
	public boolean isNewTag(String tag) {
		TagDaoImpl tagDao = new TagDaoImpl();
		if(tagDao.countTagByName(tag) > 0) {
			return false;
		} else {
			return true;
		}
	}
	/**
	 * [getTagByName description]
	 * @param tagName [description]
	 * @return [Tag] tag [description]	
	 */
	public Tag getTagByName(String tagName) {
		TagDaoImpl tagDao = new TagDaoImpl();
		return tagDao.findTagByName(tagName);
	}
}






