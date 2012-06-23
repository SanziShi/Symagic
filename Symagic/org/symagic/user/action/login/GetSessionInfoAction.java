package org.symagic.user.action.login;

import org.symagic.user.utilty.UserSessionUtilty;

import com.opensymphony.xwork2.ActionSupport;

public class GetSessionInfoAction extends ActionSupport {

private String username;//用户名
private String nickname;//昵称
private  int   loginErrorTimes;//记录登录失败次数



@Override
public String execute() throws Exception {
	// TODO Auto-generated method stub
	this.setUsername(UserSessionUtilty.getUsername());
	this.setNickname(UserSessionUtilty.getNickname());
	
	return super.execute();
}



public int getLoginErrorTimes() {
	return loginErrorTimes;
}
public void setLoginErrorTimes(int loginErrorTimes) {
	this.loginErrorTimes = loginErrorTimes;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getNickname() {
	return nickname;
}
public void setNickname(String nickname) {
	this.nickname = nickname;
}



}
