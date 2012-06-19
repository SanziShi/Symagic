package org.symagic.admin.utilty;

import java.util.Map;

import org.symagic.common.utility.session.SessionUtilty;

import com.opensymphony.xwork2.ActionContext;

public class AdminSessionUtilty extends SessionUtilty {
	
	/**
	 * 记录当前管理员的登陆信息
	 * 
	 * @param adminName
	 */
	public static void logLogin(String adminName) {
		// 获得session
		Map<String, Object> session = ActionContext.getContext().getSession();

		session.put("adminName", adminName);
	}

	/**
	 * 判断当前管理员是否已经登陆
	 * 
	 * @return 当前管理员是否已经登陆
	 */
	public static boolean isLogin() {
		// 获得session
		Map<String, Object> session = ActionContext.getContext().getSession();

		return session.containsKey("adminName");

	}
	
	
}
