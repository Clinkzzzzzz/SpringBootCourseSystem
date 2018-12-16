/**
 * 
 */
package com.ray.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ray.entity.Role;
import com.ray.entity.User;
import com.ray.service.RoleService;

/**
 * @author ray
 *
 */
@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleService roleService;
	
    @RequestMapping("/rolelist")
    public String rolelist(HttpSession session,Map<String, Object> map) {
    	User user=(User) session.getAttribute("user");
    	if(user.getRole().getRoleId()==1) {
    		map.put("roleList",roleService.loadAll());
    		return "user/role_list";
    	}else {
    		return "error/405";
    	}
    }
    
    @PostMapping(value="/update")
    @ResponseBody
    public Map<String, Object> update(@RequestBody Role role) {
    	Map<String, Object> msgMap=new HashMap<>();
    	try {
    		roleService.updateRole(role);
    		msgMap.put("success", "更新成功");
		} catch (Exception e) {
			msgMap.put("false", e);
			System.out.println(e);
		}
    	return msgMap;
    	
    }
    
    @PostMapping("/addRole")
    @ResponseBody
    public Map<String, Object> addRole(@RequestBody Role role){
    	Map<String, Object> msgMAp=new HashMap<>();
    	try {
    		roleService.addRoleType(role);
    		msgMAp.put("success", "添加成功");
		} catch (Exception e) {
			// TODO: handle exception
			msgMAp.put("fail", e);
			System.out.println(e);
		}
    	return msgMAp;
    }
	
    @RequestMapping(value="/getRole/{roleId}")
    @ResponseBody
    public Map<String, Object> getRole(@PathVariable("roleId")Integer roleId){
    	Map<String, Object> msgMap=new HashMap<>();
    	try {
			msgMap.put("role",roleService.getRoleById(roleId));
			msgMap.put("success", "查询角色成功");
		} catch (Exception e) {
			// TODO: handle exception
			msgMap.put("fail", e);
			System.out.println(e);
		}
    	return msgMap;
    }
    
	@DeleteMapping(value="/remove/{roleId}")
	@ResponseBody
	public Map<String, Object> remove(@PathVariable("roleId")Integer roleId){
		Map<String, Object> map =new HashMap<>();
		if(roleId!=1&&roleId!=2&&roleId!=3)
		{
			roleService.removeRole(roleId);
			map.put("success", "删除成功");
		}
		return map;
	}
	
}
