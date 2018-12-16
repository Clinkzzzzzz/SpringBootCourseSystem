/**
 * 
 */
package com.ray.service;

import java.util.List;

import com.ray.entity.Message;

/**
 * @author ray
 *
 */
public interface MessageService {
	Message get(Integer msgId);
	
	void addMessage(Message message);
	
	boolean removeMessageById(Integer msgId);
	
	void updateMessage(Message message);
	
	Message loadMessageById(Integer msgId);
	
	List<Message> loadAllMessage();
	
	List<Message> loadMessageByUserNo(String userNo);
}
