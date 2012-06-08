package org.symagic.common.action;

import java.util.Map;

import org.apache.struts2.interceptor.CookiesAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

/**
 * 
 * @author hao
 * Symagic系统使用的Action，该类提供userName，nickName的获取，并对其子类开放session存取。<br>
 * SymagicAction的子类通过session变量访问session.
 */
public class SymagicAction extends ActionSupport implements CookiesAware,
		SessionAware, Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6763375712103903592L;
	
	
	
	/**
	 * @author hao
	 * 储存用户名的变量
	 */
	private String userName;
	
	/**
	 * @author hao
	 * 储存用户昵称
	 */
	private String nickName;
	
	/**
	 * @author hao
	 * Action所使用的Session结构
	 */
	protected Map<String, Object> session;
	
	/**
	 * @author hao
	 * Action所使用的Cookies结构
	 */
	protected Map<String, String> cookies;
	

	/**
	 * 设置通用的用户名的用户昵称
	 */
	@Override
	public void prepare() throws Exception {
		//获得用户名和用户昵称
		userName = (String)session.get("userName");
		nickName = (String)session.get("nickName");
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 * 判断用户是否已经登陆
	 */
	public String isLogin() throws Exception{
		
		if( session.containsKey("userName") && session.containsKey("nickName") )
			return "true";
		else
			return "false";
		
	}

	
	/**
	 * @author hao
	 * 设置Action使用的Session
	 */
	@Override
	public void setSession(Map<String, Object> arg0) {
		session = arg0;

	}
	
	/**
	 * @author hao
	 * 设置Action使用的Cookies
	 */
	@Override
	public void setCookiesMap(Map<String, String> arg0) {
		cookies = arg0;

	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getNickName() {
		return nickName;
	}


	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

}
