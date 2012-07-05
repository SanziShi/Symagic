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
	private String resultInfo;
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
	   if(registerResult){
		   resultInfo="注册成功";
	   }
	   else{
		   resultInfo="注册失败";
	   }
	    return SUCCESS;
	}

	@Override
	public void validate() {
		// TODO Auto-generated method stub
		//验证各项内容 是否为空
		 if(isEmpty(userName)||isEmpty(password)||isEmpty(passwordConfirm)||
				 isEmpty(nickname)||isEmpty(securityQuestion)||isEmpty(securityAnswer)||isEmpty(captchaValue)){
			    validateResult=false;
			   resultInfo="有必填项不能为空";
			     return;
		 }
		 //验证密码前后是否一致
		 if(!password.trim().equals(passwordConfirm.trim())){
			 validateResult=false;
			 resultInfo="两次密码输入不一致";
			 return;
		 }
		 //验证邮箱 格式 与唯一性
		 if(userName.indexOf("@")==-1||(!userService.isUsernameUnique(userName))){
			 validateResult=false;
			  resultInfo="用户名不可用，可能已被注册或用户名不是邮箱";
			 return;
		 }
		 //昵称合法性
		 if(byteCount(nickname)>20||byteCount(nickname)<4){
			 validateResult=false;
			 resultInfo="昵称长度不合法";
			 return;
		 }
		 //验证密码合法性
		 if(password.length()<6||password.length()>20)
		 {
			 validateResult=false;
			 resultInfo="密码不合法";
			 return;
		 }
		 //验证码是否正确
		 if(!symagicCaptcha.validateCaptcha(SessionUtilty.getSessionID(),captchaValue)){
			 validateResult=false;
			 resultInfo="验证码不正确";
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

    public String getResultInfo() {
		return resultInfo;
	}

	public void setResultInfo(String resultInfo) {
		this.resultInfo = resultInfo;
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
