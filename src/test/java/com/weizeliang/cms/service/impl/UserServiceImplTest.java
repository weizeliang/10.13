package com.weizeliang.cms.service.impl;

import javax.annotation.Resource;

import org.junit.Test;

import com.github.pagehelper.PageInfo;
import com.weizeliang.cms.domain.User;
import com.weizeliang.cms.service.UserService;


public class UserServiceImplTest extends JunitParent {
	
	@Resource
	private UserService userService;
	

	@Test
	public void testSelects() {
		PageInfo<User> info = userService.selects(null, 1, 3);
		System.out.println(info.getList());
	}

	@Test
	public void testInsertSelective() {
		
		
	}

	@Test
	public void testSelectByPrimaryKey() {
	}

	@Test
	public void testUpdateByPrimaryKeySelective() {
		
		
		User user = new User();
		user.setId(124);
		user.setLocked(1);
		userService.updateByPrimaryKeySelective(user);
		
	}

}
