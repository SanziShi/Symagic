package org.symagic.common.utilty.session;

import java.util.Map;

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
	
	public static void setInterceptedFormData( Map<String, String[]> data ){
		ServletActionContext.getRequest().getSession().setAttribute("formData", data);
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, String[]> getInterceptedFormData(){
		Object formData = ServletActionContext.getRequest().getSession().getAttribute("formData");
		
		if( formData instanceof Map ){
			return (Map<String, String[]>)formData;
		}
		else{
			return null;
		}
		
	}
	
}
