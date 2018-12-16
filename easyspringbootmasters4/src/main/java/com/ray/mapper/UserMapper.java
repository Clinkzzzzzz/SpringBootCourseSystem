package com.ray.mapper;


import java.util.List;
import com.ray.entity.*;

/**
 * UserMapper
 * 
 * @author ray
 * 
 *
 */
public interface UserMapper {
	/**
	 * 
	 * @param user
	 */
	void addUser(User user);
	
	/**
	 * 
	 * @param userNo
	 * @return
	 */
	boolean removeUserByNo(String userNo);
	
	/**
	 * 
	 * @param typeId
	 * @return
	 */
	boolean removeUserByRoleId(Integer roleId);
	
	/**
	 *
	 * @param user
	 */
	void updateUser(User user);
	
	/**
	 * 
	 * @param userNo
	 * @return
	 */
	User loadUserByNo(String userNo);
	
	/**
	 * 
	 * @param roleId
	 * @return
	 */
	List<String> loadUserByRoleId(Integer roleId);
	/**
	 * 根据游戏最高分降序选出前n名名
	 * @return
	 */
	List<User> loadUserByGameRank(Integer rank);
	
	List<User> loadAllUser();
}		
