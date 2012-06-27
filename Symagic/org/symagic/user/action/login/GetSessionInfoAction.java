package org.symagic.user.action.login;

import org.symagic.user.utilty.UserSessionUtilty;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GetSessionInfoAction extends ActionSupport {




/**
	 * 
	 */
private static final long serialVersionUID = -43495528308593602L;
private String userName;//用户名
private String nickname;//昵称
private  Integer   loginErrorTimes;//记录登录失败次数
private  Integer totalNumber=0;//购物车中总数量





@Override
public String execute() throws Exception {
	// TODO Auto-generated method stub
	this.setUserName(UserSessionUtilty.getUsername());
	this.setNickname(UserSessionUtilty.getNickname());
	this.setLoginErrorTimes(UserSessionUtilty.getLoginErrorTime());
	this.setTotalNumber((Integer)ActionContext.getContext().getSession().get("totalNumber"));
	return super.execute();
}



public String getUserName() {
	return userName;
}



public void setUserName(String userName) {
	this.userName = userName;
}



public Integer getLoginErrorTimes() {
	return loginErrorTimes;
}

public String getNickname() {
	return nickname;
}
public void setNickname(String nickname) {
	this.nickname = nickname;
}
public Integer getTotalNumber() {
	return totalNumber;
}



public void setTotalNumber(Integer totalNumber) {
	this.totalNumber = totalNumber;
}



public void setLoginErrorTimes(Integer loginErrorTimes) {
	this.loginErrorTimes = loginErrorTimes;
}



}
