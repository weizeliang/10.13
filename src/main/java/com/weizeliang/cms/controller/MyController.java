package com.weizeliang.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 
 * @ClassName: MyController 
 * @Description: 个人中心
 * @author: weizeliang
 * @date: 2019年9月17日 上午10:06:45
 */
@RequestMapping("my")
@Controller
public class MyController {
	
	@GetMapping(value = {"","/","index"})
	public String index() {
		return "my/index";
	}

}
