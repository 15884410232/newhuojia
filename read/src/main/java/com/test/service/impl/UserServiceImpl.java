package com.test.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.bean.User;
import com.test.mapper.UserMapper;
import com.test.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;

	@Override
	public User findUser(String userName, String password) {
		return userMapper.findUser(userName, password);
	}

	@Override
	public int updateUserInfo(User user) {
		return userMapper.updateUserInfo(user);
	}
	
}
