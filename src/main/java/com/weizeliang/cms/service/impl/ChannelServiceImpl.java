package com.weizeliang.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.weizeliang.cms.dao.ChannelMapper;
import com.weizeliang.cms.domain.Channel;
import com.weizeliang.cms.service.ChannelService;

@Service
public class ChannelServiceImpl implements ChannelService {
	@Resource
	private ChannelMapper channelMapper;

	@Override
	public List<Channel> selects() {
		// TODO Auto-generated method stub
		return channelMapper.selects();
	}

}
