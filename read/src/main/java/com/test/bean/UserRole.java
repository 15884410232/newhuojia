package com.test.bean;

/**
 * �û���ɫ��ϵ��
 * @author Chenqi
 *
 */
public class UserRole extends Base{

	//id
	private String id;
	//�û�id
	private String userId;
	//��ɫId
	private String roleId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
}
