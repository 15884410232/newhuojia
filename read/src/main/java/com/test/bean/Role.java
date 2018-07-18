package com.test.bean;

/**
 * juese表
 * @author Chenqi
 *
 */
public class Role extends Base{
	//角色id
	private String roleId;
	//角色名字
	private String roleName;
	//角色描述
	private String roleDesc;
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

}
