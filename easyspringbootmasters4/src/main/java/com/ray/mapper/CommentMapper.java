/**
 * 
 */
package com.ray.mapper;

import java.util.List;

import com.ray.entity.Comment;

/**
 * @author ray
 *
 */
public interface CommentMapper {
	void addComment(Comment comment);
	
	boolean removeCommentById(Integer commentId);
	
	boolean removeCommentByCourseNo(String courseNo);
	
	void updateComment(Comment comment);
	
	Comment loadCommentById(Integer commentId);
	
	List<Comment> loadCommentByCourseNo(String courseNo);
}
