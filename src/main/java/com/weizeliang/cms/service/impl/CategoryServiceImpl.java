package com.weizeliang.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.weizeliang.cms.dao.CategoryMapper;
import com.weizeliang.cms.domain.Category;
import com.weizeliang.cms.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Resource
	private CategoryMapper categoryMapper;

	@Override
	public List<Category> selects(Integer channelId) {
		// TODO Auto-generated method stub
		return categoryMapper.selects(channelId);
	}

}
