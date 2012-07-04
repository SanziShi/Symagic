package org.symagic.user.action.userinfo;

import org.symagic.common.action.catalog.CatalogBase;
import org.symagic.common.db.bean.BeanUser;
import org.symagic.common.db.func.DaoUser;
import org.symagic.common.db.func.Util;
import org.symagic.common.service.MailService;

public class ForgetPasswordAction extends CatalogBase{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8347861188999367256L;

	private String userName;
	
	private String securityQuestion;
	
	private String securityAnswer;
	
	private DaoUser daoUser;
	
	private boolean isValidate;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getSecurityAnswer() {
		return securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}
	
	public DaoUser getDaoUser() {
		return daoUser;
	}

	public void setDaoUser(DaoUser daoUser) {
		this.daoUser = daoUser;
	}

	public String execute() throws Exception{
		BeanUser user = daoUser.getUser(userName);
		String oldPass = user.getPassword();
		if(user == null)
			return ERROR;
		if(!user.getQuestion().equals(getSecurityQuestion())){
			return ERROR;
		}
		if(!user.getAnswer().equals(Util.getMD5(getSecurityAnswer().getBytes())))
			return ERROR;
		Long newPass = (long) (Math.random()*10000);
		daoUser.updatePassword(userName, newPass.toString(), oldPass);
		
		user.setPassword(newPass.toString());
		MailService.sendNewPassword(user, newPass.toString());
		return SUCCESS;
	}
	
	public void validate(){
		if(securityAnswer == null || securityQuestion == null || userName == null){
			isValidate = false;
		}
	}
}
