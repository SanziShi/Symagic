package org.symagic.common.action;

import java.util.Map;

import org.apache.struts2.interceptor.CookiesAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class GuestInformationAction extends ActionSupport implements
		CookiesAware, SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7443014819229477415L;
	
	/**
	 * 向其子类转发session，cookies。
	 */
	protected Map<String,Object> session;
	protected Map<String,String> cookies;

	@Override
	public void setSession(Map<String, Object> arg0) {
		session = arg0;

	}

	@Override
	public void setCookiesMap(Map<String, String> arg0) {
		cookies = arg0;

	}

}
