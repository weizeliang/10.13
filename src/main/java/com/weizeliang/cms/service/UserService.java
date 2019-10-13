package com.weizeliang.cms.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.weizeliang.cms.domain.User;
import com.weizeliang.cms.vo.UserVO;

/**
 * 
 * @ClassName: UserService
 * @Description: TODO
 * @author: weizeliang
 * @date: 2019年9月10日 下午4:20:30
 */
public interface UserService {

	/**
	 * 
	 * @Title: selects
	 * @Description: 用户列表
	 * @param username
	 * @return
	 * @return: List<User>
	 */
	PageInfo<User> selects(String username, Integer page, Integer pageSize);

	User selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(User record);

	int insertSelective(UserVO userVO);

	/**
	 * 
	 * @Title: login
	 * @Description: 登录
	 * @param user
	 * @return
	 * @return: User
	 */
	User login(User user);

}
