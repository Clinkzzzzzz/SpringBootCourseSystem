/**
 * 
 */
package com.ray.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import org.springframework.stereotype.Component;

/**
 * @author ray
 *
 */
@Component
public class Message implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private Integer msgId;
	
	private String content;
	
	private Timestamp createTime;
	
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getMsgId() {
		return msgId;
	}

	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}
	
}
