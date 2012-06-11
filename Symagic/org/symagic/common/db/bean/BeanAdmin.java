package org.symagic.common.db.bean;

/**
 * 管理员bean
 * @author wanran
 *
 */
public class BeanAdmin {
	private int	adminID	=	0;	// 管理员id
	private String	adminName	= "";	// 管理员用户名
	private String	password	= "";	// 管理员密码，经过Md5加密的
	
	public int getAdminID() {
		return adminID;
	}
	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
