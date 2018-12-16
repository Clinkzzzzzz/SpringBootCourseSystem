package com.ray.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ray.entity.User;
import com.ray.service.RoleService;
import com.ray.service.UserService;

/**
 * SecurityController
 * @author ray
 *
 */
@Controller
@RequestMapping("/security")
public class SecurityController {
    @Autowired
    private UserService userService ;
    
    @Autowired
    private RoleService roleService;

    @RequestMapping("/index")
    public String root() {
        return "index";
    }
    /**
     * /security/toLogin
     * @param map
     * @return
     */
    @GetMapping("/toLogin")
    public String toLogin(Map<String, Object> map) {
        map.put("user", new User());
        map.put("roleList", roleService.loadAll());
        return "login_register";
    }

    /**
     * @param user
     * @param map
     * @return
     * @throws JSONException 
     */
    @PostMapping(value="/login")
    @ResponseBody
    public Map<String,Object> LoginConfig(@RequestBody User user,HttpSession httpSession){
    	//测试
    	System.out.println(user.getUserNo());
    	System.out.println(user.getUserPwd());
    	
    	Map<String, Object> map=new HashMap<>();
    	String flagMessage="fail";
    	String hintMessage="登录失败";
    	//判空
    	if(user.getUserNo().equals("")) {
    		hintMessage="请输入学工号";
    		map.put(flagMessage,hintMessage);
    		return map;
    	}
    	if(user.getUserPwd().equals("")) {
    		hintMessage="请输入密码";
    		map.put(flagMessage, hintMessage);
    		return map;
    	}
    	//验证
    	if(userService.get(user.getUserNo())!=null){//ͨ是否有该学号
            User user1=userService.get(user.getUserNo());//如果有，获取该学号用户
            if(user1.getUserPwd().equals(user.getUserPwd())){//比较密码
            	httpSession.setAttribute("user", user1);
                flagMessage="success";
                hintMessage="登录成功";
            }else {
            	hintMessage="密码错误";
            }
        }else {
        	hintMessage="该用户不存在！";
        }
    	map.put(flagMessage, hintMessage);
    	return map;
    }   
    
    @RequestMapping("toLogin/success")
    public String logined(HttpSession session,Map<String, Object> map) {
    	map.put("user", (User)session.getAttribute("user"));
    	//根据user.role.roleId跳转页面
    	return "newMain";
    }
    
    
    @PostMapping(value="/update")
    @ResponseBody
    public Map<String, Object> update(@RequestParam("userpic") MultipartFile file,User user,Map<String, Object> map,HttpSession session)throws Exception{
    	Map<String, Object> Errormap=new HashMap<>();
    	if(file.getBytes().length>0) {
    		if(file.getSize()>11000000) {
    			Errormap.put("fail", "图片大小超过限制");
    			return Errormap;
    		}
    		user.setUserPic(file.getBytes());
    	}
    	
    	try {
			userService.updateUser(user);
		} catch (Exception e) {
			map.put("exceptionMessage", e.getMessage());
			Errormap.put("fail", "修改用户信息失败："+e.getMessage());
			return Errormap;
		}
    	Errormap.put("success", "修改成功");
    	session.setAttribute("user", userService.get(user.getUserNo()));
    	map.put("user",userService.get(user.getUserNo()));
    	return Errormap;
    }
    
    @GetMapping("/getPic/{userNo}")
    public String getPic(@PathVariable("userNo") String userNO,HttpServletRequest request,HttpServletResponse response)throws Exception{
    	byte[] userPic=userService.getUserPic(userNO);
    	
    	if(userPic==null) {
    		String path=request.getSession().getServletContext().getRealPath("/pics/cat.jpg");
    		FileInputStream fis=new FileInputStream(new File(path));//获取文件cat.jpg
    		userPic=new byte[fis.available()];
    		fis.read(userPic);
    		fis.close();
    	}
    	response.setContentType("image/jpeg");
    	ServletOutputStream sos=response.getOutputStream();
    	sos.write(userPic);
    	sos.flush();
    	sos.close();
    	return null;
    }
    
    @RequestMapping("/toPlay")
    public String toPlay(HttpSession session,Map<String, Object> map) {
    	map.put("rankList",userService.loadUserByGameRank(5));
    	map.put("user", (User)session.getAttribute("user"));
    	return "mygame";
    }
    
    @RequestMapping("/toPlay/record")
    @ResponseBody
    public Map<String, Object> record(@RequestBody User user,HttpSession session){
    	System.out.println(user.getMaxScore());
    	Map<String, Object> map=new HashMap<>();
    	User user1=(User)session.getAttribute("user");
    	if(user1.getMaxScore()<user.getMaxScore()) {
    		user1.setMaxScore(user.getMaxScore());
    		userService.updateUser(user1);
    		map.put("great", "恭喜你，打破了个人最高记录");
    	}else {
    		map.put("soso", "再接再厉");
    	}
    	return map;
    }

    @GetMapping("/mainController")
    public String main(){

        return "newMain";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session,Map<String, Object> map){
    	session.removeAttribute("user");
        return "redirect:/security/toLogin";

    }
    
    @PostMapping(value="/signup")
    @ResponseBody
    public Map<String, Object> signup(Integer roleId,User user) {
    	System.out.println(user.getUserNo());
    	System.out.println(user.getUserName());
    	System.out.println(user.getUserPwd());
    	Map<String, Object> ErrorMap=new HashMap<>();
    	try {
    		user.setRole(roleService.getRoleById(roleId));
		} catch (Exception e) {
			// TODO: handle exception
			ErrorMap.put("SignUpError", e);
			return ErrorMap;
		}
    	if(user.getUserNo().equals("")) {
    		ErrorMap.put("SignUpError", "学工号不能为空哦");
    		return ErrorMap;
    	}
    	
    	if(user.getUserName().equals("")) {
    		ErrorMap.put("SignUpError", "请输入用户名");
    		return ErrorMap;
    	}
    	
    	if(user.getRole().getRoleId()==0) {
    		ErrorMap.put("SignUpError", "请选择用户类型");
    		return ErrorMap;
    	}
    	//不允许注册管理员
    	if(user.getRole().getRoleId()==1) {
    		ErrorMap.put("SignUpError", "无法注册管理员");
    		return ErrorMap;
    	}
    	
    	if(user.getUserPwd().equals("")) {
    		ErrorMap.put("SignUpError", "请输入密码！");
    		return ErrorMap;
    	}
    	
    	if(userService.get(user.getUserNo())==null) {
    			userService.addUser(user);
    			ErrorMap.put("SignUpSuccess", "注册成功！");
    			return ErrorMap;
    	}
    	else {
    		//学号重复
    		ErrorMap.put("SignUpError", "学工号:"+user.getUserNo()+"已经注册，不能使用此学号");
    		return ErrorMap;
    	}
    }
    
    
    @GetMapping("/userlist")
    public String userlist(HttpSession session,Map<String, Object> map,@RequestParam(value="pageNo", required=false, defaultValue="1") String pageNoStr) {
    	User user=(User) session.getAttribute("user");
    	if(user.getRole().getRoleId()==1) {
    		int pageNo=1;
    		pageNo=Integer.parseInt(pageNoStr);
    		if(pageNo<1) {
    			pageNo=1;
    		}
    		PageHelper.startPage(pageNo, 5);
    		List<User> userList=userService.loadAllUser();
    		PageInfo<User> page=new PageInfo<User>(userList);
    		map.put("page", page);
    		map.put("roleList", roleService.loadAll());
    		return "user/user_list";
    	}else {
    		return "error/405";
    	}
    }
    
	@DeleteMapping(value="/remove/{userNo}")
	@ResponseBody
	public Map<String, Object> remove(@PathVariable("userNo")String userNo){
		Map<String, Object> map =new HashMap<>();
		userService.removeUser(userNo);
		map.put("success", "删除成功");
		return map;
	}
	
	@PostMapping(value="/modify")
	@ResponseBody
	public Map<String, Object> modify(@RequestBody User user){
		Map<String, Object> msgMap=new HashMap<>();
		try {
			userService.updateUser(user);
			msgMap.put("success", "修改用户成功");
		}catch (Exception e) {
			msgMap.put("false", e);
			System.out.println(e);
		}
		return msgMap;
	}
	
	@PostMapping("/addUser")
	@ResponseBody
	public Map<String, Object> addUser(@RequestBody User user){
		Map<String, Object> msgMap=new HashMap<>();
		if(userService.get(user.getUserNo())!=null) {
			msgMap.put("fail", "该学工号已存在");
			return msgMap;
		}
		try {
			userService.addUser(user);
			msgMap.put("success","添加用户成功");
		} catch (Exception e) {
			msgMap.put("fail", e);
			System.out.println(e);
			// TODO: handle exception
		}
		return msgMap;
	}
	
    @RequestMapping(value="/getUser/{userNo}")
    @ResponseBody
    public Map<String, Object> getUser(@PathVariable("userNo")String userNo){
    	Map<String, Object> msgMap=new HashMap<>();
    	try {
			msgMap.put("getUser",userService.get(userNo));
			msgMap.put("success", "查询用户成功");
		} catch (Exception e) {
			// TODO: handle exception
			msgMap.put("fail", e);
			System.out.println(e);
		}
    	return msgMap;
    }
}