/**
 * 
 */
package com.ray.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ray.entity.CommentType;
import com.ray.mapper.CommentMapper;
import com.ray.mapper.CommentTypeMapper;
import com.ray.service.CommentTypeService;

/**
 * @author ray
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CommentTypeServiceImpl implements CommentTypeService{

	@Autowired
	private CommentTypeMapper commentTypeMapper;
	
	@Autowired
	private CommentMapper CommentMapper;
	
	@Override
	public void addCommentType(CommentType commentType) {
		commentTypeMapper.insert(commentType);
		
	}

	@Override
	public void removeCommentType(Integer typeId) {
		if(CommentMapper.loadCommentByTypeId(typeId)!=null) {
			CommentMapper.removeCommentByTypeId(typeId);
		}
		commentTypeMapper.deleteByPrimaryKey(typeId);
		
	}


	@Override
	public void updateCommentType(CommentType commentType) {
		commentTypeMapper.updateByPrimaryKey(commentType);
		
	}

	@Override
	public CommentType getCommentTypeById(Integer typeId) {
		
		return commentTypeMapper.selectByPrimaryKey(typeId);
	}

	@Override
	public List<CommentType> loadAll() {
		return commentTypeMapper.selectAll();
	}
	
}
