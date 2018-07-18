package com.test.service;

import java.util.Set;

public interface PermissionService {
	/**
	 * 获取所有权限
	 * @return
	 */
	public  Set<String> findAll();
}
