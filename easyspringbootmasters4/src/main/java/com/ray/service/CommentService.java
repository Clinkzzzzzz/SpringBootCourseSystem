/**
 * 
 */
package com.ray.service;

import java.util.List;
import java.util.Map;

import com.ray.entity.Comment;

/**
 * @author ray
 *
 */
public interface CommentService {
	Comment get(Integer commentId);
	
	void  addComment(Comment comment);
	
	boolean removeCommentById(Integer commentId);
	
	void updateComment(Comment comment);
	
	List<Comment> loadCommentByCourseNo(String courseNo);
	
	List<Comment> loadAllComment();
	
	List<Map<String, Object>> loadCountByType(Integer typeId);
	
	List<Map<String,Object> > loadCount();
}
