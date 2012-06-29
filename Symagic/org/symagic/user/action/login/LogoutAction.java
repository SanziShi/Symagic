package org.symagic.user.action.login;

import org.symagic.user.utilty.UserSessionUtilty;

import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2525720594658485426L;
	//传出
	private boolean logoutResult;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
	   UserSessionUtilty.cleanSession();
	   logoutResult=true;
		return super.execute();
	}

	public boolean isLogoutResult() {
		return logoutResult;
	}

	public void setLogoutResult(boolean logoutResult) {
		this.logoutResult = logoutResult;
	}
	

}
