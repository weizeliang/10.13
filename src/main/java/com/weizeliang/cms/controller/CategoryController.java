package com.weizeliang.cms.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weizeliang.cms.domain.Category;
import com.weizeliang.cms.service.CategoryService;

@RequestMapping("category")
@Controller
public class CategoryController {
	@Resource
	private CategoryService categoryService;

	
	/**
	 * 返回栏目下的分类
	 * @Title: selects 
	 * @Description: TODO
	 * @param channelId
	 * @return
	 * @return: List<Category>
	 */
	@ResponseBody
	@GetMapping("selects")
	public List<Category> selects(Integer channelId){
		
		return categoryService.selects(channelId);
		
	}
}
