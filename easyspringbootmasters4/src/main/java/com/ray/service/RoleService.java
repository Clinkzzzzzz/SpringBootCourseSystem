/**
 * 
 */
package com.ray.service;

import java.util.List;

import com.ray.entity.Role;

/**
 * @author ray
 *
 */
public interface RoleService {
	void addRoleType(Role role);
	
	void removeRole(Integer roleId);
	
	void updateRole(Role role);
	
	Role getRoleById(Integer roleId);
	
	List<Role> loadAll();
}
