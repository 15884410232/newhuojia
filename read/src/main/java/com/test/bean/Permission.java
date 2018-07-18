package com.test.bean;

/**
 * 权限表
 * @author Chenqi
 *
 */
public class Permission extends Base{
	//权限id
	private String permissionId;
	//用户id
	private String resourceId;
	//jueseId
	private String roleId;
	public String getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}

	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}
