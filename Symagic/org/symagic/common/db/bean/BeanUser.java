package org.symagic.common.db.bean;

public class BeanUser {
	private int	userId	= 0;	// 用户id
	private String username	= "";	// 用户名
	private int	integral	= 0;	// 用户积分


	/**
	 * 默认无参构造方法
	 */
	public BeanUser() {}
	
	/**
	 * 有参构造方法
	 * @param username	用户名
	 * @param secret	密码
	 */
	public BeanUser(String username, String secret)
	{
		this.username = username;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getIntegral() {
		return integral;
	}
	public void setIntegral(int integral) {
		this.integral = integral;
	}

	
	
}
