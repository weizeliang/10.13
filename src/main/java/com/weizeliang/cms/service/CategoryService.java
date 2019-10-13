package com.weizeliang.cms.service;

import java.util.List;

import com.weizeliang.cms.domain.Category;

public interface CategoryService {

	/**
	 * 
	 * @Title: selects 
	 * @Description: 查询栏目下的所有分类
	 * @param channelId
	 * @return
	 * @return: List<Category>
	 */
	List<Category> selects(Integer channelId);
}
