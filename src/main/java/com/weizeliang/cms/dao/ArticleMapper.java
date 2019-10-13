package com.weizeliang.cms.dao;

import java.util.Date;
import java.util.List;

import com.weizeliang.cms.domain.Article;

public interface ArticleMapper {
	/**
	 * 
	 * @Title: selects 
	 * @Description: TODO
	 * @param article
	 * @return
	 * @return: List<Article>
	 */
	List<Article> selects(Article article);
	
	

	
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);
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
    

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);
}