package com.ray.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ray.entity.User;
import com.ray.mapper.UserMapper;
import com.ray.service.UserService;

/**
 * UserServiceImpl
 *
 * @author ray
 * 
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)//事务管理
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper usermapper;
	
	@Override
	public User get(String userNo) {
		User user = new User();
		user=null;
		if(userNo!=null) {
			user=usermapper.loadUserByNo(userNo);
		}
		return user;
	}


	@Override
	public void addUser(User user) {

		usermapper.addUser(user);
	}


	@Override
	public boolean removeUserByNo(String userNo) {
		
		usermapper.removeUserByNo(userNo);
		return true;
	}


	@Override
	public void updateUser(User user) {
		usermapper.updateUser(user);
	}


	@Override
	public List<User> loadUserByGameRank(Integer rank) {
		List<User> list=usermapper.loadUserByGameRank(rank);
		return list;
	}


	@Override
	public byte[] getUserPic(String userNo) {
		byte[] userPic=null;
		User user=usermapper.loadUserByNo(userNo);
		userPic=user.getUserPic();
		return userPic;
	}
	
	

}