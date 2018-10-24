/**
 * 
 */
package com.ray.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ray.entity.Comment;
import com.ray.mapper.CommentMapper;
import com.ray.service.CommentService;

/**
 * @author ray
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentMapper commentMapper;
	
	@Override
	public Comment get(Integer commentId) {
		Comment comment=new Comment();
		comment=null;
		if(commentId!=null) {
			comment=commentMapper.loadCommentById(commentId);
		}
		return comment;
	}

	@Override
	public void addComment(Comment comment) {
		commentMapper.addComment(comment);
	}


	@Override
	public boolean removeCommentById(Integer commentId) {
		commentMapper.removeCommentById(commentId);
		return true;
	}


	@Override
	public void updateComment(Comment comment) {
		commentMapper.updateComment(comment);
	}

	@Override
	public List<Comment> loadCommentByCourseNo(String courseNo) {
		List<Comment> list=commentMapper.loadCommentByCourseNo(courseNo);
		return list;
	}
	
}
