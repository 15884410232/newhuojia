package com.test.controller;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.support.json.JSONUtils;
import com.test.bean.Huojia;
import com.test.bean.User;
import com.test.service.DataService;
import com.test.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private DataService dataService;
	@Autowired
	private UserService UserService;

	// 进入登录页面
	@RequestMapping("login")
	public String login(HttpServletRequest request, HttpServletResponse response) {

		return "login";
	}

	// 进入主页面
	@RequiresPermissions("index")
	@RequestMapping("index")
	public String index(HttpServletRequest request, HttpServletResponse response) {

		return "index";
	}

	// 退出
	@RequestMapping("loginOut")
	public String loginOut(HttpServletRequest request, HttpServletResponse response) {
		HttpSession s = request.getSession();
		s.invalidate();
		return "login";
	}

	// // 登录验证
	// @RequestMapping("loginCheck")
	// public ModelAndView loginCheck(String name, String password, String
	// code,HttpServletRequest request) {
	// ModelAndView mv = new ModelAndView();
	// try {
	// // 解决url中文乱码
	// name = new String(name.getBytes("iso-8859-1"), "utf-8");
	// } catch (UnsupportedEncodingException e) {
	// e.printStackTrace();
	// }
	// System.out.println("ss"+request.getSession().getAttribute("code"));
	// System.out.println(code.toUpperCase());
	// User user = UserService.findUser(name, password);
	//
	// if(!request.getSession().getAttribute("code").equals(code.toUpperCase())){
	// mv.addObject("msg","验证码不正确");
	// mv.setViewName("login");
	// return mv;
	// }
	// if(user==null){
	// mv.addObject("msg","用户名或密码错误");
	// mv.setViewName("login");
	// return mv;
	// }
	//
	// mv.setViewName("index");
	// mv.addObject("userName", user.getUserName());
	// request.getSession().setAttribute("login", "true");
	// request.getSession().setAttribute("user", user);
	//
	// return mv;
	// }

	/**
	 * 验证用户名和密码
	 * 
	 * @param String
	 *            username,String password
	 * @return
	 */
	@RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
	public String checkLogin(String username, String password, String code, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		ModelAndView mv = new ModelAndView();
		try {

			if (!request.getSession().getAttribute("code").equals(code.toUpperCase())) {
				return "";
			}

			// UsernamePasswordToken token = new UsernamePasswordToken(username,
			// password);
			// Subject currentUser = SecurityUtils.getSubject();
			// if (!currentUser.isAuthenticated()){
			// //使用shiro来验证
			// token.setRememberMe(true);
			// currentUser.login(token);//验证角色和权限
			// }

			Subject sb=SecurityUtils.getSubject();
			System.out.println(sb.isAuthenticated());
			if(!sb.isAuthenticated()){
				sb.login(new UsernamePasswordToken(username, password));
			}

			return "redirect:/index";
		} catch (AuthenticationException e) {
			e.printStackTrace();
			return "redirect:/login";

		}
	}

	/**
	 * 退出登录
	 */
	@RequestMapping(value = "/logout.json", method = RequestMethod.POST)
	@ResponseBody
	public String logout() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		return JSONUtils.toJSONString(result);
	}
	@RequiresPermissions("huojia")
	@RequestMapping("myloginfo")
	public ModelAndView myloginfo(HttpSession session) {
		Subject sub=SecurityUtils.getSubject();

		ModelAndView mv = new ModelAndView();
		if (session.getAttribute("login").equals("true")) {
			mv.setViewName("myloginfo");
		} else {
			mv.setViewName("login");
		}
		return mv;
	}


	@ResponseBody
	@RequestMapping("getHuojia")
	public List<Huojia> getRecord(@RequestParam("page") String page, HttpSession session) {
		if (!session.getAttribute("login").equals("true")) {
			return null;
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String count = dataService.getCount();
		Integer offset = (Integer.parseInt(page) - 1) * 10;
		List<Huojia> list = dataService.getHuojia(offset);
		// for (Map<String, String> map : list) {
		// map.put("time", sf.format(map.get("time")));
		// }
		return list;
	}

	// 分页总条数
	@ResponseBody
	@RequestMapping("getRecordCount")
	public Map<String, Object> getRecordCount(HttpSession session) {
		if (!session.getAttribute("login").equals("true")) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		String count = dataService.getCount();

		int pageCount = Integer.valueOf(count) / 10;
		if ((Integer.valueOf(count) % 10) == 0) {

		} else {
			pageCount++;
		}
		map.put("pageCount", pageCount);
		return map;

	}

	// 验证码
	@RequestMapping("code")
	public void code(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> codeMap = CodeUtil.generateCodeAndPic();

		// 将四位数字的验证码保存到Session中。
		HttpSession session = request.getSession();
		session.setAttribute("code", codeMap.get("code").toString());
		System.out.println(session.getAttribute("code"));
		// 禁止图像缓存。
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", -1);

		response.setContentType("image/jpeg");

		// 将图像输出到Servlet输出流中。
		try {
			ImageIO.write((RenderedImage) codeMap.get("codePic"), "jpeg", response.getOutputStream());
			response.getOutputStream().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
