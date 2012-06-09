package org.symagic.common.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author zsc
 * Symagic系统用户登录时对于用户名和密码的格式验证与身份验证
 */
public class LoginAction extends ActionSupport {
    /**
     * 保存用户名的变量
     */
	private String name;
	/**
	 * 保存密码的变量
	 */
	private String password;
	/**
	 * 记录格式验证与身份验证的结果
	 */
	private  String result="true";
	/**
	 * 对于用户名和密码的格式进行验证
	 */
	
	private boolean validate=true;
	@Override
	public void validate() {
		// TODO Auto-generated method stub
	  if(name==null||name.trim().length()==0||!name.matches("[_0-9a-zA-Z]*")){
		  validate=false;
	  }
	  if(password==null||password.trim().length()==0||!password.matches("[_0-9a-zA-Z]")){
		  
		  validate=false;
	  }
	  
		super.validate();
	}
public String getUserName() {
		return name;
	}
	public void setUserName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
 
	/**
	 * 判断用户名和密码是否正确
	 */

public void  Login(){
	if(validate){
		//业务处理
	ActionContext.getContext().getSession().put("userName", name);
	result="欢迎你"+name;
	}
	
	
	
	}

}
