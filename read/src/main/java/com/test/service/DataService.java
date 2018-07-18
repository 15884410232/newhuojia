package com.test.service;

import java.util.List;
import java.util.Map;

import com.test.bean.Huojia;

public interface DataService {
	public List<Map<String,String>> getData(String xinghao);
	public List<Huojia> getHuojia(Integer offset);
	public String getCount();
	public void inserRecord(Map<String,Object> param);
}
