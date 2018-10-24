/**
 * 
 */
package com.ray.entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * @author ray
 *
 */
@Component
@Table(name="tbl_users_role")
public class Role implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer roleId;
	
	private String roleName;
	
	public Integer getRoleId() {
		return roleId;
	}
	
	public void setRoleId(Integer roleId) {
		this.roleId=roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
    @Override
    public String toString() {
        return "RoleType [roleId=" + roleId + ", roleName=" + roleName + "]";
    }
}
