package com.test.controller;

import org.apache.shiro.authc.UsernamePasswordToken;
/**
 * 扩展令牌增加接收验证码参数
 * 令牌包含的参数：
 * username : 登录账号 
 * password : 登录密码 
 * rememberMe : 是否记住账号 
 * host       : 登录主机
 * captcha    : 验证码(扩展)
 * @author yhm
 *
 */
public class CaptchaUsernamePasswordToken extends UsernamePasswordToken {  

	private static final long serialVersionUID = -1639376359126097251L;
	/**接收输入的验证码字符串*/  
    private String captcha;
    
    /**构造函数*/
    public CaptchaUsernamePasswordToken(String username, char[] password,boolean rememberMe, String host){
    	super(username, password, rememberMe, host);
    }
    /**构造函数*/
    public CaptchaUsernamePasswordToken(String username, char[] password,boolean rememberMe, String host, String captcha) {  
        super(username, password, rememberMe, host);  
        this.captcha = captcha;  
    }
    /**构造函数*/
    public CaptchaUsernamePasswordToken(String username, String password, boolean rememberMe, String host){
    	super(username, password, rememberMe, host);
    }
    /**构造函数*/
    public CaptchaUsernamePasswordToken(String username, String password, boolean rememberMe, String host, String captcha) {
    	super(username, password, rememberMe, host);
    	this.captcha=captcha;
	}
    /**接收输入的验证码字符串*/
	public String getCaptcha() {  
        return captcha;  
    }  
	/**接收输入的验证码字符串*/
    public void setCaptcha(String captcha) {  
        this.captcha = captcha;  
    }

	@Override
	public String toString() {
		return "CaptchaUsernamePasswordToken [captcha=" + captcha + ", username=" + getUsername() + ", host="+ getHost() + ", isRememberMe=" + isRememberMe() + "]";
	}
	@Override
	public void clear() {
		super.clear();
		this.captcha=null;
	}  
      
}  
