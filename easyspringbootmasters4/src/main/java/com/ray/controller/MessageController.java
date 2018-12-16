/**
 * 
 */
package com.ray.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ray.entity.Message;
import com.ray.entity.User;
import com.ray.service.MessageService;

/**
 * @author ray
 *
 */
@Controller
@RequestMapping("/message")
public class MessageController {
	@Autowired
	private MessageService messageService;
	
	@RequestMapping("/list")
    public String list(Map<String, Object> map,HttpServletRequest request) {
		List<Message> list=messageService.loadAllMessage();
		int length=list.size();
		String requestContext=request.getContextPath();
		for(int i=0;i<length;i++) {
			String temp=replace_em(list.get(i).getContent(),requestContext);
			list.get(i).setContent(temp);
		}
		map.put("length", length);
		map.put("messageList", list);
		return "message/list_message1";
	}
	
	/*@RequestMapping(value="/getList")
	@ResponseBody
	public Map<String, Object> getList(){
		Map<String, Object> msgMap=new HashMap<>();
		List<Message> messagelist=messageService.loadAllMessage();
		msgMap.put("list", messagelist);
		msgMap.put("success", "获取留言列表成功");
		return msgMap;
	}*/
	
	
	@DeleteMapping(value="/remove/{msgId}")
	@ResponseBody
	public Map<String, Object> remove(@PathVariable("msgId")Integer msgId){
		Map<String, Object> map =new HashMap<>();
		messageService.removeMessageById(msgId);
		map.put("success", "删除成功");
		return map;
	}
	
    @PostMapping("/addMessage")
    @ResponseBody
    public Map<String, Object> addComment(Message message,Map<String, Object> map,HttpSession httpSession){
    	Map<String, Object> msgMap = new HashMap<>();
    	
    	User user=(User) httpSession.getAttribute("user");
    	if(user==null) {
    		msgMap.put("fail", "你没有足够的权限");
    		return msgMap;
    	}
    	message.setUser(user);
    	
    	Timestamp createTime = new Timestamp(System.currentTimeMillis());
    	message.setCreateTime(createTime);
    	
    	messageService.addMessage(message);
    	msgMap.put("success", "留言发表成功");
    	return msgMap;
    }
    
	public String replace_em(String string,String requsetContext) {
		string = string.replaceAll("<", "&lt;");
		string = string.replaceAll(">", "&gt;");
		string = string.replaceAll("\\n", "<br/>");
		string = string.replaceAll("\\[(em)(_)([1-9][0-9]{0,2})\\]", "<img src=\""+requsetContext+"/pics/arclist/$3.gif\" border=\"0\" />");
		return  string;
	}
	
}
