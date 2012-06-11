package org.symagic.common.action;


public class UserSessionInformation extends GuestInformationAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7612238184414790769L;
	
	/**
	 * 客户session信息
	 */
	private String userName;
	private String nickName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Override
	public String execute() throws Exception {
		
		userName = (String) session.get("userName");
		nickName = (String) session.get("nickName");
		
		return SUCCESS;
	}

}
