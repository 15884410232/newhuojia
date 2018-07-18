package com.test.controller;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;



public class CustomerFormAuthenticationFilter extends FormAuthenticationFilter {
	
	private Logger logger=LoggerFactory.getLogger(getClass());
	

	
	/**默认验证码参数名称*/
	public static final String DEFAULT_CAPTCHA_PARAM = "captcha";
	/**验证码参数名称:默认为captcha*/
	private String captchaParam = DEFAULT_CAPTCHA_PARAM;
	/**验证码参数名称:默认为captcha*/
	public String getCaptchaParam() {
		return captchaParam;
	}
	/**验证码参数名称:默认为captcha*/
	public void setCaptchaParam(String captchaParam) {
		this.captchaParam = captchaParam;
	}
	/**验证码的值*/
	protected String getCaptcha(ServletRequest request) {
		return WebUtils.getCleanParam(request, getCaptchaParam());
	}

	/**保存异常的提示信息到请求中,便于前台获取*/
	@Override
	protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
		if(ae!=null) request.setAttribute(getFailureKeyAttribute(), ae.getMessage());
	}
	/**
	 * 判断给出的请求是不是AJAX请求
	 **/
	protected boolean isAjaxRequest(HttpServletRequest request) {
		return (request != null && ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With")) || request.getParameter("ajax") != null));
	}
	/**
	 * 执行登录
	 */
	@Override
	protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
		CaptchaUsernamePasswordToken token=null;
		try {
			token = createToken(request, response);
			logger.debug("执行登录获取参数:{}",token);
			//doCaptchaValidate((HttpServletRequest) request, token);/* 图形验证码验证 */
			Subject subject = getSubject(request, response);
			subject.login(token);// 正常验证
			logger.info("[{}]登录成功",token.getUsername());
			

			
			return onLoginSuccess(token, subject, request, response);
		} catch (AuthenticationException e) {

			
			logger.info("[{}]登录失败:{}",token==null?"":token.getUsername(),e.getMessage());
			return onLoginFailure(token, e, request, response);
		}
	}
	/**
	 * 比较输入的验证码和session中的验证码是否一致
	 * 不一致时报异常
	 * @param request
	 * @param token
	 */
//	protected void doCaptchaValidate(HttpServletRequest request, CaptchaUsernamePasswordToken token) {
//		String captcha = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);// session中的图形码字符串
//		//TODO 暂时屏蔽验证码
//		//if(StringUtil.isEmpty(captcha)) throw new UnknownAccountException("获取不到后台生成的验证码.");
//		// 输入的验证码和session中的验证码比较
//		if (captcha != null && !captcha.equalsIgnoreCase(token.getCaptcha())) {
//			throw new UnknownAccountException("验证码错误！");
//		}
//		request.getSession().removeAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
//	}
	/**
	 * 获取登录输入的相关参数
	 */
	@Override
	protected CaptchaUsernamePasswordToken createToken(ServletRequest request, ServletResponse response) {
		String username = getUsername(request);
		String password = getPassword(request);
		String captcha = getCaptcha(request);
		boolean rememberMe = isRememberMe(request);
		String host = getHost(request);					
		return new CaptchaUsernamePasswordToken(username, (password==null?"":password).toCharArray(), rememberMe, host, captcha);
	}
	
}