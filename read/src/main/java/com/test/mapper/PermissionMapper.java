package com.test.mapper;

import java.util.Set;

public interface PermissionMapper {
	/**
	 * 获取所有权限
	 * @return
	 */
	public  Set<String> findAll();
}
