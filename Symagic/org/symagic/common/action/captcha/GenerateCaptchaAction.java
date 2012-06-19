package org.symagic.common.action.captcha;

import java.io.InputStream;

import org.symagic.common.utility.captcha.Captcha;
import org.symagic.common.utility.session.SessionUtilty;

import com.opensymphony.xwork2.ActionSupport;

public class GenerateCaptchaAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5566129440313649119L;

	/**
	 * Symagic封装的Captcha接口
	 */
	private Captcha symagicCaptcha;
	
	/**
	 * 生成的图片输出流
	 */
	private InputStream imageInputStream;

	/**
	 * 从SessionUtil类中获得sessionID生成Captcha图片,并将其输出到imageInputStream
	 */
	@Override
	public String execute() throws Exception {
		
		imageInputStream = symagicCaptcha.generateImageCaptcha(SessionUtilty.getSessionID());
		
		return SUCCESS;
	}

	/**
	 * 获得Symagic Captche生成器
	 * @return 
	 */
	public Captcha getSymagicCaptcha() {
		return symagicCaptcha;
	}

	/**
	 * 设置验证码生成器
	 * @param symagicCaptcha
	 */
	public void setSymagicCaptcha(Captcha symagicCaptcha) {
		this.symagicCaptcha = symagicCaptcha;
	}

	/**
	 * 获得图片输出流
	 * @return
	 */
	public InputStream getImageInputStream() {
		return imageInputStream;
	}

	/**
	 * 设置图片输出流对象
	 * @param imageInputStream
	 */
	public void setImageInputStream(InputStream imageInputStream) {
		this.imageInputStream = imageInputStream;
	}
	
	
	
}
