package com.dragon.blog.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Database connect and close
 */
public abstract class BaseDao {
	private static String driver = 
		"com.mysql.jdbc.Driver";
	private static String url = 
		"jdbc:mysql://localhost:3306/blog?useUnicode=true&characterEncoding=utf-8";// 
	private static String user = "root";
	private static String password = "root";
	
	protected Connection conn;
	protected PreparedStatement pstmt;
	protected ResultSet rs;
	
	/**
	 * Get object of database connect
	 */
	public Connection getConnection() {
		Connection conn = null;
		// catch exception of connect
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;// return connect object
	}
	/**
	 * close object of database
	 * @param conn
	 * @param stmt Statement
	 * @param rs
	 */
	public void closeAll(Connection conn, Statement stmt, ResultSet rs) {
		// 
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 
		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void closeAll() {
		closeAll(this.conn, this.pstmt, this.rs);
	}
	
	/**
	 * insert, update, delete, select
	 * @param sql 
	 * @param params
	 * @return execute result
	 */
	public int exceuteUpdate(String sql, Object...params){
		int result = 0;
		conn = this.getConnection();
		try {
			if(conn != null && conn.isClosed() == false) {
				pstmt = conn.prepareStatement(sql);
				for(int i = 0;i < params.length;i++){
					pstmt.setObject(i + 1, params[i]);	
				}
				result = pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll();
		}
		
		return result;
	}
	
	public Object executeQuery(RSProcessor processor, String sql, Object...params) {
		Object result = null;
		conn = this.getConnection();
		try {
			if(conn != null && conn.isClosed() == false) {
				pstmt = conn.prepareStatement(sql);
				for(int i = 0;i < params.length;i++){
					pstmt.setObject(i + 1, params[i]);	
				}
				rs = pstmt.executeQuery();
				result = processor.process(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll();
		}
		return result;
	}
	public int executeInsertReturnId(String sql, Object... params) {
		int result = 0;
		conn = this.getConnection();
		try {
			if(conn != null && !conn.isClosed()) {
				pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				if(params != null) {
					for(int i = 0; i < params.length; i++) {
						pstmt.setObject(i+1, params[i]);
					}
				}
				if(pstmt.executeUpdate() > 0) {
					ResultSet keys = pstmt.getGeneratedKeys();
					if(keys.next()) {
						result = keys.getInt(1);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return result;
	}
}
