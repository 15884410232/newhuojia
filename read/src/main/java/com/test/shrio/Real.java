package com.test.shrio;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.bean.User;
import com.test.service.PermissionService;
import com.test.service.UserService;

public class Real extends AuthorizingRealm {
	@Autowired
	private UserService userService;
	
	@Autowired
	private PermissionService permissionService;
	
	
	//当前登录用户写入到session中的key
	public static String ACCOUNTKEY="loginUser";
 
    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		if(!StringUtils.hasText(upToken.getUsername())) throw new UnknownAccountException("必须输入账号或密码！");
		if(upToken.getPassword()==null || upToken.getPassword().length<=0)  throw new UnknownAccountException("必须输入账号或密码！");
		
		//查找用户
		User user=userService.findUser(upToken.getUsername(), upToken.getUsername());
		if(user==null) throw new UnknownAccountException("账户不存在或账户");
		
		String realmName = this.getName(); 
		//开始写入会话
		setSession(ACCOUNTKEY,user);//写入用户信息到回话中
		setSession("login", "true");
        //此处无需比对,比对的逻辑Shiro会做,我们只需返回一个和令牌相关的正确的验证信息  
        //说白了就是第一个参数填登录用户名,第二个参数填合法的登录密码(可以是从数据库中取到的  
        //这样一来,在随后的登录页面上就只有这里指定的用户和密码才能通过验证
		//写入会话完成
		return new SimpleAuthenticationInfo(user.getUserName(),user.getUserPassword(),realmName);
    }
	
	
	
	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 * 经测试:并且每次访问需授权资源时都会执行该方法中的逻辑
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//String username=(String)principals.fromRealm(getName()).iterator().next(); 
		String userName=(String)super.getAvailablePrincipal(principals);//获取当前登录的用户名
		if( userName != null ){
			SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
			User user=userService.findUser(userName, userName);
			if(user!=null){//所有能够操作的权限
				info.addStringPermissions(permissionService.findAll());

				return info;
			}
		}
		return null;
	}



    /**
     * 将一些数据放到ShiroSession中,以便于其它地方使用
     * 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
     */
    private void setSession(String key, Object value){
        Subject currentUser = SecurityUtils.getSubject();
        if(null != currentUser){
            Session session = currentUser.getSession();
            if(null != session){
                session.setAttribute(key, value);
            }
        }
    }
}