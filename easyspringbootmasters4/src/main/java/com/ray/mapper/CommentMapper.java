/**
 * 
 */
package com.ray.mapper;

import java.util.List;
import java.util.Map;

import com.ray.entity.Comment;

/**
 * @author ray
 *
 */
public interface CommentMapper {
	void addComment(Comment comment);
	
	boolean removeCommentById(Integer commentId);
	
	boolean removeCommentByCourseNo(String courseNo);
	
	boolean removeCommentByTypeId(Integer typeId);
	
	boolean removeCommentByUserNo(String userNo);
	
	void updateComment(Comment comment);
	
	Comment loadCommentById(Integer commentId);
	
	List<Integer> loadCommentByTypeId(Integer typeId); 
	
	List<Comment> loadCommentByCourseNo(String courseNo);
	
	List<Integer> loadCommentByUserNo(String useNo);
	
	List<Comment> loadAllComment();
	
	List<Map<String, Object>> loadCountByType(Integer typeId);
	
	List<Map<String,Object> > loadCount();
}
