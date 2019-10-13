package com.weizeliang.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/***
 * 
 * @ClassName: AdminController 
 * @Description: 后台模块控制器
 * @author: weizeliang
 */
@Controller
public class AdminController {
	
	@RequestMapping("admin")
	public String index() {
		return "admin/index";
		
	}

}
