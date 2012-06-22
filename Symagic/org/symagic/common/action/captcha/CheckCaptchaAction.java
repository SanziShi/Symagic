package org.symagic.common.action.captcha;

import org.symagic.common.utilty.captcha.Captcha;
import org.symagic.common.utilty.session.SessionUtilty;

import com.opensymphony.xwork2.ActionSupport;

public class CheckCaptchaAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4471341834120505008L;
	
	/**
	 * 放置验证码检查结果的变量
	 */
	private boolean captchaCheckResult;
	
	
	/**
	 * 验证码输入变量
	 */
	private String captchaValue;
	
	/**
	 * Symagic封装的Captcha接口
	 */
	private Captcha symagicCaptcha;

	/**
	 * 通过symagicCaptcha验证验证码，并把验证码放置到captchaCheckResult中
	 * 通过SessionUtil获得需要的ID
	 */
	@Override
	public String execute() throws Exception {
		
		captchaCheckResult = symagicCaptcha.validateCaptcha(SessionUtilty.getSessionID(), captchaValue);
		
		return SUCCESS;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isCaptchaCheckResult() {
		return captchaCheckResult;
	}

	/**
	 * 
	 * @param captchaCheckResult
	 */
	public void setCaptchaCheckResult(boolean captchaCheckResult) {
		this.captchaCheckResult = captchaCheckResult;
	}

	/**
	 * 
	 * @return
	 */
	public Captcha getSymagicCaptcha() {
		return symagicCaptcha;
	}

	/**
	 * 
	 * @param symagicCaptcha
	 */
	public void setSymagicCaptcha(Captcha symagicCaptcha) {
		this.symagicCaptcha = symagicCaptcha;
	}

	/**
	 * 
	 * @return
	 */
	public String getCaptchaValue() {
		return captchaValue;
	}

	/**
	 * 
	 * @param captchaValue
	 */
	public void setCaptchaValue(String captchaValue) {
		this.captchaValue = captchaValue;
	}
	
	
	
}
