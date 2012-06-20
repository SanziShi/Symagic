package org.symagic.common.utility.session;

import org.apache.struts2.ServletActionContext;

/**
 * 
 * @author hao
 * Session通用操作集
 */
public class SessionUtilty {
		
	/**
	 * 获得当前请求的session id
	 * 
	 * @return
	 */
	public static String getSessionID() {
		return ServletActionContext.getRequest().getSession().getId();
	}
	
}
