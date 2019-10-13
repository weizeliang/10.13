package com.weizeliang.cms.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.weizeliang.cms.domain.User;
import com.weizeliang.cms.service.UserService;
import com.weizeliang.cms.util.PageUtil;

@RequestMapping("user")
@Controller
public class UserController {
	@Resource
	private  UserService userService;
	
	@RequestMapping("selects")
	public String users(Model model ,@RequestParam(defaultValue = "")String username,
			@RequestParam(defaultValue = "1")Integer page,
			@RequestParam(defaultValue = "3")Integer pageSize) {
		
		PageInfo<User> info = userService.selects(username, page, pageSize);
		
		String pages = PageUtil.page(page, info.getPages(), "/user/selects?username="+username, pageSize);
		
		
		model.addAttribute("users", info.getList());//集合对象
		model.addAttribute("username", username);//查询条件
		model.addAttribute("pages", pages);//分页
		
		return "admin/users";
		
	}
	/**
	 * 
	 * @Title: update 
	 * @Description: 更新用户
	 * @param user
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@PostMapping("update")
	public boolean update(User user) {
		return userService.updateByPrimaryKeySelective(user)>0;
	}

}
