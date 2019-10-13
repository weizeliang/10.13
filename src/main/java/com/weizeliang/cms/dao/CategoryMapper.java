package com.weizeliang.cms.dao;

import java.util.List;

import com.weizeliang.cms.domain.Category;

public interface CategoryMapper {
	/**
	 * 
	 * @Title: selects 
	 * @Description: 查询栏目下的所有分类
	 * @param channelId
	 * @return
	 * @return: List<Category>
	 */
	List<Category> selects(Integer channelId);
	
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
}