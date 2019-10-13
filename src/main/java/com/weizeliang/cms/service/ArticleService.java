package com.weizeliang.cms.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.weizeliang.cms.domain.Article;

public interface ArticleService {
	
	
	 /**
     * 
     * @Title: selectPreByPrimaryKey 
     * @Description: TODO
     * @param date
     * @return
     * @return: Article
     */
    Article selectPreByDate(Article article);
    
    /**
     * 
     * @Title: selectPreByPrimaryKey 
     * @Description: TODO
     * @param date
     * @return
     * @return: Article
     */
    Article selectSufByDate(Article article);

	/**
	 * 
	 * @Title: selects 
	 * @Description: TODO
	 * @param article
	 * @return
	 * @return: List<Article>
	 */
	PageInfo<Article> selects(Article article,Integer page,Integer pageSize);
	


    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Article record);

}
