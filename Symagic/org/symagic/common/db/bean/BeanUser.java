package org.symagic.common.db.bean;

public class BeanUser {
	private int	userId	= 0;	// 用户id
	private String username	= "";	// 用户名
	private int	integral	= 0;	// 用户积分
	private String secret = "";
	


	public BeanUser() {}
	
	public BeanUser(String username, String secret)
	{
		this.username = username;
		this.secret = secret;
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
	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
	
	
}
