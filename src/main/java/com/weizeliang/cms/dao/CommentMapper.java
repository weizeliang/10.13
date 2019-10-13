package com.weizeliang.cms.dao;

import java.util.List;

import com.weizeliang.cms.domain.Comment;
/**
 * 
    * @ClassName: CommentMapper
    * @Description: mapper层 与mapper文件对应
    * @author DELL
    * @date 2019年9月25日
    *
 */
public interface CommentMapper {
	/**
	 * 查询评论
	 * @Title: selects 
	 * @Description: TODO
	 * @return
	 * @param articleId 文章id
	 * @return: List<Comment>
	 */
	List<Comment> selects(Integer articleId);

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
