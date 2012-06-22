package org.symagic.user.action.register;

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
    private JCaptcha symagicCaptcha;
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



	private UserService userService;
    
	private String name; // 注册用户名
	



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	private String password; // 注册密码
	private String passwordConfirm;// 注册时密码确认
	private String nickname; // 注册昵称
	private String securityQuestion;// 安全问题
	private String securityAnswer; // 安全问题的甜答案
	private String captchaValue; // 注册验证码
	private String toURL; // 注册之后转向的页面地址
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		//验证失败返回
		if(validateResult==false){
			registerResult=false;
			return SUCCESS;
		}
		
		//进行注册
	    registerResult= userService.register(name, nickname, password, securityQuestion, securityAnswer);
	   
	    return SUCCESS;
	}

	@Override
	public void validate() {
		// TODO Auto-generated method stub
		//验证各项内容 是否为空
		 if(isEmpty(name)||isEmpty(password)||isEmpty(password)||isEmpty(passwordConfirm)||
				 isEmpty(nickname)||isEmpty(securityQuestion)||isEmpty(securityAnswer)||isEmpty(captchaValue)||isEmpty(toURL)){
			    validateResult=false;
			     return;
		 }
		 //验证密码前后是否一致
		 if(!password.trim().equals(passwordConfirm.trim())){
			 validateResult=false;
			 return;
		 }
		 //验证邮箱 格式 与唯一性
		 if(name.indexOf("@")==-1||(!userService.isUsernameUnique(name))){
			 validateResult=false;
			 return;
		 }
		 //昵称长度合法性
		 if(nickname.length()>10){
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

	
	// 验证内容是否为空
	private boolean isEmpty(String content) {
		return content == null || content.trim().equals("");
	}
	
	

	private boolean validateResult=true;// 验证输入是否合法
	private boolean registerResult; // 注册是否成功

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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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

	
	

}
