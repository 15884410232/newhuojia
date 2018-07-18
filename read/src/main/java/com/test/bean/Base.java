package com.test.bean;

/**
 * 基础字段
 * @author Chenqi
 *
 */
public class Base {
	//创建时间
	private String createTime;
	//更新时间
	private String updateTime;
	//版本
	private String version;
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}

}
