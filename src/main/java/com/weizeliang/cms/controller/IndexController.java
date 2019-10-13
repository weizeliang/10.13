package com.weizeliang.cms.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.weizeliang.cms.domain.Article;
import com.weizeliang.cms.domain.Category;
import com.weizeliang.cms.domain.Channel;
import com.weizeliang.cms.domain.Slide;
import com.weizeliang.cms.service.ArticleService;
import com.weizeliang.cms.service.CategoryService;
import com.weizeliang.cms.service.ChannelService;
import com.weizeliang.cms.service.SlideService;
import com.weizeliang.cms.util.PageUtil;

/**
 * 
 * @ClassName: IndexController
 * @Description: CMS首页
 * @author: weizeliang
 * @date: 2019年9月19日 上午10:08:43
 */

@Controller
public class IndexController {

	@Resource
	private ChannelService channelService;
	@Resource
	private SlideService slideService;
	@Resource
	private ArticleService articleService;
	
	@Resource
	private CategoryService categoryService;
	


	@GetMapping(value = { "", "/", "index" })
	public String index(Model model, Article article, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "5") Integer pageSize) {
		//0.设置查询条件
		article.setStatus(1);//查询审核通过的文章
		// 1.查询出所有栏目
			List<Channel> channels = channelService.selects();
			model.addAttribute("channels", channels);
		// 2.查询出轮播图-默认显示轮播即用户不点击具体的栏目
			if(article.getChannelId()==null) {
				List<Slide> slides = slideService.selects();
				model.addAttribute("slides", slides);
				
			// 3.默认显示热门文章
				article.setHot(1);// 设置查询条件
				PageInfo<Article> info = articleService.selects(article, page, pageSize);
		
				String pages = PageUtil.page(page, info.getPages(), "/", pageSize);
				model.addAttribute("pages", pages);
				model.addAttribute("hotArticles", info.getList());
			}
			
		  //4.查询栏目下的分类-用户点击栏目则查询其下分类
			if(article.getChannelId()!=null) {
				List<Category> categorys = categoryService.selects(article.getChannelId());
				model.addAttribute("categorys", categorys);
				//栏目下所有文章查询
				PageInfo<Article> info2 = articleService.selects(article, page, pageSize);
				String pages = PageUtil.page(page, info2.getPages(), "/", pageSize);
				model.addAttribute("pages", pages);
				model.addAttribute("articles", info2.getList());
				
			}
			
			
		//4.最新文章 按照文章的发布时间倒序排序,, 只取10条
			//只显示审核过的文章
			Article article2 = new Article();
			article2.setStatus(1);
			PageInfo<Article> info2 = articleService.selects(article2, 1, 10);
			model.addAttribute("lastArticles", info2.getList());
			//封装查询条件
		  model.addAttribute("article", article);
		return "index/index";
	}
}
