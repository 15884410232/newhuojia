package com.test.bean;

/**
 * �û���Ϣ
 * @author Chenqi
 *
 */
public class User extends Base{

	//�û�Id
	private String userId;
	//�û���
	private String userName;
	//�û�����
	private String userPassword;
	//��ʵ����
	private String userRealName;
	//�û��绰
	private String userPhone;
	//�û���ɫ
	private String userRole;

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

}
