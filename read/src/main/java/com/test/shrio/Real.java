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
	
	
	//��ǰ��¼�û�д�뵽session�е�key
	public static String ACCOUNTKEY="loginUser";
 
    /**
     * ��֤
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		if(!StringUtils.hasText(upToken.getUsername())) throw new UnknownAccountException("���������˺Ż����룡");
		if(upToken.getPassword()==null || upToken.getPassword().length<=0)  throw new UnknownAccountException("���������˺Ż����룡");
		
		//�����û�
		User user=userService.findUser(upToken.getUsername(), upToken.getUsername());
		if(user==null) throw new UnknownAccountException("�˻������ڻ��˻�");
		
		String realmName = this.getName(); 
		//��ʼд��Ự
		setSession(ACCOUNTKEY,user);//д���û���Ϣ���ػ���
		setSession("login", "true");
        //�˴�����ȶ�,�ȶԵ��߼�Shiro����,����ֻ�践��һ����������ص���ȷ����֤��Ϣ  
        //˵���˾��ǵ�һ���������¼�û���,�ڶ���������Ϸ��ĵ�¼����(�����Ǵ����ݿ���ȡ����  
        //����һ��,�����ĵ�¼ҳ���Ͼ�ֻ������ָ�����û����������ͨ����֤
		//д��Ự���
		return new SimpleAuthenticationInfo(user.getUserName(),user.getUserPassword(),realmName);
    }
	
	
	
	/**
	 * ��Ȩ��ѯ�ص�����, ���м�Ȩ�����������û�����Ȩ��Ϣʱ����.
	 * ������:����ÿ�η�������Ȩ��Դʱ����ִ�и÷����е��߼�
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//String username=(String)principals.fromRealm(getName()).iterator().next(); 
		String userName=(String)super.getAvailablePrincipal(principals);//��ȡ��ǰ��¼���û���
		if( userName != null ){
			SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
			User user=userService.findUser(userName, userName);
			if(user!=null){//�����ܹ�������Ȩ��
				info.addStringPermissions(permissionService.findAll());

				return info;
			}
		}
		return null;
	}



    /**
     * ��һЩ���ݷŵ�ShiroSession��,�Ա��������ط�ʹ��
     * ����Controller,ʹ��ʱֱ����HttpSession.getAttribute(key)�Ϳ���ȡ��
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