package com.weizeliang.cms.service.impl;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;


import com.weizeliang.cms.domain.Article;
import com.weizeliang.cms.domain.Category;
import com.weizeliang.cms.service.ArticleService;
import com.weizeliang.cms.service.CategoryService;
import com.weizeliang.utils.DateUtil;
import com.weizeliang.utils.RandomUtil;
import com.weizeliang.utils.StreamUtil;

public class ArticleServiceImplTest  extends JunitParent{

	@Resource
	private ArticleService articleService;
	
	@Resource
	private CategoryService categoryService;
	@Test
	public void testSelects() {
	}

	@Test//批量导入文章
	public void testInsertSelective() {
		
		
		//根据路径初始化一个File对象
		File file = new File("E:/1704DJsoup");
		//获取目录下所有文件
		File[] files = file.listFiles();
//		/遍历文件
		for (File file2 : files) {
		
			Article article = new Article();
			
				//把制造业搞上去.txt
				String name = file2.getName();//获取文件名称,带文件后缀
				//去掉文件后缀
				String title = name.substring(0, name.lastIndexOf("."));
				
			article.setTitle(title);//1封装TITLE
			//封装文章内容 .调用工具类
			String content = StreamUtil.readTextFile(file2);
			article.setContent(content);//2封装文章内容
			//3在文本内容中截取前140个字作为摘要
			
			article.setSummary(content.substring(0, 140));
			//4“点击量”和“是否热门”、“频道”字段要使用随机值
			
			article.setHits(RandomUtil.random(0, Integer.MAX_VALUE-1));//点击量
			
			article.setHot(RandomUtil.random(0, 1));//是否热门
			
			int channelId = RandomUtil.random(1, 9);
			article.setChannelId(channelId);//栏目ID
			//根据栏目ID 查询分类
			List<Category> list = categoryService.selects(channelId);
			//System.out.println("=======================>"+(list.size()-1)+"channelId="+channelId);
			Category category = list.get(RandomUtil.random(0, list.size()-1));
			
			article.setCategoryId(category.getId());//分类ID
			//文章发布日期从2019年1月1日模拟到今天
			Calendar c = Calendar.getInstance();
			c.set(2019, 0, 1, 0, 0, 0);
			Date date = DateUtil.randomDate(c.getTime(), new Date());

			article.setCreated(date);//文章发布日期
			article.setUpdated(date);//修改日期
			article.setUserId(127);
			article.setStatus(1);//默认审核过
			article.setDeleted(0);//默认未删除
			article.setPicture("b41d2b42a3b51f2a4095e19cd5dfc97a.png");
			articleService.insertSelective(article)	;//执行插入
			
		}
		
		
		
		
	  	
		
		
		
	}

	@Test
	public void testSelectByPrimaryKey() {
	}

	@Test
	public void testUpdateByPrimaryKeyWithBLOBs() {
	}

}
