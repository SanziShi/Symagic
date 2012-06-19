package org.symagic.user.utilty;

import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * @author hao
 * Session通用操作集
 */
public class SessionUtil {
	
	/**
	 * 判断当前用户是否已经登陆
	 * @return 当前用户是否已经登陆
	 */
	public static boolean isLogin(){
		//获得session
		Map<String, Object> session = ActionContext.getContext().getSession();
		
		return session.containsKey("userName") && session.containsKey("nickname");
		
	}
	
	/**
	 * 记录当前用户的登陆信息
	 * @param userName
	 * @param nickname
	 */
	public static void logLogin(String userName, String nickname ){
		//获得session
		Map<String, Object> session = ActionContext.getContext().getSession();
		
		session.put("userName", userName);
		session.put("nickname", nickname);
	}
	
	/**
	 * 获得当前请求的session id
	 * @return
	 */
	public static String getSessionID(){
		return ServletActionContext.getRequest().getSession().getId();
	}
	
}
