package com.test.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.bean.Huojia;
import com.test.mapper.DataMapper;
import com.test.service.DataService;

@Service
public class DataServiceImpl implements DataService{

	@Autowired
	private DataMapper dataMapper;

	@Override
	public String getCount() {
		return dataMapper.getCount();
	}

	@Override
	public List<Map<String, String>> getData(String xinghao) {
		return dataMapper.getData(xinghao);
	}

	@Override
	public List<Huojia> getHuojia(Integer offset) {
		return dataMapper.getHuojia(offset);
	}

	@Override
	public void inserRecord(Map<String, Object> param) {
		dataMapper.inserRecord(param);		
	}
	
}
