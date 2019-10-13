package com.weizeliang.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weizeliang.cms.dao.CommentMapper;
import com.weizeliang.cms.domain.Comment;
import com.weizeliang.cms.service.CommentService;
/**
 * 
    * @ClassName: CommentServiceImpl
    * @Description: 评论serviceimpl实现类
    * @author DELL
    * @date 2019年9月25日
    *
 */
@Service
public class CommentServiceImpl implements CommentService {
	
	@Resource
	private CommentMapper commentMapper;



	@Override
	public int insert(Comment comment) {
		// TODO Auto-generated method stub
		return commentMapper.insert(comment);
	}

	
	@Override
	public PageInfo<Comment> selects(Integer articleId,Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		
		List<Comment> list = commentMapper.selects(articleId);
		
		return new PageInfo<Comment>(list);
	}


	@Override
	public int delete(Comment comment) {
		// TODO Auto-generated method stub
		return commentMapper.delete(comment);
	}
	

	

}
