/**
 * 
 */
package com.ray.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ray.service.CommentService;
import com.ray.service.CourseService;
import com.ray.service.UserService;

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
	private UserService userService;
	
	@Autowired
	private CourseService courseService;
}
