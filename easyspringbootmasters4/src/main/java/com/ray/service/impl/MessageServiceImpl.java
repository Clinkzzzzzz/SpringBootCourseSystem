/**
 * 
 */
package com.ray.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ray.entity.Message;
import com.ray.mapper.MessageMapper;
import com.ray.service.MessageService;

/**
 * @author ray
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class MessageServiceImpl implements MessageService{

	@Autowired
	private MessageMapper messageMapper;
	
	@Override
	public Message get(Integer msgId) {
		Message message=new Message();
		message=null;
		if(msgId!=null) {
			message=messageMapper.loadMessageById(msgId);
		}
		return message;
	}


	@Override
	public void addMessage(Message message) {
		messageMapper.addMessage(message);
	}


	@Override
	public boolean removeMessageById(Integer msgId) {
		messageMapper.removeMessageById(msgId);
		return true;
	}


	@Override
	public void updateMessage(Message message) {
		messageMapper.updateMessage(message);
	}


	@Override
	public Message loadMessageById(Integer msgId) {
		Message message =messageMapper.loadMessageById(msgId);
		return message;
	}


	@Override
	public List<Message> loadAllMessage() {
		List<Message> list=messageMapper.loadAllMessage();
		return list;
	}


	@Override
	public List<Message> loadMessageByUserNo(String userNo) {
		List<Message> list=loadMessageByUserNo(userNo);
		return list;
	}

}
