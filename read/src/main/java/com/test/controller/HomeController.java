//package com.test.controller;
//
//import java.awt.image.RenderedImage;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.net.URLDecoder;
//import java.text.SimpleDateFormat;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.imageio.ImageIO;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
//import org.apache.shiro.subject.Subject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.alibaba.druid.stat.TableStat.Mode;
//import com.alibaba.druid.support.json.JSONUtils;
//import com.test.bean.Huojia;
//import com.test.bean.User;
//import com.test.service.DataService;
//import com.test.service.UserService;
//
//@Controller
//public class HomeController {
//
//	@Autowired
//	private DataService dataService;
//	@Autowired
//	private UserService UserService;
//
//	// �����¼ҳ��
//	@RequestMapping("login")
//	public String login(HttpServletRequest request, HttpServletResponse response) {
//
//		return "login";
//	}
//
//	// ������ҳ��
//	@RequestMapping("index")
//	public String index(HttpServletRequest request, HttpServletResponse response) {
//
//		return "index";
//	}
//	
//	// �˳�
//	@RequestMapping("loginOut")
//	public String loginOut(HttpServletRequest request, HttpServletResponse response) {
//		HttpSession s = request.getSession();
//		s.invalidate();
//		return "login";
//	}
//
//	// ��֤��
//	@RequestMapping("code")
//	public void code(HttpServletRequest request, HttpServletResponse response) {
//		Map<String, Object> codeMap = CodeUtil.generateCodeAndPic();
//
//		// ����λ���ֵ���֤�뱣�浽Session�С�
//		HttpSession session = request.getSession();
//		session.setAttribute("code", codeMap.get("code").toString());
//		System.out.println(session.getAttribute("code"));
//		// ��ֹͼ�񻺴档
//		response.setHeader("Pragma", "no-cache");
//		response.setHeader("Cache-Control", "no-cache");
//		response.setDateHeader("Expires", -1);
//
//		response.setContentType("image/jpeg");
//
//		// ��ͼ�������Servlet������С�
//		try {
//			ImageIO.write((RenderedImage) codeMap.get("codePic"), "jpeg", response.getOutputStream());
//			response.getOutputStream().close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	// ��¼��֤
//	@RequestMapping("loginCheck")
//	public ModelAndView loginCheck(String name, String password, String code,HttpServletRequest request) {
//		ModelAndView mv = new ModelAndView();
//		try {
//			// ���url��������
//			name = new String(name.getBytes("iso-8859-1"), "utf-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		System.out.println("ss"+request.getSession().getAttribute("code"));
//		System.out.println(code.toUpperCase());
//		User user = UserService.findUser(name, password);
//		
//		if(!request.getSession().getAttribute("code").equals(code.toUpperCase())){
//			mv.addObject("msg","��֤�벻��ȷ");
//			mv.setViewName("login");
//			return mv;
//		}
//		if(user==null){
//			mv.addObject("msg","�û������������");
//			mv.setViewName("login");
//			return mv;
//		}
//
//			mv.setViewName("index");
//			mv.addObject("userName", user.getUserName());
//			request.getSession().setAttribute("login", "true");
//			request.getSession().setAttribute("user", user);
//
//		return mv;
//	}
//
//	
//	   /** 
//     * ��֤�û��������� 
//     * @param String username,String password
//     * @return 
//     */  
//    @RequestMapping(value="/checkLogin.json",method=RequestMethod.POST)  
//    @ResponseBody  
//    public String checkLogin(String username,String password) {  
//        Map<String, Object> result = new HashMap<String, Object>();
//        try{
//            UsernamePasswordToken token = new UsernamePasswordToken(username, password);  
//            Subject currentUser = SecurityUtils.getSubject();  
//            if (!currentUser.isAuthenticated()){
//                //ʹ��shiro����֤  
//                token.setRememberMe(true);  
//                currentUser.login(token);//��֤��ɫ��Ȩ��  
//            } 
//        }catch(Exception ex){
//            throw ex;
//        }
//        result.put("success", true);
//        return JSONUtils.toJSONString(result);  
//    }  
//	
//    /** 
//     * �˳���¼
//     */  
//    @RequestMapping(value="/logout.json",method=RequestMethod.POST)    
//    @ResponseBody    
//    public String logout() {   
//        Map<String, Object> result = new HashMap<String, Object>();
//        result.put("success", true);
//        Subject currentUser = SecurityUtils.getSubject();       
//        currentUser.logout();    
//        return JSONUtils.toJSONString(result);
//    }  
//    
//    
//    
//    
//	
//	@RequestMapping("myloginfo")
//	public ModelAndView myloginfo(HttpSession session) {
//		ModelAndView  mv=new ModelAndView();
//		if(session.getAttribute("login").equals("true")){
//			mv.setViewName("myloginfo");
//		}else{
//			mv.setViewName("login");
//		}
//		return mv;
//	}
//
//	@ResponseBody
//	@RequestMapping("getHuojia")
//	public List<Huojia> getRecord(@RequestParam("page") String page,HttpSession session) {
//		if(!session.getAttribute("login").equals("true")){
//			return null;
//		}
//		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String count = dataService.getCount();
//		Integer offset = (Integer.parseInt(page) - 1) * 10;
//		List<Huojia> list = dataService.getHuojia(offset);
////		for (Map<String, String> map : list) {
////			map.put("time", sf.format(map.get("time")));
////		}
//		return list;
//	}
//
//	@ResponseBody
//	@RequestMapping("getRecordCount")
//	public Map<String, Object> getRecordCount(HttpSession session) {
//		if(!session.getAttribute("login").equals("true")){
//			return null;
//		}
//		Map<String, Object> map = new HashMap<String, Object>();
//		String count = dataService.getCount();
//
//		int pageCount = Integer.valueOf(count) / 10;
//		if ((Integer.valueOf(count) % 10) == 0) {
//
//		} else {
//			pageCount++;
//		}
//		map.put("pageCount", pageCount);
//		return map;
//
//	}
//
//	// @RequestMapping("myloginfo")
//	// public String myloginfo(ModelMap map) {
//	// Map<String,String> pageInfo=new HashMap<String,String>();
//	// pageInfo.put("currentPage", "0");
//	// pageInfo.put("pageNum", "0");
//	// pageInfo.put("totalCount", "0");
//	//
//	//
//	// map.addAttribute("pageInfo",pageInfo);
//	// return "myloginfo";
//	// }
//
//	// ��ȡҺλ�����޺�����
//	@RequiresPermissions("p2p.product.select.makeLoansList.selectDaihuankuanInfo.repayOperation")
//	@ResponseBody
//	@RequestMapping("data")
//	public String data(String xinghao) {
//		return dataService.getData(xinghao).get(0).toString();
//	}
//
//	// ���������Ϣҳ��
//	@RequestMapping("personInfo")
//	public ModelAndView personInfo(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
//		if(!session.getAttribute("login").equals("true")){
//			return null;
//		}
//		ModelAndView mv=new ModelAndView();
//		mv.setViewName("personInfo");
//		User user=(User) session.getAttribute("user");
//		mv.addObject("user",user);
//		return mv;
//	}
//	// ���������Ϣҳ��
//	@RequestMapping("updateUserInfo")
//	public ModelAndView updateUserInfo(@RequestParam("realName")String realName,@RequestParam("phone") String phone,HttpServletRequest request, HttpServletResponse response,HttpSession session) {
//		if(!session.getAttribute("login").equals("true")){
//			return null;
//		}
//		ModelAndView mv=new ModelAndView();
//		mv.setViewName("personInfo");
//		User user=(User) session.getAttribute("user");
//		user.setUserRealName(realName);
//		user.setUserPhone(phone);
//		UserService.updateUserInfo(user);
//		mv.addObject("user",user);
//		return mv;
//	}
//
//	// �����޸�����ҳ��
//	@RequestMapping("changepwd")
//	public ModelAndView changepwd(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
//		if(!session.getAttribute("login").equals("true")){
//			return null;
//		}
//		ModelAndView mv=new ModelAndView();
//		mv.setViewName("changepwd");
//		User user=(User) session.getAttribute("user");
//		mv.addObject(user);
//		return mv;
//	}
//	@RequestMapping("changepwds")
//	public ModelAndView changepwds(@RequestParam("password") String password,HttpServletRequest request, HttpServletResponse response,HttpSession session) {
//		if(!session.getAttribute("login").equals("true")){
//			return null;
//		}
//		User user =(User) session.getAttribute("user");
//		user.setUserPassword(password);
//		UserService.updateUserInfo(user);
//		ModelAndView mv=new ModelAndView();
//		mv.setViewName("changepwd");
//		mv.addObject(user);
//		return mv;
//	}
//}
