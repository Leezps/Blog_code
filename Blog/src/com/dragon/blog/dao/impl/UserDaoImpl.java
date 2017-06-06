package com.dragon.blog.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dragon.blog.dao.BaseDao;
import com.dragon.blog.dao.RSProcessor;
import com.dragon.blog.dao.UserDao;
import com.dragon.blog.entity.User;

public class UserDaoImpl extends BaseDao implements UserDao {

	@Override
	public User findUserByNamePass(String username, String password) {
		String sql = "select * from users where username = ? and password = ?";
		Object[] params = {username, password};
		
		RSProcessor findUserByNamePassProcessor = new RSProcessor() {

			@Override
			public Object process(ResultSet rs) throws SQLException {
				User user = null;
				
				if(rs != null) {
					if(rs.next()) {
						int id = rs.getInt("id");
						String username = rs.getString("username");
						String password = rs.getString("password");
						String showName = rs.getString("showName");
						String iconPath = rs.getString("iconPath");
						String gitHubUrl = rs.getString("gitHubUrl");
						String blogName = rs.getString("blogName");
						int pageView = rs.getInt("pageView");
						
						user = new User(id, username, password, showName, iconPath, gitHubUrl,
								blogName, pageView);
					}
				}
				return user;
			}
		};
		
		return (User)this.executeQuery(findUserByNamePassProcessor, sql, params);
	}

	@Override
	public User findUserBaseInfo(String username) {
		String sql = "select * from users where username = ? ";
		Object[] params = {username};
		
		RSProcessor findUserBaseInfoProcessor = new RSProcessor() {

			@Override
			public Object process(ResultSet rs) throws SQLException {
				User user = null;
				
				if(rs != null) {
					if(rs.next()) {
						int id = rs.getInt("id");
						String username = rs.getString("username");
						String password = rs.getString("password");
						String showName = rs.getString("showName");
						String iconPath = rs.getString("iconPath");
						String gitHubUrl = rs.getString("gitHubUrl");
						String blogName = rs.getString("blogName");
						int pageView = rs.getInt("pageView");
						
						user = new User(id, username, password, showName, iconPath, gitHubUrl,
								blogName, pageView);
					}
				}
				return user;
			}
		};
		
		return (User)this.executeQuery(findUserBaseInfoProcessor, sql, params);
	}

	@Override
	public int updatePageView(String username) {
		String sql = "update users set pageView = pageView + 1 where username = ?";
		Object[] params = {username};
		
		return this.exceuteUpdate(sql, params);
	}

}
