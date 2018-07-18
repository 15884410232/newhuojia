package com.test.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.test.bean.User;

public interface UserMapper {
	
	public User findUser(@Param("userName")String userName,@Param("password")String password);
	
	public int updateUserInfo(User user);
}
