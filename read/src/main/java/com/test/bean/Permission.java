package com.test.bean;

/**
 * Ȩ�ޱ�
 * @author Chenqi
 *
 */
public class Permission extends Base{
	//Ȩ��id
	private String permissionId;
	//�û�id
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
