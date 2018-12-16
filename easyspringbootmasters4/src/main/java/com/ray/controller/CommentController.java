/**
 * 
 */
package com.ray.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ray.entity.Comment;
import com.ray.entity.Course;
import com.ray.entity.User;
import com.ray.service.CommentService;
import com.ray.service.CommentTypeService;
import com.ray.service.CourseService;


/**
 * @author ray
 *
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CommentTypeService commentTypeService;
/*	
	@ModelAttribute
	public void getComment(@RequestParam(value="commentId",required=false)Integer commentId,
			Map<String, Object> map,Comment comment) {
		comment=commentService.get(commentId);
		if(commentId!=null&&comment!=null) {
			map.put("comment", comment);
		}
	}
	*/
	
	@RequestMapping("/list/{courseNo}")
	public void list(@PathVariable("courseNo") String courseNo,Map<String, Object> map){
		System.out.println("--------------courseNo:"+courseNo);
		List<Comment> list=commentService.loadCommentByCourseNo(courseNo);
		if(list==null||list.isEmpty()) {
			map.put("commentMsg", "暂无评论");
		}else {
			map.put("commentList", list);
			map.put("commentMsg", courseNo+"号课程有"+list.size()+"条评论");
		}
	}
	
	@DeleteMapping(value="/remove/{commentId}")
	@ResponseBody
	public Map<String, Object> remove(@PathVariable("commentId")Integer commentId){
		Map<String, Object> map =new HashMap<>();
		commentService.removeCommentById(commentId);
		map.put("success", "删除成功");
		return map;
	}
	
    @PostMapping("/addComment")
    @ResponseBody
    public Map<String, Object> addComment(Integer commtentTypeId,String courseNo,Comment comment,Map<String, Object> map,HttpSession httpSession){
    	Map<String, Object> msgMap = new HashMap<>();
    	//String courseNo=comment.getCourse().getCourseNo();
    	Course course =courseService.loadCourseByNo(courseNo);
    	
    	User user=(User) httpSession.getAttribute("user");
    	if(user==null) {
    		msgMap.put("fail", "你没有足够的权限");
    		return msgMap;
    	}
    	comment.setUser(user);
    	comment.setCourse(course);
    	comment.setCommentType(commentTypeService.getCommentTypeById(commtentTypeId));
    	
    	Timestamp createTime = new Timestamp(System.currentTimeMillis());
    	comment.setCreateTime(createTime);
    	
    	if(comment.getContent().equals("")) {
    		msgMap.put("fail", "评论不能为空");
    		return msgMap;
    	}
    	
    	commentService.addComment(comment);
    	msgMap.put("success", "评论发表成功");
    	return msgMap;
    }
    
    @RequestMapping("/getTypeCnt/{typeId}")
    @ResponseBody
    public List< Map<String, Object> > getTypeCnt(@PathVariable("typeId")Integer typeId){
    	return commentService.loadCountByType(typeId);
    }
    
    @RequestMapping("/toReport")
    public String toReport() {
    	return "comment/report1";
    }
    
    @RequestMapping("/getCnt")
    @ResponseBody
    public List<Map<String,Object> > getCnt(){
    	return commentService.loadCount();
    }
}
