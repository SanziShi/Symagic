package org.symagic.common.utilty.captcha;

import java.io.InputStream;

/**
 * 
 * @author hao
 * 为系统提供验证码服务的借口
 */
public interface Captcha {
	/**
	 * 
	 * @param 用于生成Image的ID，一个Image对应一个独立的ID
	 * @return 返回包含图片数据的InputStream
	 */
	public InputStream generateImageCaptcha( String ID );
	
	/**
	 * 
	 * @return 返回当前当前Captcha生成数据的ContentType
	 */
	public String getCaptchaContentType();
	
	/**
	 * 
	 * @param 用于生成Image的ID，一个Image对应一个独立的ID
	 * @param captcha，用户输入的验证码
	 * @return 用户是否是机器。
	 */
	public boolean validateCaptcha( String ID, String captcha );
	
	
	public boolean ajaxValidateCaptcha( String ID, String captcha );
	
	
}
