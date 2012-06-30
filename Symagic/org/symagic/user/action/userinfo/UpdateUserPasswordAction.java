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
		if(newPassword.equals(newPasswordConfirm))
			updateResult = daoUser.updatePassword(UserSessionUtilty.getUsername(), newPasswordConfirm, password);
		else {
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
}
