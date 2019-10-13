package com.weizeliang.cms.service;

import java.util.List;

import com.weizeliang.cms.domain.Channel;

public interface ChannelService {

	/**
	 * 
	 * @Title: selects 
	 * @Description: 查询所有栏目
	 * @return
	 * @return: List<Channel>
	 */
	List<Channel> selects();
}
