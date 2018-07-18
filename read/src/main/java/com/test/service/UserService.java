package com.test.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.test.bean.User;

public interface UserService {
	public User findUser(String userName,String password);
	
	public int updateUserInfo(User user);
}
