package com.weizeliang.cms.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weizeliang.cms.dao.UserMapper;
import com.weizeliang.cms.domain.User;
import com.weizeliang.cms.service.UserService;
import com.weizeliang.cms.util.CMSException;
import com.weizeliang.cms.util.Md5Util;
import com.weizeliang.cms.vo.UserVO;
import com.weizeliang.utils.StringUtil;

@Service
public class UserServiceImpl implements UserService {
	@Resource
	private UserMapper userMapper;

	@Override
	public PageInfo<User> selects(String username, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<User> list = userMapper.selects(username);

		return new PageInfo<User>(list);
	}

	@Override
	public int insertSelective(UserVO userVO) {
		
		//后台校验
		//1.两次密码是否一致
		if(!userVO.getPassword().equals(userVO.getRepassword()))
			throw new CMSException("两次密码不一致");
		//2.用户名是否已经存在
		User user = userMapper.selectByName(userVO.getUsername());
		if(null!=user)
		throw new CMSException("该用户已经存在.请更换用户名!");
		
		//3.对密码进行加密
		String md5Password = Md5Util.md5Encoding(userVO.getPassword());
		userVO.setPassword(md5Password);
		
		//4注册 用户初始默认值
		userVO.setLocked(0);//默认不禁用
		userVO.setCreated(new Date());//注册时间
		userVO.setUpdated(new Date());//更新时间
		userVO.setNickname(userVO.getUsername());
		
		
		
		return userMapper.insertSelective(userVO);
	}

	@Override
	public User selectByPrimaryKey(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		return userMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public User login(User user) {
		
		
		//后台校验
		//1
		if(null==user)
			throw new CMSException("登录失败:用户名不能为空");
		//2
		if(!StringUtil.hasText(user.getUsername()))
		 throw new CMSException("登录失败:用户名不能为空");
		//3
		if(!StringUtil.hasText(user.getPassword()))
			 throw new CMSException("登录失败:密码不能为空");	
		//4 判断用户名是否存在
		User user2 = userMapper.selectByName(user.getUsername());
		if(null==user2)
			 throw new CMSException("登录失败:用户名不存在");		
		
		//5判断密码是否一致
		if(!Md5Util.md5Encoding(user.getPassword()).equals(user2.getPassword()))
			 throw new CMSException("登录失败:密码不正确");
		if(user2.getLocked()==1)
			 throw new CMSException("登录失败:用户账户被停用");
		return user2;
	
	}

}
