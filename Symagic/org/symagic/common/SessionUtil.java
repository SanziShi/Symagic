package org.symagic.common;

import java.util.Map;

/**
 * 
 * @author hao
 * Session通用操作集
 */
public class SessionUtil {
	
	/**
	 * 判断当前用户是否已经登陆
	 * @param session
	 * @return 当前用户是否已经登陆
	 */
	public static boolean is_login( Map<String,Object> session ){
		
		return session.containsKey("userName") && session.containsKey("nickname");
		
	}
	
	/**
	 * 记录当前用户的登陆信息
	 * @param session
	 * @param userName
	 * @param nickname
	 */
	public static void log_login( Map<String,Object> session, String userName, String nickname ){
		session.put("userName", userName);
		session.put("nickname", nickname);
	}
	
}
