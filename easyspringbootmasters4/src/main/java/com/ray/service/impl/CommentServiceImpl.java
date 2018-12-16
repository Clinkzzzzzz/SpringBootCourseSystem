/**
 * 
 */
package com.ray.service.impl;

import java.util.List;
import java.util.Map;

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


	@Override
	public List<Comment> loadAllComment() {
		List<Comment> list=commentMapper.loadAllComment();
		return list;
	}

	@Override
	public List<Map<String, Object>> loadCountByType(Integer typeId) {
		List<Map<String, Object>> list=commentMapper.loadCountByType(typeId);
		return list;
	}


	@Override
	public List<Map<String, Object>> loadCount() {
		List<Map<String,Object> > list=commentMapper.loadCount();
		return list;
	}
	
}
