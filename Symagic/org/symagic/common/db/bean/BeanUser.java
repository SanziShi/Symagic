package org.symagic.common.db.bean;

public class BeanUser {
	/**
	 * 用户id
	 */
	private int userId = 0; 
	
	/**
	 * 用户名
	 */
	private String username = ""; 
	
	/**
	 * 用戶昵称
	 */
	private String nickname	= "";	
	
	/**
	 * 用户积分
	 */
	private int integral = 0; 
	
	/**
	 * 问题
	 */
	private String question = ""; 
	
	/**
	 * 答案
	 */
	private String answer = "";

	/**
	 * 密码，未加密状态
	 */
	private String password	= "";	

	/**
	 * 默认无参构造方法
	 */
	public BeanUser() {
	}

	/**
	 * 有参构造方法
	 * 
	 * @param username
	 *            用户名
	 * @param secret
	 *            密码
	 */
	public BeanUser(String username, String secret) {
		this.username = username;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
