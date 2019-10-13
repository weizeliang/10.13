package com.weizeliang.cms.service;

import com.github.pagehelper.PageInfo;
import com.weizeliang.cms.domain.Comment;

/**
 * 
    * @ClassName: CommentService
    * @Description: 评论service接口
    * @author DELL
    * @date 2019年9月25日
    *
 */
public interface CommentService {

	/**
	 * 查询评论
	 * @Title: selects 
	 * @Description: TODO
	 * @return
	 * @return: List<Comment>
	 */
	PageInfo<Comment> selects(Integer articleId,Integer page ,Integer pageSize);

	/**
	 * 
	 * @Title: insert 
	 * @Description: 增加评论
	 * @param comment
	 * @return
	 * @return: int
	 */
	int insert(Comment comment);
	
	/**
	 * 
	    * @Title: delete
	    * @Description: 删除评论
	    * @param comment
	    * @param 
	    * @return int   
	    * @throws
	 */
	int delete(Comment comment);
}
