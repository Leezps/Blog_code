package com.dragon.blog.dao;

import com.dragon.blog.entity.User;

public interface UserDao {
	//
	public User findUserByNamePass(String username, String password);
	
	//
	public User findUserBaseInfo(String username);
	
	//
	public int updatePageView(String username);
}
