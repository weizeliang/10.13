package com.weizeliang.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weizeliang.cms.dao.ArticleMapper;
import com.weizeliang.cms.domain.Article;
import com.weizeliang.cms.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {
	@Resource
	private ArticleMapper articleMapper;

	@Override
	public PageInfo<Article> selects(Article article, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<Article> list = articleMapper.selects(article);
		return new PageInfo<Article>(list);
	}

	@Override
	public int insertSelective(Article record) {
		// TODO Auto-generated method stub
		return articleMapper.insertSelective(record);
	}

	@Override
	public Article selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return articleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Article record) {
		return articleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Article selectPreByDate(Article article) {
		// TODO Auto-generated method stub
		return articleMapper.selectPreByDate(article);
	}

	@Override
	public Article selectSufByDate(Article article) {
		// TODO Auto-generated method stub
		return articleMapper.selectSufByDate(article);
	}

}
