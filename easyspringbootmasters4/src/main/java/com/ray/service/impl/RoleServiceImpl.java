/**
 * 
 */
package com.ray.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ray.entity.Role;
import com.ray.mapper.RoleMapper;
import com.ray.mapper.UserMapper;
import com.ray.service.RoleService;

/**
 * @author ray
 *
 */

@Service
@Transactional(rollbackFor=Exception.class)
public class RoleServiceImpl implements RoleService{
	@Autowired
	private RoleMapper rolemapper;
	
	@Autowired
	private UserMapper UserMapper; 
	
	@Override
	public void addRoleType(Role role) {
		rolemapper.insert(role);
	}


	@Override
	public void removeRole(Integer roleId) {
		if(UserMapper.loadUserByRoleId(roleId)!=null) {
			UserMapper.removeUserByRoleId(roleId);
		}
		rolemapper.deleteByPrimaryKey(roleId);
	}


	@Override
	public void updateRole(Role role) {
		
		rolemapper.updateByPrimaryKey(role);
	}


	@Override
	public Role getRoleById(Integer roleId) {
		
		return rolemapper.selectByPrimaryKey(roleId);
	}


	@Override
	public List<Role> loadAll() {
		
		return rolemapper.selectAll();
	}

}
