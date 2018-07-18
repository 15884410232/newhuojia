package com.test.bean;

/**
 * 用户角色关系表
 * @author Chenqi
 *
 */
public class UserRole extends Base{

	//id
	private String id;
	//用户id
	private String userId;
	//角色Id
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
