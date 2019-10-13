package com.weizeliang.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.weizeliang.cms.dao.SlideMapper;
import com.weizeliang.cms.domain.Slide;
import com.weizeliang.cms.service.SlideService;

@Service
public class SlideServiceImpl implements SlideService {
	
	@Resource
	SlideMapper slideMapper;

	@Override
	public List<Slide> selects() {
		// TODO Auto-generated method stub
		return slideMapper.selects();
	}

}
