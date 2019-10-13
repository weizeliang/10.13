package com.weizeliang.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.weizeliang.cms.domain.User;

public interface UserMapper {
	/**
	 * 
	 * @Title: selects 
	 * @Description: 用户列表
	 * @param username
	 * @return
	 * @return: List<User>
	 */
	List<User> selects(@Param("username")String username);
	
	
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByName(@Param("username")String username);
/**
 * 根据用户名查询用户
 * @Title: selectByName 
 * @Description: TODO
 * @param username
 * @return
 * @return: User
 */
}