package org.symagic.security.action;

import java.io.InputStream;

import org.apache.struts2.ServletActionContext;
import org.symagic.common.bean.captcha.Captcha;

import com.opensymphony.xwork2.ActionSupport;


public class CaptchaAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5705041225538818776L;
	
	/**
	 *  验证码生成器
	 */
	private Captcha symagicCaptche;
	
	/**
	 * 验证码输入域
	 */
	private String captcheValue;
	
	/**
	 * 图片输出流
	 */
	private InputStream imageInputStream;
	
	/**
	 * @author hao
	 * 返回注册码图片
	 */
	@Override
	public String execute() throws Exception {
		
		imageInputStream = symagicCaptche.generateImageCaptcha(ServletActionContext.getRequest().getSession().getId());
		
		return SUCCESS;
	}
	
	public String checkCaptcha() throws Exception{
		if( symagicCaptche.validateCaptcha(ServletActionContext.getRequest().getSession().getId(), captcheValue))
			return "true";
		else
			return "false";
	}

	public Captcha getSymagicCaptche() {
		return symagicCaptche;
	}

	public void setSymagicCaptche(Captcha symagicCaptche) {
		this.symagicCaptche = symagicCaptche;
	}

	public String getCaptcheValue() {
		return captcheValue;
	}

	public void setCaptcheValue(String captcheValue) {
		this.captcheValue = captcheValue;
	}

	public InputStream getImageInputStream() {
		return imageInputStream;
	}

	public void setImageInputStream(InputStream imageInputStream) {
		this.imageInputStream = imageInputStream;
	}

}
