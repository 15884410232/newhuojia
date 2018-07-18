package com.test.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.mapper.PermissionMapper;
import com.test.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService{
	
	@Autowired
	private PermissionMapper permissionMapper;

	
	@Override
	public Set<String> findAll() {
		return permissionMapper.findAll();
	}

}
