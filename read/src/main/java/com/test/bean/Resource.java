package com.test.bean;

/**
 * 资源表
 * @author Chenqi
 *
 */
public class Resource extends Base{
	
	//资源id
	private String resourceId;
	//资源地址
	private String resource;
	//资源名字
	private String resourceName;
	//资源描述
	private String resourceDesc;
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getResourceDesc() {
		return resourceDesc;
	}
	public void setResourceDesc(String resourceDesc) {
		this.resourceDesc = resourceDesc;
	}
	
}
