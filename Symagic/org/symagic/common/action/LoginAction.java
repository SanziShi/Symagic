package org.symagic.common.action;

import org.symagic.common.SessionUtil;
import org.symagic.common.db.func.DaoUser;

/**
 * 
 * @author zsc Symagic系统用户登录时对于用户名和密码的格式验证与身份验证
 */
public class LoginAction extends GuestInformationAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4521010265859831478L;
	/**
	 * 保存用户名的变量
	 */
	private String name;
	/**
	 * 保存密码的变量
	 */
	private String password;
	/**
	 * 记录身份验证的结果
	 */
	private String result = "true";

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * 判断用户名和密码是否正确
	 */

	public String Login() {

		DaoUser daoUser = new DaoUser();

		if (daoUser.validateUser(name, password)){
			SessionUtil.logLogin(session, name, "");
		}

		return SUCCESS;

	}

}
