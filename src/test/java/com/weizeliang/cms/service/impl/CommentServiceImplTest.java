package com.weizeliang.cms.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.github.pagehelper.PageInfo;
import com.weizeliang.cms.domain.Article;
import com.weizeliang.cms.domain.Comment;
import com.weizeliang.cms.domain.User;
import com.weizeliang.cms.service.ArticleService;
import com.weizeliang.cms.service.CommentService;
import com.weizeliang.utils.DateUtil;
import com.weizeliang.utils.RandomUtil;
import com.weizeliang.utils.StringUtil;

public class CommentServiceImplTest extends JunitParent {
	
	@Resource
	private CommentService commentService;
	@Resource
	private ArticleService articleService;
	@Test
	public void testInsert() {
		
		
	
		User user = new User();
		//1.评论人
		user.setId(127);//
	
		//2.评论时间
		
		//3.评论文章
		//3.1 把要评论的文章查询出来
		  
		Article a = new Article();
		
		a.setStatus(1);//查询出被审核通过的文章
		a.setDeleted(0);//查询出被删除的文章
		PageInfo<Article> info = articleService.selects(a, 1, 11);
		
		List<Article> list = info.getList();
		//3.2从List<Article> 随机拿出一篇文章
	
		
		//4.评论内容
		
		
		for(int i =0 ;i<1000; i++) {
			Comment comment = new Comment();
			//用户
				comment.setUser(user);
			//发布时间从2019-1-1 00:00:00至今随机
				Calendar c = Calendar.getInstance();
				c.set(2019, 0, 1, 0, 0, 0);
				Date date = DateUtil.randomDate(c.getTime(), new Date());
				comment.setCreated(date);
			//文章
				Article article = list.get(RandomUtil.random(0, list.size() -1));
			
				comment.setArticle(article);
				//4.1用随机字符串生成，最少100字以上
				String content =StringUtil.randomChineseString(150);
				comment.setContent(content);
			
			commentService.insert(comment);
		}
		
	
		
		
	}

	@Test
	public void testSelects() {
	}

}
