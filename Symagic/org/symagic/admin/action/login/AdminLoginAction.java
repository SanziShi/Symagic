package org.symagic.admin.action.login;

import org.symagic.admin.utilty.AdminSessionUtilty;
import org.symagic.common.db.func.DaoAdmin;
import org.symagic.common.utility.captcha.Captcha;

import com.opensymphony.xwork2.ActionSupport;

public class AdminLoginAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9192677626180527058L;
	
	/**
	 * 
	 */
	private String userName;
	
	/**
	 * 
	 */
	private String password;
	
	/**
	 * 
	 */
	private String captchaValue;
	
	/**
	 * 
	 */
	private String toURL;
	

	/**
	 * 
	 */
	private Captcha symagicCaptcha;
	
	private boolean validateResult;
	
	private DaoAdmin daoAdmin;

	@Override
	public void validate() {
		
		if( userName == null || password == null || captchaValue == null )
			validateResult = false;
		else
			validateResult = true;
		
	}

	@Override
	public String execute() throws Exception {
		
		if( !validateResult )
			return ERROR;
		
		if( !symagicCaptcha.validateCaptcha(AdminSessionUtilty.getSessionID(), captchaValue) )
			return ERROR;
		
		if( !daoAdmin.validateAdmin(userName, password) ){
			return ERROR;
		}
		
		AdminSessionUtilty.logLogin(userName);
		
		return SUCCESS;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCaptchaValue() {
		return captchaValue;
	}

	public void setCaptchaValue(String captchaValue) {
		this.captchaValue = captchaValue;
	}

	public String getToURL() {
		return toURL;
	}

	public void setToURL(String toURL) {
		this.toURL = toURL;
	}

	public Captcha getSymagicCaptcha() {
		return symagicCaptcha;
	}

	public void setSymagicCaptcha(Captcha symagicCaptcha) {
		this.symagicCaptcha = symagicCaptcha;
	}

	public boolean isValidateResult() {
		return validateResult;
	}

	public void setValidateResult(boolean validateResult) {
		this.validateResult = validateResult;
	}

	public DaoAdmin getDaoAdmin() {
		return daoAdmin;
	}

	public void setDaoAdmin(DaoAdmin daoAdmin) {
		this.daoAdmin = daoAdmin;
	}

}
