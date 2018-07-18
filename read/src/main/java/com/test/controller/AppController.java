package com.test.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.test.bean.User;
import com.test.service.UserService;

@Controller
@RequestMapping("app")
public class AppController {
	
	@Autowired
	private UserService userService;
	// ��¼��֤
		@ResponseBody
		@RequestMapping("login")
		public String loginCheck(String name, String password,HttpServletRequest request) {
			try {
				// ���url��������
				name = new String(name.getBytes("iso-8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			User user = userService.findUser(name, password);
			if(user==null){
				return "�û������������";

			}
				return "ok";
		}
}
