package org.symagic.common.action.captcha;

import org.symagic.common.utility.captcha.Captcha;

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
	 * Symagic封装的Captcha接口
	 */
	private Captcha symagicCaptcha;

	/**
	 * 通过symagicCaptcha验证验证码，并把验证码放置到captchaCheckResult中
	 * 通过SessionUtil获得需要的ID
	 * (未实现）
	 */
	@Override
	public String execute() throws Exception {
		
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
	
	
	
}
