package org.symagic.user.action.register;

import java.io.UnsupportedEncodingException;

import org.symagic.common.service.UserService;
import org.symagic.common.utilty.captcha.JCaptcha;
import org.symagic.common.utilty.session.SessionUtilty;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author zsc
 * 
 */
public class RegisterAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7443659242913503854L;
    //传入
	private String userName; // 注册用户名
    private String password; // 注册密码
	private String passwordConfirm;// 注册时密码确认
	private String nickname; // 注册昵称
	private String securityQuestion;// 安全问题
	private String securityAnswer; // 安全问题的甜答案
	private String captchaValue; // 注册验证码
	private String toURL; // 注册之后转向的页面地址
	//配置项
	private JCaptcha symagicCaptcha;
    private UserService userService;
    
	
    
	//传出
	private boolean registerResult; // 注册是否成功
	
    //内部使用
	private boolean validateResult=true;// 验证输入是否合法
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		//验证失败返回
		if(validateResult==false){
			registerResult=false;
			return SUCCESS;
		}
		
		//进行注册
	    registerResult= userService.register(userName, nickname, password, securityQuestion, securityAnswer);
	   
	    return SUCCESS;
	}

	@Override
	public void validate() {
		// TODO Auto-generated method stub
		//验证各项内容 是否为空
		 if(isEmpty(userName)||isEmpty(password)||isEmpty(passwordConfirm)||
				 isEmpty(nickname)||isEmpty(securityQuestion)||isEmpty(securityAnswer)||isEmpty(captchaValue)){
			    validateResult=false;
			   
			     return;
		 }
		 //验证密码前后是否一致
		 if(!password.trim().equals(passwordConfirm.trim())){
			 validateResult=false;
			 return;
		 }
		 //验证邮箱 格式 与唯一性
		 if(userName.indexOf("@")==-1||(!userService.isUsernameUnique(userName))){
			 validateResult=false;
			 return;
		 }
		 //昵称合法性
		 if(!nickname.matches("^[a-zA-Z0-9_\u4e00-\u9fa5]+$")||byteCount(nickname)>20||byteCount(nickname)<5){
			 validateResult=false;
			 return;
		 }
		 //验证密码合法性
		 if(password.length()<6||password.length()>20||(!password.matches("[0-9a-zA-Z]*")))
		 {
			 validateResult=false;
			 return;
		 }
		 //验证码是否正确
		 if(!symagicCaptcha.validateCaptcha(SessionUtilty.getSessionID(),captchaValue)){
			 validateResult=false;
			 return;
		 }
	}
//返回中文字符长度
	private int byteCount(String s) {
		int length=0;
		try {
			length=s.getBytes("gbk").length;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return length;
	}
	// 验证内容是否为空
	private boolean isEmpty(String content) {
		return content == null || content.trim().equals("");
	}
	
	

	

	

	

	public boolean isRegisterResult() {
		return registerResult;
	}

	public void setRegisterResult(boolean registerResult) {
		this.registerResult = registerResult;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
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
	public JCaptcha getSymagicCaptcha() {
		return symagicCaptcha;
	}

	public void setSymagicCaptcha(JCaptcha symagicCaptcha) {
		this.symagicCaptcha = symagicCaptcha;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}



	
	



	



	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}



	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	
	

}
