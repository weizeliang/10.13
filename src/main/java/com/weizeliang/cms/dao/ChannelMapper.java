package com.weizeliang.cms.dao;

import java.util.List;

import com.weizeliang.cms.domain.Channel;
/**
 * 
 * @ClassName: ChannelMapper 
 * @Description: 栏目
 * @author: weizeliang
 * @date: 2019年9月17日 上午10:52:12
 */
public interface ChannelMapper {
	/**
	 * 
	 * @Title: selects 
	 * @Description: 查询所有栏目
	 * @return
	 * @return: List<Channel>
	 */
	List<Channel> selects();
	
    int deleteByPrimaryKey(Integer id);

    int insert(Channel record);

    int insertSelective(Channel record);

    Channel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Channel record);

    int updateByPrimaryKey(Channel record);
}