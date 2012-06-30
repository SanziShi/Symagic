package org.symagic.admin.action.login;

import org.symagic.admin.utilty.AdminSessionUtilty;

import com.opensymphony.xwork2.ActionSupport;

public class AdminLogoutAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6828164807369969525L;

	@Override
	public String execute() throws Exception {
		AdminSessionUtilty.logLogout();
		return super.execute();
	}
	
	

}
