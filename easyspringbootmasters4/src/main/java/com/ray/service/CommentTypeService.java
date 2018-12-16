/**
 * 
 */
package com.ray.service;

import java.util.List;

import com.ray.entity.CommentType;

/**
 * @author ray
 *
 */
public interface CommentTypeService {
   
	void addCommentType(CommentType commentType);
	
	void removeCommentType(Integer typeId);
	
	void updateCommentType(CommentType commentType);
	
	CommentType getCommentTypeById(Integer typeId);
	
	List<CommentType> loadAll();
}
