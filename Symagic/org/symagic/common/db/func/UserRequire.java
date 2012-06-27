package org.symagic.common.db.func;

public class UserRequire {

	
	/**
	 * 用户名
	 */
	private String username	= "%";
	
	/**
	 * 用户等级
	 */
	private Integer	userLevel	=  null;
	
	/**
	 * 时间下线
	 */
	private String startTime	= null;
	
	/**
	 * 时间上线
	 */
	private String endTime	= null;
	
	/**
	 * 第几页
	 */
	private int page	= 0;
	
	/**
	 * 每页显示多少行
	 */
	private int lines	= 0;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLines() {
		return lines;
	}

	public void setLines(int lines) {
		this.lines = lines;
	}
	
	
	
}
