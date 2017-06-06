package com.dragon.blog.business;

import java.math.BigInteger;
import java.security.MessageDigest;

import com.dragon.blog.dao.impl.ArticleDaoImpl;
import com.dragon.blog.dao.impl.TagDaoImpl;
import com.dragon.blog.dao.impl.UserDaoImpl;
import com.dragon.blog.entity.*;

/**
 * @for: handle business from servlet
 * @version: 1.0
 * @time: 2016.12.24
 *
 */

public class HandleBusiness {
	/**
	 * [handleLogin description]
	 * 
	 * @param [String]
	 *            username [description]
	 * @param [String]
	 *            password [description]
	 * @return [User] user [description]
	 */
	public User handleLogin(String username, String password) {
		String passwordMd5 = null;

		try {
			// 生成一个MD5加密计算摘要
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 计算md5函数
			md.update(password.getBytes());
			// digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
			// BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
			passwordMd5 = new BigInteger(1, md.digest()).toString(16);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("HandleBusiness.hanleLogin, exception");
		}

		UserDaoImpl userDao = new UserDaoImpl();
		User user = userDao.findUserByNamePass(username, passwordMd5);

		return user;
	}

	/**
	 * [handleEditArticle description]
	 * 
	 * @param [Article]
	 *            article [description]
	 * @return [boolean] result [description]
	 */
	public boolean handleEditArticle(Article article, int flag) {
		boolean result = false;
		ArticleDaoImpl articleDao = new ArticleDaoImpl();
		if (flag == 0) {
			articleDao.insert(article);
			result = true;
		} else if (flag == 1) {
			articleDao.updateArticleById(article);
			result = true;
		}
		return result;
	}

	/**
	 * [handleDeleteArticle description]
	 * 
	 * @param [Article]
	 *            article [description]
	 * @return [boolean] result [description]
	 */
	public boolean handleDeleteArticle(Article article) {
		boolean result = false;

		return result;
	}

	/**
	 * [handleReComment description]
	 * 
	 * @param [Comment]
	 *            comment [description]
	 * @return [boolean] result [description]
	 */
	public boolean handleReComment(Comment comment) {
		boolean result = false;

		return result;
	}

	/**
	 * [handleDelComment description]
	 * 
	 * @param [Comment]
	 *            comment [description]
	 * @return [boolean] result [description]
	 */
	public boolean handleDelComment(Comment comment) {
		boolean result = false;

		return result;
	}

	/**
	 * [handleInsertTag description]
	 * 
	 * @param [Tag]
	 *            tag [description]
	 * @return Integer tagId [description]
	 */
	public int handleInsertTag(Tag tag) {
		int result = -1;
		TagDaoImpl tagDao = new TagDaoImpl();
		result = tagDao.insert(tag);
		return result;
	}
}
