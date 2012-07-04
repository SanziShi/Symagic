package org.symagic.user.action.userinfo;

import org.symagic.common.action.catalog.CatalogBase;
import org.symagic.common.db.func.DaoUser;
import org.symagic.user.utilty.UserSessionUtilty;

public class UpdateUserPasswordAction extends CatalogBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6505045761111132540L;

	private String userName;
	
	private String password;
	
	private String newPassword;
	
	private String newPasswordConfirm;
	
	private Boolean updateResult;
	
	private DaoUser daoUser;
	
	private String resultInfo;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordConfirm() {
		return newPasswordConfirm;
	}

	public void setNewPasswordConfirm(String newPasswordConfirm) {
		this.newPasswordConfirm = newPasswordConfirm;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String execute() throws Exception{
		if(newPassword.equals(newPasswordConfirm)){
			if(newPassword.getBytes().length < 6){
				updateResult = false;
				resultInfo = "密码过短，应大于6位";
				return SUCCESS;
			}
			if(newPassword.getBytes().length > 20){
				updateResult = false;
				resultInfo = "密码过长，应小于20位";
				return SUCCESS;
			}
			if(newPassword.trim().equals(password.trim())){
				updateResult = false;
				resultInfo = "与原密码相同";
				return SUCCESS;
			}
			updateResult = daoUser.updatePassword(UserSessionUtilty.getUsername(), newPasswordConfirm, password);
			if(!updateResult){
				resultInfo = "密码错误";
			}
		}
		else {
			resultInfo = "两次输入的密码不同";
			updateResult = false;
		}
		return SUCCESS;
	}

	public Boolean getUpdateResult() {
		return updateResult;
	}

	public void setUpdateResult(Boolean updateResult) {
		this.updateResult = updateResult;
	}

	public DaoUser getDaoUser() {
		return daoUser;
	}

	public void setDaoUser(DaoUser daoUser) {
		this.daoUser = daoUser;
	}

	public String getResultInfo() {
		return resultInfo;
	}

	public void setResultInfo(String resultInfo) {
		this.resultInfo = resultInfo;
	}
}
