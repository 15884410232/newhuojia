package com.test.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.test.bean.Huojia;

public interface DataMapper {
	
	public List<Map<String,String>> getData(String xinghao);
	public List<Huojia> getHuojia(@Param("offset") Integer offset);
	public String getCount();
	public void inserRecord(Map<String,Object> param);
}
