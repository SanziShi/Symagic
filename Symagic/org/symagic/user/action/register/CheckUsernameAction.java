package org.symagic.user.action.register;

import org.symagic.common.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

public class CheckUsernameAction extends ActionSupport {
    
	
	private UserService userService; //访问用户信息
	private String name; //用户名
	private boolean checkResult;//是否唯一
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		if(name!=null){
			checkResult=userService.isUsernameUnique(name);
		}
		 
		return super.execute();
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isCheckResult() {
		return checkResult;
	}
	public void setCheckResult(boolean checkResult) {
		this.checkResult = checkResult;
	}
	
	
}
