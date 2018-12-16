/**
 * 
 */
package com.ray.mapper;

import java.util.List;

import com.ray.entity.Message;

/**
 * @author ray
 *
 */
public interface MessageMapper {
	void addMessage(Message message);
	
	boolean removeMessageById(Integer msgId);
	
	boolean removeMessageByUserNo(String userNo);
	
	void updateMessage(Message message);
	
	List<Message> loadAllMessage();
	
	List<Message> loadMessageByUserNo(String userNo);
	
	Message loadMessageById(Integer msgId);
}
