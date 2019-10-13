package com.weizeliang.cms.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weizeliang.cms.domain.Channel;
import com.weizeliang.cms.service.ChannelService;

@RequestMapping("channel")
@Controller
public class ChannelController {
	@Resource
	private ChannelService channelService;

	
	/**
	 * 返回所有栏目
	 * @Title: selects 
	 * @Description: TODO
	 * @param channelId
	 * @return
	 * @return: List<Category>
	 */
	@ResponseBody
	@GetMapping("selects")
	public List<Channel> selects(){
		
		return channelService.selects();
		
	}
}
