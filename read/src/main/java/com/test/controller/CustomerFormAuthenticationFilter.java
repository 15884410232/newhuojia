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
	

	
	/**Ĭ����֤���������*/
	public static final String DEFAULT_CAPTCHA_PARAM = "captcha";
	/**��֤���������:Ĭ��Ϊcaptcha*/
	private String captchaParam = DEFAULT_CAPTCHA_PARAM;
	/**��֤���������:Ĭ��Ϊcaptcha*/
	public String getCaptchaParam() {
		return captchaParam;
	}
	/**��֤���������:Ĭ��Ϊcaptcha*/
	public void setCaptchaParam(String captchaParam) {
		this.captchaParam = captchaParam;
	}
	/**��֤���ֵ*/
	protected String getCaptcha(ServletRequest request) {
		return WebUtils.getCleanParam(request, getCaptchaParam());
	}

	/**�����쳣����ʾ��Ϣ��������,����ǰ̨��ȡ*/
	@Override
	protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
		if(ae!=null) request.setAttribute(getFailureKeyAttribute(), ae.getMessage());
	}
	/**
	 * �жϸ����������ǲ���AJAX����
	 **/
	protected boolean isAjaxRequest(HttpServletRequest request) {
		return (request != null && ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With")) || request.getParameter("ajax") != null));
	}
	/**
	 * ִ�е�¼
	 */
	@Override
	protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
		CaptchaUsernamePasswordToken token=null;
		try {
			token = createToken(request, response);
			logger.debug("ִ�е�¼��ȡ����:{}",token);
			//doCaptchaValidate((HttpServletRequest) request, token);/* ͼ����֤����֤ */
			Subject subject = getSubject(request, response);
			subject.login(token);// ������֤
			logger.info("[{}]��¼�ɹ�",token.getUsername());
			

			
			return onLoginSuccess(token, subject, request, response);
		} catch (AuthenticationException e) {

			
			logger.info("[{}]��¼ʧ��:{}",token==null?"":token.getUsername(),e.getMessage());
			return onLoginFailure(token, e, request, response);
		}
	}
	/**
	 * �Ƚ��������֤���session�е���֤���Ƿ�һ��
	 * ��һ��ʱ���쳣
	 * @param request
	 * @param token
	 */
//	protected void doCaptchaValidate(HttpServletRequest request, CaptchaUsernamePasswordToken token) {
//		String captcha = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);// session�е�ͼ�����ַ���
//		//TODO ��ʱ������֤��
//		//if(StringUtil.isEmpty(captcha)) throw new UnknownAccountException("��ȡ������̨���ɵ���֤��.");
//		// �������֤���session�е���֤��Ƚ�
//		if (captcha != null && !captcha.equalsIgnoreCase(token.getCaptcha())) {
//			throw new UnknownAccountException("��֤�����");
//		}
//		request.getSession().removeAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
//	}
	/**
	 * ��ȡ��¼�������ز���
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